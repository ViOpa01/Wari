package com.wizarpos.emvsample.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.wizarpos.emvsample.MainApp;

import java.util.Timer;
import java.util.TimerTask;

public class CallHomeService extends Service {
    private static long UPDATE_INTERVAL = 1*5*1000;  //default
    MainApp appState;
    private static Timer timer = new Timer();
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        timer = new Timer();
        Log.i("okh", "Service has statrted");
        appState = ((MainApp)getApplicationContext());
        _startService();

    }

    private void _startService()
    {
        try{
            timer.scheduleAtFixedRate(
                    new TimerTask() {

                        public void run() {

                            doServiceWork();

                        }
                    }, 60000,300000);
            Log.i("CallHome okh", "FileScannerService Timer started....");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doServiceWork()
    {
    // appState.StartStopCallHome(true);

    }

    private void _shutdownService()
    {
        if (timer != null) timer.cancel();
        Log.i(getClass().getSimpleName(), "Timer stopped...");
     //   appState.StartStopCallHome(false);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i("okh", "Service was destroy");

        _shutdownService();

        // if (MAIN_ACTIVITY != null)  Log.d(getClass().getSimpleName(), "FileScannerService stopped");
    }
}
