package com.iisysgroup.payvice.startimes.viewmodel;

import java.lang.System;

/**
 * * Created by Olije Favour on 6/24/2019.
 * *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\bH\u0016J\u0010\u00100\u001a\u00020.2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u00101\u001a\u00020.2\u0006\u00102\u001a\u000203H\u0016J\u0010\u00104\u001a\u00020.2\u0006\u00105\u001a\u00020\u0014H\u0016J\u001a\u00106\u001a\u00020.2\u0006\u00107\u001a\u00020\u00142\b\b\u0002\u00108\u001a\u00020\u0016H\u0002JP\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\u00162\u0006\u0010<\u001a\u00020\u00162\u0006\u0010=\u001a\u00020\u00162\u0006\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u00162\u0006\u0010@\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u00162\u0006\u0010B\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u00020.2\u0006\u0010A\u001a\u00020\u0016H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\fR\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\n8F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\fR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\n8F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\fR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\n8F\u00a2\u0006\u0006\u001a\u0004\b#\u0010\fR \u0010$\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00160%0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00160%0\n8F\u00a2\u0006\u0006\u001a\u0004\b\'\u0010\fR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001d0\n8F\u00a2\u0006\u0006\u001a\u0004\b)\u0010\fR\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00160\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2 = {"Lcom/iisysgroup/payvice/startimes/viewmodel/StartimesViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "Lcom/iisysgroup/payvice/startimes/presenter/StartimesPresenter;", "appContext", "Landroid/app/Application;", "(Landroid/app/Application;)V", "error", "Landroid/arch/lifecycle/MutableLiveData;", "", "errorWatcher", "Landroid/arch/lifecycle/LiveData;", "getErrorWatcher", "()Landroid/arch/lifecycle/LiveData;", "interactor", "Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractorImpl;", "getInteractor", "()Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractorImpl;", "interactor$delegate", "Lkotlin/Lazy;", "isSmartcardValidated", "", "iucNameLiveData", "", "getIucNameLiveData", "lLookUpResponse", "Lcom/itex/richard/payviceconnect/model/StartimesModel$lookupResponse;", "getLLookUpResponse", "lookUpResponse", "paymentResponse", "Lcom/itex/richard/payviceconnect/model/StartimesModel$payResponse;", "paymentResponseLiveData", "getPaymentResponseLiveData", "product", "Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractor$StartimesProduct;", "productLiveData", "getProductLiveData", "progressDialog", "Lkotlin/Pair;", "progressDialogLiveData", "getProgressDialogLiveData", "selectPlanLiveData", "getSelectPlanLiveData", "selectedPlan", "smartCardName", "validated", "setError", "", "throwable", "setProduct", "setService", "service", "Lcom/wizarpos/util/Service;", "setSmartCardIsValidated", "isValidated", "showProgressDialog", "show", "message", "subscribe", "authPin", "password", "customerName", "phone", "productCode", "bouquet", "paymentMethod", "smartCardCode", "amount", "", "validateSmartCardCode", "wari-wari_online_debug"})
public final class StartimesViewModel extends android.arch.lifecycle.AndroidViewModel implements com.iisysgroup.payvice.startimes.presenter.StartimesPresenter {
    private final kotlin.Lazy interactor$delegate = null;
    private android.arch.lifecycle.MutableLiveData<com.itex.richard.payviceconnect.model.StartimesModel.lookupResponse> lookUpResponse;
    private final android.arch.lifecycle.MutableLiveData<java.lang.String> smartCardName = null;
    private final android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.Boolean, java.lang.String>> progressDialog = null;
    private final android.arch.lifecycle.MutableLiveData<com.itex.richard.payviceconnect.model.StartimesModel.payResponse> paymentResponse = null;
    private final android.arch.lifecycle.MutableLiveData<java.lang.Throwable> error = null;
    private final android.arch.lifecycle.MutableLiveData<com.iisysgroup.payvice.startimes.interactor.StartimesInteractor.StartimesProduct> product = null;
    private final android.arch.lifecycle.MutableLiveData<java.lang.Boolean> validated = null;
    private final android.arch.lifecycle.MutableLiveData<com.itex.richard.payviceconnect.model.StartimesModel.payResponse> selectedPlan = null;
    
    private final com.iisysgroup.payvice.startimes.interactor.StartimesInteractorImpl getInteractor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<com.itex.richard.payviceconnect.model.StartimesModel.lookupResponse> getLLookUpResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<java.lang.String> getIucNameLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<kotlin.Pair<java.lang.Boolean, java.lang.String>> getProgressDialogLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<com.itex.richard.payviceconnect.model.StartimesModel.payResponse> getPaymentResponseLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<java.lang.Throwable> getErrorWatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<com.iisysgroup.payvice.startimes.interactor.StartimesInteractor.StartimesProduct> getProductLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<java.lang.Boolean> isSmartcardValidated() {
        return null;
    }
    
    @java.lang.Override()
    public void setSmartCardIsValidated(boolean isValidated) {
    }
    
    @java.lang.Override()
    public void setProduct(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.startimes.interactor.StartimesInteractor.StartimesProduct product) {
    }
    
    @java.lang.Override()
    public void setError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable) {
    }
    
    @java.lang.Override()
    public void validateSmartCardCode(@org.jetbrains.annotations.NotNull()
    java.lang.String smartCardCode) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<com.itex.richard.payviceconnect.model.StartimesModel.payResponse> getSelectPlanLiveData() {
        return null;
    }
    
    @java.lang.Override()
    public void subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String authPin, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String customerName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String productCode, @org.jetbrains.annotations.NotNull()
    java.lang.String bouquet, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String smartCardCode, int amount) {
    }
    
    @java.lang.Override()
    public void setService(@org.jetbrains.annotations.NotNull()
    com.wizarpos.util.Service service) {
    }
    
    private final void showProgressDialog(boolean show, java.lang.String message) {
    }
    
    public StartimesViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application appContext) {
        super(null);
    }
}