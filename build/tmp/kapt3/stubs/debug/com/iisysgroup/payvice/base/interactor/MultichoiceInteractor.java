package com.iisysgroup.payvice.base.interactor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001\rJ3\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H&\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\u000e"}, d2 = {"Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor;", "", "subscribe", "Lio/reactivex/Single;", "error/NonExistentClass", "iuc", "", "plan", "product", "Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor$MultichoiceProduct;", "authPin", "(Ljava/lang/String;Lerror/NonExistentClass;Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor$MultichoiceProduct;Ljava/lang/String;)Lio/reactivex/Single;", "validateIucAndGetPlans", "MultichoiceProduct", "NIBSS_debug"})
public abstract interface MultichoiceInteractor {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<error.NonExistentClass> validateIucAndGetPlans(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct product);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<error.NonExistentClass> subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc, @org.jetbrains.annotations.NotNull()
    error.NonExistentClass plan, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct product, @org.jetbrains.annotations.NotNull()
    java.lang.String authPin);
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor$MultichoiceProduct;", "", "(Ljava/lang/String;I)V", "toString", "", "DSTV", "GOTV", "NIBSS_debug"})
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