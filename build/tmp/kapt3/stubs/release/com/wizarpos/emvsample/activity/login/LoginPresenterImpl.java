package com.wizarpos.emvsample.activity.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J \u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/wizarpos/emvsample/activity/login/LoginPresenterImpl;", "Lcom/wizarpos/emvsample/activity/login/LoginPresenter;", "interactor", "Lcom/wizarpos/emvsample/activity/login/LoginInteractor;", "view", "Lcom/wizarpos/emvsample/activity/login/LoginView;", "(Lcom/wizarpos/emvsample/activity/login/LoginInteractor;Lcom/wizarpos/emvsample/activity/login/LoginView;)V", "handleError", "", "error", "", "login", "userID", "", "password", "onUserInitResponse", "userId", "result", "Lcom/wizarpos/emvsample/activity/login/VasResult;", "wari-wari_online_release"})
public final class LoginPresenterImpl implements com.wizarpos.emvsample.activity.login.LoginPresenter {
    private final com.wizarpos.emvsample.activity.login.LoginInteractor interactor = null;
    private final com.wizarpos.emvsample.activity.login.LoginView view = null;
    
    @java.lang.Override()
    public void login(@org.jetbrains.annotations.NotNull()
    java.lang.String userID, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    private final void onUserInitResponse(java.lang.String userId, java.lang.String password, com.wizarpos.emvsample.activity.login.VasResult result) {
    }
    
    private final void handleError(java.lang.Throwable error) {
    }
    
    public LoginPresenterImpl(@org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.LoginInteractor interactor, @org.jetbrains.annotations.NotNull()
    com.wizarpos.emvsample.activity.login.LoginView view) {
        super();
    }
}