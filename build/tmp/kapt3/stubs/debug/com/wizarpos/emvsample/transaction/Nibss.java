package com.wizarpos.emvsample.transaction;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 T2\u00020\u0001:\u0005TUVWXB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u00062\f\u00108\u001a\b\u0012\u0004\u0012\u00020:09J\u0010\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020\u0006H\u0002J\u0016\u0010=\u001a\b\u0012\u0004\u0012\u00020?0>2\u0006\u0010<\u001a\u00020\u0006H\u0002JL\u0010@\u001a\u0002062\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020P09JD\u0010@\u001a\u0002062\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020P09JL\u0010Q\u001a\u0002062\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020R2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020P09JD\u0010S\u001a\u0002062\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020P09R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0004R\u001a\u0010\u0011\u001a\u00020\u0012X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010\u0006X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR\u001a\u0010&\u001a\u00020\'X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010\u0006X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\b\"\u0004\b.\u0010\nR\u001a\u0010/\u001a\u000200X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104\u00a8\u0006Y"}, d2 = {"Lcom/wizarpos/emvsample/transaction/Nibss;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_clearSessionKey", "", "get_clearSessionKey$wari_wari_online_debug", "()Ljava/lang/String;", "set_clearSessionKey$wari_wari_online_debug", "(Ljava/lang/String;)V", "_clearpinKey", "get_clearpinKey$wari_wari_online_debug", "set_clearpinKey$wari_wari_online_debug", "getContext$wari_wari_online_debug", "()Landroid/content/Context;", "setContext$wari_wari_online_debug", "hostInteractor", "Lcom/iisysgroup/poslib/host/HostInteractor;", "getHostInteractor$wari_wari_online_debug", "()Lcom/iisysgroup/poslib/host/HostInteractor;", "setHostInteractor$wari_wari_online_debug", "(Lcom/iisysgroup/poslib/host/HostInteractor;)V", "initDb", "Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "getInitDb$wari_wari_online_debug", "()Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "setInitDb$wari_wari_online_debug", "(Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;)V", "initEodDb", "Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "getInitEodDb$wari_wari_online_debug", "()Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "setInitEodDb$wari_wari_online_debug", "(Lcom/wizarpos/emvsample/db/detailed/EodDoa;)V", "ip", "getIp$wari_wari_online_debug", "setIp$wari_wari_online_debug", "mainApp", "Lcom/wizarpos/emvsample/MainApp;", "getMainApp$wari_wari_online_debug", "()Lcom/wizarpos/emvsample/MainApp;", "setMainApp$wari_wari_online_debug", "(Lcom/wizarpos/emvsample/MainApp;)V", "port", "getPort$wari_wari_online_debug", "setPort$wari_wari_online_debug", "sslStatus", "", "getSslStatus$wari_wari_online_debug", "()Z", "setSslStatus$wari_wari_online_debug", "(Z)V", "configureTerminal", "", "terminalID", "t", "Lcom/wizarpos/emvsample/transaction/Nibss$Nibs;", "Lcom/wizarpos/emvsample/transaction/Nibss$NIbbsData;", "getVasKeys", "tid", "getVasTerminalService", "Lio/reactivex/Single;", "Lcom/iisysgroup/poslib/host/entities/VasTerminalData;", "goOnline", "emvCard", "Lcom/iisysgroup/poslib/commons/emv/EmvCard;", "transactionType", "Lcom/iisysgroup/poslib/host/Host$TransactionType;", "inputData", "Lcom/iisysgroup/poslib/utils/InputData;", "keyHolder", "Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "connectionData", "Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "processData", "Lcom/iisysgroup/poslib/ISO/common/IsoRefundProcessData;", "resultNibs", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "reverse", "Lcom/iisysgroup/poslib/ISO/common/IsoReversalProcessData;", "vasGoOnline", "Companion", "NIbbsData", "Nibs", "VasData", "saveVasKeyHolder", "wari-wari_online_debug"})
public final class Nibss {
    @org.jetbrains.annotations.NotNull()
    private com.iisysgroup.poslib.host.HostInteractor hostInteractor;
    @org.jetbrains.annotations.NotNull()
    private com.wizarpos.emvsample.MainApp mainApp;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String ip;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String port;
    private boolean sslStatus;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String _clearpinKey;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String _clearSessionKey;
    @org.jetbrains.annotations.NotNull()
    private com.wizarpos.emvsample.db.detailed.TransactionDataDoa initDb;
    @org.jetbrains.annotations.NotNull()
    private com.wizarpos.emvsample.db.detailed.EodDoa initEodDb;
    @org.jetbrains.annotations.NotNull()
    private android.content.Context context;
    @org.jetbrains.annotations.NotNull()
    public static com.iisysgroup.poslib.host.dao.PosLibDatabase poslibdb;
    private static final java.lang.String TAG = "Nibss";
    public static final com.wizarpos.emvsample.transaction.Nibss.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.HostInteractor getHostInteractor$wari_wari_online_debug() {
        return null;
    }
    
