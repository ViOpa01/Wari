package com.wizarpos.emvsample.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConfigService {
    private SQLiteDatabase db = null;
    private Cursor queryCursor = null;

    public  ConfigService(SQLiteDatabase db)
    {
        this.db = db;
    }

    public void save(String data){
        db.execSQL("insert into config" + " (config) values(?)", new Object[]{data});
    }

    public void update(String data){
        db.execSQL("update config Set config=?",new Object[]{data});
    }

    public String get(){
       queryCursor = db.rawQuery("Select  * from `config`",new String[]{});
       if(queryCursor.moveToFirst()){
           try{
               return queryCursor.getString(queryCursor.getColumnIndex("config"));
           }
           catch (Exception e){
               return "";
           }
       }else{
           return "";
       }

    }

}
