package com.wizarpos.emvsample.db.detailed;

import java.lang.System;

/**
 * * Created by Olije Favour on 11/22/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@android.arch.persistence.room.Database(entities = {com.wizarpos.emvsample.db.detailed.EodData.class, com.wizarpos.emvsample.db.detailed.VasTransactionResult.class, com.wizarpos.emvsample.db.detailed.CardTransactionResult.class, com.wizarpos.emvsample.db.detailed.vas.vas_entity.AirtimeEntity.class, com.wizarpos.emvsample.db.detailed.vas.vas_entity.CableTvEntity.class, com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity.class, com.wizarpos.emvsample.db.detailed.vas.vas_entity.TransferEntity.class, com.wizarpos.emvsample.db.detailed.vas.vas_entity.WithdrawalEntity.class}, version = 1)
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&\u00a8\u0006\u0012"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/TransDataBase;", "Landroid/arch/persistence/room/RoomDatabase;", "()V", "getAirtimeTable", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/AirtimeDoa;", "getCableTable", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/CableTvDoa;", "getDiscoTable", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/DiscoDoa;", "getEodDataBase", "Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "getTransactionDataDoa", "Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "getTransferTable", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/TransferDoa;", "getVasDataBase", "Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;", "Companion", "wari-wari_online_release"})
public abstract class TransDataBase extends android.arch.persistence.room.RoomDatabase {
    @org.jetbrains.annotations.Nullable()
    private static volatile com.wizarpos.emvsample.db.detailed.TransDataBase INSTANCE;
    public static final com.wizarpos.emvsample.db.detailed.TransDataBase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wizarpos.emvsample.db.detailed.TransactionDataDoa getTransactionDataDoa();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wizarpos.emvsample.db.detailed.EodDoa getEodDataBase();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wizarpos.emvsample.db.detailed.VasTransactionDoa getVasDataBase();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wizarpos.emvsample.db.detailed.vas.vas_doa.DiscoDoa getDiscoTable();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wizarpos.emvsample.db.detailed.vas.vas_doa.AirtimeDoa getAirtimeTable();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wizarpos.emvsample.db.detailed.vas.vas_doa.CableTvDoa getCableTable();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.wizarpos.emvsample.db.detailed.vas.vas_doa.TransferDoa getTransferTable();
    
    public TransDataBase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/TransDataBase$Companion;", "", "()V", "INSTANCE", "Lcom/wizarpos/emvsample/db/detailed/TransDataBase;", "getINSTANCE", "()Lcom/wizarpos/emvsample/db/detailed/TransDataBase;", "setINSTANCE", "(Lcom/wizarpos/emvsample/db/detailed/TransDataBase;)V", "getInstance", "context", "Landroid/content/Context;", "wari-wari_online_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.Nullable()
        public final com.wizarpos.emvsample.db.detailed.TransDataBase getINSTANCE() {
            return null;
        }
        
        public final void setINSTANCE(@org.jetbrains.annotations.Nullable()
        com.wizarpos.emvsample.db.detailed.TransDataBase p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.emvsample.db.detailed.TransDataBase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}