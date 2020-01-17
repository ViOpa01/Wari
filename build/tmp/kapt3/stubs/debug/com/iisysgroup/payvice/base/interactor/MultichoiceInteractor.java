package com.iisysgroup.payvice.base.interactor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u000eJ.\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H&J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000f"}, d2 = {"Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor;", "", "subscribe", "Lio/reactivex/Single;", "Lcom/itex/richard/payviceconnect/model/DstvModel$PayResponse;", "iuc", "", "plan", "Lcom/itex/richard/payviceconnect/model/DstvModel$Data;", "product", "Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor$MultichoiceProduct;", "authPin", "validateIucAndGetPlans", "Lcom/itex/richard/payviceconnect/model/DstvModel$DstvResponse;", "MultichoiceProduct", "wari-wari_online_debug"})
public abstract interface MultichoiceInteractor {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.itex.richard.payviceconnect.model.DstvModel.DstvResponse> validateIucAndGetPlans(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct product);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<com.itex.richard.payviceconnect.model.DstvModel.PayResponse> subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc, @org.jetbrains.annotations.NotNull()
    com.itex.richard.payviceconnect.model.DstvModel.Data plan, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct product, @org.jetbrains.annotations.NotNull()
    java.lang.String authPin);
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor$MultichoiceProduct;", "", "(Ljava/lang/String;I)V", "toString", "", "DSTV", "GOTV", "wari-wari_online_debug"})
    public static enum MultichoiceProduct {
        /*public static final*/ DSTV /* = new DSTV() */,
        /*public static final*/ GOTV /* = new GOTV() */;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        MultichoiceProduct() {
        }
    }
}