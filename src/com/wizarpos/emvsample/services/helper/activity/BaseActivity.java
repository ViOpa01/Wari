package com.wizarpos.emvsample.services.helper.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
//import android.widget.TextView;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.login.Helper;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {

    protected String userName = "", userId = "", terminalID = "";
    protected ProgressDialog progressDialog;
    FragmentManager fm;
    FragmentTransaction ft;
    private String startDate;
    private int currentDay, currentYear,currentMonth;
    private SimpleDateFormat dateFormat;
    private String newDate;
    private FilterPrefrences filterPrefrences;
    private String endDate;
    private String product ,productName;
    private String walletId, defaultWalletId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        userName = SecureStorage.retrieve(Helper.USERNAME, "");
        userId = SecureStorage.retrieve(Helper.USER_ID, "");
        terminalID = SecureStorage.retrieve(Helper.TERMINAL_ID, "");




        //timeOutTimer = new TimeOutTimer(Helper.getSessionDuration(this), 1000 * 30);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (Helper.shouldLogOut(this)) {
//            logUserOut();
//        }
//
//    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public void loadFragment(Fragment fragment) {
        ft = fm.beginTransaction();


        if (fm.findFragmentByTag(fragment.getClass().getSimpleName()) != null)
            ft.remove(fragment);

        ft.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();

    }







    @Override
    protected void onStop() {
        super.onStop();
        Helper.saveTimeOutTime(this);
    }

//    protected void logUserOut() {
//        Helper.saveTimeOutTime(this);
//        Helper.savePreference(this, Helper.LAST_LOGGED_IN, Helper.getPreference(this, Helper.LOG_IN_TIME, ""));
//        Requests.doLogOut(this);
//        finish();
//        startActivity(new Intent(this, LoginActivity.class));
//    }

//
//    public void sendGoogleAppInvite() {
//        Map<String, String> inviteParams = new ArrayMap<>();
//        inviteParams.put(Helper.REFERRAL_CODE, terminalID);
//
//        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
//                .setMessage(getString(R.string.invitation_message, userName))
//                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link, terminalID)))
//                .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
//                .setCallToActionText(getString(R.string.invitation_cta))
//                .setAdditionalReferralParameters(inviteParams)
//                .build();
//
//        startActivityForResult(intent, Helper.REQUEST_INVITE);
//    }

//    public void sendFacebookInvite() {
//        String appLinkUrl, previewImageUrl;
//
//        appLinkUrl = getString(R.string.facebook_invitation_deep_link, terminalID);
//        previewImageUrl = getString(R.string.invitation_custom_image);
//
//        if (AppInviteDialog.canShow()) {
//            AppInviteContent content = new AppInviteContent.Builder()
//                    .setApplinkUrl(appLinkUrl)
//                    .setPreviewImageUrl(previewImageUrl)
//                    .setPromotionDetails("Referral Code", terminalID)
//                    .build();
//            AppInviteDialog.show(this, content);
//        }
//    }


//    public void showReferralQrDialog() {
//        final ProgressDialog progressDialog = ProgressDialog.show(this, null, "Generating Referral QR... Please Wait", true, false);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                QRCodeWriter qrCodeWriter = new QRCodeWriter();
//                final String content = SecureStorage.retrieve(Helper.TERMINAL_ID, "");
//                try {
//                    BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400);
//                    final Bitmap qrBitmap = Helper.toBitmap(bitMatrix);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            View refView = View.inflate(BaseActivity.this, R.layout.referal_code_layout, null);
//                            ImageView qrImageView = refView.findViewById(R.id.qrImage);
//                            qrImageView.setImageBitmap(qrBitmap);
//                            TextView referralCodeTextView = refView.findViewById(R.id.referralCodeText);
//                            referralCodeTextView.setText(content);
//                            final AlertDialog alertDialog =
//                                    new AlertDialog.Builder(BaseActivity.this)
//                                            // .setTitle("Referral")
//                                            .setView(refView).create();
//
//
//                            refView.findViewById(R.id.cancel_button_ic).setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    alertDialog.dismiss();
//                                }
//                            });
//
//
//                            progressDialog.dismiss();
//                            alertDialog.show();
//
//                        }
//                    });
//
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            progressDialog.dismiss();
//                            Snackbar.make(BaseActivity.this.getCurrentFocus().getRootView(),
//                                    "Could not generate QR code", Snackbar.LENGTH_INDEFINITE).show();
//                        }
//                    });
//
//                }
//            }
//        }).start();
//
//
//    }
//
//    public void showReferralOptionDialog() {
//        ReferDialog referDialog = new ReferDialog();
//        referDialog.show(fm, "");
//    }


