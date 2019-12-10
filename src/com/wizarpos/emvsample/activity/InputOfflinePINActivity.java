package com.wizarpos.emvsample.activity;

//TODO 10

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cloudpos.jniinterface.EMVJNIInterface;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.transaction.TransDefine;
import com.wizarpos.jni.PinPadCallbackHandler;

import static com.cloudpos.jniinterface.EMVJNIInterface.setEmvOfflinePinCallbackHandler;

//import static com.cloudpos.jniinterface.EMVJNIInterface.setEmvOfflinePinCallbackHandler;

public class InputOfflinePINActivity extends FuncActivity implements PinPadCallbackHandler
{
	private TextView textTitle  = null;
	private TextView textPin = null;
	private Button   buttonBack = null;
    private Button   buttonMore = null;

	char[] stars = "●●●●●●●●●●●●●●●●".toCharArray();

	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_input_pin);
    	// title
        textTitle = (TextView)findViewById(R.id.tAppTitle);
		textTitle.setText(appState.getString(TransDefine.transInfo[appState.getTranType()].id_display_en));

		textPin = (TextView) findViewById(R.id.input_pin);
		if(textPin != null) textPin.setText("");

	    buttonBack = (Button)findViewById(R.id.btn_back);
        buttonBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
        
        buttonMore = (Button)findViewById(R.id.btn_more);
        buttonMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
    }

	@Override
	protected void onStart()
	{
		super.onStart();
		setEmvOfflinePinCallbackHandler(InputOfflinePINActivity.this);
		EMVJNIInterface.emv_process_next();
		Log.d(">>>> complete pinblock1", "appState.trans.getPinBlock() : " + appState.trans.getPinBlock());
		Log.d(">>>> complete pinblock1", "appState.trans.getPinBlock()).length == 0 : " + String.valueOf((appState.trans.getPinBlock()).length == 0));

		if((appState.trans.getPinBlock()).length == 0) {
//		mHandler.setFunActivity(this);
		}
		else {
			mHandler.setFunActivity(this);
		}

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

	@Override
	public void handleMessageSafe(Message msg)
	{
		/*这里是处理信息的方法*/
		switch (msg.what)
		{
		case OFFLINE_PIN_NOTIFIER:
			textPin.setText(stars, 0, msg.arg1 & 0x0F);
			break;
		case EMV_PROCESS_NEXT_COMPLETED_NOTIFIER:
			setResult(Activity.RESULT_OK, getIntent());
			exit();
			break;
	}
	}

	public void processCallback(byte[] data) {
		processCallback(data[0], data[1]);
	}

	public void processCallback(int nCount, int nExtra)
	{
		Message msg = new Message();
		msg.what = OFFLINE_PIN_NOTIFIER;
		msg.arg1 = nCount;
		msg.arg2 = nExtra;
		mHandler.sendMessage(msg);
	}
}