package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wizarpos.emvsample.generators.PfmStateGenerator;
import com.iisysgroup.androidlite.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel;
import com.iisysgroup.poslib.commons.emv.EmvCard;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;
import com.wizarpos.emvsample.models.PfmJournalGenerator;
import com.wizarpos.emvsample.models.Pfm;
import com.wizarpos.emvsample.payments_menu.Services.TransferServices;
import com.wizarpos.emvsample.payments_menu.models.WithdrawalDetails;
import com.wizarpos.emvsample.printer.PrinterException;
import com.wizarpos.emvsample.printer.PrinterHelper;
import com.wizarpos.emvsample.transaction.TransDefine;
import com.wizarpos.jni.PinPadInterface;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wizarpos.emvsample.payments_menu.utils.EncryptionUtilsKt.Hashing;
import static com.wizarpos.util.StringUtil.getClientRef;

public class TransResultActivity extends FuncActivity 
{
	private TextView textTitle  = null;
	private Button   buttonBack = null;
    private Button   buttonMore = null;
    
	private TextView textLine1;
	private TextView textLine2;
	private TextView textLine3;
	private TextView textLine4;
	
	private Button buttonOK;
	private Button buttonCancel;
	
	private int mPrintTimer = 0;
	private int intSeconds = 0;
	private Timer mTimerSeconds;
	private boolean printPaused = false;

	private ImageView transactionStatus;
	private WithdrawalDetails withdrawalDetails;

	@Override
	public void handleMessageSafe(Message msg)
	{
		/*这里是处理信息的方法*/
		switch (msg.what)
		{
		case PRINT_PAUSE_TIMER_NOTIFIER:
			if(printPaused == true)
			{
				cancelPrintPauseTimer();
				continuePrintReceipt();
			}
			break;
		}
	}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_trans_result);
        initToolbar();

        // title
        textTitle = (TextView)findViewById(R.id.tAppTitle);
		//textTitle.setText(appState.getString(TransDefine.transInfo[appState.getTranType()].id_display_en));

        transactionStatus = findViewById(R.id.transactionStatus);

//	    buttonBack = (Button)findViewById(R.id.btn_back);;;
//        buttonBack.setOnClickListener(new ClickListener());
        
//        buttonMore = (Button)findViewById(R.id.btn_more);
//        buttonMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
        
        textLine1 = (TextView)findViewById(R.id.tTransResult_Line1);
        textLine2 = (TextView)findViewById(R.id.tTransResult_Line2);
        textLine3 = (TextView)findViewById(R.id.tTransResult_Line3);
        textLine4 = (TextView)findViewById(R.id.tTransResult_Line4);
        
//        buttonOK  = (Button)findViewById(R.id.btn_digit_enter);
//        buttonOK.setOnClickListener(new ClickListener());
    	
