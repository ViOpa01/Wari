package com.wizarpos.emvsample.activity;


import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.util.StringUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Settle extends FuncActivity implements Constants
{
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		if(debug)Log.d(APP_TAG, "Settle onCreate");
        super.onCreate(savedInstanceState);
        
		appState.setTranType(TRAN_SETTLE);
		appState.getCurrentDateTime();
		appState.trans.setTransDate(   appState.currentYear
                                     + StringUtil.fillZero(Integer.toString(appState.currentMonth), 2)
                                     + StringUtil.fillZero(Integer.toString(appState.currentDay), 2)
                                   );
		appState.trans.setTransTime(   StringUtil.fillZero(Integer.toString(appState.currentHour), 2)
                                     + StringUtil.fillZero(Integer.toString(appState.currentMinute), 2)
                                     + StringUtil.fillZero(Integer.toString(appState.currentSecond), 2)
                                   );
		processOnline();
    }
    
	@Override
	public void onStart()
	{
		if(debug)Log.d(APP_TAG, "Settle onStart");
		super.onStart();
	}
	
    @Override
    protected void onStop()
    {
        if(debug)Log.d(APP_TAG, "Settle onStop");
        super.onStop();
    }
    
    @Override
    protected void onPause()
    {
        if(debug)Log.d(APP_TAG, "Settle onPause");
    	super.onPause();
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(debug)Log.d(APP_TAG, " pinblock1  Settle onActivityResult, state[" + requestCode + "]result[" + resultCode + "]");
		if(    requestCode != STATE_TRANS_END
			&& (	appState.getErrorCode() > 0
				|| resultCode != Activity.RESULT_OK
			   )
		  )
		{
			Log.i(">>>> complete  Settle Activity   pinblock1    requestCode != STATE_TRANS_END\n" +
					"\t\t\t&& (\tappState.getErrorCode() > 0\n" +
					"\t\t\t\t|| resultCode != Activity.RESULT_OK  "," showTransResult() " );

			showTransResult();
			return;
		}
		switch(requestCode)
		{
		case STATE_PROCESS_ONLINE:
			appState.trans.setResponseCode(new byte[]{'0','0'});
			appState.transDetailService.clearTable();
			appState.adviceService.clearTable();
			appState.batchInfo.initBatch(appState.batchInfo.getBatchNumber() + 1);
			showTransResult();
			break;
		case STATE_TRANS_END:
			appState.initData();
			exit();
			break;
		}
	}
}