package com.wizarpos.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J \u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001d"}, d2 = {"Lcom/wizarpos/util/MyService;", "Landroid/app/Service;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "backgroundThread", "Ljava/lang/Thread;", "isRunning", "", "request", "Lokhttp3/Request;", "getRequest", "()Lokhttp3/Request;", "setRequest", "(Lokhttp3/Request;)V", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "", "onStart", "startId", "", "onStartCommand", "flags", "retry", "CallTask", "Wariok_debug"})
public final class MyService extends android.app.Service {
    private final java.lang.String TAG = null;
    @org.jetbrains.annotations.NotNull()
    public okhttp3.Request request;
    private boolean isRunning;
    private java.lang.Thread backgroundThread;
    
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.Request getRequest() {
        return null;
    }
    
    public final void setRequest(@org.jetbrains.annotations.NotNull()
    okhttp3.Request p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @android.support.annotation.Nullable()
    @java.lang.Override()
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onStart(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent, int startId) {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    public final void retry(@org.jetbrains.annotations.NotNull()
    okhttp3.Request request) {
    }
    
    public MyService() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000e"}, d2 = {"Lcom/wizarpos/util/MyService$CallTask;", "Ljava/util/TimerTask;", "request", "Lokhttp3/Request;", "service", "Landroid/app/Service;", "(Lokhttp3/Request;Landroid/app/Service;)V", "getRequest", "()Lokhttp3/Request;", "getService", "()Landroid/app/Service;", "retry", "", "run", "Wariok_debug"})
    public static final class CallTask extends java.util.TimerTask {
        @org.jetbrains.annotations.NotNull()
        private final okhttp3.Request request = null;
        @org.jetbrains.annotations.NotNull()
        private final android.app.Service service = null;
        
        @java.lang.Override()
        public void run() {
        }
        
        public final void retry() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final okhttp3.Request getRequest() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.app.Service getService() {
            return null;
        }
        
        public CallTask(@org.jetbrains.annotations.NotNull()
        okhttp3.Request request, @org.jetbrains.annotations.NotNull()
        android.app.Service service) {
            super();
        }
    }
}