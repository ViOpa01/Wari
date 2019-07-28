package com.iisysgroup.payvice.baseimpl.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190 0\nH\u0016J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\bH\u0016J\u0016\u00103\u001a\u0002012\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00190 H\u0016J\u0010\u00104\u001a\u0002012\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u00105\u001a\u0002012\u0006\u00106\u001a\u000207H\u0016J\u0010\u00108\u001a\u0002012\u0006\u00109\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u0002012\u0006\u0010<\u001a\u00020\u0014H\u0016J\u001a\u0010=\u001a\u0002012\u0006\u0010>\u001a\u00020\u00142\b\b\u0002\u0010?\u001a\u00020\u0016H\u0002J\u0018\u0010@\u001a\u0002012\u0006\u0010A\u001a\u00020\u00162\u0006\u0010B\u001a\u00020\u0016H\u0016J\u0010\u0010C\u001a\u0002012\u0006\u0010A\u001a\u00020\u0016H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\fR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\n8F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\fR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\n8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\fR\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190 0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\n8F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\fR \u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00160&0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00160&0\n8F\u00a2\u0006\u0006\u001a\u0004\b(\u0010\fR\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00190\n8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010\fR\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00190\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00160\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006D"}, d2 = {"Lcom/iisysgroup/payvice/baseimpl/viewmodel/MultichoiceViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "Lcom/iisysgroup/payvice/base/presenter/MultichoicePresenter;", "appContext", "Landroid/app/Application;", "(Landroid/app/Application;)V", "error", "Landroid/arch/lifecycle/MutableLiveData;", "", "errorWatcher", "Landroid/arch/lifecycle/LiveData;", "getErrorWatcher", "()Landroid/arch/lifecycle/LiveData;", "interactor", "Lcom/iisysgroup/payvice/baseimpl/interactor/MultichoiceInteractorImpl;", "getInteractor", "()Lcom/iisysgroup/payvice/baseimpl/interactor/MultichoiceInteractorImpl;", "interactor$delegate", "Lkotlin/Lazy;", "isIucValidated", "", "iucNameLiveData", "", "getIucNameLiveData", "lValidationResponse", "error/NonExistentClass", "getLValidationResponse", "mValidationResponse", "paymentResponse", "paymentResponseLiveData", "getPaymentResponseLiveData", "plans", "", "product", "Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor$MultichoiceProduct;", "productLiveData", "getProductLiveData", "progressDialog", "Lkotlin/Pair;", "progressDialogLiveData", "getProgressDialogLiveData", "selectPlanLiveData", "getSelectPlanLiveData", "selectedPlan", "smartCardName", "tempList", "validated", "getPlans", "setError", "", "throwable", "setPlans", "setProduct", "setSelectedPlan", "position", "", "setService", "service", "Lcom/wizarpos/util/Service;", "setSmartCardIsValidated", "isValidated", "showProgressDialog", "show", "message", "subscribe", "iuc", "authPin", "validateIuc", "NIBSS_debug"})
public final class MultichoiceViewModel extends android.arch.lifecycle.AndroidViewModel implements com.iisysgroup.payvice.base.presenter.MultichoicePresenter {
    private java.util.List<? extends error.NonExistentClass> tempList;
    private final kotlin.Lazy interactor$delegate = null;
    private final android.arch.lifecycle.MutableLiveData<com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct> product = null;
    private final android.arch.lifecycle.MutableLiveData<java.lang.Throwable> error = null;
    private final android.arch.lifecycle.MutableLiveData<error.NonExistentClass> paymentResponse = null;
    private final android.arch.lifecycle.MutableLiveData<java.util.List<error.NonExistentClass>> plans = null;
    private final android.arch.lifecycle.MutableLiveData<error.NonExistentClass> selectedPlan = null;
    private final android.arch.lifecycle.MutableLiveData<java.lang.String> smartCardName = null;
    private final android.arch.lifecycle.MutableLiveData<error.NonExistentClass> mValidationResponse = null;
    private final android.arch.lifecycle.MutableLiveData<kotlin.Pair<java.lang.Boolean, java.lang.String>> progressDialog = null;
    private final android.arch.lifecycle.MutableLiveData<java.lang.Boolean> validated = null;
    
    private final com.iisysgroup.payvice.baseimpl.interactor.MultichoiceInteractorImpl getInteractor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct> getProductLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<java.lang.Throwable> getErrorWatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<error.NonExistentClass> getPaymentResponseLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<error.NonExistentClass> getSelectPlanLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<java.lang.String> getIucNameLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<error.NonExistentClass> getLValidationResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<kotlin.Pair<java.lang.Boolean, java.lang.String>> getProgressDialogLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LiveData<java.lang.Boolean> isIucValidated() {
        return null;
    }
    
    @java.lang.Override()
    public void setService(@org.jetbrains.annotations.NotNull()
    com.wizarpos.util.Service service) {
    }
    
    @java.lang.Override()
    public void setProduct(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct product) {
    }
    
    @java.lang.Override()
    public void setSelectedPlan(int position) {
    }
    
    @java.lang.Override()
    public void validateIuc(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc) {
    }
    
    @java.lang.Override()
    public void subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc, @org.jetbrains.annotations.NotNull()
    java.lang.String authPin) {
    }
    
    public void setPlans(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends error.NonExistentClass> plans) {
    }
    
    @java.lang.Override()
    public void setSmartCardIsValidated(boolean isValidated) {
    }
    
    @java.lang.Override()
    public void setError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable) {
    }
    
    private final void showProgressDialog(boolean show, java.lang.String message) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.arch.lifecycle.LiveData<java.util.List<error.NonExistentClass>> getPlans() {
        return null;
    }
    
    public MultichoiceViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application appContext) {
        super(null);
    }
}