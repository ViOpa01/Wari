package com.wizarpos.emvsample.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.iisysgroup.poslib.host.entities.TransactionResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionResultService {
    private SQLiteDatabase db = null;
    private Cursor queryCursor = null;

    public  TransactionResultService(SQLiteDatabase db , Context context){
        if(!db.isOpen()){
            DatabaseOpenHelper dbOpenHelper = new DatabaseOpenHelper(context);
            this.db = dbOpenHelper.getWritableDatabase();
        }else{
            this.db = db;
        }
    }

    public void save(TransactionResult transactionResult, String amount){
        Log.i("db", "Saving transaction");
        db.execSQL("insert into " + DatabaseOpenHelper.TABLE_TRANS_RESULT + "(amount, status, rrn, result) " +
        "values(?,?,?,?)",
                new Object[]{
                amount,transactionResult.transactionStatus,transactionResult.RRN,
                        new Gson().toJson(transactionResult, TransactionResult.class)
                });
        Log.i("db", "transaction saved");
    }

    public  void delete(EodModel eodModel){
      int idr  = eodModel.getId();
      db.delete(DatabaseOpenHelper.TABLE_TRANS_RESULT,  "_id=?", new String[]{idr + ""});
    }

    public void truncate(){
        db.execSQL("delete from "+ DatabaseOpenHelper.TABLE_TRANS_RESULT);
    }

    public List<EodModel> getTodayTransactions(){
        int i = 0;
        Log.i("db", "geting transaction");
        queryCursor = db.rawQuery("select * from "
                + DatabaseOpenHelper.TABLE_TRANS_RESULT+ " where time >= datetime('now', '-1 days')", null);
        Log.i("db", queryCursor.getCount() + "");
        ArrayList<EodModel> results = new ArrayList<>(queryCursor.getCount());

        if(queryCursor.getCount() > 0){
            queryCursor.moveToFirst();
            String transres = queryCursor.getString(queryCursor.getColumnIndex("result"));
            String amt = queryCursor.getString(queryCursor.getColumnIndex("amount"));
            Log.i("db", queryCursor.getString(queryCursor.getColumnIndex("time")));
            results.add(new EodModel(new Gson().fromJson(transres,TransactionResult.class), amt));
            while (queryCursor.moveToNext()){
                Log.i("db", queryCursor.getString(queryCursor.getColumnIndex("time")));
                String amtT = queryCursor.getString(queryCursor.getColumnIndex("amount"));
                String transresT = queryCursor.getString(queryCursor.getColumnIndex("result"));
                results.add(new EodModel(new Gson().fromJson(transresT,TransactionResult.class),amtT));
            }
        }
        Log.i("db", "transaction gotten");
        if(results.size() >= 1){
            return results;
        }else{
            return null;
        }
    }

    public ArrayList<EodModel> getALllTransactions(){
        int i = 0;
        Log.i("db", "geting transaction");
        queryCursor = db.rawQuery("select * from "
                + DatabaseOpenHelper.TABLE_TRANS_RESULT, null);
        Log.i("db", queryCursor.getCount() + "");
        ArrayList<EodModel> results = new ArrayList<>(queryCursor.getCount());

        if(queryCursor.getCount() > 0){
            queryCursor.moveToFirst();
            String transres = queryCursor.getString(queryCursor.getColumnIndex("result"));
            String amt = queryCursor.getString(queryCursor.getColumnIndex("amount"));
            int curId = queryCursor.getInt(queryCursor.getColumnIndex("_id"));
            Log.i("okh", curId + "");
            EodModel eodModel3 = new EodModel(new Gson().fromJson(transres,TransactionResult.class), amt);
            eodModel3.setId(curId);
            results.add(eodModel3);

            while (queryCursor.moveToNext()){
                Log.i("db", queryCursor.getString(queryCursor.getColumnIndex("time")));
                String amtT = queryCursor.getString(queryCursor.getColumnIndex("amount"));
                String transresT = queryCursor.getString(queryCursor.getColumnIndex("result"));
                int curId1 = queryCursor.getInt(queryCursor.getColumnIndex("_id"));
                Log.i("okh", curId1 + "");
                EodModel eodModel1= new EodModel(new Gson().fromJson(transres,TransactionResult.class), amt);
                eodModel3.setId(curId1);
                results.add(eodModel1);
            }
        }
        Log.i("db", "transaction gotten");
        if(results.size() >= 1){
            return results;
        }else{
            return null;
        }
    }


    public List<TransactionResult> getTrasactions(String startdate, String endDate){
        int i = 0;
        Log.i("db", "geting transaction");
        queryCursor = db.rawQuery("select * from "
                + DatabaseOpenHelper.TABLE_TRANS_RESULT+ " where time >= datetime('"+startdate+"') and time <= datetime('"+endDate+"')", null);
        Log.i("db", queryCursor.getCount() + "");
        ArrayList<TransactionResult> results = new ArrayList<>(queryCursor.getCount());

        if(queryCursor.getCount() > 0){
            queryCursor.moveToFirst();
            String transres = queryCursor.getString(queryCursor.getColumnIndex("result"));
            Log.i("db", queryCursor.getString(queryCursor.getColumnIndex("time")));
            results.add(new Gson().fromJson(transres,TransactionResult.class));
            while (queryCursor.moveToNext()){
                Log.i("db", queryCursor.getString(queryCursor.getColumnIndex("time")));
                String transresT = queryCursor.getString(queryCursor.getColumnIndex("result"));
                results.add(new Gson().fromJson(transresT,TransactionResult.class));
            }
        }
        Log.i("db", "transaction gotten");
        return results;
    }

    public TransactionResult getLastTransaction(){
        queryCursor = db.rawQuery("select * from "
                + DatabaseOpenHelper.TABLE_TRANS_RESULT, null);
        if(queryCursor.moveToLast()){
            String transresT = queryCursor.getString(queryCursor.getColumnIndex("result"));
            return new Gson().fromJson(transresT,TransactionResult.class);
        }
        else{
            return null;
        }
    }

}
