package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.iisysgroup.poslib.commons.emv.EmvCard;
import com.iisysgroup.poslib.host.Host;
import com.iisysgroup.poslib.host.entities.TransactionResult;
import com.iisysgroup.poslib.utils.AccountType;
import com.iisysgroup.poslib.utils.InputData;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.db.TransactionResultService;
import com.wizarpos.emvsample.transaction.Nibss;
import com.wizarpos.util.ByteUtil;
import com.wizarpos.util.StringUtil;

import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_tag_data;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_tag_list_data;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_is_tag_present;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_online_result;

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
    	// title
		getEMVCardInfo();
		appState.goneOnline = true;
		textTitle = (TextView)findViewById(R.id.tAppTitle);
		setTitle(textTitle);
		buttonBack = (Button)findViewById(R.id.btn_back);
        buttonBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
        
        buttonMore = (Button)findViewById(R.id.btn_more);
        buttonMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));

        if(appState.nibssData == null){
        	//Terminal has not been configured
            Log.i("nibs", "Preping terminal");
            setResult(8984, getIntent());
        }else{
			Nibss nibss = new Nibss(ProcessOnlineActivity.this);

			//Build Pin info
			EmvCard.PinInfo pinInfo;
			if(appState.trans.getPinBlock() != null){
                Log.i("okh", "pinblock :" + appState.trans.getPinBlock());
				pinInfo = new EmvCard.PinInfo(appState.trans.getPinBlock(),null, StringUtil.hexString2bytes(appState.clearPin));
			}
			else{
				pinInfo = null;
			}
			//Build emv carf
			EmvCard emvCard = new EmvCard(appState.trans.getCardHolderName(),appState.trans.getTrack2Data(),
					appState.trans.getICCData(),pinInfo);

			//Build InputData
			InputData inputData = new InputData(appState.trans.getTransAmount().longValue(),
					appState.trans.getOthersAmount().longValue(), AccountType.DEFAULT_UNSPECIFIED);

			//Go online
			if(transactionType == Host.TransactionType.REFUND){
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

			}else if(transactionType == Host.TransactionType.REVERSAL){
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
			else{
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
				appState.trans.setEMVOnlineResult(ONLINE_FAIL);
				emv_set_online_result(appState.trans.getEMVOnlineResult(), appState.trans.getResponseCode(), new byte[]{' '}, 0);
			}
			else{
				appState.trans.setEMVOnlineResult(ONLINE_DENIAL);
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
