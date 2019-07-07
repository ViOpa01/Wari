package com.wizarpos.emvsample.models

import android.util.Log
import com.iisysgroup.poslib.ISO.common.IsoConfigData
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.iisysgroup.poslib.host.Host
import com.iisysgroup.poslib.host.entities.ConfigData
import com.iisysgroup.poslib.host.entities.TransactionResult

//class PfmJournalGenerator(private val transactionResult: TransactionResult, private  val configData : ConfigData, private val printer : Printer, private val isReceiptPrinted: Boolean, private val vasCategory : String = "", private val vasProduct : String = "", private val cardData : EmvCard) {

class PfmJournalGenerator(private val transactionResult: TransactionResult?, private  val configData : ConfigData?, private val isReceiptPrinted: Boolean, private val amount : String, private val cardData : EmvCard?,private  val vasProduct:String,private val vasCategory:String,private val customField:String) {

    fun generateJournal(): com.itex.richard.payviceconnect.model.Journal {

        return com.itex.richard.payviceconnect.model.Journal(getstan(),getmPan(),getRrn(),getAcode(),amount,getTimeStamp(),getmti(),"",getResp(),getTap(),getRep().toString(),getVm(),getOstan(),getOrrn(),getOacode(),getMid(),getVasProduct(), getVasCategory(),getMcc(),getTransMethod(),getCustomField())
    }

    /*fun generateJournal(): DstvModel.PfmJournal {

        return DstvModel.PfmJournal(getMid(), getstan(), getmPan(), getRrn(), getAcode(), getAmount(), getTimeStamp(), getmti(), getps(), getResp(), getMcc(), getTap(), getRep(), getVm(), getVasProduct(), getVasCategory(), getTransMethod(), getOstan(), getOrrn(), getOacode())
    }*/

    private fun getOacode(): String {
        return if(transactionResult != null){
            transactionResult.authID
        }else{
            ""
        }
    }

    private fun getOrrn(): String {
        return if(transactionResult != null){
            transactionResult.RRN
    }else{
        ""
       }
    }

    private fun getOstan(): String {
        return if(transactionResult !=null){
            transactionResult.STAN
    }else{
        ""
       }
    }
    var status ="cash"
    private fun getTransMethod(): String {

        if(transactionResult != null){
            if (transactionResult.PAN.isNotEmpty()){
                status = "card"
            }
        }

        return status
    }

    private fun getVasCategory(): String {
        return vasCategory
    }

    private fun getVasProduct(): String {
        return vasProduct
    }

    private fun getCustomField(): String {
        return customField
    }

    //Verification method - OnlinePin, OfflinePin, Signature, NoCVM, Others,
    private fun getVm(): String {
        var vm=""
     try {

         if(status !="cash"){

                 if(cardData!!.pinInfo == null || cardData == null){
                 vm=""
        }else {
            vm = if (cardData!!.pinInfo.pinBlock.isEmpty() || cardData.pinInfo.pinBlock == null) {
                "offlinePin"
            } else {
                "onlinePin"
            }
        }}else{
             "others"
         }
     }catch (e:Throwable){
         Log.i("Was a throwable full details ",e.toString())

         Log.i("Was a throwable","")

         vm ="offlinePin"
     }
        return vm
    }

    private fun getRep(): Boolean {
        return isReceiptPrinted
    }

    //Response code from the upstream entity usually field 39 of ISO8583
    private fun getResp(): String {

        return if(transactionResult != null){
            transactionResult.responseCode
        }else{
            ""

        }
    }


    private fun getTap(): Boolean {

        return if(transactionResult != null){
            transactionResult.isApproved
        }else{
           false

        }
    }

    private fun getMcc(): String {
        return if(configData != null){
             configData.getConfigData(IsoConfigData.TAG_LEN_MERCHANT_CATEGORY_CODE) as String
        }else{
            ""

        }
    }


//    private fun getps(): String {
//        return printer.printerStatus.toString()
//    }

    //todo handle mti
    private fun getmti(): String {


        var mti =""
         if(transactionResult != null){
            if (transactionResult.transactionType == Host.TransactionType.REVERSAL) {
                mti = 440.toString()
            }else {
                mti= 200.toString() }

        }
        return mti
    }

    private fun getTimeStamp(): String {
        return if(transactionResult != null){
            transactionResult.longDateTime.toString()
        }else{
            ""

        }
    }

    private fun getAmount(): Long {
        return if(transactionResult != null){
            transactionResult.amount
        }else{
            0L

        }
    }

    //todo handle acode
    private fun getAcode(): String {
        return if(transactionResult != null){
            transactionResult.authID
        }else{
            ""

        }
    }

    private fun getRrn(): String {
        return if(transactionResult != null){
            transactionResult.RRN
        }else{
            ""

        }
    }

    private fun getmPan(): String {
        return if(transactionResult != null){
            transactionResult.PAN
        }else{
            ""

        }
    }

    private fun getstan(): String {
        return if(transactionResult != null){
            transactionResult.STAN
        }else{
            ""

        }
    }

    private fun getMid(): String {
        return if(transactionResult != null){
            transactionResult.merchantID
        }else{
            ""

        }
    }


}

