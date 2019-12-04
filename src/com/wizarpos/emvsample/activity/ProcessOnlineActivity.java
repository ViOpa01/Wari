package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cloudpos.jniinterface.PINPadInterface;
import com.google.gson.Gson;
import com.iisysgroup.poslib.ISO.POSVAS.PosvasKeyProcessor;
import com.iisysgroup.poslib.commons.emv.EmvCard;
import com.iisysgroup.poslib.host.Host;
import com.iisysgroup.poslib.host.dao.PosLibDatabase;
import com.iisysgroup.poslib.host.dao.VasTerminalDataDao;
import com.iisysgroup.poslib.host.entities.ConfigData;
import com.iisysgroup.poslib.host.entities.ConnectionData;
import com.iisysgroup.poslib.host.entities.KeyHolder;
import com.iisysgroup.poslib.host.entities.TransactionResult;
import com.iisysgroup.poslib.host.entities.VasTerminalData;
import com.iisysgroup.poslib.utils.AccountType;
import com.iisysgroup.poslib.utils.InputData;
import com.iisysgroup.poslib.utils.TransactionData;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.VasCommunicator;
import com.wizarpos.emvsample.activity.login.Helper;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;
import com.wizarpos.emvsample.db.TransactionResultService;
import com.wizarpos.emvsample.transaction.Nibss;
import com.wizarpos.util.ByteUtil;
import com.wizarpos.util.StringUtil;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_tag_data;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_tag_list_data;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_is_tag_present;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_online_result;
import static com.iisysgroup.poslib.ISO.POSVAS.PosvasKeyProcessor.decryptKey;
import static com.wizarpos.emvsample.getMasterKey.getMasterKey;
import static com.wizarpos.emvsample.transaction.Nibss.poslibdb;

public class ProcessOnlineActivity extends FuncActivity
{
	private TextView textTitle  = null;
	private Button   buttonBack = null;
    private Button   buttonMore = null;
    Host.TransactionType transactionType = Host.TransactionType.PURCHASE;

	private int nibsTag[] = { 0x9F1A ,0x82 , 0x5F2A , 0x9F02 , 0x9C, 0x9A, 0x95, 0x9F36 , 0x9F37 ,
			0x9F10 , 0x9F34 , 0x9F35, 0x5F34, 0x9F33, 0x9F27 , 0x9F26};
    

    boolean socketThreadRun = false;
    boolean requestDataReady = false;

    private static InputData inputData = null;
    private static EmvCard emvCard = null;
	VasTerminalData vasTerminalDetails=null;
	KeyHolder keyHolder=null;
	String TID=null;

	@Override
    public void onCreate(Bundle savedInstanceState)
	{

		if(appState.balanceEnc){
			transactionType = Host.TransactionType.BALANCE_INQUIRY;
			appState.trans.setTransAmount(0);
			appState.balanceEnc = false;
			appState.trans.setBalanceTxn(true);
			appState.trans.setRefundTxn(false);
			appState.trans.setPurchasewithCash(false);
			appState.trans.setReversal(false);
		}

		if(appState.refund){
			transactionType = Host.TransactionType.REFUND;
			appState.refund = false;
			appState.trans.setBalanceTxn(false);
			appState.trans.setRefundTxn(true);

			appState.trans.setPurchasewithCash(false);
			appState.trans.setReversal(false);

		}

		if(appState.purchaseWithCashBack){
			transactionType = Host.TransactionType.PURCHASE_WITH_CASH_BACK;
			appState.purchaseWithCashBack = false;
			appState.trans.setBalanceTxn(false);
			appState.trans.setRefundTxn(false);
			appState.trans.setPurchasewithCash(true);
			appState.trans.setReversal(false);

		}

		if(appState.revarsal){
			transactionType = Host.TransactionType.REVERSAL;
			appState.revarsal = false;
			appState.purchaseWithCashBack = false;
			appState.trans.setBalanceTxn(false);
			appState.trans.setRefundTxn(false);
			appState.trans.setPurchasewithCash(false);
			appState.trans.setReversal(true);

		}

		showPropress("Please wait..");
		if(debug)Log.d(APP_TAG, "processOnlineActivity onCreate");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_process_online);
		initToolbar();

