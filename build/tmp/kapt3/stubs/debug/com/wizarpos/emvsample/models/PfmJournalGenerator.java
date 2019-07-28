package com.wizarpos.emvsample.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0013\u0018\u00002\u00020\u0001BK\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\tH\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\tH\u0002J\b\u0010\u001c\u001a\u00020\tH\u0002J\b\u0010\u001d\u001a\u00020\tH\u0002J\b\u0010\u001e\u001a\u00020\tH\u0002J\b\u0010\u001f\u001a\u00020\tH\u0002J\b\u0010 \u001a\u00020\tH\u0002J\b\u0010!\u001a\u00020\u0007H\u0002J\b\u0010\"\u001a\u00020\tH\u0002J\b\u0010#\u001a\u00020\tH\u0002J\b\u0010$\u001a\u00020\u0007H\u0002J\b\u0010%\u001a\u00020\tH\u0002J\b\u0010&\u001a\u00020\tH\u0002J\b\u0010\'\u001a\u00020\tH\u0002J\b\u0010(\u001a\u00020\tH\u0002J\b\u0010)\u001a\u00020\tH\u0002J\b\u0010*\u001a\u00020\tH\u0002J\b\u0010+\u001a\u00020\tH\u0002J\b\u0010,\u001a\u00020\tH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/wizarpos/emvsample/models/PfmJournalGenerator;", "", "transactionResult", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "configData", "Lcom/iisysgroup/poslib/host/entities/ConfigData;", "isReceiptPrinted", "", "amount", "", "cardData", "Lcom/iisysgroup/poslib/commons/emv/EmvCard;", "vasProduct", "vasCategory", "customField", "(Lcom/iisysgroup/poslib/host/entities/TransactionResult;Lcom/iisysgroup/poslib/host/entities/ConfigData;ZLjava/lang/String;Lcom/iisysgroup/poslib/commons/emv/EmvCard;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "status", "getStatus", "()Ljava/lang/String;", "setStatus", "(Ljava/lang/String;)V", "generateJournal", "error/NonExistentClass", "()Lerror/NonExistentClass;", "getAcode", "getAmount", "", "getCustomField", "getMcc", "getMid", "getOacode", "getOrrn", "getOstan", "getRep", "getResp", "getRrn", "getTap", "getTimeStamp", "getTransMethod", "getVasCategory", "getVasProduct", "getVm", "getmPan", "getmti", "getstan", "NIBSS_debug"})
public final class PfmJournalGenerator {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String status;
    private final com.iisysgroup.poslib.host.entities.TransactionResult transactionResult = null;
    private final com.iisysgroup.poslib.host.entities.ConfigData configData = null;
    private final boolean isReceiptPrinted = false;
    private final java.lang.String amount = null;
    private final com.iisysgroup.poslib.commons.emv.EmvCard cardData = null;
    private final java.lang.String vasProduct = null;
    private final java.lang.String vasCategory = null;
    private final java.lang.String customField = null;
    
    @org.jetbrains.annotations.NotNull()
    public final error.NonExistentClass generateJournal() {
        return null;
    }
    
    private final java.lang.String getOacode() {
        return null;
    }
    
    private final java.lang.String getOrrn() {
        return null;
    }
    
    private final java.lang.String getOstan() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatus() {
        return null;
    }
    
    public final void setStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    private final java.lang.String getTransMethod() {
        return null;
    }
    
    private final java.lang.String getVasCategory() {
        return null;
    }
    
    private final java.lang.String getVasProduct() {
        return null;
    }
    
    private final java.lang.String getCustomField() {
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
    
    private final long getAmount() {
        return 0L;
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
    
    public PfmJournalGenerator(@org.jetbrains.annotations.Nullable()
    com.iisysgroup.poslib.host.entities.TransactionResult transactionResult, @org.jetbrains.annotations.Nullable()
    com.iisysgroup.poslib.host.entities.ConfigData configData, boolean isReceiptPrinted, @org.jetbrains.annotations.NotNull()
    java.lang.String amount, @org.jetbrains.annotations.Nullable()
    com.iisysgroup.poslib.commons.emv.EmvCard cardData, @org.jetbrains.annotations.NotNull()
    java.lang.String vasProduct, @org.jetbrains.annotations.NotNull()
    java.lang.String vasCategory, @org.jetbrains.annotations.NotNull()
    java.lang.String customField) {
        super();
    }
}