//        buttonCancel  = (Button)findViewById(R.id.btn_digit_cancel);
//        buttonCancel.setOnClickListener(new ClickListener());

        mHandler.setFunActivity(this);

        if(appState.getErrorCode() > 0)
        {
        	textLine2.setText(appState.getErrorCode());
        	if(   appState.trans.getCardEntryMode() == INSERT_ENTRY
        	   || appState.trans.getCardEntryMode() == CONTACTLESS_ENTRY
        	  )
        	{
        			textLine3.setText("TVR: " + appState.trans.getTVR());
        			textLine4.setText("TSI: " + appState.trans.getTSI());
        			if(appState.getErrorCode() == 8984){
						textLine4.setText("Please configure terminal");
					}
        	}
        		
        }
        else
        {
        	if(appState.getTranType() == TRAN_SETTLE)
        	{
        		textLine1.setText("SETTLE FINISH");
        	}
        	else{
	        	if(appState.trans.getCardEntryMode()  == SWIPE_ENTRY)
	        	{
	        		if(   appState.trans.getResponseCode()[0] == '0'
	        		   || appState.trans.getResponseCode()[1] == '0'
	        		  )
	        		{
						textLine1.setText(R.string.response_00);
						if(( TransDefine.transInfo[appState.getTranType()].flag & T_NORECEIPT) == 0)
						{
		        			textLine2.setText("PRINTING...");
		        			appState.printReceipt = 0;
		        			printReceipt();
						}
	        		}
	        		else{
	        			textLine1.setText("DECLINED");
                        transactionStatus.setImageResource(R.drawable.ic_cancel_black_24dp);
	        		}
	        	}
	        	else{
		        	if(   appState.trans.getEMVRetCode() == APPROVE_OFFLINE
		        	   || appState.trans.getEMVRetCode() == APPROVE_ONLINE
		        	  )
		        	{
						if(appState.trans.getEMVRetCode() == APPROVE_ONLINE)
						{
							textLine1.setText("Approved");
							if (appState.withdrawal){
								Log.d("okh", "result withdrawal credit now");
								creditWallet();
								FuncActivity.appState.withdrawal = false;
							}
							if (appState.airtime){
								Log.d("okh", "result airtime credit now");
								creditAirtime();
								FuncActivity.appState.airtime = false;
							}
							transactionStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
						}
						else{
							textLine1.setText("ACCEPTED OFFLINE");
						}
						if(( TransDefine.transInfo[appState.getTranType()].flag & T_NORECEIPT) == 0)
						{
		        			textLine2.setText("PRINTING...");
		        			appState.printReceipt = 0;
		        			printReceipt();
						}
		    		}
					else
					{
						if(appState.trans.getEMVRetCode() == DECLINE_ONLINE)
						{
							textLine1.setText("DECLINED OFFLINE");
						}
						else{
							textLine1.setText("DECLINED");
							appState.printReceipt = 0;
							printReceipt();
						}
					}
        			textLine3.setText("TVR: " + appState.trans.getTVR());
        			textLine4.setText("TSI: " + appState.trans.getTSI());
	        	}
        	}
        }
    }

	private void creditAirtime() {

		String phone_number = SecureStorage.retrieve("phonerecharge", "").replace(" ", "");
		String airtimeProvider = SecureStorage.retrieve("airtimeprovider", "");
		String mpin = SecureStorage.retrieve("pinentered", "");
		String airtime_amount = SecureStorage.retrieve("amountrecharge", "");;
		String wallet = SecureStorage.retrieve("wallet", "");
		String password = SecureStorage.retrieve("password", "");

		String username = SecureStorage.retrieve("username", "");

		AirtimeRequestDetails details = new AirtimeRequestDetails(wallet, username, airtime_amount, phone_number, airtimeProvider, mpin, password);

		AirtimeServices airtimeServices = new AirtimeServices();
		airtimeServices.create().airtimePurchase(details).enqueue(new Callback<Object>() {
			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {


			}

			@Override
			public void onFailure(Call<Object> call, Throwable t) {

			}
		});

	}

	private void creditWallet() {
		Double amount = SecureStorage.retrieve("amount", 2.0);
		Log.d("okh", amount + "");
		String channel = SecureStorage.retrieve("channel", "");
		String password = SecureStorage.retrieve("password", "");
		String paymentMethod = SecureStorage.retrieve("paymentMethod", "");
		String pin = SecureStorage.retrieve("pin", "");
		String productCode = SecureStorage.retrieve("productCode", "");
		String type = SecureStorage.retrieve("type", "");
		String vendorBankCode = SecureStorage.retrieve("vendorBankCode", "");
		String wallet = SecureStorage.retrieve("wallet", "");
		String username = SecureStorage.retrieve("username", "");
		String phone = SecureStorage.retrieve("phone", "");
		Log.d("okh", " settocredit" + vendorBankCode);
		WithdrawalWalletCreditModel transferResponse;
		String clientReference = getClientRef(this, "");

		EmvCard.PinInfo pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(), null, null);
		EmvCard emvCard = new EmvCard(appState.trans.getCardHolderName(), appState.trans.getTrack2Data(), appState.trans.getICCData(), pinInfo);

		Pfm pfm = new Pfm(new PfmStateGenerator(this).generateState(), new PfmJournalGenerator(appState.trans.getTransactionResult(), appState.nibssData.getConfigData(), false,  amount.longValue(), emvCard).generateJournal());

		withdrawalDetails =  new WithdrawalDetails(wallet, username, password, pin, "default", amount, "", vendorBankCode, "ANDROIDPOS", "card", productCode,  pfm);

		Log.d("okh", appState.trans.getTransactionResult().terminalID);
		Log.d("okh", appState.trans.getTransactionResult().transactionStatus);
		Log.d("okh", appState.trans.getTransactionResult().responseCode);
		Log.d("okh", appState.trans.getTransactionResult().isApproved()+"");
		Gson gson = new Gson();
		String jsonPayload = gson.toJson(withdrawalDetails);
		String base64encoded = new String(org.apache.commons.codec.binary.Base64.encodeBase64(jsonPayload.getBytes()));
		String encoded = null;
		try {
			encoded = URLEncoder.encode(base64encoded, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String nonce = clientReference;
		Log.e("sign", base64encoded + " " + encoded);
		String encryptedStuff = nonce+"IL0v3Th1sAp11111111UC4NDoV4SSWITHVICEBANKING"+encoded;
		String signature = Hashing(encryptedStuff);
		//String signature = EncryptionUtilsKt.Hashing(encryptedStuff);

//		String signature = hash(encryptedStuff);
		//String signature = EncryptionUtilsKt.Hashing(encryptedStuff).toLowerCase();
		TransferServices transferServices = new TransferServices();

		transferServices.TransferService().withdraw(withdrawalDetails, "application/json", signature, nonce).enqueue(new Callback<WithdrawalWalletCreditModel>() {

			@Override
			public void onResponse(Call<WithdrawalWalletCreditModel> call, Response<WithdrawalWalletCreditModel> response) {
				if (response != null) {

					Log.d("okh", "result " + response.body());
					if (response.body().getStatus() != 1) {
						Toast.makeText(getBaseContext(), response.body().getMessage() + "", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(getBaseContext(), "Your wallet has been debitted \n " + response.body().getAmountSettled() / 100 + " \n " + "Beneficiary : " + response.body().getBeneficiaryName(), Toast.LENGTH_LONG).show();
					}
				}

			}

			@Override
			public void onFailure(Call<WithdrawalWalletCreditModel> call, Throwable t) {

			}
		});

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
	protected void onBack() {
		finish();
		super.onBack();
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		
	}
	
    @Override
    protected void onStop()
    {
    	super.onStop();
    }
    
    @Override
    protected void onPause()
    {
    	super.onPause();
    }
    
    @Override
    public void onBackPressed(){

    }
    
	public class ClickListener implements View.OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			if(printPaused == true)
			{
				switch(v.getId())
				{
				case R.id.btn_digit_enter:
					// 打印
					cancelPrintPauseTimer();
					continuePrintReceipt();
					break;
				case R.id.btn_digit_cancel:
					cancelPrintPauseTimer();
					if(appState.needClearPinpad == true)
			        {
			        	// clear pinpad
			        	appState.needClearPinpad = false;
	    	    		PinPadInterface.setText(0, null, 0, 0);
	    	    		PinPadInterface.setText(1, null, 0, 0);
			        }
					setResult(Activity.RESULT_OK, getIntent());
					exit();
					break;
				}
			}
			else{
				switch(v.getId())
				{
				case R.id.btn_digit_enter:
				case R.id.btn_digit_cancel:
					if(appState.getErrorCode() > 0)
					{
						appState.setErrorCode(0);
					}
					setResult(Activity.RESULT_OK, getIntent());
					exit();
					break;
				}
			}
		}
    }
	
    private void startPrintPauseTimer(int timerSecond)
    {
    	printPaused = true;
    	mPrintTimer = timerSecond;
    	intSeconds = 0;
            //create timer to tick every second
        mTimerSeconds = new Timer();
        mTimerSeconds.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                intSeconds++;
                if (intSeconds == mPrintTimer)
                {
		    		Message m = new Message(); 
		    		m.what = PRINT_PAUSE_TIMER_NOTIFIER;
		    		mHandler.sendMessage(m);
                }
            }
        }, 0, 1000);
    }
    
    private void cancelPrintPauseTimer()
    {
    	printPaused = false;
    	intSeconds = 0;
    	mTimerSeconds.cancel();
    }
    
    
    private void continuePrintReceipt()
    {
    	appState.printReceipt++;
    	printReceipt();
    }
    
    private void printReceipt()
    {
		try {
			PrinterHelper.getInstance().printReceipt(appState, 1);
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setMessage("Print Merchant copy");
			alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					try {
						PrinterHelper.getInstance().printReceipt(appState, 0);
					} catch (PrinterException e) {
						e.printStackTrace();
					}
				}
			});
			alertDialog.show();
		} catch (PrinterException e) {
		}
		
		if( appState.terminalConfig.getReceipt() > (appState.printReceipt + 1) )
		{
			textLine2.setText("CONTINUE PRINT?");
			startPrintPauseTimer(4);
		}
		else
		{
			textLine2.setText("PRINT_COMPLETED");
		}
    }
}
