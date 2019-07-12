package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ProgressBar;

import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.transaction.TransDefine;
import com.wizarpos.jni.MsrInterface;
import com.wizarpos.jni.PinPadInterface;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import static com.cloudpos.jniinterface.EMVJNIInterface.close_reader;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_preprocess_qpboc;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_kernel_type;
import static com.cloudpos.jniinterface.EMVJNIInterface.get_card_type;
import static com.cloudpos.jniinterface.EMVJNIInterface.open_reader;


public class FunctionsAll implements Constants
{
	protected static WeakReferenceHandler mHandler = new WeakReferenceHandler(null);
    protected static Socket socket = null;
    protected static FunctionsAll funstan;
    protected static MainApp appState = null;
    Activity context;


    ProgressBar progressBar;
    
    protected static Thread msrThread = null;
    protected static boolean msrThreadActived = false;
    protected static boolean readMSRCard = false;
    protected static boolean msrClosed = true;
    
    protected static boolean contactOpened = false;
    protected static boolean contactlessOpened = false;
    
    protected static Thread mOpenPinpadThread = null;
    
    
    
	private Timer mTimerSeconds;
    private int mIntIdleSeconds;
    private boolean mBoolInitialized=false;
	private byte mTimerMode = 0;
    private int idleTimer = DEFAULT_IDLE_TIME_SECONDS;
    
	public void handleMessageSafe(Message msg){}

	protected static class WeakReferenceHandler extends Handler{

	    private WeakReference<FunctionsAll> mActivity;
	    public WeakReferenceHandler(FunctionsAll activity){
	        mActivity = new WeakReference<FunctionsAll>(activity);
        }

        public void setFunActivity(FunctionsAll activity){
            mActivity = new WeakReference<FunctionsAll>(activity);
        }
		@Override
		public void handleMessage(Message msg) {
			FunctionsAll activity = mActivity.get();
			if(activity != null){
				activity.handleMessageSafe(msg);
			}
		}
	}

