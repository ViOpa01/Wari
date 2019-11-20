package com.wizarpos.util

import android.content.Context
import android.net.ConnectivityManager
import com.iisysgroup.poslib.host.entities.TransactionResult
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.login.Helper
//import com.wizarpos.emvsample.generators.PfmNotification
import java.math.BigInteger
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*

class ClientReferenceKey {


//        public fun generatePFM(tr : TransactionResult, context: Context) : PfmNotification.PFMDATA {
//            return PfmNotification().generatePFM(tr, context)
//        }


//    companion object {
//        fun generatePFM(tr : TransactionResult, context: Context) : PfmNotification.PFMDATA {
//         return PfmNotification().generatePFM(tr, context)
//        }
//    }

    fun generateReference(context: Context): String {

       /*var json = "{" +
                "    \"sessionKey\": \"${SharedPreferenceUtils.getSessionKey(context)}\"," +
                "    \"timestamp\": \" ${getCurrentTimeStamp()}\"," +
                "    \"randomString\":\"${generateRandomHexToken(16)}\"" +
                "}"*/
        var json = "{'sessionKey': '${SharedPreferenceUtils.getSessionKey(context)}','timestamp': '${getCurrentTimeStamp()}','randomString': '${generateRandomHexToken(16)}"
        return String(org.apache.commons.codec.binary.Base64.encodeBase64(json.toByteArray()))
    }


    fun generateRandomHexToken(byteLength: Int): String {
        val secureRandom = SecureRandom()
        val token = ByteArray(byteLength)
        secureRandom.nextBytes(token)
        return BigInteger(1, token).toString(16) //hex encoding
    }

    fun getCurrentTimeStamp(): String {
        val sdfDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")//dd/MM/yyyy
        val now = Date()
        return sdfDate.format(now)
    }

  companion object {
      fun hasInternetConnectivity(context: Context): Boolean {
          val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

          val status = connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnectedOrConnecting

          if (!status)
              Helper.showInfoDialog(context, null, context.getString(R.string.no_connectivity_prompt))

          return status
      }

  }

}