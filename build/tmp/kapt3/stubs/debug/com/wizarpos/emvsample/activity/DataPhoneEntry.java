package com.wizarpos.emvsample.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002:\u0001eB\u0005\u00a2\u0006\u0002\u0010\u0003J\u001c\u0010S\u001a\u00020T2\n\u0010U\u001a\u00060Vj\u0002`W2\u0006\u0010X\u001a\u00020>H\u0002J\u0014\u0010Y\u001a\u00020T2\n\u0010U\u001a\u00060Vj\u0002`WH\u0002J\b\u0010Z\u001a\u00020TH\u0002J\b\u0010[\u001a\u00020TH\u0002J\u0010\u0010\\\u001a\u00020T2\u0006\u0010]\u001a\u00020^H\u0016J\u0012\u0010_\u001a\u00020T2\b\u0010`\u001a\u0004\u0018\u00010aH\u0014J\b\u0010b\u001a\u00020TH\u0002J\b\u0010c\u001a\u00020TH\u0002J\b\u0010d\u001a\u00020TH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\n\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R\u001a\u0010(\u001a\u00020)X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001b\u00104\u001a\u0002058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b7\u0010\t\u001a\u0004\b4\u00106R\u001b\u00108\u001a\u0002098BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b<\u0010\t\u001a\u0004\b:\u0010;R\u000e\u0010=\u001a\u00020>X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020>X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010@\u001a\u0002098BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bB\u0010\t\u001a\u0004\bA\u0010;R\u001b\u0010C\u001a\u00020>8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bF\u0010\t\u001a\u0004\bD\u0010ER\u001b\u0010G\u001a\u00020>8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bI\u0010\t\u001a\u0004\bH\u0010ER\u001b\u0010J\u001a\u00020>8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bL\u0010\t\u001a\u0004\bK\u0010ER#\u0010M\u001a\n N*\u0004\u0018\u00010>0>8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bP\u0010\t\u001a\u0004\bO\u0010ER\u000e\u0010Q\u001a\u00020RX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006f"}, d2 = {"Lcom/wizarpos/emvsample/activity/DataPhoneEntry;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "()V", "dataItem", "Lcom/wizarpos/emvsample/activity/DataModel$DataResponseElements;", "getDataItem", "()Lcom/wizarpos/emvsample/activity/DataModel$DataResponseElements;", "dataItem$delegate", "Lkotlin/Lazy;", "initAirtimeDb", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/AirtimeDoa;", "getInitAirtimeDb", "()Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/AirtimeDoa;", "setInitAirtimeDb", "(Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/AirtimeDoa;)V", "initCableTvDb", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/CableTvDoa;", "getInitCableTvDb", "()Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/CableTvDoa;", "setInitCableTvDb", "(Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/CableTvDoa;)V", "initCardDb", "Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "getInitCardDb", "()Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "setInitCardDb", "(Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;)V", "initDiscoDb", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/DiscoDoa;", "getInitDiscoDb", "()Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/DiscoDoa;", "setInitDiscoDb", "(Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/DiscoDoa;)V", "initEodDb", "Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "getInitEodDb", "()Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "setInitEodDb", "(Lcom/wizarpos/emvsample/db/detailed/EodDoa;)V", "initTransferDb", "Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/TransferDoa;", "getInitTransferDb", "()Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/TransferDoa;", "setInitTransferDb", "(Lcom/wizarpos/emvsample/db/detailed/vas/vas_doa/TransferDoa;)V", "initVasDb", "Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;", "getInitVasDb", "()Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;", "setInitVasDb", "(Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;)V", "isBeneficiary", "", "()Z", "isBeneficiary$delegate", "mAirtimeProcessDialog", "Landroid/app/ProgressDialog;", "getMAirtimeProcessDialog", "()Landroid/app/ProgressDialog;", "mAirtimeProcessDialog$delegate", "mPayvicePin", "", "mPhoneNumber", "mProgressDialog", "getMProgressDialog", "mProgressDialog$delegate", "mWalletId", "getMWalletId", "()Ljava/lang/String;", "mWalletId$delegate", "mWalletPassword", "getMWalletPassword", "mWalletPassword$delegate", "mWalletUsername", "getMWalletUsername", "mWalletUsername$delegate", "phoneNumber", "kotlin.jvm.PlatformType", "getPhoneNumber", "phoneNumber$delegate", "transactionResult", "Lcom/iisysgroup/poslib/host/entities/TransactionResult;", "fixAppend", "", "displayStr", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "digit", "fixDelete", "gotoDataPage", "initializeAmountEntryElements", "onClick", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onEnterPressed", "payWithCard", "payWithWallet", "KEYS", "wari-wari_online_debug"})
public final class DataPhoneEntry extends android.support.v7.app.AppCompatActivity implements android.view.View.OnClickListener {
    private final kotlin.Lazy isBeneficiary$delegate = null;
    private com.iisysgroup.poslib.host.entities.TransactionResult transactionResult;
    private final kotlin.Lazy phoneNumber$delegate = null;
    private final kotlin.Lazy mProgressDialog$delegate = null;
    private final kotlin.Lazy mAirtimeProcessDialog$delegate = null;
    private java.lang.String mPayvicePin;
    private java.lang.String mPhoneNumber;
    private final kotlin.Lazy mWalletUsername$delegate = null;
    private final kotlin.Lazy mWalletId$delegate = null;
    private final kotlin.Lazy mWalletPassword$delegate = null;
    private final kotlin.Lazy dataItem$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.emvsample.db.detailed.TransactionDataDoa initCardDb;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.emvsample.db.detailed.EodDoa initEodDb;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.emvsample.db.detailed.vas.vas_doa.AirtimeDoa initAirtimeDb;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.emvsample.db.detailed.vas.vas_doa.CableTvDoa initCableTvDb;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.emvsample.db.detailed.vas.vas_doa.DiscoDoa initDiscoDb;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.emvsample.db.detailed.vas.vas_doa.TransferDoa initTransferDb;
    @org.jetbrains.annotations.NotNull()
    public com.wizarpos.emvsample.db.detailed.VasTransactionDoa initVasDb;
    private java.util.HashMap _$_findViewCache;
    
    private final boolean isBeneficiary() {
        return false;
    }
    
    private final java.lang.String getPhoneNumber() {
        return null;
    }
    
    private final android.app.ProgressDialog getMProgressDialog() {
        return null;
    }
    
    private final android.app.ProgressDialog getMAirtimeProcessDialog() {
        return null;
    }
    
    private final java.lang.String getMWalletUsername() {
        return null;
    }
    
    private final java.lang.String getMWalletId() {
        return null;
    }
    
    private final java.lang.String getMWalletPassword() {
        return null;
    }
    
    private final com.wizarpos.emvsample.activity.DataModel.DataResponseElements getDataItem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.TransactionDataDoa getInitCardDb() {
        return null;
    }
    
    public final void setInitCardDb(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.TransactionDataDoa p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.EodDoa getInitEodDb() {
        return null;
    }
    
    public final void setInitEodDb(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.EodDoa p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.vas.vas_doa.AirtimeDoa getInitAirtimeDb() {
        return null;
    }
    
    public final void setInitAirtimeDb(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.vas.vas_doa.AirtimeDoa p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.vas.vas_doa.CableTvDoa getInitCableTvDb() {
        return null;
    }
    
    public final void setInitCableTvDb(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.vas.vas_doa.CableTvDoa p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.vas.vas_doa.DiscoDoa getInitDiscoDb() {
        return null;
    }
    
    public final void setInitDiscoDb(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.vas.vas_doa.DiscoDoa p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.vas.vas_doa.TransferDoa getInitTransferDb() {
        return null;
    }
    
    public final void setInitTransferDb(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.vas.vas_doa.TransferDoa p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.VasTransactionDoa getInitVasDb() {
        return null;
    }
    
    public final void setInitVasDb(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.db.detailed.VasTransactionDoa p0) {
    }
    
    private final void payWithWallet() {
    }
    
    private final void payWithCard() {
    }
    
    @java.lang.Override()
    public void onClick(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    private final void onEnterPressed() {
    }
    
    private final void initializeAmountEntryElements() {
    }
    
    private final void fixAppend(java.lang.StringBuilder displayStr, java.lang.String digit) {
    }
    
    private final void fixDelete(java.lang.StringBuilder displayStr) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void gotoDataPage() {
    }
    
    public DataPhoneEntry() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/wizarpos/emvsample/activity/DataPhoneEntry$KEYS;", "", "()V", "KEY_INTENT_RESULT_CODE", "", "KEY_PHONE_NUMBER", "", "TYPE_OF_DATA_KEY", "DATA_TYPE", "wari-wari_online_debug"})
    public static final class KEYS {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TYPE_OF_DATA_KEY = "type_of_data_key";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String KEY_PHONE_NUMBER = "key_phone_number";
        public static final int KEY_INTENT_RESULT_CODE = 2114;
        public static final com.wizarpos.emvsample.activity.DataPhoneEntry.KEYS INSTANCE = null;
        
        private KEYS() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/wizarpos/emvsample/activity/DataPhoneEntry$KEYS$DATA_TYPE;", "", "(Ljava/lang/String;I)V", "GLO", "ETISALAT", "MTN", "wari-wari_online_debug"})
        public static enum DATA_TYPE {
            /*public static final*/ GLO /* = new GLO() */,
            /*public static final*/ ETISALAT /* = new ETISALAT() */,
            /*public static final*/ MTN /* = new MTN() */;
            
            DATA_TYPE() {
            }
        }
    }
}