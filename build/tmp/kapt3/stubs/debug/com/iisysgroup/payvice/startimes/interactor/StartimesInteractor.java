package com.iisysgroup.payvice.startimes.interactor;

import java.lang.System;

/**
 * * Created by Olije Favour on 6/24/2019.
 * *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0012JV\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0006\u0010\r\u001a\u00020\u0006H&\u00a8\u0006\u0013"}, d2 = {"Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractor;", "", "subscribe", "Lio/reactivex/Single;", "Lcom/itex/richard/payviceconnect/model/StartimesModel$payResponse;", "authPin", "", "password", "customerName", "phone", "productCode", "bouquet", "paymentMethod", "smartCardCode", "amount", "", "validateNumber", "Lcom/itex/richard/payviceconnect/model/StartimesModel$lookupResponse;", "StartimesProduct", "Wariok_debug"})
public abstract interface StartimesInteractor {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.itex.richard.payviceconnect.model.StartimesModel.lookupResponse> validateNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String smartCardCode);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.itex.richard.payviceconnect.model.StartimesModel.payResponse> subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String authPin, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String customerName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String productCode, @org.jetbrains.annotations.NotNull()
    java.lang.String bouquet, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String smartCardCode, int amount);
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractor$StartimesProduct;", "", "(Ljava/lang/String;I)V", "toString", "", "STARTIMES", "Wariok_debug"})
    public static enum StartimesProduct {
        /*public static final*/ STARTIMES /* = new STARTIMES() */;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        StartimesProduct() {
        }
    }
}