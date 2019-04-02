package com.wizarpos.emvsample.transaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


import com.iisysgroup.poslib.ISO.GTMS.GtmsHost;
import com.iisysgroup.poslib.ISO.common.IsoRefundProcessData;
import com.iisysgroup.poslib.ISO.common.IsoReversalProcessData;
import com.iisysgroup.poslib.ISO.common.IsoReversalTransactionData;
import com.iisysgroup.poslib.commons.emv.EmvCard;

import com.iisysgroup.poslib.host.Host;
import com.iisysgroup.poslib.host.HostInteractor;
import com.iisysgroup.poslib.host.entities.ConfigData;
import com.iisysgroup.poslib.host.entities.ConnectionData;
import com.iisysgroup.poslib.host.entities.KeyHolder;
import com.iisysgroup.poslib.host.entities.TransactionResult;
//import com.iisysgroup.poslib.utils.DeviceHealth;
import com.iisysgroup.poslib.utils.InputData;
import com.iisysgroup.poslib.utils.TransactionData;
import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.db.TransDetailService;
import com.wizarpos.emvsample.db.TransactionResultService;
import com.wizarpos.emvsample.pfmState.PfmStateBuilder;
import com.wizarpos.util.AppUtil;


import java.io.Serializable;
import java.util.Arrays;

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

    public Nibss(Context context){
        this.context = context;
        hostInteractor = HostInteractor.getInstance(new GtmsHost(context));
        mainApp = MainApp.getInstance();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.netWork_pref, Context.MODE_PRIVATE);
        ip = sharedPreferences.getString("ip","196.6.103.73");
        port = sharedPreferences.getString("port", "5043");
        sslStatus = sharedPreferences.getBoolean("ssl", true);
    }



    //Key Keys to
    public  void prepare (String terminalID, final Nibs<NIbbsData> t){
        Log.i("okh", "preping terminal");
        final ConnectionData connectionData = new ConnectionData( terminalID,ip,Integer.parseInt(port),sslStatus);
        Log.d("okh", terminalID+ "" + ip + " " + port + " " + sslStatus);
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
                                           Toast.makeText(context, "Successfully Configured", Toast.LENGTH_SHORT);
                                           Log.i("okh", "Config data ready");
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

    public  void configureTerminal (String terminalID, final Nibs<NIbbsData> t){
        Log.i("okh", "preping terminal");
//        final ConnectionData connectionData = new ConnectionData( terminalID,ip,Integer.parseInt(port),sslStatus);
        final ConnectionData connectionData = new ConnectionData("2033GP23", "196.6.103.73", 5043, true);
        Single<KeyHolder> liveKeyHolder = hostInteractor.getKeyHolder(connectionData);

        Log.d("okh", terminalID+ "" + ip + " " + port + " " + sslStatus);
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
                        e.printStackTrace();
                        t.error(e.getMessage());
                    }

                    @Override
                    public void onSuccess(final KeyHolder keyHolder) {
                        Log.i("okh", "KeyHolder Ready");
//                        Log.i("okh", "Master key "  + keyHolder.getMasterKey());
//                        Log.i("okh", "pin key "  + keyHolder.getPinKey());
//                        Log.i("okh", "session key "  + keyHolder.getSessionKey());
//                        Log.i("okh", "preping to fetch config data");

                          if(mainApp.nibssData != null){
                              mainApp.nibssData.setKeyHolder(keyHolder);
                              t.complete(mainApp.nibssData);
                          }
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
                                        Toast.makeText(context, "Successfully Configured", Toast.LENGTH_SHORT);
                                        Log.i("okh", "Config data ready");
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


    public void goOnline(EmvCard emvCard, final Host.TransactionType transactionType,
                         final InputData inputData, KeyHolder keyHolder, ConfigData configData,
                         ConnectionData connectionData, final Nibs<TransactionResult> resultNibs){
        TransactionData transactionData = new TransactionData(inputData,emvCard, configData, keyHolder);

       try{
           Log.i("ok2", emvCard.getPinInfo().getKey().toString());
       }catch (Exception e){
           Log.i("ok2", "No pin block");
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
  public   interface  Nibs<T>{
         void complete(T res);
        void error(String e);
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
}