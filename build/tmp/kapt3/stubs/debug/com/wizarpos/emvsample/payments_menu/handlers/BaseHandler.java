package com.wizarpos.emvsample.payments_menu.handlers;

import java.lang.System;

/**
 * * Created by Bamitale@Itex on 06/03/2018.
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u000e\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H&R\u001a\u0010\r\u001a\u00020\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"\u00a8\u0006&"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/handlers/BaseHandler;", "", "owner", "Landroid/arch/lifecycle/LifecycleOwner;", "db", "Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "hostInteractor", "Lcom/iisysgroup/poslib/host/HostInteractor;", "connData", "Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "emvInteractor", "Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;", "(Landroid/arch/lifecycle/LifecycleOwner;Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;Lcom/iisysgroup/poslib/host/HostInteractor;Lcom/iisysgroup/poslib/host/entities/ConnectionData;Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;)V", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "getConfigData", "()Lcom/iisysgroup/poslib/host/entities/ConfigData;", "setConfigData", "(Lcom/iisysgroup/poslib/host/entities/ConfigData;)V", "getConnData", "()Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "getDb", "()Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "getEmvInteractor", "()Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;", "getHostInteractor", "()Lcom/iisysgroup/poslib/host/HostInteractor;", "keyHolder", "Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "getKeyHolder", "()Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "setKeyHolder", "(Lcom/iisysgroup/poslib/host/entities/KeyHolder;)V", "getOwner", "()Landroid/arch/lifecycle/LifecycleOwner;", "getTransactionResult", "Landroid/arch/lifecycle/LiveData;", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "NIBSS_debug"})
public abstract class BaseHandler {
    @org.jetbrains.annotations.NotNull()
    public com.iisysgroup.poslib.host.entities.ConfigData configData;
    @org.jetbrains.annotations.NotNull()
    public com.iisysgroup.poslib.host.entities.KeyHolder keyHolder;
    @org.jetbrains.annotations.NotNull()
    private final android.arch.lifecycle.LifecycleOwner owner = null;
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.host.dao.PosLibDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.host.HostInteractor hostInteractor = null;
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.host.entities.ConnectionData connData = null;
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor emvInteractor = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.entities.ConfigData getConfigData() {
        return null;
    }
    
    public final void setConfigData(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConfigData p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.entities.KeyHolder getKeyHolder() {
        return null;
    }
    
    public final void setKeyHolder(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.KeyHolder p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.arch.lifecycle.LiveData<com.iisysgroup.poslib.host.entities.TransactionResult> getTransactionResult();
    
    @org.jetbrains.annotations.NotNull()
    public final android.arch.lifecycle.LifecycleOwner getOwner() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.dao.PosLibDatabase getDb() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.HostInteractor getHostInteractor() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.entities.ConnectionData getConnData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor getEmvInteractor() {
        return null;
    }
    
    public BaseHandler(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.dao.PosLibDatabase db, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.HostInteractor hostInteractor, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData connData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor emvInteractor) {
        super();
    }
}