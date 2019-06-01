package com.wizarpos.emvsample.models.WithdrawalWalletResponse

import com.wizarpos.emvsample.models.WithdrawalResponseDescription.description


data class WithdrawalWalletCreditModel(val transactionID : Int, val status : Int, val error : String, val message : String, val description : description, val convenienceFee : Double, val amountSettled : Int, val percentageCharged : Int, val beneficiaryName : String, val beneficiaryWallet : String, val reference : String)

data class description(val status : Int, val message: String, val description: description, val insertId :  Int)


