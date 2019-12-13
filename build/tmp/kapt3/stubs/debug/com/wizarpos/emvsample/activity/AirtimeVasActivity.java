package com.wizarpos.emvsample.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u0002:\u0001[B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010;\u001a\u00020<H\u0014J\b\u0010=\u001a\u00020<H\u0014J\b\u0010>\u001a\u00020?H\u0002J\b\u0010@\u001a\u00020?H\u0002J\"\u0010A\u001a\u00020?2\u0006\u0010B\u001a\u00020<2\u0006\u0010C\u001a\u00020<2\b\u0010D\u001a\u0004\u0018\u00010EH\u0014J\b\u0010F\u001a\u00020?H\u0016J\u0012\u0010G\u001a\u00020?2\b\u0010H\u001a\u0004\u0018\u00010IH\u0014J\u0018\u0010J\u001a\u00020?2\u0006\u0010K\u001a\u00020\u00052\u0006\u0010%\u001a\u00020$H\u0016J\u0012\u0010L\u001a\u00020$2\b\u0010M\u001a\u0004\u0018\u00010NH\u0016J\u0010\u0010O\u001a\u00020?2\u0006\u0010P\u001a\u00020QH\u0016J\u0018\u0010R\u001a\u00020?2\u0006\u00100\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\u0005H\u0002J\u0018\u0010T\u001a\u00020?2\u0006\u00100\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\u0005H\u0002J\u0010\u0010U\u001a\u00020?2\u0006\u0010S\u001a\u00020\u0005H\u0002J\b\u0010V\u001a\u00020?H\u0002J\b\u0010W\u001a\u00020?H\u0002J\u0006\u0010X\u001a\u00020?J\b\u0010Y\u001a\u00020?H\u0002J\u0006\u0010Z\u001a\u00020?R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0004\b\u001b\u0010\u0012R#\u0010\u001c\u001a\n \u001e*\u0004\u0018\u00010\u001d0\u001d8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020$X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010-\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b/\u0010\"\u001a\u0004\b.\u0010\u0007R\u001a\u00100\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0007\"\u0004\b2\u0010\tR\u001b\u00103\u001a\u0002048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b7\u0010\"\u001a\u0004\b5\u00106R\u000e\u00108\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\\"}, d2 = {"Lcom/wizarpos/emvsample/activity/AirtimeVasActivity;", "Lcom/wizarpos/emvsample/activity/BaseVasActivity;", "Lcom/wizarpos/emvsample/activity/AirtimeProcessor$onAirtimeTransactionResultListener;", "()V", "airtime_amount", "", "getAirtime_amount", "()Ljava/lang/String;", "setAirtime_amount", "(Ljava/lang/String;)V", "airtime_provider", "getAirtime_provider", "setAirtime_provider", "cancel", "Landroid/widget/Button;", "getCancel", "()Landroid/widget/Button;", "setCancel", "(Landroid/widget/Button;)V", "constraintLayout", "Landroid/support/constraint/ConstraintLayout;", "getConstraintLayout", "()Landroid/support/constraint/ConstraintLayout;", "setConstraintLayout", "(Landroid/support/constraint/ConstraintLayout;)V", "enter", "getEnter", "setEnter", "hostInteractor", "Lcom/iisysgroup/poslib/host/HostInteractor;", "kotlin.jvm.PlatformType", "getHostInteractor", "()Lcom/iisysgroup/poslib/host/HostInteractor;", "hostInteractor$delegate", "Lkotlin/Lazy;", "isBeneficiary", "", "isCard", "isFromVasPage", "()Z", "setFromVasPage", "(Z)V", "mAirtimeProvider", "mPhoneNumber", "mPin", "mTerminalId", "getMTerminalId", "mTerminalId$delegate", "phone_number", "getPhone_number", "setPhone_number", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "progressDialog$delegate", "ref", "transactionResult", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "getMaxCount", "", "getTextLayoutId", "moveToNextPage", "", "moveToPreviousPage", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onError", "errorMessage", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResponse", "model", "Lcom/wizarpos/emvsample/activity/AirtimeSuccessResponse;", "payWithCard", "airtimeProvider", "payWithWallet", "performTransaction", "processCardTransaction", "resetAirtimeValues", "showAirtimeProviderScreen", "showAmountScreen", "showPhoneNumberScreen", "TAGS", "Wariok_debug"})
public final class AirtimeVasActivity extends com.wizarpos.emvsample.activity.BaseVasActivity implements com.wizarpos.emvsample.activity.AirtimeProcessor.onAirtimeTransactionResultListener {
    private boolean isCard;
    private com.iisysgroup.poslib.host.entities.TransactionResult transactionResult;
    private final kotlin.Lazy hostInteractor$delegate = null;
    private final kotlin.Lazy mTerminalId$delegate = null;
    private java.lang.String mPin;
    private java.lang.String mPhoneNumber;
    private java.lang.String mAirtimeProvider;
    private boolean isBeneficiary;
    private final kotlin.Lazy progressDialog$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String airtime_amount;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String phone_number;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String airtime_provider;
    @org.jetbrains.annotations.NotNull()
    public android.widget.Button enter;
    @org.jetbrains.annotations.NotNull()
    public android.widget.Button cancel;
    private java.lang.String ref;
    @org.jetbrains.annotations.NotNull()
    public android.support.constraint.ConstraintLayout constraintLayout;
    private boolean isFromVasPage;
    private java.util.HashMap _$_findViewCache;
    
