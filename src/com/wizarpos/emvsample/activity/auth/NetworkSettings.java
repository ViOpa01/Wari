package com.wizarpos.emvsample.activity.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.FuncActivity;

public class NetworkSettings extends FuncActivity {
    SharedPreferences sharedPreferences;
    EditText ip, port;
    private Button buttonBack;
    CheckBox ssl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_settings);
        buttonBack = (Button)findViewById(R.id.btn_back);
        ssl = findViewById(R.id.ssl);

        sharedPreferences = getSharedPreferences(netWork_pref, Context.MODE_PRIVATE);
        ip = findViewById(R.id.ip);
        port = findViewById(R.id.port);
        getConfig();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void saveConfig(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(ip.getText().toString().isEmpty()){
            ip.setError("Required field");
            return;
        }
        if(port.getText().toString().isEmpty()){
            port.setError("Required field");
            return;
        }
        editor.putString("ip", ip.getText().toString());
        editor.putString("port", port.getText().toString());
        editor.putBoolean("ssl", ssl.isChecked());
        editor.commit();
        Toast.makeText(this,"Settings Saved", Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    public void getConfig(){
        String ipS = sharedPreferences.getString("ip", "196.6.103.73");
        String ports = sharedPreferences.getString("port", "5043");
//        String ipS = sharedPreferences.getString("ip", "197.253.19.78");
//        String ports = sharedPreferences.getString("port", "5001");
        Boolean sslStatus = sharedPreferences.getBoolean("ssl", true);
        ssl.setChecked(sslStatus);
        ip.setText(ipS);
        port.setText(ports);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        requestFuncMenu();
        exit();
    }

    @Override
    protected void onCancel() {
        super.onCancel();
        onBackPressed();
    }
}
