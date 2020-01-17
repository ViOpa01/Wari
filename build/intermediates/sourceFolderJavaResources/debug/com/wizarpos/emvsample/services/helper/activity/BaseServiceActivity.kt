package com.iisysgroup.payvice.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.google.gson.Gson
//import com.iisysgroup.payvice.activities.BaseActivity
//import com.iisysgroup.payvice.R
//import com.iisysgroup.payvice.entities.Service
//import com.iisysgroup.payvice.util.Helper
//import com.iisysgroup.payvice.util.HistoryAdapter
//import com.iisysgroup.payvice.util.SingleImageTitleObject
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.services.helper.activity.BaseActivity
import com.wizarpos.util.Service
import com.wizarpos.util.SingleImageTitleObject
import org.jetbrains.anko.longToast
import java.util.*

abstract class BaseServiceActivity : BaseActivity() {

    protected lateinit var service: Service
    protected var product: Service.Product? = null

//    private var historyList: MutableList<HistoryAdapter.History> = ArrayList()
//    private lateinit var historyAdapter: HistoryAdapter


    protected var encryptedUserPin = ""


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initControls()
    }


    protected open fun initControls() {
        getHistoryListView()?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        historyAdapter = HistoryAdapter(this, historyList)
//        getHistoryListView()?.adapter = historyAdapter

        getSubTitleView()?.text = service.name
        getServiceImageView()?.setImageResource(service.icon)

//        previousRecharge()
    }

    //This method pops up the list of Products  such as VTU data and Pin
    protected fun selectProduct() {
        val networkDialog = BottomSheetDialog(this)

        Log.d ("Service>>>>> ", "Here")

        val view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_list_layout, null, false)
        networkDialog.setContentView(view)

        val titleView = view.findViewById<TextView>(R.id.titleText)
        titleView.text = String.format("Select %s Product", service)

        val listView = view.findViewById<ListView>(android.R.id.list)
        val itemList = ArrayList<SingleImageTitleObject>()

        for (product in service.products) {
            itemList.add(SingleImageTitleObject(product.name, service.icon))
        }

        listView.adapter = SingleImageTitleObject.SingleImageTitleAdapter(itemList, this,
                R.layout.bottom_sheet_list_item)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//             Log.d("Product>>>>>>>", Gson().toJson(service.products[position]))

            if (service.products[position].proxyCode == "MTNPIN" || service.products[position].proxyCode == "ETISALATPIN"){
                   longToast("service currently not available")
            }else{
            onSelectProduct(service.products[position])
            }
            networkDialog.dismiss()
        }

        networkDialog.show()
    }

//    private fun previousRecharge() {
//        val historySerial = Helper.getPreference(this, Helper.HISTORY_SERIAL, "")
//
//        if (!historySerial.trim { it <= ' ' }.isEmpty()) {
//            val tempList = HistoryAdapter.processRecords(historySerial)
//            historyList.clear()
//            Thread(Runnable {
//                for (history in tempList) {
//                    if (historyList.size > 2) break
//                    for (product in service.products) {
//                        if (history.product == product.requestCode) {
//                            historyList.add(history)
//                            break
//                        }
//                    }
//                }
//
//                if (historyList.isEmpty()) {
//                    hideHistory()
//                } else {
//                    historyAdapter.notifyDataSetChanged()
//                }
//            }).start()
//
//
//        } else {
//            hideHistory()
//        }
//
//    }

    protected fun showProgressDialog(show: Boolean, message: String = "Processing...") {
        progressDialog?.dismiss()

        if (show) {
            progressDialog = ProgressDialog.show(this, title, message, true)
        }
    }


    private fun hideHistory() {
        getHistoryLayout()?.visibility = View.GONE
    }


    protected abstract fun getHistoryLayout(): View?
    protected abstract fun getHistoryListView(): RecyclerView?
    protected abstract fun getSubTitleView(): TextView?
    protected abstract fun getServiceImageView(): ImageView?
    protected abstract fun onSelectProduct(product: Service.Product)
}