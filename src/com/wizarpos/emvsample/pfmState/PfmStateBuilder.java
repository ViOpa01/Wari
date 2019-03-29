package com.wizarpos.emvsample.pfmState;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

//import com.iisysgroup.poslib.utils.DeviceHealth;
import com.wizarpos.emvsample.MainApp;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PfmStateBuilder {
    public static   String getConectionTypeAndNAme(Context context){
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

//mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(0).getState();

//wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(1).getState();
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
            return "Mobile " + getNetworkClass(context)+" " + getNetworkName(context);
        } else if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
            return "Wifi";
        }
        return "Unknown";
    }


//    public  static DeviceHealth.pfmState getState(Context context, String serial){
//        //Current time
//        MainApp mainApp = MainApp.getInstance();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//
//        //Battery level
//        BatteryManager bm = (BatteryManager)context.getSystemService(Context.BATTERY_SERVICE);
//        int batLevel = 0;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//             batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
//        }
//
//
//
//
//        DeviceHealth.pfmState state = new DeviceHealth.pfmState(serial, formatter.format(date), batLevel,
//                "0","0",mainApp.nibssData.getConnectionData().getTerminalID(),getConectionTypeAndNAme(context),"0","0",getDeviceName(),getDeviceName(),"0","0","0","0",getConectionTypeAndNAme(context),"0");
//
//
//        return  state;
//    }

    private static String getNetworkName(Context context){
        TelephonyManager manager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String carrierName = manager.getNetworkOperatorName();
        return carrierName;
    }
    private static String getNetworkClass(Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G";
            default:
                return "Unknown";
        }
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }


}
