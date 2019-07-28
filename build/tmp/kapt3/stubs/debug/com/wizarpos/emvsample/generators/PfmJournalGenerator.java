package com.wizarpos.emvsample.generators;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u0007H\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0002J\b\u0010\u0018\u001a\u00020\u0007H\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\b\u0010\u001a\u001a\u00020\u000eH\u0002J\b\u0010\u001b\u001a\u00020\u000eH\u0002J\b\u0010\u001c\u001a\u00020\u000eH\u0002J\b\u0010\u001d\u001a\u00020\u000eH\u0002J\b\u0010\u001e\u001a\u00020\u000eH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/wizarpos/emvsample/generators/PfmJournalGenerator;", "", "transactionResult", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "isReceiptPrinted", "", "amount", "", "cardData", "Lcom/iisysgroup/poslib/commons/emv/EmvCard;", "(Lcom/iisysgroup/poslib/host/entities/TransactionResult;Lcom/iisysgroup/poslib/host/entities/ConfigData;ZJLcom/iisysgroup/poslib/commons/emv/EmvCard;)V", "getAcode", "", "getAmount", "getMcc", "getMid", "getOacode", "getOrrn", "getOstan", "getRep", "getResp", "getRrn", "getTap", "getTimeStamp", "getTransMethod", "getVm", "getmPan", "getmti", "getstan", "NIBSS_debug"})
public final class PfmJournalGenerator {
    private final com.iisysgroup.poslib.host.entities.TransactionResult transactionResult = null;
    private final com.iisysgroup.poslib.host.entities.ConfigData configData = null;
    private final boolean isReceiptPrinted = false;
    private final long amount = 0L;
    private final com.iisysgroup.poslib.commons.emv.EmvCard cardData = null;
    
    private final java.lang.String getOacode() {
        return null;
    }
    
    private final java.lang.String getOrrn() {
        return null;
    }
    
    private final java.lang.String getOstan() {
        return null;
    }
    
    private final java.lang.String getTransMethod() {
        return null;
    }
    
    private final java.lang.String getVm() {
        return null;
    }
    
    private final boolean getRep() {
        return false;
    }
    
    private final java.lang.String getResp() {
        return null;
    }
    
    private final boolean getTap() {
        return false;
    }
    
    private final java.lang.String getMcc() {
        return null;
    }
    
    private final java.lang.String getmti() {
        return null;
    }
    
    private final java.lang.String getTimeStamp() {
        return null;
    }
    
    private final java.lang.String getAmount() {
        return null;
    }
    
    private final java.lang.String getAcode() {
        return null;
    }
    
    private final java.lang.String getRrn() {
        return null;
    }
    
    private final java.lang.String getmPan() {
        return null;
    }
    
    private final java.lang.String getstan() {
        return null;
    }
    
    private final java.lang.String getMid() {
        return null;
    }
    
    public PfmJournalGenerator(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.TransactionResult transactionResult, @org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.ConfigData configData, boolean isReceiptPrinted, long amount, @org.jetbrains.annotations.Nullable()
    com.iisysgroup.poslib.commons.emv.EmvCard cardData) {
        super();
    }
}