    	// title
		getEMVCardInfo();
		appState.goneOnline = true;
//		textTitle = (TextView)findViewById(R.id.tAppTitle);
//		setTitle(textTitle);
//		buttonBack = (Button)findViewById(R.id.btn_back);
//        buttonBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
//
//        buttonMore = (Button)findViewById(R.id.btn_more);
//        buttonMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));

        if(appState.nibssData == null){
        	//Terminal has not been configured
            Log.i("nibs", "Prep terminal");
            setResult(8984, getIntent());
        }else{
			final Nibss nibss = new Nibss(ProcessOnlineActivity.this);

			//Build Pin info
			EmvCard.PinInfo pinInfo;
			byte[] pinblock = appState.trans.getPinBlock();
			if(pinblock != null){
                Log.d(" >>>>>> okh", "pinblock1 :" + pinblock);
                String StringedPinBlock = StringUtil.toHexString(pinblock,false);
				Log.d(">>>>> okh", "pinblock1 StringedPinBlock :" + StringedPinBlock);
                PINPadInterface.close();

//				String clearPinBlock = null;
////
////					Log.d("okh", "pinblock1 StringedPinBlock before use  :" + StringedPinBlock);
////
////					//se clear pin key
////
//				Log.d("okh", "pinblock1 stringCipherNewUserKey : " +  appState.stringCipherNewUserKey);
//				Log.d("okh", "pinblock1 static key : " + appState.PlainKeyInjected);
//				String clearPinKey;
//				try {
//					 clearPinKey = PosvasKeyProcessor.decryptKey(appState.PlainKeyInjected, appState.stringCipherNewUserKey);
//					Log.d("okh", "pinblock1 clearPinKey>>>>> clearPinKey :" + clearPinKey);
//
//
//
//
//					clearPinBlock = StringUtil.threeDesDecrypt(appState.stringCipherNewUserKey, StringedPinBlock);
//				} catch (InvalidKeyException e) {
//					e.printStackTrace();
//				} catch (NoSuchAlgorithmException e) {
//					e.printStackTrace();
//				} catch (NoSuchPaddingException e) {
//					e.printStackTrace();
//				} catch (InvalidKeySpecException e) {
//					e.printStackTrace();
//				} catch (IllegalBlockSizeException e) {
//					e.printStackTrace();
//				} catch (NoSuchProviderException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}

								pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(),null, pinblock);





//				Log.d("okh", "pinblock1 Decrypted with pin injected >>>>> clearPinBlock :" + clearPinBlock);


//				String clearMaster = getMasterKey(appState.nibssData.getKeyHolder().getMasterKey(),false);
//				String clearPinKey;
//				try {
//					clearPinKey = decryptKey(appState.nibssData.getKeyHolder().getPinKey(), clearMaster);
//					Log.i("clearkey",clearPinKey);
//				} catch (Exception e) {
//					e.printStackTrace();
////					notifyPinError();
//					return;
//				}

//				pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(),null, StringUtil.hexString2bytes(appState.clearPin));
//				pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(),null, StringUtil.hexString2bytes(clearPinKey));

//                emvCard = new EmvCard(appState.trans.getCardHolderName(),appState.trans.getTrack2Data(),
//                        appState.trans.getICCData(),pinInfo);
			}
			else{
				Log.d("okh", "pinblock2 :" + pinblock);
                pinInfo = new EmvCard.PinInfo(null, null, null);
            }
//			else{
//				Log.i("okh", "pinblock2 :" + pinblock);
//				pinInfo = null;
////                emvCard = new EmvCard(appState.trans.getCardHolderName(),appState.trans.getTrack2Data(),
////                        appState.trans.getICCData(),pinInfo);
//			}
			//Build emv carf
			emvCard = new EmvCard(appState.trans.getCardHolderName(),appState.trans.getTrack2Data(),
					appState.trans.getICCData(),pinInfo);

