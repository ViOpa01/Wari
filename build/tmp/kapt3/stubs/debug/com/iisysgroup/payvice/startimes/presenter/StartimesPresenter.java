package com.iisysgroup.payvice.startimes.presenter;

import java.lang.System;

/**
 * * Created by Olije Favour on 6/24/2019.
 * *Copyright (c) 2019  Itex Integrated Services  All rights reserved.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&JP\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH&J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0011H&\u00a8\u0006\u001c"}, d2 = {"Lcom/iisysgroup/payvice/startimes/presenter/StartimesPresenter;", "", "setError", "", "throwable", "", "setProduct", "product", "Lcom/iisysgroup/payvice/startimes/interactor/StartimesInteractor$StartimesProduct;", "setService", "service", "Lcom/wizarpos/util/Service;", "setSmartCardIsValidated", "isValidated", "", "subscribe", "authPin", "", "password", "customerName", "phone", "productCode", "bouquet", "paymentMethod", "smartCardCode", "amount", "", "validateSmartCardCode", "Wariok_debug"})
public abstract interface StartimesPresenter {
    
    public abstract void setService(@org.jetbrains.annotations.NotNull()
    com.wizarpos.util.Service service);
    
    public abstract void setProduct(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.startimes.interactor.StartimesInteractor.StartimesProduct product);
    
    public abstract void setError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable);
    
    public abstract void validateSmartCardCode(@org.jetbrains.annotations.NotNull()
    java.lang.String smartCardCode);
    
    public abstract void subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String authPin, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String customerName, @org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String productCode, @org.jetbrains.annotations.NotNull()
    java.lang.String bouquet, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentMethod, @org.jetbrains.annotations.NotNull()
    java.lang.String smartCardCode, int amount);
    
    public abstract void setSmartCardIsValidated(boolean isValidated);
}