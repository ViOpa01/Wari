package com.iisysgroup.payvice.base.presenter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\n\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H&J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H&J\u0018\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH&J\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aH&\u00a8\u0006\u001d"}, d2 = {"Lcom/iisysgroup/payvice/base/presenter/MultichoicePresenter;", "", "getPlans", "Landroid/arch/lifecycle/LiveData;", "", "Lcom/itex/richard/payviceconnect/model/DstvModel$Data;", "setError", "", "throwable", "", "setPlans", "plans", "setProduct", "product", "Lcom/iisysgroup/payvice/base/interactor/MultichoiceInteractor$MultichoiceProduct;", "setSelectedPlan", "position", "", "setService", "service", "Lcom/wizarpos/util/Service;", "setSmartCardIsValidated", "isValidated", "", "subscribe", "iuc", "", "authPin", "validateIuc", "wari-wari_online_release"})
public abstract interface MultichoicePresenter {
    
    public abstract void setService(@org.jetbrains.annotations.NotNull()
    com.wizarpos.util.Service service);
    
    public abstract void setProduct(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.payvice.base.interactor.MultichoiceInteractor.MultichoiceProduct product);
    
    public abstract void setError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable);
    
    public abstract void setSelectedPlan(int position);
    
    public abstract void validateIuc(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc);
    
    public abstract void subscribe(@org.jetbrains.annotations.NotNull()
    java.lang.String iuc, @org.jetbrains.annotations.NotNull()
    java.lang.String authPin);
    
    public abstract void setPlans(@org.jetbrains.annotations.NotNull()
    java.util.List<com.itex.richard.payviceconnect.model.DstvModel.Data> plans);
    
    public abstract void setSmartCardIsValidated(boolean isValidated);
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.arch.lifecycle.LiveData<java.util.List<com.itex.richard.payviceconnect.model.DstvModel.Data>> getPlans();
}