    private final com.iisysgroup.poslib.host.HostInteractor getHostInteractor() {
        return null;
    }
    
    private final java.lang.String getMTerminalId() {
        return null;
    }
    
    private final android.app.ProgressDialog getProgressDialog() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAirtime_amount() {
        return null;
    }
    
    public final void setAirtime_amount(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPhone_number() {
        return null;
    }
    
    public final void setPhone_number(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAirtime_provider() {
        return null;
    }
    
    public final void setAirtime_provider(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.Button getEnter() {
        return null;
    }
    
    public final void setEnter(@org.jetbrains.annotations.NotNull()
    android.widget.Button p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.Button getCancel() {
        return null;
    }
    
    public final void setCancel(@org.jetbrains.annotations.NotNull()
    android.widget.Button p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.support.constraint.ConstraintLayout getConstraintLayout() {
        return null;
    }
    
    public final void setConstraintLayout(@org.jetbrains.annotations.NotNull()
    android.support.constraint.ConstraintLayout p0) {
    }
    
    public final boolean isFromVasPage() {
        return false;
    }
    
    public final void setFromVasPage(boolean p0) {
    }
    
    @java.lang.Override()
    public void onResponse(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.AirtimeSuccessResponse model) {
    }
    
    @java.lang.Override()
    public void onError(@org.jetbrains.annotations.NotNull()
    java.lang.String errorMessage, boolean isCard) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.Nullable()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    protected int getTextLayoutId() {
        return 0;
    }
    
    @java.lang.Override()
    protected int getMaxCount() {
        return 0;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void resetAirtimeValues() {
    }
    
    private final void payWithWallet(java.lang.String phone_number, java.lang.String airtimeProvider) {
    }
    
    private final void payWithCard(java.lang.String phone_number, java.lang.String airtimeProvider) {
    }
    
    private final void performTransaction(java.lang.String airtimeProvider) {
    }
    
    private final void moveToNextPage() {
    }
    
    private final void moveToPreviousPage() {
    }
    
    private final void processCardTransaction() {
    }
    
    private final void showAmountScreen() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public final void showPhoneNumberScreen() {
    }
    
    public final void showAirtimeProviderScreen() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    public AirtimeVasActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/wizarpos/emvsample/activity/AirtimeVasActivity$TAGS;", "", "()V", "AIRTIME_PURCHASE_KEY", "", "AIRTIME_PURCHASE_PROVIDER_TYPE", "AIRTIME_REQUEST_CODE", "", "AIRTIME_REQUEST_CODE_CARD", "Wariok_debug"})
    public static final class TAGS {
        public static final int AIRTIME_REQUEST_CODE = 3423;
        public static final int AIRTIME_REQUEST_CODE_CARD = 3424;
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String AIRTIME_PURCHASE_KEY = "airtime_purchase_key";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String AIRTIME_PURCHASE_PROVIDER_TYPE = "airtime_purchase_provider_type";
        public static final com.wizarpos.emvsample.activity.AirtimeVasActivity.TAGS INSTANCE = null;
        
        private TAGS() {
            super();
        }
    }
}