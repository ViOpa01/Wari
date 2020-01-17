package com.wizarpos.emvsample.services.discos.viewmodels;

import java.lang.System;

/**
 * * Created by Olije Favour on 7/1/2019.
 * *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020-J\b\u0010.\u001a\u00020/H\u0016J\u0010\u00100\u001a\u00020/2\u0006\u00101\u001a\u000202H\u0016J\u0010\u00103\u001a\u00020/2\u0006\u00104\u001a\u00020\u0003H\u0016J\u0010\u00105\u001a\u00020/2\u0006\u00106\u001a\u000207H\u0016J\u0096\u0001\u00108\u001a\u00020/2\u0006\u0010,\u001a\u00020-2\u0006\u00109\u001a\u00020\u00142\u0006\u0010:\u001a\u00020\u00142\u0006\u0010;\u001a\u00020\u00142\u0006\u0010<\u001a\u00020\u00142\u0006\u0010=\u001a\u00020\u00142\u0006\u0010>\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\u00142\u0006\u0010@\u001a\u00020\u00142\u0006\u0010A\u001a\u00020\u00142\u0006\u0010B\u001a\u00020\u00142\u0006\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u00020\u00142\u0006\u0010E\u001a\u00020\u00142\u0006\u0010F\u001a\u00020\u00142\u0006\u0010G\u001a\u00020\u00142\u0006\u0010H\u001a\u00020\u00142\u0006\u0010I\u001a\u00020JR\u001a\u0010\u0007\u001a\u00020\bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00138F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010\"\u001a\n $*\u0004\u0018\u00010#0#X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(\u00a8\u0006K"}, d2 = {"Lcom/wizarpos/emvsample/services/discos/viewmodels/EleectricityPaymentVM;", "Landroid/arch/lifecycle/AndroidViewModel;", "Lio/reactivex/Observer;", "", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "dialog", "Landroid/app/ProgressDialog;", "getDialog", "()Landroid/app/ProgressDialog;", "setDialog", "(Landroid/app/ProgressDialog;)V", "isValidation", "", "()Z", "setValidation", "(Z)V", "lError", "Landroid/arch/lifecycle/LiveData;", "", "getLError", "()Landroid/arch/lifecycle/LiveData;", "lPaymentRes", "getLPaymentRes", "mError", "Landroid/arch/lifecycle/MutableLiveData;", "mPaymentRes", "mProduct", "Lcom/wizarpos/util/Service$Product;", "getMProduct", "()Lcom/wizarpos/util/Service$Product;", "setMProduct", "(Lcom/wizarpos/util/Service$Product;)V", "payviceServices", "Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;", "kotlin.jvm.PlatformType", "getPayviceServices", "()Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;", "setPayviceServices", "(Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;)V", "loginProgressDialog", "message", "showDialogue", "activity", "Landroid/app/Activity;", "onComplete", "", "onError", "e", "", "onNext", "t", "onSubscribe", "d", "Lio/reactivex/disposables/Disposable;", "payElectricBill", "amount", "wallet", "userName", "requestType", "meterType", "meterNumber", "channel", "phone", "productCode", "pin", "paymentMetod", "electricMeterType", "password", "customerName", "clientReference", "terminalId", "pfm", "Lcom/itex/richard/payviceconnect/model/Pfm;", "wari-wari_online_debug"})
public final class EleectricityPaymentVM extends android.arch.lifecycle.AndroidViewModel implements io.reactivex.Observer<java.lang.Object> {
    private boolean isValidation;
    private com.itex.richard.payviceconnect.wrapper.PayviceServices payviceServices;
    @org.jetbrains.annotations.Nullable()
    private com.wizarpos.util.Service.Product mProduct;
    @org.jetbrains.annotations.NotNull()
    public android.app.ProgressDialog dialog;
    private final android.arch.lifecycle.MutableLiveData<java.lang.String> mError = null;
    private final android.arch.lifecycle.MutableLiveData<java.lang.Object> mPaymentRes = null;
    
    public final boolean isValidation() {
        return false;
    }
    
    public final void setValidation(boolean p0) {
    }
    
    public final com.itex.richard.payviceconnect.wrapper.PayviceServices getPayviceServices() {
        return null;
    }
    
    public final void setPayviceServices(com.itex.richard.payviceconnect.wrapper.PayviceServices p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.wizarpos.util.Service.Product getMProduct() {
        return null;
    }
    
    public final void setMProduct(@org.jetbrains.annotations.Nullable()
    com.wizarpos.util.Service.Product p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.ProgressDialog loginProgressDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String message, boolean showDialogue, @org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.ProgressDialog getDialog() {
        return null;
    }
    
    public final void setDialog(@org.jetbrains.annotations.NotNull()
    android.app.ProgressDialog p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<java.lang.String> getLError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<java.lang.Object> getLPaymentRes() {
        return null;
    }
    
    @java.lang.Override()
    public void onComplete() {
    }
    
    @java.lang.Override()
    public void onSubscribe(@org.jetbrains.annotations.NotNull()
    io.reactivex.disposables.Disposable d) {
    }
    
    @java.lang.Override()
    public void onNext(@org.jetbrains.annotations.NotNull()
    java.lang.Object t) {
    }
    
    @java.lang.Override()
    public void onError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable e) {
    }
    
    public final void payElectricBill(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String amount, @org.jetbrains.annotations.NotNull()
    java.lang.String wallet, @org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String requestType, @org.jetbrains.annotations.NotNull()
    java.lang.String meterType, @org.jetbrains.annotations.NotNull()
    java.lang.String meterNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String channel, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String productCode, @org.jetbrains.annotations.NotNull()
    java.lang.String pin, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMetod, @org.jetbrains.annotations.NotNull()
    java.lang.String electricMeterType, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String customerName, @org.jetbrains.annotations.NotNull()
    java.lang.String clientReference, @org.jetbrains.annotations.NotNull()
    java.lang.String terminalId, @org.jetbrains.annotations.NotNull()
    com.itex.richard.payviceconnect.model.Pfm pfm) {
    }
    
    public EleectricityPaymentVM(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
}