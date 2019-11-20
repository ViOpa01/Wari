package com.wizarpos.emvsample.activity;

import android.app.Activity;

import com.wizarpos.util.StringUtil;

public class CardTxn extends FunctionsAll {
    public CardTxn(Activity activity){
      onCreate(activity);
    }

    public void StartTransaction(byte transactionType, Long amt){
        appState.setTranType(transactionType);
        appState.trans.setTransType(transactionType);
        appState.getCurrentDateTime();
        if(amt == null){
            appState.trans.setTransAmount(100);
        }else{
            appState.trans.setTransAmount(amt.intValue());
        }
        appState.trans.setTransDate(   appState.currentYear
                + StringUtil.fillZero(Integer.toString(appState.currentMonth), 2)
                + StringUtil.fillZero(Integer.toString(appState.currentDay), 2)
        );
        appState.trans.setTransTime(   StringUtil.fillZero(Integer.toString(appState.currentHour), 2)
                + StringUtil.fillZero(Integer.toString(appState.currentMinute), 2)
                + StringUtil.fillZero(Integer.toString(appState.currentSecond), 2)
        );

    }
}
