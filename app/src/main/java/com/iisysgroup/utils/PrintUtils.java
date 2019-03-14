package com.iisysgroup.utils;

import android.app.Activity;
import android.text.TextUtils;

import com.newland.mtype.module.common.printer.PrintContext;
import com.newland.mtype.module.common.printer.Printer;
import com.newland.mtype.module.common.printer.PrinterResult;
import com.newland.mtype.module.common.printer.PrinterStatus;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/9/20.
 */
public class PrintUtils {

    public  static  int  printerMsg(Activity activity, Printer printer , String cardNo, String amount) throws UnsupportedEncodingException {
        return  printerMsg(activity,printer,cardNo,amount,null);
    }

    public  static  int  printerMsg(Activity activity, Printer printer , String cardNo, String amount, String type) throws UnsupportedEncodingException {
        printer.init();
        PrinterStatus status = printer.getStatus();
        if (status == PrinterStatus.BUSY) {
            DialogUtils.getPromptDialog(activity,"","Printer is busy");
            return 4;
        }
        if (status == PrinterStatus.FLASH_READWRITE_ERROR) {
//            warningAlert("Flash read-write error");
            DialogUtils.getPromptDialog(activity,"","Flash read-write error");
            return 3;
        }
        if (status == PrinterStatus.HEAT_LIMITED) {
//            warningAlert(" Heat limit exceeded");
            DialogUtils.getPromptDialog(activity,"","Heat limit exceeded");
            return 2;
        }
        if (status == PrinterStatus.OUTOF_PAPER) {
//            warningAlert(" Out of paper");
            DialogUtils.getPromptDialog(activity,"","Out of paper");
            return 1;
        }
        if (TextUtils.isEmpty(cardNo)){
            cardNo="621226*********1973";
        }

        cardNo= cardNo.substring(0,5)+"******"+cardNo.substring(cardNo.length()-6,cardNo.length());
        StringBuffer scriptBuffer =getScriptStr(cardNo,amount,type);
        PrinterResult printerResult= printer.printByScript(PrintContext.defaultContext(), scriptBuffer.toString().getBytes("GBK"), 60, TimeUnit.SECONDS);

        if (printerResult.equals(PrinterResult.SUCCESS)) {
            MyLogger.jLog().e("脚本打印成功！" + "\r\n");
            return  0;
        } else {
            MyLogger.jLog().e("脚本打印失败!" + printerResult.toString());
            return  -1;
        }
    }


