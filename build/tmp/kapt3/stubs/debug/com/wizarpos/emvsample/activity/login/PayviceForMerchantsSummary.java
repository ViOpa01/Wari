package com.wizarpos.emvsample.activity.login;

import java.lang.System;

/**
 * * Created by simileoluwaaluko on 20/05/2018.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\bm\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00e9\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\n\u0012\u0006\u0010\u0016\u001a\u00020\f\u0012\u0006\u0010\u0017\u001a\u00020\u000e\u0012\u0006\u0010\u0018\u001a\u00020\f\u0012\u0006\u0010\u0019\u001a\u00020\u000e\u0012\u0006\u0010\u001a\u001a\u00020\f\u0012\u0006\u0010\u001b\u001a\u00020\u000e\u0012\u0006\u0010\u001c\u001a\u00020\f\u0012\u0006\u0010\u001d\u001a\u00020\u000e\u0012\u0006\u0010\u001e\u001a\u00020\f\u0012\u0006\u0010\u001f\u001a\u00020\u000e\u0012\u0006\u0010 \u001a\u00020\f\u0012\u0006\u0010!\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\"J\t\u0010e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010f\u001a\u00020\fH\u00c6\u0003J\t\u0010g\u001a\u00020\u000eH\u00c6\u0003J\t\u0010h\u001a\u00020\u0003H\u00c6\u0003J\t\u0010i\u001a\u00020\u0003H\u00c6\u0003J\t\u0010j\u001a\u00020\u0003H\u00c6\u0003J\u0014\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00150\nH\u00c6\u0003\u00a2\u0006\u0002\u0010]J\t\u0010l\u001a\u00020\fH\u00c6\u0003J\t\u0010m\u001a\u00020\u000eH\u00c6\u0003J\t\u0010n\u001a\u00020\fH\u00c6\u0003J\t\u0010o\u001a\u00020\u000eH\u00c6\u0003J\t\u0010p\u001a\u00020\u0003H\u00c6\u0003J\t\u0010q\u001a\u00020\fH\u00c6\u0003J\t\u0010r\u001a\u00020\u000eH\u00c6\u0003J\t\u0010s\u001a\u00020\fH\u00c6\u0003J\t\u0010t\u001a\u00020\u000eH\u00c6\u0003J\t\u0010u\u001a\u00020\fH\u00c6\u0003J\t\u0010v\u001a\u00020\u000eH\u00c6\u0003J\t\u0010w\u001a\u00020\fH\u00c6\u0003J\t\u0010x\u001a\u00020\u000eH\u00c6\u0003J\t\u0010y\u001a\u00020\u0003H\u00c6\u0003J\t\u0010z\u001a\u00020\u0003H\u00c6\u0003J\t\u0010{\u001a\u00020\u0003H\u00c6\u0003J\t\u0010|\u001a\u00020\u0003H\u00c6\u0003J\u0014\u0010}\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010RJ\t\u0010~\u001a\u00020\fH\u00c6\u0003J\t\u0010\u007f\u001a\u00020\u000eH\u00c6\u0003J\u00aa\u0002\u0010\u0080\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\n2\b\b\u0002\u0010\u0016\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\f2\b\b\u0002\u0010\u001d\u001a\u00020\u000e2\b\b\u0002\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010\u001f\u001a\u00020\u000e2\b\b\u0002\u0010 \u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\u000eH\u00c6\u0001\u00a2\u0006\u0003\u0010\u0081\u0001J\u0016\u0010\u0082\u0001\u001a\u00030\u0083\u00012\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\t\u0010\u0085\u0001\u001a\u00020\fH\u0016J\n\u0010\u0086\u0001\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0018\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010\u001c\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010$\"\u0004\b0\u0010&R\u001a\u0010\u001d\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010(\"\u0004\b2\u0010*R\u001a\u0010\u001e\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010$\"\u0004\b4\u0010&R\u001a\u0010\u001f\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010(\"\u0004\b6\u0010*R\u001a\u0010 \u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u0010$\"\u0004\b8\u0010&R\u001a\u0010!\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010(\"\u0004\b:\u0010*R\u001a\u0010\u001a\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u0010$\"\u0004\b<\u0010&R\u001a\u0010\u001b\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010(\"\u0004\b>\u0010*R\u001a\u0010\u000f\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010$\"\u0004\b@\u0010&R\u001a\u0010\u0010\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010(\"\u0004\bB\u0010*R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010,\"\u0004\bD\u0010.R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010,\"\u0004\bF\u0010.R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010,\"\u0004\bH\u0010.R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010,\"\u0004\bJ\u0010.R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010,\"\u0004\bL\u0010.R\u001a\u0010\u0016\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u0010$\"\u0004\bN\u0010&R\u001a\u0010\u0017\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bO\u0010(\"\u0004\bP\u0010*R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010U\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u0010$\"\u0004\bW\u0010&R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bX\u0010(\"\u0004\bY\u0010*R\u001a\u0010\u0011\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010,\"\u0004\b[\u0010.R\"\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\nX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010`\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R\u001a\u0010\u0012\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\ba\u0010,\"\u0004\bb\u0010.R\u001a\u0010\u0013\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bc\u0010,\"\u0004\bd\u0010.\u00a8\u0006\u0087\u0001"}, d2 = {"Lcom/wizarpos/emvsample/activity/login/PayviceForMerchantsSummary;", "", "balance", "", "hist", "list", "journalCount", "myJournal", "merchantId", "successful", "", "successfulCount", "", "successfulSum", "", "failedCount", "failedSum", "successfulTransactions", "unSuccessfulTransactions", "xAxisTicks", "terminalTransactions", "Lcom/wizarpos/emvsample/activity/login/PfmTerminalTransactions;", "purchaseTransactionsCount", "purchaseTransactionsSum", "airtimeInternetTransactionsCount", "airtimeInternetTransactionsSum", "electricityTransactionsCount", "electricityTransactionsSum", "cableTransactionsCount", "cableTransactionsSum", "cardTransactionsCount", "cardTransactionsSum", "cashTransactionsCount", "cashTransactionsSum", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;IDIDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lcom/wizarpos/emvsample/activity/login/PfmTerminalTransactions;IDIDIDIDIDID)V", "getAirtimeInternetTransactionsCount", "()I", "setAirtimeInternetTransactionsCount", "(I)V", "getAirtimeInternetTransactionsSum", "()D", "setAirtimeInternetTransactionsSum", "(D)V", "getBalance", "()Ljava/lang/String;", "setBalance", "(Ljava/lang/String;)V", "getCableTransactionsCount", "setCableTransactionsCount", "getCableTransactionsSum", "setCableTransactionsSum", "getCardTransactionsCount", "setCardTransactionsCount", "getCardTransactionsSum", "setCardTransactionsSum", "getCashTransactionsCount", "setCashTransactionsCount", "getCashTransactionsSum", "setCashTransactionsSum", "getElectricityTransactionsCount", "setElectricityTransactionsCount", "getElectricityTransactionsSum", "setElectricityTransactionsSum", "getFailedCount", "setFailedCount", "getFailedSum", "setFailedSum", "getHist", "setHist", "getJournalCount", "setJournalCount", "getList", "setList", "getMerchantId", "setMerchantId", "getMyJournal", "setMyJournal", "getPurchaseTransactionsCount", "setPurchaseTransactionsCount", "getPurchaseTransactionsSum", "setPurchaseTransactionsSum", "getSuccessful", "()[Ljava/lang/Object;", "setSuccessful", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "getSuccessfulCount", "setSuccessfulCount", "getSuccessfulSum", "setSuccessfulSum", "getSuccessfulTransactions", "setSuccessfulTransactions", "getTerminalTransactions", "()[Lcom/wizarpos/emvsample/activity/login/PfmTerminalTransactions;", "setTerminalTransactions", "([Lcom/wizarpos/emvsample/activity/login/PfmTerminalTransactions;)V", "[Lcom/wizarpos/emvsample/activity/login/PfmTerminalTransactions;", "getUnSuccessfulTransactions", "setUnSuccessfulTransactions", "getXAxisTicks", "setXAxisTicks", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;IDIDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Lcom/wizarpos/emvsample/activity/login/PfmTerminalTransactions;IDIDIDIDIDID)Lcom/wizarpos/emvsample/activity/login/PayviceForMerchantsSummary;", "equals", "", "other", "hashCode", "toString", "NIBSS_debug"})
public final class PayviceForMerchantsSummary {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String balance;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String hist;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String list;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String journalCount;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String myJournal;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String merchantId;
    @org.jetbrains.annotations.NotNull()
    private java.lang.Object[] successful;
    private int successfulCount;
    private double successfulSum;
    private int failedCount;
    private double failedSum;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String successfulTransactions;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String unSuccessfulTransactions;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String xAxisTicks;
    @org.jetbrains.annotations.NotNull()
    private com.wizarpos.emvsample.activity.login.PfmTerminalTransactions[] terminalTransactions;
    private int purchaseTransactionsCount;
    private double purchaseTransactionsSum;
    private int airtimeInternetTransactionsCount;
    private double airtimeInternetTransactionsSum;
    private int electricityTransactionsCount;
    private double electricityTransactionsSum;
    private int cableTransactionsCount;
    private double cableTransactionsSum;
    private int cardTransactionsCount;
    private double cardTransactionsSum;
    private int cashTransactionsCount;
    private double cashTransactionsSum;
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBalance() {
        return null;
    }
    
    public final void setBalance(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHist() {
        return null;
    }
    
    public final void setHist(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getList() {
        return null;
    }
    
    public final void setList(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJournalCount() {
        return null;
    }
    
    public final void setJournalCount(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMyJournal() {
        return null;
    }
    
    public final void setMyJournal(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMerchantId() {
        return null;
    }
    
    public final void setMerchantId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.Object[] getSuccessful() {
        return null;
    }
    
    public final void setSuccessful(@org.jetbrains.annotations.NotNull()
    java.lang.Object[] p0) {
    }
    
    public final int getSuccessfulCount() {
        return 0;
    }
    
    public final void setSuccessfulCount(int p0) {
    }
    
    public final double getSuccessfulSum() {
        return 0.0;
    }
    
    public final void setSuccessfulSum(double p0) {
    }
    
    public final int getFailedCount() {
        return 0;
    }
    
    public final void setFailedCount(int p0) {
    }
    
    public final double getFailedSum() {
        return 0.0;
    }
    
    public final void setFailedSum(double p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSuccessfulTransactions() {
        return null;
    }
    
    public final void setSuccessfulTransactions(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUnSuccessfulTransactions() {
        return null;
    }
    
    public final void setUnSuccessfulTransactions(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getXAxisTicks() {
        return null;
    }
    
    public final void setXAxisTicks(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.activity.login.PfmTerminalTransactions[] getTerminalTransactions() {
        return null;
    }
    
    public final void setTerminalTransactions(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.PfmTerminalTransactions[] p0) {
    }
    
    public final int getPurchaseTransactionsCount() {
        return 0;
    }
    
    public final void setPurchaseTransactionsCount(int p0) {
    }
    
    public final double getPurchaseTransactionsSum() {
        return 0.0;
    }
    
    public final void setPurchaseTransactionsSum(double p0) {
    }
    
    public final int getAirtimeInternetTransactionsCount() {
        return 0;
    }
    
    public final void setAirtimeInternetTransactionsCount(int p0) {
    }
    
    public final double getAirtimeInternetTransactionsSum() {
        return 0.0;
    }
    
    public final void setAirtimeInternetTransactionsSum(double p0) {
    }
    
    public final int getElectricityTransactionsCount() {
        return 0;
    }
    
    public final void setElectricityTransactionsCount(int p0) {
    }
    
    public final double getElectricityTransactionsSum() {
        return 0.0;
    }
    
    public final void setElectricityTransactionsSum(double p0) {
    }
    
    public final int getCableTransactionsCount() {
        return 0;
    }
    
    public final void setCableTransactionsCount(int p0) {
    }
    
    public final double getCableTransactionsSum() {
        return 0.0;
    }
    
    public final void setCableTransactionsSum(double p0) {
    }
    
    public final int getCardTransactionsCount() {
        return 0;
    }
    
    public final void setCardTransactionsCount(int p0) {
    }
    
    public final double getCardTransactionsSum() {
        return 0.0;
    }
    
    public final void setCardTransactionsSum(double p0) {
    }
    
    public final int getCashTransactionsCount() {
        return 0;
    }
    
    public final void setCashTransactionsCount(int p0) {
    }
    
    public final double getCashTransactionsSum() {
        return 0.0;
    }
    
    public final void setCashTransactionsSum(double p0) {
    }
    
    public PayviceForMerchantsSummary(@org.jetbrains.annotations.NotNull()
    java.lang.String balance, @org.jetbrains.annotations.NotNull()
    java.lang.String hist, @org.jetbrains.annotations.NotNull()
    java.lang.String list, @org.jetbrains.annotations.NotNull()
    java.lang.String journalCount, @org.jetbrains.annotations.NotNull()
    java.lang.String myJournal, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantId, @org.jetbrains.annotations.NotNull()
    java.lang.Object[] successful, int successfulCount, double successfulSum, int failedCount, double failedSum, @org.jetbrains.annotations.NotNull()
    java.lang.String successfulTransactions, @org.jetbrains.annotations.NotNull()
    java.lang.String unSuccessfulTransactions, @org.jetbrains.annotations.NotNull()
    java.lang.String xAxisTicks, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.PfmTerminalTransactions[] terminalTransactions, int purchaseTransactionsCount, double purchaseTransactionsSum, int airtimeInternetTransactionsCount, double airtimeInternetTransactionsSum, int electricityTransactionsCount, double electricityTransactionsSum, int cableTransactionsCount, double cableTransactionsSum, int cardTransactionsCount, double cardTransactionsSum, int cashTransactionsCount, double cashTransactionsSum) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
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
    public final java.lang.Object[] component7() {
        return null;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final double component11() {
        return 0.0;
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
    public final com.wizarpos.emvsample.activity.login.PfmTerminalTransactions[] component15() {
        return null;
    }
    
    public final int component16() {
        return 0;
    }
    
    public final double component17() {
        return 0.0;
    }
    
    public final int component18() {
        return 0;
    }
    
    public final double component19() {
        return 0.0;
    }
    
    public final int component20() {
        return 0;
    }
    
    public final double component21() {
        return 0.0;
    }
    
    public final int component22() {
        return 0;
    }
    
    public final double component23() {
        return 0.0;
    }
    
    public final int component24() {
        return 0;
    }
    
    public final double component25() {
        return 0.0;
    }
    
    public final int component26() {
        return 0;
    }
    
    public final double component27() {
        return 0.0;
    }
    
    /**
     * * Created by simileoluwaaluko on 20/05/2018.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.activity.login.PayviceForMerchantsSummary copy(@org.jetbrains.annotations.NotNull()
    java.lang.String balance, @org.jetbrains.annotations.NotNull()
    java.lang.String hist, @org.jetbrains.annotations.NotNull()
    java.lang.String list, @org.jetbrains.annotations.NotNull()
    java.lang.String journalCount, @org.jetbrains.annotations.NotNull()
    java.lang.String myJournal, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantId, @org.jetbrains.annotations.NotNull()
    java.lang.Object[] successful, int successfulCount, double successfulSum, int failedCount, double failedSum, @org.jetbrains.annotations.NotNull()
    java.lang.String successfulTransactions, @org.jetbrains.annotations.NotNull()
    java.lang.String unSuccessfulTransactions, @org.jetbrains.annotations.NotNull()
    java.lang.String xAxisTicks, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.PfmTerminalTransactions[] terminalTransactions, int purchaseTransactionsCount, double purchaseTransactionsSum, int airtimeInternetTransactionsCount, double airtimeInternetTransactionsSum, int electricityTransactionsCount, double electricityTransactionsSum, int cableTransactionsCount, double cableTransactionsSum, int cardTransactionsCount, double cardTransactionsSum, int cashTransactionsCount, double cashTransactionsSum) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
}