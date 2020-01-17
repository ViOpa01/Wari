package com.wizarpos.emvsample.db.detailed;

import java.lang.System;

@android.arch.persistence.room.Entity()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\bV\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u00b7\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\u0002\u0010\u001bB\u00ed\u0001\u0012\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0003\u0012\b\b\u0002\u0010 \u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010!\u001a\u00020\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020\u0003\u0012\b\b\u0002\u0010#\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\u0002\u0010$J\u0010\u0010Z\u001a\u0004\u0018\u00010\u001dH\u00c6\u0003\u00a2\u0006\u0002\u0010DJ\t\u0010[\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\\\u001a\u00020\u0003H\u00c6\u0003J\t\u0010]\u001a\u00020\u0003H\u00c6\u0003J\t\u0010^\u001a\u00020\u0003H\u00c6\u0003J\t\u0010_\u001a\u00020\u0003H\u00c6\u0003J\t\u0010`\u001a\u00020\u0003H\u00c6\u0003J\t\u0010a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010f\u001a\u00020\u0016H\u00c6\u0003J\t\u0010g\u001a\u00020\u0016H\u00c6\u0003J\t\u0010h\u001a\u00020\u0016H\u00c6\u0003J\t\u0010i\u001a\u00020\u0003H\u00c6\u0003J\t\u0010j\u001a\u00020\u0003H\u00c6\u0003J\t\u0010k\u001a\u00020\u0003H\u00c6\u0003J\t\u0010l\u001a\u00020\u0003H\u00c6\u0003J\t\u0010m\u001a\u00020\u0003H\u00c6\u0003J\t\u0010n\u001a\u00020\u0003H\u00c6\u0003J\t\u0010o\u001a\u00020\u0003H\u00c6\u0003J\t\u0010p\u001a\u00020\u0003H\u00c6\u0003J\u00f6\u0001\u0010q\u001a\u00020\u00002\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010rJ\u0013\u0010s\u001a\u00020t2\b\u0010u\u001a\u0004\u0018\u00010vH\u00d6\u0003J\t\u0010w\u001a\u00020\u001dH\u00d6\u0001J\u0006\u0010x\u001a\u00020tJ\t\u0010y\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010#\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u001a\u0010 \u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R\u001a\u0010\u001e\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R\u001a\u0010\u001f\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R\u001a\u0010!\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010&\"\u0004\b0\u0010(R\u001a\u0010\"\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010&\"\u0004\b2\u0010(R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010&\"\u0004\b4\u0010(R\u001a\u0010\u0017\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u00106\"\u0004\b:\u00108R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010&\"\u0004\b<\u0010(R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010&\"\u0004\b>\u0010(R\u001a\u0010\u0011\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010&\"\u0004\b@\u0010(R\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010&\"\u0004\bB\u0010(R\"\u0010\u001c\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010G\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bH\u0010&\"\u0004\bI\u0010(R\u001a\u0010\u0018\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bJ\u00106\"\u0004\bK\u00108R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bL\u0010&\"\u0004\bM\u0010(R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bN\u0010&\"\u0004\bO\u0010(R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bP\u0010&\"\u0004\bQ\u0010(R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bR\u0010&\"\u0004\bS\u0010(R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bT\u0010&\"\u0004\bU\u0010(R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u0010&\"\u0004\bW\u0010(R\u001a\u0010\u0019\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bX\u0010&\"\u0004\bY\u0010(\u00a8\u0006z"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/CardTransactionResult;", "Ljava/io/Serializable;", "rrn", "", "authID", "stan", "pan", "transactionStatus", "responseCode", "transactionStatusReason", "accountType", "batchNumber", "merchantID", "isoTransmissionDateTime", "terminalID", "originalForwardingInstitutionCode", "cardHolderName", "cardExpiry", "tsi", "tvr", "aid", "amount", "", "additionalAmount", "longDateTime", "transactionType", "Lcom/iisysgroup/poslib/host/Host$TransactionType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJLcom/iisysgroup/poslib/host/Host$TransactionType;)V", "id", "", "RRN", "STAN", "PAN", "TSI", "TVR", "AID", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJLjava/lang/String;)V", "getAID", "()Ljava/lang/String;", "setAID", "(Ljava/lang/String;)V", "getPAN", "setPAN", "getRRN", "setRRN", "getSTAN", "setSTAN", "getTSI", "setTSI", "getTVR", "setTVR", "getAccountType", "setAccountType", "getAdditionalAmount", "()J", "setAdditionalAmount", "(J)V", "getAmount", "setAmount", "getAuthID", "setAuthID", "getBatchNumber", "setBatchNumber", "getCardExpiry", "setCardExpiry", "getCardHolderName", "setCardHolderName", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getIsoTransmissionDateTime", "setIsoTransmissionDateTime", "getLongDateTime", "setLongDateTime", "getMerchantID", "setMerchantID", "getOriginalForwardingInstitutionCode", "setOriginalForwardingInstitutionCode", "getResponseCode", "setResponseCode", "getTerminalID", "setTerminalID", "getTransactionStatus", "setTransactionStatus", "getTransactionStatusReason", "setTransactionStatusReason", "getTransactionType", "setTransactionType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJLjava/lang/String;)Lcom/wizarpos/emvsample/db/detailed/CardTransactionResult;", "equals", "", "other", "", "hashCode", "isApproved", "toString", "wari-wari_online_release"})
public final class CardTransactionResult implements java.io.Serializable {
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.PrimaryKey(autoGenerate = true)
    private java.lang.Integer id;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String RRN;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String authID;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String STAN;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String PAN;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String transactionStatus;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String responseCode;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String transactionStatusReason;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String accountType;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String batchNumber;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String merchantID;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String isoTransmissionDateTime;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String terminalID;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String originalForwardingInstitutionCode;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String cardHolderName;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String cardExpiry;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String TSI;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String TVR;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String AID;
    private long amount;
    private long additionalAmount;
    private long longDateTime;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String transactionType;
    
