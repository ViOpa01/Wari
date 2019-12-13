package com.wizarpos.emvsample.db.detailed.vas.vas_entity;

import java.lang.System;

/**
 * * Created by Olije Favour on 12/12/2019.
 * *Copyright (c) 2019    All rights reserved.
 */
@android.arch.persistence.room.Entity(indices = {@android.arch.persistence.room.Index(value = {"card_id"})}, foreignKeys = {@android.arch.persistence.room.ForeignKey(entity = com.wizarpos.emvsample.db.detailed.CardTransactionResult.class, childColumns = {"card_id"}, parentColumns = {"id"})})
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B-\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012\u00a8\u0006\u001a"}, d2 = {"Lcom/wizarpos/emvsample/db/detailed/vas/vas_entity/AirtimeEntity;", "Landroid/os/Parcelable;", "id", "", "cardrowId", "Ljava/lang/Integer;", "transactionRef", "", "recepiant_phone", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getCardrowId", "()Ljava/lang/Integer;", "setCardrowId", "(Ljava/lang/Integer;)V", "getId", "setId", "Ljava/lang/Integer;", "getRecepiant_phone", "()Ljava/lang/String;", "getTransactionRef", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Wariok_debug"})
public final class AirtimeEntity implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.PrimaryKey(autoGenerate = true)
    private java.lang.Integer id;
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.ColumnInfo(name = "card_id")
    private java.lang.Integer cardrowId;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String transactionRef = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String recepiant_phone = null;
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTransactionRef() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRecepiant_phone() {
        return null;
    }
    
    public AirtimeEntity(@org.jetbrains.annotations.Nullable()
    @android.support.annotation.NonNull()
    java.lang.Integer id, @org.jetbrains.annotations.Nullable()
    java.lang.Integer cardrowId, @org.jetbrains.annotations.NotNull()
    java.lang.String transactionRef, @org.jetbrains.annotations.NotNull()
    java.lang.String recepiant_phone) {
        super();
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