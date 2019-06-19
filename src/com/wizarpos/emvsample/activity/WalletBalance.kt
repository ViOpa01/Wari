package com.wizarpos.emvsample.activity

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.models.BalanceModel
import com.wizarpos.util.SharedPreferenceUtils
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_wallet_balance.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import org.jetbrains.anko.indeterminateProgressDialog
import okhttp3.RequestBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast


class WalletBalance : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_balance)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(isInternetAvailable()){
            toast("Getting Balance").duration = Toast.LENGTH_LONG
            try {
                Single.fromCallable {
                    getBalance(SharedPreferenceUtils.getPayviceUsername(this@WalletBalance), SecureStorage.retrieve(Helper.PLAIN_PASSWORD, ""))
                }.subscribeOn(Schedulers.io()).subscribe(Consumer {
                    //Do Nothing
                }, Consumer {
                    alert {
                        title = "Error"
                        message = "Error occured. Try Again"
                        positiveButton(buttonText = "Ok", onClicked = {
                            this@WalletBalance.finish()
                        })
                    }.show()
                })

            }catch (e : Exception){

            }


        }else{
            toast("No internet connection")
            finish()

        }

    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.title = "Wallet Balance"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun isInternetAvailable(): Boolean{
        val cm = this@WalletBalance.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activityNetworkInfo : NetworkInfo? = null
        activityNetworkInfo = cm.activeNetworkInfo
        return activityNetworkInfo != null &&activityNetworkInfo.isConnected
    }

    fun getBalance(email : String, password : String) {
        val client = OkHttpClient()

        val mediaType = MediaType.parse("application/json")
        val body = RequestBody.create(mediaType, "{\"username\": \""+email+"\",\"password\": \""+password+"\"}")
        val request = Request.Builder()
                .url("https://www.payvice.com/api/account")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build()

        val response = client.newCall(request).execute()
        if(response.isSuccessful){
         //   progress.dismiss()
            val balanceModel = Gson().fromJson(response.body()!!.string(), BalanceModel::class.java)
            GlobalScope.launch(Dispatchers.Main) {
                if(balanceModel.error){
                    alert {
                        title = "Error"
                        message = balanceModel.message
                        positiveButton(buttonText = "Close", onClicked = {
                            this@WalletBalance.finish()
                        })
                    }.show()
                }
                else{
                    Log.i("okh", balanceModel.toString())
                    Log.i("okh", balanceModel.balance.toString() + " " + balanceModel.commissionBalance.toString())
                    balance.text = "NGN " + balanceModel.balance.toString()
                    //commision.text = "NGN " + balanceModel.commissionBalance.toString()
                    emailTxt.text = balanceModel.email
                    name.text = balanceModel.name
                    walletId.text = balanceModel.walletID

                }
            }
        }else{
          //  progress.dismiss()
            GlobalScope.launch(Dispatchers.Main) {
                alert {
                    title = "Error"
                    message = "Unknown error occured." + response.code()
                    okButton {
                        this@WalletBalance.finish()
                    }
                }.show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }
}
