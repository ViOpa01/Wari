package com.wizarpos.emvsample.services.cable_tv.multichoice.activities;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\'\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00122\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u000fH\u0002\u00a2\u0006\u0002\u0010!J\n\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\n\u0010$\u001a\u0004\u0018\u00010%H\u0014J\n\u0010&\u001a\u0004\u0018\u00010\'H\u0014J\n\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020\u001cH\u0014J\b\u0010+\u001a\u00020\u000bH\u0002J\u0012\u0010,\u001a\u00020\u001c2\b\u0010-\u001a\u0004\u0018\u00010.H\u0014J\u0010\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u000201H\u0014J\u0014\u00102\u001a\u00020\u001c*\u0002032\b\u00104\u001a\u0004\u0018\u000105R#\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0010R#\u0010\u0011\u001a\n \u0005*\u0004\u0018\u00010\u00120\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\t\u001a\u0004\b\u0018\u0010\u0019\u00a8\u00066"}, d2 = {"Lcom/wizarpos/emvsample/services/cable_tv/multichoice/activities/MultichoiceActivity;", "Lcom/iisysgroup/payvice/activities/BaseServiceActivity;", "()V", "alertDialog", "Landroid/support/v7/app/AlertDialog;", "kotlin.jvm.PlatformType", "getAlertDialog", "()Landroid/support/v7/app/AlertDialog;", "alertDialog$delegate", "Lkotlin/Lazy;", "isValidated", "", "planDialog", "Lcom/iisysgroup/payvice/dialogs/MultichoicePlanDialog;", "selectedPlan", "error/NonExistentClass", "Lerror/NonExistentClass;", "terminalId", "", "getTerminalId", "()Ljava/lang/String;", "terminalId$delegate", "viewModel", "Lcom/iisysgroup/payvice/baseimpl/viewmodel/MultichoiceViewModel;", "getViewModel", "()Lcom/iisysgroup/payvice/baseimpl/viewmodel/MultichoiceViewModel;", "viewModel$delegate", "continuePayment", "", "paymentOption", "card", "Lcom/cloudpos/card/Card;", "lookupResponse", "(Ljava/lang/String;Lcom/cloudpos/card/Card;Lerror/NonExistentClass;)V", "getHistoryLayout", "Landroid/view/View;", "getHistoryListView", "Landroid/support/v7/widget/RecyclerView;", "getServiceImageView", "Landroid/widget/ImageView;", "getSubTitleView", "Landroid/widget/TextView;", "initControls", "isValidInput", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSelectProduct", "product", "Lcom/wizarpos/util/Service$Product;", "showError", "Landroid/app/Activity;", "throwable", "", "NIBSS_debug"})
public final class MultichoiceActivity extends com.iisysgroup.payvice.activities.BaseServiceActivity {
    private com.iisysgroup.payvice.dialogs.MultichoicePlanDialog planDialog;
    private error.NonExistentClass selectedPlan;
    private boolean isValidated;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy terminalId$delegate = null;
    private final kotlin.Lazy alertDialog$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.iisysgroup.payvice.baseimpl.viewmodel.MultichoiceViewModel getViewModel() {
        return null;
    }
    
    private final java.lang.String getTerminalId() {
        return null;
    }
    
    private final android.support.v7.app.AlertDialog getAlertDialog() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void initControls() {
    }
    
    private final void continuePayment(java.lang.String paymentOption, com.cloudpos.card.Card card, error.NonExistentClass lookupResponse) {
    }
    
    private final boolean isValidInput() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected android.view.View getHistoryLayout() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected android.support.v7.widget.RecyclerView getHistoryListView() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected android.widget.TextView getSubTitleView() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    protected android.widget.ImageView getServiceImageView() {
        return null;
    }
    
    @java.lang.Override()
    protected void onSelectProduct(@org.jetbrains.annotations.NotNull()
    com.wizarpos.util.Service.Product product) {
    }
    
    public final void showError(@org.jetbrains.annotations.NotNull()
    android.app.Activity $receiver, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable throwable) {
    }
    
    public MultichoiceActivity() {
        super();
    }
}