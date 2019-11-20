package com.wizarpos.emvsample;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.wizarpos.emvsample.activity.AirtimeDataActivity;
import com.wizarpos.emvsample.activity.FuncActivity;
import com.wizarpos.emvsample.activity.FuncMenuActivity;
import com.wizarpos.emvsample.services.cable_tv.CableTvActivity;
import com.wizarpos.emvsample.services.discos.activities.DiscosActivity;

class VasActivity extends FuncActivity {

    private ImageView ImageViewAirtime = null;

    private ImageView ImageViewElectricity= null;
    private ImageView ImageViewCableTv= null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_func_menu);
        initToolbar();

        new FuncActivity();

        ImageViewAirtime = findViewById(R.id.bFunc_Airtime);
        ImageViewAirtime.setOnClickListener(new VasActivity.ClickListener());

        ImageViewElectricity = findViewById(R.id.imgVwElectricity);
        ImageViewElectricity.setOnClickListener(new VasActivity.ClickListener());

        ImageViewCableTv= findViewById(R.id.imgVwCableTv);
        ImageViewCableTv.setOnClickListener(new VasActivity.ClickListener());
        }



    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("WARI");
    }


    public class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {


                case R.id.bFunc_Airtime:
                    startActivity(new Intent(VasActivity.this, AirtimeDataActivity.class));
                    //finish();
                    break;

                case R.id.imgVwElectricity:
                    startActivity(new Intent(VasActivity.this, DiscosActivity.class));
                    break;

                case R.id.imgVwCableTv:
                    startActivity(new Intent(VasActivity.this, CableTvActivity.class));
                    //finish();
                    break;

        }

        }

        }

        }
