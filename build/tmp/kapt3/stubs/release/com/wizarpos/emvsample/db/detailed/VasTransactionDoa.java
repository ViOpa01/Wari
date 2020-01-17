package com.wizarpos.emvsample.db.detailed;

import java.lang.System;

/**
 * * Created by Olije Favour on 11/8/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@android.arch.persistence.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\b\u001a\u00020\u0006H\'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\'J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\r\u001a\u00020\u000eH\'J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\n0\u0003H\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\'\u00a8\u0006\u0013"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;", "", "getAirtimeResultwithRRN", "Landroid/arch/lifecycle/LiveData;", "Lcom/wizarpos/emvsample/db/detailed/VasCardResult;", "RRN", "", "getAirtimeResultwithrequestId", "transactionRef", "getAllVasTransactions", "", "Lcom/wizarpos/emvsample/db/detailed/VasTransactionResult;", "getVas", "id", "", "getVasResult", "saveVasTransData", "", "vasTransactionResult", "wari-wari_online_release"})
public abstract interface VasTransactionDoa {
    
    @android.arch.persistence.room.Insert()
    public abstract long saveVasTransData(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.VasTransactionResult vasTransactionResult);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "Select * From VasTransactionResult ORDER BY dateTime DESC")
    public abstract java.util.List<com.wizarpos.emvsample.db.detailed.VasTransactionResult> getAllVasTransactions();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM VasTransactionResult WHERE card_id LIKE :id LIMIT 1")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.VasTransactionResult> getVas(int id);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "Select CardTransactionResult.*, VasTransactionResult.* from  VasTransactionResult LEFT JOIN CardTransactionResult on   VasTransactionResult.card_id LIKE CardTransactionResult.id ")
    public abstract android.arch.lifecycle.LiveData<java.util.List<com.wizarpos.emvsample.db.detailed.VasCardResult>> getVasResult();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM  VasTransactionResult  INNER JOIN AirtimeEntity on VasTransactionResult.transactionRef = AirtimeEntity.transactionRef where VasTransactionResult.vasType = :transactionRef   ")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.VasCardResult> getAirtimeResultwithrequestId(@org.jetbrains.annotations.NotNull()
    java.lang.String transactionRef);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM  VasTransactionResult  INNER JOIN CardTransactionResult ON CardTransactionResult.id = VasTransactionResult.card_id  INNER JOIN AirtimeEntity on VasTransactionResult.transactionRef = AirtimeEntity.transactionRef Where CardTransactionResult.RRN = :RRN ")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.VasCardResult> getAirtimeResultwithRRN(@org.jetbrains.annotations.NotNull()
    java.lang.String RRN);
}