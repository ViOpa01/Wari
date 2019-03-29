package com.wizarpos.emvsample.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iisysgroup.poslib.ISO.GTMS.GtmsKeyProcessor;
import com.wizarpos.emvsample.R;


public class GetMasterKey extends FuncActivity {
    private Button buttonBack;
    private Button buttonMore;
    private TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getmasterkey);
        final TextView comp1 =  findViewById(R.id.massterKey);
        comp1.setText(decrypt());
        final TextView comp2 =  findViewById(R.id.comp2);
        buttonBack = (Button)findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textTitle = (TextView)findViewById(R.id.tAppTitle);
        textTitle.setText("Master Key");

        comp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(comp1.getText().toString());
                    Toast.makeText(GetMasterKey.this,"Copied", Toast.LENGTH_LONG).show();
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("WordKeeper",comp1.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(GetMasterKey.this,"Copied", Toast.LENGTH_LONG).show();
                }
            }
        });

        comp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(comp2.getText().toString());
                    Toast.makeText(GetMasterKey.this,"Copied", Toast.LENGTH_LONG).show();

                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("WordKeeper",comp2.getText().toString());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(GetMasterKey.this,"Copied", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private String decrypt(){
        String clearMaster = "";
        try {
            clearMaster = GtmsKeyProcessor.getMasterKey(appState.nibssData.getKeyHolder().getMasterKey(), true);
            Log.i("okh", clearMaster);
        }catch (Exception e ){

        }

        return clearMaster;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        requestFuncMenu();
    }
}
