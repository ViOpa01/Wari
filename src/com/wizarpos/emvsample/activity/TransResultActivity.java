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
import com.iisysgroup.payvice.baseimpl.viewmodel.MultichoiceViewModel;
import com.iisysgroup.payvice.startimes.viewmodel.StartimesViewModel;
import com.iisysgroup.poslib.host.entities.ConfigData;
import com.iisysgroup.poslib.host.entities.ConnectionData;
import com.iisysgroup.poslib.host.entities.KeyHolder;
import com.iisysgroup.poslib.host.entities.TransactionResult;
import com.iisysgroup.poslib.host.entities.VasTerminalData;
import com.iisysgroup.poslib.utils.InputData;
import com.iisysgroup.poslib.utils.TransactionData;
import com.itex.richard.payviceconnect.model.AbujaModel;
import com.itex.richard.payviceconnect.model.AirtimeModel;
import com.itex.richard.payviceconnect.model.DstvModel;
import com.itex.richard.payviceconnect.model.EkoModel;
import com.itex.richard.payviceconnect.model.EnuguModel;
import com.itex.richard.payviceconnect.model.IbadanModel;
import com.itex.richard.payviceconnect.model.IkejaModel;
import com.itex.richard.payviceconnect.model.Journal;
import com.itex.richard.payviceconnect.model.PortharcourtModel;
import com.itex.richard.payviceconnect.model.StartimesModel;
import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.activity.login.Helper;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorageUtils;
import com.wizarpos.emvsample.generators.PfmStateGenerator;
import com.wizarpos.emvsample.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel;
import com.iisysgroup.poslib.commons.emv.EmvCard;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;
import com.wizarpos.emvsample.models.PfmJournalGenerator;
import com.wizarpos.emvsample.models.Pfm;
import com.wizarpos.emvsample.payments_menu.Services.TransferServices;
import com.wizarpos.emvsample.payments_menu.models.WithdrawalDetails;
import com.wizarpos.emvsample.services.discos.activities.ElectricityPaymentActivity;
import com.wizarpos.emvsample.services.discos.viewmodels.EleectricityPaymentVM;
import com.wizarpos.emvsample.services.helper.activity.util.Models.GeneralElectricityDetails;
import com.wizarpos.emvsample.services.helper.activity.util.Models;
import com.wizarpos.emvsample.transaction.TransDefine;
import com.wizarpos.jni.PinPadInterface;
import com.wizarpos.util.MemoryUtil;
import com.wizarpos.util.ReversalCommunicator;
import com.wizarpos.util.SharedPreferenceUtils;
import com.wizarpos.util.StringUtil;
import com.wizarpos.util.TransactionModel;
import com.wizarpos.util.VasServices;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wizarpos.emvsample.payments_menu.utils.EncryptionUtilsKt.Hashing;
import static com.wizarpos.emvsample.services.discos.activities.ElectricityPaymentActivity.USER_PIN;
import static com.wizarpos.emvsample.transaction.Nibss.poslibdb;
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

	VasTerminalData vasTerminalDetails=null;
	String TID =null;
	 ConfigData configData=new ConfigData();
	KeyHolder keyHolder=null;


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

	StartimesViewModel startimesViewModel =   null;

	MultichoiceViewModel multichoiceViewModel =   null;
	String tid ;


	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_trans_result);
        initToolbar();

		tid = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "");


		MemoryUtil.setValue(this, MemoryUtil.LastTransactionTimeKey, new Date());


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


