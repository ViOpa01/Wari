package com.iisysgroup.androidlite.utils

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.iisysgroup.poslib.deviceinterface.interactors.PrinterInteractor
import com.iisysgroup.poslib.deviceinterface.printer.PrintFormat
import com.iisysgroup.poslib.deviceinterface.printer.Printable
import com.iisysgroup.poslib.deviceinterface.printer.PrinterState
import com.iisysgroup.poslib.deviceinterface.printer.StringPrintable
import com.iisysgroup.poslib.host.Host
import com.iisysgroup.poslib.host.entities.TransactionResult
import com.iisysgroup.poslib.utils.Utilities

import com.wizarpos.emvsample.R
import com.wizarpos.emvsample.activity.login.Helper
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage
import com.wizarpos.emvsample.models.ReceiptModel
import com.wizarpos.util.AppUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_print.view.*
import kotlinx.android.synthetic.main.individual_receipt_item.view.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


/**
 * Created by Agbede on 2/28/2018.
 */

object PrintUtils {

//    fun getDrawableFromVasType(context : Context, vasType : PrintActivity.VasType?) : Drawable? {
//        when (vasType){
//            PrintActivity.VasType.MTN_DATA, PrintActivity.VasType.MTN_VTU -> {
//                return ContextCompat.getDrawable(context,R.drawable.mtn_logo)
//            }
//
//            PrintActivity.VasType.GLO_DATA, PrintActivity.VasType.GLO_VTU -> {
//                return ContextCompat.getDrawable(context,R.drawable.glo_logo)!!
//            }
//
//            PrintActivity.VasType.AIRTEL_DATA, PrintActivity.VasType.AIRTEL_VTU -> {
//                return ContextCompat.getDrawable(context,R.drawable.airtel_logo)
//            }
//
//            PrintActivity.VasType.ETISALAT_DATA, PrintActivity.VasType.ETISALAT_VTU -> {
//                return ContextCompat.getDrawable(context,R.drawable.ninemobile_logo)
//            }
//
//            PrintActivity.VasType.IKEDC_PREPAID, PrintActivity.VasType.IKEDC_POSTPAID -> {
//                return ContextCompat.getDrawable(context,R.drawable.ikedc)
//            }
//
//            PrintActivity.VasType.EKEDC_PREPAID, PrintActivity.VasType.EKEDC_POSTPAID -> {
//                return ContextCompat.getDrawable(context,R.drawable.ekedc)
//            }
//
//            PrintActivity.VasType.SMILE_DATA_PURCHASE, PrintActivity.VasType.SMILE_TOP_UP -> {
//                return ContextCompat.getDrawable(context,R.drawable.smile)
//            }
//
//            PrintActivity.VasType.DSTV -> {
//                return ContextCompat.getDrawable(context,R.drawable.dstv)
//            }
//
//            PrintActivity.VasType.GOTV -> {
//                return ContextCompat.getDrawable(context,R.drawable.gotv)
//            }
//
//
//        }
//
//        return  return ContextCompat.getDrawable(context,R.drawable.wari_small)
//
//    }



