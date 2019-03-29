package com.wizarpos.emvsample.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.iisysgroup.poslib.ISO.GTMS.GtmsKeyProcessor;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.keys.PinBlockEncryptionUtil;
import com.wizarpos.emvsample.keys.TrippleDes;
import com.wizarpos.emvsample.transaction.TransDefine;
import com.wizarpos.jni.PinPadCallbackHandler;
import com.wizarpos.jni.PinPadInterface;
import com.wizarpos.util.AppUtil;
import com.wizarpos.util.ByteConvert;
import com.wizarpos.util.StringUtil;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class InputPINActivity extends FuncActivity implements PinPadCallbackHandler
{
	private final int PINPAD_CANCEL  = -65792;
	private final int PINPAD_TIMEOUT = -65538;
	private String  clearPinKey;
	private String transportKey;

	private String onlinePinString;
	
	private TextView textTitle  = null;
	private TextView textPin = null;
	private Button   buttonBack = null;
    private Button   buttonMore = null;
    
	private Handler mHandler;
	private Thread mReadPINThread;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_input_pin);
    	// title
        textTitle = (TextView)findViewById(R.id.tAppTitle);
         setTitle(textTitle);

		textPin = (TextView) findViewById(R.id.input_pin);
		if(textPin != null) textPin.setText("");

		TextView amt = findViewById(R.id.amt);
		if(appState.balanceEnc){
			amt.setText("Balance Enquiry");
			appState.trans.setBalanceTxn(true
			);
		}else if(appState.refund){
			amt.setText("Refun of NGN "+AppUtil.formatAmount(appState.trans.getTransAmount()));
			appState.trans.setRefundTxn(true);

		}
		else  if(appState.purchaseWithCashBack){
			amt.setText("Total NGN "+AppUtil.formatAmount(appState.trans.getTransAmount() + appState.trans.getOthersAmount()));
		}
		else if(appState.revarsal){
			amt.setText("Reversal NGN " + AppUtil.formatAmount(appState.trans.getTransAmount()));
		}
		else{
			amt.setText("NGN "+AppUtil.formatAmount(appState.trans.getTransAmount()));
			appState.trans.setBalanceTxn(false);
			appState.trans.setRefundTxn(false);
		}

	    buttonBack = (Button)findViewById(R.id.btn_back);
        buttonBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
        
        buttonMore = (Button)findViewById(R.id.btn_more);
        buttonMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
        
    	mHandler = new Handler() 
		{ 
			public void handleMessage(Message msg)
			{ /*这里是处理信息的方法*/ 
				switch (msg.what)
				{ 
				case PIN_SUCCESS_NOTIFIER:
					setResult(Activity.RESULT_OK, getIntent());
					break;
				case PIN_ERROR_NOTIFIER:
					appState.setErrorCode(R.string.prepTerminal);
					break;
				case PIN_CANCELLED_NOTIFIER:
					appState.setErrorCode(R.string.error_user_cancelled);
					break;
				case PIN_TIMEOUT_NOTIFIER:
					appState.setErrorCode(R.string.error_input_timeout);
					break;
				} 
				exit();
			}
		};

    }

	@Override
	protected void onStart()
	{
		super.onStart();
		mReadPINThread=new ReadPINThread(); 
		mReadPINThread.start();
	}
	
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
    }
    
    @Override
    public void onBackPressed(){

    }
    
    private void notifyPinSuccess()
    {
    	Message msg = new Message();
    	msg.what = PIN_SUCCESS_NOTIFIER;
    	mHandler.sendMessage(msg);
    }
    
    private void notifyPinError()
    {
    	Message msg = new Message();
    	msg.what = PIN_ERROR_NOTIFIER;
    	mHandler.sendMessage(msg);
		resetPurchase();
    }
    
    private void notifyPinCancel()
    {
    	Message msg = new Message();
    	msg.what = PIN_CANCELLED_NOTIFIER;
    	mHandler.sendMessage(msg);
    	resetPurchase();
    }
    
    private void notifyPinTimeout()
    {
    	Message msg = new Message();
    	msg.what = PIN_TIMEOUT_NOTIFIER;
    	mHandler.sendMessage(msg);
		resetPurchase();
    }

	protected char[] stars = "●●●●●●●●●●●●●●●●".toCharArray();
	public static final int PIN_AMOUNT_SHOW  = 0x10000;
	public static final int PIN_KEY_CALLBACK = 0x10001;
	private Handler commHandler = createCommHandler();

	public void processCallback(byte[] data) {
		Log.i("processCallback", "" + data.length);
		if(data != null)
			commHandler.obtainMessage(PIN_KEY_CALLBACK, data[0], data[1]).sendToTarget();
	}

	@SuppressLint("HandlerLeak")
	protected Handler createCommHandler()
	{	// 无 Pinpad时跳过. DuanCS@[20141001]
		return new Handler()
		{
			public void handleMessage(Message msg)
			{ /* 这里是处理信息的方法 */
				switch (msg.what)
				{
				case PIN_AMOUNT_SHOW:	// 其值已通过onFlush显示. DuanCS@[20150907]
//					setTextById(R.id.amount, msg.obj.toString());
					textPin.setText(msg.obj.toString());	// 这一行也不会执行, 因为 Pinpad.showText() 不会触发回调... DuanCS@[20150912]
					break;
				case PIN_KEY_CALLBACK:
					onlinePinString += msg.arg1;
					Log.i("ok3", msg.arg2 + " : "+ onlinePinString);
					textPin.setText(stars, 0, msg.arg1 & 0x0F);
					break;
				}
			}
		};
	}

	class ReadPINThread extends Thread
    {
    	public void run() 
    	{ 
    		byte[] pinBlock = new byte[8];
    		byte[] zeroPAN = appState.trans.getPAN().getBytes();
    		
    		// masterKey is new byte[]{'1','1','1','1','1','1','1','1' }
			//Q1上不支持单倍长PINKEY
			if(appState.nibssData == null)
			{
				if(debug)Log.d(APP_TAG, "pinpad open error");
				notifyPinError();
				PinPadInterface.close();
				appState.pinpadOpened = false;
				return;
			}

			String clearMaster = GtmsKeyProcessor.getMasterKey(appState.nibssData.getKeyHolder().getMasterKey(),false);
			try {
				clearPinKey = GtmsKeyProcessor.decryptKey(appState.nibssData.getKeyHolder().getPinKey(), clearMaster);
				Log.i("clearkey",clearPinKey);
			} catch (Exception e) {
				e.printStackTrace();
				notifyPinError();
				return;
			}


			byte[] defaultPINKey = parseHexBinary(appState.nibssData.getKeyHolder().getPinKey());
			byte[] defaultMasterKey = StringUtil.hex2byte(appState.nibssData.getKeyHolder().getMasterKey(),0,16);



			int ret = -1;
    		if(appState.pinpadOpened == false)
    		{
    			if(PinPadInterface.open() < 0)
    		    {
    				notifyPinError();
    				return;
    		    }
			    appState.pinpadOpened = true;

				PinPadInterface.setupCallbackHandler(InputPINActivity.this);
    		}




			ret = PinPadInterface.updateUserKey(1,
                                                0,
                                                defaultPINKey,
                                                defaultPINKey.length);
    		//PinPadInterface.selectKey(2,1,0,PinPadInterface.ALGORITH_3DES);
			PinPadInterface.setKey(2, 1, 0, PinPadInterface.ALGORITH_3DES);

			if(ret < 0)
    		{
    			if(debug)Log.d(APP_TAG, "pinpad open error");
    			notifyPinError();
    			PinPadInterface.close();
    			appState.pinpadOpened = false;
    			return;
    		}
			//Q1上不支持单倍长PINKEY
//    		PinPadInterface.setKey(2, appState.terminalConfig.getKeyIndex(), 0, appState.terminalConfig.getKeyAlgorithm());
    		if(appState.trans.getTransAmount() > 0)
    		{
	    		byte[] text = (AppUtil.formatAmount(appState.trans.getTransAmount())).getBytes();
	    		PinPadInterface.setText(0, text, text.length, 0);
    		}
    		ret = PinPadInterface.inputPIN(zeroPAN, zeroPAN.length, pinBlock, 60000, 0);
    		if(ret < 0)
    		{
    			if(ret == PINPAD_CANCEL)
    			{
    				notifyPinCancel();
    			}
    			else if(ret == PINPAD_TIMEOUT)
    			{
    				notifyPinTimeout();
    			}
    			else{
	    			notifyPinError();
    			}
    			PinPadInterface.close();
    			appState.pinpadOpened = false;
    			return;
    		}
    		if(ret == 0)
    		{
    			appState.trans.setPinEntryMode(CAN_PIN);
    		}
    		else
    		{
	    		appState.trans.setPinBlock(pinBlock);
	    		appState.trans.setPinEntryMode(CAN_PIN);
				appState.clearPin = clearPinKey;
	    		/*if(pinBlock != null){
					try {
						String clearPinBlock = PinBlockEncryptionUtil.getHexString(PinBlockEncryptionUtil.encryptPinBlock(appState.trans.getPAN(),getPinCount(onlinePinString)));
						String encPinBlock = TrippleDes.encrypt(clearPinBlock, clearPinKey);
						appState.clearPin = clearPinKey;
						appState.trans.setPinBlock(StringUtil.hexString2bytes(encPinBlock));
					} catch (IllegalBlockSizeException e) {
						e.printStackTrace();
					}
				}*/
    		}
    		notifyPinSuccess();
			PinPadInterface.close();
			appState.pinpadOpened = false;
		} 
    }

	public byte[] parseHexBinary(String s) {
		final int len = s.length();

		// "111" is not a valid hex encoding.
		if( len%2 != 0 )
			throw new IllegalArgumentException("hexBinary needs to be even-length: "+s);

		byte[] out = new byte[len/2];

		for( int i=0; i<len; i+=2 ) {
			int h = hexToBin(s.charAt(i  ));
			int l = hexToBin(s.charAt(i+1));
			if( h==-1 || l==-1 )
				throw new IllegalArgumentException("contains illegal character for hexBinary: "+s);

			out[i/2] = (byte)(h*16+l);
		}

		return out;
	}

	private static int hexToBin( char ch ) {
		if( '0'<=ch && ch<='9' )    return ch-'0';
		if( 'A'<=ch && ch<='F' )    return ch-'A'+10;
		if( 'a'<=ch && ch<='f' )    return ch-'a'+10;
		return -1;
	}

	private String getPinCount(String word){
		if (word.length() == 4) {
			return word;
		} else if (word.length() > 4) {
			return word.substring(word.length() - 4);
		} else {
			// whatever is appropriate in this case
			throw new IllegalArgumentException("word has less than 3 characters!");
		}
	}

}