//		startimesViewModel= ViewModelProviders.of(this).get(StartimesViewModel.class);
//
//		multichoiceViewModel = ViewModelProviders.of(this).get(MultichoiceViewModel.class);


		startimesViewModel= new StartimesViewModel(getApplication());

		multichoiceViewModel = new MultichoiceViewModel(getApplication());




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
		        			printReceipt(new Models.VasDetails());
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
							textLine1.setText("Approved \n Purchase successful!");
							if (appState.withdrawal){
								Log.d("okh", "result withdrawal credit now");
								creditWallet();
//								FuncActivity.appState.withdrawal = false;
							}
							if (appState.airtime){
//								appState.airtime = true;
								Log.d("okh", "result airtime credit now");
								creditAirtime();
//								appState.airtime =false;
							}

							if(appState.data ){
								String  pin = SecureStorage.retrieve(USER_PIN, "");
								EmvCard.PinInfo pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(), null, null);

								EmvCard emvCard = new EmvCard(appState.trans.getCardHolderName(), appState.trans.getTrack2Data(), appState.trans.getICCData(), pinInfo);



								Pfm pfm = new Pfm (new PfmStateGenerator(this,tid).generateState(), new PfmJournalGenerator(appState.trans.getTransactionResult(), appState.nibssData.getConfigData(), false,    appState.dataSubscriptionDetails.getAmount(), emvCard,  "Data",  appState.dataSubscriptionDetails.getService(),"").generateJournal());


								Log.d("pfm >>>>>", new Gson().toJson(pfm));
								DataModel.DataSubscriptionDetails dataSubscriptionDetails=new DataModel.DataSubscriptionDetails(appState.dataSubscriptionDetails.getTerminal_id(),appState.dataSubscriptionDetails.getUser_id(),appState.dataSubscriptionDetails.getPassword(),appState.dataSubscriptionDetails.getPin(),appState.dataSubscriptionDetails.getPhone(),appState.dataSubscriptionDetails.getService(),appState.dataSubscriptionDetails.getAmount(),appState.dataSubscriptionDetails.getDescription(),appState.dataSubscriptionDetails.getCode(),pfm,"Card");

                                Log.d("dataSubscriptionDetails >>>>>", new Gson().toJson(dataSubscriptionDetails));


                                purchaseData(dataSubscriptionDetails);
							}
							if(appState.cableTv ) {
								Log.d("appState.cableTv >>>>", String.valueOf(appState.cableTv));

								Log.d("appState.startimes  >>>>>", String.valueOf(appState.startimes));
								Log.d("appState.gotv  <>>>>>", String.valueOf(appState.gotv));
								Log.d("appState.dstv  >>>>", String.valueOf(appState.dstv));


								if(appState.startimes){
									Log.d("appState.startimes", String.valueOf(appState.startimes));

									StartimesModel.payRequest requestDetails = FuncActivity.appState.startimesPayRequest;
								startimesViewModel.subscribe(requestDetails.getPin(), requestDetails.getPassword(), requestDetails.getCustomerName(), requestDetails.getPhone(), requestDetails.getProductCode(), requestDetails.getBouquet(), requestDetails.getPaymentMethod(), requestDetails.getSmartCardCode(), requestDetails.getAmount());
//									appState.startimes=false;
								}

								else if(appState.gotv || appState.dstv){

									Log.d("appState.gotv", String.valueOf(appState.gotv));
									Log.d("appState.dstv", String.valueOf(appState.dstv));

									DstvModel.PayDetails payDetails =appState.dstvPayRequest;
									multichoiceViewModel.subscribe(payDetails.getIuc(),payDetails.getPin());
//									appState.gotv=false;
//									appState.dstv=false;

								}

							}
                            if(appState.electricityBills) {
//                            	Log.d("appState.electricityBills",String.valueOf(appState.electricityBills));
								processPayment(appState.generalElectricityDetails);

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
		        			appState.printVasReceipt = 0;
							Log.d("Tag", "appState.cableTv  = [" +appState.cableTv  + "]"+ "  appState.airtime   = [" +appState.airtime   + "] " + "appState.withdrawal   = [" +appState.withdrawal   + "]" + "appState.electricityBills  = [" +appState.electricityBills   + "]"+ "appState.data  = [" +appState.data   + "]");

							Log.d("Tag", "!appState.cableTv  = [" +!appState.cableTv  + "]"+ "  !appState.airtime   = [" +!appState.airtime   + "] " + "!appState.withdrawal   = [" +!appState.withdrawal   + "]" + "!appState.electricityBills  = [" +!appState.electricityBills   + "]"+  "!appState.data  = [" +!appState.data   + "]");
		        			if(!appState.cableTv && !appState.airtime && !appState.withdrawal && !appState.electricityBills && !appState.data) {
                                appState.vasTransactionstatus ="APPROVED";
		        			    printReceipt(new Models.VasDetails());


							}
					resetPurchase();

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
							printReceipt(new Models.VasDetails());
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
					String wallet =SecureStorage.retrieve(Helper.TERMINAL_ID,"");
					String product ="";
			     	String transactionRef ="";
			     	int logo =0;
			     	boolean error =true;
			     	Models.DiscosModel discosModel =null;
			     	String stan="";
			     	String meterType ="";

					boolean isCardTransaction=true;
					String transactionTID ="";
				Log.d("appState.generalElectricityDetails.getElectricMeterType() >>>",appState.generalElectricityDetails.getElectricMeterType());


				switch (appState.generalElectricityDetails.getElectricMeterType()){

					case VasServices.ABUJA_ELECTRICITY_POSTPAID:
					case VasServices.ABUJA_ELECTRICITY_PREPAID:{
						AbujaModel.PurchaseResponse response =   (AbujaModel.PurchaseResponse) paymentResponse;

						Log.d("yellow >>>","Now");
						Log.d("appState.generalElectricityDetails.amount >>>", appState.generalElectricityDetails.getAmount());
						meterNumber = response.getAccount().equals("") ? appState.generalElectricityDetails.getMeterNumber(): response.getAccount();
						beneficiaryName = response.getName().equals("") ? appState.generalElectricityDetails.getMeterName():response.getName();
								beneficiaryAddress = appState.generalElectricityDetails.getAddress();
						amount =  appState.generalElectricityDetails.getAmount();
						product = VasServices.ABUJA_ELECTRIC;
						meterType = (appState.generalElectricityDetails.getMeterType().equals("0")) ? VasServices.PREPAID : VasServices.POSTPAID;
						responsemessage = response.getMessage();
						token =  (response.getToken().isEmpty()) ? "" : response.getToken();
						transactionRef = response.getReference();
						logo = R.drawable.aedc;
						stan=response.getTransactionID();
						error = response.getError();
						discosModel = new Models.DiscosModel(error, beneficiaryName, meterType, stan, "", response.getUnit_value(), response.getVat(), meterNumber, token, beneficiaryAddress, "", "");
//                    responseModel = ResponseModel(response!!.amount.toString(),response.error,response.message)

					}
                       break;
					case VasServices.ENUGU_ELECTRICITY_POSTPAID:
					case VasServices.ENUGU_ELECTRICITY_PREPAID:{
						EnuguModel.PayResponse  response =   (EnuguModel.PayResponse) paymentResponse;
//						val response = paymentResponse as EnuguModel.PayResponse
								meterNumber = appState.generalElectricityDetails.getMeterNumber();
								beneficiaryName = appState.generalElectricityDetails.getMeterName();
								beneficiaryAddress = appState.generalElectricityDetails.getAddress();
						amount =  appState.generalElectricityDetails.getAmount();
//                        meterType =if (appState.generalElectricityDetails.meterType.equals(ENUGU_ELECTRICITY_POSTPAID)) VasServices.POSTPAID else VasServices.PREPAID
						meterType =meterType;
						product = VasServices.ENUGU_ELECTRIC;
						responsemessage = response.getMessage();
						token = (response.getToken().isEmpty())? "" : response.getToken();
								transactionRef =  response.getReference();
								logo = R.drawable.eedc;
						stan=response.getTransactionID();
						error = response.getError();
						discosModel = new Models.DiscosModel(error, beneficiaryName,meterType,stan, "",response.getUnit_value(), response.getVat(), meterNumber, token, beneficiaryAddress, response.getArrears(), response.getTariff());

//                    responseModel = ResponseModel(response!!.value.toString(),response!!.error!!,response!!.message!!)

					}
					break;

					case VasServices.EKO_ELECTRICITY_POSTPAID:
					case VasServices.EKO_ELECTRICITY_PREPAID :{

						EkoModel.EkoPayResponse  response =   (EkoModel.EkoPayResponse )paymentResponse;

						meterNumber = (response.getCustomerMeterNumber().equals("") ) ? appState.generalElectricityDetails.getMeterNumber() : response.getCustomerMeterNumber();
						beneficiaryName = appState.generalElectricityDetails.getMeterName();
						beneficiaryAddress = appState.generalElectricityDetails.getAddress();
						amount =  appState.generalElectricityDetails.getAmount();
                        meterType =  appState.generalElectricityDetails.getMeterType()	;
					    product = VasServices.EKO_ELECTRIC;
					    if(response.getToken() !=null)
						token = (response.getToken().equals("")) ? "" : response.getToken();
					    else
					        token="";
						transactionRef = response.getRef();
						responsemessage = response.getMessage();
						logo = R.drawable.ekedc;
						error = response.getError();
						stan=response.getTransactionID();

						discosModel = new Models.DiscosModel(error, beneficiaryName,meterType,stan, "", "","", meterNumber, token, beneficiaryAddress, "", "");


//                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)

					}
                     break;
					case  VasServices.IBADAN_ELECTRICITY_POSTPAID:
					case VasServices.IBADAN_ELECTRICITY_PREPAID:{
						IbadanModel.IbPayResponse  response =   (IbadanModel.IbPayResponse ) paymentResponse;

						meterNumber = (response.getAccount().equals("") ) ? appState.generalElectricityDetails.getMeterNumber(): response.getAccount();
								beneficiaryName = appState.generalElectricityDetails.getMeterName();
								beneficiaryAddress = appState.generalElectricityDetails.getAddress();
						amount =  appState.generalElectricityDetails.getAmount();
						meterType =  appState.generalElectricityDetails.getMeterType()	;
						token =  (response.getToken().isEmpty()) ? "" : response.getToken();
						transactionRef = response.getTransactionID();
						responsemessage = response.getMessage();
						product = VasServices.IBADAN_ELECTRIC;
						logo = R.drawable.ibedc;
						error = response.getError();
						discosModel = new Models.DiscosModel(error, beneficiaryName,meterType,stan, "",response.getUnit_value(),response.getVat(), meterNumber, token, beneficiaryAddress, "","");

//                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)
					}
					break;
					case VasServices.IKEJA_ELECTRICITY_POSTPAID:
					case VasServices.IKEJA_ELECTRICITY_PREPAID:{
						IkejaModel.IkejaPayResponse  response =   (IkejaModel.IkejaPayResponse ) paymentResponse;

						meterNumber = appState.generalElectricityDetails.getMeterNumber();
								beneficiaryName = appState.generalElectricityDetails.getMeterName();
								beneficiaryAddress = appState.generalElectricityDetails.getAddress();
						amount =  appState.generalElectricityDetails.getAmount();
                           meterType =appState.generalElectricityDetails.getMeterType();
                            stan=response.getTransactionID();
								token = (response.getToken().equals(""))? "" : response.getToken();
								transactionRef = response.getRef();
						responsemessage = response.getMessage();
						logo = R.drawable.ikedc;
						error = response.getError();

						discosModel = new Models.DiscosModel(error, beneficiaryName,meterType,stan,"", response.getUnit_value(), response.getVat(), meterNumber, token, beneficiaryAddress, "", "");


//                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)

					}
                   break;
					case VasServices.PORTHARCOURT_ELECTRICITY_POSTPAID:
					case VasServices.PORTHARCOURT_ELECTRICITY_PREPAID :{

						PortharcourtModel.purchaseResponse response =   (PortharcourtModel.purchaseResponse) paymentResponse;

						meterNumber = appState.generalElectricityDetails.getMeterNumber();
								beneficiaryName = appState.generalElectricityDetails.getMeterName();
								beneficiaryAddress = appState.generalElectricityDetails.getAddress();
						amount =  appState.generalElectricityDetails.getAmount();
                        meterType = appState.generalElectricityDetails.getMeterType();
						stan=response.getTransactionID();
								token =  (response.getToken().isEmpty()) ? "" : response.getToken();
								transactionRef = response.getReceiptNumber();
						logo = R.drawable.phdc;
						error = response.getError();
						discosModel = new Models.DiscosModel(error, beneficiaryName,meterType,stan, "", "","", meterNumber, token, beneficiaryAddress, "", "");


//                    responseModel = ResponseModel(response!!.amount.toString(),response!!.error!!,response!!.message!!)


					}

                default:
				}

//              if(isCardTransaction){

				String merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
				String merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
				String merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"");
				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
				String vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,"");
				String vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME,"");
