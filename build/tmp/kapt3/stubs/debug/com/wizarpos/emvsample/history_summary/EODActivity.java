package com.wizarpos.emvsample.history_summary;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00e2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\b\u00b2\u0001\u00b3\u0001\u00b4\u0001\u00b5\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\u0095\u0001\u001a\u00020\u00042\u0010\u0010\u0095\u0001\u001a\u000b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0096\u0001J\u0019\u0010\u0097\u0001\u001a\u00020\u00042\u0010\u0010\u0098\u0001\u001a\u000b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0096\u0001J\u0007\u0010\u0099\u0001\u001a\u00020\u0004J\n\u0010\u009a\u0001\u001a\u00030\u0081\u0001H\u0002J\u0019\u0010\u0098\u0001\u001a\u00020\u00042\u0010\u0010\u0098\u0001\u001a\u000b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0096\u0001J&\u0010\u009b\u0001\u001a\u00030\u009c\u00012\b\u0010\u009d\u0001\u001a\u00030\u009e\u00012\u0007\u0010\u009f\u0001\u001a\u0002022\u0007\u0010\u00a0\u0001\u001a\u000202H\u0002J&\u0010\u00a1\u0001\u001a\u00030\u009c\u00012\b\u0010\u00a2\u0001\u001a\u00030\u009e\u00012\u0007\u0010\u009f\u0001\u001a\u0002022\u0007\u0010\u00a0\u0001\u001a\u000202H\u0002J\u0011\u0010\u00a3\u0001\u001a\u00020,2\b\u0010\u00a4\u0001\u001a\u00030\u00a5\u0001J\u0016\u0010\u00a6\u0001\u001a\u00030\u009c\u00012\n\u0010\u00a7\u0001\u001a\u0005\u0018\u00010\u00a8\u0001H\u0014J\u0016\u0010\u00a9\u0001\u001a\u00030\u0081\u00012\n\u0010\u00aa\u0001\u001a\u0005\u0018\u00010\u00ab\u0001H\u0016J\u0016\u0010\u00ac\u0001\u001a\u00030\u0081\u00012\n\u0010\u00ad\u0001\u001a\u0005\u0018\u00010\u00ae\u0001H\u0016J\u0016\u0010\u00af\u0001\u001a\u00030\u0081\u00012\n\u0010\u00aa\u0001\u001a\u0005\u0018\u00010\u00ab\u0001H\u0016J\u0010\u0010\u00b0\u0001\u001a\u00030\u009c\u00012\u0006\u0010\u0016\u001a\u00020\u0004J&\u0010\u00b1\u0001\u001a\u00030\u009c\u00012\b\u0010\u009d\u0001\u001a\u00030\u009e\u00012\u0007\u0010\u009f\u0001\u001a\u0002022\u0007\u0010\u00a0\u0001\u001a\u000202H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR\u001a\u0010\u0019\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R\u001a\u0010%\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001f\"\u0004\b\'\u0010!R\u001a\u0010(\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u000202X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001a\u00107\u001a\u000202X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u00104\"\u0004\b9\u00106R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010;X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020>X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010?\u001a\u00060@R\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010A\u001a\u00060@R\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010B\u001a\u0004\u0018\u00010,X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010.\"\u0004\bD\u00100R\u001a\u0010E\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0006\"\u0004\bG\u0010\bR#\u0010H\u001a\n J*\u0004\u0018\u00010I0I8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bK\u0010LR#\u0010O\u001a\n J*\u0004\u0018\u00010P0P8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bS\u0010N\u001a\u0004\bQ\u0010RR#\u0010T\u001a\n J*\u0004\u0018\u00010U0U8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bX\u0010N\u001a\u0004\bV\u0010WR\u001a\u0010Y\u001a\u00020\u0004X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\u0006\"\u0004\b[\u0010\bR\u001c\u0010\\\u001a\u0004\u0018\u00010]X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u001c\u0010b\u001a\u0004\u0018\u00010cX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u001c\u0010h\u001a\u0004\u0018\u00010]X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bi\u0010_\"\u0004\bj\u0010aR\u001c\u0010k\u001a\u0004\u0018\u00010lX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bm\u0010n\"\u0004\bo\u0010pR\u000e\u0010q\u001a\u00020rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010s\u001a\u0004\u0018\u00010tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010u\u001a\u0004\u0018\u00010vX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010w\u001a\u00020\u001dX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bx\u0010\u001f\"\u0004\by\u0010!R\u001c\u0010z\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b{\u0010\u0006\"\u0004\b|\u0010\bR\u001c\u0010}\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b~\u0010\u0006\"\u0004\b\u007f\u0010\bR \u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0086\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001d\u0010\u0086\u0001\u001a\u000202X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0001\u00104\"\u0005\b\u0088\u0001\u00106R\u001d\u0010\u0089\u0001\u001a\u000202X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u00104\"\u0005\b\u008b\u0001\u00106R\u001d\u0010\u008c\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u0010\u0006\"\u0005\b\u008e\u0001\u0010\bR\u0010\u0010\u008f\u0001\u001a\u00030\u0090\u0001X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0093\u0001\u001a\u00030\u0094\u0001X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u00b6\u0001"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/EODActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "RRN", "", "getRRN", "()Ljava/lang/String;", "setRRN", "(Ljava/lang/String;)V", "_merchantName", "get_merchantName", "set_merchantName", "allApproved", "Ljava/util/ArrayList;", "Lcom/wizarpos/emvsample/db/detailed/EodData;", "getAllApproved", "()Ljava/util/ArrayList;", "setAllApproved", "(Ljava/util/ArrayList;)V", "allDeclined", "getAllDeclined", "setAllDeclined", "allTransactions", "getAllTransactions", "setAllTransactions", "amount", "getAmount", "setAmount", "approvedCount", "", "getApprovedCount", "()I", "setApprovedCount", "(I)V", "approvedsumCount", "getApprovedsumCount", "setApprovedsumCount", "declinedCount", "getDeclinedCount", "setDeclinedCount", "declinedsumCount", "getDeclinedsumCount", "setDeclinedsumCount", "dummy", "Landroid/graphics/Bitmap;", "getDummy", "()Landroid/graphics/Bitmap;", "setDummy", "(Landroid/graphics/Bitmap;)V", "end_Date", "", "getEnd_Date", "()J", "setEnd_Date", "(J)V", "ends", "getEnds", "setEnds", "format", "Lcom/cloudpos/printer/Format;", "format2", "handler", "Landroid/os/Handler;", "historyAdapter", "Lcom/wizarpos/emvsample/history_summary/EODActivity$HistoryAdapter;", "historyAdapter2", "icon", "getIcon", "setIcon", "image_url", "getImage_url", "setImage_url", "initDb", "Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "kotlin.jvm.PlatformType", "getInitDb", "()Lcom/wizarpos/emvsample/db/detailed/TransactionDataDoa;", "initDb$delegate", "Lkotlin/Lazy;", "initEodDb", "Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "getInitEodDb", "()Lcom/wizarpos/emvsample/db/detailed/EodDoa;", "initEodDb$delegate", "initVasDb", "Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;", "getInitVasDb", "()Lcom/wizarpos/emvsample/db/detailed/VasTransactionDoa;", "initVasDb$delegate", "logo", "getLogo$wari_wari_online_debug", "setLogo$wari_wari_online_debug", "mFragmentHolder", "Landroid/widget/FrameLayout;", "getMFragmentHolder$wari_wari_online_debug", "()Landroid/widget/FrameLayout;", "setMFragmentHolder$wari_wari_online_debug", "(Landroid/widget/FrameLayout;)V", "mPassword", "Landroid/widget/EditText;", "getMPassword$wari_wari_online_debug", "()Landroid/widget/EditText;", "setMPassword$wari_wari_online_debug", "(Landroid/widget/EditText;)V", "mPinHolder", "getMPinHolder$wari_wari_online_debug", "setMPinHolder$wari_wari_online_debug", "mSubmit", "Landroid/widget/Button;", "getMSubmit$wari_wari_online_debug", "()Landroid/widget/Button;", "setMSubmit$wari_wari_online_debug", "(Landroid/widget/Button;)V", "myRunnable", "Ljava/lang/Runnable;", "picassoImage", "Lcom/squareup/picasso/RequestCreator;", "printerDevice", "Lcom/cloudpos/printer/PrinterDevice;", "resourceId", "getResourceId$wari_wari_online_debug", "setResourceId$wari_wari_online_debug", "sDate", "getSDate", "setSDate", "sTerminalId", "getSTerminalId", "setSTerminalId", "shouldPrinterShow", "", "getShouldPrinterShow", "()Z", "setShouldPrinterShow", "(Z)V", "start_Date", "getStart_Date", "setStart_Date", "starts", "getStarts", "setStarts", "time", "getTime", "setTime", "toolbar", "Landroid/support/v7/widget/Toolbar;", "viewEOD", "Landroid/widget/ScrollView;", "walletHistory", "Lcom/wizarpos/emvsample/history_summary/model/HistoryModel;", "approvedTransactions", "", "arrangeData", "declinedTransactions", "baseHeader", "checkPassword", "fetchApprovedData", "", "historyType", "Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "startDate", "endDate", "fetchDeclinedData", "historyType2", "getBitmapFromView", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "printString", "triggerTransactions", "HistoryAdapter", "HistoryAdapter2", "HistoryViewHolder", "HistoryViewHolder2", "wari-wari_online_debug"})
public final class EODActivity extends android.support.v7.app.AppCompatActivity {
    private com.wizarpos.emvsample.history_summary.model.HistoryModel walletHistory;
    private com.wizarpos.emvsample.history_summary.EODActivity.HistoryAdapter historyAdapter;
    private com.wizarpos.emvsample.history_summary.EODActivity.HistoryAdapter historyAdapter2;
    private android.support.v7.widget.Toolbar toolbar;
    private android.widget.ScrollView viewEOD;
    private int approvedCount;
    private int declinedCount;
    private int approvedsumCount;
    private int declinedsumCount;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String image_url;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> allDeclined;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> allApproved;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String sDate;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String sTerminalId;
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Bitmap dummy;
    @org.jetbrains.annotations.Nullable()
    private android.graphics.Bitmap icon;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String allTransactions;
    private long starts;
    private long ends;
    private long start_Date;
    private long end_Date;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String _merchantName;
    private boolean shouldPrinterShow;
    @org.jetbrains.annotations.Nullable()
    private android.widget.EditText mPassword;
    @org.jetbrains.annotations.Nullable()
    private android.widget.FrameLayout mPinHolder;
    @org.jetbrains.annotations.Nullable()
    private android.widget.FrameLayout mFragmentHolder;
    @org.jetbrains.annotations.Nullable()
    private android.widget.Button mSubmit;
    private final kotlin.Lazy initDb$delegate = null;
    private final kotlin.Lazy initVasDb$delegate = null;
    private final kotlin.Lazy initEodDb$delegate = null;
    private final android.os.Handler handler = null;
    private final java.lang.Runnable myRunnable = null;
    private com.cloudpos.printer.Format format;
    private com.cloudpos.printer.Format format2;
    private com.cloudpos.printer.PrinterDevice printerDevice;
    private int resourceId;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String logo;
    private com.squareup.picasso.RequestCreator picassoImage;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String time;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String RRN;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String amount;
    private java.util.HashMap _$_findViewCache;
    
