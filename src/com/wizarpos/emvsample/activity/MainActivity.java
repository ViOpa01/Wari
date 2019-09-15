package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cloudpos.DeviceException;
import com.cloudpos.POSTerminal;
import com.cloudpos.printer.Format;
import com.cloudpos.printer.PrinterDevice;
import com.google.gson.Gson;
import com.iisysgroup.androidlite.utils.PrintUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.login.Helper;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.models.ReceiptModel;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    private boolean isDonePrinting=false;
    private LinearLayout receipt;
    private TextView merchantName;
    int resourceId;
    private TextView receiptOwner;

    private ImageView bankImage;

    private RequestCreator picassoImage;
    String logo="";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_print);

        receipt= findViewById(R.id.receipt);
        merchantName= findViewById(R.id.merchantName);
        bankImage= findViewById(R.id.bankImage);
        receiptOwner =findViewById(R.id.receiptOwner);
        btnprintMerchant = findViewById(R.id.printMerchant);
//        printLayout = findViewById(R.id.printLayout);

        if(!SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"").equals("")) {
            String bankNumber = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "").substring(0, 4) + ".png";
            Log.d("MainActivity", bankNumber);

            logo=  SecureStorage.retrieve(Helper.BANK_LOGO,"");

            Log.d("Logo >>>>", logo);


            if(logo.equals("")) {
                picassoImage = Picasso.get().load("http://www.merchant.payvice.com/external-assets/logos/" + bankNumber);
            }
        }
        else{

            bankImage.setImageResource(R.drawable.wari_small);

        }





        walletID = SecureStorage.retrieve(Helper.TERMINAL_ID, "");
        printerDevice = (PrinterDevice) POSTerminal.getInstance(getApplicationContext()).getDevice(
                "cloudpos.device.printer");
        Intent intent = getIntent();
        final TransactionModel transactionModel = (TransactionModel) intent.getSerializableExtra("transactionModel");
        final String[] copy = {intent.getStringExtra("copy")};


        receiptOwner.setText(copy[0]);


//        printLayout.setOnClickListener(
//                new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isDonePrinting =true;
//
//                finish();
//            }
//        });


//        title = "Merchant's copy"
//        message = "Press OK to print Merchant's copy"

        printImage(transactionModel, copy[0]);
        btnprintMerchant.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                receiptOwner.setText(copy[0]);
                copy[0] = "*** MERCHANT COPY ***";
                printImage(transactionModel, copy[0]);
                isDonePrinting=true;
                onBackPressed();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private void steUpView (){

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
                    Bitmap bitmap=null;
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    if(transactionModel.getVasDetails().getLogo() != 0) {
                        resourceId = transactionModel.getVasDetails().getLogo();
                        bankImage.setImageResource(resourceId);
                        Log.d("Yeah >>>", String.valueOf(resourceId));
                        Drawable drawable = getResources().getDrawable(resourceId);
                        bitmap= ((BitmapDrawable) drawable).getBitmap();

                    }else {

                        Log.d("logo.equals(\"\") >>>",String.valueOf(logo.equals("")).toString());
                        if(logo.equals("")) {
                            picassoImage.into(bankImage);
//                        bitmap= picassoImage.get();
//                        Log.d("picassoImage.get() ",picassoImage.get().toString());
                            Drawable drawable =null ;
                                   drawable =bankImage.getDrawable();
                            Log.d("logo drawable  >>>",String.valueOf(drawable==null));

                            bitmap= ((BitmapDrawable) drawable).getBitmap();

                            appState.bankLogo = bitmap;
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            byte[] b = stream.toByteArray();
                            String _sBankLogo = Base64.encodeToString(b, Base64.DEFAULT);
                            Log.d("logo string  >>>",_sBankLogo);

                            Boolean status = SecureStorage.store(Helper.BANK_LOGO,_sBankLogo);

                            Log.d("logo stored status     >>>",String.valueOf(status));

                            String val = SecureStorage.retrieve(Helper.BANK_LOGO,"");


                            Log.d("logo retrived   >>>",val);


                        }else{
                            byte[] imageAsBytes = Base64.decode(logo.getBytes(), Base64.DEFAULT);
                            bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
                            bankImage.setImageBitmap(bitmap);


                        }





                    }




                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                    printerDevice.printBitmap(format, bitmap);
                }catch (Exception e){
                    resourceId  = R.drawable.wari_small;
                }
                printerDevice.printlnText("\n");


                LinkedHashMap<String,String> receiptMap = new LinkedHashMap<>();





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

                String transactionType="";
                if (transactionModel.getVasDetails().getProduct().toUpperCase().isEmpty() || transactionModel.getVasDetails().getVasType().toUpperCase() ==null) {
                     //You can use the logo to determine what shows up here
                    //Better still include it in your VasModel object and genrel electric variable
                    if(appState.isTransfer) {
                        transactionType = "Transfer".toUpperCase();

//                        printerDevice.printlnText(format, );

                    }else if (appState.isWithdrawal) {
                        transactionType = "Withdrawal".toUpperCase();
//                        printerDevice.printlnText(format, );

                    }else{

                        transactionType ="Purchase".toUpperCase();

//                        printerDevice.printlnText(format, );


                    }

                }
                else
                    {
                        transactionType=transactionModel.getVasDetails().getProduct().toUpperCase();
//                        printerDevice.printlnText(format,transactionModel.getVasDetails().getProduct().toUpperCase() );
                    }

                printerDevice.printlnText(format, transactionType);



                printerDevice.printlnText(format, copy);

