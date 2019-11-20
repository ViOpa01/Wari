package com.wizarpos.emvsample;

import android.content.Context;
import android.util.Log;

import com.iisysgroup.poslib.ISO.common.Constants;
import com.iisysgroup.poslib.ISO.common.IsoAdapter;
import com.iisysgroup.poslib.ISO.common.IsoTimeManager;
import com.iisysgroup.poslib.ISO.common.IsoTransactionExecutor;
import com.iisysgroup.poslib.ISO.common.IsoUtility;
import com.iisysgroup.poslib.ISO.common.TlvHelper;
import com.iisysgroup.poslib.commons.TLV;
import com.iisysgroup.poslib.commons.TLVParser;
import com.iisysgroup.poslib.commons.TripleDES;
import com.iisysgroup.poslib.commons.Utility;
import com.iisysgroup.poslib.commons.dukpt.StringUtil;
import com.iisysgroup.poslib.commons.emv.EmvCard;
import com.iisysgroup.poslib.host.entities.ConnectionData;
import com.iisysgroup.poslib.host.entities.KeyHolder;
import com.iisysgroup.poslib.host.entities.TransactionResult;
import com.iisysgroup.poslib.utils.TransactionData;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.IsoValue;

import org.jpos.iso.ISOMsg;

import java.net.SocketTimeoutException;
import java.util.List;

public class VasCommunicator {
    protected static final String posDataCode = "510101511344101";
    protected static final String posEntryMode = "051";
    protected static final String posConditionCode = "00";
    protected static final String posPinCaptureMode = "12";
    protected static final String amountTransactionFee = "D00000000";
    protected Context context;
    protected ConnectionData connectionData;
    protected TransactionData transactionData;
    protected IsoTimeManager timeMgr = new IsoTimeManager();
    protected TransactionResult transactionResult;
    protected String amount;
    protected String track2Data;
    protected String iccData;
    protected String pan;
    protected String acquiringInstitutionIdCode;
    protected String merchantType;
    protected String merchantNameLocation;
    protected String cardAcceptorIdCode;
    protected String serviceCode;
    protected String terminalID;
    protected String retrievalRefNumber;
    protected String fromAccountType;
    protected String processingCode;
    protected String expiryDate;
    protected String panSequenceNumber = "001";
    protected String transmissionDateTime;
    protected String timeLocalTransaction;
    protected String dateLocalTransaction;
    protected String transactionCurrencyCode = "566";
    protected String sequenceNumber;
    protected KeyHolder keysh;


    public VasCommunicator(Context cont,TransactionData emvCard, ConnectionData connectingData, KeyHolder keyHold){
        this.context = cont;
        this.connectionData = connectingData;
        this.transactionData = emvCard;
        this.keysh = keyHold;
        this.amount = this.transactionData.getInputData().getAmount() + "";
        this.track2Data = this.transactionData.getEmvCard().getTrack2Data();
        this.iccData = this.transactionData.getEmvCard().getIccData();
        this.fromAccountType = this.transactionData.getInputData().getAccountType().ordinal() + "0";
        this.pan = TlvHelper.getPan(this.track2Data);
        this.expiryDate = TlvHelper.getExpiryDate(this.track2Data);
        this.serviceCode = TlvHelper.getServiceCode(this.track2Data);
        this.acquiringInstitutionIdCode = TlvHelper.getAcquiringInstitutionIdCode(this.track2Data);
        this.merchantType = this.transactionData.getConfigData().getConfigData("08004").toString();
        this.merchantNameLocation = this.transactionData.getConfigData().getConfigData("52040").toString();
        this.cardAcceptorIdCode = this.transactionData.getConfigData().getConfigData("03015").toString();
        this.transactionCurrencyCode = this.transactionData.getConfigData().getConfigData("05003").toString();
        this.terminalID = this.connectionData.getTerminalID();
        this.pullPanSequenceNumber();
        this.track2Data = this.track2Data.replace("F", "").replace("f", "");
        this.transmissionDateTime = this.timeMgr.getLongDate();
        this.timeLocalTransaction = this.timeMgr.getTime();
        this.dateLocalTransaction = this.timeMgr.getShortDate();
        this.retrievalRefNumber = Utility.padLeft(this.transmissionDateTime, 12, '0');
        this.sequenceNumber = this.retrievalRefNumber.substring(6);
        this.transactionResult = new TransactionResult();
    }


