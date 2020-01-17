package com.wizarpos.emvsample.db.detailed;

import java.lang.System;

/**
 * * Created by Olije Favour on 11/5/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@android.arch.persistence.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J,\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\'J,\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\'J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\'J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\'J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\'J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0013\u001a\u00020\u000bH\'J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0005H\'\u00a8\u0006\u0016"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "", "findAll", "Landroid/arch/lifecycle/LiveData;", "", "Lcom/wizarpos/emvsample/db/detailed/CardTransactionResult;", "findApprovedTransactionsInDateRange", "minDate", "", "maxDate", "responsecode", "", "findDeclinedTransactionsInDateRange", "getAllTransactions", "getImmediately", "id", "", "getTransactionByID", "getTransactionByRRN", "rrn", "saveTransData", "cardTransactionResult", "wari-wari_online_release"})
public abstract interface TransactionDataDoa {
    
    @android.arch.persistence.room.Insert()
    public abstract long saveTransData(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.CardTransactionResult cardTransactionResult);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "Select * From CardTransactionResult ORDER BY LongDateTime DESC")
    public abstract java.util.List<com.wizarpos.emvsample.db.detailed.CardTransactionResult> getAllTransactions();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM CardTransactionResult WHERE id LIKE :id LIMIT 1")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.CardTransactionResult> getTransactionByID(int id);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM CardTransactionResult WHERE RRN LIKE :rrn LIMIT 1")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.CardTransactionResult> getTransactionByRRN(@org.jetbrains.annotations.NotNull()
    java.lang.String rrn);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM CardTransactionResult WHERE id LIKE :id LIMIT 1")
    public abstract com.wizarpos.emvsample.db.detailed.CardTransactionResult getImmediately(int id);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM CardTransactionResult ORDER BY LongDateTime DESC ")
    public abstract android.arch.lifecycle.LiveData<java.util.List<com.wizarpos.emvsample.db.detailed.CardTransactionResult>> findAll();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM CardTransactionResult WHERE responseCode !=:responsecode AND LongDateTime >= :minDate AND LongDateTime <= :maxDate ORDER BY LongDateTime DESC ")
    public abstract android.arch.lifecycle.LiveData<java.util.List<com.wizarpos.emvsample.db.detailed.CardTransactionResult>> findDeclinedTransactionsInDateRange(long minDate, long maxDate, @org.jetbrains.annotations.NotNull()
    java.lang.String responsecode);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM CardTransactionResult WHERE responseCode =:responsecode AND LongDateTime >= :minDate AND LongDateTime <= :maxDate ORDER BY LongDateTime DESC ")
    public abstract android.arch.lifecycle.LiveData<java.util.List<com.wizarpos.emvsample.db.detailed.CardTransactionResult>> findApprovedTransactionsInDateRange(long minDate, long maxDate, @org.jetbrains.annotations.NotNull()
    java.lang.String responsecode);
}