package com.wizarpos.emvsample.transaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.iisysgroup.poslib.ISO.GTMS.GtmsHost;
import com.iisysgroup.poslib.ISO.common.IsoRefundProcessData;
import com.iisysgroup.poslib.ISO.common.IsoReversalProcessData;
import com.iisysgroup.poslib.ISO.common.IsoReversalTransactionData;
import com.iisysgroup.poslib.commons.emv.EmvCard;

import com.iisysgroup.poslib.host.Host;
import com.iisysgroup.poslib.host.HostInteractor;
import com.iisysgroup.poslib.host.dao.PosLibDatabase;
import com.iisysgroup.poslib.host.entities.ConfigData;
import com.iisysgroup.poslib.host.entities.ConnectionData;
import com.iisysgroup.poslib.host.entities.KeyHolder;
import com.iisysgroup.poslib.host.entities.TransactionResult;
//import com.iisysgroup.poslib.utils.DeviceHealth;
import com.iisysgroup.poslib.host.entities.VasTerminalData;
import com.iisysgroup.poslib.utils.InputData;
import com.iisysgroup.poslib.utils.TransactionData;
import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.VasTerminalService;
import com.wizarpos.emvsample.activity.login.Helper;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.db.TransDetailService;
import com.wizarpos.emvsample.db.TransactionResultService;
import com.wizarpos.util.AppUtil;