	public void capkChecksumErrorDialog(Context context) 
	{
		Builder builder = new Builder(context);
		builder.setTitle("提示");
		builder.setMessage("CAPK:" + appState.failedCAPKInfo + "\nChecksum Error");

		builder.setPositiveButton("确认", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
 	public static void emvProcessCallback(byte[] data)
	{
		if(debug)Log.d(APP_TAG, "emvProcessNextCompleted");
		appState.trans.setEMVStatus(data[0]);
		appState.trans.setEMVRetCode(data[1]);
		
     	Message msg = new Message();
     	msg.what = EMV_PROCESS_NEXT_COMPLETED_NOTIFIER;
     	mHandler.sendMessage(msg);
	}
    
	public static void cardEventOccured(int eventType)
	{
 		if(debug)Log.d(APP_TAG, "get cardEventOccured");
 		Message msg = new Message();
 		if(eventType == SMART_CARD_EVENT_INSERT_CARD)
 		{
 			appState.cardType = get_card_type();
 			if(debug)Log.d(APP_TAG, "cardType = " + appState.cardType);
 			
 			if(appState.cardType == CARD_CONTACT)
 			{
 				msg.what = CARD_INSERT_NOTIFIER;
 				mHandler.sendMessage(msg);
 			}
 			else if(appState.cardType == CARD_CONTACTLESS)
 			{
 				msg.what = CARD_TAPED_NOTIFIER;
 				mHandler.sendMessage(msg);
 			}
 			else{
 				appState.cardType = -1;
 			}
 		}
 		else if(eventType == SMART_CARD_EVENT_POWERON_ERROR)
 		{
 			appState.cardType = -1;
 			msg.what = CARD_ERROR_NOTIFIER;
			mHandler.sendMessage(msg);
 		}
 		else if(eventType == SMART_CARD_EVENT_REMOVE_CARD)
 		{
 			appState.cardType = -1;
 		}
 		else if(eventType == SMART_CARD_EVENT_CONTALESS_HAVE_MORE_CARD)
 		{
 			appState.cardType = -1;
 			msg.what = CONTACTLESS_HAVE_MORE_CARD_NOTIFIER;
			mHandler.sendMessage(msg);
 		}

	}


    public void onCreate(Activity context)
    {
    	this.context = context;
        appState = ((MainApp)context.getApplicationContext());
    }



   
    
    public void onTouch()
    {
        //if(debug)Log.d(APP_TAG, "mIntIdleSeconds = 0");
    	mIntIdleSeconds=0;
    }

    public void cancelIdleTimer()
    {
    	mIntIdleSeconds=0;
    	if (mTimerSeconds != null)
        {
            if(debug)Log.d(APP_TAG, "timer cancelled");
        	mTimerSeconds.cancel();
        }
    }
    
    public void startIdleTimer(byte timerMode, int timerSecond)
    {
    	idleTimer = timerSecond;
    	mTimerMode = timerMode;
        //initialize idle counter
        mIntIdleSeconds=0;
    	if (mBoolInitialized == false)
        {
        	if(debug)Log.d(APP_TAG, "timer start");
    		mBoolInitialized = true;
            //create timer to tick every second
            mTimerSeconds = new Timer();
            mTimerSeconds.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    mIntIdleSeconds++;
                    if (mIntIdleSeconds == idleTimer)
                    {
                    	if(mTimerMode == TIMER_IDLE)
                    	{
                    		go2Idle();
                    	}
                    	else
                    	{
                    		if(appState.needClearPinpad == true)
             		        {
             		        	// clear pinpad
             		        	appState.needClearPinpad = false;
                 	    		PinPadInterface.setText(0, null, 0, 0);
                 	    		PinPadInterface.setText(1, null, 0, 0);
             		        }
            			    
                    		context.setResult(Activity.RESULT_CANCELED, context.getIntent());
	                    	exit();
                    	}
                    }
                }
            }, 0, 1000);
        }
    }

    protected boolean connectSocket(String ip, int port, int timeout)
    {
	    try {
	    	socket = new Socket(); 
	    	socket.setSoTimeout(timeout); // 设置读超时
	    	SocketAddress remoteAddr = new InetSocketAddress(ip, port); 
	    	if(debug)
	    	{
	    		Log.d(APP_TAG, "Connect IP[" + ip + "]port[" + port + "]");
	    	}
	    	socket.connect(remoteAddr, timeout);
		} catch (UnknownHostException e) {

		} catch (IOException e) {

		}
	    if(socket!= null && socket.isConnected())
	    {
	    	return true;
	    }
	    return false;
    }
    
    protected void disconnectSocket()
    {
		if(debug)Log.d(APP_TAG, "disconnectSocket");
    	try {
            if(socket != null)
            {
            	socket.close();
            }
        } catch (IOException e) {

        }
    }
    

	protected synchronized void readAllCard()
	{
		if(appState.acceptMSR)
		{
			startMSRThread();
		}

		if(appState.acceptContactCard)
		{
			contactOpened = true;
			open_reader(1);
		}
		if(appState.acceptContactlessCard)
		{
			contactlessOpened = true;
			open_reader(2);
		}
	}

    protected void waitContactCard()
    {
    	contactOpened = true;
    	open_reader(1);
    }
    
    protected void cancelAllCard()
    {
   		cancelMSRThread();
    	cancelContactCard();
    	cancelContactlessCard();
    }
    
    protected void cancelContactCard()
    {
    	if(debug)Log.d(APP_TAG, "Close contact card");
    	if(contactOpened)
    	{
    		contactOpened = false;
    		close_reader(1);
    	}
    }
    
    
    protected void cancelContactlessCard()
    {
    	if(contactlessOpened)
    	{
    		contactlessOpened = false;
    		close_reader(2);
    	}
    }
    
    private void notifyContactlessCardOpenError()
    {
    	Message msg = new Message();
    	msg.what = CARD_OPEN_ERROR_NOTIFIER;
    	mHandler.sendMessage(msg);
    }

	protected void startMSRThread()
	{
		if(   readMSRCard == false
			&& appState.msrError == false
			)
		{
			while(msrThreadActived){

			}
			msrThread=new MSRThread();
			msrThread.start();
		}
	}

	protected void cancelMSRThread()
	{
		if(readMSRCard == true)
		{
			readMSRCard = false;
		}
	}

	protected void notifyMSR()
	{
		Message msg = new Message();
		msg.what = MSR_READ_DATA_NOTIFIER;
		mHandler.sendMessage(msg);
	}

	protected void notifyMsrOpenError()
	{
		Message msg = new Message();
		msg.what = MSR_OPEN_ERROR_NOTIFIER;
		mHandler.sendMessage(msg);
	}

	protected void notifyMsrReadError()
	{
		Message msg = new Message();
		msg.what = MSR_READ_ERROR_NOTIFIER;
		mHandler.sendMessage(msg);
	}

	protected boolean read_track2_data()
	{
		if(debug)Log.d(APP_TAG, "read_track2_data");
		int trackDatalength;
		byte[] byteArry = new byte[255];
		trackDatalength = MsrInterface.getTrackData(1, byteArry, byteArry.length);  // nTrackIndex: 0-Track1; 1-track2; 2-track3
		if(debug)
		{
			String strDebug = "";
			for(int i=0; i<trackDatalength; i++)
				strDebug += String.format("%02X ", byteArry[i]);
			Log.d(APP_TAG, "track2 Data: " + strDebug);
		}
		if(trackDatalength > 0)
		{
			if(   trackDatalength > 37
				|| trackDatalength < 21
				)
			{
				return false;
			}

			int panStart = -1;
			int panEnd = -1;
			for (int i = 0; i < trackDatalength; i++)
			{
				if (byteArry[i] >= (byte) '0' && byteArry[i] <= (byte) '9')
				{
					if( panStart == -1)
					{
						panStart = i;
					}
				}
				else if (byteArry[i] == (byte) '=')
				{
					/* Field separator */
					panEnd = i;
					break;
				}
				else
				{
					panStart = -1;
					panEnd = -1;
					break;
				}
			}
			if (panEnd == -1 || panStart == -1)
			{
				return false;
			}
			appState.trans.setPAN(new String(byteArry, panStart, panEnd - panStart));
			appState.trans.setExpiry(new String(byteArry, panEnd + 1, 4));
			appState.trans.setServiceCode(new String(byteArry, panEnd + 5, 3));
			appState.trans.setTrack2Data(byteArry, 0, trackDatalength);
			appState.trans.setCardEntryMode(SWIPE_ENTRY);
			return true;
		}
		return false;
	}

	protected void read_track3_data()
	{
		if(debug)Log.d(APP_TAG, "read_track3_data");
		int trackDatalength;
		byte[] byteArry = new byte[255];
		trackDatalength = MsrInterface.getTrackData(2, byteArry, byteArry.length);  // nTrackIndex: 0-Track1; 1-track2; 2-track3
		if(debug)
		{
			String strDebug = "";
			for(int i=0; i<trackDatalength; i++)
				strDebug += String.format("%02X ", byteArry[i]);
			Log.d(APP_TAG, "track3 Data: " + strDebug);
		}
		if(trackDatalength > 0)
		{
			appState.trans.setTrack3Data(byteArry, 0, trackDatalength);
		}
	}

	class MSRThread extends Thread
	{
		public void run()
		{
			super.run();
			msrThreadActived = true;
			readMSRCard = false;
			if(msrClosed == true)
			{
				if(MsrInterface.open() >= 0)
				{
					msrClosed = false;
				}
			}
			if(msrClosed == false)
			{
				readMSRCard = true;
				do{
					int nReturn = -1;
					nReturn = MsrInterface.poll(500);
//					appState.msrPollResult = nReturn;
					if(debug)Log.d(APP_TAG, "MsrInterface.poll, " + nReturn);
					if(readMSRCard == false)
					{
						MsrInterface.close();
						msrClosed = true;
						if(debug)Log.d(APP_TAG, "MsrInterface.close");
					}
					else if(nReturn >= 0)
					{
						if(read_track2_data())
						{
							read_track3_data();
							MsrInterface.close();
							readMSRCard = false;
							msrClosed = true;
							notifyMSR();
						}
						else
						{
							MsrInterface.close();
							msrClosed = true;
							readMSRCard = false;
							notifyMsrReadError();
						}
					}
				}while(readMSRCard == true);
			}
			else
			{
				notifyMsrOpenError();
			}
			if(debug)Log.d(APP_TAG, "MSRThread.exit");
			msrThreadActived = false;
		}
	}


	protected void offlineSuccess()
    {
    		transSuccess();
    }

	public void saveTrans()
	{
		if(debug)Log.d(APP_TAG, "save trans");
		appState.transDetailService.save(appState.trans);
	}
	
	public void saveAdvice()
	{
		if(debug)Log.d(APP_TAG, "save advice");
		appState.adviceService.save(appState.trans);
	}
	
	public void clearTrans()
	{
		appState.transDetailService.clearTable();
	}
	
	public void clearAdvice()
	{
		appState.adviceService.clearTable();		
	}
	
	public void transSuccess()
	{
		if(appState.getTranType() != TRAN_SETTLE)
		{
		  	if ((TransDefine.transInfo[appState.getTranType()].flag & T_NOCAPTURE) == 0)
		  	{
	  			saveTrans();
	  			appState.batchInfo.incSale(appState.trans.getTransAmount());
		  	}
		}
	}
	
    public void exit()
    {
    	cancelIdleTimer();
    }
    
	public void exitTrans()
	{
		cancelContactlessCard();
		cancelMSRThread();
		if(appState.cardType == CARD_CONTACT)
		{
			removeCard();
		}
		else
		{
			appState.initData();
		}
	}
	
	// ilde
	public void go2Idle()
	{
		cancelIdleTimer();
//		Intent intent = new Intent(context, IdleActivity.class);
//		context.startActivity(intent);
	}
	
	public void go2Error(int errorCode)
	{
		cancelIdleTimer();
		appState.setErrorCode(errorCode);
		Intent intent = new Intent(context, ErrorActivity.class);
		context.startActivity(intent);
	}
	
	// menu
	public void requestFuncMenu()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, FuncMenuActivity.class);
		context.startActivity(intent);
	}
	
	public void requestDataClear()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, DataClearActivity.class);
		context.startActivity(intent);
	}

	public void requestEnquireTrans()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, EnquireTransActivity.class);
		context.startActivity(intent);
	}

	public void showLastPBOC()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, ShowLastPBOCActivity.class);
		context.startActivity(intent);
	}
	
	// trans flow For Result
	public void requestCard(boolean acceptMSR, boolean acceptContact, boolean acceptContactless)
	{
		cancelIdleTimer();
		appState.setState(STATE_REQUEST_CARD);
		if(appState.msrError == false)
		{
			appState.acceptMSR = acceptMSR;
		}
		else{
			appState.acceptMSR = false;
		}
		appState.acceptContactCard = acceptContact;
		appState.acceptContactlessCard = acceptContactless;
		Intent intent = new Intent(context, RequestCardActivity.class);
		context.startActivityForResult(intent, appState.getState());
	}
	
	public void removeCard()
	{
		cancelIdleTimer();
		appState.setState(STATE_REMOVE_CARD);
		Intent intent = new Intent(context, RemoveCardActivity.class);
		context.startActivityForResult(intent, appState.getState());
	}
	
