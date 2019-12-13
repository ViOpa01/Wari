package com.wizarpos.emvsample.payments_menu.handlers;

import java.lang.System;

/**
 * * Created by Agbede on 3/19/2018.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0016"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/handlers/Transfer;", "Lcom/wizarpos/emvsample/payments_menu/handlers/BaseHandler;", "lifecycleOwner", "Landroid/arch/lifecycle/LifecycleOwner;", "db", "Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "inputData", "Lcom/iisysgroup/poslib/utils/InputData;", "hostInteractor", "Lcom/iisysgroup/poslib/host/HostInteractor;", "connData", "Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "device", "Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;", "(Landroid/arch/lifecycle/LifecycleOwner;Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;Lcom/iisysgroup/poslib/utils/InputData;Lcom/iisysgroup/poslib/host/HostInteractor;Lcom/iisysgroup/poslib/host/entities/ConnectionData;Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;)V", "getInputData", "()Lcom/iisysgroup/poslib/utils/InputData;", "getLifecycleOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "getTransactionResult", "Landroid/arch/lifecycle/LiveData;", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "Wariok_debug"})
public final class Transfer extends com.wizarpos.emvsample.payments_menu.handlers.BaseHandler {
    @org.jetbrains.annotations.NotNull()
    private final android.arch.lifecycle.LifecycleOwner lifecycleOwner = null;
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.utils.InputData inputData = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.arch.lifecycle.LiveData<com.iisysgroup.poslib.host.entities.TransactionResult> getTransactionResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LifecycleOwner getLifecycleOwner() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.utils.InputData getInputData() {
        return null;
    }
    
    public Transfer(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.LifecycleOwner lifecycleOwner, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.dao.PosLibDatabase db, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.utils.InputData inputData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.HostInteractor hostInteractor, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData connData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor device) {
        super(null, null, null, null, null);
    }
}