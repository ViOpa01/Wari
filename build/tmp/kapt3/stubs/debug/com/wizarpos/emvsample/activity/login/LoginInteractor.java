package com.wizarpos.emvsample.activity.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0003H&J&\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H&J(\u0010\f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H&J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&\u00a8\u0006\u0016"}, d2 = {"Lcom/wizarpos/emvsample/activity/login/LoginInteractor;", "", "getDeviceId", "", "getUserInfo", "Lio/reactivex/Single;", "Lcom/wizarpos/emvsample/activity/login/VasResult;", "userId", "login", "userID", "password", "walletId", "storeLoginDetails", "", "encryptedPassword", "key", "loginResult", "storePfmData", "data", "Lcom/wizarpos/emvsample/activity/login/PayviceForMerchants;", "summary", "Lcom/wizarpos/emvsample/activity/login/PayviceForMerchantsSummary;", "Wariok_debug"})
public abstract interface LoginInteractor {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.wizarpos.emvsample.activity.login.VasResult> getUserInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.wizarpos.emvsample.activity.login.VasResult> login(@org.jetbrains.annotations.NotNull()
    java.lang.String userID, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String walletId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getDeviceId();
    
    public abstract void storeLoginDetails(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String encryptedPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.VasResult loginResult);
    
    public abstract void storePfmData(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.PayviceForMerchants data, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.PayviceForMerchantsSummary summary);
}