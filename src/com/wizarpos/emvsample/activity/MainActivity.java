package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloudpos.DeviceException;
import com.cloudpos.POSTerminal;
import com.cloudpos.printer.Format;
import com.cloudpos.printer.PrinterDevice;
import com.google.gson.Gson;
import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.parameter.BalanceResponse;
import com.wizarpos.emvsample.printer.PrinterCommand;
import com.wizarpos.emvsample.printer.PrinterException;
import com.wizarpos.jni.PrinterInterface;
import com.wizarpos.util.AppUtil;
import com.wizarpos.util.StringUtil;
import com.wizarpos.util.TransactionModel;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class MainActivity extends Activity {

    private Button btnprintMerchant;
    private PrinterDevice printerDevice;
    private Format format;
    private Format format2;
    private TextView txt;
    private String str;
    private Context context = MainActivity.this;
    private RelativeLayout printLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printerDevice = (PrinterDevice) POSTerminal.getInstance(getApplicationContext()).getDevice(
                "cloudpos.device.printer");
        Intent intent = getIntent();
        final TransactionModel transactionModel = (TransactionModel) intent.getSerializableExtra("transactionModel");
        final String[] copy = {intent.getStringExtra("copy")};
        btnprintMerchant = findViewById(R.id.printMerchant);
        printLayout = findViewById(R.id.printLayout);
        printLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        printImage(transactionModel, copy[0]);
        btnprintMerchant.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                copy[0] = "*** MERCHANT COPY ***";
                printImage(transactionModel, copy[0]);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private Handler handler = new Handler();

    private Runnable myRunnable = new Runnable() {
        public void run() {
//            txt.setText(str);
        }
    };

    public void printImage(TransactionModel transactionModel, String copy){
        try {

//                str = context.getString(R.string.openingPrint) + "\n";
            handler.post(myRunnable);
            printerDevice.open();
            format = new Format();
            format2 = new Format();
//                str += context.getString(R.string.printerOpenSuc) + "\n";
            handler.post(myRunnable);
            if (printerDevice.queryStatus() == printerDevice.STATUS_OUT_OF_PAPER) {
//                    str += context.getString(R.string.queryStatus) + "\n";
                handler.post(myRunnable);
            } else if (printerDevice.queryStatus() == printerDevice.STATUS_PAPER_EXIST) {
//                    str += context.getString(R.string.statusNor) + "\n";
                handler.post(myRunnable);
                // Bitmap bitmap = encode("0123456789abc", 400, 90);

                format.setParameter("align", "center");
                format.setParameter("bold", "true");

                format2.setParameter("align", "left");
                format2.setParameter("bold", "false");

                try {
                    String bankLogoName = transactionModel.getBankLogoName();
                    int resourceId = getResources().getIdentifier(bankLogoName, "drawable",
                            getPackageName());

                    Drawable drawable = getResources().getDrawable(resourceId);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);


                    printerDevice.printBitmap(format, bitmap);
                }catch (Exception e){

                }

                printerDevice.printlnText("\n");
                printerDevice.printText(format, copy);
                printerDevice.printText(format, transactionModel.getTransactionStatus());
                printerDevice.printlnText("\n");
                printerDevice.printText(format, transactionModel.getTransactionStatusReason());
                printerDevice.printlnText("\n");
                printerDevice.printText(format2, "--------------------------------");
                printerDevice.printlnText(format2,  "TID: "+transactionModel.getTerminalID());
                printerDevice.printlnText(format2, "MID: "+transactionModel.getMerchantId());
                printerDevice.printlnText(format2, "TRANSACTION TYPE: "+transactionModel.getTransactionType().toUpperCase());
                printerDevice.printlnText(format2, "CUSTOMER: "+transactionModel.getCardholderName());
                printerDevice.printlnText(format2, "DATE: "+transactionModel.getISODateTime());
                printerDevice.printlnText(format2, "RRN: "+transactionModel.getRrn());

                if (transactionModel.getTransactionType().equalsIgnoreCase("airtime")){
                    printerDevice.printlnText(format2, "PHONE: "+transactionModel.getPhoneNumber());
                }

                if (!transactionModel.getTransactionType().equalsIgnoreCase("transfer") && !transactionModel.getTransactionType().equalsIgnoreCase("airtime")){
                    printerDevice.printlnText(format2, "MERCHANT: "+transactionModel.getMerchantName());
                    printerDevice.printlnText(format2, "PAN: "+transactionModel.getPan());
                    printerDevice.printlnText(format2, "TICKET: "+transactionModel.getTicket());
                    printerDevice.printlnText(format2, "UNRP: "+transactionModel.getUNRP());
                    printerDevice.printlnText(format2, "AC: "+transactionModel.getAC());
                    printerDevice.printlnText(format2, "TVR: "+transactionModel.getTVR());
                    printerDevice.printlnText(format2, "AID: "+transactionModel.getAID());
                    printerDevice.printlnText(format2, "TSI: "+transactionModel.getTSI());
                    printerDevice.printlnText(format2, "CARD TYPE: "+transactionModel.getCardType());
                    printerDevice.printlnText(format2, "AIP: "+transactionModel.getAIP());
                }


                printerDevice.printlnText("\n");
                printerDevice.printlnText(format, "***********************");
                printerDevice.printlnText(format, "NGN" + transactionModel.getAmount());
                printerDevice.printlnText(format, "***********************");
                printerDevice.printlnText("\n");

                printerDevice.printlnText(format, "WARI");
                printerDevice.printlnText(format, "www.iisysgroup.com");
                printerDevice.printlnText(format, "0700-2255-4839");
                printerDevice.close();

            }
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }


}

