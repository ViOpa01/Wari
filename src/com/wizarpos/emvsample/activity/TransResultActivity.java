package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
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
import com.itex.richard.payviceconnect.model.AbujaModel;
import com.itex.richard.payviceconnect.model.AirtimeModel;
import com.itex.richard.payviceconnect.model.EkoModel;
import com.itex.richard.payviceconnect.model.EnuguModel;
import com.itex.richard.payviceconnect.model.IbadanModel;
import com.itex.richard.payviceconnect.model.IkejaModel;
import com.itex.richard.payviceconnect.model.Journal;
import com.itex.richard.payviceconnect.model.PortharcourtModel;
import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.activity.login.Helper;
import com.wizarpos.emvsample.generators.PfmStateGenerator;
import com.wizarpos.emvsample.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel;
import com.iisysgroup.poslib.commons.emv.EmvCard;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;
import com.wizarpos.emvsample.models.PfmJournalGenerator;
import com.wizarpos.emvsample.models.Pfm;
import com.wizarpos.emvsample.payments_menu.Services.TransferServices;
import com.wizarpos.emvsample.payments_menu.models.WithdrawalDetails;
import com.wizarpos.emvsample.services.discos.viewmodels.EleectricityPaymentVM;
import com.wizarpos.emvsample.services.helper.activity.util.GeneralElectricityDetails;
import com.wizarpos.emvsample.transaction.TransDefine;
import com.wizarpos.jni.PinPadInterface;
import com.wizarpos.util.SharedPreferenceUtils;
import com.wizarpos.util.StringUtil;
import com.wizarpos.util.TransactionModel;
import com.wizarpos.util.VasServices;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wizarpos.emvsample.payments_menu.utils.EncryptionUtilsKt.Hashing;
import static com.wizarpos.emvsample.services.discos.activities.ElectricityPaymentActivity.USER_PIN;
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


	private EleectricityPaymentVM mEleectricityPaymentVM=null;

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


		mEleectricityPaymentVM= ViewModelProviders.of(this).get(EleectricityPaymentVM.class);







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
		        			appState.printVasReceipt = 0;
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
								appState.withdrawal = true;
								Log.d("okh", "result withdrawal credit now");
								creditWallet();
								FuncActivity.appState.withdrawal = false;
							}
							if (appState.airtime){
//								appState.airtime = true;
								Log.d("okh", "result airtime credit now");
								creditAirtime();
							}
                            if(appState.electricityBills) {
								processPayment(appState.generalElectricityDetails)	;						}
							transactionStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
						}
						else{
							textLine1.setText("ACCEPTED OFFLINE");
						}
						if(( TransDefine.transInfo[appState.getTranType()].flag & T_NORECEIPT) == 0)
						{
		        			textLine2.setText("PRINTING...");
		        			appState.printReceipt = 0;
		        			appState.printVasReceipt = 0;
		        			printReceipt();
						}
		    		}
					else
					{
						if(appState.trans.getEMVRetCode() == DECLINE_ONLINE)
						{
							appState.airtime = false;
							textLine1.setText("DECLINED OFFLINE");
							transactionStatus.setImageResource(R.drawable.ic_cancel_black_24dp);
						}
						else{
							appState.airtime = false;
							textLine1.setText("DECLINED");
							appState.printReceipt = 0;
							appState.printVasReceipt = 0;
							transactionStatus.setImageResource(R.drawable.ic_cancel_black_24dp);
							printReceipt();
						}
					}
        			textLine3.setText("TVR: " + appState.trans.getTVR());
        			textLine4.setText("TSI: " + appState.trans.getTSI());
	        	}
        	}
        }




        //Observe The request


		mEleectricityPaymentVM.getLPaymentRes().observe(this, new Observer<Object>() {
			@Override
			public void onChanged(@Nullable Object paymentResponse) {

					Log.d("yellow >>>","Now");

//					boolean  error=transactionResult.isApproved ?:true
//					String vasTid =transactionResult.terminalID?:" "
//					String cardExpiary =transactionResult.cardExpiry?:" "
//					String cardHolderName=transactionResult.cardHolderName?:" "
					String smartCardNumber ="";
					String meterNumber="";
					String beneficiaryName ="";
					String beneficiaryAddress="";
					String responsemessage ="";
					String amount="";
					String token ="";

					boolean isCardTransaction=true;
					String transactionTID ="";


				switch (appState.generalElectricityDetails.getElectricMeterType()){

					case VasServices.ABUJA_ELECTRICITY_POSTPAID:
					case VasServices.ABUJA_ELECTRICITY_PREPAID:{
						AbujaModel.PurchaseResponse response =   (AbujaModel.PurchaseResponse) paymentResponse;

						Log.d("yellow >>>","Now");
						meterNumber =response.getAccount();
						beneficiaryName =response.getName();
						beneficiaryAddress ="";
						responsemessage =response.getMessage();
						amount =String.valueOf(response.getAmount());
						token=response.getToken().isEmpty()? "" : response.getToken();
//                    responseModel = ResponseModel(response!!.amount.toString(),response.error,response.message)

					}
                       break;
					case VasServices.ENUGU_ELECTRICITY_POSTPAID:
					case VasServices.ENUGU_ELECTRICITY_PREPAID:{
						EnuguModel.PayResponse  response =   (EnuguModel.PayResponse) paymentResponse;
								meterNumber =response.getAccount();
								beneficiaryName =response.getName();
								beneficiaryAddress =response.getAddress();
								responsemessage =response.getMessage();
								amount =response.getValue();
								token=response.getToken().isEmpty()? "" : response.getToken();
//                    responseModel = ResponseModel(response!!.value.toString(),response!!.error!!,response!!.message!!)

					}
					break;

					case VasServices.EKO_ELECTRICITY_POSTPAID:
					case VasServices.EKO_ELECTRICITY_PREPAID :{

						EkoModel.EkoPayResponse  response =   (EkoModel.EkoPayResponse )paymentResponse;

						meterNumber =response.getCustomerMeterNumber();
						beneficiaryName =response.getPayer();
						beneficiaryAddress =response.getAddress();
						responsemessage =response.getMessage();
						amount =response.getAmount();
						token=response.getToken().isEmpty()? "" : response.getToken();

//                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)

					}
                     break;
					case  VasServices.IBADAN_ELECTRICITY_POSTPAID:
					case VasServices.IBADAN_ELECTRICITY_PREPAID:{
						IbadanModel.IbPayResponse  response =   (IbadanModel.IbPayResponse ) paymentResponse;

						meterNumber =response.getAccount();
						beneficiaryName =response.getName();
						beneficiaryAddress ="";
						responsemessage =response.getMessage();
						amount =String.valueOf(response.getAmount());
						token=response.getToken().isEmpty()? "" : response.getToken();
//                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)
					}

					case VasServices.IKEJA_ELECTRICITY_POSTPAID:
					case VasServices.IKEJA_ELECTRICITY_PREPAID:{
						IkejaModel.IkejaPayResponse  response =   (IkejaModel.IkejaPayResponse ) paymentResponse;

						meterNumber ="";
						beneficiaryName =response.getPayer();
								beneficiaryAddress =response.getAddress();
						responsemessage =response.getMessage();
						amount =String.valueOf(response.getAmount());
						token=response.getToken().isEmpty()? "" : response.getToken();

//                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)

					}

					case VasServices.PORTHARCOURT_ELECTRICITY_POSTPAID:
					case VasServices.PORTHARCOURT_ELECTRICITY_PREPAID :{

						PortharcourtModel.purchaseResponse response =   (PortharcourtModel.purchaseResponse) paymentResponse;

						meterNumber =response.getMeterNumber();
						beneficiaryName =response.getName();
						beneficiaryAddress =response.getAddress();
						responsemessage =response.getMessage();
						amount =String.valueOf(response.getAmount());
						token=response.getToken().isEmpty()? "" : response.getToken();

//                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)


					}

                default:
				}

//              if(isCardTransaction){
				printReceipt();


//					print(amount,error,responsemessage,transactionTID,isCardTransaction,vasTid,cardExpiary,smartCardNumber,meterNumber,beneficiaryName,beneficiaryAddress,cardHolderName)



//              print(responseModel!!.amount,responseModel.error,responseModel!!.response,transactionTID)
//              }else{
//                  print(responseModel!!.amount,responseModel.error,responseModel!!.response,)

//                  transactionResult.
//              }


			}
		}
		);



		//End the request







    }


	private void  processPayment(GeneralElectricityDetails generalElectricityDetails) {
        String  pin = SecureStorage.retrieve(USER_PIN, "");
		EmvCard.PinInfo pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(), null, null);

		EmvCard emvCard = new EmvCard(appState.trans.getCardHolderName(), appState.trans.getTrack2Data(), appState.trans.getICCData(), pinInfo);

		com.itex.richard.payviceconnect.model.Pfm pfm = new com.itex.richard.payviceconnect.model.Pfm(new PfmStateGenerator(this).generateState(), new PfmJournalGenerator(appState.trans.getTransactionResult(), appState.nibssData.getConfigData(), false,  generalElectricityDetails.getAmount(), emvCard,"Airtime",generalElectricityDetails.getElectricMeterType(),"").generateJournal());


		mEleectricityPaymentVM.payElectricBill(this ,generalElectricityDetails.getAmount(), generalElectricityDetails.getWallet(), generalElectricityDetails.getUserName(), generalElectricityDetails.getRequestType(),generalElectricityDetails.getMeterType().toLowerCase(),generalElectricityDetails.getMeterName(),generalElectricityDetails.getChannel(), generalElectricityDetails.getPhone_number(), generalElectricityDetails.getProductCode(),pin , VasServices.CARD,generalElectricityDetails.getClientReference(),generalElectricityDetails.getTerminalId(),generalElectricityDetails.getElectricMeterType(),generalElectricityDetails.getPassword(),generalElectricityDetails.getMeterName(),pfm);
        SecureStorage.store("pinentered", "");
    }

	private void creditAirtime() {

		final String phone_number = SecureStorage.retrieve("phonerecharge", "").replace(" ", "");
		String airtimeProvider = SecureStorage.retrieve("airtimeprovider", "");
		String mpin = SecureStorage.retrieve("pinentered", "");
		final String airtime_amount = SecureStorage.retrieve("amountrecharge", "");;
		final String wallet = SecureStorage.retrieve("wallet", "");
		String password = SecureStorage.retrieve("password", "");
		 String terminalID = SecureStorage.retrieve(Helper.TERMINAL, "");
		final String merchantID = appState.nibssData.getConfigData().getConfigData("03015").toString();
		final String merchantName = appState.nibssData.getConfigData().getConfigData("52040").toString();
		 String username = SecureStorage.retrieve("userName", "");
		final String airtimetype = SecureStorage.retrieve("airtimeType", "");



        username=SharedPreferenceUtils.INSTANCE.getPayviceUsername(this);



        terminalID=SecureStorage.retrieve(Helper.TERMINAL_ID, "");


        password=SharedPreferenceUtils.INSTANCE.getPayvicePassword(this);



        SecureStorage.retrieve(Helper.PASSWORD, "");

		EmvCard.PinInfo pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(), null, null);

