package com.wizarpos.emvsample.payments_menu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.iisysgroup.poslib.utils.AccountType;
import com.wizarpos.emvsample.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Agbede on 2/12/2018.
 */

public abstract class BasePaymentActivity extends AppCompatActivity implements View.OnClickListener{
    //This class handles radio button selections, and methods that are used across almost all payments_menu
    private final String TAG = getClass().getSimpleName();

    public static final int INT_PURCHASE_ACTIVITY = 0;
    public static final int INT_BALANCE_ENQUIRY = 1;
    public static final int INT_CASH_ADVANCE = 2;
    public static final int INT_CASH_BACK = 3;
    public static final int INT_REFUND_REVERSAL = 4;
    public static final int INT_TRANSFER = 5;

    public static final int INT_BILL_PAYMENT = 6;


    public static final int GTB_PURCHASE =7;

    //    public static String TRANSACTION_TYPE = "transaction_type";
    public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String TRANSACTION_AMOUNT = "transaction_amount";
    public static final String TRANSACTION_ACCOUNT_TYPE = "account_type";
    public static final String TRANSACTION_ADDITIONAL_AMOUNT = "additional_amount";
    public static final String TRANSACTION_ACCOUNT_NUMBER = "account_number";
    public static final String TRANSACTION_BANK_NAME = "bank_name";
    public static final String ADDITIONAL_TRANSACTION_TYPE = "additional_transaction_type";
    public static final String PROCESSING_CARD_OR_WALLET = "processing_card_or_wallet";

    public static final String TRANSACTION_RRN = "rrn";

    public static String SAVINGS = "savings";
    public static String CURRENT = "current";
    public static String DEFAULT = "default";
    public static String CREDIT = "credit";

    public   Boolean isCard = true;

    private AccountType radio_selected = null;

    RadioGroup radioGroup;


    abstract int getMaxCount();
    protected abstract int getTextLayoutId();
    abstract int getNumberOfPages();
    protected abstract int getPageLayout();
    public abstract void onBackPressed();
    abstract void onEnterPressed();
    protected abstract void onAccountTypeSet(AccountType account_type);


    EditText mPassword = null;
    FrameLayout mPinHolder= null;
    FrameLayout mFragmentHolder = null;

    Button mSubmit = null;



    TextView pageTitle;


    @Override
    public void onClick(View view) {

//        StringBuilder displayStr = new StringBuilder(pageTitle.getText().toString());
//        StringBuffer strAmount = new StringBuffer(pageTitle.getText().toString());

        StringBuilder displayStr = null;
        StringBuffer strAmount;
        if(view.getId() != R.id.submit_button) {
            displayStr = new StringBuilder(pageTitle.getText().toString());

            strAmount = new StringBuffer(pageTitle.getText().toString());
        }

        switch (view.getId()) {
            case R.id.btn1:
                fixAppend(displayStr, "1");
                break;
            case R.id.btn2:
                fixAppend(displayStr, "2");
                break;
            case R.id.btn3:
                fixAppend(displayStr, "3");
                break;
            case R.id.btn4:
                fixAppend(displayStr, "4");
                break;
            case R.id.btn5:
                fixAppend(displayStr, "5");
                break;
            case R.id.btn6:
                fixAppend(displayStr, "6");
                break;
            case R.id.btn7:
                fixAppend(displayStr, "7");
                break;
            case R.id.btn8:
                fixAppend(displayStr, "8");
                break;
            case R.id.btn9:
                fixAppend(displayStr, "9");
                break;
            case R.id.btn0:
                fixAppend(displayStr, "0");
                break;
            case R.id.btn00:
                fixAppend(displayStr, "00");
                break;
            case R.id.btnenter:
                //when the user presses enter after entering the amount. The response should move to the next page where the user can select type or enter cash again
                //depending on whether the user needs to enter cash
                onEnterPressed();

                break;
            case R.id.btnclr:
                displayStr.deleteCharAt(displayStr.length() - 1);
                fixDelete(displayStr);
                break;
            case R.id.btncancel:
                onBackPressed();
                break;
            case R.id.submit_button:
                getAccountType();
                break;

//            case R.id.btn_balance_enquiry:
//                getAccountType();
//                break;
        }
    }

    private void getAccountType() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        switch (selectedId)
        {
            case R.id.radio_savings: radio_selected = AccountType.SAVINGS;
                break;
            case R.id.radio_current: radio_selected = AccountType.CURRENT;
                break;
            case R.id.radio_default: radio_selected = AccountType.DEFAULT_UNSPECIFIED;
                break;
            case R.id.radio_credit: radio_selected = AccountType.CREDIT;
                break;
            default: radio_selected = AccountType.DEFAULT_UNSPECIFIED;
                break;
        }

        onAccountTypeSet(radio_selected);
    }


    private boolean checkPassword() {
        String  password = mPassword.getText().toString();

        Log.d(TAG, "checkPassword:   " +password);
        if (password.trim().equals("4839")) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mPassword.getWindowToken(), 0);
