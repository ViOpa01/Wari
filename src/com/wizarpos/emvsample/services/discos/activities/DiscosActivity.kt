package com.wizarpos.emvsample.services.discos.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.wizarpos.emvsample.R
import com.wizarpos.util.VasServices
import kotlinx.android.synthetic.main.electric_companies.*

class DiscosActivity : AppCompatActivity(),View.OnClickListener {
    companion object{
        const  val SERVICE = "service"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discos)

        aedc.setOnClickListener(this)
        eedc.setOnClickListener(this)
        ekedc.setOnClickListener(this)
        ibedc.setOnClickListener(this)
        iedc.setOnClickListener(this)
        phdc.setOnClickListener(this)


    }

    //Opens Electricity activity with the required information
    override fun onClick(v: View?) {
      val intent = Intent(this,MeterValidationActivity::class.java)
        when(v!!.id){

            R.id.aedc->{

                intent.putExtra(SERVICE,VasServices.ABUJA_ELECTRIC)
            }

            R.id.eedc->{
                intent.putExtra(SERVICE,VasServices.ENUGU_ELECTRIC)
            }

            R.id.ekedc->{
                intent.putExtra(SERVICE,VasServices.EKO_ELECTRIC)
            }

            R.id.ibedc->{
                intent.putExtra(SERVICE,VasServices.IBADAN_ELECTRIC)
            }
            R.id.iedc->{
                intent.putExtra(SERVICE,VasServices.IKEJA_ELECTRIC)
            }
            R.id.phdc->{
                intent.putExtra(SERVICE,VasServices.PORTHARCOURT_ELECTRIC)
            }



        }




        startActivity(intent)
    }
}
