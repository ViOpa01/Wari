package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.wizarpos.emvsample.R;
import com.wizarpos.util.StringUtil;

public class Sale extends FuncActivity
{
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
			appState.setErrorCode(R.string.error_settle_first);
	    	showTransResult();
			return;
		}

		if(appState.needCard == true)
		{
		//requestCard(true, true);
			inputAmount();
		}
		else
		{
			if(appState.trans.getCardEntryMode() == SWIPE_ENTRY)
			{
				inputAmount();
			}
			else{
				processEMVCard(PBOC_KERNAL);
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
		if(   requestCode != STATE_TRANS_END
		   && appState.getErrorCode() > 0
		  )
		{
			showTransResult();
			return;
		}
		if(resultCode != Activity.RESULT_OK)
		{
			exitTrans();
			return;
		}
		switch(requestCode)
		{
		case STATE_INPUT_AMOUNT:
            if(appState.needCard)
            {
                requestCard(true,true, true);
                finish();
            }
            else
			    inputPIN();
            finish();
			break;
		case STATE_REQUEST_CARD:
            if(appState.trans.getCardEntryMode() == INSERT_ENTRY)
            {
                processEMVCard(PBOC_KERNAL);
            }
            else if(appState.trans.getCardEntryMode() == CONTACTLESS_ENTRY)
            {
                processEMVCard(QPBOC_KERNAL);
                finish();
            }
            else
            {
                confirmCard();
                finish();
            }
			break;
        case STATE_CONFIRM_CARD:
            inputPIN();

            break;
		case STATE_INPUT_PIN:
			processOnline();
			break;
		case STATE_PROCESS_ONLINE:
			showTransResult();
			break;
		case STATE_PROCESS_EMV_CARD:
			if(!appState.goneOnline){
				processOnline();
			}else{
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