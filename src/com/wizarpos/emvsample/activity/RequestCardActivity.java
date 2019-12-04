package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.wizarpos.emvsample.R;
import com.wizarpos.util.AppUtil;

import static com.cloudpos.jniinterface.EMVJNIInterface.emv_anti_shake_finish;

public class RequestCardActivity extends FuncActivity
{
	private TextView textTitle  = null;
	private Button   buttonBack = null;
    private Button   buttonMore = null;
    
	private TextView txtTransType = null;
	private TextView txtPrompt = null;
	private TextView txtError = null;
	private TextView txtAmount = null;

	final String APP_TAG = "RequestCardActivity";


	@Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_request_card);
		initToolbar();

        // title
//        textTitle = (TextView)findViewById(R.id.tAppTitle);
//        setTitle(textTitle);
//	    buttonBack = (Button)findViewById(R.id.btn_back);
//        buttonBack.setOnClickListener(new ClickListener());
//
//        buttonMore = (Button)findViewById(R.id.btn_more);
//        buttonMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
        
        txtTransType = (TextView)findViewById(R.id.tRequestCard_TransType);
        txtError = (TextView)findViewById(R.id.tRequestCard_Error);
        txtAmount = (TextView)findViewById(R.id.tRequestCard_Amount);
        txtPrompt = (TextView)findViewById(R.id.tRequestCard_Prompt);

        
        if(appState.resetCardError == true)
        {
			Log.e(APP_TAG, "RequestCardActivity  handleMessageSafe  resetCardError = true ");

			txtError.setText("IC ERROR, PLEASE REBOOT DEVICE");
        }
        else if(appState.trans.getEmvCardError() == true)
        {
        	txtError.setText("TRANS HALTED");
        	if(appState.trans.getTransAmount() > 0)
        	{
        		txtAmount.setText("AMOUNT: " + AppUtil.formatAmount(appState.trans.getTransAmount()));
        	}
        }

    }

	private void initToolbar() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Purchase");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onSupportNavigateUp() {
		finish();
		return super.onSupportNavigateUp();
	}


	@Override
	public void handleMessageSafe(Message msg)
	{
		Log.d("RequestCard Activity  handleMessageSafe  >>>>>>>>", "Here again handleMessageSafe");
		/*这里是处理信息的方法*/

		Log.d("RequestCard Activity  handleMessageSafe  >>>>>>>", "msg.what = " + msg.what);

		switch (msg.what)
		{


			case MSR_READ_DATA_NOTIFIER:
			if(   appState.trans.getServiceCode().length() > 0
				&& (   appState.trans.getServiceCode().getBytes()[0] == '2'
				|| appState.trans.getServiceCode().getBytes()[0] == '6'
			)
				)
			{
				if(appState.trans.getEmvCardError() == false)
				{

					Log.d("Request Card Activity MSR_READ_DATA_NOTIFIER  appState.trans.getEmvCardError() == false >>>>>","Here");
					startMSRThread();
					appState.promptCardIC = true;
					setPrompt();
				}
				else{

					Log.d("RequestCard Activity ! MSR_READ_DATA_NOTIFIER  appState.trans.getEmvCardError() == false >>>>>","Here");

					cancelAllCard();
					setResult(Activity.RESULT_OK, getIntent());
					finish();
				}
			}
			else{
				if( appState.trans.getServiceCode().length() > 0
						&& appState.trans.getServiceCode().getBytes()[0] == '1' )
				{
					appState.trans.setEmvCardError(false);
					appState.trans.setPanViaMSR(true);
				}
				else{
					appState.trans.setEmvCardError(false);
					appState.trans.setPanViaMSR(false);
				}
				cancelAllCard();
				setResult(Activity.RESULT_OK, getIntent());
				finish();
			}
			break;
		case MSR_OPEN_ERROR_NOTIFIER:
				Log.d(" Open  MSR_OPEN_ERROR_NOTIFIER  >>>>","HERE ");

			appState.msrError = true;
			appState.acceptMSR = false;

			txtPrompt.setText(appState.getString(R.string.insert_card));

			break;
		case MSR_READ_ERROR_NOTIFIER:
			Log.d(" Read  MSR_READ_ERROR_NOTIFIER  >>>>","HERE ");

			readAllCard();
			break;
		case CARD_INSERT_NOTIFIER:
			//TODO 8
			emv_anti_shake_finish(1);
			Bundle bundle = msg.getData();
			int nEventID = bundle.getInt("nEventID");
			int nSlotIndex = bundle.getInt("nSlotIndex");
			Log.d("Inserted card CARD_INSERT_NOTIFIER >>>","Here");

			if(debug)Log.d(APP_TAG, "get CONTACT_CARD_EVENT_NOTIFIER,event[" + nEventID + "]slot[" + nSlotIndex + "]" );
			if(   nSlotIndex == 0  && nEventID == SMART_CARD_EVENT_INSERT_CARD )
			{
				appState.trans.setEmvCardError(false);
				if(appState.acceptContactlessCard == true)
				{

					Log.d("RequestCardActivity appState.acceptContactlessCard == true >>>","Here");

					cancelContactlessCard();
				}
				appState.trans.setCardEntryMode(INSERT_ENTRY);
				setResult(Activity.RESULT_OK, getIntent());
				exit();
			}
			break;
		case CARD_TAPED_NOTIFIER:
			bundle = msg.getData();

			nEventID = bundle.getInt("nEventID");
			if(nEventID == SMART_CARD_EVENT_INSERT_CARD)
			{
				cancelContactCard();
				cancelMSRThread();
				appState.trans.setCardEntryMode(CONTACTLESS_ENTRY);
				if(debug)Log.d(APP_TAG, "get CONTACTLESS_CARD_EVENT_NOTIFIER" );
				setResult(Activity.RESULT_OK, getIntent());
				exit();
			}
			break;
		case CONTACTLESS_HAVE_MORE_CARD_NOTIFIER:
			if(debug)Log.d(APP_TAG, ">>> error, have more card" );
			appState.setErrorCode(R.string.error_more_card);
			setResult(Activity.RESULT_OK, getIntent());
			exit();
			break;
		case CARD_ERROR_NOTIFIER:
			//OEM MESSAGE
//			txtError.setText("IC POWERON ERROR");

			txtError.setText("IC ERROR, PLEASE REBOOT DEVICE");
			txtPrompt.setText("PLEASE INSERT CARD");
			appState.trans.setEmvCardError(true);
			break;


		}
	}

	@Override
    protected void onStart() { 
        super.onStart();
        mHandler.setFunActivity(this);
        if(appState.balanceEnc){

		}else if(appState.purchaseWithCashBack){
			txtAmount.setText("AMOUNT: " + AppUtil.formatAmount(appState.trans.getTransAmount()) + System.getProperty("line.separator") + "CASHBACK AMOUNT: " + AppUtil.formatAmount(appState.trans.getOthersAmount()) +
					System.getProperty("line.separator") + "TOTAL: " + AppUtil.formatAmount( appState.trans.getTransAmount() + appState.trans.getOthersAmount()));

		}

		setTitle(txtTransType);
   		setPrompt();
        readAllCard();
        super.startIdleTimer(TIMER_FINISH, DEFAULT_IDLE_TIME_SECONDS);
	}
    
    private void setPrompt()
    {
        txtPrompt.setText("PLEASE USE YOUR CARD");
    }
	
    @Override
    protected void onPause()
    {
        super.onPause();
    }
    
    @Override
    protected void onStop()
    {
    	//cancelAllCard();
    	super.onStop();
    }
    
    @Override
    public void onBackPressed()
    {
    	cancelAllCard();
    	setResult(Activity.RESULT_CANCELED, getIntent());
    	finish();
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		switch(requestCode)
		{
		case STATE_REQUEST_CARD_ERROR:
			if(resultCode == Activity.RESULT_OK)
			{
				Log.d("RequestCardActivity  STATE_REQUEST_CARD_ERROR >>>","card error ");

				setResult(Activity.RESULT_OK, getIntent());
			}
			else
			{
				setResult(Activity.RESULT_CANCELED, getIntent());
			}
			finish();
			break;
		default:
			break;
		}
	}
	
	public class ClickListener implements View.OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			switch(v.getId())
			{
			case R.id.btn_back:
		    	cancelAllCard();
		    	setResult(Activity.RESULT_CANCELED, getIntent());
		    	finish();
				break;
			}
		}
    }
}
