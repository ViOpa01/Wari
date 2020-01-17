package com.iisysgroup.payvice.startimes.interactor;

import java.lang.System;

/**
 * * Created by Olije Favour on 6/24/2019.
 * *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JV\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020%H\u0016J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u001a2\u0006\u0010#\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR#\u0010\u0010\u001a\n \u000b*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R#\u0010\u0016\u001a\n \u000b*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0014\u001a\u0004\b\u0017\u0010\u0012\u00a8\u0006("}, d2 = {"Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractorImpl;", "Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "channel", "", "getContext", "()Landroid/content/Context;", "payviceServices", "Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;", "kotlin.jvm.PlatformType", "getPayviceServices", "()Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;", "setPayviceServices", "(Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;)V", "terminalId", "getTerminalId", "()Ljava/lang/String;", "terminalId$delegate", "Lkotlin/Lazy;", "type", "userId", "getUserId", "userId$delegate", "subscribe", "Lio/reactivex/Single;", "Lcom/itex/richard/payviceconnect/model/StartimesModel$payResponse;", "authPin", "password", "customerName", "phone", "productCode", "bouquet", "paymentMethod", "smartCardCode", "amount", "", "validateNumber", "Lcom/itex/richard/payviceconnect/model/StartimesModel$lookupResponse;", "wari-wari_online_release"})
public final class StartimesInteractorImpl implements com.iisysgroup.payvice.startimes.interactor.StartimesInteractor {
    private com.itex.richard.payviceconnect.wrapper.PayviceServices payviceServices;
    private final kotlin.Lazy terminalId$delegate = null;
    private final java.lang.String type = "default";
    private final java.lang.String channel = "ANDROIDPOS";
    private final kotlin.Lazy userId$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public final com.itex.richard.payviceconnect.wrapper.PayviceServices getPayviceServices() {
        return null;
    }
    
    public final void setPayviceServices(com.itex.richard.payviceconnect.wrapper.PayviceServices p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.itex.richard.payviceconnect.model.StartimesModel.payResponse> subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String authPin, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String customerName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String productCode, @org.jetbrains.annotations.NotNull()
    java.lang.String bouquet, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String smartCardCode, int amount) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.itex.richard.payviceconnect.model.StartimesModel.lookupResponse> validateNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String smartCardCode) {
        return null;
    }
    
    private final java.lang.String getTerminalId() {
        return null;
    }
    
    private final java.lang.String getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public StartimesInteractorImpl(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}