package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.wizarpos.emvsample.activity.login.Helper;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.parameter.BalanceResponse;
import com.wizarpos.emvsample.printer.PrinterCommand;
import com.wizarpos.emvsample.printer.PrinterException;
import com.wizarpos.emvsample.services.helper.activity.util.Models;
import com.wizarpos.jni.PrinterInterface;
import com.wizarpos.util.AppUtil;
import com.wizarpos.util.StringUtil;
import com.wizarpos.util.TransactionModel;
import com.wizarpos.util.VasServices;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static com.wizarpos.emvsample.activity.FuncActivity.appState;

public class MainActivity extends Activity {

    private Button btnprintMerchant;
    private PrinterDevice printerDevice;
    private Format format;
    private Format format2;
    private TextView txt;
    private String str;
    private String walletID;
    private Context context = MainActivity.this;
    private RelativeLayout printLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        walletID = SecureStorage.retrieve(Helper.TERMINAL_ID, "");
        printerDevice = (PrinterDevice) POSTerminal.getInstance(getApplicationContext()).getDevice(
                "cloudpos.device.printer");
        Intent intent = getIntent();
        final TransactionModel transactionModel = (TransactionModel) intent.getSerializableExtra("transactionModel");
        final String[] copy = {intent.getStringExtra("copy")};
        btnprintMerchant = findViewById(R.id.printMerchant);
        printLayout = findViewById(R.id.printLayout);
        printLayout.setOnClickListener(
                new OnClickListener() {
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
                format2.setParameter("bold", "true");
                boolean isElectricityPurchase =false;
                boolean isPrepaidPurchase =false;
                boolean isPostpaidPurchase =false;
                boolean isCableTV =false;
                boolean vastransactionFailed =false;
                boolean didVasTransaction =false;



                try {


                    String bankLogoName = "";
                    int resourceId  = R.drawable.wari_small;;
                    if(transactionModel.getVasDetails().getLogo() != 0) {
                        resourceId = transactionModel.getVasDetails().getLogo();
                        }


                        Log.d("Yeah >>>", String.valueOf(resourceId));
                        Drawable drawable = getResources().getDrawable(resourceId);
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                    printerDevice.printBitmap(format, bitmap);
                }catch (Exception e){

                }

                printerDevice.printlnText("\n");



//               if(transactionModel.getVasDetails() != null ) {


                                       printerDevice.printlnText(format2,  SecureStorage.retrieve(Helper.VAS_MERCHANT_NAME,""));

                printerDevice.printlnText(format2,  SecureStorage.retrieve(Helper.MID,"") + "     " + SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,""));
//               }else{
//
//                   printerDevice.printlnText(format2, transactionModel.getCardDetails().getMerchantName());transactionModel.getCardDetails().getTerminalID()
//
//                   printerDevice.printlnText(format2, transactionModel.getCardDetails().getMerchantId());
//
//                   printerDevice.printlnText(format2, transactionModel.getCardDetails().getMerchantId() + "     " + transactionModel.getCardDetails().getTerminalID());
//               }


                printerDevice.printText(format2, "--------------------------------");
                printerDevice.printlnText("\n");
                if (transactionModel.getVasDetails().getProduct().toUpperCase().isEmpty() || transactionModel.getVasDetails().getVasType().toUpperCase() ==null) {
                     //You can use the logo to determine what shows up here
                    //Better still include it in your VasModel object and genrel electric variable
                    if(appState.isTransfer)
                         printerDevice.printlnText(format, "Transfer".toUpperCase());

                     else
                    printerDevice.printlnText(format, "Purchase".toUpperCase());

                }
                else
                    {
                        printerDevice.printlnText(format, transactionModel.getVasDetails().getProduct().toUpperCase());
                    }
                printerDevice.printlnText(format, copy);
               if (!transactionModel.getVasDetails().getError()){
                   appState.vasTransactionstatus= "APPROVED";
               }
                printerDevice.printlnText(format, appState.vasTransactionstatus);


//                if (transactionModel.getVasDetails().getError() == true){
//
//                }else {
//
//                }


//                if(transactionModel.getVasDetails() != null){
//                    if (transactionModel.getVasDetails().getError() ) {
//                        printerDevice.printText(format, transactionModel.getVasDetails().getTransactionStatusMessage());
//                    }else {
//                        printerDevice.printText(format, transactionModel.getCardDetails().getTransactionStatus());
//                    }
//                }
                printerDevice.printlnText("\n");

//                if(transactionModel.getVasDetails() != null ) {
                    printerDevice.printlnText(format2, "TID:                " + SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,""));
                    printerDevice.printlnText(format2, "TNX TID :           " +  SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,""));
                    printerDevice.printlnText(format2, "REF:" + transactionModel.getVasDetails().getTransactionRef());