    fun generateReceipt(context: Context, receiptModel: ReceiptModel, totalReceipt: LinearLayout) : View {

        receiptModel.map.forEach{
            totalReceipt. merchantName.text = SecureStorage.retrieve(Helper.MERCHANT_NAME, "")
            totalReceipt.merchantId.text = SecureStorage.retrieve(Helper.MERCHANT_MID, "")
            totalReceipt.terminalid.text = SecureStorage.retrieve(Helper.TERMINAL_ENTERED_BY_USER, "")
            totalReceipt.transactionStatus.text = receiptModel.transactionStatus
            totalReceipt.datetime.text = receiptModel.date
            totalReceipt.transactionType.text = receiptModel.transactionType
//            totalReceipt.transactionStatusReason.text = receiptModel.transactionStatusReason


            val linearLayout = LayoutInflater.from(context).inflate(R.layout.individual_receipt_item, null, false)
            linearLayout.receiptTitle.text = it.key
            linearLayout.receiptValue.text = it.value


            totalReceipt.addView(linearLayout)
        }

        val starText = TextView(context)
        starText.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        starText.text = "***********"
        starText.textSize = 24f
        starText.gravity = Gravity.CENTER_HORIZONTAL

        val amountTextView = TextView(context)
        amountTextView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        val decimalFormat = DecimalFormat("" + "###,###,###,###.##")
//        decimalFormat.maximumFractionDigits = 2
//
//        val amount = decimalFormat.format(receiptModel.amount.replace(".","").toLong())
//
//
//        amountTextView.text = "\u20A6$amount"

//        Log.i( "oky","amount " + receiptModel.amount)
//        var final: String  =""
//        var split: String  =receiptModel.amount.split(".")[0]
//        if (receiptModel.amount.split(".")[1].length <= 1){
//            final = split + receiptModel.amount.split(".")[1] + "0"}
//        else{
//            final = receiptModel.amount}
//
//        Log.i("oky","final " + final)
//        val amt =receiptModel.amount.replace(".", "")
//
//        Log.i( "oky","amt " + amt)

//        val amount = Utilities.parseLongIntoNairaKoboString(receiptModel.amount)

                val amount = AppUtil.formatAmount(receiptModel.amount,true )


        Log.i("oky","parseLongIntoNaira "+ amount)


        amountTextView.text = "NGN" + amount.replace("N","\u20A6")
        amountTextView.textSize = 24f
        amountTextView.gravity = Gravity.CENTER_HORIZONTAL

        val starText2 = TextView(context)
        starText2.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        starText2.text = "***********"
        starText2.textSize = 24f
        starText2.gravity = Gravity.CENTER_HORIZONTAL


        val transactionReason = TextView(context)
        transactionReason.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        transactionReason.text = receiptModel.transactionStatusReason
        transactionReason.textSize = 19f
        transactionReason.gravity = Gravity.CENTER_HORIZONTAL


        val blankline = TextView(context)
        blankline.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        blankline.text = "_________________________"
        blankline.textSize = 14f
        blankline.gravity = Gravity.CENTER_HORIZONTAL

        val versionText = TextView(context)
        versionText.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        versionText.text = "TAMSLITE v(1.0.0) WA"
        versionText.textSize = 19f
        versionText.gravity = Gravity.CENTER_HORIZONTAL

        val poweredText = TextView(context)
        poweredText.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        poweredText.text = "Powered by ITEX"
        poweredText.textSize = 19f
        poweredText.gravity = Gravity.CENTER_HORIZONTAL

        val websiteText = TextView(context)
        websiteText.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        websiteText.text = "www.iisysgroup.com"
        websiteText.textSize = 19f
        websiteText.gravity = Gravity.CENTER_HORIZONTAL


        val phoneNumber = TextView(context)
        phoneNumber.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        phoneNumber.text = "0700-2255-4839"
        phoneNumber.textSize = 19f
        phoneNumber.gravity = Gravity.CENTER_HORIZONTAL

        totalReceipt.addView(starText)
        totalReceipt.addView(amountTextView)
        totalReceipt.addView(starText2)
        totalReceipt.addView(transactionReason)
        totalReceipt.addView(blankline)
        totalReceipt.addView(versionText)
        totalReceipt.addView(poweredText)
        totalReceipt.addView(websiteText)
        totalReceipt.addView(phoneNumber)

        return totalReceipt
    }