//				String vasTerminalId = SecureStorage.retrieve(Helper.,"");


				final Models.VasDetails vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.DISCO,discosModel);
				Log.d("Trans result", "onChanged() called with: paymentResponse, vasDetails= [" + vasDetails + "]");


				 if(error){

					 new Thread(new Runnable() {
						 @Override
						 public void run() {



							 poslibdb.getVasTerminalDataDao().get();
							 Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
							 vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();

							 Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


							 //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
							 Log.d("okh", vasTerminalDetails.getTid()+" tid");

							 TID = vasTerminalDetails.getTid();

							 Log.d("okh now gotten TID", TID +" tid");


							 keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


							 configData.storeConfigData("04002", "90");

							 //Country Code
							 configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

							 //MCC
							 configData.storeConfigData("08004", vasTerminalDetails.getMcc());


							 //Merchant's name - 40 length
							 configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

							 //Merchant Id - 15 length
							 configData.storeConfigData("03015", vasTerminalDetails.getMid());

							 //Currency Code
							 configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




							 ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


							 InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//





//							 final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);


							 TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
							 KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

							 ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

							 TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


							 Log.d("Connection data >>>", new Gson().toJson(_connectionData));

							 Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
							 Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


							 Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

							 Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
							 Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
							 Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

							 Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

							 ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


							 final boolean reversalcom = reversalCommunicator.rollBackTransaction();
							 Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));



//                          reversalcom = reversalCommunicator.rollBackTransaction();


							 final boolean _reversalcom=reversalcom;
							 runOnUiThread(new Runnable() {
								 @Override
								 public void run() {
									 //Update UI

									 String reversalStatus = _reversalcom ? vasDetails.getTransactionStatusMessage() +" \n \n Reversal Successful" : vasDetails.getTransactionStatusMessage() +" \n \n Reversal UnSuccessful";
                                     vasDetails.setTransactionStatusMessage(reversalStatus);
									 printReceipt(vasDetails);
									 Toast.makeText(getBaseContext(), String.valueOf(_reversalcom), Toast.LENGTH_SHORT).show();

								 }
							 });

						 }
					 }).start();




				 }else{


					 printReceipt(vasDetails);
				 }




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


   startimesViewModel.getPaymentResponseLiveData().observe(this, new Observer<StartimesModel.payResponse>() {
	   @Override
	   public void onChanged(@Nullable StartimesModel.payResponse payResponse) {

		   Log.d("Here  >>>>>>>>>>", "Startimes");

		   String smartCardNumber =payResponse.getSmartCardCode();
		   String meterNumber="";
		   String beneficiaryName ="";
		   String beneficiaryAddress="";
		   String responsemessage =payResponse.getMessage();
		   String amount=appState.starTimesAmount;
		   String token ="";
		   String stan=payResponse.getTransactionID();
		   String wallet =SecureStorage.retrieve(Helper.TERMINAL_ID,"");
		   String product =VasServices.STARTIMES;
		   String transactionRef =payResponse.getReference();
		   int logo =R.drawable.startimes_print;
		   boolean error =payResponse.getError();
		   Models.CableTvModel cabelTvModel = new Models.CableTvModel(error,smartCardNumber);


		   boolean isCardTransaction=true;
		   String transactionTID ="";
           String merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
           String merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
           String merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"");
           String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
           String vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,"");
           String vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME,"");
//				String vasTerminalId = SecureStorage.retrieve(Helper.,"");


		   final Models.VasDetails vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.CABLE_TV,cabelTvModel);
		   Log.d("About to print", "onChanged() called with: VasDetails = [" + vasDetails + "]");


		   if(error){

			   new Thread(new Runnable() {
				   @Override
				   public void run() {



					   poslibdb.getVasTerminalDataDao().get();
					   Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
					   vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();

					   Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


					   //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
					   Log.d("okh", vasTerminalDetails.getTid()+" tid");

					   TID = vasTerminalDetails.getTid();

					   Log.d("okh now gotten TID", TID +" tid");


					   keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


					   configData.storeConfigData("04002", "90");

					   //Country Code
					   configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

					   //MCC
					   configData.storeConfigData("08004", vasTerminalDetails.getMcc());


					   //Merchant's name - 40 length
					   configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

					   //Merchant Id - 15 length
					   configData.storeConfigData("03015", vasTerminalDetails.getMid());

					   //Currency Code
					   configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




					   ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


					   InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//





//					   final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);


					   TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
					   KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

					   ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

					   TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


					   Log.d("Connection data >>>", new Gson().toJson(_connectionData));

					   Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
					   Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


					   Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

					   Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
					   Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
					   Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

					   Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

					   ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


					   final boolean reversalcom = reversalCommunicator.rollBackTransaction();
					   Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));



//                          reversalcom = reversalCommunicator.rollBackTransaction();


					   final boolean _reversalcom=reversalcom;
					   runOnUiThread(new Runnable() {
						   @Override
						   public void run() {
							   //Update UI

							   String reversalStatus = _reversalcom ? vasDetails.getTransactionStatusMessage() +" \n \n Reversal Successful" : vasDetails.getTransactionStatusMessage() +" \n \n Reversal UnSuccessful";
							   vasDetails.setTransactionStatusMessage(reversalStatus);
							   printReceipt(vasDetails);
							   Toast.makeText(getBaseContext(), String.valueOf(_reversalcom), Toast.LENGTH_SHORT).show();

						   }
					   });

				   }
			   }).start();




		   }else{


			   printReceipt(vasDetails);
		   }



//		   printReceipt(vasDetails);


	   }
   });

      multichoiceViewModel.getPaymentResponseLiveData().observe(this,new Observer<DstvModel.PayResponse>(){

		  @Override
		  public void onChanged(@Nullable DstvModel.PayResponse payDetails) {
			  String smartCardNumber =appState.multichoiceAccount;
			  String meterNumber="";
			  String beneficiaryName ="";
			  String beneficiaryAddress="";
			  String responsemessage = payDetails.getMessage();
			  String amount=appState.multichoiceAmount;
			  String token ="";
			  String wallet =SecureStorage.retrieve(Helper.TERMINAL_ID,"");
			  String product =appState.product;
			  String transactionRef =payDetails.getRef();
			  int logo =appState.logo;
			  String stan =payDetails.getTransactionID();
			  boolean error =payDetails.getError();
			  Models.CableTvModel cableTvModel =new Models.CableTvModel(error,smartCardNumber);

			  boolean isCardTransaction=true;
			  String transactionTID ="";
              String merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
              String merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
              String merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"");
              String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
              String vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,"");
              String vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME,"");
