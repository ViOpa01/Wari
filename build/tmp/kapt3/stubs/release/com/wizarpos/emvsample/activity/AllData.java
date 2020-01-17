package com.wizarpos.emvsample.activity;

import java.lang.System;

@kotlin.Suppress(names = {"IMPLICIT_CAST_TO_ANY"})
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001$B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u001c\u0010\u0015\u001a\u00020\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00172\u0006\u0010\u0018\u001a\u00020\u0002J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u001e\u0010\u001c\u001a\u00020\u00142\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u00172\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006%"}, d2 = {"Lcom/wizarpos/emvsample/activity/AllData;", "Landroid/support/v7/app/AppCompatActivity;", "Lcom/wizarpos/emvsample/activity/DataAdapter$DataClickListener;", "()V", "amount", "", "dataItem", "Lcom/wizarpos/emvsample/activity/DataModel$DataResponseElements;", "mProgressDialog", "Landroid/app/ProgressDialog;", "getMProgressDialog", "()Landroid/app/ProgressDialog;", "mProgressDialog$delegate", "Lkotlin/Lazy;", "serviceType", "", "getServiceType", "()Ljava/lang/String;", "serviceType$delegate", "gotoDataAndAirtime", "", "initializeRecyclerView", "items", "Ljava/util/ArrayList;", "listener", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDataItemClick", "data", "position", "", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "KEYS", "wari-wari_online_release"})
public final class AllData extends android.support.v7.app.AppCompatActivity implements com.wizarpos.emvsample.activity.DataAdapter.DataClickListener {
    private com.wizarpos.emvsample.activity.DataModel.DataResponseElements dataItem;
    private long amount;
    private final kotlin.Lazy serviceType$delegate = null;
    private final kotlin.Lazy mProgressDialog$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.Nullable()
    android.view.MenuItem item) {
        return false;
    }
    
    private final java.lang.String getServiceType() {
        return null;
    }
    
    @java.lang.Override()
    public void onDataItemClick(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.wizarpos.emvsample.activity.DataModel.DataResponseElements> data, int position) {
    }
    
    public final void initializeRecyclerView(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.wizarpos.emvsample.activity.DataModel.DataResponseElements> items, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.DataAdapter.DataClickListener listener) {
    }
    
    private final android.app.ProgressDialog getMProgressDialog() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void gotoDataAndAirtime() {
    }
    
    public AllData() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/wizarpos/emvsample/activity/AllData$KEYS;", "", "()V", "DATA_VALUE", "", "PHONE_NUMBER", "TYPE_OF_DATA_KEY", "DATA_TYPE", "wari-wari_online_release"})
    public static final class KEYS {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String DATA_VALUE = "data_value";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PHONE_NUMBER = "phone_number";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TYPE_OF_DATA_KEY = "type_of_data_key";
        public static final com.wizarpos.emvsample.activity.AllData.KEYS INSTANCE = null;
        
        private KEYS() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wizarpos/emvsample/activity/AllData$KEYS$DATA_TYPE;", "", "(Ljava/lang/String;I)V", "GLO", "ETISALAT", "MTN", "AIRTEL", "wari-wari_online_release"})
        public static enum DATA_TYPE {
            /*public static final*/ GLO /* = new GLO() */,
            /*public static final*/ ETISALAT /* = new ETISALAT() */,
            /*public static final*/ MTN /* = new MTN() */,
            /*public static final*/ AIRTEL /* = new AIRTEL() */;
            
            DATA_TYPE() {
            }
        }
    }
}