    private void pullPanSequenceNumber() {
        int index = this.iccData.toUpperCase().indexOf("5F34");
        if (index >= 0) {
            this.panSequenceNumber = this.iccData.substring(index + 6, index + 8);
        }

    }

    public TransactionResult processOnlineTransaction() {
        this.processingCode = "00" + this.fromAccountType + "00";
        try {
            IsoMessage isoMsge = new IsoMessage();
            isoMsge.setType(512);
            isoMsge.setField(2, new IsoValue(IsoType.LLVAR, this.pan));
            isoMsge.setField(3, new IsoValue(IsoType.ALPHA, this.processingCode, 6));
            isoMsge.setField(4, new IsoValue(IsoType.ALPHA, Utility.padLeft(this.amount, 12, '0'), 12));
            isoMsge.setField(7, new IsoValue(IsoType.ALPHA, this.transmissionDateTime, 10));
            isoMsge.setField(11, new IsoValue(IsoType.NUMERIC, Utility.padLeft(this.sequenceNumber + "", 6, '0'), 6));
            isoMsge.setField(12, new IsoValue(IsoType.ALPHA, this.timeLocalTransaction, 6));
            isoMsge.setField(13, new IsoValue(IsoType.ALPHA, this.dateLocalTransaction, 4));
            isoMsge.setField(14, new IsoValue(IsoType.ALPHA, this.expiryDate, 4));
            isoMsge.setField(18, new IsoValue(IsoType.ALPHA, this.merchantType, 4));
            isoMsge.setField(22, new IsoValue(IsoType.ALPHA, "051", 3));
            isoMsge.setField(23, new IsoValue(IsoType.ALPHA, this.getPanSequenceNumber(), 3));
            isoMsge.setField(25, new IsoValue(IsoType.ALPHA, "00", 2));
            isoMsge.setField(26, new IsoValue(IsoType.ALPHA, "12", 2));
            isoMsge.setField(28, new IsoValue(IsoType.ALPHA, "D00000000", 9));
            isoMsge.setField(32, new IsoValue(IsoType.LLVAR, this.acquiringInstitutionIdCode));
            isoMsge.setField(35, new IsoValue(IsoType.LLVAR, this.track2Data));
            isoMsge.setField(37, new IsoValue(IsoType.ALPHA, this.retrievalRefNumber, 12));
            isoMsge.setField(40, new IsoValue(IsoType.ALPHA, this.serviceCode, 3));
            isoMsge.setField(41, new IsoValue(IsoType.ALPHA, this.terminalID, 8));
            isoMsge.setField(42, new IsoValue(IsoType.ALPHA, this.cardAcceptorIdCode, 15));
            isoMsge.setField(43, new IsoValue(IsoType.ALPHA, this.merchantNameLocation, 40));
            isoMsge.setField(49, new IsoValue(IsoType.ALPHA, this.transactionCurrencyCode, 3));
            String pinBlock = this.getDecryptedPinBlock(this.transactionData.getEmvCard().getPinInfo(), this.keysh);
            if (!pinBlock.isEmpty()) {
                isoMsge.setField(52, new IsoValue(IsoType.ALPHA, pinBlock, 16));
            }

            isoMsge.setField(55, new IsoValue(IsoType.LLLVAR, this.iccData));
            isoMsge.setField(123, new IsoValue(IsoType.LLLVAR, "510101511344101"));
            isoMsge.setField(128, new IsoValue(IsoType.ALPHA, "", 64));
            String isoMessage = (new String(isoMsge.writeData())).trim();

            for(int i = 0; i <= 128; ++i) {
                if (isoMsge.hasField(i)) {
                    Log.d("Iso message", "Field-" + i + ": " + isoMsge.getField(i));
                }
            }

            String hash = TripleDES.generateHash256Value(isoMessage, keysh.getSessionKey());
            Log.i("Iso field 128", hash);
            isoMessage = isoMessage + hash.toUpperCase();
            this.buildTransactionResultData(isoMsge);
            String response = IsoTransactionExecutor.execute(this.context, IsoAdapter.prepareByteStream(isoMessage.getBytes("UTF-8")), this.connectionData);
            Log.i("Iso execution response", response);
            ISOMsg isoMsg = (new IsoAdapter(this.context)).processISOBitStream(response);
            this.processHostResponse(isoMsg);
        } catch (Exception var7) {
            var7.printStackTrace();
            this.handleException(var7, true);
        }

        return this.transactionResult;
    }

    protected String getPanSequenceNumber() {
        return Utility.padLeft(this.panSequenceNumber, 3, '0');
    }

