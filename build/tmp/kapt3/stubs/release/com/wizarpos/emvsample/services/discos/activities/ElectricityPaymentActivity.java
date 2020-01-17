package com.wizarpos.emvsample.services.discos.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 m2\u00020\u0001:\u0001mB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010S\u001a\u00020TH\u0014J\b\u0010U\u001a\u00020TH\u0014J\b\u0010V\u001a\u00020WH\u0002J\b\u0010X\u001a\u00020WH\u0002J\"\u0010Y\u001a\u00020W2\u0006\u0010Z\u001a\u00020T2\u0006\u0010[\u001a\u00020T2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0014J\b\u0010^\u001a\u00020WH\u0016J\u0012\u0010_\u001a\u00020W2\b\u0010`\u001a\u0004\u0018\u00010aH\u0014J\u0018\u0010b\u001a\u00020W2\u0006\u0010>\u001a\u00020\u00042\u0006\u0010c\u001a\u00020\u0004H\u0002J`\u0010d\u001a\u00020W2\u0006\u0010>\u001a\u00020\u00042\u0006\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0006\u0010h\u001a\u00020\u0004H\u0002J\b\u0010i\u001a\u00020WH\u0002J\b\u0010j\u001a\u00020WH\u0002J\b\u0010k\u001a\u00020WH\u0002J\u0006\u0010l\u001a\u00020WR#\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\rR\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R#\u0010\u0017\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\t\u001a\u0004\b\u0018\u0010\u0007R#\u0010\u001a\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\t\u001a\u0004\b\u001b\u0010\u0007R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R#\u0010#\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010\t\u001a\u0004\b$\u0010\u0007R\u001a\u0010&\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0014\"\u0004\b(\u0010\u0016R#\u0010)\u001a\n \u0005*\u0004\u0018\u00010*0*8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010\t\u001a\u0004\b+\u0010,R\u000e\u0010.\u001a\u00020/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u00102\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b4\u0010\t\u001a\u0004\b3\u0010\u0007R#\u00105\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b7\u0010\t\u001a\u0004\b6\u0010\u0007R#\u00108\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b:\u0010\t\u001a\u0004\b9\u0010\u0007R#\u0010;\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b=\u0010\t\u001a\u0004\b<\u0010\u0007R\u001a\u0010>\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0007\"\u0004\b@\u0010\rR#\u0010A\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bC\u0010\t\u001a\u0004\bB\u0010\u0007R\u000e\u0010D\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u0010E\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bG\u0010\t\u001a\u0004\bF\u0010\u0007R#\u0010H\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bJ\u0010\t\u001a\u0004\bI\u0010\u0007R\u000e\u0010K\u001a\u00020LX\u0082.\u00a2\u0006\u0002\n\u0000R#\u0010M\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bO\u0010\t\u001a\u0004\bN\u0010\u0007R#\u0010P\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bR\u0010\t\u001a\u0004\bQ\u0010\u0007\u00a8\u0006n"}, d2 = {"Lcom/wizarpos/emvsample/services/discos/activities/ElectricityPaymentActivity;", "Lcom/wizarpos/emvsample/activity/BaseVasActivity;", "()V", "address", "", "kotlin.jvm.PlatformType", "getAddress", "()Ljava/lang/String;", "address$delegate", "Lkotlin/Lazy;", "airtime_amount", "getAirtime_amount", "setAirtime_amount", "(Ljava/lang/String;)V", "airtime_provider", "getAirtime_provider", "setAirtime_provider", "cancel", "Landroid/widget/Button;", "getCancel", "()Landroid/widget/Button;", "setCancel", "(Landroid/widget/Button;)V", "channel", "getChannel", "channel$delegate", "clientReference", "getClientReference", "clientReference$delegate", "constraintLayout", "Landroid/support/constraint/ConstraintLayout;", "getConstraintLayout", "()Landroid/support/constraint/ConstraintLayout;", "setConstraintLayout", "(Landroid/support/constraint/ConstraintLayout;)V", "electricMeterType", "getElectricMeterType", "electricMeterType$delegate", "enter", "getEnter", "setEnter", "hostInteractor", "Lcom/iisysgroup/poslib/host/HostInteractor;", "getHostInteractor", "()Lcom/iisysgroup/poslib/host/HostInteractor;", "hostInteractor$delegate", "isBeneficiary", "", "mEleectricityPaymentVM", "Lcom/wizarpos/emvsample/services/discos/viewmodels/EleectricityPaymentVM;", "meterName", "getMeterName", "meterName$delegate", "meterNumber", "getMeterNumber", "meterNumber$delegate", "meterType", "getMeterType", "meterType$delegate", "password", "getPassword", "password$delegate", "phone_number", "getPhone_number", "setPhone_number", "productCode", "getProductCode", "productCode$delegate", "ref", "requestType", "getRequestType", "requestType$delegate", "terminalId", "getTerminalId", "terminalId$delegate", "transactionResult", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "userName", "getUserName", "userName$delegate", "wallet", "getWallet", "wallet$delegate", "getMaxCount", "", "getTextLayoutId", "moveToNextPage", "", "moveToPreviousPage", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "payWithCard", "airtimeProvider", "payWithWallet", "activity", "Landroid/app/Activity;", "amount", "paymentMetod", "performTransaction", "resetAirtimeValues", "showAmountScreen", "showPhoneNumberScreen", "Companion", "wari-wari_online_release"})
public class ElectricityPaymentActivity extends com.wizarpos.emvsample.activity.BaseVasActivity {
    @org.jetbrains.annotations.NotNull()
    public android.widget.Button enter;
    @org.jetbrains.annotations.NotNull()
    public android.widget.Button cancel;
    private java.lang.String ref;
    @org.jetbrains.annotations.NotNull()
    public android.support.constraint.ConstraintLayout constraintLayout;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String airtime_amount;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String phone_number;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String airtime_provider;
    private com.iisysgroup.poslib.host.entities.TransactionResult transactionResult;
    private final kotlin.Lazy hostInteractor$delegate = null;
    private final kotlin.Lazy password$delegate = null;
    private final kotlin.Lazy userName$delegate = null;
    private final kotlin.Lazy wallet$delegate = null;
    private final kotlin.Lazy channel$delegate = null;
    private final kotlin.Lazy address$delegate = null;
    private final kotlin.Lazy requestType$delegate = null;
    private final kotlin.Lazy meterNumber$delegate = null;
    private final kotlin.Lazy meterType$delegate = null;
    private final kotlin.Lazy meterName$delegate = null;
    private final kotlin.Lazy productCode$delegate = null;
    private final kotlin.Lazy electricMeterType$delegate = null;
    private final kotlin.Lazy terminalId$delegate = null;
    private final kotlin.Lazy clientReference$delegate = null;
    private boolean isBeneficiary;
    private com.wizarpos.emvsample.services.discos.viewmodels.EleectricityPaymentVM mEleectricityPaymentVM;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_PIN = "pin";
    public static final int ELECTRICITY_REQUEST_CODE_CARD = 3424;
    public static final com.wizarpos.emvsample.services.discos.activities.ElectricityPaymentActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
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
    
