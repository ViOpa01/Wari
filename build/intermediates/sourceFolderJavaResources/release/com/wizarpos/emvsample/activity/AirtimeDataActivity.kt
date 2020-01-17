package com.wizarpos.emvsample.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncActivity.appState
import kotlinx.android.synthetic.main.activity_selection.*

class AirtimeDataActivity : AppCompatActivity() {


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        FuncActivity.appState.data = false
        airtime.setOnClickListener {setUpAirtime() }
        data.setOnClickListener{ setUpData() }
        Log.d(TAG,"appState.data >>>" +(appState.data).toString())
    }

    private fun setUpData() {
        Log.d(TAG,"appState.data >>>" +(appState.data).toString())
        val intent = Intent(this@AirtimeDataActivity, DataActivity::class.java)
        startActivity(intent)
    }

    private fun setUpAirtime() {
        Log.d(TAG, "appState.data  >>>" +(appState.data).toString())
        val intent = Intent(this@AirtimeDataActivity, AirtimeVasActivity::class.java)
        startActivity(intent)
    }



//    private fun setUpData() {
//        val intent = Intent(this@AirtimeDataActivity, DataActivity::class.java)
//        startActivity(intent)
//    }
//
//    private fun setUpAirtime() {
//        val intent = Intent(this@AirtimeDataActivity, AirtimeVasActivity::class.java)
//        startActivity(intent)
//    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    companion object{
        private  final var  TAG: String="AirtimeDataActivity"
    }
}