//                if(!appState.cableTv && !appState.airtime && !appState.withdrawal && !appState.electricityBills) {
//                    transactionModel.getCardDetails().getTransactionStatus();
//                }

                if(appState.isVas) {
//            appState.vasTransactionstatus = "DECLINED";

                    if (!transactionModel.getVasDetails().getError()) {
                        appState.vasTransactionstatus = "APPROVED";
                    }else{
                        appState.vasTransactionstatus = "DECLINED";
                    }
                }else {
                    if (appState.trans.isTransactionStatus()) {
                        appState.vasTransactionstatus = "APPROVED";
                    }else{
                        appState.vasTransactionstatus = "DECLINED";
                    }
                }

                printerDevice.printlnText(format, appState.vasTransactionstatus);








                printerDevice.printlnText("\n");

                 String terminalId= SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"");
                 String vasTerminalId = SecureStorage.retrieve(Helper.VAS_TERMINAL_ID,"");
                 String walletId =SecureStorage.retrieve(Helper.TERMINAL_ID,"");
                    printerDevice.printlnText(format2, "TID:                " + terminalId);
                    printerDevice.printlnText(format2, "TXN TID :           " + vasTerminalId);
                    printerDevice.printlnText(format2, "WID:                 " + walletId);

                receiptMap.put("TID",terminalId);
                receiptMap.put("TXN TID",vasTerminalId);
                receiptMap.put("WID",walletId);


                if(!transactionModel.getVasDetails().getTransactionRef().equals("")) {
                    String transactionRef =transactionModel.getVasDetails().getTransactionRef();
                    receiptMap.put("REF",transactionRef);

                    printerDevice.printlnText(format2, "REF:" +transactionRef );
                }


                if(!(transactionModel.getVasDetails().getVasType().isEmpty() )|| !(transactionModel.getVasDetails().getVasType() == null) ) {
                    String vasType = transactionModel.getVasDetails().getVasType();

                    Log.d("Main", "printImage() called with: vasType = [" + vasType + "]");
                    switch (vasType) {


                        case Models.AIRTIME: {

                            Models.AirtimeModel airtimeModel = (Models.AirtimeModel) transactionModel.getVasDetails().getVasTypeModel();

                            if(!airtimeModel.getError()) {
                                String phone = airtimeModel.getRecepiant_phone();
                                String stan = transactionModel.getVasDetails().getStan();
                                receiptMap.put("RECIPIENT",phone);
                                receiptMap.put("STAN",stan);

                                printerDevice.printlnText(format2, "RECIPIENT :      " + phone);
                                printerDevice.printlnText(format2, "STAN:             " + stan);

                                //              printerDevice.printlnText(format2, "TRANS SEQ : " + transactionModel.getVasDetails());
//                    printerDevice.printlnText(format2, "DATE :     " + transactionModel.getVasDetails().getDateTime());
                    //extref or ref
                            }
                        }
                        break;
                        case Models.TRANSFER: {
                            Models.TransferModel transferModel = (Models.TransferModel) transactionModel.getVasDetails().getVasTypeModel();

                            if(!transferModel.getError()) {
                                String recipient = transferModel.getRecepiant();
                                String stan = transactionModel.getVasDetails().getStan();
                                String acountName =transferModel.getAccountName();
                                String recivingBank =transferModel.getRecivingBank();

                                receiptMap.put("RECIPIENT",recipient);
                                receiptMap.put("STAN",stan);
                                receiptMap.put("ACC. NAME",acountName);
                                receiptMap.put("REC. Bank",recivingBank);
                                printerDevice.printlnText(format2, "RECIPIENT :       " + recipient);
                                printerDevice.printlnText(format2, "STAN :             " +stan );
                                printerDevice.printlnText(format2, "ACC. NAME :       " + acountName);
                                printerDevice.printlnText(format2, "REC. Bank:             " + recivingBank);

//                  printerDevice.printlnText(format2, "TRANS SEQ :  " + transactionModel.getVasDetails());
//                    printerDevice.printlnText(format2, "DATE :       " + transactionModel.getVasDetails().getDateTime());
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

                                String iuc = cableTvModel.getIuc();
                                String stan = transactionModel.getVasDetails().getStan();

                                receiptMap.put("IUC",iuc);
                                receiptMap.put("STAN",stan);


                    printerDevice.printlnText(format2, "IUC :              " + iuc );
                    printerDevice.printlnText(format2, "STAN:             " +stan  );

//
                            }

                        }
                       break;
                        case Models.DISCO: {
                            Models.DiscosModel discosModel = ( Models.DiscosModel) transactionModel.getVasDetails().getVasTypeModel();






                            if(!discosModel.getRecepiant_name().trim().equals("")) {
                                String recepiant_name = discosModel.getRecepiant_name();
                                receiptMap.put("NAME",recepiant_name);



                                printerDevice.printlnText(format2, "NAME :    " + recepiant_name);
                            }
                            if(!discosModel.getAddress().trim().equals("")) {
                                String address = discosModel.getAddress();
                                receiptMap.put("ADDR",address);


                                printerDevice.printlnText(format2, "ADDR :   " + address);
                            }
                            if(!discosModel.getMeterType().trim().equals("")) {
                                String meterType =discosModel.getMeterType();
                                receiptMap.put("METER TYPE",meterType);


                                printerDevice.printlnText(format2, "METER TYPE :            " + meterType);

                            }

                            if(!discosModel.getMeterNumber().equals("")) {
                                String meterNumber =discosModel.getMeterNumber();
                                receiptMap.put("METER NO",meterNumber);
                                printerDevice.printlnText(format2, "METER NO :          " + meterNumber);
                            }
                            if(!discosModel.getError()) {

                                Log.d("discosModel.getUnit().trim() !=\"\"", String.valueOf(discosModel.getUnit().trim() !="").toString());

                                Log.d("discosModel.getUnit().trim() !=\"\"", String.valueOf(discosModel.getUnit().trim() !="").toString());
                                Log.d("String.valueOf(discosModel.getUnit().trim().equals()).toString()", String.valueOf(discosModel.getUnit().trim().equals("")).toString());






                                if(!discosModel.getUnit().trim().equals("")) {
                                    String unit = discosModel.getUnit();
                                    receiptMap.put("UNIT",unit);

                                    printerDevice.printlnText(format2, "UNIT :            " + unit );
                                }
                                if(!discosModel.getUnit_value().trim().equals("")) {
                                    String unit_value = discosModel.getUnit_value();
                                    receiptMap.put("UNIT VALUE",unit_value);

                                    printerDevice.printlnText(format2, "UNIT VALUE :      " + discosModel.getUnit_value());
                                }
                                if(!discosModel.getArras().trim() .equals("")) {
                                    String arras =discosModel.getArras();
                                    receiptMap.put("ARREARS",arras);


                                    printerDevice.printlnText(format2, "ARREARS :         " +arras );
                                }
                                if(!discosModel.getTarrif().trim().equals("")) {
                                    String tarrif =discosModel.getTarrif();
                                    receiptMap.put("TARRIF",tarrif);
                                    printerDevice.printlnText(format2, "TARRIF :          " + discosModel.getTarrif());
                                }

                                    if(!discosModel.getToken().equals("")) {
                                        String token =discosModel.getToken();
                                        receiptMap.put("TOKEN",token);
                                        printerDevice.printlnText(format2, "TOKEN  :   " + token );

                                    }

                                if(!transactionModel.getVasDetails().getStan().equals("")) {
                                    String stan =transactionModel.getVasDetails().getStan();
                                    receiptMap.put("STAN",stan);
                                    printerDevice.printlnText(format2, "STAN:             " +stan );

                                }

//                    printerDevice.printlnText(format2, "DATE :       " + transactionModel.getVasDetails().getDateTime());
                            }
                        }
                        break;
                        default: {

                            vastransactionFailed =false ;



                        }

                    }
                    String paymentMethod = "Card";
                    if(appState.isWallet) {
                        paymentMethod ="Cash";
                    }

                    printerDevice.printlnText(format2, "PAYMENT METHOD :     " + paymentMethod);



                    receiptMap.put("PAYMENT METHOD ",paymentMethod);



                }
         String date = transactionModel.getCardDetails().getISODateTime().trim().equals("")?transactionModel.getVasDetails().getDateTime().trim():transactionModel.getCardDetails().getISODateTime().trim();
                printerDevice.printlnText(format2, "DATE:       " + date  );




                if(!appState.isWallet) {

                    String cardholderName = transactionModel.getCardDetails().getCardholderName();
                    String rrn = transactionModel.getCardDetails().getRrn();
                    String merchantName =transactionModel.getCardDetails().getMerchantName();
                    String pan =transactionModel.getCardDetails().getPan();
                    String ticket = transactionModel.getCardDetails().getTicket();
                    String tvr = transactionModel.getCardDetails().getTVR();
                    String aid =transactionModel.getCardDetails().getAID();
                    String cardType =transactionModel.getCardDetails().getCardType();
                    String expiry =transactionModel.getCardDetails().getExpiry();



                    receiptMap.put("CARDHOLDER NAME",cardholderName);
                    receiptMap.put("RRN",rrn);
                    receiptMap.put("MERCHANT",merchantName);
                    receiptMap.put("PAN",pan);
                    receiptMap.put("TICKET",ticket);
                    receiptMap.put("TVR",tvr);
                    receiptMap.put("AID",aid);
                    receiptMap.put("CARD TYPE",cardType);
                    receiptMap.put("EXPIRY",expiry);








                    printerDevice.printlnText(format2, "CARDHOLDER NAME :  " + cardholderName);
                       printerDevice.printlnText(format2, "RRN:             " + rrn);
                       printerDevice.printlnText(format2, "MERCHANT:  " + merchantName);
                       printerDevice.printlnText(format2, "PAN:          " + pan);
                       printerDevice.printlnText(format2, "TICKET:       " + ticket);
                       printerDevice.printlnText(format2, "TVR:          " + tvr);
                       printerDevice.printlnText(format2, "AID:          " + aid);
                       printerDevice.printlnText(format2, "CARD TYPE:    " + cardType);
                       printerDevice.printlnText(format2, "EXPIRY :          " + expiry);
                   }


                Log.d("MainActivity", "printImage() called with: transactionModel = [" + transactionModel + "], getAmount()).equals(\"0.00\") = [" + String.valueOf((transactionModel.getVasDetails().getAmount()).equals("0.00")) + "],  getAmount()).isEmpty() = [" + String.valueOf((transactionModel.getVasDetails().getAmount()).isEmpty() + "]"+ "],  getAmount()).getAmount()==null = [" + String.valueOf((transactionModel.getVasDetails().getAmount()==null ) )));
                Log.d("MainActivity", "printImage() called with: transactionModel getAmount = [" + transactionModel.getVasDetails().getAmount() + "], copy = [" +  transactionModel.getVasDetails().getAmount() + "]");
                Log.d("MainActivity", "printImage() called with: transactionModel transactionModel.getCardDetails().getAmount() = [" + transactionModel.getCardDetails().getAmount() + "], getVasDetails().getAmount() = [" +  transactionModel.getVasDetails().getAmount() + "]");
                Log.d("MainActivity", "printImage() called with: transactionModel transactionModel.getCardDetails().getAmount() = [" + ((transactionModel.getVasDetails().getAmount()).equals("0.00") ? transactionModel.getCardDetails().getAmount(): transactionModel.getVasDetails().getAmount()) + "]");