//				String vasTerminalId = SecureStorage.retrieve(Helper.,"");


			  final Models.VasDetails vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.CABLE_TV,cableTvModel);



			  if(error){

				  new Thread(new Runnable() {
					  @Override
					  public void run() {



						  poslibdb.getVasTerminalDataDao().get();
						  Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
						  vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();

						  Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


						  //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
						  Log.d("okh", vasTerminalDetails.getTid()+" tid");

						  TID = vasTerminalDetails.getTid();

						  Log.d("okh now gotten TID", TID +" tid");


						  keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


						  configData.storeConfigData("04002", "90");

						  //Country Code
						  configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

						  //MCC
						  configData.storeConfigData("08004", vasTerminalDetails.getMcc());


						  //Merchant's name - 40 length
						  configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

						  //Merchant Id - 15 length
						  configData.storeConfigData("03015", vasTerminalDetails.getMid());

						  //Currency Code
						  configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




						  ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


						  InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//





//						  final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);


						  TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
						  KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

						  ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

						  TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


						  Log.d("Connection data >>>", new Gson().toJson(_connectionData));

						  Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
						  Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


						  Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

						  Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
						  Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
						  Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

						  Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

						  ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


						  final boolean reversalcom = reversalCommunicator.rollBackTransaction();
						  Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));



//                          reversalcom = reversalCommunicator.rollBackTransaction();


						  final boolean _reversalcom=reversalcom;
						  runOnUiThread(new Runnable() {
							  @Override
							  public void run() {
								  //Update UI

								  String reversalStatus = _reversalcom ? vasDetails.getTransactionStatusMessage() +" \n \n Reversal Successful" : vasDetails.getTransactionStatusMessage() +" \n \n Reversal UnSuccessful";
								  vasDetails.setTransactionStatusMessage(reversalStatus);
								  printReceipt(vasDetails);
								  Toast.makeText(getBaseContext(), String.valueOf(_reversalcom), Toast.LENGTH_SHORT).show();

							  }
						  });

					  }
				  }).start();




			  }else{


				  printReceipt(vasDetails);
			  }
//			  printReceipt(vasDetails);
		  }
	  });


    }


	private void  processPayment(Models.GeneralElectricityDetails generalElectricityDetails) {
        String  pin = SecureStorage.retrieve(USER_PIN, "");
		EmvCard.PinInfo pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(), null, null);

		EmvCard emvCard = new EmvCard(appState.trans.getCardHolderName(), appState.trans.getTrack2Data(), appState.trans.getICCData(), pinInfo);



		com.itex.richard.payviceconnect.model.Pfm pfm = new com.itex.richard.payviceconnect.model.Pfm(new PfmStateGenerator(this,tid).generateState(), new PfmJournalGenerator(appState.trans.getTransactionResult(), appState.nibssData.getConfigData(), false,  generalElectricityDetails.getAmount(), emvCard,generalElectricityDetails.getElectricMeterType(),generalElectricityDetails.getElectricMeterType(),"").generateJournal());


		mEleectricityPaymentVM.payElectricBill(this ,generalElectricityDetails.getAmount(), generalElectricityDetails.getWallet(), generalElectricityDetails.getUserName(), generalElectricityDetails.getRequestType(),generalElectricityDetails.getMeterType().toLowerCase(),generalElectricityDetails.getMeterNumber(),generalElectricityDetails.getChannel(), generalElectricityDetails.getPhone_number(), generalElectricityDetails.getProductCode(),pin ,generalElectricityDetails.getPaymentMetod(),generalElectricityDetails.getElectricMeterType(),generalElectricityDetails.getPassword(),generalElectricityDetails.getMeterName(),generalElectricityDetails.getClientReference(),generalElectricityDetails.getTerminalId(),pfm);
        SecureStorage.store("pinentered", "");
    }

	private void creditAirtime() {

		final String phone_number = SecureStorage.retrieve("phonerecharge", "").replace(" ", "");
		String airtimeProvider = SecureStorage.retrieve("airtimeprovider", "");
		String mpin = SecureStorage.retrieve("pinentered", "");
		final String airtime_amount = SecureStorage.retrieve("amountrecharge", "");;
		final String wallet = SecureStorage.retrieve("wallet", "");
		String password = SecureStorage.retrieve("password", "");
		 String terminalID = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "");
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

		Log.d(" pinInfo >>>>  ", new Gson().toJson(pinInfo) );
//
//		Log.d(" pinInfo.pinBlock ",pinInfo.getPinBlock().toString() );



		final EmvCard emvCard = new EmvCard(appState.trans.getCardHolderName(), appState.trans.getTrack2Data(), appState.trans.getICCData(), pinInfo);

		com.itex.richard.payviceconnect.model.Pfm pfm = new com.itex.richard.payviceconnect.model.Pfm(new PfmStateGenerator(this,tid).generateState(), new PfmJournalGenerator(appState.trans.getTransactionResult(), appState.nibssData.getConfigData(), false,  airtime_amount, emvCard,"Airtime",airtimeProvider,"").generateJournal());

		final String date = new PfmStateGenerator(getBaseContext(),tid).getCurrentTime();
		AirtimeRequestDetails details = new AirtimeRequestDetails(terminalID, username, airtime_amount, phone_number, airtimeProvider, mpin, password,pfm,"Card");

		AirtimeServices airtimeServices = new AirtimeServices();
		final String finalTerminalID = terminalID;
		airtimeServices.create().airtimeCardPurchase(details).enqueue(new Callback<AirtimeModel.AirtimePinResponse>() {
			@Override
			public void onResponse(Call<AirtimeModel.AirtimePinResponse> call, final Response<AirtimeModel.AirtimePinResponse> response) {
				TransactionModel transactionModel = null;

				Log.d("AirtimePinResponse >>>>>>>", new Gson().toJson(response));





				String smartCardNumber ="";
				String meterNumber="";
				String beneficiaryName ="";
				String beneficiaryAddress="";
				final  String responsemessage = response.body().getMessage();
				final String amount=response.body().getAmount().isEmpty() ? airtime_amount:response.body().getAmount();
				String token ="";
				final String wallet =SecureStorage.retrieve(Helper.TERMINAL_ID,"");
				final String product =appState.product;
				final String transactionRef =response.body().getRef();
				final int logo =appState.logo;
				final boolean error =response.body().getError();
				final String stan =response.body().getTransactionID();
				final Models.AirtimeModel airtimeModel =new Models.AirtimeModel(error,phone_number);

				Log.d("appState.logo",String.valueOf(appState.logo));
				Log.d("Mtn",String.valueOf(R.drawable.mtn_logo));


				boolean isCardTransaction=true;
				String transactionTID ="";
				final String merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
				final String merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
				final String merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"");
//				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
				final String vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,"");
				final String vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME,"");
//				String vasTerminalId = SecureStorage.retrieve(Helper.,"");


				Models.VasDetails vasDetails = null;


//					if (airtimetype.equalsIgnoreCase("wallet")){
//						vasDetails =  new Models.VasDetails(amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,responsemessage,vasmerchantID,transactionRef,VasServices.CASH,logo,date,error,Models.AIRTIME,airtimeModel);
//					}
//					else if (airtimetype.equalsIgnoreCase("card")){



				vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.AIRTIME,airtimeModel);