//	public void requestManualCard(boolean acceptMSR, boolean acceptContact, boolean acceptContactless)
//	{
//		cancelIdleTimer();
//		appState.setState(STATE_REQUEST_CARD);
//		appState.acceptMSR = acceptMSR;
//		appState.acceptContactCard = acceptContact;
//		appState.acceptContactlessCard = acceptContactless;
//		Intent intent = new Intent(context, RequestManualCardActivity.class);
//		context.startActivityForResult(intent, appState.getState());
//	}
	
	public void confirmBypassPin()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, ConfirmBypassPinActivity.class);
		context.startActivityForResult(intent, STATE_CONFIRM_BYPASS_PIN);
	}
	
	public void confirmCard()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, ConfirmCardActivity.class);
		context.startActivityForResult(intent, STATE_CONFIRM_CARD);
	}
	
	public void inputAmount()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, InputAmountActivity.class);
		context.startActivityForResult(intent, STATE_INPUT_AMOUNT);
	}
	
	public void inputPIN()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, InputPINActivity.class);
		context.startActivityForResult(intent, STATE_INPUT_PIN);
	}
	public void processOnline()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, ProcessOnlineActivity.class);
		context.startActivityForResult(intent, STATE_PROCESS_ONLINE);
	}
	
	public void selectEMVAppList()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, SelectEMVAppListActivity.class);
		context.startActivityForResult(intent, STATE_SELECT_EMV_APP);
	}
	
	public void showPBOCCardRecord()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, ShowPBOCCardRecordActivity.class);
		context.startActivityForResult(intent, STATE_SHOW_EMV_CARD_TRANS);
	}
	
	public void showTransInfo()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, ShowTransInfoActivity.class);
		context.startActivityForResult(intent, STATE_SHOW_TRANS_INFO);
	}

	public void processEMVCard(byte kernelType)
	{
		appState.trans.setEMVKernelType(kernelType);
		Intent intent = new Intent(context, ProcessEMVCardActivity.class);
		context.startActivityForResult(intent, STATE_PROCESS_EMV_CARD);
	}
	
	public void showTransResult()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, TransResultActivity.class);
		context.startActivityForResult(intent, STATE_TRANS_END);
	}
	
	// Trans Object
	public void sale()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, Sale.class);
		context.startActivity(intent);
	}
	
	public void settle()
	{
		cancelIdleTimer();
		Intent intent = new Intent(context, Settle.class);
		context.startActivity(intent);
	}
	
	public void queryCardRecord(byte recordType)
	{
		appState.recordType = recordType;
		cancelIdleTimer();
		Intent intent = new Intent(context, QueryCardRecord.class);
		context.startActivity(intent);
	}
	//=============== Q1 keyboard =============
	protected void onEnter()
	{
	}

	protected void onCancel()
	{
	}

	protected void onBack()
	{
	}

	protected void onDel()
	{
	}

	protected void onKeyCode(char key)
	{}


	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(debug) Log.i("FuncActivity", "onKeyDown:"+keyCode);
		onTouch();
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK:
			onBack();
			break;
		case KeyEvent.KEYCODE_ESCAPE:
			onCancel();
			break;
		case KeyEvent.KEYCODE_DEL:
			onDel();
			break;
		case KeyEvent.KEYCODE_ENTER:
			onEnter();
			break;
		case 232://'.'
			onKeyCode('.');
			break;
		default:
			onKeyCode((char) ('0'+(keyCode-KeyEvent.KEYCODE_0)));
			break;
		}
		return true;
	}
	//=============== Q1 keyboard =============

	protected boolean preProcessQpboc()
	{
		//pre-process
		int res = emv_preprocess_qpboc();
		if(res < 0)
		{
			if(res == -2)
			{
				appState.setErrorCode(R.string.error_amount_zero);
			}
			else
			{
				appState.setErrorCode(R.string.error_amount_over_limit);
			}
			emv_set_kernel_type(PBOC_KERNAL);
			return false;
		}
		return true;
	}
}
