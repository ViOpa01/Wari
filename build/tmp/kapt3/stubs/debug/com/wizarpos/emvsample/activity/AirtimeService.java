package com.wizarpos.emvsample.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\t"}, d2 = {"Lcom/wizarpos/emvsample/activity/AirtimeService;", "", "airtimeCardPurchase", "Lretrofit2/Call;", "error/NonExistentClass", "request", "Lcom/wizarpos/emvsample/activity/AirtimeRequestDetails;", "airtimePurchase", "Factory", "NIBSS_debug"})
public abstract interface AirtimeService {
    public static final com.wizarpos.emvsample.activity.AirtimeService.Factory Factory = null;
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "vas/vtu/purchase")
    @retrofit2.http.Headers(value = {"Authorization:IISYSGROUP c1e750cf89b05b0fc56eecf6fc25cce85e2bb8e0c46d7bfed463f6c6c89d4b8e", "sysid:ee2dadd1e684032929a2cea40d1b9a2453435da4f588c1ee88b1e76abb566c31", "Content-Type:application/json"})
    public abstract retrofit2.Call<error.NonExistentClass> airtimePurchase(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wizarpos.emvsample.activity.AirtimeRequestDetails request);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "vas/card/vtu/purchase")
    @retrofit2.http.Headers(value = {"Authorization:IISYSGROUP c1e750cf89b05b0fc56eecf6fc25cce85e2bb8e0c46d7bfed463f6c6c89d4b8e", "sysid:ee2dadd1e684032929a2cea40d1b9a2453435da4f588c1ee88b1e76abb566c31", "Content-Type:application/json"})
    public abstract retrofit2.Call<error.NonExistentClass> airtimeCardPurchase(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wizarpos.emvsample.activity.AirtimeRequestDetails request);
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/wizarpos/emvsample/activity/AirtimeService$Factory;", "", "()V", "BASE_URL", "", "getBASE_URL", "()Ljava/lang/String;", "create", "Lcom/wizarpos/emvsample/activity/AirtimeService;", "NIBSS_debug"})
    public static final class Factory {
        @org.jetbrains.annotations.NotNull()
        private static final java.lang.String BASE_URL = "http://vas.itexapp.com/";
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBASE_URL() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.emvsample.activity.AirtimeService create() {
            return null;
        }
        
        private Factory() {
            super();
        }
    }
}