			//Build InputData
			inputData = new InputData(appState.trans.getTransAmount().longValue(),
					appState.trans.getOthersAmount().longValue(), AccountType.DEFAULT_UNSPECIFIED);

			//Go online
			if(transactionType == Host.TransactionType.REFUND){
				Log.d("okh", "refund");
				nibss.goOnline(emvCard, transactionType, inputData,
						appState.nibssData.getKeyHolder(), appState.nibssData.getConfigData(),
						appState.nibssData.getConnectionData(), appState.isoRefundProcessData ,new Nibss.Nibs<TransactionResult>() {
							@Override
							public void complete(TransactionResult res) {
								appState.trans.setMaskPan(res.PAN);
								appState.trans.setRrn(res.RRN);
								appState.trans.setTransactionResult(res);
								if(res.isApproved()){
									Log.i("okh", res.toString());
									appState.trans.setTrace(appState.terminalConfig.getTrace());

									appState.trans.setResponseCode(res.responseCode.getBytes());
									try{
										appState.trans.setIssuerAuthData(res.issuerAuthData91.getBytes(),0,res.issuerAuthData91.getBytes().length);
									}catch (Exception e){
										e.printStackTrace();
									}
									appState.trans.setTransactionStatus(true);
									appState.terminalConfig.incTrace();
									processResult();
								}else{
									Log.i("okh", res.toString());
									appState.trans.setTrace(appState.terminalConfig.getTrace());
									appState.trans.setResponseCode(new byte[]{'F','F'});
									appState.trans.setTransactionStatus(false);
									appState.terminalConfig.incTrace();
									processResult();
								}
							}

							@Override
							public void error(String e) {
								Log.i("okh", e );
								appState.trans.setTrace(appState.terminalConfig.getTrace());
								appState.trans.setResponseCode(new byte[]{'F','F'});
								appState.terminalConfig.incTrace();
								processResult();
							}
						});

			}
			else if(transactionType == Host.TransactionType.PURCHASE_WITH_CASH_BACK){


				Log.d(" process online okh  ", "Reversal");
				Log.d(" Transaction Type  ", transactionType.name());
				Log.d(" InputData Type  ", new Gson().toJson(inputData));


				Log.d(" process online okh airtime", String.valueOf(appState.airtime));
				Log.d("process online okh data", String.valueOf(appState.data));
				Log.d("process online okh cableTv", String.valueOf(appState.cableTv));
				Log.d("process online okh  electricityBills", String.valueOf(appState.electricityBills));

				nibss.goOnline(emvCard, transactionType, inputData,
						appState.nibssData.getKeyHolder(), appState.nibssData.getConfigData(),
						appState.nibssData.getConnectionData(), new Nibss.Nibs<TransactionResult>() {
							@Override
							public void complete(TransactionResult res) {
								appState.trans.setMaskPan(res.PAN);
								appState.trans.setRrn(res.RRN);
								appState.trans.setTransactionResult(res);
								if(res.isApproved()){
									Log.i("okh", res.toString());
									appState.trans.setTrace(appState.terminalConfig.getTrace());

									appState.trans.setResponseCode(res.responseCode.getBytes());
									try{
										appState.trans.setIssuerAuthData(res.issuerAuthData91.getBytes(),0,res.issuerAuthData91.getBytes().length);
									}catch (Exception e){
										e.printStackTrace();
									}
									appState.trans.setTransactionStatus(true);
									appState.terminalConfig.incTrace();
									processResult();
								}else{
									Log.i("okh", res.toString());
									appState.trans.setTrace(appState.terminalConfig.getTrace());
									appState.trans.setResponseCode(new byte[]{'F','F'});
									appState.trans.setTransactionStatus(false);
									appState.terminalConfig.incTrace();
									processResult();
								}
							}

							@Override
							public void error(String e) {
								Log.i("okh", e );
								appState.trans.setTrace(appState.terminalConfig.getTrace());
								appState.trans.setResponseCode(new byte[]{'F','F'});
								appState.terminalConfig.incTrace();
								processResult();
							}
						});

			}
			else if(transactionType == Host.TransactionType.REVERSAL){
				Log.d("okh", "reversal");
				nibss.reverse(emvCard,transactionType,inputData,appState.nibssData.getKeyHolder(), appState.nibssData.getConfigData(),
						appState.nibssData.getConnectionData(),appState.iisoReverSal,new Nibss.Nibs<TransactionResult>() {
							@Override
							public void complete(TransactionResult res) {
								appState.trans.setMaskPan(res.PAN);
								appState.trans.setRrn(res.RRN);
								appState.trans.setTransactionResult(res);
								if(res.isApproved()){
									Log.i("okh", res.toString());
									appState.trans.setTrace(appState.terminalConfig.getTrace());

									appState.trans.setResponseCode(res.responseCode.getBytes());
									try{
										appState.trans.setIssuerAuthData(res.issuerAuthData91.getBytes(),0,res.issuerAuthData91.getBytes().length);
									}catch (Exception e){
										e.printStackTrace();
									}
									appState.trans.setTransactionStatus(true);
									appState.terminalConfig.incTrace();
									processResult();
								}else{
									Log.i("okh", res.toString());
									appState.trans.setTrace(appState.terminalConfig.getTrace());
									appState.trans.setResponseCode(new byte[]{'F','F'});
									appState.trans.setTransactionStatus(false);
									appState.terminalConfig.incTrace();
									processResult();
								}
							}

							@Override
							public void error(String e) {
								Log.i("okh", e );
								appState.trans.setTrace(appState.terminalConfig.getTrace());
								appState.trans.setResponseCode(new byte[]{'F','F'});
								appState.terminalConfig.incTrace();
								processResult();
							}
						});
			}
			else if (appState.airtime || appState.data || appState.cableTv || appState.electricityBills ||appState.withdrawal){
				Log.i("okh", "appState.airtime || appState.data || appState.cableTv || appState.electricityBills");
//				Nibss nibss = new Nibss(ProcessOnlineActivity.this);

				//Build Pin info
//				EmvCard.PinInfo pinInfo;

				Log.d("process online okh airtime", String.valueOf(appState.airtime));
				Log.d("process online okh data", String.valueOf(appState.data));
				Log.d("process online okh cableTv", String.valueOf(appState.cableTv));
				Log.d("process online okh  electricityBills", String.valueOf(appState.electricityBills));


				if(appState.trans.getPinBlock() != null){
					Log.i("okh", "pinblock :" + appState.trans.getPinBlock());
					pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(),null, StringUtil.hexString2bytes(appState.clearPin));
				}
				else{
					pinInfo = null;
				}
				//Build emv carf
				emvCard = new EmvCard(appState.trans.getCardHolderName(),appState.trans.getTrack2Data(),
						appState.trans.getICCData(),pinInfo);

