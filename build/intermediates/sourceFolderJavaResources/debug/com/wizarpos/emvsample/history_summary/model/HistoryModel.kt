package com.iisysgroup.gtb_agency_banking.history_summary.model

data class HistoryModel(val data : List<HistoryData>, val error : Boolean)

data class HistoryData(val ref : String, val service : String, val amount : String, val phone : String, val status : Boolean, val date : String)