    //unionpay
    private static StringBuffer getScriptStr(String cardNo, String amount, String type){
        StringBuffer scriptBuffer = new StringBuffer();
//        scriptBuffer.append("!NLPRNOVER"); //走纸
        scriptBuffer.append("!hz l\n !asc l\n !gray 10\n");// 设置标题字体为大号
        // ,取值【0,60】，默认6
        scriptBuffer.append("*text l - - x - - - - - - x - - \n");
        scriptBuffer.append("*text c Unionpay\n");


        scriptBuffer.append("!yspace 2\n");// 设置行间距
        scriptBuffer.append("*line" + "\n");// 打印虚线
        scriptBuffer.append("!hz n\n !asc s !gray 5\n");// 设置内容字体为xio号
        scriptBuffer.append("*text l CUSTOMER COPY\n");
        scriptBuffer.append("*line" + "\n");// 打印虚线


        scriptBuffer.append("!hz n\n !asc n !gray 5\n");// 设置内容字体为中号
        scriptBuffer.append("!yspace 8\n");// 设置内容行间距
        scriptBuffer.append("*text l MERCHANT NAME:China Unionpay\n");

        scriptBuffer.append("*text l MERCHANT No.:123455432112345\n");
        scriptBuffer.append("*text l TERMINAL NO:20170917\n");
        scriptBuffer.append("*text l OPERATOR NO:06\n");
        scriptBuffer.append("*line" + "\n");// 打印虚线


        scriptBuffer.append("*text l ACQUIRER:115681555223\n");
        scriptBuffer.append("*text l ISSUER: 8736367223\n");
        scriptBuffer.append("*text l CARD NO.:\n");
        scriptBuffer.append("!hz l\n !asc l\n !gray 10\n");// 设置标题字体为大号
         String cNo ="*text l "+cardNo+"\n";
        scriptBuffer.append(cNo);
        scriptBuffer.append("!hz n\n !asc n !gray 5\n");// 设置内容字体为中号
        scriptBuffer.append("*text l EXP DATE:1225\n");
        scriptBuffer.append("*text l SEQUENCE:000\n");
        scriptBuffer.append("*text l TRANS TYPE:\n");
        scriptBuffer.append("!hz l\n !asc l\n !gray 5\n");// 设置标题字体为大号

        String transtype ="SALE";
        if (TextUtils.isEmpty(type)||type.equalsIgnoreCase("Pay")){
            transtype ="SALE";
        }else if (type.equalsIgnoreCase("refund")){
            transtype ="FEFUND";
        }else if (type.equalsIgnoreCase("revroked")){
            transtype ="VOID";
        }else {
            transtype ="SALE";
        }
        String scriptranstype ="*text l "+transtype+"\n";
        scriptBuffer.append(scriptranstype);

        scriptBuffer.append("!hz n\n !asc n !gray 5\n");// 设置内容字体为中号
        // scriptBuffer.append("!hz s\n !asc s\n !gray 10\n");//
        // 设置内容字体为中号
        scriptBuffer.append("*text l BATCH NO.:00001\n");
        scriptBuffer.append("*text l VOUCHER NO.:00095\n");
        scriptBuffer.append("*text l AUTH. NO.:00001\n");
        scriptBuffer.append("*text l REFER NO.:00095\n");
        scriptBuffer.append("*text l DATE/TIME:\n");
        scriptBuffer.append("*text r 2017/09/25 11:41:06\n");
        scriptBuffer.append("*text l AMOUNT:\n");
        scriptBuffer.append("!hz l\n !asc l\n !gray 10\n");// 设置标题字体为大号
        String amounts ="*text l $: "+amount+"\n";
        scriptBuffer.append(amounts);
        scriptBuffer.append("!hz n\n !asc n !gray 5\n");// 设置内容字体为中号
        scriptBuffer.append("*line" + "\n");// 打印虚线
        scriptBuffer.append("*text l REFERENCE:\n");
        scriptBuffer.append("!hz n\n !asc s !gray 5\n");// 设置内容字体为中号
        scriptBuffer.append("!yspace 2\n");// 设置内容行间距
        if (TextUtils.isEmpty(type)||type.equalsIgnoreCase("Pay")){
            scriptBuffer.append("*text l AQRC:DCBA10028710101\n");
            scriptBuffer.append("*text l AID:A000000333010101\n");
            scriptBuffer.append("*text l CSN:001 CVM:020301\n");
        }else if (type.equalsIgnoreCase("refund")){
            scriptBuffer.append("*text l AQRC:DCBA10028710101\n");
            scriptBuffer.append("*text l Original transactions date:\n");
            scriptBuffer.append("*text r 2017/09/25 11:41:06\n");
        }else if (type.equalsIgnoreCase("revroked")){
            scriptBuffer.append("*text l VOUCHER NO.:00095\n");
        }
        scriptBuffer.append("*line" + "\n");// 打印虚线
        scriptBuffer.append("!hz sn\n !asc sn\n !gray 10\n");//
        scriptBuffer.append("*text l CARD HOLDER SIGNATURE:\n");
        scriptBuffer.append("*feedline 5\n");

        scriptBuffer.append("*text l I ACKNOWLEDGE SATISFACTORY RECEIPT OF RELATIVE GOODS/SERVICES\n");
        scriptBuffer.append("*text l HOTLINE: 05916666\n");
        scriptBuffer.append("*text l - - - - - - - X - - - - - - - X - - - - - - - \n");
        scriptBuffer.append("!NLPRNOVER"); //走纸
        return  scriptBuffer;
    }