import java.io.Serializable;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Nibss {

    HostInteractor hostInteractor ;
    Context context;
    MainApp mainApp ;
    String ip;
    String port;
    boolean sslStatus;

    private static final String TAG = "Nibss";

    public Nibss(Context context){
        this.context = context;
        hostInteractor = HostInteractor.getInstance(new GtmsHost(context));
        mainApp = MainApp.getInstance();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.netWork_pref, Context.MODE_PRIVATE);
//        ip = sharedPreferences.getString("ip","196.6.103.73");//
//        port = sharedPreferences.getString("port", "5043");//

        ip = sharedPreferences.getString("ip","196.6.103.73");
        port = sharedPreferences.getString("port", "5043");

//         ip = "197.253.19.78";
//         port = "5001";

        sslStatus = sharedPreferences.getBoolean("ssl", true);
        //        final ConnectionData connectionData = new ConnectionData("2033GP23", "196.6.103.73", 5043, true);

    }

    public static PosLibDatabase poslibdb = MainApp.getInstance().poslibdb;

    //Key Keys to
    public  void prepare (String terminalID, final Nibs<NIbbsData> t){

        getVasKeys();



        Log.i("okh", "preping terminal");
        Log.d(TAG, "prepare() called with: terminalID = [" + terminalID + "]");
        final ConnectionData connectionData = new ConnectionData( terminalID,ip,Integer.parseInt(port),sslStatus);

        Log.d("okh", terminalID+ "  " + ip + "  " + port + " " + sslStatus);
        Log.d(TAG, "ConnectionData() called with: ip  = [" + ip  + "], port = [" + port + "], sslStatus= = [" + sslStatus + "]");
          hostInteractor.getKeyHolder(connectionData)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new SingleObserver<KeyHolder>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }


                      @Override
                      public void onError(Throwable e) {
                          Log.i("okh", "Error on prepare getKeyHolder method " + e.getMessage());
                          Toast.makeText(context, "Could not prep, please retry", Toast.LENGTH_SHORT).show();
                          e.printStackTrace();
                         t.error(e.getMessage());
                      }

                      @Override
                      public void onSuccess(final KeyHolder keyHolder) {
                         // new saveKeyHolder().execute(keyHolder);

                          Log.i("okh", "KeyHolder Ready");
                          Log.i("okh", "Master key "  + keyHolder.getMasterKey());
                          Log.i("okh", "pin key "  + keyHolder.getPinKey());
                          Log.i("okh", "session key "  + keyHolder.getSessionKey());
                          Log.i("okh", "preping to fetch config data");

//                          if(mainApp.nibssData != null){
//                              mainApp.nibssData.setKeyHolder(keyHolder);
//                              t.complete(mainApp.nibssData);
//                          }
//
//                          Log.d("okh", connectionData.getIpAddress() + " " + connectionData.getTerminalID() + " " + connectionData.getIpPort() + " " + connectionData.isSSL());
//
//                          Log.d("okh", keyHolder.getPinKey() + " " + keyHolder.getSessionKey() + " " + keyHolder.getMasterKey());

                           hostInteractor.getConfigData(connectionData,keyHolder)
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new SingleObserver<ConfigData>() {
                                       @Override
                                       public void onSubscribe(Disposable d) {

                                       }

                                       @Override
                                       public void onSuccess(ConfigData configData) {
                                           Toast.makeText(context, "Successfully Configured", Toast.LENGTH_SHORT).show();
                                           Log.i("okh", "Config data ready");
//                                           SecureStorage.store(Helper.TERMINAL, connectionData.getTerminalID());
                                           Log.i("okh", configData.toString());
                                            NIbbsData nIbbsData = new NIbbsData(keyHolder, configData,connectionData);
                                            t.complete(nIbbsData);
                                       }

                                       @Override
                                       public void onError(Throwable e) {
                                           Log.i("okh" , "Error on Nibbs getConfigData callable in prepare method");
                                           e.printStackTrace();
                                           t.error(e.getMessage());
                                       }
                                   });
                      }


                  });

    }

    private static class saveKeyHolder extends AsyncTask<KeyHolder, Integer, Void> {
        protected Void doInBackground(KeyHolder...keyholder) {
            KeyHolder keyHolders = poslibdb.getKeyHolderDao().get();
            poslibdb.getKeyHolderDao().save(keyholder[0]);
            Log.d("OkH", keyholder[0].getMasterKey());
            Log.d("sbd","dfddd");

         //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);

            return null;
        }

        protected void onPostExecute(Long result) {

        }
    }


    private static class saveVasKeyHolder extends AsyncTask<VasTerminalData, Integer, Void> {
        protected Void doInBackground(VasTerminalData...vasTerminalData) {
            poslibdb.getVasTerminalDataDao().save(vasTerminalData[0]);
            Log.d("OkH", "vasmaster "+vasTerminalData[0].getMasterKey());
            Log.d("sbd","dfddd");
            Log.d("OkH", "vasname "+poslibdb.getVasTerminalDataDao().get().getMerchantName());
            Log.d("OkH", "vasterminal "+poslibdb.getVasTerminalDataDao().get().getTid());
            Log.d("OkH", "vasmid "+poslibdb.getVasTerminalDataDao().get().getMid());
            SecureStorage.store(Helper.MID,  poslibdb.getVasTerminalDataDao().get().getMid());
            SecureStorage.store(Helper.VAS_TERMINAL_ID,  poslibdb.getVasTerminalDataDao().get().getTid());
            SecureStorage.store(Helper.VAS_MERCHANT_NAME,  poslibdb.getVasTerminalDataDao().get().getMerchantName());


            //   SharedPreferenceUtils.setIsTerminalPrepped(this, true);

            return null;
        }

        protected void onPostExecute(Long result) {

        }
    }

    //For configuring the terminal
    public  void configureTerminal (final String terminalID, final Nibs<NIbbsData> t){
        Log.i("okh", "preping terminal");

        getVasKeys();

        final ConnectionData connectionData = new ConnectionData(terminalID,ip,Integer.parseInt(port),sslStatus);


//        final ConnectionData connectionData = new ConnectionData("2033GP23", "196.6.103.73", 5043, true);
        Log.d("okh", connectionData.getIpAddress() + connectionData.getTerminalID() + connectionData.getIpPort() + connectionData.isSSL());
        Single<KeyHolder> liveKeyHolder = hostInteractor.getKeyHolder(connectionData);

        Log.d("okh", terminalID+ "" + ip + " " + port + " " + sslStatus);

        Log.d(TAG, "ConnectionData() called with: ip  = [" + ip  + "], port = [" + port + "], sslStatus= = [" + sslStatus + "]");

        liveKeyHolder
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<KeyHolder>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.i("okh", "Error on prepare getKeyHolder method " + e.getMessage());
                        Toast.makeText(context, "Could not prep, please retry", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        t.error(e.getMessage());
                    }

                    @Override
                    public void onSuccess(final KeyHolder keyHolder) {
                        Log.i("okh", "KeyHolder Ready");
                        Log.i("okh", "Master key "  + keyHolder.getMasterKey());
                        Log.i("okh", "pin key "  + keyHolder.getPinKey());
                        Log.i("okh", "session key "  + keyHolder.getSessionKey());
                        Log.i("okh", "preping to fetch config data");

                          if(mainApp.nibssData != null){
                              mainApp.nibssData.setKeyHolder(keyHolder);
                              t.complete(mainApp.nibssData);
                          }

                        SecureStorage.store(Helper.TERMINAL_ENTERED_BY_USER, connectionData.getTerminalID());
                        SecureStorage.store(Helper.BANK_LOGO,"");

//
                          Log.d("okh", connectionData.getIpAddress() + " " + connectionData.getTerminalID() + " " + connectionData.getIpPort() + " " + connectionData.isSSL()+" " +connectionData.getId());
//
                          Log.d("okh", keyHolder.getPinKey() + " " + keyHolder.getSessionKey() + " " + keyHolder.getMasterKey());

                        hostInteractor.getConfigData(connectionData,keyHolder)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new SingleObserver<ConfigData>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onSuccess(ConfigData configData) {
                                        Toast.makeText(context, "Successfully Configured", Toast.LENGTH_SHORT).show();
                                        Log.i("okh", "Config data ready");
                                        SecureStorage.store(Helper.TERMINAL, terminalID);
                                        String ter = SecureStorage.retrieve(Helper.TERMINAL, "");
                                        NIbbsData nIbbsData = new NIbbsData(keyHolder, configData,connectionData);
                                        t.complete(nIbbsData);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.i("okh" , "Error on Nibbs getConfigData callable in prepare method");
                                        e.printStackTrace();
                                        t.error(e.getMessage());
                                    }
                                });
                    }


                });

    }


    //Used to Make purchase
    public void goOnline(EmvCard emvCard, final Host.TransactionType transactionType,
                         final InputData inputData, KeyHolder keyHolder, ConfigData configData,
                         ConnectionData connectionData, final Nibs<TransactionResult> resultNibs){
        TransactionData transactionData = new TransactionData(inputData,emvCard, configData, keyHolder);

       try{
           Log.d("okh", "nibss pinblock "+emvCard.getPinInfo().getKey());
       }catch (Exception e){
           Log.d("okh", "No pin block");
       }

//       Log.d("okh", Arrays.toString(emvCard.getPinInfo().getPinBlock()) + " " + transactionType + " " + inputData.getAccountType() + " " + keyHolder + " " + configData + " " + connectionData + resultNibs);

        hostInteractor.getTransactionResult(transactionType,connectionData,transactionData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<TransactionResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(final TransactionResult transactionResult) {
                            Log.i("okh", transactionResult.toString());

                        TransactionResultService transactionResultService = new TransactionResultService(mainApp.db, context);
                        if(mainApp.trans.getTransactionType().equals("Purchase") || mainApp.trans.getTransactionType().equals("Purchase with Cash Back") ){
                            transactionResultService.save(transactionResult, AppUtil.formatAmount(mainApp.trans.getTransAmount()));
                        }
                        TransDetailService transDetailService = new TransDetailService(mainApp.db,context);
                        transDetailService.save2(mainApp.trans);
                        resultNibs.complete(transactionResult);

                    }

                    @Override
                    public void onError(Throwable e) {
                       resultNibs.error(e.getLocalizedMessage());
                    }
                });



    }

    public void goOnline(EmvCard emvCard, final Host.TransactionType transactionType,
                         final InputData inputData, KeyHolder keyHolder, ConfigData configData,
                         ConnectionData connectionData,IsoRefundProcessData processData, final Nibs<TransactionResult> resultNibs){
        IsoReversalTransactionData transactionData = new IsoReversalTransactionData(inputData,emvCard,configData,keyHolder, processData);
        try{
            Log.i("ok2", emvCard.getPinInfo().getKey().toString());
        }catch (Exception e){
            Log.i("ok2", "No pin block");
        }
        hostInteractor.getTransactionResult(transactionType,connectionData,transactionData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<TransactionResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TransactionResult transactionResult) {
                        Log.i("okh", transactionResult.toString());
                        TransactionResultService transactionResultService = new TransactionResultService(mainApp.db, context);
                        if(mainApp.trans.getTransactionType().equals("Purchase") || mainApp.trans.getTransactionType().equals("Purchase with Cash Back") ){
                            transactionResultService.save(transactionResult, AppUtil.formatAmount(mainApp.trans.getTransAmount()));
                        }                        TransDetailService transDetailService = new TransDetailService(mainApp.db,context);
                        transDetailService.save2(mainApp.trans);
                        resultNibs.complete(transactionResult);

                    }

                    @Override
                    public void onError(Throwable e) {
                        resultNibs.error(e.getLocalizedMessage());
                    }
                });


    }


    public void reverse(EmvCard emvCard, final Host.TransactionType transactionType,
                         final InputData inputData, KeyHolder keyHolder, ConfigData configData,
                         ConnectionData connectionData,IsoReversalProcessData processData, final Nibs<TransactionResult> resultNibs){
        IsoReversalTransactionData transactionData = new IsoReversalTransactionData(inputData,emvCard,configData,keyHolder, processData);
        try{
            Log.i("ok2", emvCard.getPinInfo().getKey().toString());
        }catch (Exception e){
            Log.i("ok2", "No pin block");
        }
        hostInteractor.getTransactionResult(transactionType,connectionData,transactionData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<TransactionResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TransactionResult transactionResult) {
                        Log.i("okh", transactionResult.toString());
                        TransactionResultService transactionResultService = new TransactionResultService(mainApp.db, context);
                        if(mainApp.trans.getTransactionType().equals("Purchase") || mainApp.trans.getTransactionType().equals("Purchase with Cash Back") ){
                            transactionResultService.save(transactionResult, AppUtil.formatAmount(mainApp.trans.getTransAmount()));
                        }                        TransDetailService transDetailService = new TransDetailService(mainApp.db,context);
                        transDetailService.save2(mainApp.trans);
                        resultNibs.complete(transactionResult);

                    }

                    @Override
                    public void onError(Throwable e) {
                        resultNibs.error(e.getLocalizedMessage());
                    }
                });


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
  public  interface  Nibs<T>{
         void complete(T res);
        void error(String e);
  }

    private Single<VasTerminalData> getVasTerminalService()  {
        return VasTerminalService.Factory.INSTANCE.getService().getVasTerminalDetails();
    }

    private void getVasKeys()
    {
        getVasTerminalService()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<VasTerminalData>() {

                               @Override
                               public void onSubscribe(Disposable d) {

                               }

                               @Override
                               public void onSuccess(VasTerminalData vasTerminalData) {
                                   Log.d("okh", "itex vas "+ vasTerminalData.getMerchantName());
                                   Log.d("okh", "itex vas "+ vasTerminalData.getTid());

                                   SecureStorage.store(Helper.VAS_TERMINAL_ID,vasTerminalData.getTid());

                                   SecureStorage.store(Helper.VAS_MERCHANT_NAME,vasTerminalData.getMerchantName());

                                    new saveVasKeyHolder().execute(vasTerminalData);
//                                   launch {
//                                       (application as App).db.vasTerminalDataDao.save(it)
//                                   }
                               }

                               @Override
                               public void onError(Throwable e) {

                               }
                           });

    }



  public static class NIbbsData implements Serializable{
        private KeyHolder k; private ConfigData cd; private ConnectionData conD;
        public NIbbsData(KeyHolder keyHolder, ConfigData configData, ConnectionData connectionData){
            this.k = keyHolder;
            this.cd = configData;
            this.conD = connectionData;
        }

      public KeyHolder getKeyHolder() {
          return k;
      }

      public void setKeyHolder(KeyHolder keys){
            this.k = keys;
          Log.i("okh", "Key saved");
      }

      public ConfigData getConfigData() {
          return cd;
      }

      public ConnectionData getConnectionData() {
          return conD;
      }
  }


    public static class VasData implements Serializable{
        private KeyHolder k; private ConfigData cd; private ConnectionData conD;
        public VasData(KeyHolder keyHolder, ConfigData configData, ConnectionData connectionData){
            this.k = keyHolder;
            this.cd = configData;
            this.conD = connectionData;
        }

        public KeyHolder getKeyHolder() {
            return k;
        }

        public void setKeyHolder(KeyHolder keys){
            this.k = keys;
            Log.i("okh", "Key saved");
        }

        public ConfigData getConfigData() {
            return cd;
        }

        public ConnectionData getConnectionData() {
            return conD;
        }
    }
}
