package com.wizarpos.emvsample.history_summary

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Base64
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.bumptech.glide.Glide
import com.cloudpos.POSTerminal
import com.cloudpos.printer.Format
import com.cloudpos.printer.PrinterDevice
import com.google.gson.Gson
import com.iisysgroup.androidlite.models.TransactionModel
import com.iisysgroup.poslib.utils.Utilities
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.wizarpos.emvsample.MainApp
import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.FuncMenuActivity
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.db.detailed.EodData
import com.wizarpos.emvsample.history_summary.model.HistoryModel
import com.wizarpos.emvsample.history_summary.service.HistoryService
import com.wizarpos.util.SharedPreferenceUtils
import com.wizarpos.util.TimeUtils
import kotlinx.android.synthetic.main.activity_eod.*
import kotlinx.android.synthetic.main.activity_eod.merchantName
import kotlinx.android.synthetic.main.activity_print.*
import kotlinx.android.synthetic.main.eod_approved_history.*
import kotlinx.android.synthetic.main.eod_declined_history.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import org.jetbrains.anko.alert
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class EODActivity : AppCompatActivity() {


//    private lateinit var walletHistory : HistoryModel
//
//    private lateinit var historyAdapter : HistoryAdapter
//    private lateinit var historyAdapter2 : HistoryAdapter
//
//    private lateinit var toolbar : Toolbar
//    private lateinit var viewEOD : ScrollView
//
//    private lateinit var printTask: PrintTask
//
//    var approvedCount = 0
//    var declinedCount = 0
//    var approvedsumCount = 0
//    var declinedsumCount = 0
//    var image_url = "http://merchant.payvice.com/external-assets/logos/"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_eod)
//        toolbar = findViewById(R.id.toolbar)
//
//        val mTerminalId by lazy {
//            SharedPreferenceUtils.getTerminalId(this)
//        }
//        val bankPrefix  = mTerminalId.substring(0,4)
//        Glide.with(this).load(image_url+bankPrefix+".png").into(terminalOwnerLogo);
//
//        Log.d("ss","saa")
//        setSupportActionBar(toolbar)
//        terminalID.text = mTerminalId.toString()
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//        val date = Date()
//        EODDate.text = dateFormat.format(date)
//        fetchApprovedData()
//        fetchDeclinedData()
//
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        //menuInflater.inflate(R.menu.eod_menu, menu)
//        return true
//    }
//
//
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when (item?.itemId){
////            R.id.historyCard -> {
////                fetchApprovedData(TransactionHistory.HISTORY_TYPE.CARD)
////
////                return true
////            }
////
////            R.id.historyWallet -> {
////                fetchApprovedData(TransactionHistory.HISTORY_TYPE.WALLET)
////                return true
////            }
//
//              R.id.print -> {
//                  val relEod : RelativeLayout = findViewById(R.id.relEod)
//                  val bitmap = getBitmapFromView(relEod)
//                        printBitmap(bitmap, true)
//                return true
//            }
//
////            R.id.date -> {
////
////                return true
////            }
//        }
//        return false
//    }
//
//
//    fun getBitmapFromView(view: View): Bitmap {
//        //Define a bitmap with the same size as the view
//        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
//        //Bind a canvas to it
//        val canvas = Canvas(returnedBitmap)
//        //Get the view's background
//        val bgDrawable = view.background
//        if (bgDrawable != null)
//        //has background drawable , then draw it on the canvas
//            bgDrawable.draw(canvas)
//        else
//        //does not have background drawable, then draw white background on the canvas
//            canvas.drawColor(Color.WHITE)
//        // draw the view on the canvas
//        view.draw(canvas)
//        //return the bitmap
//        return returnedBitmap
//    }
//
//
//    private fun printBitmap(bitmap: Bitmap?, fitToPage: Boolean) {
//        if (bitmap == null || bitmap.width == 0 || bitmap.height == 0) {
//            return
//        }
//
//        Log.d("viewEOD", "original Bitmap Width:" + bitmap.width + "   Height:" + bitmap.height + "  FitToPage=" + fitToPage)
//
//        // PrintTask constructor
//        printTask = PrintTask()
//
//
//
//        // PrintCanvas constructor
//        val printCanvas = PrintCanvas()
//        val paint = Paint()
//
//
//
//
//        if (fitToPage && bitmap.width != PRINTER_WIDTH) {
//            val scaledHeight = PRINTER_WIDTH * bitmap.height / bitmap.width
//            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, PRINTER_WIDTH, scaledHeight, false)
//            Log.d("viewEOD", "scaled Bitmap Width:" + scaledBitmap.width + "   Height:" + scaledBitmap.height)
//            // Draw the bitmap
//            printCanvas.drawBitmap(scaledBitmap, paint)
//        } else {
//            // Draw the bitmap
//            printCanvas.drawBitmap(bitmap, paint)
//        }
//        // Set print canvas
//        printTask.setPrintCanvas(printCanvas)
//        // Set the amount of feed paper
//        printTask.addFeedPaper(100)
//        // Get the gray value of the task
//        printTask.setGray(130)
//
//        // Start print task
//        Printer.getInstance().startPrint(printTask, printerCallback)
//    }
//
//    private val printerCallback = PrinterCallback { arg0, arg1 ->
//
//    }
//
//    private fun fetchApprovedData(historyType : TransactionHistory.HISTORY_TYPE = TransactionHistory.HISTORY_TYPE.CARD) {
//
//        historyAdapter = HistoryAdapter(historyType)
//      //  historyAdapter2 = HistoryAdapter(historyType)
//
//        historyProgressBar.visibility = View.VISIBLE
//
//        transactionHistory.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
//      //  transactionHistory2.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
//
//        when (historyType){
//            TransactionHistory.HISTORY_TYPE.WALLET -> {
//                transactionHistory.adapter = historyAdapter
//                GlobalScope.launch {
//                    val walletId = SharedPreferenceUtils.getPayviceWalletId(this@EODActivity)
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
//                        //historyAdapter.setTransactionResults(walletHistory)
//                    }
//
//                }
//            }
//
//            TransactionHistory.HISTORY_TYPE.CARD -> {
//                Log.d("here", "here")
//                val adapter = HistoryAdapter(TransactionHistory.HISTORY_TYPE.CARD)
//                transactionHistory.adapter = adapter
//               // transactionHistory2.adapter = adapter
//
//                val result = (application as App).db.transactionResultDao.findAll()
//                result.observe({lifecycle}){
//                    historyProgressBar.visibility = View.GONE
//                    adapter.setTransactionResults(it!!)
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
//        private var transactionResults = ArrayList<TransactionResult>()
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
//
//            val view = LayoutInflater.from(this@EODActivity).inflate(R.layout.individual_eod_history, parent, false)
//            return HistoryViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
//            when (historyType){
//                TransactionHistory.HISTORY_TYPE.WALLET -> {
//                    val result = walletHistory.data[position]
//
//                    holder.transaction_amount.text = Utilities.parseLongIntoNairaKoboString(result.amount.toLong()).substring(1)
////                    holder.beneficiary_name.text = result.date
////                    holder.transaction_type.text = result.service
////                    holder.transaction_id.text = result.ref
//
//                    holder.itemView.tag = result
//
//                }
//
//                TransactionHistory.HISTORY_TYPE.CARD -> {
//
//                    val result = transactionResults[position]
//                    Log.d("amount", result.amount.toString())
//                    Log.d("position", position.toString())
//                    if (result.isApproved){
//                        approvedCount++
//                        approvedsumCount = (approvedsumCount+result.amount).toInt()
//                        holder.transaction_amount.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result.amount).substring(1)
//                        holder.transaction_time.text = TimeUtils.convertLongToTime(result.longDateTime)
//                        // holder.transaction_amount.text = result.amount.toString()
//                        holder.beneficiary_RRN.text = result.RRN
//                        Log.d("count", approvedCount.toString())
//                        //holder.transaction_id.text = result.PAN
//                        approvedValue.text = approvedCount.toString()
//                        approvedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1)
//                        holder.itemView.tag = result
//                    }
//                    else{
//                        holder.transaction_amount.visibility = View.GONE
//                        holder.transaction_time.visibility = View.GONE
//                        holder.beneficiary_RRN.visibility = View.GONE
//                        holder.eodrel.visibility = View.GONE
//                    }
////                    else{
////                        declinedCount++
////                        declinedsumCount = (declinedsumCount+result.amount).toInt()
////                        holder.transaction_amount.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result.amount).substring(1)
////                        holder.transaction_time.text = TimeUtils.convertLongToTime(result.longDateTime)
////                        // holder.transaction_amount.text = result.amount.toString()
////                        holder.beneficiary_RRN.text = result.RRN
////                        Log.d("count", declinedCount.toString())
////                        //holder.transaction_id.text = result.PAN
////                        declinedValue.text = declinedCount.toString()
////                        declinedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)
////                        holder.itemView.tag = result
////                    }
//
//
//                }
//            }
//
//
//        }
//
//        fun setTransactionResults(transactionResults: List<TransactionResult>?) {
//            transactionResults?.let {
//                this.transactionResults.clear()
//                this.transactionResults.addAll(it)
//                notifyDataSetChanged()
//            }
//        }
//
//        fun setWalletTransactionResults(transactionResults : List<HistoryModel>){
//
//        }
//
//        override fun getItemCount(): Int {
//            return  transactionResults.size
//        }
//    }
//
//    internal inner class HistoryViewHolder(view_that_is_passed: View) : RecyclerView.ViewHolder(view_that_is_passed), View.OnClickListener {
//        var transaction_time: TextView
//        var beneficiary_RRN: TextView
//        var transaction_amount: TextView
//        var eodrel: RelativeLayout
//
//       // var transaction_id: TextView
//
//        init {
//            transaction_time = itemView.findViewById(R.id.time)
//            beneficiary_RRN = itemView.findViewById(R.id.RRN)
//            transaction_amount = itemView.findViewById(R.id.amount)
//            eodrel = itemView.findViewById(R.id.eodrel)
//
//           // transaction_id = itemView.findViewById(R.id.history_transaction_id)
//
//            view_that_is_passed.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View) {
//            val transactionResult = itemView.tag as TransactionResult
//
//            val intent = Intent(this@EODActivity, RefundActivity::class.java)
//            intent.putExtra(BasePaymentActivity.TRANSACTION_RRN, transactionResult.RRN)
//            startActivity(intent)
//        }
//    }
//
//
//    private fun fetchDeclinedData(historyType2 : TransactionHistory.HISTORY_TYPE = TransactionHistory.HISTORY_TYPE.CARD) {
//
//        historyAdapter2 = HistoryAdapter(historyType2)
//        //  historyAdapter2 = HistoryAdapter(historyType)
//
//        historyProgressBar.visibility = View.VISIBLE
//
//        transactionHistory2.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
//        //  transactionHistory2.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
//
//        when (historyType2){
//            TransactionHistory.HISTORY_TYPE.WALLET -> {
//                transactionHistory2.adapter = historyAdapter2
//                GlobalScope.launch {
//                    val walletId = SharedPreferenceUtils.getPayviceWalletId(this@EODActivity)
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
//                        //historyAdapter.setTransactionResults(walletHistory)
//                    }
//
//                }
//            }
//
//            TransactionHistory.HISTORY_TYPE.CARD -> {
//                Log.d("here", "here")
//                val adapter2 = HistoryAdapter2(TransactionHistory.HISTORY_TYPE.CARD)
//                transactionHistory2.adapter = adapter2
//                // transactionHistory2.adapter = adapter
//
//                val result2 = (application as App).db.transactionResultDao.findAll()
//                result2.observe({lifecycle}){
//                    historyProgressBar.visibility = View.GONE
//                    adapter2.setTransactionResults2(it!!)
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
//    internal inner class HistoryAdapter2(val historyType2 : TransactionHistory.HISTORY_TYPE) : RecyclerView.Adapter<HistoryViewHolder2>() {
//        private var transactionResults2 = ArrayList<TransactionResult>()
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder2 {
//
//            val view2 = LayoutInflater.from(this@EODActivity).inflate(R.layout.individual_eod_history2, parent, false)
//            return HistoryViewHolder2(view2)
//        }
//
//        override fun onBindViewHolder(holder2: HistoryViewHolder2, position2: Int) {
//            when (historyType2){
//                TransactionHistory.HISTORY_TYPE.WALLET -> {
//                    val result2 = walletHistory.data[position2]
//
//                    holder2.transaction_amount2.text = Utilities.parseLongIntoNairaKoboString(result2.amount.toLong()).substring(1)
////                    holder.beneficiary_name.text = result.date
////                    holder.transaction_type.text = result.service
////                    holder.transaction_id.text = result.ref
//
//                    holder2.itemView.tag = result2
//
//                }
//
//                TransactionHistory.HISTORY_TYPE.CARD -> {
//
//                    val result2 = transactionResults2[position2]
//                    Log.d("amount", result2.amount.toString())
//                    Log.d("position", position2.toString())
//                    if (!result2.isApproved){
////                        approvedCount++
////                        approvedsumCount = (approvedsumCount+result.amount).toInt()
////                        holder2.transaction_amount2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result.amount).substring(1)
////                        holder2.transaction_time2.text = TimeUtils.convertLongToTime(result.longDateTime)
////                        // holder.transaction_amount.text = result.amount.toString()
////                        holder2.beneficiary_RRN2.text = result.RRN
////                        Log.d("count", approvedCount.toString())
////                        //holder.transaction_id.text = result.PAN
////                        approvedValue.text = approvedCount.toString()
////                        approvedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1)
////                        holder2.itemView.tag = result
////                    }
////                    else{
//                        declinedCount++
//                        declinedsumCount = (declinedsumCount+result2.amount).toInt()
//                        holder2.transaction_amount2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result2.amount).substring(1)
//                        holder2.transaction_time2.text = TimeUtils.convertLongToTime(result2.longDateTime)
//                        // holder.transaction_amount.text = result.amount.toString()
//                        holder2.beneficiary_RRN2.text = result2.RRN
//                        Log.d("count", declinedCount.toString())
//                        //holder.transaction_id.text = result.PAN
//                        declinedValue.text = declinedCount.toString()
//                        declinedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)
//                        holder2.itemView.tag = result2
//                    }
//                    else{
//                        holder2.transaction_amount2.visibility = View.GONE
//                        holder2.transaction_time2.visibility = View.GONE
//                        holder2.beneficiary_RRN2.visibility = View.GONE
//                        holder2.eodrel2.visibility = View.GONE
//                    }
//
//
//                }
//            }
//
//
//        }
//
//        fun setTransactionResults2(transactionResults2: List<TransactionResult>?) {
//            transactionResults2?.let {
//                this.transactionResults2.clear()
//                this.transactionResults2.addAll(it)
//                notifyDataSetChanged()
//            }
//        }
//
//        fun setWalletTransactionResults(transactionResults : List<HistoryModel>){
//
//        }
//
//        override fun getItemCount(): Int {
//            return  transactionResults2.size
//        }
//    }
//
//    internal inner class HistoryViewHolder2(view_that_is_passed: View) : RecyclerView.ViewHolder(view_that_is_passed), View.OnClickListener {
//        var transaction_time2: TextView
//        var beneficiary_RRN2: TextView
//        var transaction_amount2: TextView
//        var eodrel2: RelativeLayout
//
//        // var transaction_id: TextView
//
//        init {
//            transaction_time2 = itemView.findViewById(R.id.time2)
//            beneficiary_RRN2 = itemView.findViewById(R.id.RRN2)
//            transaction_amount2 = itemView.findViewById(R.id.amount2)
//            eodrel2 = itemView.findViewById(R.id.eodrel2)
//
//            // transaction_id = itemView.findViewById(R.id.history_transaction_id)
//
//            view_that_is_passed.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View) {
//            val transactionResult = itemView.tag as TransactionResult
//
//            val intent = Intent(this@EODActivity, RefundActivity::class.java)
//            intent.putExtra(BasePaymentActivity.TRANSACTION_RRN, transactionResult.RRN)
//            startActivity(intent)
//        }
//    }






    private lateinit var walletHistory: HistoryModel

    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyAdapter2: HistoryAdapter

    private lateinit var toolbar: Toolbar
    private lateinit var viewEOD: ScrollView


    var approvedCount = 0
    var declinedCount = 0
    var approvedsumCount = 0
    var declinedsumCount = 0
    var image_url = "http://merchant.payvice.com/external-assets/logos/"
    var allDeclined = ArrayList<EodData>()
    var allApproved = ArrayList<EodData>()
    var sDate: String? = null
    var sTerminalId: String? = null
    var dummy: Bitmap? = null
    var icon: Bitmap? = null
    var allTransactions: String = ""
    var starts: Long = 0L
    var ends: Long = 0L
    var start_Date : Long = 0L
    var end_Date : Long = 0L
    var _merchantName : String  = ""
    var shouldPrinterShow:Boolean=false

    internal var mPassword: EditText? = null
    internal var mPinHolder: FrameLayout? = null
    internal var mFragmentHolder: FrameLayout? = null

    internal var mSubmit: Button? = null

//    private val configData by lazy {
//        (application as MainApp).db.configDataDao
//    }


    val  initDb by lazy {
        (application as MainApp ).transactionDb
    }


    val  initVasDb by lazy {
        (application as MainApp ).vasDb
    }

    val  initEodDb by lazy {
        (application as MainApp ).eodDb
    }

//    var rowId= SecureStorage.retrieve(Helper.ROW_ID,"").toInt()


    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        Log.i("shouldPrinterShow", shouldPrinterShow.toString())


        if(shouldPrinterShow) {
//            menu?.clear()
            Log.i("shouldPrinterShow here ", shouldPrinterShow.toString())

            menu?.findItem(R.id.print)?.isVisible = shouldPrinterShow
            menu?.findItem(R.id.sort)?.isVisible = shouldPrinterShow

        }else{

            menu?.findItem(R.id.print)?.isVisible = shouldPrinterShow
            menu?.findItem(R.id.sort)?.isVisible = shouldPrinterShow
        }
        return super.onPrepareOptionsMenu(menu)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eod)
        toolbar = findViewById(R.id.toolbar)

        printerDevice = POSTerminal.getInstance(applicationContext).getDevice(
                "cloudpos.device.printer") as PrinterDevice


        mSubmit = findViewById(R.id.submit_pin)
//        mPinHolder = findViewById(R.id.frame_password_layout)
        mPassword = findViewById(R.id.password_text)


        relEod.visibility=(View.GONE)

        frame_password_layout_eod.visibility = View.VISIBLE


        mSubmit!!.setOnClickListener(View.OnClickListener {
            if (checkPassword()) {
                relEod.visibility=(View.VISIBLE)
                shouldPrinterShow=true
                frame_password_layout_eod.visibility = View.GONE


            }
        })

        mPassword!!.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                if (checkPassword()) {
                    relEod.visibility = View.VISIBLE
                    shouldPrinterShow=true
                    frame_password_layout_eod.visibility = View.GONE

                }
            }
            true
        })


        if (SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "") != "") {
            val bankNumber = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "").substring(0, 4) + ".png"
            Log.d("MainActivity", bankNumber)

            logo = SecureStorage.retrieve(Helper.BANK_LOGO, "")

            Log.d("Logo >>>>", logo)


            if (logo == "") {
                picassoImage = Picasso.get().load("http://www.merchant.payvice.com/external-assets/logos/$bankNumber")
            }
        } else {

            bankImage.setImageResource(R.drawable.wari_small)

        }




        val mTerminalId by lazy {
            SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER,"")
        }

        GlobalScope.launch {
            try {

//                merchantName.text= configData.get().getConfigData("52040").toString()
                merchantName.text= FuncMenuActivity.appState.terminalConfig.merchantName1


//                merchantName.text= SecureStorage.retrieve(Helper.ADDRESS,"")

            }catch(e:Throwable){

//                merchantName.text= configData.get().getConfigData("52040").toString()


            }
        }