//                String amount = (transactionModel.getVasDetails().getAmount()).equals("0.00") ? transactionModel.getCardDetails().getAmount(): (transactionModel.getVasDetails().getAmount());appState.trans.transAmount
                Log.d("amount check amt  >>>>>>>> ",  (transactionModel.getVasDetails().getAmount()));

                Log.d("amount check   >>>>>>>> ", String.valueOf((transactionModel.getVasDetails().getAmount()).equals("0.00")));

//                int vasTransactionAmount = ;
//                String amount = (transactionModel.getVasDetails().getAmount()).equals("0.00") ? transactionModel.getCardDetails().getAmount(): (appState.trans.getTransAmount().toString());
                String amount = (transactionModel.getVasDetails().getAmount()).equals("0.00") ? transactionModel.getCardDetails().getAmount(): (String.valueOf(Integer.parseInt(transactionModel.getVasDetails().getAmount()) * 100));

                Log.d("amount",amount);
//               String nairaSign= 	\u20A6;
                printerDevice.printlnText("\n");
                printerDevice.printlnText(format, "***********************");
//                if(transactionModel.getCardDetails() != null ) {
//                    printerDevice.printlnText(format, "NGN " +transactionModel.getVasDetails().getAmount());
//                }else{
//                    printerDevice.printlnText(format, "NGN " + transactionModel.getCardDetails().getAmount());Double.valueOf(amount)))
                              printerDevice.printlnText(format, ("NGN " +  AppUtil.formatAmount(amount,true) ));
