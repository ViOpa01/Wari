package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wizarpos.emvsample.R;
import com.wizarpos.util.StringUtil;

import static com.wizarpos.emvsample.constant.Constant.CONTACT_EMV_KERNAL;

public class Sale extends FuncActivity
{
	private static final String TAG = "Sale";
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        appState.setTranType(TRAN_GOODS);
		appState.trans.setTransType(TRAN_GOODS);
		appState.getCurrentDateTime();
		appState.trans.setTransDate(   appState.currentYear
                                     + StringUtil.fillZero(Integer.toString(appState.currentMonth), 2)
                                     + StringUtil.fillZero(Integer.toString(appState.currentDay), 2)
                                   );
		appState.trans.setTransTime(   StringUtil.fillZero(Integer.toString(appState.currentHour), 2)
                                     + StringUtil.fillZero(Integer.toString(appState.currentMinute), 2)
                                     + StringUtil.fillZero(Integer.toString(appState.currentSecond), 2)
                                   );
		if (appState.batchInfo.getSettlePending() != 0)
		{

			Log.d(TAG, "onCreate() : appState.batchInfo.getSettlePending() >>>>");

			appState.setErrorCode(R.string.error_settle_first);
	    	showTransResult();
			return;
		}

		if(appState.needCard == true)
		{
		//requestCard(true, true);
			Log.d(TAG, "onCreate() appState.needCard == true >>>>");

			inputAmount();
		}
		else
		{
			if(appState.trans.getCardEntryMode() == SWIPE_ENTRY)
			{
				inputAmount();
				Log.d(TAG, "onCreate() : appState.trans.getCardEntryMode() == SWIPE_ENTRY >>>>");

			}
			else{
				processEMVCard(PBOC_KERNAL);

				Log.d(TAG, "onCreate() : processEMVCard(PBOC_KERNAL) >>>>");

			}
		}
    }
        
	@Override
	public void onStart()
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		Log.d("onActivityResult requestCode >>>>>>", String.valueOf(requestCode));

		if(   requestCode != STATE_TRANS_END  && appState.getErrorCode() > 0 )
		{
			Log.i(">>>> complete  Sale Activity   pinblock1   requestCode != STATE_TRANS_END  && appState.getErrorCode() > 0  "," showTransResult() " );

			showTransResult();
			return;
		}
		if(resultCode != Activity.RESULT_OK)
		{
			Log.i(">>>> complete  Sale Activity   pinblock1   esultCode != Activity.RESULT_OK  "," exitTrans() " );

			exitTrans();
			return;
		}
		switch(requestCode)
		{
		case STATE_INPUT_AMOUNT:
            if(appState.needCard)
            {
				Log.d(" OnActivity result  called  STATE_INPUT_AMOUNT >>>>","requestCard() ");

				requestCard(true,true, true);

			}
            else {
				Log.d(TAG," processEMVCard STATE_REQUEST_CARD PBOC_KERNAL  About to call online key   >>>>");
//ToDo 19

//				inputPIN();

				inputOnlinePIN();

			}
			break;
		case STATE_REQUEST_CARD:
            if(appState.trans.getCardEntryMode() == INSERT_ENTRY)
            {
                Log.d(TAG," processEMVCard STATE_REQUEST_CARD PBOC_KERNAL  >>>>");

                //TODO Original code
//                processEMVCard(PBOC_KERNAL);

				//TODO What I saw from the code sent
				processEMVCard(CONTACT_EMV_KERNAL);

			}
            else if(appState.trans.getCardEntryMode() == CONTACTLESS_ENTRY)
            {
                processEMVCard(QPBOC_KERNAL);
            }
            else
            {
                confirmCard();
            }
			break;
        case STATE_CONFIRM_CARD:
			Log.d(TAG,"processOnline STATE_CONFIRM_CARD inputPIN() >>>>");
//ToDo 20

//			inputPIN();
			inputOnlinePIN();

			break;
			//ToDo 21
//		case STATE_INPUT_PIN:
//			Log.d(TAG,"processOnline STATE_INPUT_PIN processOnline() >>>>");
//
//			processOnline();
//			break;

			case STATE_INPUT_ONLINE_PIN:
				processOnline();
				break;
		case STATE_PROCESS_ONLINE: {

			Log.d(TAG,"processOnline STATE_PROCESS_ONLINE >>>>");
			Log.i(">>>> complete  Sale Activity   pinblock1   STATE_PROCESS_ONLINE  "," showTransResult() " );


			showTransResult();

		}
			break;
		case STATE_PROCESS_EMV_CARD:
			if(!appState.goneOnline){
				Log.d(TAG,"processOnline STATE_PROCESS_EMV_CARD  go online  not  true>>>>");
				processOnline();
			}else{

				Log.d(TAG,"showTransResult STATE_PROCESS_EMV_CARD  >>>>");

				Log.i(">>>> complete  Sale Activity   pinblock1   STATE_PROCESS_EMV_CARD  "," showTransResult() " );

				showTransResult();
			}

			break;
		case STATE_TRANS_END:
		case STATE_REMOVE_CARD:
			exitTrans();
			break;
		}
	}
}