//        if (mTerminalId.length == 1){
//            Toast("Terminal Id is incorrect or not set")
//        }
        val bankPrefix = mTerminalId.substring(0, 4)
        Glide.with(this).load(image_url + bankPrefix + ".png").into(terminalOwnerLogo)




        Log.d("ss", "saa")
        setSupportActionBar(toolbar)
        sTerminalId = mTerminalId.toString()
        terminalID.text = sTerminalId
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title="Admin Password"
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
//        s
        EODDate.text =dateFormat.format(date)
        val c: Calendar = Calendar.getInstance()
        val month = c.get(Calendar.MONTH)
        Log.i("xoxo", " Before -- Month " + month)

//        val dayOfTheMonth = c.get(Calendar.DAY_OF_MONTH)
//        val month = c.get(Calendar.MONTH)
//        c.add(Calendar.DAY_OF_MONTH, 1)
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)
        starts = c.timeInMillis
        ends = starts + (86400000 - 1000)


        Log.i("Yeah", " starts " + start_Date + " ends " + end_Date )

        Log.i("xoxo", " starts " + starts + " ends " + ends + " Before -- Month " + month + " now -- Month " + c.get(Calendar.MONTH))

        fetchApprovedData(TransactionHistory.HISTORY_TYPE.CARD, starts, ends)

        fetchDeclinedData(TransactionHistory.HISTORY_TYPE.CARD, starts, ends)




        Log.i("allData", "Declined" + allDeclined?.size)
        Log.i("allData", "Approved...." + allApproved?.size)


    }


    private fun checkPassword(): Boolean {
        val password = mPassword?.getText().toString()

//        Log.d(TAG, "checkPassword:   $password")
        if (password.trim({ it <= ' ' }) == "4839") {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mPassword?.getWindowToken(), 0)
            shouldPrinterShow=true
            invalidateOptionsMenu()
            supportActionBar!!.title="EOD"
            //            mFragmentHolder?.setVisibility(View.VISIBLE)
            mPinHolder?.setVisibility(View.GONE)
//            Log.d(TAG, "checkPassword:True   $password")

            return true
        } else {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(mPassword?.getWindowToken(), 0)
            mPassword?.setError("Wrong password")
            return false

        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.eod_menu, menu)
