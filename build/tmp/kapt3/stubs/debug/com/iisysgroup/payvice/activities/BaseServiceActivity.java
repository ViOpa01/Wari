package com.iisysgroup.payvice.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H$J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0018H$J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH$J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001cH$J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0014J\u0012\u0010 \u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\u0010\u0010#\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\nH$J\b\u0010$\u001a\u00020\u001eH\u0004J\u001a\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\'2\b\b\u0002\u0010(\u001a\u00020\u0004H\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006)"}, d2 = {"Lcom/iisysgroup/payvice/activities/BaseServiceActivity;", "Lcom/wizarpos/emvsample/services/helper/activity/BaseActivity;", "()V", "encryptedUserPin", "", "getEncryptedUserPin", "()Ljava/lang/String;", "setEncryptedUserPin", "(Ljava/lang/String;)V", "product", "Lcom/wizarpos/util/Service$Product;", "getProduct", "()Lcom/wizarpos/util/Service$Product;", "setProduct", "(Lcom/wizarpos/util/Service$Product;)V", "service", "Lcom/wizarpos/util/Service;", "getService", "()Lcom/wizarpos/util/Service;", "setService", "(Lcom/wizarpos/util/Service;)V", "getHistoryLayout", "Landroid/view/View;", "getHistoryListView", "Landroid/support/v7/widget/RecyclerView;", "getServiceImageView", "Landroid/widget/ImageView;", "getSubTitleView", "Landroid/widget/TextView;", "hideHistory", "", "initControls", "onPostCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSelectProduct", "selectProduct", "showProgressDialog", "show", "", "message", "wari-wari_online_debug"})
public abstract class BaseServiceActivity extends com.wizarpos.emvsample.services.helper.activity.BaseActivity {
    @org.jetbrains.annotations.NotNull()
    protected com.wizarpos.util.Service service;
    @org.jetbrains.annotations.Nullable()
    private com.wizarpos.util.Service.Product product;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String encryptedUserPin;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    protected final com.wizarpos.util.Service getService() {
        return null;
    }
    
    protected final void setService(@org.jetbrains.annotations.NotNull()
    com.wizarpos.util.Service p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    protected final com.wizarpos.util.Service.Product getProduct() {
        return null;
    }
    
    protected final void setProduct(@org.jetbrains.annotations.Nullable()
    com.wizarpos.util.Service.Product p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final java.lang.String getEncryptedUserPin() {
        return null;
    }
    
    protected final void setEncryptedUserPin(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    protected void onPostCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    protected void initControls() {
    }
    
    protected final void selectProduct() {
    }
    
    protected final void showProgressDialog(boolean show, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    private final void hideHistory() {
    }
    
    @org.jetbrains.annotations.Nullable()
    protected abstract android.view.View getHistoryLayout();
    
    @org.jetbrains.annotations.Nullable()
    protected abstract android.support.v7.widget.RecyclerView getHistoryListView();
    
    @org.jetbrains.annotations.Nullable()
    protected abstract android.widget.TextView getSubTitleView();
    
    @org.jetbrains.annotations.Nullable()
    protected abstract android.widget.ImageView getServiceImageView();
    
    protected abstract void onSelectProduct(@org.jetbrains.annotations.NotNull()
    com.wizarpos.util.Service.Product product);
    
    public BaseServiceActivity() {
        super();
    }
}