//		 pinInfo.pinBlock.isEmpty();

//		Log.d(" pinInfo ", new Gson().toJson(pinInfo) );
//
//		Log.d(" pinInfo.pinBlock ",pinInfo.getPinBlock().toString() );



		EmvCard emvCard = new EmvCard(appState.trans.getCardHolderName(), appState.trans.getTrack2Data(), appState.trans.getICCData(), pinInfo);

		com.itex.richard.payviceconnect.model.Pfm pfm = new com.itex.richard.payviceconnect.model.Pfm(new PfmStateGenerator(this).generateState(), new PfmJournalGenerator(appState.trans.getTransactionResult(), appState.nibssData.getConfigData(), false,  airtime_amount, emvCard,"Airtime",airtimeProvider,"").generateJournal());


		AirtimeRequestDetails details = new AirtimeRequestDetails(terminalID, username, airtime_amount, phone_number, airtimeProvider, mpin, password,pfm);

		AirtimeServices airtimeServices = new AirtimeServices();
		final String finalTerminalID = terminalID;
		airtimeServices.create().airtimeCardPurchase(details).enqueue(new Callback<AirtimeModel.AirtimePinResponse>() {
			@Override
			public void onResponse(Call<AirtimeModel.AirtimePinResponse> call, Response<AirtimeModel.AirtimePinResponse> response) {
				TransactionModel transactionModel = null;
				String date = new PfmStateGenerator(getBaseContext()).getCurrentTime();

				String bankLogoName = "";
				try {
					bankLogoName = "bank" + finalTerminalID.substring(0, 4);
				} catch (Exception e) {

				}
				FuncActivity.appState.airtime = false;

				String status = "Declined";
				if (!response.body().getError()){
					status = "Approved";
					textLine1.setText("APPROVED");
					transactionStatus.setImageResource(R.drawable.ic_cancel_black_24dp);
				}else{
					textLine1.setText("DECLINED");
					transactionStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);
				}

					if (airtimetype.equalsIgnoreCase("wallet")){
						transactionModel = new TransactionModel(finalTerminalID, "",  "", "", airtime_amount, "", "airtime", "", status, "" , merchantID , merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, phone_number);
					}
					else if (airtimetype.equalsIgnoreCase("card")){
						transactionModel = new TransactionModel(wallet, "",  "", "", airtime_amount, "", "airtime", "", status, "" , merchantID , merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, phone_number);
					}


				    printReceipt();

//					Intent intent = new Intent(getBaseContext(), MainActivity.class);
//
//					intent.putExtra("transactionModel", transactionModel);
//					intent.putExtra("copy", "** CUSTOMER COPY **");
//					startActivity(intent);

			}

			@Override
			public void onFailure(Call<AirtimeModel.AirtimePinResponse> call, Throwable t) {

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
		String username = SecureStorage.retrieve("userName", "");
		String phone = SecureStorage.retrieve("phone", "");
		Log.d("okh", " settocredit" + vendorBankCode);
//		WithdrawalWalletCreditModel transferResponse;
		String clientReference = getClientRef(this, "");

		EmvCard.PinInfo pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(), null, null);
		EmvCard emvCard = new EmvCard(appState.trans.getCardHolderName(), appState.trans.getTrack2Data(), appState.trans.getICCData(), pinInfo);

		com.itex.richard.payviceconnect.model.Pfm pfm = new com.itex.richard.payviceconnect.model.Pfm(new PfmStateGenerator(this).generateState(), new PfmJournalGenerator(appState.trans.getTransactionResult(), appState.nibssData.getConfigData(), false,  amount.toString(), emvCard,"Transfer","Cash-In","").generateJournal());

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
		//String signature = EncryptitransferResponseonUtilsKt.Hashing(encryptedStuff).toLowerCase();
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

        String[] nameArr = appState.terminalConfig.getMerchantName1().split(" ");
        nameArr = Arrays.copyOf(nameArr, nameArr.length - 2);
        String terminalConfig = StringUtil.join(nameArr, " ");
        String transactionstatus = "DECLINED";
        if (appState.trans.isTransactionStatus()){
            transactionstatus = "APPROVED";
        }
        String transactionstatusReason = appState.trans.getTransactionResult().transactionStatusReason;
        String responseCode = appState.trans.getTransactionResult().responseCode;
        String terminalID = appState.nibssData.getConnectionData().getTerminalID();
        String merchantID = appState.nibssData.getConfigData().getConfigData("03015").toString();
        String merchantName = appState.nibssData.getConfigData().getConfigData("52040").toString();
        String transactionType = appState.trans.getTransactionType();
        String cardholderName = appState.trans.getCardHolderName();
        String pan = appState.trans.getMaskPan();
        String rrn = appState.trans.getRrn();
        String date = appState.trans.getTransDate().substring(0, 4)
                + "/" + appState.trans.getTransDate().substring(4, 6)
                + "/" + appState.trans.getTransDate().substring(6, 8)
                + " " + appState.trans.getTransTime().substring(0, 2)
                + ":" + appState.trans.getTransTime().substring(2, 4)
                + ":" + appState.trans.getTransTime().substring(4, 6);
        String ticket = appState.trans.getTrace().toString();
        String UNPR = appState.trans.getUnpredictableNumber();
        String AC = appState.trans.getAC();
        String TVR = appState.trans.getTVR();
        String AID = appState.trans.getAID();
        String TSI = appState.trans.getTSI();
        String cardType = appState.trans.getAppName();
        String AIP = appState.trans.getAIP();
        String amount = (appState.trans.getTransAmount()/100)+"";
        String othersAmount = appState.trans.getOthersAmount().toString();
		String bankLogoName = "";
        try{
			bankLogoName = "bank" + terminalID.substring(0, 4);
		}catch (Exception e){

		}

       MainApp.transactionModel = new TransactionModel(terminalID,rrn,cardholderName, pan, amount, othersAmount, transactionType, responseCode, transactionstatus, transactionstatusReason,merchantID, merchantName, ticket, UNPR, AC, TVR, AID, TSI, date, cardType, AIP, bankLogoName, "");
      final  TransactionModel transactionModel = MainApp.transactionModel;

        Log.i("Yeah TransactionResult >>>", transactionModel.toString());

		Log.i("appState.airtime >>>", String.valueOf(appState.airtime));

		Log.i("!appState.airtime >>>", String.valueOf((!appState.airtime)));


		//try {
		if(!appState.airtime) {

			Log.i("!appState.airtime  here >>>", String.valueOf((!appState.airtime)));
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("transactionModel", transactionModel);
			intent.putExtra("copy", "** CUSTOMER COPY **");
			startActivity(intent);
			//PrinterHelper.getInstance().printReceipt( appState, 1);
//			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//			alertDialog.setMessage("Print Merchant copy");
//			alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialogInterface, int i) {
//					Intent intent = new Intent(getBaseContext(), MainActivity.class);
//					intent.putExtra("transactionModel", transactionModel);
//					intent.putExtra("copy", "*** MERCHANT COPY ***");
//					startActivity(intent);
//				}
//			});
//			alertDialog.show();
		}else{
			Log.i("!appState.airtime  else here >>>", String.valueOf((!appState.airtime)));
			appState.airtime = false;
		}
//		} catch (PrinterException e) {
//		} successful is 1

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
