package com.wizarpos.emvsample.history_summary.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\b"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/service/HistoryService;", "", "getWalletHistory", "Lkotlinx/coroutines/Deferred;", "Lcom/wizarpos/emvsample/history_summary/model/HistoryModel;", "walletId", "", "Companion", "wari-wari_online_release"})
public abstract interface HistoryService {
    public static final com.wizarpos.emvsample.history_summary.service.HistoryService.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "vas/history/{walletId}")
    public abstract kotlinx.coroutines.Deferred<com.wizarpos.emvsample.history_summary.model.HistoryModel> getWalletHistory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "walletId")
    java.lang.String walletId);
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/wizarpos/emvsample/history_summary/service/HistoryService$Companion;", "", "()V", "base_url", "", "getInstance", "Lcom/wizarpos/emvsample/history_summary/service/HistoryService;", "wari-wari_online_release"})
    public static final class Companion {
        private static final java.lang.String base_url = "https://197.253.19.77/api/";
        
        @org.jetbrains.annotations.NotNull()
        public final com.wizarpos.emvsample.history_summary.service.HistoryService getInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}