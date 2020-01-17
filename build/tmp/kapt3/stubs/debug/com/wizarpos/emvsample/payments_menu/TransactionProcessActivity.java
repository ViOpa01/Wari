package com.wizarpos.emvsample.payments_menu;

import java.lang.System;

@android.annotation.SuppressLint(value = {"MissingPermission"})
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020\u00062\n\b\u0002\u0010f\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u0010g\u001a\u00020dH\u0002J\u0010\u0010h\u001a\u00020d2\u0006\u0010i\u001a\u000204H\u0002J\u0010\u0010j\u001a\u00020d2\u0006\u0010k\u001a\u00020@H\u0002J\b\u0010l\u001a\u00020dH\u0002J\b\u0010m\u001a\u00020dH\u0002J\u0018\u0010n\u001a\u00020d2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010o\u001a\u00020\u0006H\u0002J\u0012\u0010p\u001a\u00020d2\b\u0010q\u001a\u0004\u0018\u00010rH\u0014J\"\u0010s\u001a\u00020d2\u000e\u0010t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010u2\b\u0010v\u001a\u0004\u0018\u00010wH\u0016J(\u0010x\u001a\u00020d2\u000e\u0010t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010u2\u000e\u0010y\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010zH\u0016J\u0006\u0010{\u001a\u00020dJ\u0006\u0010|\u001a\u00020dJ\u0010\u0010}\u001a\u00020d2\u0006\u0010~\u001a\u00020\u007fH\u0002J\r\u0010\u0080\u0001\u001a\u00020@*\u00020YH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R#\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\f\u001a\u0004\b\u0019\u0010\u001aR#\u0010\u001c\u001a\n \b*\u0004\u0018\u00010\u001d0\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\f\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010!\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\f\u001a\u0004\b\"\u0010\u0015R#\u0010$\u001a\n \b*\u0004\u0018\u00010%0%8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010\f\u001a\u0004\b&\u0010\'R#\u0010)\u001a\n \b*\u0004\u0018\u00010*0*8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010\f\u001a\u0004\b+\u0010,R\u001b\u0010.\u001a\u00020/8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b2\u0010\f\u001a\u0004\b0\u00101R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u00105\u001a\n \b*\u0004\u0018\u000106068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b9\u0010\f\u001a\u0004\b7\u00108R\u001b\u0010:\u001a\u00020;8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b>\u0010\f\u001a\u0004\b<\u0010=R\u001b\u0010?\u001a\u00020@8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bB\u0010\f\u001a\u0004\b?\u0010AR#\u0010C\u001a\n \b*\u0004\u0018\u00010D0D8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bG\u0010\f\u001a\u0004\bE\u0010FR\u001b\u0010H\u001a\u00020\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bJ\u0010\f\u001a\u0004\bI\u0010\u001fR#\u0010K\u001a\n \b*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bM\u0010\f\u001a\u0004\bL\u0010\nR#\u0010N\u001a\n \b*\u0004\u0018\u00010O0O8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bR\u0010\f\u001a\u0004\bP\u0010QR\u001b\u0010S\u001a\u00020T8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bW\u0010\f\u001a\u0004\bU\u0010VR#\u0010X\u001a\n \b*\u0004\u0018\u00010Y0Y8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\\\u0010\f\u001a\u0004\bZ\u0010[R#\u0010]\u001a\n \b*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b_\u0010\f\u001a\u0004\b^\u0010\nR\u001d\u0010`\u001a\u0004\u0018\u00010\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bb\u0010\f\u001a\u0004\ba\u0010\u001a\u00a8\u0006\u0081\u0001"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/TransactionProcessActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Lretrofit2/Callback;", "", "()V", "TAG", "", "accountNumber", "kotlin.jvm.PlatformType", "getAccountNumber", "()Ljava/lang/String;", "accountNumber$delegate", "Lkotlin/Lazy;", "accountType", "Lcom/iisysgroup/poslib/utils/AccountType;", "getAccountType", "()Lcom/iisysgroup/poslib/utils/AccountType;", "accountType$delegate", "additionalAmount", "", "getAdditionalAmount", "()J", "additionalAmount$delegate", "additionalTransactionType", "Lcom/iisysgroup/poslib/host/Host$TransactionType;", "getAdditionalTransactionType", "()Lcom/iisysgroup/poslib/host/Host$TransactionType;", "additionalTransactionType$delegate", "alert", "Landroid/support/v7/app/AlertDialog;", "getAlert", "()Landroid/support/v7/app/AlertDialog;", "alert$delegate", "amount", "getAmount", "amount$delegate", "bankCode", "Ljava/io/Serializable;", "getBankCode", "()Ljava/io/Serializable;", "bankCode$delegate", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "getConfigData", "()Lcom/iisysgroup/poslib/host/entities/ConfigData;", "configData$delegate", "connectionData", "Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "getConnectionData", "()Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "connectionData$delegate", "currentTransactionResult", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "hostInteractor", "Lcom/iisysgroup/poslib/host/HostInteractor;", "getHostInteractor", "()Lcom/iisysgroup/poslib/host/HostInteractor;", "hostInteractor$delegate", "inputData", "Lcom/iisysgroup/poslib/utils/InputData;", "getInputData", "()Lcom/iisysgroup/poslib/utils/InputData;", "inputData$delegate", "isCard", "", "()Z", "isCard$delegate", "keyHolder", "Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "getKeyHolder", "()Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "keyHolder$delegate", "payviceUserNameAlertDialog", "getPayviceUserNameAlertDialog", "payviceUserNameAlertDialog$delegate", "payviceUsername", "getPayviceUsername", "payviceUsername$delegate", "poslibdb", "Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "getPoslibdb", "()Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "poslibdb$delegate", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "progressDialog$delegate", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "sharedPreferences$delegate", "terminalId", "getTerminalId", "terminalId$delegate", "transactionType", "getTransactionType", "transactionType$delegate", "displayInfo", "", "message", "title", "handleInsertCard", "handleRollBack", "lastTransactionResult", "handleStatusDisplay", "isApproved", "handleWalletTransfer", "initializeApproveDeclinedState", "lookup", "bank_code", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFailure", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "performTransfer", "processPassword", "showVisibility", "view", "Landroid/view/View;", "isSSL", "wari-wari_online_debug"})
public final class TransactionProcessActivity extends android.support.v7.app.AppCompatActivity implements retrofit2.Callback<java.lang.Object> {
    private final java.lang.String TAG = "TransactionProcess";
    private final kotlin.Lazy isCard$delegate = null;
    private com.iisysgroup.poslib.host.entities.TransactionResult currentTransactionResult;
    private final kotlin.Lazy additionalTransactionType$delegate = null;
    private final kotlin.Lazy alert$delegate = null;
    private final kotlin.Lazy terminalId$delegate = null;
    private final kotlin.Lazy payviceUserNameAlertDialog$delegate = null;
    private final kotlin.Lazy hostInteractor$delegate = null;
    private final kotlin.Lazy sharedPreferences$delegate = null;
    private final kotlin.Lazy poslibdb$delegate = null;
    private final kotlin.Lazy progressDialog$delegate = null;
    private final kotlin.Lazy connectionData$delegate = null;
    private final kotlin.Lazy transactionType$delegate = null;
    private final kotlin.Lazy accountNumber$delegate = null;
    private final kotlin.Lazy bankCode$delegate = null;
    private final kotlin.Lazy accountType$delegate = null;
    private final kotlin.Lazy amount$delegate = null;
    private final kotlin.Lazy additionalAmount$delegate = null;
    private final kotlin.Lazy inputData$delegate = null;
    private final kotlin.Lazy keyHolder$delegate = null;
    private final kotlin.Lazy configData$delegate = null;
    private final kotlin.Lazy payviceUsername$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public void onFailure(@org.jetbrains.annotations.Nullable()
    retrofit2.Call<java.lang.Object> call, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable t) {
    }
    
