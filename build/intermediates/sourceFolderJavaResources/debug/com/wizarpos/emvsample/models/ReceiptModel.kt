package com.wizarpos.emvsample.models

import java.io.Serializable

data class ReceiptModel(val date : String, val transactionType : String, val transactionStatus : String, val map : LinkedHashMap<String, String>, val amount : String, val transactionStatusReason : String) : Serializable