//					}






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
					transactionStatus.setImageResource(R.drawable.ic_check_circle_black_24dp);

					printReceipt(vasDetails);
				}else{
					textLine1.setText("DECLINED");
					transactionStatus.setImageResource(R.drawable.ic_cancel_black_24dp);


					Log.d("reversal com >>>>>>>", "Began");
//
//					final ConnectionData connectionData = appState.nibssData.getConnectionData();
////
////				private val keyHolder by lazy {
//					final KeyHolder keys = appState.nibssData.getKeyHolder();
////
//
//					InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
////
//					ConfigData configData=appState.nibssData.getConfigData();
//
//					final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keys);
//
//                     new Thread(new Runnable() {
//						 @Override
//						 public void run() {
//							 ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), transactionData, connectionData, keys,appState.trans.getTransactionResult());
//
//
//							 boolean reversalcom = reversalCommunicator.rollBackTransaction();
//							 Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));
//						 }
//					 }).start();


					final Models.VasDetails finalVasDetails = vasDetails;
					new Thread(new Runnable() {
						@Override
						public void run() {



							poslibdb.getVasTerminalDataDao().get();
							Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
							vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();

							Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


							//   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
							Log.d("okh", vasTerminalDetails.getTid()+" tid");

							TID = vasTerminalDetails.getTid();

							Log.d("okh now gotten TID", TID +" tid");


							keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


							configData.storeConfigData("04002", "90");

							//Country Code
							configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

							//MCC
							configData.storeConfigData("08004", vasTerminalDetails.getMcc());


							//Merchant's name - 40 length
							configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

							//Merchant Id - 15 length
							configData.storeConfigData("03015", vasTerminalDetails.getMid());

							//Currency Code
							configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




							ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


							InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//





							final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);


							TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
							KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

							ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

							TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


							Log.d("Connection data >>>", new Gson().toJson(_connectionData));

							Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
							Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


							Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

							Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
							Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
							Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

							Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

							ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


							final boolean reversalcom = reversalCommunicator.rollBackTransaction();
							Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));



//                          reversalcom = reversalCommunicator.rollBackTransaction();


							final boolean _reversalcom=reversalcom;
							runOnUiThread(new Runnable() {
								@Override
								public void run() {

									String resmessage =_reversalcom ? responsemessage +" \n \n Reversal Successful" : responsemessage +" \n \n Reversal UnSuccessful";

									Models.VasDetails vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,resmessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.AIRTIME,airtimeModel);

									printReceipt(vasDetails);




                                    //Update UI
									Toast.makeText(getBaseContext(), String.valueOf(_reversalcom), Toast.LENGTH_SHORT).show();

								}
							});

						}
					}).start();

				}


//				    printReceipt();

//					Intent intent = new Intent(getBaseContext(), MainActivity.class);
//
//					intent.putExtra("transactionModel", transactionModel);
//					intent.putExtra("copy", "** CUSTOMER COPY **");
//					startActivity(intent);

			}

			@Override
			public void onFailure(Call<AirtimeModel.AirtimePinResponse> call, final Throwable t) {

				Log.d("reversal com >>>>>>>", "Began");

//				final ConnectionData connectionData = appState.nibssData.getConnectionData();
//
//				private val keyHolder by lazy {
//					final KeyHolder keys = appState.nibssData.getKeyHolder();
//

//				InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//
//					final ConfigData _configData=appState.nibssData.getConfigData();

//				final TransactionData transactionData =new TransactionData(inputData,emvCard,_configData,keys);



               new Thread(new Runnable() {
				   @Override
				   public void run() {



//					   poslibdb.getVasTerminalDataDao().get();
//					   Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
//					   vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();
//
//					   Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


					   //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
//					   Log.d("okh", vasTerminalDetails.getTid()+" tid");
//
//					   TID = vasTerminalDetails.getTid();
//
//					   Log.d("okh now gotten TID", TID +" tid");


					   keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


					   configData.storeConfigData("04002", "90");

					   //Country Code
					   configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

					   //MCC
					   configData.storeConfigData("08004", vasTerminalDetails.getMcc());


					   //Merchant's name - 40 length
					   configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

					   //Merchant Id - 15 length
					   configData.storeConfigData("03015", vasTerminalDetails.getMid());

					   //Currency Code
					   configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




					   ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


					   InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//




//
//					   final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);





					   TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
					   KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

					   ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

					   TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


					   Log.d("Connection data >>>", new Gson().toJson(_connectionData));

					   Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
					   Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


					   Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

					   Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
					   Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
					   Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

					   Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

					   ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


					   final boolean reversalcom = reversalCommunicator.rollBackTransaction();
					   Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));


//					   final boolean reversalcom = reversalCommunicator.rollBackTransaction();
//					   Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));



//                          reversalcom = reversalCommunicator.rollBackTransaction();


					   final boolean _reversalcom=reversalcom;
					   runOnUiThread(new Runnable() {
						   @Override
						   public void run() {
							   //Update UI




                               String smartCardNumber ="";
                               String meterNumber="";
                               String beneficiaryName ="";
                               String beneficiaryAddress="";
                               String responsemessage = t.getMessage();
                               String amount=airtime_amount;
                               String token ="";
                               String wallet =SecureStorage.retrieve(Helper.TERMINAL_ID,"");
                               String product =appState.product;
                               String transactionRef ="";
                               int logo =appState.logo;
                               boolean error =true;
                               String stan ="";
                               Models.AirtimeModel airtimeModel =new Models.AirtimeModel(error,phone_number);

                               Log.d("appState.logo",String.valueOf(appState.logo));
                               Log.d("Mtn",String.valueOf(R.drawable.mtn_logo));


                               boolean isCardTransaction=true;
                               String transactionTID ="";
                               String merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
                               String merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
                               String merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"");
//				String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                               String vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,"");
                               String vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME,"");
//				String vasTerminalId = SecureStorage.retrieve(Helper.,"");


                               Models.VasDetails vasDetails = null;


//					if (airtimetype.equalsIgnoreCase("wallet")){
//						vasDetails =  new Models.VasDetails(amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,responsemessage,vasmerchantID,transactionRef,VasServices.CASH,logo,date,error,Models.AIRTIME,airtimeModel);
//					}
//					else if (airtimetype.equalsIgnoreCase("card")){

                               responsemessage =_reversalcom ? responsemessage +" \n  \n  Reversal Successful" : responsemessage +" \n   \n  Reversal UnSuccessful";

                               vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.AIRTIME,airtimeModel);
//					}

                               printReceipt(vasDetails);


							   Toast.makeText(getBaseContext(), String.valueOf(_reversalcom), Toast.LENGTH_SHORT).show();

						   }
					   });

				   }
			   }).start();








//
//				 {
//					//Update UI
////					this.Toast.makeText(this, String.valueOf(reversalcom), Toast.LENGTH_SHORT).show();
//
//				}


			}
		});

	}


	 Models.WithdrawalModel  withdrawalModel =null;
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

		com.itex.richard.payviceconnect.model.Pfm pfm = new com.itex.richard.payviceconnect.model.Pfm(new PfmStateGenerator(this,tid).generateState(), new PfmJournalGenerator(appState.trans.getTransactionResult(), appState.nibssData.getConfigData(), false,  amount.toString(), emvCard,"Transfer","Cash-In","").generateJournal());

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

					String smartCardNumber = "";
//						String meterNumber = ""
//						String beneficiaryName = ""
//						String  beneficiaryAddress = "";
					final String responsemessage = response.body().getMessage();
					final String amount = String.valueOf(appState.trans.getTransactionResult().amount/100);
					String token = "";
					final String wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "");
					final String product = appState.product;
					final String transactionRef = "";
					final int  logo =0;
					final String stan = response.body().getTransactionID();
					final boolean  error = response.body().getError();

//						String  isCardTransaction = true
//						String  transactionTID = ""
					final String  merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
					final String  merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
					final String  merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "");
//			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
					final String  vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "");
					final String  vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "");
//				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");
					final String  date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());


//						  Models.VasDetails vasDetails = null;


//                                        if (airtimetype.equals("wallet", ignoreCase = true)) {

//						vasDetails = new Models.VasDetails(stan,amount,wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, product, transferModel);
//                                        } else if (airtimetype.equals("card", ignoreCase = true)) {
//                                            vasDetails = Models.VasDetails(amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.AIRTIME, airtimeModel)
//                                        }

