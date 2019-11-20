package com.wizarpos.emvsample.activity

import android.app.Activity
import android.arch.lifecycle.LiveDataReactiveStreams
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.iisysgroup.poslib.deviceinterface.DeviceState
import com.iisysgroup.poslib.deviceinterface.EmvCardReader
import com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor
import com.iisysgroup.poslib.host.HostInteractor
import com.iisysgroup.poslib.host.entities.ConfigData
import com.iisysgroup.poslib.host.entities.ConnectionData
import com.iisysgroup.poslib.host.entities.KeyHolder
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.R
import com.wizarpos.util.SharedPreferenceUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.contentView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

abstract class BaseCardPaymentProcessor : AppCompatActivity() {


    //todo this should not be compulsory - users can decide to use default UI
   // abstract fun initializeDefaultUI() : DefaultUIModel


    lateinit var mEmvInteractor : EmvInteractor
    lateinit var mConnectionData: ConnectionData
    lateinit var mHostInteractor: HostInteractor

    lateinit var configData: ConfigData
    lateinit var keyHolder: KeyHolder


    private var mRrn : String = ""

    private val mDb by lazy {
        (application as MainApp).poslibdb
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_process)
        Log.d("read card", "reading")
        // VerifoneDevice.
//        (application as App).db.configDataDao.get()

        doAsync {
            configData = mDb.configDataDao.get()
            keyHolder = mDb.keyHolderDao.get()
        }

        //mUIModel = initializeDefaultUI()
        //setUpDeviceInteractor()
        //setDeviceStatusObserver()

        setUpHostInteractor()
        setUpConnectionData()

    }
//
//    private fun setUpDeviceInteractor() {
//        mEmvInteractor = EmvInteractor.getInstance(wariDevice)
//    }

    private fun setUpHostInteractor() {
        mHostInteractor = (application as MainApp).hostInteractor
    }

    private fun setUpConnectionData() {
        val terminalId = SharedPreferenceUtils.getTerminalId(this)
        val ipAddress = SharedPreferenceUtils.getIpAddress(this)
        val ipPort = SharedPreferenceUtils.getPort(this)
        val isSSL = SharedPreferenceUtils.isSsl(this)

        when {
            terminalId.isNullOrEmpty() -> {
                toast("Enter valid terminal Id")
                return
            }
            ipAddress.isNullOrEmpty() -> toast("Enter valid IP Address")
            ipPort.toString().isNullOrEmpty() -> toast("Enter valid IP Port")
        }

        mConnectionData = ConnectionData(terminalId, ipAddress, ipPort.toInt(), isSSL)

//        VerifoneDevice.setData(100.toLong(), 0.toLong(), Host.TransactionType.PURCHASE,mHostInteractor,mConnectionData, configData, keyHolder);


//        try{
//            VerifoneDevice.service(this)
//        }catch (e : Exception){
//            Log.d("paying", e.toString())
//        }
    }

    fun setTransactionRrn(rrn : String){
        this.mRrn = rrn
    }

//    val db by lazy {
//        Room.databaseBuilder(this, PosLibDatabase::class.java, "poslib.db")
//                .fallbackToDestructiveMigration()
//                .build()
//    }


//    open fun setDeviceStatusObserver(){
//        Log.d("read card", "reading")
//        //uiManager = DefaultUIManager(contentView!!, mUIModel)
//
//        LiveDataReactiveStreams.fromPublisher(mEmvInteractor.observeStatus()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread()))
//                .observe({ lifecycle }){
//                    it?.let{
//                        val intent = Intent()
//                        intent.putExtra("state", it.state)
//
//                        when {
//                            it.state == DeviceState.DECLINED -> {
//                                mHostInteractor.rollBackTransaction().subscribe { transactionResult ->
//                                    Log.d("OkHRollback", transactionResult.toString())
//                                    intent.putExtra("rrn", transactionResult.RRN)
//                                    setResult(Activity.RESULT_OK, intent)
//                                    finish()
//                                }
//
//                            }
//                            it.state == DeviceState.FAILED -> {
//
//                                toast("Failed")
//                                intent.putExtra("rrn", mRrn)
//                                setResult(Activity.RESULT_OK, intent)
//                                finish()
//                            }
//
//                            it.state == DeviceState.AWAITING_ONLINE_RESPONSE -> {
//                                Log.d("OkH", "Awaiting online response")
//                            }
//
//                            it.state == DeviceState.APPROVED -> {
//                                intent.putExtra("rrn", mRrn)
//                                setResult(Activity.RESULT_OK, intent)
//                                finish()
//                            }
//                        }
//
//                        Log.d("UI_STATE", it.state.toString())
//                        //uiManager.setState(it.state)
//                    }
//                }
//
//
//    }

//    override fun finish() {
//        VerifoneDevice.removeService(this);
//        super.finish()
//    }

}