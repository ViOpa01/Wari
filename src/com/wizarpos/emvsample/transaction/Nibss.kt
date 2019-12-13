package com.wizarpos.emvsample.transaction

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.util.Base64
import android.util.Log
import android.widget.Toast


import com.google.gson.Gson
import com.iisysgroup.poslib.ISO.GTMS.GtmsHost
import com.iisysgroup.poslib.ISO.POSVAS.PosvasHost
import com.iisysgroup.poslib.ISO.common.IsoRefundProcessData
import com.iisysgroup.poslib.ISO.common.IsoReversalProcessData
import com.iisysgroup.poslib.ISO.common.IsoReversalTransactionData
import com.iisysgroup.poslib.commons.emv.EmvCard

import com.iisysgroup.poslib.host.Host
import com.iisysgroup.poslib.host.HostInteractor
import com.iisysgroup.poslib.host.dao.PosLibDatabase
import com.iisysgroup.poslib.host.entities.ConfigData
import com.iisysgroup.poslib.host.entities.ConnectionData
import com.iisysgroup.poslib.host.entities.KeyHolder
import com.iisysgroup.poslib.host.entities.TransactionResult
//import com.iisysgroup.poslib.utils.DeviceHealth;
import com.iisysgroup.poslib.host.entities.VasTerminalData
import com.iisysgroup.poslib.utils.InputData
import com.iisysgroup.poslib.utils.TransactionData
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.VasTerminalService
import com.wizarpos.emvsample.activity.FuncActivity
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.constant.Constants
import com.wizarpos.emvsample.db.TransDetailService
import com.wizarpos.emvsample.db.TransactionResultService
import com.wizarpos.emvsample.db.detailed.CardTransactionResult
import com.wizarpos.emvsample.db.detailed.EodData
import com.wizarpos.emvsample.db.detailed.EodDoa
import com.wizarpos.emvsample.db.detailed.TransactionDataDoa
import com.wizarpos.util.AppUtil


import java.io.ByteArrayOutputStream
import java.io.Serializable

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope

import com.wizarpos.emvsample.activity.FuncActivity.appState

//Host is set here
class Nibss(internal var context: Context) {

    internal var hostInteractor: HostInteractor
    internal var mainApp: MainApp
    internal var ip: String? = null
    internal var port: String? = null
    internal var sslStatus: Boolean = false

    internal var _clearpinKey: String? = null
    internal var _clearSessionKey: String? = null


    internal var initDb: TransactionDataDoa
    internal var initEodDb: EodDoa


    init {
//        hostInteractor = HostInteractor.getInstance(GtmsHost(context))
                hostInteractor = HostInteractor.getInstance(PosvasHost(context));

        _clearpinKey = SecureStorage.retrieve(Helper.CLEAR_PIN_KEY, "")
        _clearSessionKey = SecureStorage.retrieve(Helper.CLEAR_SESSION_KEY, "")

        mainApp = MainApp.getInstance()
        val sharedPreferences = context.getSharedPreferences(Constants.netWork_pref, Context.MODE_PRIVATE)
        //        ip = sharedPreferences.getString("ip","196.6.103.73");//
        //        port = sharedPreferences.getString("port", "5043");//

//        //        Raw epms
//        ip = sharedPreferences.getString("ip", "196.6.103.73")
//        port = sharedPreferences.getString("port", "5043")

        //Epms Test
        //        ip = sharedPreferences.getString("ip","197.253.19.78");
        //        port = sharedPreferences.getString("port", "5001");

                //Raw Posvas
                ip = sharedPreferences.getString("ip","196.6.103.18");
                port = sharedPreferences.getString("port", "5014");


        //        196.6.103.18 5014
        //         ip = "197.253.19.78";
        //         port = "5001";

        sslStatus = sharedPreferences.getBoolean("ssl", true)
        //        final ConnectionData connectionData = new ConnectionData("2033GP23", "196.6.103.73", 5043, true);


        poslibdb = MainApp.getInstance().poslibdb
        initDb = MainApp.getInstance().transactionDb
        initEodDb = MainApp.getInstance().eodDb

    }