    public static int printerCashMsg(Activity activity, Printer printer, String s) {
        printer.init();
        PrinterStatus status = printer.getStatus();
        if (status == PrinterStatus.BUSY) {
            DialogUtils.getPromptDialog(activity,"","Printer is busy");
            return 4;
        }
        if (status == PrinterStatus.FLASH_READWRITE_ERROR) {
//            warningAlert("Flash read-write error");
            DialogUtils.getPromptDialog(activity,"","Flash read-write error");
            return 3;
        }
        if (status == PrinterStatus.HEAT_LIMITED) {
//            warningAlert(" Heat limit exceeded");
            DialogUtils.getPromptDialog(activity,"","Heat limit exceeded");
            return 2;
        }
        if (status == PrinterStatus.OUTOF_PAPER) {
            DialogUtils.getPromptDialog(activity,"","Out of paper");
            return 1;
        }
         StringBuffer scriptBuffer =getScriptStr(s,"");
        PrinterResult printerResult= null;
        try {
            printerResult = printer.printByScript(PrintContext.defaultContext(), scriptBuffer.toString().getBytes("GBK"), 60, TimeUnit.SECONDS);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (printerResult.equals(PrinterResult.SUCCESS)) {
            MyLogger.jLog().e("脚本打印成功！" + "\r\n");
            return  0;
        } else {
            MyLogger.jLog().e("脚本打印失败!" + printerResult.toString());
            return  -1;
        }
    }

    //Cash Pay
    private static StringBuffer getScriptStr(String amount, String type){
        StringBuffer scriptBuffer = new StringBuffer();
//        scriptBuffer.append("!NLPRNOVER"); //走纸
        scriptBuffer.append("!hz l\n !asc l\n !gray 10\n");// 设置标题字体为大号
        // ,取值【0,60】，默认6
        scriptBuffer.append("*text l - - x - - - - - - x - - \n");
        scriptBuffer.append("*text c Cashpay\n");

        scriptBuffer.append("!yspace 2\n");// 设置行间距
        scriptBuffer.append("*line" + "\n");// 打印虚线
        scriptBuffer.append("!hz n\n !asc s !gray 5\n");// 设置内容字体为xio号
        scriptBuffer.append("*text l CUSTOMER COPY\n");
        scriptBuffer.append("*line" + "\n");// 打印虚线
        scriptBuffer.append("*text l TRANS TYPE:\n");
        scriptBuffer.append("!hz l\n !asc l\n !gray 5\n");// 设置标题字体为大号
        String transtype ="CASH";

        String scriptranstype ="*text l "+transtype+"\n";
        scriptBuffer.append(scriptranstype);

        scriptBuffer.append("!hz n\n !asc n !gray 5\n");// 设置内容字体为中号
        // scriptBuffer.append("!hz s\n !asc s\n !gray 10\n");//
        // 设置内容字体为中号
        scriptBuffer.append("*text l VOUCHER NO.:00095\n");
        scriptBuffer.append("*text l DATE/TIME:\n");
        scriptBuffer.append("*text r 2017/09/25 11:41:06\n");
        scriptBuffer.append("*text l AMOUNT:\n");
        scriptBuffer.append("!hz l\n !asc l\n !gray 10\n");// 设置标题字体为大号
        String amounts ="*text l $: "+amount+"\n";
        scriptBuffer.append(amounts);
        scriptBuffer.append("!hz n\n !asc n !gray 5\n");// 设置内容字体为中号
        scriptBuffer.append("*line" + "\n");// 打印虚线
        scriptBuffer.append("*text l REFERENCE:\n");
        scriptBuffer.append("!hz n\n !asc s !gray 5\n");// 设置内容字体为中号
        scriptBuffer.append("!yspace 2\n");// 设置内容行间距

        scriptBuffer.append("*line" + "\n");// 打印虚线
        scriptBuffer.append("!hz sn\n !asc sn\n !gray 10\n");//
        scriptBuffer.append("*feedline 2\n");

        scriptBuffer.append("*text l I ACKNOWLEDGE SATISFACTORY RECEIPT OF RELATIVE GOODS/SERVICES\n");
        scriptBuffer.append("*text l HOTLINE: 05916666\n");
        scriptBuffer.append("*text l - - - - - - - X - - - - - - - X - - - - - - - \n");
        scriptBuffer.append("!NLPRNOVER"); //走纸
        return  scriptBuffer;
    }

}
