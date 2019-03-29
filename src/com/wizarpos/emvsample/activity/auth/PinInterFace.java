package com.wizarpos.emvsample.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.activity.FuncActivity;


public class PinInterFace extends FuncActivity {
    EditText password;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_interface);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        password.requestFocus();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnConfirm();
            }
        });
    }

    public void btnConfirm(){
       if(password != null){
           if(password.getText().toString().equals("1414")){
               Log.i("okh", "Passed");
               startActivity(new Intent(PinInterFace.this, EodMenu.class));
               finish();
           }else{
               Toast.makeText(PinInterFace.this,"Authentication failed", Toast.LENGTH_LONG).show();
               btnCancel(null);
           }
       }else{
           Log.i("okh",  "password is null");
       }

    }

    public void btnClear(View view){
        password.setText("");
        password.requestFocus();
    }

    public void btnCancel(View view){
        requestFuncMenu();
        exit();
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