    //Key Keys to
    //    public  void prepare (String terminalID, final Nibs<NIbbsData> t){
    //
    //        getVasKeys();
    //
    //
    //
    //        Log.i("okh", "preping terminal");
    //        Log.d(TAG, "prepare() called with: terminalID = [" + terminalID + "]");
    //        final ConnectionData connectionData = new ConnectionData( terminalID,ip,Integer.parseInt(port),sslStatus);
    //
    //        Log.d("okh", terminalID+ "  " + ip + "  " + port + " " + sslStatus);
    //        Log.d(TAG, "ConnectionData() called with: ip  = [" + ip  + "], port = [" + port + "], sslStatus= = [" + sslStatus + "]");
    //        hostInteractor.getKeyHolder(connectionData)
    //                .subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .subscribe(new SingleObserver<KeyHolder>() {
    //                    @Override
    //                    public void onSubscribe(Disposable d) {
    //
    //                    }
    //
    //
    //                    @Override
    //                    public void onError(Throwable e) {
    //                        Log.i("okh", "Error on prepare getKeyHolder method " + e.getMessage());
    //                        Toast.makeText(context, "Could not prep, please retry", Toast.LENGTH_SHORT).show();
    //                        e.printStackTrace();
    //                        t.error(e.getMessage());
    //                    }
    //
    //                    @Override
    //                    public void onSuccess(final KeyHolder keyHolder) {
    //                        // new saveKeyHolder().execute(keyHolder);
    //
    //                        Log.i("okh", "KeyHolder Ready");
    //                        Log.i("okh", "Master key "  + keyHolder.getMasterKey());
    //                        Log.i("okh", "pin key "  + keyHolder.getPinKey());
    //                        Log.i("okh", "session key "  + keyHolder.getSessionKey());
    //                        Log.i("okh", "preping to fetch config data");
    //
    ////                          if(mainApp.nibssData != null){
    ////                              mainApp.nibssData.setKeyHolder(keyHolder);
    ////                              t.complete(mainApp.nibssData);
    ////                          }
    ////
    ////                          Log.d("okh", connectionData.getIpAddress() + " " + connectionData.getTerminalID() + " " + connectionData.getIpPort() + " " + connectionData.isSSL());
    ////
    ////                          Log.d("okh", keyHolder.getPinKey() + " " + keyHolder.getSessionKey() + " " + keyHolder.getMasterKey());
    //
    //                        hostInteractor.getConfigData(connectionData,keyHolder)
    //                                .subscribeOn(Schedulers.io())
    //                                .observeOn(AndroidSchedulers.mainThread())
    //                                .subscribe(new SingleObserver<ConfigData>() {
    //                                    @Override
    //                                    public void onSubscribe(Disposable d) {
    //
    //                                    }
    //
    //                                    @Override
    //                                    public void onSuccess(ConfigData configData) {
    //                                        Toast.makeText(context, "Successfully Configured", Toast.LENGTH_SHORT).show();
    //                                        Log.i("okh", "Config data ready");
    ////                                           SecureStorage.store(Helper.TERMINAL, connectionData.getTerminalID());
    //                                        Log.i("okh", configData.toString());
    //                                        NIbbsData nIbbsData = new NIbbsData(keyHolder, configData,connectionData);
    //                                        t.complete(nIbbsData);
    //                                    }
    //
    //                                    @Override
    //                                    public void onError(Throwable e) {
    //                                        Log.i("okh" , "Error on Nibbs getConfigData callable in prepare method");
    //                                        e.printStackTrace();
    //                                        t.error(e.getMessage());
    //                                    }
    //                                });
    //                    }
    //
    //
    //                });
    //
    //    }

    //    private static class saveKeyHolder extends AsyncTask<KeyHolder, Integer, Void> {
    //        protected Void doInBackground(KeyHolder...keyholder) {
    //            KeyHolder keyHolders = poslibdb.getKeyHolderDao().get();
    //            poslibdb.getKeyHolderDao().save(keyholder[0]);
    //            Log.d("OkH", keyholder[0].getMasterKey());
    //            Log.d("sbd","dfddd");
    //
    //            //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);
    //
    //            return null;
    //        }
    //
    //        protected void onPostExecute(Long result) {
    //
    //        }
    //    }

    //    poslibdb= (getA).poslibdb