//        menu?.findItem(R.id.print)?.isVisible = shouldPrinterShow
//        menu?.findItem(R.id.sort)?.isVisible = shouldPrinterShow
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
//            R.id.historyCard -> {
//                fetchApprovedData(TransactionHistory.HISTORY_TYPE.CARD)
//
//                return true
//            }
//
//            R.id.historyWallet -> {
//                fetchApprovedData(TransactionHistory.HISTORY_TYPE.WALLET)
//                return true
//            }

            R.id.print -> {
//                val relEod : RelativeLayout = findViewById(R.id.relEod)
                val relEod: ScrollView = findViewById(R.id.scrollEod)

//                val bitmap = getBitmapFromView(relEod)
//                printBitmap(bitmap, true)
//
                Log.i("allData", "Declined" + allDeclined?.size)
                Log.i("allData", "Approved 222 " + allApproved?.size)


                var baseHeader: String = baseHeader()
                allTransactions = " \n \n $time             $RRN                $amount   \n  \n"
                var sAllDeclined: String = approvedTransactions(allApproved)
                allTransactions = " \n \n $time             $RRN                $amount   \n  \n"
                var sAllApproved: String = declinedTransactions(allDeclined)
//                var  sArrangeData : String = arrangeData(allDeclined)

                Log.i("Final", "" + baseHeader + sAllDeclined + sAllApproved)


                var img: Bitmap = (terminalOwnerLogo.getDrawable() as BitmapDrawable).bitmap
                icon = Bitmap.createScaledBitmap(img, (img.getWidth() / 2) as Int, (img.getHeight() / 2) as Int, true)

                printString(baseHeader + sAllDeclined + sAllApproved)
                return true
            }

            R.id.sort -> {

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)


                val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->


                    val currentYear = year.toString()
                    val currentMonth = (monthOfYear + 1).toString()
                    val currentDay = dayOfMonth.toString()

                    val c: Calendar = Calendar.getInstance()
                    val month = c.get(Calendar.MONTH)
                    c.set(year, month, dayOfMonth)
                    c.set(Calendar.HOUR_OF_DAY, 0)
                    c.set(Calendar.MINUTE, 0)
                    c.set(Calendar.SECOND, 0)
                    c.set(Calendar.MILLISECOND, 0)


                    starts = c.timeInMillis
                    ends = starts + (86400000 - 1000)

                    val dateFormat = SimpleDateFormat("yyyy/MM/dd")

                    sDate = (dateFormat.format(starts))

                    Log.i("xoxo", " starts " + starts + " ends " + ends + " Before -- Month " + month + " now -- Month " + c.get(Calendar.MONTH))




                    triggerTransactions(TransactionHistory.HISTORY_TYPE.CARD,starts,ends)

                }, year, month, day)

                dpd.show()




                return true
            }
        }
        return false
    }

    private fun triggerTransactions(historyType : TransactionHistory.HISTORY_TYPE , startDate : Long, endDate : Long) {
        approvedCount = 0
        declinedCount = 0
        approvedsumCount = 0
        declinedsumCount = 0
        EODDate.text =sDate
        approvedValue.text = approvedCount.toString()
        approvedValue2.text= "N"+ Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1)

        declinedValue.text = declinedCount.toString()
        declinedValue2.text = "N"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)

        fetchApprovedData(TransactionHistory.HISTORY_TYPE.CARD, startDate, endDate)


        fetchDeclinedData(TransactionHistory.HISTORY_TYPE.CARD, startDate, endDate)