    public final boolean isApproved() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRRN() {
        return null;
    }
    
    public final void setRRN(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAuthID() {
        return null;
    }
    
    public final void setAuthID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSTAN() {
        return null;
    }
    
    public final void setSTAN(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPAN() {
        return null;
    }
    
    public final void setPAN(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionStatus() {
        return null;
    }
    
    public final void setTransactionStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getResponseCode() {
        return null;
    }
    
    public final void setResponseCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionStatusReason() {
        return null;
    }
    
    public final void setTransactionStatusReason(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccountType() {
        return null;
    }
    
    public final void setAccountType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBatchNumber() {
        return null;
    }
    
    public final void setBatchNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMerchantID() {
        return null;
    }
    
    public final void setMerchantID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIsoTransmissionDateTime() {
        return null;
    }
    
    public final void setIsoTransmissionDateTime(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTerminalID() {
        return null;
    }
    
    public final void setTerminalID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getOriginalForwardingInstitutionCode() {
        return null;
    }
    
    public final void setOriginalForwardingInstitutionCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCardHolderName() {
        return null;
    }
    
    public final void setCardHolderName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCardExpiry() {
        return null;
    }
    
    public final void setCardExpiry(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTSI() {
        return null;
    }
    
    public final void setTSI(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTVR() {
        return null;
    }
    
    public final void setTVR(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAID() {
        return null;
    }
    
    public final void setAID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getAmount() {
        return 0L;
    }
    
    public final void setAmount(long p0) {
    }
    
    public final long getAdditionalAmount() {
        return 0L;
    }
    
    public final void setAdditionalAmount(long p0) {
    }
    
    public final long getLongDateTime() {
        return 0L;
    }
    
    public final void setLongDateTime(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionType() {
        return null;
    }
    
    public final void setTransactionType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public CardTransactionResult(@org.jetbrains.annotations.Nullable()
    @android.support.annotation.NonNull()
    java.lang.Integer id, @org.jetbrains.annotations.NotNull()
    java.lang.String RRN, @org.jetbrains.annotations.NotNull()
    java.lang.String authID, @org.jetbrains.annotations.NotNull()
    java.lang.String STAN, @org.jetbrains.annotations.NotNull()
    java.lang.String PAN, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String responseCode, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatusReason, @org.jetbrains.annotations.NotNull()
    java.lang.String accountType, @org.jetbrains.annotations.NotNull()
    java.lang.String batchNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantID, @org.jetbrains.annotations.NotNull()
    java.lang.String isoTransmissionDateTime, @org.jetbrains.annotations.NotNull()
    java.lang.String terminalID, @org.jetbrains.annotations.NotNull()
    java.lang.String originalForwardingInstitutionCode, @org.jetbrains.annotations.NotNull()
    java.lang.String cardHolderName, @org.jetbrains.annotations.NotNull()
    java.lang.String cardExpiry, @org.jetbrains.annotations.NotNull()
    java.lang.String TSI, @org.jetbrains.annotations.NotNull()
    java.lang.String TVR, @org.jetbrains.annotations.NotNull()
    java.lang.String AID, long amount, long additionalAmount, long longDateTime, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionType) {
        super();
    }
    
    public CardTransactionResult() {
        super();
    }
    
    @android.arch.persistence.room.Ignore()
    public CardTransactionResult(@org.jetbrains.annotations.NotNull()
    java.lang.String rrn, @org.jetbrains.annotations.NotNull()
    java.lang.String authID, @org.jetbrains.annotations.NotNull()
    java.lang.String stan, @org.jetbrains.annotations.NotNull()
    java.lang.String pan, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String responseCode, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatusReason, @org.jetbrains.annotations.NotNull()
    java.lang.String accountType, @org.jetbrains.annotations.NotNull()
    java.lang.String batchNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantID, @org.jetbrains.annotations.NotNull()
    java.lang.String isoTransmissionDateTime, @org.jetbrains.annotations.NotNull()
    java.lang.String terminalID, @org.jetbrains.annotations.NotNull()
    java.lang.String originalForwardingInstitutionCode, @org.jetbrains.annotations.NotNull()
    java.lang.String cardHolderName, @org.jetbrains.annotations.NotNull()
    java.lang.String cardExpiry, @org.jetbrains.annotations.NotNull()
    java.lang.String tsi, @org.jetbrains.annotations.NotNull()
    java.lang.String tvr, @org.jetbrains.annotations.NotNull()
    java.lang.String aid, long amount, long additionalAmount, long longDateTime, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.Host.TransactionType transactionType) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component19() {
        return null;
    }
    
    public final long component20() {
        return 0L;
    }
    
    public final long component21() {
        return 0L;
    }
    
    public final long component22() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.CardTransactionResult copy(@org.jetbrains.annotations.Nullable()
    @android.support.annotation.NonNull()
    java.lang.Integer id, @org.jetbrains.annotations.NotNull()
    java.lang.String RRN, @org.jetbrains.annotations.NotNull()
    java.lang.String authID, @org.jetbrains.annotations.NotNull()
    java.lang.String STAN, @org.jetbrains.annotations.NotNull()
    java.lang.String PAN, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String responseCode, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatusReason, @org.jetbrains.annotations.NotNull()
    java.lang.String accountType, @org.jetbrains.annotations.NotNull()
    java.lang.String batchNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantID, @org.jetbrains.annotations.NotNull()
    java.lang.String isoTransmissionDateTime, @org.jetbrains.annotations.NotNull()
    java.lang.String terminalID, @org.jetbrains.annotations.NotNull()
    java.lang.String originalForwardingInstitutionCode, @org.jetbrains.annotations.NotNull()
    java.lang.String cardHolderName, @org.jetbrains.annotations.NotNull()
    java.lang.String cardExpiry, @org.jetbrains.annotations.NotNull()
    java.lang.String TSI, @org.jetbrains.annotations.NotNull()
    java.lang.String TVR, @org.jetbrains.annotations.NotNull()
    java.lang.String AID, long amount, long additionalAmount, long longDateTime, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}