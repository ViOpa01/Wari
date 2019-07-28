package com.wizarpos.emvsample.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.wizarpos.emvsample.R
import kotlinx.android.synthetic.main.activity_data.*
class DataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nine_mobile.setOnClickListener { setUpNineMobile() }

        glo.setOnClickListener { setUpGloData() }

        mtn.setOnClickListener { setUpMtnData() }

        airtel.setOnClickListener { setUpAirtelData() }

    }

    private fun setUpAirtelData() {
        val intent = Intent(this@DataActivity, AllData::class.java)
        intent.putExtra(AllData.KEYS.TYPE_OF_DATA_KEY, AllData.KEYS.DATA_TYPE.AIRTEL)
        startActivity(intent)
    }

    private fun setUpMtnData(){
        val intent = Intent(this@DataActivity, AllData::class.java)
        intent.putExtra(AllData.KEYS.TYPE_OF_DATA_KEY, AllData.KEYS.DATA_TYPE.MTN)
        startActivity(intent)
    }

    private fun setUpGloData() {
        val intent = Intent(this@DataActivity, AllData::class.java)
        intent.putExtra(AllData.KEYS.TYPE_OF_DATA_KEY, AllData.KEYS.DATA_TYPE.GLO)
        startActivity(intent)
    }

    private fun setUpNineMobile() {
        val intent = Intent(this@DataActivity, AllData::class.java)
        intent.putExtra(AllData.KEYS.TYPE_OF_DATA_KEY, AllData.KEYS.DATA_TYPE.ETISALAT)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                FuncActivity.appState.data = false
                onBackPressed()
                return true
            }
        }
        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        FuncActivity.appState.data = false

    }
}