    @java.lang.Override()
    public void onResponse(@org.jetbrains.annotations.Nullable()
    retrofit2.Call<java.lang.Object> call, @org.jetbrains.annotations.Nullable()
    retrofit2.Response<java.lang.Object> response) {
    }
    
    private final boolean isCard() {
        return false;
    }
    
    private final boolean isSSL(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences $receiver) {
        return false;
    }
    
    private final void displayInfo(java.lang.String message, java.lang.String title) {
    }
    
    private final com.iisysgroup.poslib.host.Host.TransactionType getAdditionalTransactionType() {
        return null;
    }
    
    private final android.support.v7.app.AlertDialog getAlert() {
        return null;
    }
    
    private final java.lang.String getTerminalId() {
        return null;
    }
    
    private final android.support.v7.app.AlertDialog getPayviceUserNameAlertDialog() {
        return null;
    }
    
    private final com.iisysgroup.poslib.host.HostInteractor getHostInteractor() {
        return null;
    }
    
    private final android.content.SharedPreferences getSharedPreferences() {
        return null;
    }
    
    private final com.iisysgroup.poslib.host.dao.PosLibDatabase getPoslibdb() {
        return null;
    }
    
    private final android.app.ProgressDialog getProgressDialog() {
        return null;
    }
    
    private final com.iisysgroup.poslib.host.entities.ConnectionData getConnectionData() {
        return null;
    }
    
    private final com.iisysgroup.poslib.host.Host.TransactionType getTransactionType() {
        return null;
    }
    
    private final java.lang.String getAccountNumber() {
        return null;
    }
    
    private final java.io.Serializable getBankCode() {
        return null;
    }
    
    private final com.iisysgroup.poslib.utils.AccountType getAccountType() {
        return null;
    }
    
    private final long getAmount() {
        return 0L;
    }
    
    private final long getAdditionalAmount() {
        return 0L;
    }
    
    private final com.iisysgroup.poslib.utils.InputData getInputData() {
        return null;
    }
    
    private final com.iisysgroup.poslib.host.entities.KeyHolder getKeyHolder() {
        return null;
    }
    
    private final com.iisysgroup.poslib.host.entities.ConfigData getConfigData() {
        return null;
    }
    
    private final java.lang.String getPayviceUsername() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showVisibility(android.view.View view) {
    }
    
    private final void initializeApproveDeclinedState() {
    }
    
    private final void handleInsertCard() {
    }
    
    private final void handleStatusDisplay(boolean isApproved) {
    }
    
    public final void performTransfer() {
    }
    
    private final void lookup(java.lang.String accountNumber, java.lang.String bank_code) {
    }
    
    public final void processPassword() {
    }
    
    private final void handleWalletTransfer() {
    }
    
    private final void handleRollBack(com.iisysgroup.poslib.host.entities.TransactionResult lastTransactionResult) {
    }
    
    public TransactionProcessActivity() {
        super();
    }
}