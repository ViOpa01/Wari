package com.wizarpos.emvsample.payments_menu.transfer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001ZB\u0005\u00a2\u0006\u0002\u0010\u0003J\u000e\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@J\u0006\u0010A\u001a\u00020>J\u001c\u0010B\u001a\u00020>2\n\u0010C\u001a\u00060Dj\u0002`E2\u0006\u0010F\u001a\u00020\u0011H\u0002J\u0014\u0010G\u001a\u00020>2\n\u0010C\u001a\u00060Dj\u0002`EH\u0002J\b\u0010H\u001a\u00020>H\u0002J\"\u0010I\u001a\u00020>2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020K2\b\u0010M\u001a\u0004\u0018\u00010NH\u0014J\u0010\u0010O\u001a\u00020>2\u0006\u0010P\u001a\u00020QH\u0016J\u0012\u0010R\u001a\u00020>2\b\u0010S\u001a\u0004\u0018\u00010TH\u0014J\b\u0010U\u001a\u00020>H\u0002J\u0010\u0010V\u001a\u00020>2\u0006\u0010W\u001a\u000208H\u0002J\b\u0010X\u001a\u00020>H\u0002J\b\u0010Y\u001a\u00020>H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R#\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00110\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R#\u0010\u0018\u001a\n \u0013*\u0004\u0018\u00010\u00110\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u0019\u0010\u0015R#\u0010\u001b\u001a\n \u0013*\u0004\u0018\u00010\u00110\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u0017\u001a\u0004\b\u001c\u0010\u0015R\u000e\u0010\u001e\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\"\u0010\u0017\u001a\u0004\b!\u0010\u0015R\u000e\u0010#\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\'\u0010\u0017\u001a\u0004\b&\u0010\u0015R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b,\u0010\u0017\u001a\u0004\b*\u0010+R\u001a\u0010-\u001a\u00020\u0011X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0015\"\u0004\b/\u00100R\u001b\u00101\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b3\u0010\u0017\u001a\u0004\b2\u0010\u0015R\u001b\u00104\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b6\u0010\u0017\u001a\u0004\b5\u0010\u0015R\u001a\u00107\u001a\u000208X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<\u00a8\u0006["}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/transfer/TransferAmountEntry;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "()V", "amountToDebit", "", "getAmountToDebit", "()D", "setAmountToDebit", "(D)V", "appState", "Lcom/wizarpos/emvsample/MainApp;", "getAppState", "()Lcom/wizarpos/emvsample/MainApp;", "setAppState", "(Lcom/wizarpos/emvsample/MainApp;)V", "mAccountName", "", "mAccountNumber", "kotlin.jvm.PlatformType", "getMAccountNumber", "()Ljava/lang/String;", "mAccountNumber$delegate", "Lkotlin/Lazy;", "mBankCode", "getMBankCode", "mBankCode$delegate", "mBankName", "getMBankName", "mBankName$delegate", "mConvenienceFee", "mEncryptedPin", "mPlainPassword", "getMPlainPassword", "mPlainPassword$delegate", "mProductCode", "mRrn", "mTerminalId", "getMTerminalId", "mTerminalId$delegate", "mTransactionType", "Lcom/wizarpos/emvsample/payments_menu/transfer/TransferAmountEntry$TRANSACTION_TYPE;", "getMTransactionType", "()Lcom/wizarpos/emvsample/payments_menu/transfer/TransferAmountEntry$TRANSACTION_TYPE;", "mTransactionType$delegate", "mWalletId", "getMWalletId", "setMWalletId", "(Ljava/lang/String;)V", "mWalletPassword", "getMWalletPassword", "mWalletPassword$delegate", "mWalletUsername", "getMWalletUsername", "mWalletUsername$delegate", "withdrawalResponse", "Lcom/wizarpos/emvsample/models/WithdrawalLookupSuccessModel;", "getWithdrawalResponse", "()Lcom/wizarpos/emvsample/models/WithdrawalLookupSuccessModel;", "setWithdrawalResponse", "(Lcom/wizarpos/emvsample/models/WithdrawalLookupSuccessModel;)V", "creditWallet", "", "context", "Landroid/content/Context;", "debitWallet", "fixAppend", "displayStr", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "digit", "fixDelete", "initializeAmountEntryElements", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onEnterPressed", "payWithCard", "response", "verifyTransferAccountDetails", "verifyWithdrawalAccountDetails", "TRANSACTION_TYPE", "NIBSS_debug"})
public final class TransferAmountEntry extends android.support.v7.app.AppCompatActivity implements android.view.View.OnClickListener {
    private java.lang.String mConvenienceFee;
    private java.lang.String mRrn;
    private java.lang.String mEncryptedPin;
    @org.jetbrains.annotations.Nullable()
    private com.wizarpos.emvsample.MainApp appState;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.emvsample.models.WithdrawalLookupSuccessModel withdrawalResponse;
    private final kotlin.Lazy mTerminalId$delegate = null;
    private final kotlin.Lazy mTransactionType$delegate = null;
    private final kotlin.Lazy mBankName$delegate = null;
    private final kotlin.Lazy mBankCode$delegate = null;
    private final kotlin.Lazy mAccountNumber$delegate = null;
    private final kotlin.Lazy mWalletUsername$delegate = null;
    private final kotlin.Lazy mPlainPassword$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String mWalletId;
    private final kotlin.Lazy mWalletPassword$delegate = null;
    private java.lang.String mAccountName;
    private java.lang.String mProductCode;
    private double amountToDebit;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.Nullable()
    public final com.wizarpos.emvsample.MainApp getAppState() {
        return null;
    }
    
