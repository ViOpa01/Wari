//package com.iisysgroup.payvice.startimes
//
//import com.iisgroup.bluetoothprinterlib.Manaer.PrintfManager
//import com.iisysgroup.payvice.R
//import com.iisysgroup.payvice.base.interactor.MultichoiceInteractor
//import com.iisysgroup.payvice.entities.ServiceResult
//import com.iisysgroup.payvice.entities.StatusObject
//import com.iisysgroup.payvice.util.toCurrencyString
//import com.itex.richard.payviceconnect.model.DstvModel
//import com.itex.richard.payviceconnect.model.StartimesModel
//
///**
// * Created by Olije Favour on 6/24/2019.
// *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
// */
//
//
//class StartimesResult (val data: ServiceResult.Data,
//                       val plan: String,
//                       val response: StartimesModel.payResponse) : ServiceResult {
//
//    override fun toStatusObject(): StatusObject {
//        val statusObject = StatusObject(!response.error)
//        statusObject.statusReason = response.message
//        return statusObject
//    }
//
//
//    override fun printResult(printer: PrintfManager) {
//        printer.printTabSpace(7)
//        printer.printText("${data.beneficiary.assignedProduct} Subscription")
//        printer.printfWrap(2)
//
//        val status = if (!response.error) {
//            StatusObject.STATUS_APPROVED
//        } else {
//            StatusObject.STATUS_DECLINED
//        }
//
//        printer.printTabSpace(6)
//        printer.printText(status.toUpperCase())
//        printer.printfWrap()
//
//        printer.printTwoColumn("Customer Name", data.beneficiary.displayInfo)
//        printer.printTwoColumn("IUC", data.beneficiary.data)
//        printer.printTwoColumn("Purchased Plan", plan)
////        printer.printTwoColumn("Date", response.date)
////        printer.printTwoColumn("Reference", response.ref)
//
//        printer.printTabSpace(8)
//        printer.printText("**********")
//        printer.printfWrap()
//
//        printer.printTabSpace(10)
//        printer.printText("N ${data.amount.toCurrencyString()}")
//        printer.printfWrap()
//
//        printer.printTabSpace(8)
//        printer.printText("**********")
//        printer.printfWrap(2)
//
//        printer.printText(response.message)
//        printer.printfWrap()
//    }
//
//    override fun getPrinterLogo(): Int? {
//
//        return when (data.beneficiary.assignedProduct) {
//            MultichoiceInteractor.MultichoiceProduct.DSTV.name -> R.drawable.dstv
//            MultichoiceInteractor.MultichoiceProduct.GOTV.name -> R.drawable.gotv
//            else -> R.drawable.startime
//        }
//    }
//}