package com.wizarpos.emvsample.models

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Build
import android.preference.PreferenceManager
import android.telephony.TelephonyManager
import android.telephony.gsm.GsmCellLocation
import com.iisysgroup.poslib.deviceinterface.Device
import com.iisysgroup.poslib.deviceinterface.printer.PrinterState
import java.text.SimpleDateFormat
import java.util.*
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.util.Log
import com.wizarpos.emvsample.R


//todo check this out to be sure it is not negative

class PfmStateGenerator(val context : Context,val terminalId : String ) {

    val REQUEST_ACCESS_COARSE_LOCATION = 234

    enum class CHARGING_STATUS {
        CHARGING, NOTCHARGING, UNKNOWN, FULLYCHARGED
    }

    enum class COMMS_METHOD {
        WIFI, GPRS, OTHERS
    }

    fun generateState() : com.itex.richard.payviceconnect.model.State {
        Log.d("getSerialNumber  >>>",getSerialNumber() )

        Log.d("getCurrentTime  >>>",getCurrentTime() )

        Log.d("getBatteryLevel  >>>",getBatteryLevel() )

        Log.d("getChargingStatus  >>>",getChargingStatus().toString() )

        Log.d("getTerminalId  >>>",terminalId)

        Log.d("getCommMethod  >>>",getCommMethod().toString() )

        Log.d("getLocation  >>>",getLocation())

//        Log.d("getSignalStrength  >>>",getSignalStrength() )

        Log.d("getTerminalModelName  >>>",getTerminalModelName() )


        Log.d("getTerminalManufacturer  >>>",getTerminalManufacturer() )

        Log.d("hasBattery  >>>",hasBattery().toString())

        Log.d("getSoftwareNumber  >>>",getSoftwareNumber() )

//        Log.d("getLastTransactionTime  >>>",getLastTransactionTime() )

//        Log.d("getPads  >>>",getPads() )



        return com.itex.richard.payviceconnect.model.State(getSerialNumber(), getCurrentTime(), getBatteryLevel(), getChargingStatus().toString() , terminalId,getCommMethod().toString(),"", "Location", "Signal strength ", getTerminalModelName(), getTerminalManufacturer(), hasBattery().toString(), getSoftwareNumber(), "last Trans time", "Pads")

    }


    private fun getSerialNumber() = Build.SERIAL

    public fun getCurrentTime() : String {
        val date = Calendar.getInstance().time

        val timePattern = "yyyyMMDDhhmmss"
        val timeFormatter = SimpleDateFormat(timePattern, Locale.ENGLISH)
        return timeFormatter.format(date)
    }

    private fun getBatteryIntent() : Intent {
        val intentfilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)

        return context.registerReceiver(null, intentfilter)
    }

    private fun getBatteryLevel() : String {
        val batteryStatus = getBatteryIntent()
        val batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

        val percentageLevel = batteryLevel/scale


        return percentageLevel.toString()
    }

    private fun getChargingStatus() : CHARGING_STATUS {
        val batteryStatus = getBatteryIntent()
        val batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging = batteryLevel == BatteryManager.BATTERY_STATUS_CHARGING

        if (getBatteryLevel() == "100")
            return CHARGING_STATUS.FULLYCHARGED
        var status =if (isCharging)
            CHARGING_STATUS.CHARGING
        else CHARGING_STATUS.NOTCHARGING


        return status

    }

    //todo work out how getting printer status works
//    private fun getPrinterStatus() : PrinterState {
//        return device.printerStatus
//    }

    //Do well too add this variable "key_terminal_id" to the shared preference you want to store else this method wouldn't work
//    private fun getTerminalId() : String {
//
//        return  PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.key_terminal_id), "")
//    }

    private fun getCommMethod(): COMMS_METHOD {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting){
            return COMMS_METHOD.GPRS
        } else if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting){
            return COMMS_METHOD.WIFI
        }
        return COMMS_METHOD.OTHERS
    }

    private fun getSignalStrength() : String{
        return ""
    }

    @SuppressLint("MissingPermission")
    private fun getLocation(): String{


//        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        val networkOperator = telephonyManager.networkOperator
//        val permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
//
//        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            return "cid:\"\", lac:\"\", mcc:\"\", mnc:\"\", ss:\"\""
////            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_ACCESS_COARSE_LOCATION);
//        } else {
//            //TODO
//            if (networkOperator != null){
//                if  (telephonyManager.phoneType == TelephonyManager.PHONE_TYPE_GSM){
//                    val location = telephonyManager.cellLocation as GsmCellLocation
//                    if (location != null) {
//
//                        //val mcc = Integer.parseInt(networkOperator.substring(0, 3))
//                        //val mnc = Integer.parseInt(networkOperator.substring(3))
//                        return "cid:${location.cid}, lac:${location.cid}, mcc:121, mnc:765, ss\"\""
//                    }
//                }
//            }
//
//        }
//
        return "cid:\"\", lac:\"\", mcc:\"\", mnc:\"\", ss:\"\""
    }


    class locationGenerator(){}

    private fun getTerminalModelName() : String {
        return Build.MODEL
    }

    private fun getTerminalManufacturer() : String {
        return Build.MANUFACTURER
    }

    private fun hasBattery() : Boolean {
        return true
    }

    private fun getSoftwareNumber() : String {
        return "1.0"
    }

    private fun getLastTransactionTime() : String {
        return ""
    }

    private fun getPads(): String {
        return ""
    }


}