    public final void setAppState(@org.jetbrains.annotations.Nullable()
    com.wizarpos.emvsample.MainApp p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.models.WithdrawalLookupSuccessModel getWithdrawalResponse() {
        return null;
    }
    
    public final void setWithdrawalResponse(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.models.WithdrawalLookupSuccessModel p0) {
    }
    
    private final java.lang.String getMTerminalId() {
        return null;
    }
    
    private final com.wizarpos.emvsample.payments_menu.transfer.TransferAmountEntry.TRANSACTION_TYPE getMTransactionType() {
        return null;
    }
    
    private final java.lang.String getMBankName() {
        return null;
    }
    
    private final java.lang.String getMBankCode() {
        return null;
    }
    
    private final java.lang.String getMAccountNumber() {
        return null;
    }
    
    private final java.lang.String getMWalletUsername() {
        return null;
    }
    
    private final java.lang.String getMPlainPassword() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMWalletId() {
        return null;
    }
    
    public final void setMWalletId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    private final java.lang.String getMWalletPassword() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void onEnterPressed() {
    }
    
    private final void payWithCard(com.wizarpos.emvsample.models.WithdrawalLookupSuccessModel response) {
    }
    
    private final void initializeAmountEntryElements() {
    }
    
    private final void fixAppend(java.lang.StringBuilder displayStr, java.lang.String digit) {
    }
    
    private final void fixDelete(java.lang.StringBuilder displayStr) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void verifyWithdrawalAccountDetails() {
    }
    
    private final void verifyTransferAccountDetails() {
    }
    
    public final void debitWallet() {
    }
    
    public final double getAmountToDebit() {
        return 0.0;
    }
    
    public final void setAmountToDebit(double p0) {
    }
    
    public final void creditWallet(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public TransferAmountEntry() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/transfer/TransferAmountEntry$TRANSACTION_TYPE;", "", "(Ljava/lang/String;I)V", "TRANSFER", "WITHDRAWAL", "DEPOSIT", "NIBSS_debug"})
    public static enum TRANSACTION_TYPE {
        /*public static final*/ TRANSFER /* = new TRANSFER() */,
        /*public static final*/ WITHDRAWAL /* = new WITHDRAWAL() */,
        /*public static final*/ DEPOSIT /* = new DEPOSIT() */;
        
        TRANSACTION_TYPE() {
        }
    }
}