//                    printerDevice.printlnText(format2, "WID: " + transactionModel.getVasDetails().getWalletId());
//                }else {


//                }

                    printerDevice.printlnText(format2, "WID:                 " + SecureStorage.retrieve(Helper.TERMINAL_ID,""));



                if(!(transactionModel.getVasDetails().getVasType().isEmpty() )|| !(transactionModel.getVasDetails().getVasType() == null) ) {
                    String vasType = transactionModel.getVasDetails().getVasType();
                    switch (vasType) {


                        case Models.AIRTIME: {

                            Models.AirtimeModel airtimeModel = (Models.AirtimeModel) transactionModel.getVasDetails().getVasTypeModel();

                            if(!airtimeModel.getError()) {
                    printerDevice.printlnText(format2, "RECIPIENT :      " + airtimeModel.getRecepiant_phone());
                    printerDevice.printlnText(format2, "PAYMENT METHOD : " + transactionModel.getVasDetails().getPaymentmethod());
    //              printerDevice.printlnText(format2, "TRANS SEQ : " + transactionModel.getVasDetails());
                    printerDevice.printlnText(format2, "DATE :     " + transactionModel.getVasDetails().getDateTime());
                    //extref or ref
                            }
                        }
                        break;
                        case Models.TRANSFER: {
                            Models.TransferModel transferModel = (Models.TransferModel) transactionModel.getVasDetails().getVasTypeModel();

                            if(!transferModel.getError()) {
                    printerDevice.printlnText(format2, "RECIPIENT :       " + transferModel.getRecepiant());
                    printerDevice.printlnText(format2, "PAYMENT METHOD :  " + transactionModel.getVasDetails().getPaymentmethod());
//                  printerDevice.printlnText(format2, "TRANS SEQ :  " + transactionModel.getVasDetails());
                    printerDevice.printlnText(format2, "DATE :       " + transactionModel.getVasDetails().getDateTime());
                            }
                        }
                        break;
//                        case Models.PURCHASE:{
//                            Models.TRANSFER p = ( Models.DiscosModel) transactionModel.getVasDetails().getVasTypeModel();
//
//                            if(!discosModel.getError()) {
//
//                                printerDevice.printlnText(format2, "PAYMENT METHOD : " + transactionModel.getVasDetails().getPaymentmethod());
//                                printerDevice.printlnText(format2, "NAME : " + discosModel.getRecepiant_name());
////                              printerDevice.printlnText(format2, "TRANS SEQ : " + transactionModel.getVasDetails());
//                                printerDevice.printlnText(format2, "ADDR : " + discosModel.getAddress());
//                                printerDevice.printlnText(format2, "TYPE : " + discosModel.getMeterType());
//                                printerDevice.printlnText(format2, "UNIT : " + discosModel.getUnit());
//                                printerDevice.printlnText(format2, "UNIT VALUE : " + discosModel.getUnit_value());
//                                printerDevice.printlnText(format2, "ARREARS : " + discosModel.getArras());
//                                printerDevice.printlnText(format2, "TARRIF : " +discosModel.getTarrif());
//                                printerDevice.printlnText(format2, "METER NO : " +discosModel.getMeterNumber())
//                                printerDevice.printlnText(format2, "DATE : " + transactionModel.getVasDetails().getDateTime());
//                            }
//                        }
//                        break;
                        case Models.CABLE_TV: {
                            Models.CableTvModel cableTvModel = ( Models.CableTvModel) transactionModel.getVasDetails().getVasTypeModel();

                            if(!cableTvModel.getError()) {

                    printerDevice.printlnText(format2, "PAYMENT METHOD :   " + transactionModel.getVasDetails().getPaymentmethod());
                    printerDevice.printlnText(format2, "IUC :              " + cableTvModel.getIuc());
//
                            }

                        }

                        case Models.DISCO: {
                            Models.DiscosModel discosModel = ( Models.DiscosModel) transactionModel.getVasDetails().getVasTypeModel();

                            if(!discosModel.getError()) {
                   if(transactionModel.getVasDetails().getPaymentmethod() !="")
                    printerDevice.printlnText(format2, "PAYMENT METHOD :    " + transactionModel.getVasDetails().getPaymentmethod());

                   if(discosModel.getRecepiant_name().trim() !="")
                    printerDevice.printlnText(format2, "NAME :    " + discosModel.getRecepiant_name());
//                  printerDevice.printlnText(format2, "TRANS SEQ : " + transactionModel.getVasDetails());

                   if(discosModel.getAddress().trim() !="")
                    printerDevice.printlnText(format2, "ADDR :            " + discosModel.getAddress());

                                if(discosModel.getMeterType().trim() !="")
                    printerDevice.printlnText(format2, "METER TYPE :            " + discosModel.getMeterType());

                                if(discosModel.getUnit().trim() !="")
                    printerDevice.printlnText(format2, "UNIT :            " + discosModel.getUnit());

                                if(discosModel.getUnit_value().trim() !="")
                    printerDevice.printlnText(format2, "UNIT VALUE :      " + discosModel.getUnit_value());

                                if(discosModel.getArras().trim() !="")
                    printerDevice.printlnText(format2, "ARREARS :         " + discosModel.getArras());

                                if(discosModel.getTarrif().trim() !="")
                    printerDevice.printlnText(format2, "TARRIF :          " +discosModel.getTarrif());

                                if(discosModel.getMeterNumber().trim() !="")
                    printerDevice.printlnText(format2, "METER NO :        " +discosModel.getMeterNumber());

                                    if(discosModel.getToken().trim() !="")
                    printerDevice.printlnText(format2, "TOKEN  :          " +discosModel.getToken());


                    printerDevice.printlnText(format2, "DATE :       " + transactionModel.getVasDetails().getDateTime());
                            }
                        }
                        break;
                        default: {

                            vastransactionFailed =false ;



                        }

                    }
                }




                   if(!appState.isWallet) {
                       printerDevice.printlnText(format2, "TRANSACTION TYPE:      " + transactionModel.getCardDetails().getTransactionType().toUpperCase());
                       printerDevice.printlnText(format2, "CARDHOLDER NAME :  " + transactionModel.getCardDetails().getCardholderName());
                       printerDevice.printlnText(format2, "DATE:            " + transactionModel.getCardDetails().getISODateTime());
                       printerDevice.printlnText(format2, "RRN:             " + transactionModel.getCardDetails().getRrn());

//                if (transactionModel.getTransactionType().equalsIgnoreCase("airtime") || transactionModel.getTransactionType().equalsIgnoreCase("data")){
//                    printerDevice.printlnText(format2, "PHONE: "+transactionModel.getPhoneNumber());
//                }


//                if (!transactionModel.getTransactionType().equalsIgnoreCase("transfer") && !transactionModel.getTransactionType().equalsIgnoreCase("airtime") && !transactionModel.getTransactionType().equalsIgnoreCase("data")){
                       printerDevice.printlnText(format2, "MERCHANT:  " + transactionModel.getCardDetails().getMerchantName());
                       printerDevice.printlnText(format2, "PAN:          " + transactionModel.getCardDetails().getPan());
                       printerDevice.printlnText(format2, "TICKET:       " + transactionModel.getCardDetails().getTicket());
//                    printerDevice.printlnText(format2, "UNRP:         "+transactionModel.getCardDetails().getUNRP()); Get stan !!!!!!
//                    printerDevice.printlnText(format2, "AC:           "+transactionModel.getCardDetails().getAC());
                       printerDevice.printlnText(format2, "TVR:          " + transactionModel.getCardDetails().getTVR());
                       printerDevice.printlnText(format2, "AID:          " + transactionModel.getCardDetails().getAID());
//                    printerDevice.printlnText(format2, "TSI:          "+transactionModel.getCardDetails().getTSI());
                       printerDevice.printlnText(format2, "CARD TYPE:    " + transactionModel.getCardDetails().getCardType());
                       printerDevice.printlnText(format2, "EXPIRY :          " + transactionModel.getCardDetails().getExpiry());
//                }
                   }

                printerDevice.printlnText("\n");
                printerDevice.printlnText(format, "***********************");
