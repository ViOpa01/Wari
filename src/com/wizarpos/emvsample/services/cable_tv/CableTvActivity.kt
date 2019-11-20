package com.wizarpos.emvsample.services.cable_tv

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.iisysgroup.payvice.startimes.activites.StartimesActivity
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.services.cable_tv.multichoice.activities.MtChoiceActivity
import com.wizarpos.emvsample.services.discos.activities.DiscosActivity
import com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity
import com.wizarpos.util.VasServices
import kotlinx.android.synthetic.main.activity_cable_tv.*
import kotlinx.android.synthetic.main.activity_cable_tv.toolbar

class CableTvActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cable_tv)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dstv.setOnClickListener(this)
        gotv.setOnClickListener(this)
        startimes.setOnClickListener(this)



    }

    //Opens the selectedactivity based on the users selection
    override fun onClick(v: View?) {
        var intent = Intent(this, MeterValidationActivity::class.java)
        when (v!!.id) {

            R.id.dstv -> {
                intent = Intent(this, MtChoiceActivity::class.java)
                intent.putExtra(DiscosActivity.SERVICE, VasServices.DSTV)
            }

            R.id.gotv -> {
                intent = Intent(this, MtChoiceActivity::class.java)
                intent.putExtra(DiscosActivity.SERVICE, VasServices.GOTV)
            }

            R.id.startimes -> {
                intent = Intent(this, StartimesActivity::class.java)
                intent.putExtra(DiscosActivity.SERVICE, VasServices.STARTIMES)
            }


        }

        startActivity(intent)
    }



        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when (item?.itemId){
                android.R.id.home -> {
                    onBackPressed()
                    return true
                }
            }
            return false
        }





        override fun onBackPressed() {
            finish()
            super.onBackPressed()
        }
}
