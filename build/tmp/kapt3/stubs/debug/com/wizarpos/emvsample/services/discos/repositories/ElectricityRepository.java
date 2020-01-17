package com.wizarpos.emvsample.services.discos.repositories;

import java.lang.System;

/**
 * * Created by Olije Favour on 6/26/2019.
 * *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J<\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/wizarpos/emvsample/services/discos/repositories/ElectricityRepository;", "", "payviceServices", "Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;", "(Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;)V", "getPayviceServices", "()Lcom/itex/richard/payviceconnect/wrapper/PayviceServices;", "getAbujaValidationResult", "Landroid/arch/lifecycle/MutableLiveData;", "Lcom/itex/richard/payviceconnect/model/AbujaModel$LookUpResponse;", "channel", "", "wallet", "username", "requestType", "meterNo", "meterType", "wari-wari_online_debug"})
public final class ElectricityRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.itex.richard.payviceconnect.wrapper.PayviceServices payviceServices = null;
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.MutableLiveData<com.itex.richard.payviceconnect.model.AbujaModel.LookUpResponse> getAbujaValidationResult(@org.jetbrains.annotations.NotNull()
    java.lang.String channel, @org.jetbrains.annotations.NotNull()
    java.lang.String wallet, @org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String requestType, @org.jetbrains.annotations.NotNull()
    java.lang.String meterNo, @org.jetbrains.annotations.NotNull()
    java.lang.String meterType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.itex.richard.payviceconnect.wrapper.PayviceServices getPayviceServices() {
        return null;
    }
    
    public ElectricityRepository(@org.jetbrains.annotations.NotNull()
    com.itex.richard.payviceconnect.wrapper.PayviceServices payviceServices) {
        super();
    }
}