//        val intent=Intent(this,EODActivity::class.java)

//        startActivity(intent)





    }
//    private fun printString( logo: Bitmap?, allTransactions : String) {
//
//        printTask = PrintTask()
//
//
//
//        // PrintCanvas constructor
//        val printCanvas = PrintCanvas()
//        val paint = Paint()
//
//
//
//        paint.setTextSize(20f);
//        printCanvas.drawBitmap(icon, paint)
//
//        // Draw the bitmap
//        printCanvas.drawText(allTransactions, paint)
//        // Set print canvas
//        printTask.setPrintCanvas(printCanvas)
//        // Set the amount of feed paper
//        printTask.addFeedPaper(100)
//        // Get the gray value of the task
//        printTask.setGray(130)
//
//        // Start print task
//        Printer.getInstance().startPrint(printTask, printerCallback)
//    }


    private val handler = Handler()
    private val myRunnable = Runnable {
        //            txt.setText(str);
    }
    private var format: Format? = null
    private var format2: Format? = null
    private var printerDevice: PrinterDevice? = null
    internal var resourceId: Int = 0
    internal var logo:String  = ""
    private var picassoImage: RequestCreator? = null


    fun  printString( allTransactions : String)
    {
        try {

//                str = context.getString(R.string.openingPrint) + "\n";
            handler.post(myRunnable)
            printerDevice!!.open()
            format = Format()
            format2 = Format()
//                str += context.getString(R.string.printerOpenSuc) + "\n";
            handler.post(myRunnable)
            if (printerDevice!!.queryStatus() == PrinterDevice.STATUS_OUT_OF_PAPER) {
                //                    str += context.getString(R.string.queryStatus) + "\n";
                handler.post(myRunnable)
                Toast.makeText(this, "You have run out of paper ", Toast.LENGTH_SHORT).show()
            }
//            else if (printerDevice.queryStatus() == printerDevice.STATUS_PAPER_EXIST) {

//                handler.post(myRunnable);

            //                    str += context.getString(R.string.statusNor) + "\n";
            // Bitmap bitmap = encode("0123456789abc", 400, 90);


            format!!.setParameter("align", "center")
            format!!.setParameter("bold", "true")

            format2!!.setParameter("align", "left")
            format2!!.setParameter("bold", "true")
            val isElectricityPurchase = false
            val isPrepaidPurchase = false
            val isPostpaidPurchase = false
            val isCableTV = false
            var vastransactionFailed = false
            val didVasTransaction = false



            try {


                val bankLogoName = ""
                var bitmap: Bitmap? = null
                val stream = ByteArrayOutputStream()

//                if (transactionModel.VasDetails.logo != 0) {
//                    resourceId = transactionModel.VasDetails.logo
//                    bankImage.setImageResource(resourceId)
//                    Log.d("Yeah >>>", resourceId.toString())
//                    val drawable = resources.getDrawable(resourceId)
//                    bitmap = (drawable as BitmapDrawable).bitmap
//
//                } else {

                    Log.d("logo.equals(\"\") >>>", (logo == "").toString())
                    if (logo == "") {
                        picassoImage!!.into(bankImage)
                        //                        bitmap= picassoImage.get();
                        //                        Log.d("picassoImage.get() ",picassoImage.get().toString());
                        var drawable: Drawable? = null
                        drawable = bankImage.getDrawable()
                        Log.d("logo drawable  >>>", (drawable == null).toString())

                        bitmap = (drawable as BitmapDrawable).bitmap

                        FuncMenuActivity.appState.bankLogo = bitmap
                        bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                        val b = stream.toByteArray()
                        val _sBankLogo = Base64.encodeToString(b, Base64.DEFAULT)
                        Log.d("logo string  >>>", _sBankLogo)

                        val status = SecureStorage.store(Helper.BANK_LOGO, _sBankLogo)

                        Log.d("logo stored status     >>>", status.toString())

                        val `val` = SecureStorage.retrieve(Helper.BANK_LOGO, "")


                        Log.d("logo retrived   >>>", `val`)


                    } else {
                        val imageAsBytes = Base64.decode(logo.toByteArray(), Base64.DEFAULT)
                        bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
                        bankImage.setImageBitmap(bitmap)


                    }


//                }




                bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, stream)

                printerDevice!!.printBitmap(format, bitmap)
            } catch (e: Exception) {
                resourceId = R.drawable.wari_small
            }

            printerDevice?.printlnText("\n")


            val receiptMap = LinkedHashMap<String, String>()


//               if(transactionModel.getVasDetails() != null ) {
            printerDevice!!.printlnText(format2, allTransactions)

            printerDevice!!.close()


        } catch (e: Throwable) {
        }


    }





















    var  time : String= "TIME"
    var  RRN : String= "TRANS ID"
    var  amount : String= "AMOUNT"





    fun baseHeader() : String{

        var baseData : String =     "   \n \n        ${merchantName.text}              \n"

        baseData +=               "   \n   ${sDate}            ${sTerminalId}     \n \n"
        baseData +=               "              ${transactionReport.text}               \n"


        return baseData
    }


    fun approvedTransactions(approvedTransactions : List<EodData>?) : String{


        var data : String =     " \n \n         ${ "Approved Count" }       ${ approvedCount.toString() }      \n"

        data +=                 " \n \n         ${"Approved Total" }        ${ "\u20A6"+Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1)}  \n"
        data +=                 "  \n \n       ${"APPROVED PURCHASE"}              \n \n"

        data += arrangeData(approvedTransactions)

        return data
    }


    fun declinedTransactions(declinedTransactions : List<EodData>?) : String{

//        approvedCount = 0
//        declinedCount = 0
//        approvedsumCount = 0
//        declinedsumCount = 0

        var data : String =     "\n \n         ${ "Declined Count" }       ${ declinedCount.toString() }      \n"

        data +=                 " \n  \n       ${"Declined Total" }        ${ "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)}  \n"
        data +=                 "  \n \n       ${"DECLINED PURCHASE"}               \n"

        data += arrangeData(declinedTransactions)
        return data
    }

    fun arrangeData(declinedTransactions : List<EodData>?) : String{
        allTransactions = " \n \n $time             $RRN                $amount   \n  \n"
//        allTransactions += "-----------------------------------------------------"
        var dAllTransactions = ""
        dAllTransactions  += allTransactions
        declinedTransactions?.forEach {
            var  time : String =TimeUtils.convertLongToTime(it.dateTime)
            var rrn = if (it.transactionRef.isNullOrEmpty())it.transactionRef else{it.transactionRef.substring(0,8)}
            var  RRN : String = rrn
            var amount : String  = "\u20A6"+Utilities.parseLongIntoNairaKoboString(it.amount.replace(".","").trim { it <= ' ' }.toLong()).substring(1)
            dAllTransactions += " $time      $RRN    $amount   \n"
            dAllTransactions += "-----------------------------------------------------"


        }

        return  dAllTransactions

    }



    fun getBitmapFromView(view: View): Bitmap {
        //Define a bitmap with the same size as the view

        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable = view.background
        if (bgDrawable != null)
        //has background drawable , then draw it on the canvas
            bgDrawable.draw(canvas)
        else
        //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap
        return returnedBitmap
    }




