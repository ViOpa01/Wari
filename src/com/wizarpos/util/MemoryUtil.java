package com.wizarpos.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Package com.wizarpos.util in
 * <p>
 * Project Wari Newland
 * <p>
 * Created by Maxwell on 2019-09-16
 */
public class MemoryUtil {

    private final static String PREFS_KEY = "Wariiz";
    public static final String LastTransactionTimeKey = "LastTransactionTimeKey";


    public static void setValue(Context ctx, String key, Object value){
        SharedPreferences prefs = ctx.getSharedPreferences(MemoryUtil.PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key ,  String.valueOf(value));

        editor.apply();
    }

    public static void deleteValue(Context ctx, String key){
        SharedPreferences prefs = ctx.getSharedPreferences(MemoryUtil.PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.apply();
    }

    public static String getValue(Context ctx, String key){
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_KEY , Context.MODE_PRIVATE);
        return prefs.getString(key , null);
    }

    public static String getValue(Context ctx, String key, Object def){
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS_KEY , Context.MODE_PRIVATE);
        return prefs.getString(key , String.valueOf(def));
    }

}
