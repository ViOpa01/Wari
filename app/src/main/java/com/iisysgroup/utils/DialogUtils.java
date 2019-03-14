package com.iisysgroup.utils;

import android.app.Activity;

import com.iisysgroup.ui.dialog.CommonAlertDialog;


public class DialogUtils {

    public static void getPromptDialog(final Activity mContext, String title, final String msg) {
        mContext.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CommonAlertDialog dialog = new CommonAlertDialog(mContext);
                dialog.setTitle("Prompt");
                dialog.setMessage(msg);
                dialog.setBtnConfirmTitle("OK", null);
                dialog.setBtnCancelTitle("Cancle", null);
                dialog.show();
            }
        });
    }




}