//    private fun printBitmap(bitmap: Bitmap?, fitToPage: Boolean) {
//        if (bitmap == null || bitmap.width == 0 || bitmap.height == 0) {
//            return
//        }
//
//        Log.d("viewEOD", "original Bitmap Width:" + bitmap.width + "   Height:" + bitmap.height + "  FitToPage=" + fitToPage)
//
//        // PrintTask constructor
//        printTask = PrintTask()
//
//
//
//        // PrintCanvas constructor
//        val printCanvas = PrintCanvas()
//        val paint = Paint()
//
//
//
//
////        if (fitToPage && bitmap.width != PRINTER_WIDTH) {
////            val scaledHeight = PRINTER_WIDTH * bitmap.height / bitmap.width
////            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, PRINTER_WIDTH, scaledHeight, false)
////            Log.d("viewEOD", "scaled Bitmap Width:" + scaledBitmap.width + "   Height:" + scaledBitmap.height)
////            // Draw the bitmap
////            printCanvas.drawBitmap(scaledBitmap, paint)
////        } else {
//        // Draw the bitmap
//        printCanvas.drawBitmap(bitmap, paint)
////        }
//        // Set print canvas
//        printTask.setPrintCanvas(printCanvas)
//        // Set the amount of feed paper
//        printTask.addFeedPaper(100)
//        // Get the gray value of the task
//        printTask.setGray(250)
//
//        // Start print task
//        Printer.getInstance().startPrint(printTask, printerCallback)
//    }