//                }
                printerDevice.printlnText(format, "***********************");
               String transactionReason = "";
                if(transactionModel.getVasDetails().getTransactionStatusMessage().isEmpty() || transactionModel.getVasDetails().getTransactionStatusMessage() == null ) {
                    transactionReason =transactionModel.getCardDetails().getTransactionStatusReason();
                    printerDevice.printlnText(format, transactionModel.getCardDetails().getTransactionStatusReason());
                                   }else{
                    transactionReason =transactionModel.getVasDetails().getTransactionStatusMessage();

                    printerDevice.printlnText(format, transactionModel.getVasDetails().getTransactionStatusMessage());

                }


                printerDevice.printlnText(format, "-----------------------");

                printerDevice.printlnText(format, "TAMSLITE v(1.0.0) WA");
                printerDevice.printlnText(format, "Powered by ITEX");
                printerDevice.printlnText(format, "www.iisysgroup.com");
                printerDevice.printlnText(format, "0700-2255-4839");
                printerDevice.printlnText("\n");

                ReceiptModel receiptModel = new ReceiptModel(date, transactionType,  appState.vasTransactionstatus, receiptMap,amount, transactionReason);

                View view = PrintUtils.INSTANCE.generateReceipt(this, receiptModel, receipt);
//                Bitmap bitmap = getBitmapFromView(view);
//                printerDevice.printBitmap(format, bitmap);

                printerDevice.close();

                if(isDonePrinting){
                    appState.isWithdrawal=false;
                    appState.isTransfer=false;
                }

            }
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }

    private Bitmap  getBitmapFromView( View view ){
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable , then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }



    @Override
    public void onBackPressed(){
        Intent intent = new Intent(context, FuncMenuActivity.class);

        startActivity(intent);

    }

//    @Override
//    protected void onCancel()
//    {
//        onBackPressed();
//    }
//
//    @Override
//    protected void onBack()
//    {
//        onBackPressed();
//    }


//    /                                bankLogoName = transactionModel.getBankLogoName();
//                                 resourceId  = getResources().getIdentifier(bankLogoName, "drawable",
//                                        getPackageName());


}