    private fun printFile(printer: PrinterInteractor, printables: ArrayList<Printable>, lifecycleOwner: LifecycleOwner) {
        printer.print(printables).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { t: PrinterState? ->
                    t?.let {
                        when (it){
                            PrinterState.BUSY -> Log.d("PrinterState", "Busy")
                            PrinterState.ERROR -> Log.d("PrinterState", "Error")
                            PrinterState.NO_PAPER -> Toast.makeText(lifecycleOwner as Context, "No paper", Toast.LENGTH_LONG).show()
                            PrinterState.LOW_OR_NO_BATTERY -> Log.d("PrinterState", "Low battery")
                            PrinterState.OVER_HEATING -> Log.d("PrinterState", "Over heating")
                            else -> {}
                        }
                    }
                }

    }


//    private fun generatePrintable(currentTransactionResult: TransactionResult, printer : PrinterInteractor, lifecycleOwner: LifecycleOwner, copy : String){
//        currentTransactionResult.let{
//            val printables = ArrayList<Printable>()
//            with(printables)
//            {
//                val dateTimeString = "${TimeUtils.convertLongToString(it.longDateTime)}"
//                val amountString = Utilities.parseLongIntoNairaKoboString(it.amount)
//                val additionalAmountString = StringUtils.getPrintableLine("Additional Amount", Utilities.parseLongIntoNairaKoboString(it.additionalAmount))
//                val NAME = StringUtils.getPrintableLine("Name", it.cardHolderName)
//                val expiry_date = StringUtils.getPrintableLine("Expiry date", it.cardExpiry)
//                val MID = StringUtils.getPrintableLine("MID", it.merchantID)
//                val AID = StringUtils.getPrintableLine("AID", it.authID)
//                val PAN = StringUtils.getPrintableLine("PAN", it.PAN)
//                val LABEL = StringUtils.getPrintableLine("LABEL", it.responseCode)
//                val SEO_NO = StringUtils.getPrintableLine("SEO NO", it.STAN)
//                val RRN = StringUtils.getPrintableLine("RRN", it.RRN)
//                val AUTH_ID = StringUtils.getPrintableLine("AUTH ID", it.authID)
//                val acct_type = StringUtils.getPrintableLine("ACCOUNT TYPE", it.accountType)
//                val TSI = StringUtils.getPrintableLine("TSI", it.transactionStatus)
//                val TVR = StringUtils.getPrintableLine("TVR", it.transactionStatusReason)
//
//                add(StringPrintable(dateTimeString,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                Enum
//
//                add(StringPrintable(it.transactionType.toString(),
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.BOLD)))
//
//                add(StringPrintable("***$copy***",
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.BOLD)))
//
//                add(StringPrintable(it.transactionStatusReason,
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(MID,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(AID,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(PAN,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(NAME,
//                       PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(LABEL,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(SEO_NO,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(RRN,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(acct_type,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//
//                add(StringPrintable("***********",
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.BOLD)))
//
//                add(StringPrintable(amountString,
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.BOLD)))
//
//                add(StringPrintable("***********",
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.BOLD)))
//
//                add(StringPrintable(TSI,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(TVR,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable(expiry_date,
//                        PrintFormat(PrintFormat.Align.LEFT, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable("-".repeat(32),
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable("Powered by Itex Integrated Services",
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                add(StringPrintable("0700-2255-4839",
//                        PrintFormat(PrintFormat.Align.CENTER, PrintFormat.FontSize.NORMAL, PrintFormat.FontStyle.NORMAL)))
//
//                PrintUtils.printFile(printer, printables, lifecycleOwner)
//            }
//        }
//    }
//
//    fun generatePrintableForMerchant(currentTransactionResult: TransactionResult, printer : PrinterInteractor, lifecycleOwner: LifecycleOwner)
//    {
//        val copy_string = "Merchant's Copy"
//        generatePrintable(currentTransactionResult, printer, lifecycleOwner, copy_string)
//    }
//
//    fun generatePrintableForCustomer(currentTransactionResult: TransactionResult, printer : PrinterInteractor, lifecycleOwner: LifecycleOwner) {
//        val copy_string = "Customer's Copy"
//        generatePrintable(currentTransactionResult, printer, lifecycleOwner, copy_string)
//    }
//
//
//    fun printBitmap(bitmap: Bitmap?, fitToPage: Boolean) {
//        if (bitmap == null || bitmap.width == 0 || bitmap.height == 0) {
//            Log.d("OkH", "Bitmap size")
//            return
//        }
//
//        Log.d("OkH", "original Bitmap Width:" + bitmap.width + "   Height:" + bitmap.height + "  FitToPage=" + fitToPage)
//
//        // PrintTask constructor
//        val printTask = PrintTask()
//
//        // PrintCanvas constructor
//        val printCanvas = PrintCanvas()
//        val paint = Paint()
//
//
//
//        if (fitToPage && bitmap.width != 384) {
//            val scaledHeight = 384 * bitmap.height / bitmap.width
//            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 384, scaledHeight, false)
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
//
//
//
//        com.pos.device.printer.Printer.getInstance().startPrint(printTask){ arg1, printTask ->
//
//            Log.d("OkH", "Printer")
//        }
//    }
//
//    fun getDrawableFromTerminalId(context : Context, terminalId : String) : Drawable {
//        try {
//            val bankLogoName = "bank${terminalId.substring(0, 4)}"
//            val resources = context.resources;
//            val resourceId = resources.getIdentifier(bankLogoName, "drawable",
//                    context.getPackageName());
//
//            val drawable = resources.getDrawable(resourceId)
///*
//       val bitmap = BitmapFactory.decodeResource(context.getResources(),
//               resourceId)*/
//
//            return drawable
//        } catch (exception : Exception){
//            return context.getDrawable(R.drawable.itex)
//        }
//
//    }
//
//    fun getBitmapFromView(view: View): Bitmap {
//        //Define a bitmap with the same size as the view
//        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
//        //Bind a canvas to it
//        val canvas = Canvas(returnedBitmap)
//        //Get the view's background
//        val bgDrawable = view.getBackground()
//        if (bgDrawable != null)
//        //has background drawable, then draw it on the canvas
//            bgDrawable!!.draw(canvas)
//        else
//        //does not have background drawable, then draw white background on the canvas
//            canvas.drawColor(Color.WHITE)
//        // draw the view on the canvas
//        view.draw(canvas)
//        //return the bitmap
//        return returnedBitmap
//    }

}
