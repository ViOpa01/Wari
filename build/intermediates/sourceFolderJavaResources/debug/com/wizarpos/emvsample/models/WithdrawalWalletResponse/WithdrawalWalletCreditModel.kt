package com.wizarpos.emvsample.models.WithdrawalWalletResponse

import com.wizarpos.emvsample.models.WithdrawalResponseDescription.description


data class WithdrawalWalletCreditModel(val transactionID : String , val status : Int, val error : Boolean, val message : String, val description : description, val convenienceFee : Double, val amountSettled : Int, val percentageCharged : Int, val beneficiaryName : String, val beneficiaryWallet : String, val reference : String)

data class description(val status : Int, val message: String, val description: description, val insertId :  Int)


