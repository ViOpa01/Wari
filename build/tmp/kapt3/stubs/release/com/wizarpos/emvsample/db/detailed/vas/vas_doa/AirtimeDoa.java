package com.wizarpos.emvsample.db.detailed.vas.vas_doa;

import java.lang.System;

/**
 * * Created by Olije Favour on 12/12/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@android.arch.persistence.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\b\u001a\u00020\tH\'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH\'J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\'\u00a8\u0006\u000f"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/AirtimeDoa;", "", "getAirtimePurchased", "Landroid/arch/lifecycle/LiveData;", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_entity/AirtimeEntity;", "id", "", "getAirtimewithrequestId", "transactionRef", "", "getAllAirtimeData", "", "saveAirtimeData", "", "vasTransactionResult", "wari-wari_online_release"})
public abstract interface AirtimeDoa {
    
    @android.arch.persistence.room.Insert()
    public abstract long saveAirtimeData(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.vas.vas_entity.AirtimeEntity vasTransactionResult);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "Select * From AirtimeEntity ORDER BY card_id DESC")
    public abstract java.util.List<com.wizarpos.emvsample.db.detailed.vas.vas_entity.AirtimeEntity> getAllAirtimeData();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM AirtimeEntity WHERE transactionRef = :id LIMIT 1")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.vas.vas_entity.AirtimeEntity> getAirtimePurchased(int id);
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM  AirtimeEntity  WHERE  AirtimeEntity.transactionRef LIKE :transactionRef ")
    public abstract android.arch.lifecycle.LiveData<com.wizarpos.emvsample.db.detailed.vas.vas_entity.AirtimeEntity> getAirtimewithrequestId(@org.jetbrains.annotations.NotNull()
    java.lang.String transactionRef);
}