    public final int getApprovedCount() {
        return 0;
    }
    
    public final void setApprovedCount(int p0) {
    }
    
    public final int getDeclinedCount() {
        return 0;
    }
    
    public final void setDeclinedCount(int p0) {
    }
    
    public final int getApprovedsumCount() {
        return 0;
    }
    
    public final void setApprovedsumCount(int p0) {
    }
    
    public final int getDeclinedsumCount() {
        return 0;
    }
    
    public final void setDeclinedsumCount(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImage_url() {
        return null;
    }
    
    public final void setImage_url(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> getAllDeclined() {
        return null;
    }
    
    public final void setAllDeclined(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> getAllApproved() {
        return null;
    }
    
    public final void setAllApproved(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSDate() {
        return null;
    }
    
    public final void setSDate(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSTerminalId() {
        return null;
    }
    
    public final void setSTerminalId(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap getDummy() {
        return null;
    }
    
    public final void setDummy(@org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap getIcon() {
        return null;
    }
    
    public final void setIcon(@org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAllTransactions() {
        return null;
    }
    
    public final void setAllTransactions(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getStarts() {
        return 0L;
    }
    
    public final void setStarts(long p0) {
    }
    
    public final long getEnds() {
        return 0L;
    }
    
    public final void setEnds(long p0) {
    }
    
    public final long getStart_Date() {
        return 0L;
    }
    
    public final void setStart_Date(long p0) {
    }
    
    public final long getEnd_Date() {
        return 0L;
    }
    
    public final void setEnd_Date(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String get_merchantName() {
        return null;
    }
    
    public final void set_merchantName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final boolean getShouldPrinterShow() {
        return false;
    }
    
    public final void setShouldPrinterShow(boolean p0) {
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
    
    public final com.wizarpos.emvsample.db.detailed.TransactionDataDoa getInitDb() {
        return null;
    }
    
    public final com.wizarpos.emvsample.db.detailed.VasTransactionDoa getInitVasDb() {
        return null;
    }
    
    public final com.wizarpos.emvsample.db.detailed.EodDoa getInitEodDb() {
        return null;
    }
    
    @java.lang.Override()
    public boolean onPrepareOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final boolean checkPassword() {
        return false;
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.Nullable()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.Nullable()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void triggerTransactions(com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType, long startDate, long endDate) {
    }
    
    public final int getResourceId$wari_wari_online_debug() {
        return 0;
    }
    
    public final void setResourceId$wari_wari_online_debug(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLogo$wari_wari_online_debug() {
        return null;
    }
    
    public final void setLogo$wari_wari_online_debug(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final void printString(@org.jetbrains.annotations.NotNull()
    java.lang.String allTransactions) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTime() {
        return null;
    }
    
    public final void setTime(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRRN() {
        return null;
    }
    
    public final void setRRN(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAmount() {
        return null;
    }
    
    public final void setAmount(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String baseHeader() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String approvedTransactions(@org.jetbrains.annotations.Nullable()
    java.util.List<com.wizarpos.emvsample.db.detailed.EodData> approvedTransactions) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String declinedTransactions(@org.jetbrains.annotations.Nullable()
    java.util.List<com.wizarpos.emvsample.db.detailed.EodData> declinedTransactions) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String arrangeData(@org.jetbrains.annotations.Nullable()
    java.util.List<com.wizarpos.emvsample.db.detailed.EodData> declinedTransactions) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap getBitmapFromView(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
        return null;
    }
    
    private final void fetchApprovedData(com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType, long startDate, long endDate) {
    }
    
    private final void fetchDeclinedData(com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType2, long startDate, long endDate) {
    }
    
    public EODActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0016\u0010\u0016\u001a\u00020\u000f2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0017J\u0014\u0010\u0018\u001a\u00020\u000f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00190\u0017R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/EODActivity$HistoryAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/wizarpos/emvsample/history_summary/EODActivity$HistoryViewHolder;", "Lcom/wizarpos/emvsample/history_summary/EODActivity;", "historyType", "Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "(Lcom/wizarpos/emvsample/history_summary/EODActivity;Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;)V", "getHistoryType", "()Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "transactionResults", "Ljava/util/ArrayList;", "Lcom/wizarpos/emvsample/db/detailed/EodData;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setTransactionResults", "", "setWalletTransactionResults", "Lcom/wizarpos/emvsample/history_summary/model/HistoryModel;", "wari-wari_online_debug"})
    public final class HistoryAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.wizarpos.emvsample.history_summary.EODActivity.HistoryViewHolder> {
        private java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> transactionResults;
        @org.jetbrains.annotations.NotNull()
        private final com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.wizarpos.emvsample.history_summary.EODActivity.HistoryViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.wizarpos.emvsample.history_summary.EODActivity.HistoryViewHolder holder, int position) {
        }
        
        public final void setTransactionResults(@org.jetbrains.annotations.Nullable()
        java.util.List<com.wizarpos.emvsample.db.detailed.EodData> transactionResults) {
        }
        
        public final void setWalletTransactionResults(@org.jetbrains.annotations.NotNull()
        java.util.List<com.wizarpos.emvsample.history_summary.model.HistoryModel> transactionResults) {
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
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0004H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0015\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/EODActivity$HistoryViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "view_that_is_passed", "Landroid/view/View;", "(Lcom/wizarpos/emvsample/history_summary/EODActivity;Landroid/view/View;)V", "beneficiary_RRN", "Landroid/widget/TextView;", "getBeneficiary_RRN", "()Landroid/widget/TextView;", "setBeneficiary_RRN", "(Landroid/widget/TextView;)V", "eodrel", "Landroid/widget/RelativeLayout;", "getEodrel", "()Landroid/widget/RelativeLayout;", "setEodrel", "(Landroid/widget/RelativeLayout;)V", "transaction_amount", "getTransaction_amount", "setTransaction_amount", "transaction_time", "getTransaction_time", "setTransaction_time", "onClick", "", "v", "wari-wari_online_debug"})
    public final class HistoryViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView transaction_time;
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView beneficiary_RRN;
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView transaction_amount;
        @org.jetbrains.annotations.NotNull()
        private android.widget.RelativeLayout eodrel;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTransaction_time() {
            return null;
        }
        
        public final void setTransaction_time(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBeneficiary_RRN() {
            return null;
        }
        
        public final void setBeneficiary_RRN(@org.jetbrains.annotations.NotNull()
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
        public final android.widget.RelativeLayout getEodrel() {
            return null;
        }
        
        public final void setEodrel(@org.jetbrains.annotations.NotNull()
        android.widget.RelativeLayout p0) {
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
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0016\u0010\u0016\u001a\u00020\u000f2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0017J\u0014\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0017R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/EODActivity$HistoryAdapter2;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/wizarpos/emvsample/history_summary/EODActivity$HistoryViewHolder2;", "Lcom/wizarpos/emvsample/history_summary/EODActivity;", "historyType2", "Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "(Lcom/wizarpos/emvsample/history_summary/EODActivity;Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;)V", "getHistoryType2", "()Lcom/wizarpos/emvsample/history_summary/TransactionHistory$HISTORY_TYPE;", "transactionResults2", "Ljava/util/ArrayList;", "Lcom/wizarpos/emvsample/db/detailed/EodData;", "getItemCount", "", "onBindViewHolder", "", "holder2", "position2", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setTransactionResults2", "", "setWalletTransactionResults", "transactionResults", "Lcom/wizarpos/emvsample/history_summary/model/HistoryModel;", "wari-wari_online_debug"})
    public final class HistoryAdapter2 extends android.support.v7.widget.RecyclerView.Adapter<com.wizarpos.emvsample.history_summary.EODActivity.HistoryViewHolder2> {
        private java.util.ArrayList<com.wizarpos.emvsample.db.detailed.EodData> transactionResults2;
        @org.jetbrains.annotations.NotNull()
        private final com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType2 = null;
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public com.wizarpos.emvsample.history_summary.EODActivity.HistoryViewHolder2 onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.wizarpos.emvsample.history_summary.EODActivity.HistoryViewHolder2 holder2, int position2) {
        }
        
        public final void setTransactionResults2(@org.jetbrains.annotations.Nullable()
        java.util.List<com.wizarpos.emvsample.db.detailed.EodData> transactionResults2) {
        }
        
        public final void setWalletTransactionResults(@org.jetbrains.annotations.NotNull()
        java.util.List<com.wizarpos.emvsample.history_summary.model.HistoryModel> transactionResults) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE getHistoryType2() {
            return null;
        }
        
        public HistoryAdapter2(@org.jetbrains.annotations.NotNull()
        com.wizarpos.emvsample.history_summary.TransactionHistory.HISTORY_TYPE historyType2) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0004H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001a\u0010\u0015\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\t\"\u0004\b\u0017\u0010\u000b\u00a8\u0006\u001b"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/EODActivity$HistoryViewHolder2;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "view_that_is_passed", "Landroid/view/View;", "(Lcom/wizarpos/emvsample/history_summary/EODActivity;Landroid/view/View;)V", "beneficiary_RRN2", "Landroid/widget/TextView;", "getBeneficiary_RRN2", "()Landroid/widget/TextView;", "setBeneficiary_RRN2", "(Landroid/widget/TextView;)V", "eodrel2", "Landroid/widget/RelativeLayout;", "getEodrel2", "()Landroid/widget/RelativeLayout;", "setEodrel2", "(Landroid/widget/RelativeLayout;)V", "transaction_amount2", "getTransaction_amount2", "setTransaction_amount2", "transaction_time2", "getTransaction_time2", "setTransaction_time2", "onClick", "", "v", "wari-wari_online_debug"})
    public final class HistoryViewHolder2 extends android.support.v7.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView transaction_time2;
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView beneficiary_RRN2;
        @org.jetbrains.annotations.NotNull()
        private android.widget.TextView transaction_amount2;
        @org.jetbrains.annotations.NotNull()
        private android.widget.RelativeLayout eodrel2;
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTransaction_time2() {
            return null;
        }
        
        public final void setTransaction_time2(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBeneficiary_RRN2() {
            return null;
        }
        
        public final void setBeneficiary_RRN2(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTransaction_amount2() {
            return null;
        }
        
        public final void setTransaction_amount2(@org.jetbrains.annotations.NotNull()
        android.widget.TextView p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.RelativeLayout getEodrel2() {
            return null;
        }
        
        public final void setEodrel2(@org.jetbrains.annotations.NotNull()
        android.widget.RelativeLayout p0) {
        }
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.NotNull()
        android.view.View v) {
        }
        
        public HistoryViewHolder2(@org.jetbrains.annotations.NotNull()
        android.view.View view_that_is_passed) {
            super(null);
        }
    }
}