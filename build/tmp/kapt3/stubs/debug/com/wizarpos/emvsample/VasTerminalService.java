package com.wizarpos.emvsample;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0007J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\b"}, d2 = {"Lcom/wizarpos/emvsample/VasTerminalService;", "", "getVasTerminalDetails", "Lio/reactivex/Single;", "Lcom/iisysgroup/poslib/host/entities/VasTerminalData;", "tid", "", "Factory", "Wariok_debug"})
public abstract interface VasTerminalService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "xmerchant.php")
    public abstract io.reactivex.Single<com.iisysgroup.poslib.host.entities.VasTerminalData> getVasTerminalDetails(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "tid")
    java.lang.String tid);
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/wizarpos/emvsample/VasTerminalService$Factory;", "", "()V", "baseUrl", "", "getService", "Lcom/wizarpos/emvsample/VasTerminalService;", "Wariok_debug"})
    public static final class Factory {
        private static final java.lang.String baseUrl = "http://197.253.19.75/tams/eftpos/op/";
        public static final com.wizarpos.emvsample.VasTerminalService.Factory INSTANCE = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.emvsample.VasTerminalService getService() {
            return null;
        }
        
        private Factory() {
            super();
        }
    }
}