//					printReceipt(vasDetails);

					withdrawalModel = new Models.WithdrawalModel(error,wallet,appState.trans.getCardHolderName());

					if (response.body().getStatus() != 1) {
						Toast.makeText(getBaseContext(), response.body().getMessage() + "", Toast.LENGTH_LONG).show();

						new Thread(new Runnable() {
							@Override
							public void run() {



//					   poslibdb.getVasTerminalDataDao().get();
//					   Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
//					   vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();
//
//					   Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


								//   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
//					   Log.d("okh", vasTerminalDetails.getTid()+" tid");
//
//					   TID = vasTerminalDetails.getTid();
//
//					   Log.d("okh now gotten TID", TID +" tid");


								keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


								configData.storeConfigData("04002", "90");

								//Country Code
								configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

								//MCC
								configData.storeConfigData("08004", vasTerminalDetails.getMcc());


								//Merchant's name - 40 length
								configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

								//Merchant Id - 15 length
								configData.storeConfigData("03015", vasTerminalDetails.getMid());

								//Currency Code
								configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




								ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


								InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//




//
//					   final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);





								TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
								KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

								ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

								TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


								Log.d("Connection data >>>", new Gson().toJson(_connectionData));

								Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
								Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


								Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

								Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
								Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
								Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

								Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

								ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


								final boolean reversalcom = reversalCommunicator.rollBackTransaction();
								Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));


//					   final boolean reversalcom = reversalCommunicator.rollBackTransaction();
//					   Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));




//                          reversalcom = reversalCommunicator.rollBackTransaction();


								final boolean _reversalcom=reversalcom;
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										//Update UI

										 String _responsemessage = "";
										_responsemessage =_reversalcom ? responsemessage +" \n  \n  Reversal Successful" : responsemessage +" \n   \n  Reversal UnSuccessful";
//
//										vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product,responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.AIRTIME,airtimeModel);
////					}
//
										Models.VasDetails vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product, _responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.WITHDRAWAL,withdrawalModel);

										printReceipt(vasDetails);

									}
								});

							}
						}).start();
									} else {
						Toast.makeText(getBaseContext(), "Your wallet has been debitted \n " + amount + " \n " + "Beneficiary : " + response.body().getBeneficiaryName(), Toast.LENGTH_LONG).show();

						Models.VasDetails vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product, responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.WITHDRAWAL,withdrawalModel);

						printReceipt(vasDetails);




					}


				}

			}

			@Override
			public void onFailure(Call<WithdrawalWalletCreditModel> call, final Throwable t) {

				Log.d("On Failure >>>>", t.getMessage());

				new Thread(new Runnable() {
					@Override
					public void run() {



					   poslibdb.getVasTerminalDataDao().get();
					   Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
					   vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();

//					   Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


						//   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
//					   Log.d("okh", vasTerminalDetails.getTid()+" tid");
//
//					   TID = vasTerminalDetails.getTid();
//
//					   Log.d("okh now gotten TID", TID +" tid");


						keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


						configData.storeConfigData("04002", "90");

						//Country Code
						configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

						//MCC
						configData.storeConfigData("08004", vasTerminalDetails.getMcc());


						//Merchant's name - 40 length
						configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

						//Merchant Id - 15 length
						configData.storeConfigData("03015", vasTerminalDetails.getMid());

						//Currency Code
						configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




						ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


						InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//




//
//					   final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);





						final TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
						KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

						ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

						TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


						Log.d("Connection data >>>", new Gson().toJson(_connectionData));

						Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
						Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


						Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

						Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
						Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
						Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

						Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

						ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


						final boolean reversalcom = reversalCommunicator.rollBackTransaction();
						Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));


//					   final boolean reversalcom = reversalCommunicator.rollBackTransaction();
//					   Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));



//                          reversalcom = reversalCommunicator.rollBackTransaction();


						final boolean _reversalcom=reversalcom;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								//Update UI

								final String responsemessage = t.getMessage();
								final String amount = String.valueOf(_transactionData.getInputData().getAmount()/100);
								String token = "";
								final String wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "");
								final String product = appState.product;
								final String transactionRef = "";
								final int  logo =0;
								final String stan = "";
								final boolean  error = true;
//							transferModel = new Models.TransferModel(error,appState.accountNumber,appState.accountName,appState.recivingBank );
								withdrawalModel = new Models.WithdrawalModel(error,wallet,appState.trans.getCardHolderName());

//                                java.lang.IllegalArgumentException: Parameter specified as non-null is null: method kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull, parameter recepiant
//                                at com.wizarpos.emvsample.services.helper.activity.util.Models$TransferModel.<init>(Models.kt)
//                                        at com.wizarpos.emvsample.activity.TransResultActivity$5$2$1.run(TransResultActivity.java:1845)
//

//						String  isCardTransaction = true
//						String  transactionTID = ""
								final String  merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
								final String  merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
								final String  merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "");
//			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
								final String  vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "");
								final String  vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "");
//				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");
								final String  date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());




										String _responsemessage =_reversalcom ? responsemessage +" \n  \n  Reversal Successful" : responsemessage +" \n   \n  Reversal UnSuccessful";
//
									Models.VasDetails vasDetails = new Models.VasDetails(stan,amount,wallet,vasmerchantName,merchantID,merchantName,merchantTerminalId,product, _responsemessage,vasmerchantID,transactionRef,VasServices.CARD,logo,date,error,Models.WITHDRAWAL,withdrawalModel);
////					}
//
										printReceipt(vasDetails);

							}
						});

					}
				}).start();

			}
		});

	}


	public void purchaseData (final DataModel.DataSubscriptionDetails dataSubscriptionDetails){
//		GlobalScope.INSTANCE launch(Dispatchers.Main){





//        appState.dataSubscriptionDetails.setPfm(pfm);
//
//        final DataModel.DataSubscriptionDetails payDetails =appState.dataSubscriptionDetails;

        DataService.Factory.create().dataSubscribeWithCard(dataSubscriptionDetails).enqueue(new Callback<DataModel.DataSubscriptionSuccessResponse>() {
            @Override
            public void onResponse(Call<DataModel.DataSubscriptionSuccessResponse> call, Response<DataModel.DataSubscriptionSuccessResponse> response) {

                DataModel.DataSubscriptionSuccessResponse dataSubscriptionSuccessResponse = response.body();

                String beneficiaryName = "";
                String beneficiaryAddress = "";
                String responsemessage = dataSubscriptionSuccessResponse.getMessage();
                String amount = dataSubscriptionDetails.getAmount();
                String token = "";
                String wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "");
                String product = appState.product;
                String transactionRef = "";
                String stan= dataSubscriptionSuccessResponse.getTransactionID();
                int logo = FuncActivity.appState.logo;
                boolean error = dataSubscriptionSuccessResponse.getError();
                Models.AirtimeModel airtimeModel = new Models.AirtimeModel(error, dataSubscriptionDetails.getPhone());
                String date = "";
                boolean isCardTransaction = true;
                String transactionTID = "";
                String merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
                String merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
                String merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "");
//			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                String vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "");
                String vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "");
//				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");


                Models.VasDetails 	vasDetails = new Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CASH, logo, date, error, Models.DATA, airtimeModel);

                if (!dataSubscriptionSuccessResponse.getError()) {






//                                        if (airtimetype.equals("wallet", ignoreCase = true)) {
//                                        } else if (airtimetype.equals("card", ignoreCase = true)) {
//                                            vasDetails = Models.VasDetails(amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.AIRTIME, airtimeModel)
//                                        }


                    printReceipt(vasDetails);

//                    ElectricityPaymentActivity.print(this@DataPhoneEntry,vasDetails)


                }else{


                    final Models.VasDetails finalVasDetails = vasDetails;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {



                            poslibdb.getVasTerminalDataDao().get();
                            Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
                            vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();

                            Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


                            //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
                            Log.d("okh", vasTerminalDetails.getTid()+" tid");

                            TID = vasTerminalDetails.getTid();

                            Log.d("okh now gotten TID", TID +" tid");


                            keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


                            configData.storeConfigData("04002", "90");

                            //Country Code
                            configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

                            //MCC
                            configData.storeConfigData("08004", vasTerminalDetails.getMcc());


                            //Merchant's name - 40 length
                            configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

                            //Merchant Id - 15 length
                            configData.storeConfigData("03015", vasTerminalDetails.getMid());

                            //Currency Code
                            configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




                            ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


                            InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//





//						  final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);


                            TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
                            KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

                            ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

                            TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


                            Log.d("Connection data >>>", new Gson().toJson(_connectionData));

                            Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
                            Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


                            Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

                            Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
                            Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
                            Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

                            Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

                            ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


                            final boolean reversalcom = reversalCommunicator.rollBackTransaction();
                            Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));



