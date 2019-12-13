package com.wizarpos.emvsample.db.detailed.vas.vas_doa;

import java.lang.System;

/**
 * * Created by Olije Favour on 12/12/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@android.arch.persistence.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00030\u0006H\'J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\t\u001a\u00020\nH\'J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\rH\'J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u000f\u001a\u00020\rH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/TransferDoa;", "", "getTransferData", "", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_entity/TransferEntity;", "getTransferDoaResult", "Landroid/arch/lifecycle/LiveData;", "Lcom/wizarpos/emvsample/db/detailed/vas/model/TransferCardResult;", "getTransferPurchased", "id", "", "getTransferResultwithRRN", "RRN", "", "getTransferwithrequestId", "transactionRef", "saveTransferData", "", "vasTransactionResult", "Wariok_debug"})
public abstract interface TransferDoa {
    
    @android.arch.persistence.room.Insert()
    public abstract long saveTransferData(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.vas.vas_entity.TransferEntity vasTransactionResult);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "Select * From TransferEntity ORDER BY dateTime DESC")
    public abstract java.util.List<com.wizarpos.emvsample.db.detailed.vas.vas_entity.TransferEntity> getTransferData();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM TransferEntity WHERE card_id LIKE :id LIMIT 1")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.vas.vas_entity.TransferEntity> getTransferPurchased(int id);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "Select CardTransactionResult.*, TransferEntity.* from  TransferEntity LEFT JOIN CardTransactionResult on   TransferEntity.card_id LIKE CardTransactionResult.id ")
    public abstract android.arch.lifecycle.LiveData<java.util.List<com.wizarpos.emvsample.db.detailed.vas.model.TransferCardResult>> getTransferDoaResult();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM  TransferEntity  WHERE  TransferEntity.transactionRef LIKE :transactionRef ")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.vas.vas_entity.TransferEntity> getTransferwithrequestId(@org.jetbrains.annotations.NotNull()
    java.lang.String transactionRef);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM  TransferEntity  INNER JOIN CardTransactionResult ON CardTransactionResult.id = TransferEntity.card_id Where CardTransactionResult.RRN = :RRN ")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.vas.model.TransferCardResult> getTransferResultwithRRN(@org.jetbrains.annotations.NotNull()
    java.lang.String RRN);
}