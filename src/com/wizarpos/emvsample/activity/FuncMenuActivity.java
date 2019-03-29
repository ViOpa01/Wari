package com.wizarpos.emvsample.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.auth.PinInterFace;
import com.wizarpos.emvsample.db.TransactionResultService;
import com.wizarpos.emvsample.transaction.Nibss;
import com.wizarpos.jni.PinPadInterface;
import com.wizarpos.util.StringUtil;

public class FuncMenuActivity extends FuncActivity
{
	private TextView textTitle  = null;
	private Button   buttonBack = null;
	private Button   buttonMore = null;

	private Button buttonSale = null;
	private Button buttonLastPBOC = null;
	private Button buttonTrans = null;
	private Button buttonSettle = null;
	private Button buttonEncrypt = null;
	private Button purchaseCashBack;
	private AlertDialog alertDialog;
	private Button buttonEod;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_func_menu);
		initToolbar();

//		textTitle = (TextView)findViewById(R.id.tAppTitle);
//		textTitle.setText("MAIN");
//
//		buttonBack = (Button)findViewById(R.id.btn_back);
//		buttonBack.setOnClickListener(new ClickListener());
//
//		buttonMore = (Button)findViewById(R.id.btn_more);
//		buttonMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));

		buttonSale = (Button)findViewById(R.id.bFunc_Sale);
		buttonSale.setOnClickListener(new ClickListener());


		buttonLastPBOC = (Button)findViewById(R.id.bFunc_LastPBOC);
		buttonLastPBOC.setOnClickListener(new ClickListener());

		buttonTrans = (Button)findViewById(R.id.bFunc_Trans);
		buttonTrans.setOnClickListener(new ClickListener());

		buttonSettle = (Button)findViewById(R.id.bFunc_Settle);
		buttonSettle.setOnClickListener(new ClickListener());

		buttonEncrypt = (Button)findViewById(R.id.bFunc_encrypt);
		buttonEncrypt.setOnClickListener(new ClickListener());

		purchaseCashBack = findViewById(R.id.cashBack);
		purchaseCashBack.setOnClickListener(new ClickListener());

		buttonEod = (Button)findViewById(R.id.eod);
		buttonEod.setOnClickListener(new ClickListener());
	}

	private void initToolbar() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Wari");
	}

	@Override
	protected void onStart()
	{
		if(debug)Log.d(APP_TAG, "FuncMenuActivity onStart");
		super.onStart();
		if(appState.emvParamChanged == true)
		{
			setEMVTermInfo();
		}
		startIdleTimer(TIMER_IDLE, 300);
	}

	@Override
	protected void onResume()
	{
		resetPurchase();
		if(debug)Log.d(APP_TAG, "FuncMenuActivity onResume");
		super.onResume();
	}

	@Override
	protected void onStop()
	{
		if(debug)Log.d(APP_TAG, "FuncMenuActivity onStop");
		super.onStop();
	}

	@Override
	protected void onPause()
	{
		if(debug)Log.d(APP_TAG, "FuncMenuActivity onPause");
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		if(debug)Log.d(APP_TAG, "FuncMenuActivity onDestroy");
		super.onDestroy();
	}

	@Override
	public void onBackPressed(){
		go2Idle();
	}

	@Override
	protected void onCancel()
	{
		onBackPressed();
	}

	@Override
	protected void onBack()
	{
		onBackPressed();
	}

	public class ClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.cashBack:
				appState.needCard = true;
				appState.purchaseWithCashBack = true;
				sale();
				break;
			case R.id.bFunc_Sale:
				appState.needCard = true;
				sale();
				break;

			case R.id.bFunc_LastPBOC:
				startActivity(new Intent(FuncMenuActivity.this, GetMasterKey.class));
				finish();
				break;

			case R.id.bFunc_Trans:
				appState.needCard = true;
				appState.balanceEnc = true;
				sale();
				break;

			case R.id.bFunc_Settle:
				AlertDialog.Builder alert = new AlertDialog.Builder(FuncMenuActivity.this, R.style.AlertDialogCustom);
				alert.setTitle("Supervisor Pin");
				final EditText editText = new EditText(FuncMenuActivity.this);
				editText.setHint("Enter Pin");
				editText.setTextColor(Color.parseColor("#ffffff"));
				editText.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
				LinearLayout linearLayout = new LinearLayout(FuncMenuActivity.this);
				linearLayout.setLayoutParams(params);
				linearLayout.setPadding(10,10,10,10);
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				linearLayout.addView(editText);
				alert.setView(linearLayout);
				alert.setCancelable(false);

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

				alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String passwordEn = editText.getText().toString();
						if(passwordEn.isEmpty()){
							editText.setError("Empty Field");
						}
						if(passwordEn.equals("1414")){
							startActivity(new Intent(FuncMenuActivity.this, RefundActivity.class));
						}else{
							Toast.makeText(FuncMenuActivity.this,"Invalid Pin", Toast.LENGTH_LONG).show();
							dialog.dismiss();
						}
					}
				});

				alert.show();


				break;

			case R.id.bFunc_encrypt:
				//Encrypt();
				configureTerminal();

				break;

			case R.id.btn_back:
				go2Idle();
				break;

			case R.id.eod:
			    startActivity(new Intent(FuncMenuActivity.this, PinInterFace.class));
				break;

			}
		}
	}

	private void configureTerminal() {
		final AlertDialog.Builder alertDialod = new AlertDialog.Builder(this,R.style.AlertDialogCustom);
		final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setLayoutParams(params);

		//Password firs
		final EditText pwd = new EditText(this);
		pwd.setInputType(InputType.TYPE_CLASS_NUMBER |
				InputType.TYPE_NUMBER_VARIATION_PASSWORD);
		pwd.setMaxLines(1);
		params.setMargins(20,0,20,0);
		pwd.setLayoutParams(params);
		pwd.setPadding(15,15,15,15);
		pwd.setHint("Enter Authorised password");

		//MerchartId firs
		final EditText meid = new EditText(this);
		meid.setInputType(InputType.TYPE_CLASS_TEXT);
		meid.setMaxLines(1);
		params.setMargins(20,0,20,0);
		meid.setLayoutParams(params);
		meid.setPadding(15,15,15,15);
		meid.setHint("Enter Terminal ID");
		meid.setVisibility(View.GONE);


		//Confirm Button
		final  Button btn = new Button(this);
		LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		pm.setMargins(20,0,20,0);
		btn.setLayoutParams(pm);
		btn.setPadding(15,15,15,15);
		btn.setText("Confirm");
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(pwd.getText().toString().isEmpty()){
					pwd.setError("Please eneter password");
					Toast.makeText(FuncMenuActivity.this,"No password Entered",Toast.LENGTH_LONG).show();
					return;
				}
				else if(pwd.getText().toString().contentEquals("4839")){
					pwd.setVisibility(View.GONE);
					meid.setVisibility(View.VISIBLE);
					btn.setOnClickListener(null);
					btn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							if(meid.getText().toString().isEmpty()){
								Toast.makeText(FuncMenuActivity.this, "Merchant ID not entered", Toast.LENGTH_LONG).show();
								meid.setError("Merchant ID not entered");
							}
							else{
								configure(meid.getText().toString());
								alertDialog.dismiss();
							}
						}
					});


				}else{
					pwd.setError("Invalid password");
					Toast.makeText(FuncMenuActivity.this,"Invalid password",Toast.LENGTH_LONG).show();

					return;
				}
			}
		});

		linearLayout.addView(pwd);
		linearLayout.addView(meid);
		linearLayout.addView(btn);
		alertDialod.setTitle("Login");
		alertDialod.setView(linearLayout);


		alertDialod.setCancelable(false);

		alertDialod.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		});

	    alertDialog =	alertDialod.create();
	    alertDialog.show();

	}

	private void configure(String termId) {
		appState.prep(termId,this,new Nibss.Nibs<String>() {
			@Override
			public void complete(String res) {
				Toast.makeText(FuncMenuActivity.this,"Successfull",Toast.LENGTH_LONG).show();
			    startActivity(new Intent(FuncMenuActivity.this, GetMasterKey.class));
			    finish();
			}

			@Override
			public void error(String e) {
				Toast.makeText(FuncMenuActivity.this,"Error",Toast.LENGTH_LONG).show();

			}
		});
	}

	private static final String EncryptTag = "EncryptTest";

	private void Encrypt(){
		String sResult = "";
		if(PinPadInterface.open() >= 0)
		{
			if(PinPadInterface.setKey(1,2,0,1) >=0){
//				byte[] inArray = createByteArray(32);
				byte[] inArray = StringUtil.hexString2bytes("57132223000010364419D19122010000000005230F000000");

				Log.i(EncryptTag,"inArray:" + StringUtil.toHexString(inArray,true));
				byte[] outArray = new byte[1024];
//				int realLength = PinPadInterface.encrypt(inArray, inArray.length, outArray);
				int realLength = PinPadInterface.encryptWithMode(inArray, outArray, 1/*CBC*/,new byte[8]/*vector*/,8/*length of initial vector*/);

				byte[] result = subBuffer(outArray, realLength);
                Log.i(EncryptTag,"outArray:" + StringUtil.toHexString(result,true));
                Toast.makeText(this,
                    "in"+ StringUtil.toHexString(inArray,true) + "\n" +
                        "out:"+StringUtil.toHexString(result,true),
                    Toast.LENGTH_SHORT).show();

                PinPadInterface.close();
			}
		}
	}

	public byte[] createByteArray(int length) {
		byte[] array = new byte[length];
		int random;
		for (int i = 0; i < length; i++) {
			random = (int) (Math.random() * (0xFF));
			array[i] = (byte) random;
		}
		return array;
	}
	public byte[] subBuffer(byte[] buf, int length) {
		if (length < 0) {
			return null;
		}
		byte[] result = new byte[length];
		if (buf.length >= length) {
			System.arraycopy(buf, 0, result, 0, length);
		}
		return result;
	}
}
