package com.wizarpos.emvsample.history_summary;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u00039:;B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010-\u001a\u00020.H\u0002J\u0012\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u000202H\u0002J\u0012\u00103\u001a\u0002002\b\u00104\u001a\u0004\u0018\u000105H\u0014J\u0012\u00106\u001a\u00020.2\b\u00107\u001a\u0004\u0018\u000108H\u0016R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR#\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\r0\r8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR#\u0010\u0011\u001a\n \u0007*\u0004\u0018\u00010\u00120\u00128FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0017X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\u001bR\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006<"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/TransactionHistory;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "historyAdapter", "Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HistoryAdapter;", "initDb", "Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "kotlin.jvm.PlatformType", "getInitDb", "()Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "initDb$delegate", "Lkotlin/Lazy;", "initEodDb", "Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "getInitEodDb", "()Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "initEodDb$delegate", "initVasDb", "Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;", "getInitVasDb", "()Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;", "initVasDb$delegate", "mFragmentHolder", "Landroid/widget/FrameLayout;", "getMFragmentHolder$wari_wari_online_debug", "()Landroid/widget/FrameLayout;", "setMFragmentHolder$wari_wari_online_debug", "(Landroid/widget/FrameLayout;)V", "mPassword", "Landroid/widget/EditText;", "getMPassword$wari_wari_online_debug", "()Landroid/widget/EditText;", "setMPassword$wari_wari_online_debug", "(Landroid/widget/EditText;)V", "mPinHolder", "getMPinHolder$wari_wari_online_debug", "setMPinHolder$wari_wari_online_debug", "mSubmit", "Landroid/widget/Button;", "getMSubmit$wari_wari_online_debug", "()Landroid/widget/Button;", "setMSubmit$wari_wari_online_debug", "(Landroid/widget/Button;)V", "walletHistory", "Lcom/wizarpos/emvsample/history_summary/model/HistoryModel;", "checkPassword", "", "fetchData", "", "historyType", "Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "HISTORY_TYPE", "HistoryAdapter", "HistoryViewHolder", "wari-wari_online_debug"})
public final class TransactionHistory extends android.support.v7.app.AppCompatActivity {
    private com.wizarpos.emvsample.history_summary.model.HistoryModel walletHistory;
    private com.wizarpos.emvsample.history_summary.TransactionHistory.HistoryAdapter historyAdapter;
    private final kotlin.Lazy initDb$delegate = null;
    private final kotlin.Lazy initVasDb$delegate = null;
    private final kotlin.Lazy initEodDb$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private android.widget.EditText mPassword;
    @org.jetbrains.annotations.Nullable()
    private android.widget.FrameLayout mPinHolder;
    @org.jetbrains.annotations.Nullable()
    private android.widget.FrameLayout mFragmentHolder;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button mSubmit;
    private java.util.HashMap _$_findViewCache;
    
    public final com.wizarpos.emvsample.db.detailed.TransactionDataDoa getInitDb() {
        return null;
    }
    
    public final com.wizarpos.emvsample.db.detailed.VasTransactionDoa getInitVasDb() {
        return null;
    }
    
    public final com.wizarpos.emvsample.db.detailed.EodDoa getInitEodDb() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.EditText getMPassword$wari_wari_online_debug() {
        return null;
    }
    
