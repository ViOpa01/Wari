package com.wizarpos.emvsample.generators;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0002\u0018\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fJ6\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u001a"}, d2 = {"Lcom/wizarpos/emvsample/generators/PfmNotification;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "transaction", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "getTransaction", "()Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "setTransaction", "(Lcom/iisysgroup/poslib/host/entities/TransactionResult;)V", "generatePFM", "Lcom/wizarpos/emvsample/generators/PfmNotification$PFMDATA;", "transactionResult", "context", "Landroid/content/Context;", "getNetworkType", "", "sendNotification", "", "vasPorduct", "vasCategory", "amount", "", "rescode", "mid", "Companion", "PFMDATA", "NIBSS_debug"})
public final class PfmNotification extends android.support.v7.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public com.iisysgroup.poslib.host.entities.TransactionResult transaction;
    private static boolean pfmsent;
    public static final com.wizarpos.emvsample.generators.PfmNotification.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.iisysgroup.poslib.host.entities.TransactionResult getTransaction() {
        return null;
    }
    
    public final void setTransaction(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.TransactionResult p0) {
    }
    
    public final void sendNotification(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.TransactionResult transactionResult, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.generators.PfmNotification.PFMDATA generatePFM(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.TransactionResult transactionResult, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final void sendNotification(@org.jetbrains.annotations.NotNull()
    com.iisysgroup.poslib.host.entities.TransactionResult transactionResult, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String vasPorduct, @org.jetbrains.annotations.NotNull()
    java.lang.String vasCategory) {
    }
    
    public final void sendNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String vasPorduct, @org.jetbrains.annotations.NotNull()
    java.lang.String vasCategory, int amount, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String rescode, @org.jetbrains.annotations.NotNull()
    java.lang.String mid) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getNetworkType(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public PfmNotification() {
        super();
    }
    
    public static final boolean getPfmsent() {
        return false;
    }
    
    public static final void setPfmsent(boolean p0) {
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/wizarpos/emvsample/generators/PfmNotification$PFMDATA;", "", "state", "Lcom/wizarpos/util/PfmState;", "journal", "Lcom/wizarpos/util/PfmJournal;", "(Lcom/wizarpos/util/PfmState;Lcom/wizarpos/util/PfmJournal;)V", "getJournal", "()Lcom/wizarpos/util/PfmJournal;", "getState", "()Lcom/wizarpos/util/PfmState;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "NIBSS_debug"})
    public static final class PFMDATA {
        @org.jetbrains.annotations.NotNull()
        private final com.wizarpos.util.PfmState state = null;
        @org.jetbrains.annotations.NotNull()
        private final com.wizarpos.util.PfmJournal journal = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.util.PfmState getState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.util.PfmJournal getJournal() {
            return null;
        }
        
        public PFMDATA(@org.jetbrains.annotations.NotNull()
        com.wizarpos.util.PfmState state, @org.jetbrains.annotations.NotNull()
        com.wizarpos.util.PfmJournal journal) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.util.PfmState component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.util.PfmJournal component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.emvsample.generators.PfmNotification.PFMDATA copy(@org.jetbrains.annotations.NotNull()
        com.wizarpos.util.PfmState state, @org.jetbrains.annotations.NotNull()
        com.wizarpos.util.PfmJournal journal) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object p0) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/wizarpos/emvsample/generators/PfmNotification$Companion;", "", "()V", "pfmsent", "", "pfmsent$annotations", "getPfmsent", "()Z", "setPfmsent", "(Z)V", "NIBSS_debug"})
    public static final class Companion {
        
        public static void pfmsent$annotations() {
        }
        
        public final boolean getPfmsent() {
            return false;
        }
        
        public final void setPfmsent(boolean p0) {
        }
        
        private Companion() {
            super();
        }
    }
}