    private class saveVasKeyHolder : AsyncTask<VasTerminalData, Int, Void>() {
        override fun doInBackground(vararg vasTerminalData: VasTerminalData): Void? {
            Log.d("Before Saving ", Gson().toJson(vasTerminalData[0]))

            SecureStorage.store(Helper.VAS_COMMUNICATOR, vasTerminalData[0])


            SecureStorage.store(Helper.MID, vasTerminalData[0].mid)
            SecureStorage.store(Helper.VAS_TERMINAL_ID, vasTerminalData[0].tid)
            SecureStorage.store(Helper.VAS_MERCHANT_NAME, vasTerminalData[0].merchantName)

            //            if (poslibdb !=null) {


            //            Log.d(" >>> MainApp poslibdb  ",new Gson().toJson(poslibdb));


            poslibdb.vasTerminalDataDao.save(vasTerminalData[0])


            if (vasTerminalData[0] != null) {
                //                    poslibdb.getVasTerminalDataDao().update(vasTerminalData[0]);
                Log.d("After updating  ", Gson().toJson(poslibdb.vasTerminalDataDao.get()))


            }
            //                else {
            //                    poslibdb.getVasTerminalDataDao().save(vasTerminalData[0]);
            ////                Log.d("After Saving ",new Gson().toJson(poslibdb.getVasTerminalDataDao().get()));
            //
            //                }
            //            }
            //            Log.d("finally afer After updating/saving   ",new Gson().toJson(poslibdb.getVasTerminalDataDao().get()));
            Log.d("Vascommunicator  ", Gson().toJson(Gson().fromJson(SecureStorage.retrieve(Helper.VAS_COMMUNICATOR, ""), VasTerminalData::class.java)))


            //            Log.d("OkH", "vasmaster "+vasTerminalData[0].getMasterKey());

            Log.d("sbd", "dfddd")
            //            Log.d("OkH", "vasname "+poslibdb.getVasTerminalDataDao().get().getMerchantName());
            //            Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
            //            Log.d("OkH", "vasmid "+poslibdb.getVasTerminalDataDao().get().getMid()) ;

            //            Log.d("OkH", "vasname "+poslibdb.getVasTerminalDataDao().get().getMerchantName());
            //            Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
            //            Log.d("OkH", "vasmid "+poslibdb.getVasTerminalDataDao().get().getMid());
            //            SecureStorage.store(Helper.MID,  poslibdb.getVasTerminalDataDao().get().getMid());
            //            SecureStorage.store(Helper.VAS_TERMINAL_ID,  poslibdb.getVasTerminalDataDao().get().getTid());
            //            SecureStorage.store(Helper.VAS_MERCHANT_NAME,  poslibdb.getVasTerminalDataDao().get().getMerchantName());


            //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);

            return null
        }

        protected fun onPostExecute(result: Long?) {

        }
    }