//    public void popUp() {
//        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
//
//        LayoutInflater inflater =  this.getLayoutInflater();
//
//
//
//        View view = inflater.inflate(R.layout.spinner,null);
//
//        final Spinner walletIdSpinner =   (Spinner)view.findViewById(R.id.wallet_id_spinner);
//        final Spinner productSpinner =   (Spinner)view.findViewById(R.id.product_spinner);
//
//
//
//
//        final List<String> products =   new ArrayList<>();
//
//        products.add("MTN");
//        products.add("AIRTEL");
//        products.add("GLO");
//        products.add("GOTV");
//        products.add("DSTV");
//        products.add("IKEJA_ELECTRICITY");
//        products.add("EKO_ELECTRICITY");
//        ArrayAdapter<String> allSpinner= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,products);
//        productSpinner.setAdapter(allSpinner);
//
//
//        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                switch (productSpinner.getSelectedItem().toString()){
//
//                    case "MTN":
//                        product="MTN";
//                        productName ="MTN";
//                        break;
//                    case "AIRTEL":
//                        product="AIRTEL";
//                        productName ="AIRTEL";
//                        break;
//                    case "GLO":
//                        product="Glo";
//                        productName ="GLO";
//                        break;
//
//                    case "GOTV":
//                        product="GOTV";
//                        productName ="GOTV";
//                        break;
//
//                    case "DSTV":
//                        product="DSTV";
//                        productName ="DSTV";
//                        break;
//
//                    case "IKEJA_ELECTRICITY":
//                        product="IKEJA_ELECTRICITY";
//                        productName ="IKEJA_ELECTRICITY";
//                        break;
//
//                    case "EKO_ELECTRICITY":
//                        product="EKO_ELECTRICITY";
//                        productName ="EKO_ELECTRICITY";
//                        break;
//
//
//
//
//
//
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//
//
//        List<String> wallets =   new ArrayList<>();
//      String userTrans = Helper.getPreference(getApplicationContext(),Helper.USER_TRANSACTIONS,"");
//        Log.i("userTransactionStr", "popUp: "+  userTrans);
//        Gson  gson = new Gson();
//
//
//        UserTransactions userTransactions = gson.fromJson(userTrans,UserTransactions.class);
//        walletId=userTransactions.getWalletID();
//        wallets.add(walletId+  " (Default)");
//        if(!userTransactions.getSubAgents().isEmpty()){
//            for (SubAgent subAgent: userTransactions.getSubAgents()) {
//                wallets.add(subAgent.getWalletID());
//            }
//
//        }
//
//
//        ArrayAdapter<String> walletSpinner= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,wallets);
//
//        walletIdSpinner.setAdapter(walletSpinner);
//
//
//
//
//        walletIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                Log.i("Spinner", "onItemSelected: "  + walletIdSpinner.getSelectedItem().toString());
//
//
//                defaultWalletId =walletIdSpinner.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//
//
//
//
//
//
//
//        final TextView startDateTextView = (TextView)view.findViewById(R.id.start_date);
//        final TextView endDateTextView = (TextView)view.findViewById(R.id.end_date);
//
//        final Calendar toadaysDate = Calendar.getInstance();
//    currentDay = toadaysDate.get(Calendar.DAY_OF_MONTH);
//      currentMonth = toadaysDate.get(Calendar.MONTH);
//       currentYear = toadaysDate.get(Calendar.YEAR);
//
//
//       dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//       Long todaysDateInMills = toadaysDate.getTimeInMillis();
//       Long Timeat31ago= 86400000L * 31;
//       Log.i("Date", "TimeStamp 31Days Ago "+ Timeat31ago );
//       Long Date31DaysAgo = (todaysDateInMills - Timeat31ago);
//
//        Log.i("Date", "TimeStamp 31 days ago "+ Date31DaysAgo);
//
//
//        Log.i("Date", "Date before today: " + dateFormat.format(new Date(Date31DaysAgo)));
//
//
//
//        startDate =  dateFormat.format(toadaysDate.getTime());
//
//        endDate = dateFormat.format(new Date(Date31DaysAgo));
//
//        Log.i("Date", "popUp: " +dateFormat.format(toadaysDate.getTime()));
//
//        startDateTextView.setText(startDate);
//
//        endDateTextView.setText( endDate);
//
//
//
//
//
//
//
//
//
//        startDateTextView.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//
//        DatePickerDialog.OnDateSetListener startDateDialogue = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//               int currentYear= year;
//               int currentMonth=month + 1 ;
//
//               String currentDate;
//
//
//                String currentMonthst =isgreaterthan9(currentMonth);
//                String currentDayst = isgreaterthan9(currentDay);
//
//                currentDate = currentYear + "-" + currentMonthst + "-"+ currentDayst ;
//
//                startDateTextView.setText(currentDate);
//
//            }
//        };
//               new DatePickerDialog(BaseActivity.this,startDateDialogue,currentYear,currentMonth,currentDay).show();
//             }
//         });
//
//
//         endDateTextView.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//
//                 DatePickerDialog.OnDateSetListener popupCalender = new DatePickerDialog.OnDateSetListener() {
//                     @Override
//                     public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//
//                         toadaysDate.set(currentMonth,currentMonth,currentDay);
//
//                         currentYear= year;
//                         currentMonth=month + 1 ;
//                         currentDay= dayOfMonth;
//
//
//                         String currentMonthst =isgreaterthan9(currentMonth);
//                         String currentDayst = isgreaterthan9(currentDay);
//
//                         endDate = currentYear + "-" + currentMonthst + "-"+ currentDayst ;
//
//
//
//                         endDateTextView.setText(endDate);
//
//
//                     }
//                 };
//
//
//                 new DatePickerDialog(BaseActivity.this,popupCalender,currentYear,currentMonth,currentDay).show();
//
////                 endDateTextView.setText(startDate);
//
//
//             }
//         });
//
//
//        builderSingle.setView(view);
//
//
//        builderSingle.setPositiveButton( getString(R.string.apply_filter), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                String startDate =   endDateTextView.getText().toString().trim();
//                String endDate =   startDateTextView.getText().toString().trim();
//
//                Log.i("Dates", "Start Date: " + startDate +  " End Date: " + endDate);
//
//
//                filterPrefrences =(FilterPrefrences) new TransactionHistoryFragment();
//
//            filterPrefrences.filterPrefrences(walletId,product,productName,startDate,endDate,8,1);
//                Intent myIntent = new Intent(((Dialog) dialog).getContext(), TransactionHistoryActivity_D.class);
//                startActivity(myIntent);
//            }
//        });
//
//
//
//        builderSingle.show();
//
//
//
//    }
//
//
//          private String isgreaterthan9(int value) {
//          String outcome;
//          if (value > 9) {
//              outcome = ""+value;
//          }
//          else{
//              outcome ="0"+value;
//          }
//        return outcome;
//      };





    public interface FilterPrefrences{

        void filterPrefrences(String viewWallet, String product, String productName, String startDate, String endDates, int limit, int currentPage);

    }




    /*private class TimeOutTimer extends CountDownTimer {

        long time_out, interval;

        public TimeOutTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            time_out = millisInFuture;
            interval = countDownInterval;
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            Log.i("Payvice", "Timer onTick - Time left: " + (millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
//            Log.i("Payvice", "Timer onFinish");
            Toast.makeText(getApplication(), "This session has expired, please log in to continue", Toast.LENGTH_LONG).show();
            logUserOut();
        }
    }*/


}
