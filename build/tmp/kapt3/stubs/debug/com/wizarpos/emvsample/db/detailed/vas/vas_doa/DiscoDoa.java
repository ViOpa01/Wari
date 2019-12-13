package com.wizarpos.emvsample.db.detailed.vas.vas_doa;

import java.lang.System;

/**
 * * Created by Olije Favour on 12/12/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@android.arch.persistence.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\b0\u0003H\'J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\u0006\u0010\f\u001a\u00020\rH\'J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000f\u001a\u00020\rH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0004H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/DiscoDoa;", "", "getAirtimePurchased", "Landroid/arch/lifecycle/LiveData;", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_entity/DiscoEntity;", "id", "", "getDiscoData", "", "getDiscoResult", "getDiscoResultwithRRN", "Lcom/wizarpos/emvsample/db/detailed/vas/model/DiscoCardResult;", "RRN", "", "getDiscowithrequestId", "transactionId", "savDiscoData", "", "vasTransactionResult", "Wariok_debug"})
public abstract interface DiscoDoa {
    
    @android.arch.persistence.room.Insert()
    public abstract long savDiscoData(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity vasTransactionResult);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "Select * From DiscoEntity ORDER BY dateTime DESC")
    public abstract java.util.List<com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity> getDiscoData();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM DiscoEntity WHERE card_id LIKE :id LIMIT 1")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity> getAirtimePurchased(int id);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "Select CardTransactionResult.*, DiscoEntity.* from  DiscoEntity LEFT JOIN CardTransactionResult on   DiscoEntity.card_id LIKE CardTransactionResult.id ")
    public abstract android.arch.lifecycle.LiveData<java.util.List<com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity>> getDiscoResult();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM  DiscoEntity  WHERE  DiscoEntity.transactionId LIKE :transactionId ")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity> getDiscowithrequestId(@org.jetbrains.annotations.NotNull()
    java.lang.String transactionId);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM  DiscoEntity  INNER JOIN CardTransactionResult ON DiscoEntity.id = DiscoEntity.card_id Where CardTransactionResult.RRN = :RRN ")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.vas.model.DiscoCardResult> getDiscoResultwithRRN(@org.jetbrains.annotations.NotNull()
    java.lang.String RRN);
}