				//Build InputData
				inputData = new InputData(appState.trans.getTransAmount().longValue(),
						appState.trans.getOthersAmount().longValue(), AccountType.DEFAULT_UNSPECIFIED);

//				new saveVasKeyHolder(this).execute();

				final ConfigData configData = new ConfigData();



				new Thread(new Runnable() {
					  @Override
					  public void run() {
						  poslibdb.getVasTerminalDataDao().get();
//						  Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
//						  vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();
						  vasTerminalDetails = new Gson().fromJson(SecureStorage.retrieve(Helper.VAS_COMMUNICATOR,""),VasTerminalData.class);


						  Log.d("OkH", "vasterminal Details "+ new Gson().toJson(vasTerminalDetails));
//				Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());


						  //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
						  Log.d("okh", vasTerminalDetails.getTid()+" tid");

						   TID = vasTerminalDetails.getTid();

						  Log.d("okh now gotten TID", TID +" tid");


						   keyHolder = new KeyHolder(vasTerminalDetails.getMasterKey(), vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


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



                            //EPMS connectionData
//						  ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);
						  ConnectionData connectionData =  new ConnectionData(TID, appState.nibssData.getConnectionData().getIpAddress(), appState.nibssData.getConnectionData().getIpPort(), true);




						  Log.d("Connection data >>>", new Gson().toJson(connectionData));

						  Log.d("ConfigData  data >>>", new Gson().toJson(configData));
						  Log.d("KeyHolder data >>>", new Gson().toJson(keyHolder));


						  Log.d("EmvCard data >>>", new Gson().toJson(emvCard));

						  Log.d("TransactionData  data >>>", new Gson().toJson(transactionType));
						  Log.d("InputData data >>>", new Gson().toJson(inputData));



						  TransactionData transactionData = new TransactionData(inputData, emvCard, configData, keyHolder);
						  Log.d("okh", inputData.getAmount() + emvCard.getCardHolderName() + configData);
						  Log.d("okh",  transactionData.getKeyHolder().getMasterKey() + ""+connectionData.getTerminalID()+"");



						  SecureStorage.store(Helper.TRANSACTION_DATA,new Gson().toJson(transactionData));
						  SecureStorage.store(Helper.KEY_HOLDER,new Gson().toJson(keyHolder));

						  SecureStorage.store(Helper.CONNECTION_DATA,new Gson().toJson(connectionData));

						  nibss.vasGoOnline(emvCard, transactionType, inputData,
								  keyHolder, configData,
								  connectionData, new Nibss.Nibs<TransactionResult>() {
									  @Override
									  public void complete(TransactionResult res) {
										  appState.trans.setMaskPan(res.PAN);
										  appState.trans.setRrn(res.RRN);
										  appState.trans.setTransactionResult(res);
										  if(res.isApproved()){
											  Log.i("okh", res.toString());
											  SecureStorage.store(Helper.TRANSACTION_RESULT,new Gson().toJson(res));

											  appState.trans.setTrace(appState.terminalConfig.getTrace());

											  appState.trans.setResponseCode(res.responseCode.getBytes());
											  try{
												  appState.trans.setIssuerAuthData(res.issuerAuthData91.getBytes(),0,res.issuerAuthData91.getBytes().length);
											  }catch (Exception e){
												  e.printStackTrace();
											  }
											  appState.trans.setTransactionStatus(true);
											  appState.terminalConfig.incTrace();
											  processResult();
										  }else{
											  Log.i("okh", res.toString());
											  appState.trans.setTrace(appState.terminalConfig.getTrace());
											  appState.trans.setResponseCode(new byte[]{'F','F'});
											  appState.trans.setTransactionStatus(false);
											  appState.terminalConfig.incTrace();
											  processResult();
										  }
									  }

									  @Override
									  public void error(String e) {
										  Log.i("okh", e );
										  appState.trans.setTrace(appState.terminalConfig.getTrace());
										  appState.trans.setResponseCode(new byte[]{'F','F'});
										  appState.terminalConfig.incTrace();
										  processResult();
									  }
								  });
					  }
				  }).start();


//				if (appState.withdrawal){
//					Log.i("okh", "Withdrawal");
//					Nibss nibss = new Nibss(ProcessOnlineActivity.this);
//
//					//Build Pin info
//					EmvCard.PinInfo pinInfo;
//					if(appState.trans.getPinBlock() != null){
//						Log.i("okh", "pinblock :" + appState.trans.getPinBlock());
//						pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(),null, StringUtil.hexString2bytes(appState.clearPin));
//					}
//					else{
//						pinInfo = null;
//					}
//					//Build emv carf
//					emvCard = new EmvCard(appState.trans.getCardHolderName(),appState.trans.getTrack2Data(),
//							appState.trans.getICCData(),pinInfo);
//
//					//Build InputData
//					inputData = new InputData(appState.trans.getTransAmount().longValue(),
//							appState.trans.getOthersAmount().longValue(), AccountType.DEFAULT_UNSPECIFIED);
//
//					new saveVasKeyHolder(this).execute();
//
//				}


//			ConnectionData connectionData = new ConnectionData(TID, "197.253.19.78", 5001, true);





//				TransactionData transactionData = new TransactionData(inputData, emvCard, configData, keyHolder);
				Log.d("okh", inputData.getAmount() + emvCard.getCardHolderName() + configData);
//				Log.d("okh",  transactionData.getKeyHolder().getMasterKey() + ""+connectionData.getTerminalID()+"");
//				VasCommunicator  varsr = new VasCommunicator(mContext,transactionData,connectionData, keyHolder);






			}
			else{
				Log.d(" process online okh  ", "purchase");

				Log.d(" process online okh airtime", String.valueOf(appState.airtime));
				Log.d("process online okh data", String.valueOf(appState.data));
				Log.d("process online okh cableTv", String.valueOf(appState.cableTv));
				Log.d("process online okh  electricityBills", String.valueOf(appState.electricityBills));

				nibss.goOnline(emvCard, transactionType, inputData,
						appState.nibssData.getKeyHolder(), appState.nibssData.getConfigData(),
						appState.nibssData.getConnectionData(), new Nibss.Nibs<TransactionResult>() {
							@Override
							public void complete(TransactionResult res) {
								appState.trans.setMaskPan(res.PAN);
								appState.trans.setRrn(res.RRN);
								appState.trans.setTransactionResult(res);
								if(res.isApproved()){
									Log.i("okh", res.toString());
									appState.trans.setTrace(appState.terminalConfig.getTrace());

									appState.trans.setResponseCode(res.responseCode.getBytes());
									try{
										appState.trans.setIssuerAuthData(res.issuerAuthData91.getBytes(),0,res.issuerAuthData91.getBytes().length);
									}catch (Exception e){
										e.printStackTrace();
									}
									appState.trans.setTransactionStatus(true);
									appState.terminalConfig.incTrace();
									processResult();
								}else{
									Log.i("okh", res.toString());
									appState.trans.setTrace(appState.terminalConfig.getTrace());
									appState.trans.setResponseCode(new byte[]{'F','F'});
									appState.trans.setTransactionStatus(false);
									appState.terminalConfig.incTrace();
									processResult();
								}
							}

							@Override
							public void error(String e) {
								Log.i("okh", e );
								appState.trans.setTrace(appState.terminalConfig.getTrace());
								appState.trans.setResponseCode(new byte[]{'F','F'});
								appState.terminalConfig.incTrace();
								processResult();
							}
						});
			}
		}







	}

	public static class doVAs extends AsyncTask<PosLibDatabase, Void, VasTerminalDataDao> {

		@Override
		protected VasTerminalDataDao doInBackground(PosLibDatabase... posLibDatabases) {
			return posLibDatabases[0].getVasTerminalDataDao();
		}

		@Override
		protected void onPostExecute(VasTerminalDataDao vasTerminalDataDao) {
//			Log.d("okh", "vas transresult "+vasTerminalDataDao.get().getTid()+ vasTerminalDataDao.get().getMasterKey());
			super.onPostExecute(vasTerminalDataDao);
		}
	}

	private static class saveVasKeyHolder extends AsyncTask<Void, Integer, TransactionResult> {

		private Context mContext;

		private saveVasKeyHolder (Context context){
			mContext = context;
		}

		protected TransactionResult doInBackground(Void...voids) {
			poslibdb.getVasTerminalDataDao().get();
			Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
			VasTerminalData vasTerminalDetails = poslibdb.getVasTerminalDataDao().get();
			//   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
			Log.d("okh", vasTerminalDetails.getTid()+" tid");
			ConfigData configData = new ConfigData();
			String TID = vasTerminalDetails.getTid();



//			ConnectionData connectionData = new ConnectionData(TID, "197.253.19.78", 5001, true);


			//Raw epms
//			ConnectionData connectionData = new ConnectionData(TID, "196.6.103.73", 5043, true);


			ConnectionData connectionData = new ConnectionData(TID, "196.6.103.18", 5014, true);

			KeyHolder keyHolder = new KeyHolder("", vasTerminalDetails.getSessionKey(), vasTerminalDetails.getPinKey());


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


			TransactionData transactionData = new TransactionData(inputData, emvCard, configData, keyHolder);
			Log.d("okh", inputData.getAmount() + emvCard.getCardHolderName() + configData);
			Log.d("okh",  transactionData.getKeyHolder().getMasterKey() + ""+connectionData.getTerminalID()+"");
			VasCommunicator  varsr = new VasCommunicator(mContext,transactionData,connectionData, keyHolder);
			//hostInteractor.getTransactionResult(Host.TransactionType.PURCHASE, connData, transactionData).subscribeOn(Schedulers.io())
			//doVAs.execute(varsr);


			//I removed this supposed to be what makes the call
			varsr.processOnlineTransaction();
			return null;
		}

		protected void onPostExecute(TransactionResult transactionResult) {
//			Log.d("okh", transactionResult.cardHolderName);
//			Log.d("okh", transactionResult.terminalID);
//			Log.d("okh", transactionResult.transactionStatus);

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

	private void getEMVCardInfo()
	{
		byte[] tagData = new byte[100];
		int tagDataLength = 0;

		byte[] iccData = new byte[1200];
		int offset = 0;
		if(appState.getProcessState() == PROCESS_CONFIMATION)
		{
			//offset = emv_get_tag_list_data(confirmTagList, confirmTagList.length, iccData, iccData.length);
		}
		else{
			offset = emv_get_tag_list_data(nibsTag, nibsTag.length, iccData, iccData.length);
			Log.i("emv tag list", ByteUtil.arrayToHexStr(iccData, offset));
		}
		appState.trans.setICCData(iccData, 0, offset);
		Log.i("iccdata2", appState.trans.getICCData());

		// Application Label 50
		if( emv_is_tag_present(0x50) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x50, tagData, tagData.length);
			byte[] appLabel = new byte[tagDataLength];
			System.arraycopy(tagData, 0, appLabel, 0, appLabel.length);
			appState.trans.setAppLabel(StringUtil.toString(appLabel));
		}

		// AIP
		if( emv_is_tag_present(0x82) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x82, tagData, tagData.length);
			appState.trans.setAIP(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}


		//Card holder name
		if(emv_is_tag_present(0x5F20) >= 0){
			tagDataLength = emv_get_tag_data(0x5F20,tagData, tagData.length);
			appState.trans.setCardHolderName(StringUtil.convertHexToString(StringUtil.toHexString(tagData, 0, tagDataLength, false)));
		}

		// TVR
		if( emv_is_tag_present(0x95) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x95, tagData, tagData.length);
			appState.trans.setTVR(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}

		// TSI
		if( emv_is_tag_present(0x9B) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9B, tagData, tagData.length);
			appState.trans.setTSI(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}


		// Application Identifier terminal
		if( emv_is_tag_present(0x9F06) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F06, tagData, tagData.length);
			appState.trans.setAID(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}

		// IAD
		if( emv_is_tag_present(0x9F10) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F10, tagData, tagData.length);
			appState.trans.setIAD(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}

		// ApplicationPreferredName  9F12
		if( emv_is_tag_present(0x9F12) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F12, tagData, tagData.length);
			byte[] appName = new byte[tagDataLength];
			System.arraycopy(tagData, 0, appName, 0, appName.length);
			appState.trans.setAppName(StringUtil.toString(appName));
		}

		if(emv_is_tag_present(0x9F26) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F26, tagData, tagData.length);
			appState.trans.setAC(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}

		if(emv_is_tag_present(0x9F37) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F37, tagData, tagData.length);
			appState.trans.setUnpredictableNumber(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}

		if(   emv_is_tag_present(0x9F79) >= 0
				&& appState.trans.getECBalance() < 0
				)
		{
			tagDataLength = emv_get_tag_data(0x9F79, tagData, tagData.length);
			byte[] amt = new byte[tagDataLength];
			System.arraycopy(tagData, 0, amt, 0, amt.length);
			appState.trans.setECBalance(ByteUtil.bcdToInt(amt));
		}
	}
	
	private void processResult()
	{

		if(appState.balanceEnc){
			appState.balanceEnc = false;
		}
		resetPurchase();
		if(transactionType == Host.TransactionType.REFUND){
			TransactionResultService transactionResultService = new TransactionResultService(appState.db, this);
			transactionResultService.delete(appState.currEod);
		}
		if(debug)Log.d(APP_TAG, "processResult");
		switch(appState.getProcessState())
		{

//			Log.d(APP_TAG, String.val(appState.getProcessState()));
		case PROCESS_NORMAL:
			if (    appState.trans.getResponseCode() != null
				 &&	appState.trans.getResponseCode()[0] == '0'
				 && appState.trans.getResponseCode()[1] == '0'
			   ) 
			{
    			if(appState.trans.getEMVOnlineFlag() == true)
    			{
					appState.trans.setEMVOnlineResult(ONLINE_SUCCESS);
					byte[] issuerData = appState.trans.getIssuerAuthData();
					if(debug)Log.d(APP_TAG, "ProcessOnlineActivity  success");

					if(issuerData != null && issuerData.length > 0)
    				{
    					emv_set_online_result(appState.trans.getEMVOnlineResult(), appState.trans.getResponseCode(), issuerData, issuerData.length);
    				}
    				else
    				{
    					emv_set_online_result(appState.trans.getEMVOnlineResult(), appState.trans.getResponseCode(), new byte[]{' '}, 0);
    				}
    			}
				break;
			}
			else if(   appState.trans.getResponseCode() != null
				    && appState.trans.getResponseCode()[0] == 'F'
				    && appState.trans.getResponseCode()[1] == 'F'
			       )
			{

				if(debug)Log.d(APP_TAG, "ProcessOnlineActivity  Fail");

				appState.trans.setEMVOnlineResult(ONLINE_FAIL);
				emv_set_online_result(appState.trans.getEMVOnlineResult(), appState.trans.getResponseCode(), new byte[]{' '}, 0);
			}
			else{
				appState.trans.setEMVOnlineResult(ONLINE_DENIAL);
				if(debug)Log.d(APP_TAG, "ProcessOnlineActivity  DENIAL");

				byte[] issuerData = appState.trans.getIssuerAuthData();
				if(issuerData != null && issuerData.length > 0)
				{
					emv_set_online_result(appState.trans.getEMVOnlineResult(), appState.trans.getResponseCode(), issuerData, issuerData.length);
				}
				else
				{
					emv_set_online_result(appState.trans.getEMVOnlineResult(), appState.trans.getResponseCode(), new byte[]{' '}, 0);
				}
			}
			break;
		case PROCESS_ADVICE_OFFLINE:
			break;
		}
		if(debug)Log.d(APP_TAG, "ProcessOnlineActivity finish success");
		setResult(Activity.RESULT_OK, getIntent());
        Log.i("iccdata", appState.trans.getICCData());
		Log.i("iccdata", appState.trans.getTrack2Data());
		if(appState.trans.getPinBlock() != null){
			Log.i("pinblock", StringUtil.toHexString(appState.trans.getPinBlock(),false));
		}
		dismissProgress();
		exit();
	}

    
}