//    private val printerCallback = PrinterCallback { arg0, arg1 ->
//
//    }

    private fun fetchApprovedData(historyType : TransactionHistory.HISTORY_TYPE, startDate : Long, endDate : Long) {
        var out : List<EodData>?= ArrayList<EodData>()
        historyAdapter = HistoryAdapter(historyType)

        Log.d("here 1", "fetchApprovedData")
        //  historyAdapter2 = HistoryAdapter(historyType)

        historyProgressBar.visibility = View.VISIBLE

        transactionHistory.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        //  transactionHistory2.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

        when (historyType){
            TransactionHistory.HISTORY_TYPE.WALLET -> {
                transactionHistory.adapter = historyAdapter
                GlobalScope.launch {
                    val walletId = SharedPreferenceUtils.getPayviceWalletId(this@EODActivity)

                    walletHistory =  HistoryService.getInstance().getWalletHistory(walletId).await()


                    if (walletHistory.error){
                        GlobalScope.launch(Dispatchers.Main){
                            alert {
                                title = "Error"
                                message = "Error retrieving history for wallet Id $walletId"
                            }.show()
                        }
                    }

                    GlobalScope.launch(Dispatchers.Main){
                        historyProgressBar.visibility = View.GONE
                        //historyAdapter.setTransactionResults(walletHistory)
                    }

                }
            }

            TransactionHistory.HISTORY_TYPE.CARD -> {

                val adapter = HistoryAdapter(TransactionHistory.HISTORY_TYPE.CARD)
                transactionHistory.adapter = adapter
                // transactionHistory2.adapter = adapter

//                val result = (application as App).db.transactionResultDao.findAll()
                Log.i("okh Approved", " start " + startDate + " end " + endDate)
//                val result = (application as App).db.transactionResultDao.findInDateRange(startDate,endDate)
//
//                result.observe({lifecycle})


                val result2 = initEodDb.findApprovedEodInDateRange(startDate,endDate,"00")


                Log.i("okh Approved", " Eod data  " + Gson().toJson(result2))


                result2.observe({ lifecycle }){

                    historyProgressBar.visibility = View.GONE
                    adapter.setTransactionResults(it!!)


                    val size = it.size
                    if(size != 0){
                        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")


                        sDate=dateFormat.format(it.get(size - 1).dateTime)
                        EODDate.text =sDate
                    }else{
                        EODDate.text =sDate
                    }


                }
            }

            TransactionHistory.HISTORY_TYPE.ALL -> {

            }
        }

    }


    internal inner class HistoryAdapter(val historyType : TransactionHistory.HISTORY_TYPE) : RecyclerView.Adapter<HistoryViewHolder>() {
        private var transactionResults = ArrayList<EodData>()



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {

            val view = LayoutInflater.from(this@EODActivity).inflate(R.layout.individual_eod_history, parent, false)
            return HistoryViewHolder(view)
        }

        override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
            when (historyType){
                TransactionHistory.HISTORY_TYPE.WALLET -> {
                    val result = walletHistory.data[position]
                    holder.transaction_amount.text = Utilities.parseLongIntoNairaKoboString(result.amount.replace(".","").trim { it <= ' ' }.toLong()).substring(1)
//                    holder.beneficiary_name.text = result.date
//                    holder.transaction_type.text = result.service
//                    holder.transaction_id.text = result.ref

                    holder.itemView.tag = result

                }

                TransactionHistory.HISTORY_TYPE.CARD -> {

                    val result = transactionResults[position]


                    Log.d("amount", result.amount.toString())
                    Log.d("position", position.toString())
                    if (result.isApproved()){
                        approvedCount++
                        allApproved.add(result)
                        approvedsumCount = (approvedsumCount+result.amount.replace(".","").trim { it <= ' ' }.toLong()).toInt()
                        holder.transaction_amount.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result.amount.replace(".","").trim { it <= ' ' }.toLong()).substring(1)
                        holder.transaction_time.text = TimeUtils.convertLongToTime(result.dateTime)
                        // holder.transaction_amount.text = result.amount.toString()
                        holder.beneficiary_RRN.text = result.transactionRef
                        Log.d("count", approvedCount.toString())
                        //holder.transaction_id.text = result.PAN
                        approvedValue.text = approvedCount.toString()
                        approvedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1)
                        holder.itemView.tag = result
                        Log.d("amount", "" + "\u20A6"+Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1))
                        Log.d("count", approvedCount.toString())
                    }
                    else{
                        holder.transaction_amount.visibility = View.GONE
                        holder.transaction_time.visibility = View.GONE
                        holder.beneficiary_RRN.visibility = View.GONE
                        holder.eodrel.visibility = View.GONE
                    }
