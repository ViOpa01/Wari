package com.wizarpos.emvsample.history_summary

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.google.gson.Gson
//import com.iisysgroup.gtb_agency_banking.App
//import com.iisysgroup.gtb_agency_banking.R
//import com.iisysgroup.gtb_agency_banking.db.detailed.EodData
//import com.iisysgroup.gtb_agency_banking.history_summary.model.HistoryModel
//import com.iisysgroup.gtb_agency_banking.history_summary.service.HistoryService
//import com.iisysgroup.gtb_agency_banking.login.Helper
//import com.iisysgroup.gtb_agency_banking.payments_menu.BasePaymentActivity
//import com.iisysgroup.gtb_agency_banking.payments_menu.RefundActivity
//import com.iisysgroup.gtb_agency_banking.transaction_viewpager_fragments.TransactionHistory
//import com.iisysgroup.gtb_agency_banking.utils.SharedPreferenceUtils
//import com.iisysgroup.gtb_agency_banking.utils.stringValue
//import com.iisysgroup.poslib.utils.Utilities
//import kotlinx.android.synthetic.main.activity_eod.*
//import kotlinx.android.synthetic.main.activity_transaction_history.*
//import kotlinx.android.synthetic.main.activity_transaction_history.historyProgressBar
//import kotlinx.android.synthetic.main.activity_transaction_history.toolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.jetbrains.anko.alert
import java.util.ArrayList

class TransactionHistory : AppCompatActivity() {

