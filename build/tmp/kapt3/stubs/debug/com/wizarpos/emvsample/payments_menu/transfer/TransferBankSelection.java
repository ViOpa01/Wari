package com.wizarpos.emvsample.payments_menu.transfer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/transfer/TransferBankSelection;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "mTransactionType", "Lcom/wizarpos/emvsample/payments_menu/transfer/TransferAmountEntry$TRANSACTION_TYPE;", "getMTransactionType", "()Lcom/wizarpos/emvsample/payments_menu/transfer/TransferAmountEntry$TRANSACTION_TYPE;", "mTransactionType$delegate", "Lkotlin/Lazy;", "initializeBankList", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "validateInputs", "", "Companion", "wari-wari_online_debug"})
public final class TransferBankSelection extends android.support.v7.app.AppCompatActivity {
    private final kotlin.Lazy mTransactionType$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BANK_CODE = "bank_code";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACCOUNT_NUMBER = "account_number";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRANSACTION_TYPE = "transaction_type";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BANK_NAME = "bank_name";
    public static final com.wizarpos.emvsample.payments_menu.transfer.TransferBankSelection.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.wizarpos.emvsample.payments_menu.transfer.TransferAmountEntry.TRANSACTION_TYPE getMTransactionType() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeBankList() {
    }
    
    private final boolean validateInputs() {
        return false;
    }
    
    public TransferBankSelection() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/transfer/TransferBankSelection$Companion;", "", "()V", "ACCOUNT_NUMBER", "", "BANK_CODE", "BANK_NAME", "TRANSACTION_TYPE", "wari-wari_online_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}