    private final com.iisysgroup.poslib.host.HostInteractor getHostInteractor() {
        return null;
    }
    
    public final java.lang.String getPassword() {
        return null;
    }
    
    public final java.lang.String getUserName() {
        return null;
    }
    
    public final java.lang.String getWallet() {
        return null;
    }
    
    public final java.lang.String getChannel() {
        return null;
    }
    
    public final java.lang.String getAddress() {
        return null;
    }
    
    public final java.lang.String getRequestType() {
        return null;
    }
    
    public final java.lang.String getMeterNumber() {
        return null;
    }
    
    public final java.lang.String getMeterType() {
        return null;
    }
    
    public final java.lang.String getMeterName() {
        return null;
    }
    
    public final java.lang.String getProductCode() {
        return null;
    }
    
    public final java.lang.String getElectricMeterType() {
        return null;
    }
    
    public final java.lang.String getTerminalId() {
        return null;
    }
    
    public final java.lang.String getClientReference() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected int getTextLayoutId() {
        return 0;
    }
    
    @java.lang.Override()
    protected int getMaxCount() {
        return 0;
    }
    
    private final void resetAirtimeValues() {
    }
    
    private final void showAmountScreen() {
    }
    
    public final void showPhoneNumberScreen() {
    }
    
    private final void moveToNextPage() {
    }
    
    private final void moveToPreviousPage() {
    }
    
    private final void performTransaction() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    private final void payWithWallet(java.lang.String phone_number, android.app.Activity activity, java.lang.String amount, java.lang.String wallet, java.lang.String userName, java.lang.String requestType, java.lang.String meterType, java.lang.String meterNumber, java.lang.String channel, java.lang.String productCode, java.lang.String paymentMetod) {
    }
    
    private final void payWithCard(java.lang.String phone_number, java.lang.String airtimeProvider) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public ElectricityPaymentActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/wizarpos/emvsample/services/discos/activities/ElectricityPaymentActivity$Companion;", "", "()V", "ELECTRICITY_REQUEST_CODE_CARD", "", "USER_PIN", "", "print", "", "context", "Landroid/content/Context;", "vasDetails", "Lcom/wizarpos/emvsample/services/helper/activity/util/Models$VasDetails;", "wari-wari_online_release"})
    public static final class Companion {
        
        public final void print(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.Nullable()
        com.wizarpos.emvsample.services.helper.activity.util.Models.VasDetails vasDetails) {
        }
        
        private Companion() {
            super();
        }
    }
}