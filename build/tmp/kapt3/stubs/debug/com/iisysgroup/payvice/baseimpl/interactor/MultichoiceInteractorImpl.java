package com.iisysgroup.payvice.baseimpl.interactor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J.\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0006H\u0016J\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001a2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0016R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\r0\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R#\u0010\u0012\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\tR#\u0010\u0016\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0017\u0010\t\u00a8\u0006$"}, d2 = {"Lcom/iisysgroup/payvice/baseimpl/interactor/MultichoiceInteractorImpl;", "Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "clientReference", "", "kotlin.jvm.PlatformType", "getClientReference", "()Ljava/lang/String;", "getContext", "()Landroid/content/Context;", "payviceServices", "Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;", "getPayviceServices", "()Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;", "setPayviceServices", "(Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;)V", "terminalId", "getTerminalId", "terminalId$delegate", "Lkotlin/Lazy;", "userId", "getUserId", "userId$delegate", "subscribe", "Lio/reactivex/Single;", "Lcom/itex/richard/payviceconnect/model/DstvModel$PayResponse;", "iuc", "plan", "Lcom/itex/richard/payviceconnect/model/DstvModel$Data;", "product", "Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor$MultichoiceProduct;", "authPin", "validateIucAndGetPlans", "Lcom/itex/richard/payviceconnect/model/DstvModel$DstvResponse;", "wari-wari_online_debug"})
public final class MultichoiceInteractorImpl implements com.iisysgroup.payvice.base.interactor.MultichoiceInteractor {
    private final kotlin.Lazy terminalId$delegate = null;
    private final kotlin.Lazy userId$delegate = null;
    private com.itex.richard.payviceconnect.wrapper.PayviceServices payviceServices;
    private final java.lang.String clientReference = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    private final java.lang.String getTerminalId() {
        return null;
    }
    
    private final java.lang.String getUserId() {
        return null;
    }
    
    public final com.itex.richard.payviceconnect.wrapper.PayviceServices getPayviceServices() {
        return null;
    }
    
    public final void setPayviceServices(com.itex.richard.payviceconnect.wrapper.PayviceServices p0) {
    }
    
    public final java.lang.String getClientReference() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.itex.richard.payviceconnect.model.DstvModel.DstvResponse> validateIucAndGetPlans(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct product) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.itex.richard.payviceconnect.model.DstvModel.PayResponse> subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc, @org.jetbrains.annotations.NotNull()
    com.itex.richard.payviceconnect.model.DstvModel.Data plan, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct product, @org.jetbrains.annotations.NotNull()
    java.lang.String authPin) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public MultichoiceInteractorImpl(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}