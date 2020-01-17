package com.wizarpos.emvsample.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iisysgroup.poslib.ISO.common.IsoRefundProcessData;
import com.iisysgroup.poslib.ISO.common.IsoReversalProcessData;
import com.iisysgroup.poslib.host.Host;
import com.iisysgroup.poslib.host.entities.TransactionResult;
import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.FuncActivity;
import com.wizarpos.emvsample.activity.FuncMenuActivity;
import com.wizarpos.emvsample.activity.RefundActivity;
import com.wizarpos.emvsample.db.EodModel;
import com.wizarpos.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends RecyclerView.Adapter<TransactionHistory.MyViewHolder> {

    List<EodModel> mData;
    Context cont;
    LayoutInflater layoutInflater;
    MainApp mainApp;
    FuncActivity funcActivity;

    public TransactionHistory(ArrayList<EodModel> transactionHistories, Context context, MainApp mainApp1, FuncActivity activity){
        this.cont = context;
        this.mData = transactionHistories;
        layoutInflater = LayoutInflater.from(context);
        this.mainApp = mainApp1;
        this.funcActivity = activity;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_transaction,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TransactionResult transactionResult = mData.get(position).getResults();
        holder.setData(transactionResult,position);
    }

    @Override
    public int getItemCount() {
        if(mData != null){
            return mData.size();
        }
        return 0;
    }

     class MyViewHolder extends RecyclerView.ViewHolder {
        TextView amount, rrn;
        Button refund;
        Button reversal;

        public MyViewHolder(View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            rrn = itemView.findViewById(R.id.rrn);
            refund = itemView.findViewById(R.id.refund);
            reversal = itemView.findViewById(R.id.reversal);
        }

         public void setData(final TransactionResult transactionResult, final int position) {
            final long amt = transactionResult.amount;
            Log.i("ott", "amount is " + amt);
            amount.setText("NGN " + AppUtil.formatAmount(amt));
            rrn.setText(transactionResult.RRN);
            if(transactionResult.transactionType == Host.TransactionType.REFUND || transactionResult.transactionType == Host.TransactionType.REVERSAL ){
                refund.setVisibility(View.GONE);
            }
            refund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainApp.currEod = mData.get(position);
                    getAmount((int) transactionResult.amount,transactionResult, true);
                }
            });

            reversal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainApp.currEod = mData.get(position);
                    mainApp.iisoReverSal = new IsoReversalProcessData(transactionResult.STAN,transactionResult.isoTransmissionDateTime,
                            transactionResult.RRN, IsoReversalProcessData.ReversalReasonCode.CUSTOMER_CANCELLATION,transactionResult.originalForwardingInstitutionCode);
                    mainApp.revarsal = true;
                    mainApp.needCard = true;
                    mainApp.reversalAmout = (int) transactionResult.amount;
                    mainApp.trans.setTransAmount((int) transactionResult.amount);
                    funcActivity.sale();
                }
            });


         }


         private void getAmount(final int amount, final TransactionResult transactionResult, final boolean isRefund){
             AlertDialog.Builder alert = new AlertDialog.Builder(cont, R.style.AlertDialogCustom);
             alert.setTitle("Enter amount");
             final EditText editText = new EditText(cont);
             editText.setHint("Max of " + (amount / 100));
             editText.setTextColor(Color.parseColor("#ffffff"));
             editText.setInputType(InputType.TYPE_CLASS_NUMBER);
             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
             LinearLayout linearLayout = new LinearLayout(cont);
             linearLayout.setLayoutParams(params);
             linearLayout.setPadding(10,10,10,10);
             linearLayout.setOrientation(LinearLayout.VERTICAL);
             linearLayout.addView(editText);
             alert.setView(linearLayout);
             alert.setCancelable(false);


             alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                 }
             });

             alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     String passwordEn = editText.getText().toString();
                     if(passwordEn.isEmpty()){
                         editText.setError("Empty Field");
                     }
                     else{
                         if(Integer.parseInt(passwordEn) <= (amount/100) && Integer.parseInt(passwordEn) > 0){
                             mainApp.isoRefundProcessData = new IsoRefundProcessData(transactionResult.STAN,transactionResult.isoTransmissionDateTime,transactionResult.RRN,transactionResult.amount,transactionResult.originalForwardingInstitutionCode);
                             if(isRefund){
                                 mainApp.refund = true;
                             }else{
                                 mainApp.revarsal = true;
                             }
                             mainApp.needCard = true;
                             mainApp.refundAmount = Integer.parseInt(passwordEn) * 100;
                             mainApp.trans.setTransAmount(mainApp.refundAmount);
                             funcActivity.sale();
                         }
                         else{
                             Toast.makeText(cont,"Invalid amount", Toast.LENGTH_LONG).show();
                             dialog.dismiss();
                         }
                     }
                 }
             });

             alert.show();
         }
     }
}