//                          reversalcom = reversalCommunicator.rollBackTransaction();


                            final boolean _reversalcom=reversalcom;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Update UI

                                    String reversalStatus = _reversalcom ? finalVasDetails.getTransactionStatusMessage() +" \n \n Reversal Successful" : finalVasDetails.getTransactionStatusMessage() +" \n \n Reversal UnSuccessful";
                                    finalVasDetails.setTransactionStatusMessage(reversalStatus);
                                    printReceipt(finalVasDetails);
                                    Toast.makeText(getBaseContext(), String.valueOf(_reversalcom), Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    }).start();



//                    printReceipt(vasDetails);

                }
            }

            @Override
            public void onFailure(Call<DataModel.DataSubscriptionSuccessResponse> call, Throwable t) {

                String beneficiaryName = "";
                String beneficiaryAddress = "";
                String responsemessage = t.getMessage();
                String amount = dataSubscriptionDetails.getAmount();
                String token = "";
                String wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "");
                String product = appState.product;
                String transactionRef = "";
                String stan= "";
                int logo = FuncActivity.appState.logo;
                boolean error = true;
                Models.AirtimeModel airtimeModel = new Models.AirtimeModel(error, dataSubscriptionDetails.getPhone());
                String date = "";
                boolean isCardTransaction = true;
                String transactionTID = "";
                String merchantID = FuncActivity.appState.nibssData.getConfigData().getConfigData("03015").toString();
                String merchantName = FuncActivity.appState.nibssData.getConfigData().getConfigData("52040").toString();
                String merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "");
//			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                String vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "");
                String vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "");

				final Models.VasDetails 	vasDetails = new Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CASH, logo, date, error, Models.DATA, airtimeModel);


				new Thread(new Runnable() {
                    @Override
                    public void run() {



                        poslibdb.getVasTerminalDataDao().get();
                        Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
                        vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();

                        Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


                        //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
                        Log.d("okh", vasTerminalDetails.getTid()+" tid");

                        TID = vasTerminalDetails.getTid();

                        Log.d("okh now gotten TID", TID +" tid");


                        keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


                        configData.storeConfigData("04002", "90");

                        //Country Code
                        configData.storeConfigData("06003", vasTerminalDetails.getCountryCode());

                        //MCC
                        configData.storeConfigData("08004", vasTerminalDetails.getMcc());


                        //Merchant's name - 40 length
                        configData.storeConfigData("52040", vasTerminalDetails.getMerchantName());

                        //Merchant Id - 15 length
                        configData.storeConfigData("03015", vasTerminalDetails.getMid());

                        //Currency Code
                        configData.storeConfigData("05003", vasTerminalDetails.getCurrencyCode());




                        ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


                        InputData inputData =new InputData(appState.trans.getTransAmount(),appState.trans.getOthersAmount(),"Default");
//





//						  final TransactionData transactionData =new TransactionData(inputData,emvCard,configData,keyHolder);


                        TransactionData _transactionData=new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_DATA,""),TransactionData.class);
                        KeyHolder keyHolder=new Gson().fromJson(SecureStorage.retrieve(Helper.KEY_HOLDER,""),KeyHolder.class);

                        ConnectionData _connectionData=new Gson().fromJson(SecureStorage.retrieve(Helper.CONNECTION_DATA,""),ConnectionData.class);

                        TransactionResult transactionResult  =       new Gson().fromJson(SecureStorage.retrieve(Helper.TRANSACTION_RESULT,""), TransactionResult.class)  ;


                        Log.d("Connection data >>>", new Gson().toJson(_connectionData));

                        Log.d("ConfigData  data >>>", new Gson().toJson(_transactionData.getConfigData()));
                        Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


                        Log.d("EmvCard data >>>", new Gson().toJson(_transactionData.getEmvCard()));

                        Log.d("TransactionData  data >>>", new Gson().toJson(_transactionData));
                        Log.d("InputData data >>>", new Gson().toJson(_transactionData.getInputData()));
                        Log.d("Transaction result on terminal >>>", new Gson().toJson(appState.trans.getTransactionResult()));

                        Log.d("Transaction result I stored >>>", new Gson().toJson(transactionResult));

                        ReversalCommunicator reversalCommunicator = new ReversalCommunicator(getBaseContext(), _transactionData, _connectionData, keyHolder,appState.trans.getTransactionResult());


                        final boolean reversalcom = reversalCommunicator.rollBackTransaction();
                        Log.d("reversal com >>>>>>>", String.valueOf(reversalcom));



//                          reversalcom = reversalCommunicator.rollBackTransaction();


                        final boolean _reversalcom=reversalcom;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Update UI

                                String reversalStatus = _reversalcom ? vasDetails.getTransactionStatusMessage() +" \n \n Reversal Successful" : vasDetails.getTransactionStatusMessage() +" \n \n Reversal UnSuccessful";
                                vasDetails.setTransactionStatusMessage(reversalStatus);
                                printReceipt(vasDetails);
                                Toast.makeText(getBaseContext(), String.valueOf(_reversalcom), Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).start();

            }
        });

//			try {
//				val response = DataService.Factory.create().dataSubscribe(payDetails).await()
//				val jsonResponse = Gson().toJsonTree(response).asJsonObject
//
//				Log.d("OkHData", jsonResponse.toString())
//
//				if (jsonResponse.toString().contains("\"error\":false")) {
//
//					val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
//
//					val formattedResponse = gson.fromJson(jsonResponse, DataModel.DataSubscriptionSuccessResponse::class.java)
//
//					GlobalScope.launch(Dispatchers.Main) {
//						mAirtimeProcessDialog.dismiss()
//						val terminalID = SecureStorage.retrieve(Helper.TERMINAL, "")
//						val amount = formattedResponse.amount
//						val ref = formattedResponse.ref
//						alert {
//							title = "Response"
//							message = formattedResponse.message
//							positiveButton(buttonText = "Print") {
//								var transactionModel: TransactionModel? = null
//								val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
//								val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
//								try {
//									val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
//									var bankLogoName = ""
//									try {
//										bankLogoName = "bank" + terminalID.substring(0, 4)
//									} catch (e: Exception) {
//
//									}

//                                        transactionModel = TransactionModel(terminalID, ref, "", "", amount, "", "data", "", "Approved", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, mPhoneNumber);
//
//                                        val intent = Intent(baseContext, MainActivity::class.java)
//
//                                        intent.putExtra("transactionModel", transactionModel)
//                                        intent.putExtra("copy", "** CUSTOMER COPY **")
//                                        startActivity(intent)
//                                        val alertDialog = AlertDialog.Builder(baseContext)
//                                        alertDialog.setMessage("Print Merchant copy")
//                                        val finalTransactionModel = transactionModel
//                                        alertDialog.setPositiveButton("OK") { dialogInterface, i ->
//                                            val intent = Intent(baseContext, MainActivity::class.java)
//                                            intent.putExtra("transactionModel", finalTransactionModel)
//                                            intent.putExtra("copy", "*** MERCHANT COPY ***")
//                                            startActivity(intent)
//                                        }
//                                        alertDialog.show()


