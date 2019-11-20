package com.wizarpos.emvsample.activity.auth;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.iisysgroup.poslib.host.entities.TransactionResult;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.FuncActivity;
import com.wizarpos.emvsample.db.EodModel;
import com.wizarpos.emvsample.db.TransDetailService;
import com.wizarpos.emvsample.db.TransactionResultService;
import com.wizarpos.emvsample.printer.PrinterException;
import com.wizarpos.emvsample.printer.PrinterHelper;
import com.wizarpos.util.AppUtil;

import java.util.List;

public class EodMenu extends FuncActivity {
    TransactionResultService transactionResultService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eod_menu);
        transactionResultService = new TransactionResultService(appState.db,this);
        cancelIdleTimer();
    }

    public void btnPrintEod(View view){
        List<EodModel> transactionResults = transactionResultService.getTodayTransactions();
        //Log.i("okh", "Transactions + " + transactionResults.size());
       if(transactionResults != null){
           if(transactionResults.size() > 0){
               try {
                   PrinterHelper.getInstance().printEod(appState,transactionResults);
               } catch (PrinterException e) {
                   e.printStackTrace();
               }
           }else{
               Toast.makeText(this,"No Transaction for done today",Toast.LENGTH_LONG).show();
           }
       }else{
           Toast.makeText(this,"No Transaction for done today",Toast.LENGTH_LONG).show();
       }
    }

    public void btnPrintLast(View view){
        TransactionResult transactionResult = transactionResultService.getLastTransaction();
        if(transactionResult != null){
            final EodModel eodModel = new EodModel(transactionResult, AppUtil.formatAmount(transactionResult.amount));
            final TransDetailService transDetailService = new TransDetailService(appState.db, this);

            try {
                PrinterHelper.getInstance().latstTraa(appState,1,eodModel,transDetailService.getLastInfo());
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage("Print Merchant copy");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            PrinterHelper.getInstance().latstTraa(appState,0,eodModel,transDetailService.getLastInfo());
                        } catch (PrinterException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alertDialog.show();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"No transaction available", Toast.LENGTH_LONG).show();
        }

    }
    public  void btnClear(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Are you sure you want to clear the  all trasactions?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                transactionResultService.truncate();
                Toast.makeText(EodMenu.this,"All cleared", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();

    }
    public void btnChangePin(View view ){
        startActivity(new Intent(this, NetworkSettings.class));

    }

    public void finishTr(View view){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        requestFuncMenu();
        exit();
    }

    @Override
    protected void onCancel() {
        super.onCancel();
        onBackPressed();
    }
}