    enum class HISTORY_TYPE {
        WALLET, CARD, ALL
    }
//
//
//    private lateinit var walletHistory : HistoryModel
//
//    private lateinit var historyAdapter : HistoryAdapter
//
//
//    val  initDb by lazy {
//        (application as App ).TransactionDb
//    }
//
//
//    val  initVasDb by lazy {
//        (application as App ).vasDb
//    }
//
//    val  initEodDb by lazy {
//        (application as App ).eodDb
//    }
//
//    internal var mPassword: EditText? = null
//    internal var mPinHolder: FrameLayout? = null
//    internal var mFragmentHolder: FrameLayout? = null
//    internal var mSubmit: Button? = null
//
//
//    private fun checkPassword(): Boolean {
//        val password = mPassword?.getText().toString()
//
////        Log.d(TAG, "checkPassword:   $password")
//        if (password.trim({ it <= ' ' }) == "4839") {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(mPassword?.getWindowToken(), 0)
//            //            mFragmentHolder?.setVisibility(View.VISIBLE)
//            mPinHolder?.setVisibility(View.GONE)
//            supportActionBar!!.title = "Transaction History"
//
////            Log.d(TAG, "checkPassword:True   $password")
//
//            return true
//        } else {
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(mPassword?.getWindowToken(), 0)
//            mPassword?.setError("Wrong password")
//            return false
//
//        }
//    }
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_transaction_history)
//
//        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.title="Admin Password"
//
//
//        fetchData()
//
//
//
//
//        mSubmit = findViewById(R.id.submit_pin)
////        mPinHolder = findViewById(R.id.frame_password_layout)
//        mPassword = findViewById(R.id.password_text)
//
//
//        transactionHistory.visibility=(View.GONE)
//
//        linearLayout.visibility = View.VISIBLE
//
//
//        mSubmit!!.setOnClickListener(View.OnClickListener {
//            if (checkPassword()) {
//                transactionHistory.visibility=(View.VISIBLE)
//                linearLayout.visibility = View.GONE
//
//
//            }
//        })
//
//        mPassword!!.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_GO) {
//                if (checkPassword()) {
//                    transactionHistory.visibility = View.VISIBLE
//                    linearLayout.visibility = View.GONE
//
//                }
//            }
//            true
//        })
//
//    }
//
//    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.history_menu, menu)
//        return true
//    }*/
//
//
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId){
//            R.id.historyCard -> {
//                fetchData(TransactionHistory.HISTORY_TYPE.CARD)
//                return true
//            }
//
//            R.id.historyWallet -> {
//                fetchData(TransactionHistory.HISTORY_TYPE.WALLET)
//                return true
//            }
//            android.R.id.home -> {
//               onBackPressed()
//                return true
//            }
//
//        }
//        return false
//    }
//
//    private fun fetchData(historyType : TransactionHistory.HISTORY_TYPE = TransactionHistory.HISTORY_TYPE.CARD) {
//        historyAdapter = HistoryAdapter(historyType)
//
//        historyProgressBar.visibility = View.VISIBLE
//
//        transactionHistory.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
//
//        when (historyType){
//            TransactionHistory.HISTORY_TYPE.WALLET -> {
//                transactionHistory.adapter = historyAdapter
//                GlobalScope.launch {
//                    val walletId = SharedPreferenceUtils.getPayviceWalletId(this@TransactionHistory)
//
//                    walletHistory =  HistoryService.getInstance().getWalletHistory(walletId).await()
//
//
//                    if (walletHistory.error){
//                        GlobalScope.launch(Dispatchers.Main){
//                            alert {
//                                title = "Error"
//                                message = "Error retrieving history for wallet Id $walletId"
//                            }.show()
//                        }
//                    }
//
//                    GlobalScope.launch(Dispatchers.Main){
//                        historyProgressBar.visibility = View.GONE
//                        //historyAdapter.setEodDatas(walletHistory)
//                    }
//
//                }
//            }
//
//            TransactionHistory.HISTORY_TYPE.CARD -> {
//                val adapter = HistoryAdapter(TransactionHistory.HISTORY_TYPE.CARD)
//                transactionHistory.adapter = adapter
//
////                val result = (application as App).db.EodDataDao.findAll()
//
//                val result = initEodDb.getAllEodTransactions()
//                result.observe({lifecycle}){
//
//
//                    Log.i("Here CardVas data size >>>>", it?.size.toString()  )
//
//                    it?.forEach {
//
//                        Log.i("Here CardVas response >>>>", Gson().toJson(it)   )
////                        transactionRef == vasTransactionResult.
////                        if (!((vasTransactionResult?.RRN).isNullOrEmpty())){
////
////
////
////                        }else {
////                            if (!(vasTransactionResult?.requestId).isNullOrEmpty()){
////
////                            }
////                        }
//                    }
//
//                    historyProgressBar.visibility = View.GONE
//                    adapter.setEodDatas(it!!)
//                }
//            }
//
//            TransactionHistory.HISTORY_TYPE.ALL -> {
//
//            }
//        }
//    }
//
//
//    internal inner class HistoryAdapter(val historyType : TransactionHistory.HISTORY_TYPE) : RecyclerView.Adapter<HistoryViewHolder>() {
//        private var EodDatas = ArrayList<EodData>()
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
//
//            val view = LayoutInflater.from(this@TransactionHistory).inflate(R.layout.individual_transaction_history, parent, false)
//            return HistoryViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
//            when (historyType){
//                TransactionHistory.HISTORY_TYPE.WALLET -> {
//                    val result = walletHistory.data[position]
//
//                    holder.transaction_amount.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result.amount.replace(".","").trim { it <= ' ' }.toLong().toLong()).substring(1)
//                    holder.beneficiary_name.text = result.date
//                    holder.transaction_type.text = result.service
//                    holder.transaction_id.text = result.ref
//
//                    holder.itemView.tag = result
//
//                }
//
//                TransactionHistory.HISTORY_TYPE.CARD -> {
//                    val result = EodDatas[position]
//
//                    holder.transaction_amount.text = Utilities.parseLongIntoNairaKoboString(result.amount!!.replace(".","").trim { it <= ' ' }.toLong()).substring(1)
//                    holder.beneficiary_name.text = if (result.isApproved())  "Approved" else "Declined"
//                    holder.transaction_type.text = result.type
//                    holder.transaction_id.text = result.transactionRef
//
//                    holder.itemView.tag = result
//                }
//            }
//
//
//        }
//
//        fun setEodDatas(EodDatas: List<EodData>?) {
//            EodDatas?.let {
//                this.EodDatas.clear()
//                this.EodDatas.addAll(it)
//                notifyDataSetChanged()
//            }
//        }
//
//        fun setWalletEodDatas(EodDatas : List<HistoryModel>){
//
//        }
//
//        override fun getItemCount(): Int {
//            return  EodDatas.size
//        }
//    }
//
//
//    internal inner class HistoryViewHolder(view_that_is_passed: View) : RecyclerView.ViewHolder(view_that_is_passed), View.OnClickListener {
//        var transaction_type: TextView
//        var beneficiary_name: TextView
//        var transaction_amount: TextView
//        var transaction_id: TextView
//        var view_details: Button
//
//        init {
//            view_details = itemView.findViewById(R.id.view_details_btn)
//            transaction_type = itemView.findViewById(R.id.history_transaction_type)
//            beneficiary_name = itemView.findViewById(R.id.history_beneficiary_name)
//            transaction_amount = itemView.findViewById(R.id.history_transaction_amount)
//            transaction_id = itemView.findViewById(R.id.history_transaction_id)
//
//            view_details.setOnClickListener(this)
//            view_that_is_passed.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View) {
//            val EodData = itemView.tag as EodData
//
//            val intent = Intent(this@TransactionHistory, RefundActivity::class.java)
//
//
//            intent.putExtra(Helper.TRANSACTION_REF, EodData.transactionRef)
//
//
//            var type=EodData.type
//            var tranType=EodData.transactionType
//            var speTransaction=EodData.specificTransaction
//            Log.i("type>>>>>>>>",type)
//            Log.i("Specific transaction >>>>>>>>",speTransaction)
//
//            Log.i("tranType>>>>>>>>",tranType)
//
//                        intent.putExtra(Helper.TYPE, type)
//
//                        intent.putExtra(Helper.TRANSACTION_TYPE, tranType)
//            intent.putExtra(Helper.SPECIFIC_TRANSACTION, speTransaction)
//
//            startActivity(intent)
//        }
//    }
}
