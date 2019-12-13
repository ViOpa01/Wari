package com.wizarpos.emvsample.payments_menu.Services;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bJ6\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\bH\'J\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\'J\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f2\b\b\u0001\u0010\u000e\u001a\u00020\u0012H\'J6\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\f2\b\b\u0001\u0010\u0005\u001a\u00020\u00152\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\bH\'J6\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\f2\b\b\u0001\u0010\u0018\u001a\u00020\u00192\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\bH\'J6\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\b\b\u0001\u0010\u0018\u001a\u00020\u00192\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\n\u001a\u00020\bH&\u00a8\u0006\u001c"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/Services/TransferService;", "", "deposit", "Lkotlinx/coroutines/Deferred;", "Lcom/wizarpos/emvsample/payments_menu/models/TransactionResponse;", "transferModel", "Lcom/wizarpos/emvsample/payments_menu/models/TransactionDetails;", "contentType", "", "signature", "nonce", "lookUpAccountNumberTransfer", "Lretrofit2/Call;", "Lcom/wizarpos/emvsample/payments_menu/models/LookupSuccessModel;", "lookUpRequestDetails", "Lcom/wizarpos/emvsample/payments_menu/models/AccountLookUpDetailTransfer;", "lookUpAccountNumberWithdrawal", "Lcom/wizarpos/emvsample/models/WithdrawalLookupSuccessModel;", "Lcom/wizarpos/emvsample/payments_menu/models/AccountLookUpDetailWithdrawal;", "transfer", "Lcom/wizarpos/emvsample/models/transfer/TransferSuccessModel;", "Lcom/wizarpos/emvsample/payments_menu/models/TransferDetails;", "withdraw", "Lcom/wizarpos/emvsample/models/WithdrawalWalletResponse/WithdrawalWalletCreditModel;", "withdrawalModel", "Lcom/wizarpos/emvsample/payments_menu/models/WithdrawalDetails;", "withdraws", "Factory", "Wariok_debug"})
public abstract interface TransferService {
    public static final com.wizarpos.emvsample.payments_menu.Services.TransferService.Factory Factory = null;
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/vas/vice-banking/transfer/lookup")
    public abstract retrofit2.Call<com.wizarpos.emvsample.payments_menu.models.LookupSuccessModel> lookUpAccountNumberTransfer(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wizarpos.emvsample.payments_menu.models.AccountLookUpDetailTransfer lookUpRequestDetails);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/vas/vice-banking/withdrawal/lookup")
    public abstract retrofit2.Call<com.wizarpos.emvsample.models.WithdrawalLookupSuccessModel> lookUpAccountNumberWithdrawal(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wizarpos.emvsample.payments_menu.models.AccountLookUpDetailWithdrawal lookUpRequestDetails);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/vas/vice-banking/transfer/payment")
    public abstract retrofit2.Call<com.wizarpos.emvsample.models.transfer.TransferSuccessModel> transfer(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wizarpos.emvsample.payments_menu.models.TransferDetails transferModel, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Content-Type")
    java.lang.String contentType, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "ITEX-Signature")
    java.lang.String signature, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "ITEX-Nonce")
    java.lang.String nonce);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/vas/vice-banking/withdrawal/payment")
    public abstract retrofit2.Call<com.wizarpos.emvsample.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel> withdraw(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wizarpos.emvsample.payments_menu.models.WithdrawalDetails withdrawalModel, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Content-Type")
    java.lang.String contentType, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "ITEX-Signature")
    java.lang.String signature, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "ITEX-Nonce")
    java.lang.String nonce);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/tams/tams/transfer-engine.php")
    public abstract kotlinx.coroutines.Deferred<com.wizarpos.emvsample.payments_menu.models.TransactionResponse> deposit(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wizarpos.emvsample.payments_menu.models.TransactionDetails transferModel, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Content-Type")
    java.lang.String contentType, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "ITEX-Signature")
    java.lang.String signature, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "ITEX-Nonce")
    java.lang.String nonce);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.Deferred<com.wizarpos.emvsample.models.WithdrawalWalletResponse.WithdrawalWalletCreditModel> withdraws(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wizarpos.emvsample.payments_menu.models.WithdrawalDetails withdrawalModel, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Content-Type")
    java.lang.String contentType, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "ITEX-Signature")
    java.lang.String signature, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "ITEX-Nonce")
    java.lang.String nonce);
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/wizarpos/emvsample/payments_menu/Services/TransferService$Factory;", "", "()V", "BASE_URL", "", "getBASE_URL", "()Ljava/lang/String;", "retrofit", "Lretrofit2/Retrofit;", "create", "Lcom/wizarpos/emvsample/payments_menu/Services/TransferService;", "Wariok_debug"})
    public static final class Factory {
        @org.jetbrains.annotations.NotNull()
        private static final java.lang.String BASE_URL = "http://basehuge.itexapp.com:8090";
        private static retrofit2.Retrofit retrofit;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBASE_URL() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.emvsample.payments_menu.Services.TransferService create() {
            return null;
        }
        
        private Factory() {
            super();
        }
    }
}