    public final void setMPassword$wari_wari_online_debug(@org.jetbrains.annotations.Nullable()
    android.widget.EditText p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.FrameLayout getMPinHolder$wari_wari_online_debug() {
        return null;
    }
    
    public final void setMPinHolder$wari_wari_online_debug(@org.jetbrains.annotations.Nullable()
    android.widget.FrameLayout p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.FrameLayout getMFragmentHolder$wari_wari_online_debug() {
        return null;
    }
    
    public final void setMFragmentHolder$wari_wari_online_debug(@org.jetbrains.annotations.Nullable()
    android.widget.FrameLayout p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.widget.Button getMSubmit$wari_wari_online_debug() {
        return null;
    }
    
    public final void setMSubmit$wari_wari_online_debug(@org.jetbrains.annotations.Nullable()
    android.widget.Button p0) {
    }
    
    private final boolean checkPassword() {
        return false;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.Nullable()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void fetchData(com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType) {
    }
    
    public TransactionHistory() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "", "(Ljava/lang/String;I)V", "WALLET", "CARD", "ALL", "wari-wari_online_debug"})
    public static enum HISTORY_TYPE {
        /*public static final*/ WALLET /* = new WALLET() */,
        /*public static final*/ CARD /* = new CARD() */,
        /*public static final*/ ALL /* = new ALL() */;
        
        HISTORY_TYPE() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0016\u0010\u0016\u001a\u00020\u000f2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0017J\u0014\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00190\u0017R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001a"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HistoryAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HistoryViewHolder;", "Lcom/wizarpos/emvsample/history_summary/TransactionHistory;", "historyType", "Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "(Lcom/wizarpos/emvsample/history_summary/TransactionHistory;Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;)V", "EodDatas", "Ljava/util/ArrayList;", "Lcom/wizarpos/emvsample/db/detailed/EodData;", "getHistoryType", "()Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setEodDatas", "", "setWalletEodDatas", "Lcom/wizarpos/emvsample/history_summary/model/HistoryModel;", "wari-wari_online_debug"})
    public final class HistoryAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.wizarpos.emvsample.history_summary.TransactionHistory.HistoryViewHolder> {
        private java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> EodDatas;
        @org.jetbrains.annotations.NotNull()
        private final com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.wizarpos.emvsample.history_summary.TransactionHistory.HistoryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.wizarpos.emvsample.history_summary.TransactionHistory.HistoryViewHolder holder, int position) {
        }
        
        public final void setEodDatas(@org.jetbrains.annotations.Nullable()
        java.util.List<com.wizarpos.emvsample.db.detailed.EodData> EodDatas) {
        }
        
        public final void setWalletEodDatas(@org.jetbrains.annotations.NotNull()
        java.util.List<com.wizarpos.emvsample.history_summary.model.HistoryModel> EodDatas) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE getHistoryType() {
            return null;
        }
        
        public HistoryAdapter(@org.jetbrains.annotations.NotNull()
        com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0004H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001e"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HistoryViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "view_that_is_passed", "Landroid/view/View;", "(Lcom/wizarpos/emvsample/history_summary/TransactionHistory;Landroid/view/View;)V", "beneficiary_name", "Landroid/widget/TextView;", "getBeneficiary_name", "()Landroid/widget/TextView;", "setBeneficiary_name", "(Landroid/widget/TextView;)V", "transaction_amount", "getTransaction_amount", "setTransaction_amount", "transaction_id", "getTransaction_id", "setTransaction_id", "transaction_type", "getTransaction_type", "setTransaction_type", "view_details", "Landroid/widget/Button;", "getView_details", "()Landroid/widget/Button;", "setView_details", "(Landroid/widget/Button;)V", "onClick", "", "v", "wari-wari_online_debug"})
    public final class HistoryViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView transaction_type;
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView beneficiary_name;
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView transaction_amount;
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView transaction_id;
        @org.jetbrains.annotations.NotNull()
        private android.widget.Button view_details;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTransaction_type() {
            return null;
        }
        
        public final void setTransaction_type(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBeneficiary_name() {
            return null;
        }
        
        public final void setBeneficiary_name(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTransaction_amount() {
            return null;
        }
        
        public final void setTransaction_amount(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTransaction_id() {
            return null;
        }
        
        public final void setTransaction_id(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.Button getView_details() {
            return null;
        }
        
        public final void setView_details(@org.jetbrains.annotations.NotNull()
        android.widget.Button p0) {
        }
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.NotNull()
        android.view.View v) {
        }
        
        public HistoryViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view_that_is_passed) {
            super(null);
        }
    }
}