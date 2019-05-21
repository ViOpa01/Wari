package com.iisysgroup.androidlite.generators

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
import com.wizarpos.emvsample.R
import java.text.SimpleDateFormat
import java.util.*


//todo check this out to be sure it is not negative

class PfmStateGenerator(val context : Context, val device : Device) {


    enum class CHARGING_STATUS {
        CHARGING, NOTCHARGING, UNKNOWN, FULLYCHARGED
    }

    enum class COMMS_METHOD {
        WIFI, GPRS, OTHERS
    }

    /*fun generateState() : DstvModel.PfmState{
            return DstvModel.PfmState(getSerialNumber(), getCurrentTime(), getBatteryLevel(), getChargingStatus().toString(), getPrinterStatus().toString(),  getTerminalId(), getCommMethod().toString(), getLocation(), getSignalStrength(), getTerminalModelName(), getTerminalManufacturer(), hasBattery().toString(), getSoftwareNumber(), getLastTransactionTime(), getPads())

        }*/


    private fun getSerialNumber() = Build.SERIAL

    private fun getCurrentTime() : String {
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
        return if (isCharging)
            CHARGING_STATUS.CHARGING
        else  CHARGING_STATUS.NOTCHARGING

    }

    //todo work out how getting printer status works
    private fun getPrinterStatus() : PrinterState {
        return device.printerStatus
    }

    private fun getTerminalId() : String = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.key_terminal_id), "")

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
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val networkOperator = telephonyManager.networkOperator

        if (networkOperator != null){
            if  (telephonyManager.phoneType == TelephonyManager.PHONE_TYPE_GSM){
                val location = telephonyManager.cellLocation as GsmCellLocation
                if (location != null) {

                    //val mcc = Integer.parseInt(networkOperator.substring(0, 3))
                    //val mnc = Integer.parseInt(networkOperator.substring(3))
                    return "cid:${location.cid}, lac:${location.cid}, mcc:121, mnc:765, ss\"\""
                }
            }
        }

        return "cid:\"\", lac:\"\", mcc:\"\", mnc:\"\", ss:\"\""
    }

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