    public final void setHostInteractor$wari_wari_online_debug(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.HostInteractor p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.MainApp getMainApp$wari_wari_online_debug() {
        return null;
    }
    
    public final void setMainApp$wari_wari_online_debug(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.MainApp p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getIp$wari_wari_online_debug() {
        return null;
    }
    
    public final void setIp$wari_wari_online_debug(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPort$wari_wari_online_debug() {
        return null;
    }
    
    public final void setPort$wari_wari_online_debug(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final boolean getSslStatus$wari_wari_online_debug() {
        return false;
    }
    
    public final void setSslStatus$wari_wari_online_debug(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String get_clearpinKey$wari_wari_online_debug() {
        return null;
    }
    
    public final void set_clearpinKey$wari_wari_online_debug(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String get_clearSessionKey$wari_wari_online_debug() {
        return null;
    }
    
    public final void set_clearSessionKey$wari_wari_online_debug(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.TransactionDataDoa getInitDb$wari_wari_online_debug() {
        return null;
    }
    
    public final void setInitDb$wari_wari_online_debug(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.TransactionDataDoa p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.EodDoa getInitEodDb$wari_wari_online_debug() {
        return null;
    }
    
    public final void setInitEodDb$wari_wari_online_debug(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.EodDoa p0) {
    }
    
    public final void configureTerminal(@org.jetbrains.annotations.NotNull()
    java.lang.String terminalID, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.transaction.Nibss.Nibs<com.wizarpos.emvsample.transaction.Nibss.NIbbsData> t) {
    }
    
    public final void goOnline(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.commons.emv.EmvCard emvCard, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.Host.TransactionType transactionType, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.utils.InputData inputData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.KeyHolder keyHolder, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConfigData configData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData connectionData, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.transaction.Nibss.Nibs<com.iisysgroup.poslib.host.entities.TransactionResult> resultNibs) {
    }
    
    public final void vasGoOnline(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.commons.emv.EmvCard emvCard, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.Host.TransactionType transactionType, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.utils.InputData inputData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.KeyHolder keyHolder, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConfigData configData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData connectionData, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.transaction.Nibss.Nibs<com.iisysgroup.poslib.host.entities.TransactionResult> resultNibs) {
    }
    
    public final void goOnline(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.commons.emv.EmvCard emvCard, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.Host.TransactionType transactionType, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.utils.InputData inputData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.KeyHolder keyHolder, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConfigData configData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData connectionData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.ISO.common.IsoRefundProcessData processData, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.transaction.Nibss.Nibs<com.iisysgroup.poslib.host.entities.TransactionResult> resultNibs) {
    }
    
    public final void reverse(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.commons.emv.EmvCard emvCard, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.Host.TransactionType transactionType, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.utils.InputData inputData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.KeyHolder keyHolder, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConfigData configData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConnectionData connectionData, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.ISO.common.IsoReversalProcessData processData, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.transaction.Nibss.Nibs<com.iisysgroup.poslib.host.entities.TransactionResult> resultNibs) {
    }
    
    private final io.reactivex.Single<com.iisysgroup.poslib.host.entities.VasTerminalData> getVasTerminalService(java.lang.String tid) {
        return null;
    }
    
    private final void getVasKeys(java.lang.String tid) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext$wari_wari_online_debug() {
        return null;
    }
    
    public final void setContext$wari_wari_online_debug(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    public Nibss(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0005\u00a2\u0006\u0002\u0010\u0005J#\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\b\"\u00020\u0002H\u0014\u00a2\u0006\u0002\u0010\tJ\u0017\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0004\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/wizarpos/emvsample/transaction/Nibss$saveVasKeyHolder;", "Landroid/os/AsyncTask;", "Lcom/iisysgroup/poslib/host/entities/VasTerminalData;", "", "Ljava/lang/Void;", "()V", "doInBackground", "vasTerminalData", "", "([Lcom/iisysgroup/poslib/host/entities/VasTerminalData;)Ljava/lang/Void;", "onPostExecute", "", "result", "", "(Ljava/lang/Long;)V", "wari-wari_online_debug"})
    static final class saveVasKeyHolder extends android.os.AsyncTask<com.iisysgroup.poslib.host.entities.VasTerminalData, java.lang.Integer, java.lang.Void> {
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Override()
        protected java.lang.Void doInBackground(@org.jetbrains.annotations.NotNull()
        com.iisysgroup.poslib.host.entities.VasTerminalData... vasTerminalData) {
            return null;
        }
        
        protected final void onPostExecute(@org.jetbrains.annotations.Nullable()
        java.lang.Long result) {
        }
        
        public saveVasKeyHolder() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lcom/wizarpos/emvsample/transaction/Nibss$Nibs;", "T", "", "complete", "", "res", "(Ljava/lang/Object;)V", "error", "e", "", "wari-wari_online_debug"})
    public static abstract interface Nibs<T extends java.lang.Object> {
        
        public abstract void complete(T res);
        
        public abstract void error(@org.jetbrains.annotations.NotNull()
        java.lang.String e);
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/wizarpos/emvsample/transaction/Nibss$NIbbsData;", "Ljava/io/Serializable;", "k", "Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "connectionData", "Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "(Lcom/iisysgroup/poslib/host/entities/KeyHolder;Lcom/iisysgroup/poslib/host/entities/ConfigData;Lcom/iisysgroup/poslib/host/entities/ConnectionData;)V", "getConfigData", "()Lcom/iisysgroup/poslib/host/entities/ConfigData;", "getConnectionData", "()Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "keys", "keyHolder", "getKeyHolder", "()Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "setKeyHolder", "(Lcom/iisysgroup/poslib/host/entities/KeyHolder;)V", "wari-wari_online_debug"})
    public static final class NIbbsData implements java.io.Serializable {
        private com.iisysgroup.poslib.host.entities.KeyHolder k;
        @org.jetbrains.annotations.NotNull()
        private final com.iisysgroup.poslib.host.entities.ConfigData configData = null;
        @org.jetbrains.annotations.NotNull()
        private final com.iisysgroup.poslib.host.entities.ConnectionData connectionData = null;
        
        @org.jetbrains.annotations.Nullable()
        public final com.iisysgroup.poslib.host.entities.KeyHolder getKeyHolder() {
            return null;
        }
        
        public final void setKeyHolder(@org.jetbrains.annotations.Nullable()
        com.iisysgroup.poslib.host.entities.KeyHolder keys) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.iisysgroup.poslib.host.entities.ConfigData getConfigData() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.iisysgroup.poslib.host.entities.ConnectionData getConnectionData() {
            return null;
        }
        
        public NIbbsData(@org.jetbrains.annotations.Nullable()
        com.iisysgroup.poslib.host.entities.KeyHolder k, @org.jetbrains.annotations.NotNull()
        com.iisysgroup.poslib.host.entities.ConfigData configData, @org.jetbrains.annotations.NotNull()
        com.iisysgroup.poslib.host.entities.ConnectionData connectionData) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/wizarpos/emvsample/transaction/Nibss$VasData;", "Ljava/io/Serializable;", "k", "Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "connectionData", "Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "(Lcom/iisysgroup/poslib/host/entities/KeyHolder;Lcom/iisysgroup/poslib/host/entities/ConfigData;Lcom/iisysgroup/poslib/host/entities/ConnectionData;)V", "getConfigData", "()Lcom/iisysgroup/poslib/host/entities/ConfigData;", "getConnectionData", "()Lcom/iisysgroup/poslib/host/entities/ConnectionData;", "keys", "keyHolder", "getKeyHolder", "()Lcom/iisysgroup/poslib/host/entities/KeyHolder;", "setKeyHolder", "(Lcom/iisysgroup/poslib/host/entities/KeyHolder;)V", "wari-wari_online_debug"})
    public static final class VasData implements java.io.Serializable {
        private com.iisysgroup.poslib.host.entities.KeyHolder k;
        @org.jetbrains.annotations.NotNull()
        private final com.iisysgroup.poslib.host.entities.ConfigData configData = null;
        @org.jetbrains.annotations.NotNull()
        private final com.iisysgroup.poslib.host.entities.ConnectionData connectionData = null;
        
        @org.jetbrains.annotations.Nullable()
        public final com.iisysgroup.poslib.host.entities.KeyHolder getKeyHolder() {
            return null;
        }
        
        public final void setKeyHolder(@org.jetbrains.annotations.Nullable()
        com.iisysgroup.poslib.host.entities.KeyHolder keys) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.iisysgroup.poslib.host.entities.ConfigData getConfigData() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.iisysgroup.poslib.host.entities.ConnectionData getConnectionData() {
            return null;
        }
        
        public VasData(@org.jetbrains.annotations.Nullable()
        com.iisysgroup.poslib.host.entities.KeyHolder k, @org.jetbrains.annotations.NotNull()
        com.iisysgroup.poslib.host.entities.ConfigData configData, @org.jetbrains.annotations.NotNull()
        com.iisysgroup.poslib.host.entities.ConnectionData connectionData) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/wizarpos/emvsample/transaction/Nibss$Companion;", "", "()V", "TAG", "", "poslibdb", "Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "getPoslibdb", "()Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "setPoslibdb", "(Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;)V", "wari-wari_online_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.iisysgroup.poslib.host.dao.PosLibDatabase getPoslibdb() {
            return null;
        }
        
        public final void setPoslibdb(@org.jetbrains.annotations.NotNull()
        com.iisysgroup.poslib.host.dao.PosLibDatabase p0) {
        }
        
        private Companion() {
            super();
        }
    }
}