//                    else{
//                        declinedCount++
//                        declinedsumCount = (declinedsumCount+result.amount).toInt()
//                        holder.transaction_amount.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result.amount).substring(1)
//                        holder.transaction_time.text = TimeUtils.convertLongToTime(result.longDateTime)
//                        // holder.transaction_amount.text = result.amount.toString()
//                        holder.beneficiary_RRN.text = result.RRN
//                        Log.d("count", declinedCount.toString())
//                        //holder.transaction_id.text = result.PAN
//                        declinedValue.text = declinedCount.toString()
//                        declinedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)
//                        holder.itemView.tag = result
//                    }


                }

                TransactionHistory.HISTORY_TYPE.ALL -> {
                    val result = transactionResults[position]
                    approvedCount++
                    allApproved.add(result)
                    approvedsumCount = (approvedsumCount+result.amount.replace(".","").trim { it <= ' ' }.toLong()).toInt()
                    holder.transaction_amount.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result.amount.replace(".","").trim { it <= ' ' }.toLong()).substring(1)
                    holder.transaction_time.text = TimeUtils.convertLongToTime(result.dateTime)
                    // holder.transaction_amount.text = result.amount.toString()
                    holder.beneficiary_RRN.text = result.transactionRef
                    Log.d("count", approvedCount.toString())
                    //holder.transaction_id.text = result.PAN
                    approvedValue.text = approvedCount.toString()
                    approvedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1)
                    holder.itemView.tag = result
                    Log.d("amount", "" + "\u20A6"+Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1))
                    Log.d("count", approvedCount.toString())
                }
            }


        }

        fun setTransactionResults(transactionResults: List<EodData>?) {
            transactionResults?.let {
                allApproved.clear()
                this.transactionResults.clear()
                this.transactionResults.addAll(it)
                notifyDataSetChanged()
                Log.d("here", "Approved here" + it.size)

            }
        }

        fun setWalletTransactionResults(transactionResults : List<HistoryModel>){

        }

        override fun getItemCount(): Int {
            return  transactionResults.size
        }
    }

    internal inner class HistoryViewHolder(view_that_is_passed: View) : RecyclerView.ViewHolder(view_that_is_passed), View.OnClickListener {
        var transaction_time: TextView
        var beneficiary_RRN: TextView
        var transaction_amount: TextView
        var eodrel: RelativeLayout

        // var transaction_id: TextView

        init {
            transaction_time = itemView.findViewById(R.id.time)
            beneficiary_RRN = itemView.findViewById(R.id.RRN)
            transaction_amount = itemView.findViewById(R.id.amount)
            eodrel = itemView.findViewById(R.id.eodrel)

            // transaction_id = itemView.findViewById(R.id.history_transaction_id)

            view_that_is_passed.setOnClickListener(this)
        }

        override fun onClick(v: View) {
//            val transactionResult = itemView.tag as EodData
//
//            val intent = Intent(this@EODActivity, RefundActivity::class.java)
//
//            var type=transactionResult.type
//            Log.i("type>>>>>>>>",type)
//
//            GlobalScope.launch {
//                val list=initDb.getAllTransactions()
//
//                list.forEach {
//                    Log.i("AllCardTransactions >>>>>>>>", Gson().toJson(it))
//                }
////                delay(700)
//                val ok=initVasDb.getAllVasTransactions()
//                ok.forEach {
//                    Log.i("aLL vas transactions>>>>>>>>",Gson().toJson(it))
//                }
//
//
//            }
//            Log.i("transactionResult>>>>>>>>",Gson().toJson(transactionResult))
//            Log.i("RRN>>>>>>>>",transactionResult.transactionRef.toString())
//
//            intent.putExtra(BasePaymentActivity.TRANSACTION_RRN,transactionResult.id )
//
//
//            intent.putExtra(Helper.TRANSACTION_REF, transactionResult.transactionRef)
//
//
//
//            intent.putExtra(Helper.TRANSACTION_TYPE, type)
//
////            intent.putExtra(BasePaymentActivity.TRANSACTION_RRN, transactionResult.transactionRef)
//            startActivity(intent)
        }
    }



    private fun fetchDeclinedData(historyType2 : TransactionHistory.HISTORY_TYPE, startDate : Long, endDate : Long) {
        Log.d("here 2 ", "fetchDeclinedData")
        historyAdapter2 = HistoryAdapter(historyType2)
        //  historyAdapter2 = HistoryAdapter(historyType)

        historyProgressBar.visibility = View.VISIBLE

        transactionHistory2.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        //  transactionHistory2.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)

        when (historyType2){
            TransactionHistory.HISTORY_TYPE.WALLET -> {
                transactionHistory2.adapter = historyAdapter2
                GlobalScope.launch {
                    val walletId = SharedPreferenceUtils.getPayviceWalletId(this@EODActivity)

                    walletHistory =  HistoryService.getInstance().getWalletHistory(walletId).await()


                    if (walletHistory.error){
                        GlobalScope.launch(Dispatchers.Main){
                            alert {
                                title = "Error"
                                message = "Error retrieving history for wallet Id $walletId"
                            }.show()
                        }
                    }

                    GlobalScope.launch(Dispatchers.Main){
                        historyProgressBar.visibility = View.GONE
                        //historyAdapter.setTransactionResults(walletHistory)
                    }

                }
            }

            TransactionHistory.HISTORY_TYPE.CARD -> {

                val adapter2 = HistoryAdapter2(TransactionHistory.HISTORY_TYPE.CARD)
                transactionHistory2.adapter = adapter2
                // transactionHistory2.adapter = adapter

//                val result2 = (application as App).db.transactionResultDao.findAll()
                Log.i("okh Declined", " start " + startDate + " end " + endDate)

//                val result2 = (application as App).db.transactionResultDao.findInDateRange(startDate,endDate)
//
//
//                result2.observe({lifecycle})


                val result2 = initEodDb.findDeclinedEodInDateRange(startDate,endDate,"00")
                val allresult = initEodDb.findAllEod()

                Log.i("okh allresult", " allresult " + Gson().toJson(allresult))

                result2.observe({ lifecycle }){

                    historyProgressBar.visibility = View.GONE
                    adapter2.setTransactionResults2(it!!)

                    Log.i("okh Declined", " start " + startDate + " end " + endDate)
                    Log.i("okh Declined", " start " + it.size)


                }
            }

            TransactionHistory.HISTORY_TYPE.ALL -> {

            }
        }
    }


    internal inner class HistoryAdapter2(val historyType2 : TransactionHistory.HISTORY_TYPE) : RecyclerView.Adapter<HistoryViewHolder2>() {
        private var transactionResults2 = ArrayList<EodData>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder2 {

            val view2 = LayoutInflater.from(this@EODActivity).inflate(R.layout.individual_eod_history2, parent, false)
            return HistoryViewHolder2(view2)
        }

        override fun onBindViewHolder(holder2: HistoryViewHolder2, position2: Int) {
            when (historyType2){
                TransactionHistory.HISTORY_TYPE.WALLET -> {
                    val result2 = walletHistory.data[position2]



                    holder2.transaction_amount2.text = Utilities.parseLongIntoNairaKoboString(result2.amount.toLong()).substring(1)
//                    holder.beneficiary_name.text = result.date
//                    holder.transaction_type.text = result.service
//                    holder.transaction_id.text = result.ref

                    holder2.itemView.tag = result2

                }

                TransactionHistory.HISTORY_TYPE.CARD -> {

                    val result2 = transactionResults2[position2]
                    Log.d("amount", result2.amount.toString())
                    Log.d("position", position2.toString())
                    if (!result2.isApproved()){
//                        approvedCount++
//                        approvedsumCount = (approvedsumCount+result.amount).toInt()
//                        holder2.transaction_amount2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result.amount).substring(1)
//                        holder2.transaction_time2.text = TimeUtils.convertLongToTime(result.longDateTime)
//                        // holder.transaction_amount.text = result.amount.toString()
//                        holder2.beneficiary_RRN2.text = result.RRN
//                        Log.d("count", approvedCount.toString())
//                        //holder.transaction_id.text = result.PAN
//                        approvedValue.text = approvedCount.toString()
//                        approvedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(approvedsumCount.toLong()).substring(1)
//                        holder2.itemView.tag = result
//                    }
//                    else{
                        declinedCount++
                        allDeclined.add(result2)
                        Log.i("allData", "Declined" +  allDeclined.size)
                        Log.d("here 2 ", "HistoryAdapter2")
                        declinedsumCount = (declinedsumCount+result2.amount.replace(".","").trim { it <= ' ' }.toLong()).toInt()
                        Log.d("odo",  " amount before" + "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1))
                        Log.d("odo","count before " +declinedCount.toString())
                        holder2.transaction_amount2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result2.amount.replace(".","").trim { it <= ' ' }.toLong()).substring(1)
                        holder2.transaction_time2.text = TimeUtils.convertLongToTime(result2.dateTime)
                        // holder.transaction_amount.text = result.amount.toString()
                        holder2.beneficiary_RRN2.text = result2.transactionRef
                        Log.d("count", declinedCount.toString())
                        //holder.transaction_id.text = result.PAN
                        declinedValue.text = declinedCount.toString()
//                        declinedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)
                        declinedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)
                        holder2.itemView.tag = result2

                        Log.d("odo"," amount after " + "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1))
                        Log.d("odo"," count after " + declinedCount.toString())
                    }
                    else{
                        declinedValue2.text= "0"
                        holder2.transaction_amount2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(0).substring(1)
                        holder2.transaction_amount2.visibility = View.GONE
                        holder2.transaction_time2.visibility = View.GONE
                        holder2.beneficiary_RRN2.visibility = View.GONE
                        holder2.eodrel2.visibility = View.GONE
                    }


                }

                TransactionHistory.HISTORY_TYPE.ALL -> {

                    val result2 = transactionResults2[position2]
                    Log.d("amount", result2.amount.toString())
                    Log.d("position", position2.toString())


                    declinedCount++
                    allDeclined.add(result2)
                    Log.i("allData", "Declined" +  allDeclined.size)
                    Log.d("here 2 ", "HistoryAdapter2")
                    declinedsumCount = (declinedsumCount+result2.amount.replace(".","").trim { it <= ' ' }.toLong()).toInt()
                    Log.d("odo",  " amount before" + "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1))
                    Log.d("odo","count before " +declinedCount.toString())
                    holder2.transaction_amount2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(result2.amount.replace(".","").trim { it <= ' ' }.toLong()).substring(1)
                    holder2.transaction_time2.text = TimeUtils.convertLongToTime(result2.dateTime)
                    // holder.transaction_amount.text = result.amount.toString()
                    holder2.beneficiary_RRN2.text = result2.transactionRef
                    Log.d("count", declinedCount.toString())
                    //holder.transaction_id.text = result.PAN
                    declinedValue.text = declinedCount.toString()
//                        declinedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)
                    declinedValue2.text = "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1)
                    holder2.itemView.tag = result2

                    Log.d("odo"," amount after " + "\u20A6"+Utilities.parseLongIntoNairaKoboString(declinedsumCount.toLong()).substring(1))
                    Log.d("odo"," count after " + declinedCount.toString())


                }
            }


        }

        fun setTransactionResults2(transactionResults2: List<EodData>?) {
            transactionResults2?.let {
                allDeclined.clear()
                this.transactionResults2.clear()
                this.transactionResults2.addAll(it)
                notifyDataSetChanged()
                Log.d("here", "Declined here " + it.size)


//                Helper.savePreference(baseContext, Helper.START_TIME,0L)
//                Helper.savePreference(baseContext,Helper.END_TIME,0L)

            }
        }

        fun setWalletTransactionResults(transactionResults : List<HistoryModel>){

        }

        override fun getItemCount(): Int {
            return  transactionResults2.size
        }
    }

    internal inner class HistoryViewHolder2(view_that_is_passed: View) : RecyclerView.ViewHolder(view_that_is_passed), View.OnClickListener {
        var transaction_time2: TextView
        var beneficiary_RRN2: TextView
        var transaction_amount2: TextView
        var eodrel2: RelativeLayout

        // var transaction_id: TextView

        init {
            transaction_time2 = itemView.findViewById(R.id.time2)
            beneficiary_RRN2 = itemView.findViewById(R.id.RRN2)
            transaction_amount2 = itemView.findViewById(R.id.amount2)
            eodrel2 = itemView.findViewById(R.id.eodrel2)

            // transaction_id = itemView.findViewById(R.id.history_transaction_id)

            view_that_is_passed.setOnClickListener(this)
        }

        override fun onClick(v: View) {
//
//            val transactionResult = itemView.tag as EodData
////             val rowId:String=(transactionResult.id).toString()
//            val intent = Intent(this@EODActivity, RefundActivity::class.java)
//
//            var type=transactionResult.type
//            Log.i("type>>>>>>>>",type)
//            Log.i("transactionResult>>>>>>>>",Gson().toJson(transactionResult))
//            Log.i("RRN>>>>>>>>",transactionResult.transactionRef.toString())
//
//
//            GlobalScope.launch {
//                val list=initDb.getAllTransactions()
//
//                list.forEach {
//                    Log.i("AllCardTransactions >>>>>>>>", Gson().toJson(it))
//                }
////                delay(700)
//                val ok=initVasDb.getAllVasTransactions()
//                ok.forEach {
//                    Log.i("aLL vas transactions>>>>>>>>",Gson().toJson(it))
//                }
//
//
//            }
//
//
//            intent.putExtra(BasePaymentActivity.TRANSACTION_RRN,transactionResult.id)
//
//
//
//            intent.putExtra(Helper.TRANSACTION_REF, transactionResult.transactionRef)
//
//
//            intent.putExtra(Helper.TRANSACTION_TYPE, type)
//
//            startActivity(intent)

        }
    }



}