//            mFragmentHolder?.setVisibility(View.VISIBLE)
            mPinHolder.setVisibility(View.GONE);
            Log.d(TAG, "checkPassword:True   " +password);

            return true;
        } else {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mPassword.getWindowToken(), 0);
            mPassword.setError("Wrong password");
            return false;

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        switch (getPageLayout())
        {
//            case INT_PURCHASE_ACTIVITY: setContentView(R.layout.activity_purchase);
//                mSubmit = findViewById(R.id.submit_pin);
//                findViewById(R.id.enter_amount).setVisibility(View.GONE);
//                mPinHolder = findViewById(R.id.frame_password_layout);
//                mPassword = findViewById (R.id.password_text);
//                mPinHolder.setVisibility(View.VISIBLE);
//

//                mSubmit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (checkPassword()) {
//                            findViewById(R.id.enter_amount).setVisibility(View.VISIBLE);
//                            initializeAmountEntryElements();
//                            initializeAccountTypeSelection();
//                        }
//                    }
//                });
//
//                mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                    @Override
//                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                        if (actionId == EditorInfo.IME_ACTION_GO){
//                            if (checkPassword()) {
//                                findViewById(R.id.enter_amount).setVisibility(View.VISIBLE);
//                                initializeAmountEntryElements();
//                                initializeAccountTypeSelection();
//                            }                        }
//                        return true;
//                    }
//                });
//



//                break;
//            case INT_BALANCE_ENQUIRY: setContentView(R.layout.activity_balance_inq);
//                initializeBalanceEnquiryAccountTypeElements();
//                break;
//
//            case INT_CASH_ADVANCE: setContentView(R.layout.activity_cash_advance);
//                initializeAmountEntryElements();
//                initializeAccountTypeSelection();
//                break;

            case INT_CASH_BACK: setContentView(R.layout.activity_cash_back);

                initializeAmountEntryElements();
                initializeAccountTypeSelection();
                break;

//            case INT_REFUND_REVERSAL: setContentView(R.layout.activity_refund);
//                initializeAmountEntryElements();
//                initializeAccountTypeSelection();
//                break;

            case INT_TRANSFER: setContentView(R.layout.activity_transfer);
                initializeAmountEntryElements();
                // initializeAccountTypeSelection();
                break;

            case INT_BILL_PAYMENT :
                initializeAmountEntryElements();
                break;

//            case GTB_PURCHASE: setContentView(R.layout.activity_bill_purchase);
//                Log.d("Here at INT_BILL_PURCHASE", "  <<<<<<");
//



//                if(isCard){
//                    Log.d("Here isCARD checking to select ", isCard.toString());

//                initializeAccountTypeSelection();
//                }
//                break;

        }

//        pageTitle = findViewById(getTextLayoutId());



        if (getPageLayout() != GTB_PURCHASE) {

            pageTitle = findViewById(getTextLayoutId());
        }
    }

    private void initializeAccountTypeSelection() {
        radioGroup = findViewById(R.id.radio_payments_account_group);
        findViewById(R.id.submit_button).setOnClickListener(this);
    }

    private void initializeBalanceEnquiryAccountTypeElements() {
//        findViewById(R.id.btn_balance_enquiry).setOnClickListener(this);
        findViewById(R.id.submit_button).setOnClickListener(this);
        radioGroup = findViewById(R.id.radio_payments_account_group);
    }


    public void initializeAmountEntryElements() {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn00).setOnClickListener(this);
        findViewById(R.id.btnclr).setOnClickListener(this);
        findViewById(R.id.btnenter).setOnClickListener(this);
        findViewById(R.id.btncancel).setOnClickListener(this);


    }


    protected void fixAppend(StringBuilder displayStr, String digit) {
        if(displayStr.length() <= getMaxCount())
        {
            displayStr.append(digit);
            double newAmount = Double.parseDouble(displayStr.toString());
            // fix new input
            newAmount = newAmount * 10.00;
            if("00".equals(digit)) newAmount = newAmount * 10.00;


            String updatedAmount = new DecimalFormat("0.00").format(newAmount);
            pageTitle.setText(String.valueOf(updatedAmount));
        }
    }

    protected void fixDelete(StringBuilder displayStr) {
        BigDecimal bd = new BigDecimal(displayStr.toString());
        bd = bd.movePointLeft(1);
        pageTitle.setText(String.valueOf(bd.toString()));
    }

    public void showVisibility(View view)
    {
//        int [] layout_ids = {R.id.enter_amount,R.id.account_select_reversal,R.id.insert_card, R.id.search_refund,R.id.refund_details,R.id.account_select_refund, R.id.account_select, R.id.purchase_account_select};

        int [] layout_ids = {R.id.enter_amount,R.id.insert_card, R.id.account_select};

        if (view.getVisibility() == View.VISIBLE){
            Log.d(TAG, "Visibility true");
            return;
        }

        for (int ids : layout_ids)
        {
            if (findViewById(ids) != null && ids != view.getId())
                findViewById(ids).setVisibility(View.GONE);
        }

        view.setVisibility(View.VISIBLE);
    }



}

