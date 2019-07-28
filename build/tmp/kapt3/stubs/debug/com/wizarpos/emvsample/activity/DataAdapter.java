package com.wizarpos.emvsample.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0015\u0016B#\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u001c\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/wizarpos/emvsample/activity/DataAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/wizarpos/emvsample/activity/DataAdapter$DataViewHolder;", "dataItemsArrayList", "Ljava/util/ArrayList;", "Lcom/wizarpos/emvsample/activity/DataModel$DataResponseElements;", "context", "Landroid/content/Context;", "listener", "Lcom/wizarpos/emvsample/activity/DataAdapter$DataClickListener;", "(Ljava/util/ArrayList;Landroid/content/Context;Lcom/wizarpos/emvsample/activity/DataAdapter$DataClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DataClickListener", "DataViewHolder", "NIBSS_debug"})
public final class DataAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.wizarpos.emvsample.activity.DataAdapter.DataViewHolder> {
    private java.util.ArrayList<com.wizarpos.emvsample.activity.DataModel.DataResponseElements> dataItemsArrayList;
    private android.content.Context context;
    private com.wizarpos.emvsample.activity.DataAdapter.DataClickListener listener;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.wizarpos.emvsample.activity.DataAdapter.DataViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.DataAdapter.DataViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    public DataAdapter(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.wizarpos.emvsample.activity.DataModel.DataResponseElements> dataItemsArrayList, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.DataAdapter.DataClickListener listener) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/wizarpos/emvsample/activity/DataAdapter$DataClickListener;", "", "onDataItemClick", "", "data", "Ljava/util/ArrayList;", "Lcom/wizarpos/emvsample/activity/DataModel$DataResponseElements;", "position", "", "NIBSS_debug"})
    public static abstract interface DataClickListener {
        
        public abstract void onDataItemClick(@org.jetbrains.annotations.NotNull()
        java.util.ArrayList<com.wizarpos.emvsample.activity.DataModel.DataResponseElements> data, int position);
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b\u00a8\u0006\u0012"}, d2 = {"Lcom/wizarpos/emvsample/activity/DataAdapter$DataViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "itemView", "Landroid/view/View;", "(Lcom/wizarpos/emvsample/activity/DataAdapter;Landroid/view/View;)V", "dataTitle", "Landroid/widget/TextView;", "getDataTitle", "()Landroid/widget/TextView;", "setDataTitle", "(Landroid/widget/TextView;)V", "validity", "getValidity", "setValidity", "onClick", "", "v", "NIBSS_debug"})
    public final class DataViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView dataTitle;
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView validity;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getDataTitle() {
            return null;
        }
        
        public final void setDataTitle(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getValidity() {
            return null;
        }
        
        public final void setValidity(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.NotNull()
        android.view.View v) {
        }
        
        public DataViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}