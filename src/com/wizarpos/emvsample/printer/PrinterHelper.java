package com.wizarpos.emvsample.printer;

import android.text.format.DateFormat;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.db.EodModel;
import com.wizarpos.emvsample.db.TransDetailInfo;
import com.wizarpos.emvsample.parameter.BalanceResponse;
import com.wizarpos.emvsample.transaction.Nibss;
import com.wizarpos.jni.PrinterInterface;
import com.wizarpos.emvsample.MainApp;
import com.wizarpos.emvsample.R;
import com.wizarpos.util.AppUtil;
import com.wizarpos.util.StringUtil;

/**
 * 打印操作
 * 
 * @author lianyi
 */
public class PrinterHelper 
{
    private static PrinterHelper _instance;

    private PrinterHelper() {
    }

    synchronized public static PrinterHelper getInstance() 
    {
		if (null == _instance){
		    _instance = new PrinterHelper();
		}
		return _instance;
    }

    /**
     * 打印签购单
     * 
     * @throws PrinterException
     */
    synchronized public void printReceipt(MainApp appState, int receipt) throws PrinterException
    {
		try {
		    PrinterInterface.open();
		    PrinterInterface.begin();
	
		    printerWrite(PrinterCommand.init());
		    printerWrite(PrinterCommand.setHeatTime(180));
		    printerWrite(PrinterCommand.setAlignMode(1));
		    printerWrite(PrinterCommand.setFontBold(1));
		    printerWrite(PrinterCommand.feedLine(2));
		    printerWrite(PrinterCommand.setAlignMode(0));
		    //Merchant name
		    printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setFontBold(1));
			printerWrite(PrinterCommand.setAlignMode(49));
			printerWrite(PrinterCommand.setFont(0));
			String[] nameArr = appState.terminalConfig.getMerchantName1().split(" ");
			nameArr = Arrays.copyOf(nameArr, nameArr.length -2);
		    printerWrite((StringUtil.join(nameArr," ")).getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setFontBold(0));
			printerWrite(PrinterCommand.setFont(0));
			    
	    	if(receipt == 0)
		    {
		    	printerWrite(("*** MERCHANT COPY ***").getBytes("GB2312"));
			    printerWrite(PrinterCommand.linefeed());
		    }
		    else if(receipt == 1)
		    {
		    	printerWrite(("** CUSTOMER COPY **").getBytes("GB2312"));
		    	
			    printerWrite(PrinterCommand.linefeed());
		    }
		    else if(receipt == 2)
		    {
		    	printerWrite(("**** BANK COPY ****").getBytes("GB2312"));
			    printerWrite(PrinterCommand.linefeed());
		    }
			printerWrite(PrinterCommand.setFontBold(1));
			if(appState.trans.isTransactionStatus()){
				printerWrite("APPROVED".getBytes("GB2312"));
				printerWrite(PrinterCommand.linefeed());
			}
			else{
				printerWrite("DECLINED".getBytes("GB2312"));
				printerWrite(PrinterCommand.linefeed());
				printerWrite((appState.trans.getTransactionResult().responseCode + " " + appState.trans.getTransactionResult().transactionStatusReason).getBytes("GB2312"));
				printerWrite(PrinterCommand.linefeed());
			}
			printerWrite(PrinterCommand.setFontBold(0));

			printerWrite(PrinterCommand.setAlignMode(0));
		    printerWrite("--------------------------------".getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed());
			printerWrite((appState.getString(R.string.tid_tag) + " " + appState.nibssData.getConnectionData().getTerminalID()).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			printerWrite((appState.getString(R.string.mid_tag) + " " + appState.nibssData.getConfigData().getConfigData("03015")).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			printerWrite(("Transaction Type: " + " " + appState.trans.getTransactionType()).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			printerWrite(("Customer: " + " " + appState.trans.getCardHolderName()).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
 
		    String pan = appState.getString(R.string.pan_tag) + " " + appState.trans.getMaskPan();
		    switch(appState.trans.getCardEntryMode())
		    {
		    case 0:
		    	pan = pan ;
		    	break;
		    case Constants.SWIPE_ENTRY:
		    	pan = pan ;
		    	break;
		    case Constants.INSERT_ENTRY:
		    	pan = pan ;
		    	break;
		    case Constants.MANUAL_ENTRY:
		    	pan = pan ;
		    	break;
		    default:
		    	pan = pan ;
		    	break;
		    }
		    printerWrite(("RRN: " + appState.trans.getRrn()).getBytes("GB2312") );
			printerWrite(PrinterCommand.linefeed());

		    printerWrite(pan.getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed());
		    
		    printerWrite((  appState.getString(R.string.date_tag) 
		    		      + " " + appState.trans.getTransDate().substring(0, 4)
		    		      + "/" + appState.trans.getTransDate().substring(4, 6)
		    		      + "/" + appState.trans.getTransDate().substring(6, 8)
		    		      + " " + appState.trans.getTransTime().substring(0, 2)
		    		      + ":" + appState.trans.getTransTime().substring(2, 4)
		    		      + ":" + appState.trans.getTransTime().substring(4, 6) ).getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed());
		    
		    printerWrite(( "TICKET:" + StringUtil.fillZero(Integer.toString(appState.trans.getTrace()), 6)).getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed()); 
	    
		   /* printerWrite(appState.getString(TransDefine.transInfo[appState.trans.getTransType()].id_display_en).getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed());*/
		    
		   /* printerWrite(("AMOUNT:" + StringUtil.fillString(AppUtil.formatAmount(appState.trans.getTransAmount()), 22, ' ', true)).getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed());*/

		    if(appState.trans.getCardEntryMode() != Constants.SWIPE_ENTRY)
		    {
			  /*  printerWrite(("CSN:" + StringUtil.fillZero(Byte.toString(appState.trans.getCSN()),2)).getBytes());
			    printerWrite(PrinterCommand.linefeed());*/
		    	
			    printerWrite(("UNPR NUM:" + appState.trans.getUnpredictableNumber()).getBytes());
			    printerWrite(PrinterCommand.linefeed());
			    
		    	printerWrite(("AC:" + appState.trans.getAC()).getBytes());
			    printerWrite(PrinterCommand.linefeed());
			    
			    printerWrite(("TVR:" + appState.trans.getTVR()).getBytes());
			    printerWrite(PrinterCommand.linefeed());
			    
			    printerWrite(("AID:" + appState.trans.getAID()).getBytes());
			    printerWrite(PrinterCommand.linefeed());
			    
			    printerWrite(("TSI:" + appState.trans.getTSI()).getBytes());
			    printerWrite(PrinterCommand.linefeed());
			    
			   /* printerWrite(("APPLAB:" + appState.trans.getAppLabel()).getBytes());
			    printerWrite(PrinterCommand.linefeed());*/
			    
			    printerWrite(("Card Type:" + appState.trans.getAppName()).getBytes());
			    printerWrite(PrinterCommand.linefeed());
			    
			    printerWrite(("AIP:" + appState.trans.getAIP()).getBytes());
			    printerWrite(PrinterCommand.linefeed());

			    if(appState.trans.isPurchasewithCash()){
					printerWrite(("TRANS AMOUNT: NGN" + AppUtil.formatAmount(appState.trans.getTransAmount())).getBytes());
					printerWrite(PrinterCommand.linefeed());
					printerWrite(("CASHBACK AMount: NGN" + AppUtil.formatAmount(appState.trans.getOthersAmount())).getBytes());
					printerWrite(PrinterCommand.linefeed());
				}
			    
			  /*  printerWrite(("IAD:" + appState.trans.getIAD()).getBytes());
			    printerWrite(PrinterCommand.linefeed());*/
			    
			    //printerWrite(("TermCap:" + appState.terminalConfig.getTerminalCapabilities()).getBytes());
			    printerWrite(PrinterCommand.linefeed());
		    }
		    printerWrite(PrinterCommand.feedLine(1));
            printerWrite("--------------------------------".getBytes("GB2312"));
            printerWrite(PrinterCommand.feedLine(1));
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(1));
            printerWrite("***********".getBytes("GB2312"));
            if(appState.trans.isBalanceTxn()){
				printerWrite(PrinterCommand.linefeed());
				if(appState.trans.getTransactionResult().isApproved()){
					BalanceResponse balanceResponse = new Gson().fromJson(appState.trans.getTransactionResult().transactionStatusReason, BalanceResponse.class);
					printerWrite(("Account Balance is ").getBytes("GB2312"));
					printerWrite(PrinterCommand.linefeed());
					printerWrite((balanceResponse.getAccountBalance()).getBytes("GB2312"));
				}else{
					printerWrite((appState.trans.getTransactionResult().responseCode + " "+ appState.trans.getTransactionResult().transactionStatus).getBytes("GB2312"));
				}

				printerWrite(PrinterCommand.linefeed());

			}
			else if(appState.trans.isRefundTxn()){
				printerWrite(PrinterCommand.linefeed());
				if(appState.trans.getTransactionResult().isApproved()){
					printerWrite(("Refunded NGN" + AppUtil.formatAmount(appState.trans.getTransAmount())).getBytes("GB2312"));

				}else{
					printerWrite((appState.trans.getTransactionResult().transactionStatus ).getBytes("GB2312"));

				}
				printerWrite(PrinterCommand.linefeed());
				printerWrite(PrinterCommand.linefeed());

			}
			else {
				printerWrite(PrinterCommand.linefeed());
				printerWrite(("NGN "+AppUtil.formatAmount(appState.trans.getTransAmount() + appState.trans.getOthersAmount())).getBytes("UTF-8"));
				printerWrite(PrinterCommand.linefeed());
			}
            printerWrite("***********".getBytes("GB2312"));
		    printerWrite(PrinterCommand.feedLine(1));
			printerWrite(PrinterCommand.setAlignMode(0));
            printerWrite("--------------------------------".getBytes("GB2312"));
            printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(0));
            printerWrite("WARI".getBytes("GB2312"));
            printerWrite(PrinterCommand.linefeed());
			printerWrite("www.iisysgroup.com".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite("0700-2255-4839".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setAlignMode(0));
            printerWrite("--------------------------------".getBytes("GB2312"));
            printerWrite(PrinterCommand.feedLine(1));
            printerWrite(PrinterCommand.feedLine(3));
        } catch (UnsupportedEncodingException e) {
		    throw new PrinterException("PrinterHelper.printReceipt():" + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
		    throw new PrinterException(e.getMessage(), e);
		} finally {
		    PrinterInterface.end();
		    PrinterInterface.close();
		}
    }
    
    public void printerWrite(byte[] data)
    {
    	PrinterInterface.write(data, data.length);
    }

    synchronized public void printEod(MainApp appState,List<EodModel> results) throws PrinterException {
		try {
			PrinterInterface.open();
			PrinterInterface.begin();

			printerWrite(PrinterCommand.init());
			printerWrite(PrinterCommand.setHeatTime(180));
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(1));
			printerWrite(PrinterCommand.feedLine(2));
			printerWrite(PrinterCommand.setAlignMode(0));
			//Merchant name
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setFontBold(1));
			printerWrite(PrinterCommand.setAlignMode(49));
			printerWrite(PrinterCommand.setFont(0));
			String[] nameArr = appState.terminalConfig.getMerchantName1().split(" ");
			nameArr = Arrays.copyOf(nameArr, nameArr.length -2);
			printerWrite((StringUtil.join(nameArr," ")).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setFontBold(0));
			printerWrite(PrinterCommand.setFont(0));
            int[] tottalCount = countTrans(results);
			long[] sumtotal = sumTras(results);
			printerWrite(PrinterCommand.linefeed());
			printerWrite(("Total Transaction count: " + results.size()).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(("Approved Transaction Count: " + tottalCount[0]).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(("Decline Transaction Count: " + tottalCount[1]).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			printerWrite(("Approved Transaction Amount: " + AppUtil.formatAmount(sumtotal[0])).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			printerWrite(("Decline Transaction Amount: " + AppUtil.formatAmount(sumtotal[1]) ).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
            int i = 1;
            printerWrite(PrinterCommand.setFont(0));
			for (EodModel tr: results) {
				String sta = "";
				if(tr.getResults().isApproved()){
					sta = "A";
				}else{
					sta = "D";
				}
				printerWrite((i + ". " + tr.getResults().PAN +"|"+ AppUtil.formatAmount(tr.getResults().amount)  +"|"+tr.getResults().RRN + "|" + sta).getBytes("GB2312"));
				printerWrite(PrinterCommand.linefeed());
				i++;
			}
			printerWrite(PrinterCommand.setFont(0));

			printerWrite(PrinterCommand.feedLine(1));
			printerWrite(PrinterCommand.setAlignMode(0));
			printerWrite("--------------------------------".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(0));
			printerWrite("WARI ".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite("www.iisysgroup.com".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite("0700-2255-4839".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setAlignMode(0));
			printerWrite("--------------------------------".getBytes("GB2312"));
			printerWrite(PrinterCommand.feedLine(1));
			printerWrite(PrinterCommand.feedLine(3));


		}catch (UnsupportedEncodingException e) {
			throw new PrinterException("PrinterHelper.printReceipt():" + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new PrinterException(e.getMessage(), e);
		} finally {
			PrinterInterface.end();
			PrinterInterface.close();
		}

	}

	private int[] countTrans(List<EodModel> results){
    	int[] total = new int[]{0,0,};
		for (EodModel tr: results) {
			if(tr.getResults().isApproved()){
				total[0] += 1;
			}
			else{
				total[1] += 1;
			}
		}
		return total;
	}

	private long[] sumTras(List<EodModel> results){
		long[] total = new long[]{0,0,};
		for (EodModel tr: results) {
			if(tr.getResults().isApproved()){
				total[0] += tr.getResults().amount;
			}
			else{
				total[1] +=tr.getResults().amount;
			}
		}
		return total;
	}


	synchronized public void latstTraa(MainApp appState, int receipt, EodModel eodModel, TransDetailInfo transDetailInfo) throws PrinterException
	{
		try {
			PrinterInterface.open();
			PrinterInterface.begin();

			printerWrite(PrinterCommand.init());
			printerWrite(PrinterCommand.setHeatTime(180));
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(1));
			printerWrite(PrinterCommand.feedLine(2));
			printerWrite(PrinterCommand.setAlignMode(0));
			//Merchant name
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setFontBold(1));
			printerWrite(PrinterCommand.setAlignMode(49));
			printerWrite(PrinterCommand.setFont(0));
			String[] nameArr = appState.terminalConfig.getMerchantName1().split(" ");
			nameArr = Arrays.copyOf(nameArr, nameArr.length -2);
			printerWrite((StringUtil.join(nameArr," ")).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setFontBold(0));
			printerWrite(PrinterCommand.setFont(0));

			if(receipt == 0)
			{
				printerWrite(("*** MERCHANT COPY ***").getBytes("GB2312"));
				printerWrite(PrinterCommand.linefeed());
			}
			else if(receipt == 1)
			{
				printerWrite(("** CUSTOMER COPY **").getBytes("GB2312"));

				printerWrite(PrinterCommand.linefeed());
			}
			else if(receipt == 2)
			{
				printerWrite(("**** BANK COPY ****").getBytes("GB2312"));
				printerWrite(PrinterCommand.linefeed());
			}
			printerWrite(PrinterCommand.setFontBold(1));
			if(eodModel.getResults().isApproved()){
				printerWrite("APPROVED".getBytes("GB2312"));
				printerWrite(PrinterCommand.linefeed());
			}
			else{
				printerWrite("DECLINED".getBytes("GB2312"));
				printerWrite(PrinterCommand.linefeed());
			}
			printerWrite(PrinterCommand.setFontBold(0));

			printerWrite(PrinterCommand.setAlignMode(0));
			printerWrite("--------------------------------".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite((appState.getString(R.string.tid_tag) + " " + appState.nibssData.getConnectionData().getTerminalID()).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			printerWrite((appState.getString(R.string.mid_tag) + " " + appState.nibssData.getConfigData().getConfigData("03015")).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			printerWrite(("Customer: " + " " + eodModel.getResults().cardHolderName).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			String pan = appState.getString(R.string.pan_tag) + " " + eodModel.getResults().PAN;
			switch(appState.trans.getCardEntryMode())
			{
				case 0:
					pan = pan ;
					break;
				case Constants.SWIPE_ENTRY:
					pan = pan ;
					break;
				case Constants.INSERT_ENTRY:
					pan = pan ;
					break;
				case Constants.MANUAL_ENTRY:
					pan = pan ;
					break;
				default:
					pan = pan ;
					break;
			}
			printerWrite(("RRN: " + eodModel.getResults().RRN).getBytes("GB2312") );
			printerWrite(PrinterCommand.linefeed());

			printerWrite(pan.getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

			printerWrite((  appState.getString(R.string.date_tag)
					+ DateFormat.format("MM/dd/yyyy", new Date(eodModel.getResults().longDateTime)).toString()).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());

//			printerWrite(( "TICKET:" + StringUtil.fillZero(Integer.toString(transDetailInfo.getTrace()), 6)).getBytes("GB2312"));
//			printerWrite(PrinterCommand.linefeed());

		   /* printerWrite(appState.getString(TransDefine.transInfo[appState.trans.getTransType()].id_display_en).getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed());*/

		   /* printerWrite(("AMOUNT:" + StringUtil.fillString(AppUtil.formatAmount(appState.trans.getTransAmount()), 22, ' ', true)).getBytes("GB2312"));
		    printerWrite(PrinterCommand.linefeed());*/

			  /*  printerWrite(("CSN:" + StringUtil.fillZero(Byte.toString(appState.trans.getCSN()),2)).getBytes());
			    printerWrite(PrinterCommand.linefeed());*/



				/*printerWrite(("AC: Not avialable").getBytes());
				printerWrite(PrinterCommand.linefeed());

				printerWrite(("TVR:" + appState.trans.getTVR()).getBytes());
				printerWrite(PrinterCommand.linefeed());

				printerWrite(("AID:" + appState.trans.getAID()).getBytes());
				printerWrite(PrinterCommand.linefeed());

				printerWrite(("TSI:" + appState.trans.getTSI()).getBytes());
				printerWrite(PrinterCommand.linefeed());*/

			   /* printerWrite(("APPLAB:" + appState.trans.getAppLabel()).getBytes());
			    printerWrite(PrinterCommand.linefeed());*/

			  /*  printerWrite(("IAD:" + appState.trans.getIAD()).getBytes());
			    printerWrite(PrinterCommand.linefeed());*/

				//printerWrite(("TermCap:" + appState.terminalConfig.getTerminalCapabilities()).getBytes());
				printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.feedLine(1));
			printerWrite("--------------------------------".getBytes("GB2312"));
			printerWrite(PrinterCommand.feedLine(1));
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(1));
			printerWrite("***********".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(("NGN "+AppUtil.formatAmount(eodModel.getAmmount(),false)).getBytes("UTF-8"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite("***********".getBytes("GB2312"));
			printerWrite(PrinterCommand.feedLine(1));
			printerWrite(PrinterCommand.setAlignMode(0));
			printerWrite("--------------------------------".getBytes("GB2312"));
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(0));
			printerWrite("WARI ".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite("www.iisysgroup.com".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite("0700-2255-4839".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setAlignMode(0));
			printerWrite("--------------------------------".getBytes("GB2312"));
			printerWrite(PrinterCommand.feedLine(1));
			printerWrite(PrinterCommand.feedLine(3));
		} catch (UnsupportedEncodingException e) {
			throw new PrinterException("PrinterHelper.printReceipt():" + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new PrinterException(e.getMessage(), e);
		} finally {
			PrinterInterface.end();
			PrinterInterface.close();
		}
	}

	synchronized public void printConfiguration(Nibss.NIbbsData data) throws PrinterException {

		try {
			PrinterInterface.open();
			PrinterInterface.begin();

			printerWrite(PrinterCommand.init());
			printerWrite(PrinterCommand.setHeatTime(180));
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(1));
			printerWrite(PrinterCommand.feedLine(2));
			printerWrite(PrinterCommand.setAlignMode(0));
			//Merchant name
			printerWrite(PrinterCommand.setFontBold(0));

			printerWrite(PrinterCommand.setAlignMode(0));
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(("Configurartion complete").getBytes("GB2312"));
            printerWrite(PrinterCommand.linefeed());
			printerWrite(("Terminal Id: " + data.getConnectionData().getTerminalID()).getBytes("GB2312"));
            printerWrite(PrinterCommand.linefeed());
			printerWrite(("Host Ip: " + data.getConnectionData().getIpAddress()).getBytes("GB2312"));
            printerWrite(PrinterCommand.linefeed());
			printerWrite(("Sequence: " + data.getConnectionData().getSequenceNumber()).getBytes("GB2312"));
            printerWrite(PrinterCommand.linefeed());
			printerWrite(("Marchant ID: " + data.getConfigData().getConfigData("03015")).getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(("Marchant name: " + data.getConfigData().getConfigData("52040")).getBytes("GB2312"));

			printerWrite(PrinterCommand.linefeed());

			printerWrite("--------------------------------".getBytes("GB2312"));
			printerWrite(PrinterCommand.setAlignMode(1));
			printerWrite(PrinterCommand.setFontBold(0));
			printerWrite("WARI ".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite("www.iisysgroup.com".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite("0700-2255-4839".getBytes("GB2312"));
			printerWrite(PrinterCommand.linefeed());
			printerWrite(PrinterCommand.setAlignMode(0));
			printerWrite("--------------------------------".getBytes("GB2312"));
			printerWrite(PrinterCommand.feedLine(1));
			printerWrite(PrinterCommand.feedLine(3));
		} catch (UnsupportedEncodingException e) {
			throw new PrinterException("PrinterHelper.printReceipt():" + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new PrinterException(e.getMessage(), e);
		} finally {
			PrinterInterface.end();
			PrinterInterface.close();
		}

	}
}
