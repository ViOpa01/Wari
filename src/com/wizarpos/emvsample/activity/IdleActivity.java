package com.wizarpos.emvsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.login.LoginActivity;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.jni.ContactICCardReaderInterface;
import com.wizarpos.jni.ContactlessICCardReaderInterface;

import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_version_string;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_kernel_initialize;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_force_online;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_kernel_attr;
import static com.cloudpos.jniinterface.EMVJNIInterface.loadEMVKernel;
import static com.cloudpos.jniinterface.EMVJNIInterface.registerFunctionListener;

public class IdleActivity extends FuncActivity implements Constants
{
	private TextView textTitle  = null;
	private Button   buttonBack = null;
    private Button   buttonMore = null;
    
	private TextView idleLine1;
	private TextView idleLine2;
	private TextView idleLine3;


	@Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_idle);
        initToolbar();
        textTitle = (TextView)findViewById(R.id.tAppTitle);
		textTitle.setText("WARI");
		
	    buttonBack = (Button)findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new ClickListener());
        
        buttonMore = (Button)findViewById(R.id.btn_more);
        buttonMore.setOnClickListener(new ClickListener());
        
        if(debug)Log.e(APP_TAG, "idleActivity onCreate"); 
        
        idleLine1 = (TextView)findViewById(R.id.idleLine1);
    	idleLine2 = (TextView)findViewById(R.id.idleLine2);
    	idleLine3 = (TextView)findViewById(R.id.idleLine3);
    	
    	idleLine1.setText("Purchase");
    	idleLine2.setText("PLEASE INSERT CARD");
    	idleLine3.setText("Powered by TamsLite");

		resetPurchase();

    	
    	if(appState.icInitFlag == false)
    	{
    		if(ContactICCardReaderInterface.init() >= 0)
    		{
    			Log.d(APP_TAG, "ContactICCardReaderInterface.init() OK"); 
    			appState.icInitFlag = true;
    		}
    		if(ContactlessICCardReaderInterface.init() >= 0)
    		{
    			Log.d(APP_TAG, "ContactlessICCardReaderInterface.init() OK"); 
    			appState.icInitFlag = true;
    		}
    	}

        Intent intent = new Intent(this, LoginActivity.class);
    	startActivity(intent);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void handleMessageSafe(Message msg)
    {
        /*这里是处理信息的方法*/
        switch (msg.what)
        {
        case CARD_INSERT_NOTIFIER:
            Bundle bundle = msg.getData();
            int nEventID = bundle.getInt("nEventID");
            int nSlotIndex = bundle.getInt("nSlotIndex");
            if(debug)Log.d(APP_TAG, "get CONTACT_CARD_EVENT_NOTIFIER,event[" + nEventID + "]slot[" + nSlotIndex + "]" );
            if(   nSlotIndex == 0
                && nEventID == SMART_CARD_EVENT_INSERT_CARD
                )
            {
                cancelMSRThread();
                appState.resetCardError = false;
                appState.trans.setCardEntryMode(INSERT_ENTRY);
                appState.needCard = false;
                sale();
            }
            break;
        case CARD_ERROR_NOTIFIER:
            cancelMSRThread();
            appState.trans.setEmvCardError(true);
            appState.resetCardError = true;
            appState.needCard = true;
            sale();
            break;
        }
    }

    @Override 
    protected void onStart() { 
    	if(debug)Log.e(APP_TAG, "idleActivity onStart"); 
        super.onStart(); 
        appState.initData();
        
        appState.idleFlag = true;
		if(appState.emvParamLoadFlag == false)
		{
			loadEMVParam();
			byte[] version = new byte[32];
			int len = emv_get_version_string(version, version.length);
			idleLine3.setText(new String(version, 0, len));
		}
		else{
			if(appState.emvParamChanged == true)
			{
				setEMVTermInfo();
			}
		}

        mHandler.setFunActivity(this);

        if(appState.icInitFlag != true)
		{
    		appState.idleFlag = false;
			go2Error(R.string.error_init_ic);
			return;
		}
    	waitContactCard();
    }
    
    @Override 
    protected void onResume() { 
    	if(debug)Log.e(APP_TAG, "idleActivity onResume");
        super.onResume();

    }
    
//    @Override
//    protected void onStop() {
//    	if(debug)Log.e(APP_TAG, "idleActivity onStop");
//        super.onStop();
//
//    }

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
    public void onBackPressed(){
		appState.idleFlag = false;
		cancelMSRThread();
		cancelContactCard();
        requestFuncMenu();
    }

	@Override
	protected void onBack()
	{
		onBackPressed();
	}

	@Override
	protected void onCancel()
	{
		onBackPressed();
	}

	@Override
	protected void onEnter()
	{
		onBackPressed();
	}

	public void loadEMVParam()
    {
    	//lib path
		String tmpEmvLibDir = "";
		tmpEmvLibDir = appState.getDir("", 0).getAbsolutePath();
		tmpEmvLibDir = tmpEmvLibDir.substring(0, tmpEmvLibDir.lastIndexOf('/')) + "/lib/libEMVKernal.so";

		if (loadEMVKernel(tmpEmvLibDir.getBytes(),tmpEmvLibDir.getBytes().length) == 0)
    	{
			registerFunctionListener(this);
			emv_kernel_initialize();
			emv_set_kernel_attr(new byte[]{0x20}, 1);

			/*if(loadCAPK() == -2)
			{
				capkChecksumErrorDialog(IdleActivity.this);
			}*/
			loadAID();
			loadExceptionFile();
			loadRevokedCAPK();
			setEMVTermInfo();

			emv_set_force_online(appState.terminalConfig.getforceOnline());

			appState.emvParamLoadFlag = true;
    	}
    }

	@Override
	public void onPointerCaptureChanged(boolean hasCapture)
	{

	}

	public class ClickListener implements View.OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			switch(v.getId())
			{
			case R.id.btn_back:
			case R.id.btn_more:
				appState.idleFlag = false;
				cancelMSRThread();
				cancelContactCard();
		        requestFuncMenu();
				break;
			}
		}
    }

}