//									val smartCardNumber = ""
//									val meterNumber = ""
//									val beneficiaryName = ""
//									val beneficiaryAddress = ""
//									val responsemessage = formattedResponse.message
//									val amount = formattedResponse.amount
//									val token = ""
//									val wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
//									val product = appState.product
//									val transactionRef = ""
//									val stan= formattedResponse.transactionID
//									val logo = FuncActivity.appState.logo
//									val error :Boolean= formattedResponse.error
//									val airtimeModel = Models.AirtimeModel(error, mPhoneNumber)
//
//									val isCardTransaction = true
//									val transactionTID = ""
//									val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
//									val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
//									val merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "")
////			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
//									val vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "")
//									val vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "")
////				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");
//
//
//									var vasDetails: Models.VasDetails? = null
//
//
////                                        if (airtimetype.equals("wallet", ignoreCase = true)) {
//									vasDetails = Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CASH, logo, date, error, Models.AIRTIME, airtimeModel)
////                                        } else if (airtimetype.equals("card", ignoreCase = true)) {
////                                            vasDetails = Models.VasDetails(amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.AIRTIME, airtimeModel)
////                                        }
//
//									ElectricityPaymentActivity.print(this@DataPhoneEntry,vasDetails)
//								} catch (e: Exception) {
//								}
//							}
//						}.show()
//					}
//				} else {
//					val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
//
//					val formattedResponse = gson.fromJson(jsonResponse, DataModel.DataSubscriptionFailedResponse::class.java)
//					//todo handle a rollback
//					GlobalScope.launch(Dispatchers.Main) {
//						mAirtimeProcessDialog.dismiss()
//						val terminalID = SecureStorage.retrieve(Helper.TERMINAL, "")
//						val ref = formattedResponse.ref
//						alert {
//							title = "Response"
//							message = formattedResponse.message
//							positiveButton(buttonText = "Print") {
//								var transactionModel: TransactionModel? = null
//								val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
//								val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
//								try {
//									val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().time)
//									var bankLogoName = ""
//									try {
//										bankLogoName = "bank" + terminalID.substring(0, 4)
//									} catch (e: Exception) {
//
//									}
//
////                                        transactionModel = TransactionModel(terminalID, ref, "", "", dataItem.amount, "", "data", "", "Declined", "", merchantID, merchantName, "", "", "", "", "", "", date, "", "", bankLogoName, mPhoneNumber);
////
////                                        val intent = Intent(baseContext, MainActivity::class.java)
////
////                                        intent.putExtra("transactionModel", transactionModel)
////                                        intent.putExtra("copy", "** CUSTOMER COPY **")
////                                        startActivity(intent)
////                                        val alertDialog = AlertDialog.Builder(baseContext)
////                                        alertDialog.setMessage("Print Merchant copy")
////                                        val finalTransactionModel = transactionModel
////                                        alertDialog.setPositiveButton("OK") { dialogInterface, i ->
////                                            val intent = Intent(baseContext, MainActivity::class.java)
////                                            intent.putExtra("transactionModel", finalTransactionModel)
////                                            intent.putExtra("copy", "*** MERCHANT COPY ***")
////                                            startActivity(intent)
////                                        }
////                                        alertDialog.show()
//
//									val smartCardNumber = ""
//									val meterNumber = ""
//									val beneficiaryName = ""
//									val beneficiaryAddress = ""
//									val responsemessage = formattedResponse.message
//									val amount = dataItem.amount
//									val token = ""
//									val wallet = SecureStorage.retrieve(Helper.TERMINAL_ID, "")
//									val product = appState.product
//									val transactionRef = ""
//									val logo = FuncActivity.appState.logo
//									val stan  = "";
//									val error = formattedResponse.error
//									val airtimeModel = Models.AirtimeModel(error, mPhoneNumber)
//
//									val isCardTransaction = true
//									val transactionTID = ""
//									val merchantID = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()
//									val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
//									val merchantTerminalId = SecureStorage.retrieve(Helper.TERMINAL, "")
////			                          	String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
//									val vasmerchantID = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID, "")
//									val vasmerchantName = SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME, "")
////				                        String vasTerminalId = SecureStorage.retrieve(Helper.,"");
//
//
////                                     null
//
//
////                                        if (airtimetype.equals("wallet", ignoreCase = true)) {
//									var vasDetails: Models.VasDetails? =Models.VasDetails(stan,amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CASH, logo, date, error, Models.DATA, airtimeModel)
////                                        } else if (airtimetype.equals("card", ignoreCase = true)) {
////                                            vasDetails = Models.VasDetails(amount, wallet, vasmerchantName, merchantID, merchantName, merchantTerminalId, product, responsemessage, vasmerchantID, transactionRef, VasServices.CARD, logo, date, error, Models.AIRTIME, airtimeModel)
////                                        }
//
//									ElectricityPaymentActivity.print(this@DataPhoneEntry,vasDetails!!)
//									// PrinterHelper.getInstance().airtimeReceipt(FuncActivity.appState, 1,  model)
//								} catch (e: Exception) {
//								}
//							}
//						}.show()
//					}
//				}
//			}
//			catch (exception: ConnectException) {
//				GlobalScope.launch(Dispatchers.Main){
//					mAirtimeProcessDialog.dismiss()
//					alert {
//						title = "Response"
//						message = "Error in connection. Please check your internet connection"
//						okButton { }
//					}.show()
//				}
//
//			}
//                catch (exception: SocketTimeoutException) {
//				GlobalScope.launch(Dispatchers.Main) {
//					mAirtimeProcessDialog.dismiss()
//					alert {
//						title = "Response"
//						message = "This connection is taking too long. Please try again"
//					}.show()
//				}
//			}
//                catch (e: retrofit2.HttpException) {
//				GlobalScope.launch(Dispatchers.Main) {
//					mAirtimeProcessDialog.dismiss()
//					alert {
//						title = "Error"
//						message = "Error from server. Please try again"
//						okButton { }
//					}.show()
//				}
//			}
//		}


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
//    	printReceipt();
    }



//    private void   purchaseData(){}


	private void printReceipt(Models.VasDetails vasDetails) {

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

		if(SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"").equals("")){
			SecureStorage.store(Helper.TERMINAL_ENTERED_BY_USER,terminalID);
		}
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
        String amount = (appState.trans.getTransAmount())+"";
		String Expiary = appState.trans.getExpiry();
        String othersAmount = appState.trans.getOthersAmount().toString();

		String bankLogoName = "";
        try{
			bankLogoName = "bank" + terminalID.substring(0, 4);
		}catch (Exception e){

		}
		Models.CardDetails cardDetails =new Models.CardDetails(terminalID,rrn,cardholderName, pan, amount, othersAmount, transactionType, responseCode, transactionstatus, transactionstatusReason,merchantID, merchantName, ticket, UNPR, AC, TVR, AID, TSI, date, cardType, Expiary, bankLogoName);


//		MainApp.transactionModel = new TransactionModel(cardDetails,vasDetails);
//		final  TransactionModel transactionModel = MainApp.transactionModel;

//		MainApp.transactionModel = new TransactionModel(cardDetails,vasDetails);
		final  TransactionModel transactionModel =  new TransactionModel(cardDetails,vasDetails);

//        Log.i("Yeah TransactionResult >>>", transactionModel.toString());

		Log.i("appState.airtime >>>", String.valueOf(appState.airtime));

		Log.i("!appState.airtime >>>", String.valueOf((!appState.airtime)));

		Log.d("Print", "printReceipt() called with: cardDetails = [" + cardDetails + "]");

		Log.d("Print", "printReceipt() called with: vasDetails = [" + vasDetails + "]");

		Log.d("Print", "printReceipt() called with: transactionModel = [" + transactionModel + "]");




		//try {
//		if(!appState.airtime) {

//			Log.i("!appState.airtime  here >>>", String.valueOf((!appState.airtime)));
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
//		}else{
////			Log.i("!appState.airtime  else here >>>", String.valueOf((!appState.airtime)));
//			appState.airtime = false;
//		}
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
