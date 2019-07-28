package com.wizarpos.emvsample.services.discos.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ]2\u00020\u00012\u00020\u0002:\u0001]B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010F\u001a\u00020\u001cH\u0002JX\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u001c2\u0006\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u00052\u0006\u0010L\u001a\u00020\u00052\u0006\u0010M\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0012\u0010O\u001a\u00020H2\b\u0010P\u001a\u0004\u0018\u00010QH\u0016J\u0012\u0010R\u001a\u00020H2\b\u0010S\u001a\u0004\u0018\u00010TH\u0014J\"\u0010U\u001a\u0004\u0018\u00010V2\u0006\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020Z2\u0006\u00102\u001a\u000203H\u0002JP\u0010[\u001a\u00020H2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u00052\u0006\u0010\\\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u0005H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR#\u0010\u0013\u001a\n \u0014*\u0004\u0018\u00010\u00050\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0015\u0010\u0007R\u001a\u0010\u0018\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0007\"\u0004\b\u001a\u0010\tR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0007\"\u0004\b!\u0010\tR\u001a\u0010\"\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0007\"\u0004\b$\u0010\tR\u001a\u0010%\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0007\"\u0004\b\'\u0010\tR\u001a\u0010(\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0007\"\u0004\b*\u0010\tR\u001a\u0010+\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010\tR\u000e\u0010.\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0007\"\u0004\b1\u0010\tR\u001a\u00102\u001a\u000203X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u00108\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0007\"\u0004\b:\u0010\tR\u001a\u0010;\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0007\"\u0004\b=\u0010\tR\u001b\u0010>\u001a\u00020?8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bB\u0010\u0017\u001a\u0004\b@\u0010AR\u001a\u0010C\u001a\u00020\u0005X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0007\"\u0004\bE\u0010\t\u00a8\u0006^"}, d2 = {"Lcom/wizarpos/emvsample/services/discos/activities/MeterValidationActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "()V", "address", "", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "authPin", "getAuthPin", "setAuthPin", "channel", "getChannel", "setChannel", "clientReference", "getClientReference", "setClientReference", "disco", "kotlin.jvm.PlatformType", "getDisco", "disco$delegate", "Lkotlin/Lazy;", "electricMeterType", "getElectricMeterType", "setElectricMeterType", "isBeneficiary", "", "mMeterValidationViewModel", "Lcom/wizarpos/emvsample/services/discos/viewmodels/MeterValidationViewModel;", "meterName", "getMeterName", "setMeterName", "meterNumber", "getMeterNumber", "setMeterNumber", "meterType", "getMeterType", "setMeterType", "password", "getPassword", "setPassword", "productCode", "getProductCode", "setProductCode", "ref", "requestType", "getRequestType", "setRequestType", "service", "Lcom/wizarpos/util/Service;", "getService", "()Lcom/wizarpos/util/Service;", "setService", "(Lcom/wizarpos/util/Service;)V", "terminalID", "getTerminalID", "setTerminalID", "username", "getUsername", "setUsername", "validationProgressDialogue", "Landroid/app/ProgressDialog;", "getValidationProgressDialogue", "()Landroid/app/ProgressDialog;", "validationProgressDialogue$delegate", "wallet", "getWallet", "setWallet", "areFormFieldsFilled", "displayResponse", "", "error", "_message", "_meterName", "_meterNumber", "_productCode", "terminalId", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "selectProduct", "Lcom/wizarpos/util/Service$Product;", "context", "Landroid/content/Context;", "activity", "Landroid/app/Activity;", "validateMeterNumber", "meterNo", "Companion", "NIBSS_debug"})
public final class MeterValidationActivity extends android.support.v7.app.AppCompatActivity implements android.view.View.OnClickListener {
    @org.jetbrains.annotations.NotNull()
    public java.lang.String productCode;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String meterNumber;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String meterName;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String address;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String password;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String channel;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String username;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String wallet;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String authPin;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String requestType;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String meterType;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String terminalID;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String electricMeterType;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String clientReference;
    private java.lang.String ref;
    private final kotlin.Lazy disco$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy validationProgressDialogue$delegate = null;
    private com.wizarpos.emvsample.services.discos.viewmodels.MeterValidationViewModel mMeterValidationViewModel;
    private boolean isBeneficiary;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.util.Service service;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String METER_NAME = "meterName";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String METER_NUMBER = "meterNumber";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PRODUCT_CODE = "productCode";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REQUEST_TYPE = "requestType";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String METER_TYPE = "meterType";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CLIENT_REFERENCE = "clientReference";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TERMINAL_ID = "terminalId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ELECTRIC_METER_TYPE = "electricMeterType";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ADDRESS = "address";
    public static final com.wizarpos.emvsample.services.discos.activities.MeterValidationActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProductCode() {
        return null;
    }
    
    public final void setProductCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeterNumber() {
        return null;
    }
    
    public final void setMeterNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeterName() {
        return null;
    }
    
    public final void setMeterName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAddress() {
        return null;
    }
    
    public final void setAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPassword() {
        return null;
    }
    
    public final void setPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getChannel() {
        return null;
    }
    
    public final void setChannel(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUsername() {
        return null;
    }
    
    public final void setUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWallet() {
        return null;
    }
    
    public final void setWallet(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAuthPin() {
        return null;
    }
    
    public final void setAuthPin(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRequestType() {
        return null;
    }
    
    public final void setRequestType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeterType() {
        return null;
    }
    
    public final void setMeterType(@org.jetbrains.annotations.NotNull()
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
    public final java.lang.String getElectricMeterType() {
        return null;
    }
    
    public final void setElectricMeterType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getClientReference() {
        return null;
    }
    
    public final void setClientReference(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    private final java.lang.String getDisco() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.ProgressDialog getValidationProgressDialogue() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.util.Service getService() {
        return null;
    }
    
    public final void setService(@org.jetbrains.annotations.NotNull()
    com.wizarpos.util.Service p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void displayResponse(boolean error, java.lang.String _message, java.lang.String _meterName, java.lang.String _meterNumber, java.lang.String _productCode, java.lang.String requestType, java.lang.String meterType, java.lang.String clientReference, java.lang.String terminalId, java.lang.String electricMeterType) {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.Nullable()
    android.view.View v) {
    }
    
    private final void validateMeterNumber(android.app.Activity activity, java.lang.String electricMeterType, java.lang.String channel, java.lang.String wallet, java.lang.String username, java.lang.String requestType, java.lang.String meterNo, java.lang.String password, java.lang.String terminalId) {
    }
    
    private final boolean areFormFieldsFilled() {
        return false;
    }
    
    private final com.wizarpos.util.Service.Product selectProduct(android.content.Context context, android.app.Activity activity, com.wizarpos.util.Service service) {
        return null;
    }
    
    public MeterValidationActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/wizarpos/emvsample/services/discos/activities/MeterValidationActivity$Companion;", "", "()V", "ADDRESS", "", "CLIENT_REFERENCE", "ELECTRIC_METER_TYPE", "METER_NAME", "METER_NUMBER", "METER_TYPE", "PRODUCT_CODE", "REQUEST_TYPE", "TERMINAL_ID", "NIBSS_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}