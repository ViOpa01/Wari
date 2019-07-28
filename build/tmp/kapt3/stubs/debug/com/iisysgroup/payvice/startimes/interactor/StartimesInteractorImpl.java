package com.iisysgroup.payvice.startimes.interactor;

import java.lang.System;

/**
 * * Created by Olije Favour on 6/24/2019.
 * *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JV\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020%H\u0016J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\u0006\u0010#\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR#\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R#\u0010\u0017\u001a\n \u0011*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u0018\u0010\u0013\u00a8\u0006\'"}, d2 = {"Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractorImpl;", "Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "channel", "", "getContext", "()Landroid/content/Context;", "payviceServices", "error/NonExistentClass", "getPayviceServices", "()Lerror/NonExistentClass;", "setPayviceServices", "(Lerror/NonExistentClass;)V", "Lerror/NonExistentClass;", "terminalId", "kotlin.jvm.PlatformType", "getTerminalId", "()Ljava/lang/String;", "terminalId$delegate", "Lkotlin/Lazy;", "type", "userId", "getUserId", "userId$delegate", "subscribe", "Lio/reactivex/Single;", "authPin", "password", "customerName", "phone", "productCode", "bouquet", "paymentMethod", "smartCardCode", "amount", "", "validateNumber", "NIBSS_debug"})
public final class StartimesInteractorImpl implements com.iisysgroup.payvice.startimes.interactor.StartimesInteractor {
    @org.jetbrains.annotations.NotNull()
    private error.NonExistentClass payviceServices;
    private final kotlin.Lazy terminalId$delegate = null;
    private final java.lang.String type = "default";
    private final java.lang.String channel = "ANDROIDPOS";
    private final kotlin.Lazy userId$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    @org.jetbrains.annotations.NotNull()
    public final error.NonExistentClass getPayviceServices() {
        return null;
    }
    
    public final void setPayviceServices(@org.jetbrains.annotations.NotNull()
    error.NonExistentClass p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<error.NonExistentClass> subscribe(@org.jetbrains.annotations.NotNull()
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
    public io.reactivex.Single<error.NonExistentClass> validateNumber(@org.jetbrains.annotations.NotNull()
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