//                if(transactionModel.getCardDetails() != null ) {
//                    printerDevice.printlnText(format, "NGN " +transactionModel.getVasDetails().getAmount());
//                }else{
                    printerDevice.printlnText(format, "NGN " + transactionModel.getCardDetails().getAmount());
                                   printerDevice.printlnText(format, ("NGN " + transactionModel.getVasDetails().getAmount()).equals("0.00") ? transactionModel.getCardDetails().getAmount(): transactionModel.getVasDetails().getAmount());
//                }
                printerDevice.printlnText(format, "***********************");

                if(transactionModel.getVasDetails().getTransactionStatusMessage().isEmpty() || transactionModel.getVasDetails().getTransactionStatusMessage() == null ) {
                    printerDevice.printlnText(format, transactionModel.getCardDetails().getTransactionStatus());
                                   }else{
                    printerDevice.printlnText(format, transactionModel.getVasDetails().getTransactionStatusMessage());

                }


                printerDevice.printlnText(format, "-----------------------");
                printerDevice.printlnText(format, "WARI");
                printerDevice.printlnText(format, "www.Wari.com");
                printerDevice.printlnText(format, "0700-2255-4839");
                printerDevice.printlnText("\n");

                printerDevice.close();

            }
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }



//    /                                bankLogoName = transactionModel.getBankLogoName();
//                                 resourceId  = getResources().getIdentifier(bankLogoName, "drawable",
//                                        getPackageName());


}

