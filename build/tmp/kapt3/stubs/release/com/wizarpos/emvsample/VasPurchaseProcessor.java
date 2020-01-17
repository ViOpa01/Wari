package com.wizarpos.emvsample;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R#\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0013"}, d2 = {"Lcom/wizarpos/emvsample/VasPurchaseProcessor;", "Lcom/wizarpos/emvsample/activity/BaseCardPaymentProcessor;", "()V", "mAccountType", "Lcom/iisysgroup/poslib/utils/AccountType;", "mAdditionalAmount", "", "mAmount", "mDb", "Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "kotlin.jvm.PlatformType", "getMDb", "()Lcom/iisysgroup/poslib/host/dao/PosLibDatabase;", "mDb$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "wari-wari_online_release"})
public final class VasPurchaseProcessor extends com.wizarpos.emvsample.activity.BaseCardPaymentProcessor {
    private final long mAmount = 5L;
    private final long mAdditionalAmount = 0L;
    private final com.iisysgroup.poslib.utils.AccountType mAccountType = null;
    private final kotlin.Lazy mDb$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.iisysgroup.poslib.host.dao.PosLibDatabase getMDb() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public VasPurchaseProcessor() {
        super();
    }
}