    //For configuring the terminal
    fun configureTerminal(terminalID: String, t: Nibs<NIbbsData>) {
        Log.i("okh", "preping terminal")

        getVasKeys(terminalID)

        val connectionData = ConnectionData(terminalID, ip, Integer.parseInt(port), sslStatus)


        //        final ConnectionData connectionData = new ConnectionData("2033GP23", "196.6.103.73", 5043, true);
        Log.d("okh", connectionData.ipAddress + connectionData.terminalID + connectionData.ipPort + connectionData.isSSL)
        val liveKeyHolder = hostInteractor.getKeyHolder(connectionData)

        Log.d("okh", "$terminalID$ip $port $sslStatus")

        Log.d(TAG, "ConnectionData() called with: ip  = [$ip], port = [$port], sslStatus= = [$sslStatus]")

        liveKeyHolder
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<KeyHolder> {
                    override fun onSubscribe(d: Disposable) {

                    }


                    override fun onError(e: Throwable) {
                        Log.i("okh", "Error on prepare getKeyHolder method " + e.message)
                        Toast.makeText(context, "Could not prep, please retry", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                        t.error(e.message!!)
                    }

                    override fun onSuccess(keyHolder: KeyHolder) {
                        Log.i("okh", "KeyHolder Ready")
                        Log.i("okh", "Master key " + keyHolder.masterKey)
                        Log.i("okh", "pin key " + keyHolder.pinKey)
                        Log.i("okh", "session key " + keyHolder.sessionKey)
                        Log.i("okh", "preping to fetch config data")

                        if (mainApp.nibssData != null) {
                            mainApp.nibssData.keyHolder = keyHolder
                            t.complete(mainApp.nibssData)
                        }

                        SecureStorage.store(Helper.TERMINAL_ENTERED_BY_USER, connectionData.terminalID)

                        //You can get logo from you
                        SecureStorage.store(Helper.BANK_LOGO, "")

                        //
                        Log.d("okh", connectionData.ipAddress + " " + connectionData.terminalID + " " + connectionData.ipPort + " " + connectionData.isSSL + " " + connectionData.id)
                        //
                        Log.d("okh", keyHolder.pinKey + " " + keyHolder.sessionKey + " " + keyHolder.masterKey)

                        SecureStorage.store(Helper.BANK_LOGO, "")


                        //                        String merchantName;


                        hostInteractor.getConfigData(connectionData, keyHolder)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : SingleObserver<ConfigData> {
                                    override fun onSubscribe(d: Disposable) {

                                    }

                                    override fun onSuccess(configData: ConfigData) {
                                        Toast.makeText(context, "Successfully Configured", Toast.LENGTH_SHORT).show()
                                        Log.i("okh", "Config data ready")
                                        SecureStorage.store(Helper.TERMINAL, terminalID)
                                        val ter = SecureStorage.retrieve(Helper.TERMINAL, "")
                                        val nIbbsData = NIbbsData(keyHolder, configData, connectionData)
                                        t.complete(nIbbsData)


                                        Thread(Runnable {
                                            //                                                String merchantName =  poslibdb.getConfigDataDao().get().getConfigData("52040").toString();
                                            //
                                            //
                                            //                                                 RequestCreator picassoImage= Picasso.get().load("http://www.merchant.payvice.com/external-assets/logos/" + bankNumber);
                                            //                                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                            //
                                            //
                                            //                                                Bitmap bitmap= ((BitmapDrawable) drawable).getBitmap();
                                            //
                                            //                                                appState.bankLogo = bitmap;
                                            //                                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                            //                                                byte[] b = stream.toByteArray();
                                            //                                                String _sBankLogo = Base64.encodeToString(b, Base64.DEFAULT);
                                            //                                                Log.d("logo string  >>>",_sBankLogo);
                                            //
                                            //                                                Boolean status = SecureStorage.store(Helper.BANK_LOGO,_sBankLogo);
                                            //
                                            //                                                Log.d("logo stored status     >>>",String.valueOf(status));
                                            //
                                            //                                                String val = SecureStorage.retrieve(Helper.BANK_LOGO,"");
                                            //
                                            //
                                            //                                                Log.d("logo retrived   >>>",val);


                                            val merchantName = FuncActivity.appState.nibssData.configData.getConfigData("52040").toString()
                                            val merchantId = FuncActivity.appState.nibssData.configData.getConfigData("03015").toString()

                                            SecureStorage.store(Helper.MERCHANT_MID, merchantId)

                                            SecureStorage.store(Helper.MERCHANT_NAME, merchantName)

                                            Log.d(TAG + "onSuccess: get merchantName >>>>>>> ", merchantName)
                                            Log.d(TAG + "onSuccess: get merchantId >>>>>>> ", merchantId)
                                        }).start()


                                    }

                                    override fun onError(e: Throwable) {
                                        Log.i("okh", "Error on Nibbs getConfigData callable in prepare method")
                                        e.printStackTrace()
                                        t.error(e.message!!)
                                    }
                                })
                    }


                })

    }


    //Used to Make purchase
    fun goOnline(emvCard: EmvCard, transactionType: Host.TransactionType,
                 inputData: InputData, keyHolder: KeyHolder, configData: ConfigData,
                 connectionData: ConnectionData, resultNibs: Nibs<TransactionResult>) {
        val transactionData = TransactionData(inputData, emvCard, configData, keyHolder)

        try {
            Log.d("okh", "nibss pinblock " + emvCard.pinInfo.key)
        } catch (e: Exception) {
            Log.d("okh", "No pin block")
        }

        //       Log.d("okh", Arrays.toString(emvCard.getPinInfo().getPinBlock()) + " " + transactionType + " " + inputData.getAccountType() + " " + keyHolder + " " + configData + " " + connectionData + resultNibs);

        //        hostInteractor.getTransactionResult(transactionType,connectionData,transactionData,appState.clearSessionKey,appState.clearPinKey)


        val vasTerminalDetails = Gson().fromJson(SecureStorage.retrieve(Helper.VAS_COMMUNICATOR, ""), VasTerminalData::class.java)

        hostInteractor.getTransactionResult(transactionType, connectionData, transactionData, _clearSessionKey, _clearpinKey)
                //        hostInteractor.getTransactionResult(transactionType,connectionData,transactionData,vasTerminalDetails.getSessionKey(),vasTerminalDetails.getPinKey())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TransactionResult> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(transactionResult: TransactionResult) {
                        Log.i("okh", transactionResult.toString())

                        val transactionResultService = TransactionResultService(mainApp.db, context)
                        if (mainApp.trans.transactionType == "Purchase" || mainApp.trans.transactionType == "Purchase with Cash Back") {
                            transactionResultService.save(transactionResult, AppUtil.formatAmount(mainApp.trans.transAmount!!.toLong()))


                            val cardTransactionResult = CardTransactionResult(
                                    transactionResult.RRN,
                                    transactionResult.authID,
                                    transactionResult.STAN,
                                    transactionResult.PAN,
                                    transactionResult.transactionStatus,
                                    transactionResult.responseCode,
                                    transactionResult.transactionStatusReason,
                                    transactionResult.accountType,
                                    transactionResult.batchNumber,
                                    transactionResult.merchantID,
                                    transactionResult.isoTransmissionDateTime,
                                    transactionResult.terminalID,
                                    transactionResult.originalForwardingInstitutionCode,
                                    transactionResult.cardHolderName,
                                    transactionResult.cardExpiry,
                                    transactionResult.TSI,
                                    transactionResult.TVR,
                                    transactionResult.AID,
                                    transactionResult.amount,
                                    transactionResult.additionalAmount,
                                    transactionResult.longDateTime, transactionResult.transactionType)

                            //                                    transactionResult.issuerScript71 ,
                            //
                            //                                    transactionResult.issuerScript72


                            SecureStorage.store(Helper.CARD_TRANSACTION_RESULT, cardTransactionResult)



                            Thread(Runnable {
                                val value = initDb.saveTransData(cardTransactionResult).toString()

                                Log.d("val Inserted value", value)

                                //                            runOnUiThread {
                                SecureStorage.store(Helper.ROW_ID, value)

                                //                                setRowValue(value)
                                //                            }

                                val eodData = EodData(transactionRef = transactionResult.RRN, transactionType = Helper.PAYMENT_METHOD_CARD, dateTime = Helper.getTimeInMills(),responseCode =  transactionResult.responseCode,amount =  transactionResult.amount.toString())


                                Log.d("val eodData value", Gson().toJson(eodData))

                                val updatedValue = initEodDb.saveEodData(eodData)!!

                                Log.d("val rowId eodData", updatedValue.toString())

                                //                                       String  row =  value;
                            }).start()


                        }
                        val transDetailService = TransDetailService(mainApp.db, context)
                        transDetailService.save2(mainApp.trans)
                        resultNibs.complete(transactionResult)

                    }

                    override fun onError(e: Throwable) {
                        resultNibs.error(e.localizedMessage)
                    }
                })


    }


    //Used for Vas
    fun vasGoOnline(emvCard: EmvCard, transactionType: Host.TransactionType,
                    inputData: InputData, keyHolder: KeyHolder, configData: ConfigData,
                    connectionData: ConnectionData, resultNibs: Nibs<TransactionResult>) {
        val transactionData = TransactionData(inputData, emvCard, configData, keyHolder)

        try {
            Log.d("okh", "nibss pinblock " + emvCard.pinInfo.key)
        } catch (e: Exception) {
            Log.d("okh", "No pin block")
        }

        //       Log.d("okh", Arrays.toString(emvCard.getPinInfo().getPinBlock()) + " " + transactionType + " " + inputData.getAccountType() + " " + keyHolder + " " + configData + " " + connectionData + resultNibs);

        val vasTerminalDetails = Gson().fromJson(SecureStorage.retrieve(Helper.VAS_COMMUNICATOR, ""), VasTerminalData::class.java)

        hostInteractor.getTransactionResult(transactionType, connectionData, transactionData, vasTerminalDetails.sessionKey, vasTerminalDetails.pinKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TransactionResult> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(transactionResult: TransactionResult) {
                        Log.i("okh", transactionResult.toString())

                        val transactionResultService = TransactionResultService(mainApp.db, context)
                        if (mainApp.trans.transactionType == "Purchase" || mainApp.trans.transactionType == "Purchase with Cash Back") {
                            transactionResultService.save(transactionResult, AppUtil.formatAmount(mainApp.trans.transAmount!!.toLong()))

                            val cardTransactionResult = CardTransactionResult(
                                    transactionResult.RRN,
                                    transactionResult.authID,
                                    transactionResult.STAN,
                                    transactionResult.PAN,
                                    transactionResult.transactionStatus,
                                    transactionResult.responseCode,
                                    transactionResult.transactionStatusReason,
                                    transactionResult.accountType,
                                    transactionResult.batchNumber,
                                    transactionResult.merchantID,
                                    transactionResult.isoTransmissionDateTime,
                                    transactionResult.terminalID,
                                    transactionResult.originalForwardingInstitutionCode,
                                    transactionResult.cardHolderName,
                                    transactionResult.cardExpiry,
                                    transactionResult.TSI,
                                    transactionResult.TVR,
                                    transactionResult.AID,
                                    transactionResult.amount,
                                    transactionResult.additionalAmount,
                                    transactionResult.longDateTime, transactionResult.transactionType)

                            SecureStorage.store(Helper.CARD_TRANSACTION_RESULT, cardTransactionResult)


                            Thread(Runnable {
//                                val value = initDb.saveTransData(cardTransactionResult).toString()
//
//                                Log.d("val Inserted value", value)
//
//                                //                            runOnUiThread {
//                                SecureStorage.store(Helper.ROW_ID, value)

                                val value = initDb.saveTransData(cardTransactionResult).toString()

                                Log.d("val Inserted value", value)

                                //                            runOnUiThread {
                                SecureStorage.store(Helper.ROW_ID, value)

                                //                                setRowValue(value)
                                //                            }

                                val eodData = EodData(transactionRef = transactionResult.RRN, transactionType = Helper.PAYMENT_METHOD_CARD, dateTime = Helper.getTimeInMills(),responseCode =  transactionResult.responseCode,amount =  transactionResult.amount.toString())


                                Log.d("val eodData value", Gson().toJson(eodData))

                                val updatedValue = initEodDb.saveEodData(eodData)!!

                                Log.d("val rowId eodData", updatedValue.toString())
                            }).start()
                            val transDetailService = TransDetailService(mainApp.db, context)
                            transDetailService.save2(mainApp.trans)
                            resultNibs.complete(transactionResult)

                        }

                    }

                    override fun onError(e: Throwable) {
                        resultNibs.error(e.localizedMessage)
                    }
                })


    }

    fun goOnline(emvCard: EmvCard, transactionType: Host.TransactionType,
                 inputData: InputData, keyHolder: KeyHolder, configData: ConfigData,
                 connectionData: ConnectionData, processData: IsoRefundProcessData, resultNibs: Nibs<TransactionResult>) {
        val transactionData = IsoReversalTransactionData(inputData, emvCard, configData, keyHolder, processData)
        try {
            Log.i("ok2", emvCard.pinInfo.key.toString())
        } catch (e: Exception) {
            Log.i("ok2", "No pin block")
        }

        hostInteractor.getTransactionResult(transactionType, connectionData, transactionData, null, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TransactionResult> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(transactionResult: TransactionResult) {
                        Log.i("okh", transactionResult.toString())
                        val transactionResultService = TransactionResultService(mainApp.db, context)
                        if (mainApp.trans.transactionType == "Purchase" || mainApp.trans.transactionType == "Purchase with Cash Back") {
                            transactionResultService.save(transactionResult, AppUtil.formatAmount(mainApp.trans.transAmount!!.toLong()))
                        }
                        val transDetailService = TransDetailService(mainApp.db, context)
                        transDetailService.save2(mainApp.trans)
                        resultNibs.complete(transactionResult)

                    }

                    override fun onError(e: Throwable) {
                        resultNibs.error(e.localizedMessage)
                    }
                })


    }


    fun reverse(emvCard: EmvCard, transactionType: Host.TransactionType,
                inputData: InputData, keyHolder: KeyHolder, configData: ConfigData,
                connectionData: ConnectionData, processData: IsoReversalProcessData, resultNibs: Nibs<TransactionResult>) {
        val transactionData = IsoReversalTransactionData(inputData, emvCard, configData, keyHolder, processData)
        try {
            Log.i("ok2", emvCard.pinInfo.key.toString())
        } catch (e: Exception) {
            Log.i("ok2", "No pin block")
        }

        hostInteractor.getTransactionResult(transactionType, connectionData, transactionData, null, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<TransactionResult> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(transactionResult: TransactionResult) {
                        Log.i("okh", transactionResult.toString())
                        val transactionResultService = TransactionResultService(mainApp.db, context)
                        if (mainApp.trans.transactionType == "Purchase" || mainApp.trans.transactionType == "Purchase with Cash Back") {
                            transactionResultService.save(transactionResult, AppUtil.formatAmount(mainApp.trans.transAmount!!.toLong()))
                        }
                        val transDetailService = TransDetailService(mainApp.db, context)
                        transDetailService.save2(mainApp.trans)
                        resultNibs.complete(transactionResult)

                    }

                    override fun onError(e: Throwable) {
                        resultNibs.error(e.localizedMessage)
                    }
                })


    }

    //    public void callHome(Context context, ConnectionData connectionData, KeyHolder keyHolder, ConfigData configData, final Nibs<Boolean> result) {
    //        try {
    //            hostInteractor.callhome(connectionData,keyHolder, getHelath(context)).subscribeOn(Schedulers.io())
    //                    .observeOn(AndroidSchedulers.mainThread())
    //                    .subscribe(new SingleObserver<Boolean>() {
    //                        @Override
    //                        public void onSubscribe(Disposable d) {
    //
    //                        }
    //
    //                        @Override
    //                        public void onSuccess(Boolean aBoolean) {
    //                            result.complete(aBoolean);
    //                        }
    //
    //                        @Override
    //                        public void onError(Throwable e) {
    //                            e.printStackTrace();
    //                            result.error(e.getMessage());
    //                            Log.i("okh", "Error callin home");
    //                        }
    //                    });
    //
    //        } catch (PackageManager.NameNotFoundException e) {
    //            e.printStackTrace();
    //            Log.i("okh", "Packagename error");
    //            result.error(e.getMessage());
    //        }
    //    }

    //    private DeviceHealth.Health getHelath(Context context) throws PackageManager.NameNotFoundException {
    //        String serialNumber = Settings.Secure.getString(context.getContentResolver(),
    //                Settings.Secure.ANDROID_ID);
    //        PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
    //        String versionName = pInfo.versionName;
    //
    //        String networkName = PfmStateBuilder.getConectionTypeAndNAme(context);
    //
    //        DeviceHealth.pfmState pfmState = PfmStateBuilder.getState(context, serialNumber);
    //
    //        DeviceHealth.Health health = new DeviceHealth.Health(serialNumber,versionName,networkName, pfmState);
    //
    //        return  health;
    //    }


    //Use for call back durring preparation
    interface Nibs<T> {
        fun complete(res: T)
        fun error(e: String)
    }

    private fun getVasTerminalService(tid: String): Single<VasTerminalData> {
        return VasTerminalService.Factory.getService().getVasTerminalDetails(tid)
        //        return VasTerminalService.Factory.INSTANCE.getService().getVasTerminalDetails();
    }

    private fun getVasKeys(tid: String) {
        getVasTerminalService(tid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<VasTerminalData> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(vasTerminalData: VasTerminalData) {
                        Log.d("okh", Helper.VAS_MERCHANT_NAME + " >>>>>>>>>> " + vasTerminalData.merchantName)
                        Log.d("okh", Helper.VAS_TERMINAL_ID + "  >>>>>>>>>>>> " + vasTerminalData.tid)

                        SecureStorage.store(Helper.VAS_TERMINAL_ID, vasTerminalData.tid)

                        SecureStorage.store(Helper.VAS_MERCHANT_NAME, vasTerminalData.merchantName)

                        //                        countryCode currencyCode mcc mid sessionKey pinKey masterKey

                        saveVasKeyHolder().execute(vasTerminalData)
                        //                                   launch {
                        //                                       (application as App).db.vasTerminalDataDao.save(it)
                        //                                   }
                    }

                    override fun onError(e: Throwable) {

                    }
                })

    }


    class NIbbsData(private var k: KeyHolder?, val configData: ConfigData, val connectionData: ConnectionData) : Serializable {

        var keyHolder: KeyHolder?
            get() = k
            set(keys) {
                this.k = keys
                Log.i("okh", "Key saved")
            }
    }


    class VasData(private var k: KeyHolder?, val configData: ConfigData, val connectionData: ConnectionData) : Serializable {

        var keyHolder: KeyHolder?
            get() = k
            set(keys) {
                this.k = keys
                Log.i("okh", "Key saved")
            }
    }

    companion object {
        lateinit var poslibdb: PosLibDatabase

        private val TAG = "Nibss"
    }
}