    protected String getDecryptedPinBlock(EmvCard.PinInfo pinInfo, KeyHolder keyHolder) {
        String reEncryptedPinBlock = "";

        try {
            if (pinInfo == null) {
                return reEncryptedPinBlock;
            } else {
                if (pinInfo.getPinBlock() != null) {
                    String pinKey = keyHolder.getPinKey();
                    String decryptingKey = StringUtil.toHexString(pinInfo.getKey());
                    String clearPinBlock = TripleDES.threeDesDecrypt(StringUtil.toHexString(pinInfo.getPinBlock()), decryptingKey);
                    reEncryptedPinBlock = Utility.tripleDesEncrypt(pinKey, clearPinBlock);
                }

                return reEncryptedPinBlock;
            }
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }



    protected void buildTransactionResultData(IsoMessage isoMessage) {
        this.transactionResult.cardHolderName = this.transactionData.getEmvCard().getCardHolderName();
        this.transactionResult.cardExpiry = this.expiryDate;
        this.transactionResult.terminalID = this.connectionData.getTerminalID();
        this.transactionResult.accountType = IsoUtility.getAccountTypeString(isoMessage.getField(3).toString());
        this.transactionResult.amount = (long)Integer.parseInt(isoMessage.getField(4).toString());
        this.transactionResult.longDateTime = System.currentTimeMillis();
        this.transactionResult.merchantID = isoMessage.getField(42).toString();
        this.transactionResult.STAN = isoMessage.getField(11).toString();
        this.transactionResult.RRN = isoMessage.getField(37).toString();
        this.transactionResult.PAN = IsoUtility.processPan(isoMessage.getField(2).toString());
        this.transactionResult.isoTransmissionDateTime = isoMessage.getField(7).toString();
        this.transactionResult.transactionType = IsoUtility.getTransactionType(isoMessage.getField(3).toString());
        this.transactionResult.transactionStatus = "Pending";
    }
    protected void processHostResponse(ISOMsg isoMsg) {
        String responseCode = IsoAdapter.getResponseDataFromIndex(isoMsg, 39);
        this.transactionResult.responseCode = responseCode;
        if (responseCode.equals("00")) {
            this.transactionResult.transactionStatus = "Approved";
        } else {
            this.transactionResult.transactionStatus = "Declined";
        }

        this.transactionResult.transactionStatusReason = Constants.getResponseMessageFromCode(responseCode);
        if (isoMsg.hasField(38)) {
            this.transactionResult.authID = isoMsg.getString(38);
        }

        this.transactionResult.RRN = isoMsg.getString(37);
        this.transactionResult.STAN = isoMsg.getString(11);
        if (isoMsg.hasField(33)) {
            this.transactionResult.originalForwardingInstitutionCode = isoMsg.getString(33);
        }

        if (isoMsg.hasField(55)) {
            String DE55 = isoMsg.getString(55);
            this.getIssuerResponseFromDE55(DE55);
        }

    }

    protected void getIssuerResponseFromDE55(String DE55) {
        List<TLV> tagList = TLVParser.parse(DE55);
        TLVParser.printTLVList(tagList, 2);
        TLV tag91 = TLVParser.searchTLV(tagList, "91");
        if (tag91 != null) {
            this.transactionResult.issuerAuthData91 = tag91.value;
        }

        TLV tag71 = TLVParser.searchTLV(tagList, "71");
        if (tag71 != null) {
            this.transactionResult.issuerScript71 = tag71.value;
        }

        TLV tag72 = TLVParser.searchTLV(tagList, "72");
        if (tag72 != null) {
            this.transactionResult.issuerScript72 = tag72.value;
        }

    }

    protected void handleException(Exception e, boolean canRollBack) {
        e.printStackTrace();
        if (!(e instanceof IndexOutOfBoundsException) && !(e instanceof SocketTimeoutException)) {
            String responseMessage;
            if (e.getCause() != null) {
                responseMessage = e.getCause().getMessage();
            } else if (e.getMessage() != null) {
                responseMessage = e.getMessage();
            } else {
                responseMessage = "Reason unknown";
            }

            this.transactionResult.transactionStatus = "Declined";
            this.transactionResult.transactionStatusReason = responseMessage;
        } else {
            if (canRollBack) {
                //this.doRollBack();
            } else {
                this.transactionResult.transactionStatusReason = "No response from server";
                this.transactionResult.transactionStatus = "Declined";
            }

        }
    }

}
