package com.wizarpos.emvsample.activity.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0006H\u0016J&\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J(\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/wizarpos/emvsample/activity/login/LoginInteractorImpl;", "Lcom/wizarpos/emvsample/activity/login/LoginInteractor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getDeviceId", "", "getUserInfo", "Lio/reactivex/Single;", "Lcom/wizarpos/emvsample/activity/login/VasResult;", "userId", "login", "userID", "password", "walletId", "storeLoginDetails", "", "encryptedPassword", "key", "loginResult", "storePfmData", "data", "Lcom/wizarpos/emvsample/activity/login/PayviceForMerchants;", "summary", "Lcom/wizarpos/emvsample/activity/login/PayviceForMerchantsSummary;", "Wariok_debug"})
public final class LoginInteractorImpl implements com.wizarpos.emvsample.activity.login.LoginInteractor {
    private final android.content.Context context = null;
    
    @java.lang.Override()
    public void storePfmData(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.PayviceForMerchants data, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.PayviceForMerchantsSummary summary) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.wizarpos.emvsample.activity.login.VasResult> getUserInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<com.wizarpos.emvsample.activity.login.VasResult> login(@org.jetbrains.annotations.NotNull()
    java.lang.String userID, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String walletId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getDeviceId() {
        return null;
    }
    
    @java.lang.Override()
    public void storeLoginDetails(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.VasResult loginResult) {
    }
    
    public LoginInteractorImpl(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}