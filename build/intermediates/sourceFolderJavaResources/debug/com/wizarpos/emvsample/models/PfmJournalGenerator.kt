package com.wizarpos.emvsample.models

import com.iisysgroup.poslib.ISO.common.IsoConfigData
import com.iisysgroup.poslib.commons.emv.EmvCard
import com.iisysgroup.poslib.host.Host
import com.iisysgroup.poslib.host.entities.ConfigData
import com.iisysgroup.poslib.host.entities.TransactionResult

//class PfmJournalGenerator(private val transactionResult: TransactionResult, private  val configData : ConfigData, private val printer : Printer, private val isReceiptPrinted: Boolean, private val vasCategory : String = "", private val vasProduct : String = "", private val cardData : EmvCard) {

class PfmJournalGenerator(private val transactionResult: TransactionResult, private  val configData : ConfigData, private val isReceiptPrinted: Boolean, private val amount : String, private val cardData : EmvCard?,private  val vasProduct:String,private val vasCategory:String,private val customField:String) {

    fun generateJournal(): com.itex.richard.payviceconnect.model.Journal {

        return com.itex.richard.payviceconnect.model.Journal(getstan(),getmPan(),getRrn(),getAcode(),amount,getTimeStamp(),getmti(),"",getResp(),getTap(),getRep().toString(),getVm(),getOstan(),getOrrn(),getOacode(),getMid(),getVasProduct(), getVasCategory(),getMcc(),getTransMethod(),getCustomField())
    }

    /*fun generateJournal(): DstvModel.PfmJournal {

        return DstvModel.PfmJournal(getMid(), getstan(), getmPan(), getRrn(), getAcode(), getAmount(), getTimeStamp(), getmti(), getps(), getResp(), getMcc(), getTap(), getRep(), getVm(), getVasProduct(), getVasCategory(), getTransMethod(), getOstan(), getOrrn(), getOacode())
    }*/

    private fun getOacode(): String {
        return transactionResult.authID
    }

    private fun getOrrn(): String {
        return transactionResult.RRN
    }

    private fun getOstan(): String {
        return transactionResult.STAN
    }

    private fun getTransMethod(): String {
        return if (transactionResult.PAN.isNotEmpty()){
            "card"
        } else {"cash"}
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


                 if(cardData!!.pinInfo == null){
                 vm="offlinePin"
        }else {
            vm = if (cardData!!.pinInfo.pinBlock.isEmpty() || cardData.pinInfo.pinBlock == null) {
                "offlinePin"
            } else {
                "onlinePin"
            }
        }
     }catch (e:Throwable){
         vm ="offlinePin"
     }
        return vm
    }

    private fun getRep(): Boolean {
        return isReceiptPrinted
    }

    //Response code from the upstream entity usually field 39 of ISO8583
    private fun getResp(): String {
        return transactionResult.responseCode
    }


    private fun getTap(): Boolean {
        return transactionResult.isApproved
    }

    private fun getMcc(): String {
        return configData.getConfigData(IsoConfigData.TAG_LEN_MERCHANT_CATEGORY_CODE) as String
    }


//    private fun getps(): String {
//        return printer.printerStatus.toString()
//    }

    //todo handle mti
    private fun getmti(): String {
        val mti = if (transactionResult.transactionType == Host.TransactionType.REVERSAL)
            440
        else 200
        return mti.toString()
    }

    private fun getTimeStamp(): String {
        return transactionResult.longDateTime.toString()
    }

    private fun getAmount(): Long {
        return transactionResult.amount
    }

    //todo handle acode
    private fun getAcode(): String {
        return transactionResult.authID
    }

    private fun getRrn(): String {
        return transactionResult.RRN
    }

    private fun getmPan(): String {
        return transactionResult.PAN
    }

    private fun getstan(): String {
        return transactionResult.STAN
    }

    private fun getMid(): String {
        return transactionResult.merchantID
    }


}

