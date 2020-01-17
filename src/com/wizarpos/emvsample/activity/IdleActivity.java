package com.wizarpos.emvsample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cloudpos.jniinterface.IFuntionListener;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.login.LoginActivity;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.jni.ContactICCardReaderInterface;
import com.wizarpos.jni.ContactlessICCardReaderInterface;
import com.wizarpos.util.StringUtil;

import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_config_checksum;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_kernel_checksum;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_kernel_id;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_process_type;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_version_string;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_kernel_initialize;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_force_online;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_kernel_attr;
import static com.cloudpos.jniinterface.EMVJNIInterface.get_card_type;
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
	private TextView idleLine4;
	private TextView idleLine5;

	private static final String TAG = "IdleActivity";


	@Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_idle);

        try {
			if (getIntent().getBooleanExtra("EXIT", false)) {
				finish();
			}
		}catch (Exception e){

	}

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
    	//TODO 3
		idleLine4 = (TextView)findViewById(R.id.idleLine4);
		idleLine5 = (TextView)findViewById(R.id.idleLine5);

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
			Log.e(APP_TAG, "idleActivity handleMessageSafe  resetCardError = true ");
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
			//From modification
			loadEMVParam();
//          TODO 1 original code
//			byte[] version = new byte[32];
//			int len = emv_get_version_string(version, version.length);
//			idleLine3.setText(new String(version, 0, len));
		}
		else{
			if(appState.emvParamChanged == true)
			{
				setEMVTermInfo();
			}
		}


  //TODO 4
		byte[] version = new byte[32];
		byte[] kernelChecksum = new byte[8];
		byte[] configChecksum = new byte[4];

		int len = emv_get_version_string(version, version.length);
		idleLine3.setText(new String(version, 0, len));

		if(emv_get_kernel_checksum(kernelChecksum, kernelChecksum.length) > 0)
		{
			idleLine4.setText("KC: " + StringUtil.toHexString(kernelChecksum, false));
		}
		if(emv_get_config_checksum(configChecksum, configChecksum.length) > 0)
		{
			idleLine5.setText("CC: " + StringUtil.toHexString(configChecksum, false));
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

    //TODO 5
    
    @Override
    protected void onStop() {
    	if(debug)Log.e(APP_TAG, "idleActivity onStop");
        super.onStop();

    }

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
        finish();
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
			registerFunctionListener(new IFuntionListener() {
				@Override
				public void emvProcessCallback(byte[] data)
				{
					if(debug)Log.d(APP_TAG, "Idle emvProcessCallback  emvProcessNextCompleted");


					Log.d(TAG, " >>>>> emvProcessCallback  StringUtil.bytes2HexString(data)" + StringUtil.bytes2HexString(data));
					Log.d(TAG, ">>>>>  emvProcessCallback data[0]" +data[0]);
					Log.d(TAG, ">>>>>  emvProcessCallback data[1]"+ data[1]);


					appState.trans.setEMVStatus(data[0]);
					appState.trans.setEMVRetCode(data[1]);

					Message msg = new Message();
					msg.what = EMV_PROCESS_NEXT_COMPLETED_NOTIFIER;
					mHandler.sendMessage(msg);
				}

				@Override
				public void cardEventOccured(int eventType)
				{
					Log.d(TAG, "cardEventOccured: >>>>>");
					if(debug)Log.d(APP_TAG, "get cardEventOccured");
					Message msg = new Message();
					if(eventType == SMART_CARD_EVENT_INSERT_CARD)
					{

						appState.cardType = get_card_type();
						if(debug)Log.d(APP_TAG, "  >>>> cardType = " + appState.cardType);

						if(appState.cardType == CARD_CONTACT)
						{
							Log.d(TAG, "cardEventOccured: >>>>>   appState.cardType == CARD_CONTACT    sent  CARD_INSERT_NOTIFIER");

							msg.what = CARD_INSERT_NOTIFIER;
							mHandler.sendMessage(msg);
						}
						else if(appState.cardType == CARD_CONTACTLESS)
						{

							Log.d(TAG, "cardEventOccured: >>>>>   appState.cardType == CARD_CONTACTLESS    sent  CARD_TAPED_NOTIFIER");

							msg.what = CARD_TAPED_NOTIFIER;
							mHandler.sendMessage(msg);
						}
						else{
							appState.cardType = -1;
						}
					}
					else if(eventType == SMART_CARD_EVENT_POWERON_ERROR)
					{

						Log.d(TAG, "cardEventOccured: >>>>>   eventType == SMART_CARD_EVENT_POWERON_ERROR    sent  CARD_ERROR_NOTIFIER");

						appState.cardType = -1;
						msg.what = CARD_ERROR_NOTIFIER;
						mHandler.sendMessage(msg);
					}
					else if(eventType == SMART_CARD_EVENT_REMOVE_CARD)
					{
						appState.cardType = -1;
						Log.d(TAG, "cardEventOccured: >>>>>   eventType == SMART_CARD_EVENT_REMOVE_CARD    sent  appState.cardType = -1");

					}
					else if(eventType == SMART_CARD_EVENT_CONTALESS_HAVE_MORE_CARD)
					{
						appState.cardType = -1;
						msg.what = CONTACTLESS_HAVE_MORE_CARD_NOTIFIER;
						Log.d(TAG, "cardEventOccured: >>>>>   eventType == SMART_CARD_EVENT_CONTALESS_HAVE_MORE_CARD   sent  CONTACTLESS_HAVE_MORE_CARD_NOTIFIER;");

						mHandler.sendMessage(msg);
					}

				}
			});

			emv_kernel_initialize();
			emv_set_kernel_attr(new byte[]{0x20}, 1);

			if(loadCAPK() == -2)
			{
				capkChecksumErrorDialog(IdleActivity.this);
			}
			loadAID();
			loadCAPK();
			loadExceptionFile();
			loadRevokedCAPK();
			setEMVTermInfo();

			emv_set_force_online(appState.terminalConfig.getforceOnline());

			//TODO commented out due to crash 7 5 and 6 are the two classes added
			Log.i("test", "kernel id:"+emv_get_kernel_id());
			Log.i("test", "process type:"+ emv_get_process_type());

			appState.emvParamLoadFlag = true;
    	}
//
//		byte[] defaultPINKey = new byte[]{'2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2'};
//		if (PinPadInterface.open() >= 0) {
//			PinPadInterface.updateUserKey(appState.terminalConfig.getKeyIndex(), 0, defaultPINKey, defaultPINKey.length);
//			PinPadInterface.close();
//		}
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

	@Override
	public void finish() {
		super.finish();
	}
}
