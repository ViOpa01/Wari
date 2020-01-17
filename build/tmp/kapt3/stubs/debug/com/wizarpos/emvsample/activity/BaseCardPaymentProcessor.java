package com.wizarpos.emvsample.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\u000e\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020)J\b\u00100\u001a\u00020+H\u0002J\b\u00101\u001a\u00020+H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R#\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/wizarpos/emvsample/activity/BaseCardPaymentProcessor;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "getConfigData", "()Lcom/iisysgroup/poslib/host/entities/ConfigData;", "setConfigData", "(Lcom/iisysgroup/poslib/host/entities/ConfigData;)V", "keyHolder", "Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "getKeyHolder", "()Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "setKeyHolder", "(Lcom/iisysgroup/poslib/host/entities/KeyHolder;)V", "mConnectionData", "Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "getMConnectionData", "()Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "setMConnectionData", "(Lcom/iisysgroup/poslib/host/entities/ConnectionData;)V", "mDb", "Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "kotlin.jvm.PlatformType", "getMDb", "()Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "mDb$delegate", "Lkotlin/Lazy;", "mEmvInteractor", "Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;", "getMEmvInteractor", "()Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;", "setMEmvInteractor", "(Lcom/iisysgroup/poslib/deviceinterface/interactors/EmvInteractor;)V", "mHostInteractor", "Lcom/iisysgroup/poslib/host/HostInteractor;", "getMHostInteractor", "()Lcom/iisysgroup/poslib/host/HostInteractor;", "setMHostInteractor", "(Lcom/iisysgroup/poslib/host/HostInteractor;)V", "mRrn", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setTransactionRrn", "rrn", "setUpConnectionData", "setUpHostInteractor", "wari-wari_online_debug"})
public abstract class BaseCardPaymentProcessor extends android.support.v7.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor mEmvInteractor;
    @org.jetbrains.annotations.NotNull()
    public com.iisysgroup.poslib.host.entities.ConnectionData mConnectionData;
    @org.jetbrains.annotations.NotNull()
    public com.iisysgroup.poslib.host.HostInteractor mHostInteractor;
    @org.jetbrains.annotations.NotNull()
    public com.iisysgroup.poslib.host.entities.ConfigData configData;
    @org.jetbrains.annotations.NotNull()
    public com.iisysgroup.poslib.host.entities.KeyHolder keyHolder;
    private java.lang.String mRrn;
    private final kotlin.Lazy mDb$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor getMEmvInteractor() {
        return null;
    }
    
    public final void setMEmvInteractor(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.deviceinterface.interactors.EmvInteractor p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.entities.ConnectionData getMConnectionData() {
        return null;
    }
    
    public final void setMConnectionData(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.HostInteractor getMHostInteractor() {
        return null;
    }
    
    public final void setMHostInteractor(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.HostInteractor p0) {
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
    
    private final com.iisysgroup.poslib.host.dao.PosLibDatabase getMDb() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpHostInteractor() {
    }
    
    private final void setUpConnectionData() {
    }
    
    public final void setTransactionRrn(@org.jetbrains.annotations.NotNull()
    java.lang.String rrn) {
    }
    
    public BaseCardPaymentProcessor() {
        super();
    }
}