package com.wizarpos.emvsample.db.detailed.vas.vas_entity;

import java.lang.System;

/**
 * * Created by Olije Favour on 12/12/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@android.arch.persistence.room.Entity(indices = {@android.arch.persistence.room.Index(value = {"card_id"})}, foreignKeys = {@android.arch.persistence.room.ForeignKey(entity = com.wizarpos.emvsample.db.detailed.CardTransactionResult.class, childColumns = {"card_id"}, parentColumns = {"id"})})
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\bV\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00a5\u0002\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0007\u0012\u0006\u0010\u0018\u001a\u00020\u0007\u0012\u0006\u0010\u0019\u001a\u00020\u0007\u0012\u0006\u0010\u001a\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0007\u0012\b\b\u0002\u0010 \u001a\u00020\u0007\u0012\b\b\u0002\u0010!\u001a\u00020\u0007\u0012\b\b\u0002\u0010\"\u001a\u00020\u0007\u00a2\u0006\u0002\u0010#J\u0010\u0010K\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010)J\t\u0010L\u001a\u00020\u0007H\u00c6\u0003J\t\u0010M\u001a\u00020\u0007H\u00c6\u0003J\t\u0010N\u001a\u00020\u0007H\u00c6\u0003J\t\u0010O\u001a\u00020\u0007H\u00c6\u0003J\t\u0010P\u001a\u00020\u0007H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0003H\u00c6\u0003J\t\u0010R\u001a\u00020\u0007H\u00c6\u0003J\t\u0010S\u001a\u00020\u0016H\u00c6\u0003J\t\u0010T\u001a\u00020\u0007H\u00c6\u0003J\t\u0010U\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010W\u001a\u00020\u0007H\u00c6\u0003J\t\u0010X\u001a\u00020\u0007H\u00c6\u0003J\t\u0010Y\u001a\u00020\u0007H\u00c6\u0003J\t\u0010Z\u001a\u00020\u0007H\u00c6\u0003J\t\u0010[\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\\\u001a\u00020\u0007H\u00c6\u0003J\t\u0010]\u001a\u00020\u0007H\u00c6\u0003J\t\u0010^\u001a\u00020\u0007H\u00c6\u0003J\t\u0010_\u001a\u00020\u0007H\u00c6\u0003J\t\u0010`\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010d\u001a\u00020\u0007H\u00c6\u0003J\t\u0010e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010f\u001a\u00020\u0007H\u00c6\u0003J\t\u0010g\u001a\u00020\u0007H\u00c6\u0003J\u00b6\u0002\u0010h\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u00072\b\b\u0002\u0010!\u001a\u00020\u00072\b\b\u0002\u0010\"\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010iJ\t\u0010j\u001a\u00020\u0003H\u00d6\u0001J\u0013\u0010k\u001a\u00020\u00162\b\u0010l\u001a\u0004\u0018\u00010mH\u00d6\u0003J\t\u0010n\u001a\u00020\u0003H\u00d6\u0001J\t\u0010o\u001a\u00020\u0007H\u00d6\u0001J\u0019\u0010p\u001a\u00020q2\u0006\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010 \u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u0011\u0010!\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010%R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u0011\u0010\u0014\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010%R\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u00101\u001a\u0004\b/\u0010)\"\u0004\b0\u0010+R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010%R\u0011\u0010\f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010%R\u0011\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010%R\u0011\u0010\r\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010%R\u0011\u0010\u001e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010%R\u0011\u0010\u0019\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010%R\u0011\u0010\u0012\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010%R\u0011\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010%R\u0011\u0010\u0018\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010%R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010%R\u0011\u0010\"\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010%R\u0011\u0010\u001f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010%R\u0011\u0010\u001a\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010%R\u0011\u0010\u0011\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010%R\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010%\"\u0004\bC\u0010DR\u0011\u0010\u001b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u0010%R\u0011\u0010\u001c\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010%R\u0011\u0010\u0010\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bG\u0010%R\u0011\u0010\u0017\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010%R\u0011\u0010\u001d\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bI\u0010%R\u0011\u0010\t\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\bJ\u0010%\u00a8\u0006u"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/vas/vas_entity/DiscoEntity;", "Landroid/os/Parcelable;", "id", "", "cardrowId", "Ljava/lang/Integer;", "stan", "", "amount", "walletId", "marchantAddress", "marchantTid", "marchantName", "merchantId", "product", "transactionStatusMessage", "vasTid", "transactionRef", "paymentmethod", "logo", "dateTime", "error", "", "vasType", "recepiant_name", "meterType", "transactionId", "unit", "unit_value", "vat", "meterNumber", "token", "address", "arras", "tarrif", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getAmount", "getArras", "getCardrowId", "()Ljava/lang/Integer;", "setCardrowId", "(Ljava/lang/Integer;)V", "getDateTime", "getError", "()Z", "getId", "setId", "Ljava/lang/Integer;", "getLogo", "()I", "getMarchantAddress", "getMarchantName", "getMarchantTid", "getMerchantId", "getMeterNumber", "getMeterType", "getPaymentmethod", "getProduct", "getRecepiant_name", "getStan", "getTarrif", "getToken", "getTransactionId", "getTransactionRef", "getTransactionStatusMessage", "setTransactionStatusMessage", "(Ljava/lang/String;)V", "getUnit", "getUnit_value", "getVasTid", "getVasType", "getVat", "getWalletId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/wizarpos/emvsample/db/detailed/vas/vas_entity/DiscoEntity;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wari-wari_online_release"})
public final class DiscoEntity implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.PrimaryKey(autoGenerate = true)
    private java.lang.Integer id;
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.ColumnInfo(name = "card_id")
    private java.lang.Integer cardrowId;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String stan = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String amount = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String walletId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String marchantAddress = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String marchantTid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String marchantName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String merchantId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String product = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String transactionStatusMessage;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vasTid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String transactionRef = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String paymentmethod = null;
    private final int logo = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dateTime = null;
    private final boolean error = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vasType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String recepiant_name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meterType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String transactionId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String unit = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String unit_value = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String vat = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String meterNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String token = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String address = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String arras = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tarrif = null;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getCardrowId() {
        return null;
    }
    
    public final void setCardrowId(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getStan() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWalletId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarchantAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarchantTid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarchantName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMerchantId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProduct() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionStatusMessage() {
        return null;
    }
    
    public final void setTransactionStatusMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVasTid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionRef() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPaymentmethod() {
        return null;
    }
    
    public final int getLogo() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateTime() {
        return null;
    }
    
    public final boolean getError() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVasType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecepiant_name() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeterType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUnit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUnit_value() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVat() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMeterNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArras() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTarrif() {
        return null;
    }
    
    public DiscoEntity(@org.jetbrains.annotations.Nullable()
    @android.support.annotation.NonNull()
    java.lang.Integer id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer cardrowId, @org.jetbrains.annotations.Nullable()
    java.lang.String stan, @org.jetbrains.annotations.NotNull()
    java.lang.String amount, @org.jetbrains.annotations.NotNull()
    java.lang.String walletId, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantTid, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantName, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantId, @org.jetbrains.annotations.NotNull()
    java.lang.String product, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatusMessage, @org.jetbrains.annotations.NotNull()
    java.lang.String vasTid, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionRef, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentmethod, int logo, @org.jetbrains.annotations.NotNull()
    java.lang.String dateTime, boolean error, @org.jetbrains.annotations.NotNull()
    java.lang.String vasType, @org.jetbrains.annotations.NotNull()
    java.lang.String recepiant_name, @org.jetbrains.annotations.NotNull()
    java.lang.String meterType, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionId, @org.jetbrains.annotations.NotNull()
    java.lang.String unit, @org.jetbrains.annotations.NotNull()
    java.lang.String unit_value, @org.jetbrains.annotations.NotNull()
    java.lang.String vat, @org.jetbrains.annotations.NotNull()
    java.lang.String meterNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String arras, @org.jetbrains.annotations.NotNull()
    java.lang.String tarrif) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    public final int component15() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    public final boolean component17() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component25() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component27() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component29() {
        return null;
    }
    
    /**
     * * Created by Olije Favour on 12/12/2019.
     * *Copyright (c) 2019    All rights reserved.
     */
    @org.jetbrains.annotations.NotNull()
    public final com.wizarpos.emvsample.db.detailed.vas.vas_entity.DiscoEntity copy(@org.jetbrains.annotations.Nullable()
    @android.support.annotation.NonNull()
    java.lang.Integer id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer cardrowId, @org.jetbrains.annotations.Nullable()
    java.lang.String stan, @org.jetbrains.annotations.NotNull()
    java.lang.String amount, @org.jetbrains.annotations.NotNull()
    java.lang.String walletId, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantTid, @org.jetbrains.annotations.NotNull()
    java.lang.String marchantName, @org.jetbrains.annotations.NotNull()
    java.lang.String merchantId, @org.jetbrains.annotations.NotNull()
    java.lang.String product, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionStatusMessage, @org.jetbrains.annotations.NotNull()
    java.lang.String vasTid, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionRef, @org.jetbrains.annotations.NotNull()
    java.lang.String paymentmethod, int logo, @org.jetbrains.annotations.NotNull()
    java.lang.String dateTime, boolean error, @org.jetbrains.annotations.NotNull()
    java.lang.String vasType, @org.jetbrains.annotations.NotNull()
    java.lang.String recepiant_name, @org.jetbrains.annotations.NotNull()
    java.lang.String meterType, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionId, @org.jetbrains.annotations.NotNull()
    java.lang.String unit, @org.jetbrains.annotations.NotNull()
    java.lang.String unit_value, @org.jetbrains.annotations.NotNull()
    java.lang.String vat, @org.jetbrains.annotations.NotNull()
    java.lang.String meterNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String arras, @org.jetbrains.annotations.NotNull()
    java.lang.String tarrif) {
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
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
}