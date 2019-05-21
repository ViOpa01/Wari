package com.wizarpos.emvsample.models.transfer



data class TransferSuccessModel(val status : Int = 0, val error : String = "", val message : String = "", val reason : String = "", val reversal : String = "" , val description : description, val transactionID : Int, val convenienceFee : Int = 0, val amountSettled : Int = 0, val amountDebited : Int = 0, val beneficiaryName : String = "", val beneficiary : String = "", val reference : String = "")

data class TransferFailureModel(val status: Int, val error: String, val reversal : String, val message: String, val description: String, val transactionID: Int)

data class description(val status : Int = 0, val message: String, val description: String, val reference :  String, val requeries : String, val pending :String, val account: String, val vendorBankCode: String)
