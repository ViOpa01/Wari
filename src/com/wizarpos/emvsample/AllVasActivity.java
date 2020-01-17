package com.wizarpos.emvsample;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.wizarpos.emvsample.activity.AirtimeDataActivity;
import com.wizarpos.emvsample.activity.FuncActivity;
import com.wizarpos.emvsample.services.cable_tv.CableTvActivity;
import com.wizarpos.emvsample.services.discos.activities.DiscosActivity;

import static com.wizarpos.util.AppUtil.resetAllServicesStates;

public class AllVasActivity extends  FuncActivity {

    private ImageView ImageViewAirtime = null;

    private ImageView ImageViewElectricity= null;
    private ImageView ImageViewCableTv= null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_all_vas);
        initToolbar();

        new FuncActivity();

        ImageViewAirtime = findViewById(R.id.bFunc_Airtime);
        ImageViewAirtime.setOnClickListener(new ClickListener());

        ImageViewElectricity = findViewById(R.id.imgVwElectricity);
        ImageViewElectricity.setOnClickListener(new ClickListener());

        ImageViewCableTv= findViewById(R.id.imgVwCableTv);
        ImageViewCableTv.setOnClickListener(new ClickListener());
    }



    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("VAS");
    }


    public class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {


                case R.id.bFunc_Airtime:
                    startActivity(new Intent(AllVasActivity.this, AirtimeDataActivity.class));
                    //finish();
                    break;

                case R.id.imgVwElectricity:
                    startActivity(new Intent(AllVasActivity.this, DiscosActivity.class));
                    break;

                case R.id.imgVwCableTv:
                    startActivity(new Intent(AllVasActivity.this, CableTvActivity.class));
                    //finish();
                    break;

            }

        }

    }



    @Override
    protected void onResume()
    {
        resetPurchase();
        if(debug) Log.d(APP_TAG, "FuncMenuActivity onResume");
        super.onResume();
        resetAllServicesStates();
    }

    @Override
    protected void onStop()
    {
        if(debug)Log.d(APP_TAG, "FuncMenuActivity onStop");
        super.onStop();
        resetAllServicesStates();
    }


    @Override
    public void onBackPressed() {
        finish();
//        super.onBackPressed();
    }

//    @Override
//    public void onBackPressed(){
//        go2Idle();
//    }




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

}
