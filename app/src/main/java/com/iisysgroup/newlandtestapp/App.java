package com.iisysgroup.newlandtestapp;

import android.app.Application;
import android.util.Log;

import com.iisysgroup.device.N900Device;

/**
 * 说 明：TODO()
 * 作 者：林维新
 * 版 本：V1.0
 * 创建时间：2017年09月14日
 * 版权所有：新大陆支付技术有限公司
 */

public class App extends Application {
    protected static App mApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                N900Device.getInstance().connectDevice();
            }
        }).start();
    }
    public static Application getContext() {
        if (mApplication == null)
            Log.e("Application:","App->getContext error！");
        return mApplication;
    }
}
