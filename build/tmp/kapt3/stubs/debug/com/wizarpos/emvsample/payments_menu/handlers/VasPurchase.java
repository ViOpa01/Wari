package com.wizarpos.emvsample.payments_menu.handlers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0014J\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,\u00a8\u00060"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/handlers/VasPurchase;", "", "owner", "Landroid/arch/lifecycle/LifecycleOwner;", "db", "Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "inputData", "Lcom/iisysgroup/poslib/utils/InputData;", "hostInteractor", "Lcom/iisysgroup/poslib/host/HostInteractor;", "connData", "Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "emvInteractor", "Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "keyHolder", "Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "context", "Landroid/content/Context;", "(Landroid/arch/lifecycle/LifecycleOwner;Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;Lcom/iisysgroup/poslib/utils/InputData;Lcom/iisysgroup/poslib/host/HostInteractor;Lcom/iisysgroup/poslib/host/entities/ConnectionData;Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;Lcom/iisysgroup/poslib/host/entities/ConfigData;Lcom/iisysgroup/poslib/host/entities/KeyHolder;Landroid/content/Context;)V", "getConfigData", "()Lcom/iisysgroup/poslib/host/entities/ConfigData;", "setConfigData", "(Lcom/iisysgroup/poslib/host/entities/ConfigData;)V", "getConnData", "()Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "setConnData", "(Lcom/iisysgroup/poslib/host/entities/ConnectionData;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getDb", "()Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "getEmvInteractor", "()Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;", "getHostInteractor", "()Lcom/iisysgroup/poslib/host/HostInteractor;", "getInputData", "()Lcom/iisysgroup/poslib/utils/InputData;", "getKeyHolder", "()Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "setKeyHolder", "(Lcom/iisysgroup/poslib/host/entities/KeyHolder;)V", "getTransactionResult", "Lio/reactivex/Single;", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "Wariok_debug"})
public final class VasPurchase {
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.host.dao.PosLibDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.utils.InputData inputData = null;
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.host.HostInteractor hostInteractor = null;
    @org.jetbrains.annotations.NotNull()
    private com.iisysgroup.poslib.host.entities.ConnectionData connData;
    @org.jetbrains.annotations.NotNull()
    private final com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor emvInteractor = null;
    @org.jetbrains.annotations.NotNull()
    private com.iisysgroup.poslib.host.entities.ConfigData configData;
    @org.jetbrains.annotations.NotNull()
    private com.iisysgroup.poslib.host.entities.KeyHolder keyHolder;
    @org.jetbrains.annotations.NotNull()
    private android.content.Context context;
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<com.iisysgroup.poslib.host.entities.TransactionResult> getTransactionResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.dao.PosLibDatabase getDb() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.utils.InputData getInputData() {
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
    
    public final void setConnData(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor getEmvInteractor() {
        return null;
    }
    
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
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    public VasPurchase(@org.jetbrains.annotations.NotNull()
    android.arch.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.dao.PosLibDatabase db, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.utils.InputData inputData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.HostInteractor hostInteractor, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData connData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor emvInteractor, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConfigData configData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.KeyHolder keyHolder, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}