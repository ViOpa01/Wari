package com.wizarpos.emvsample.activity.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\u0012\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0016J\b\u0010 \u001a\u00020\u0019H\u0016J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u0019H\u0016J\b\u0010%\u001a\u00020\u0019H\u0016J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\'\u001a\u00020\u0016H\u0016J\b\u0010(\u001a\u00020\u0019H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/wizarpos/emvsample/activity/login/LoginActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Lcom/wizarpos/emvsample/activity/login/LoginView;", "()V", "loginProgressDialog", "Landroid/app/ProgressDialog;", "getLoginProgressDialog", "()Landroid/app/ProgressDialog;", "loginProgressDialog$delegate", "Lkotlin/Lazy;", "presenter", "Lcom/wizarpos/emvsample/activity/login/LoginPresenter;", "getPresenter", "()Lcom/wizarpos/emvsample/activity/login/LoginPresenter;", "presenter$delegate", "sign_in_button", "Landroid/widget/Button;", "getSign_in_button", "()Landroid/widget/Button;", "setSign_in_button", "(Landroid/widget/Button;)V", "wallet_password", "", "wallet_username", "dismissProgress", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setDeviceChangedError", "setInvalidPassword", "setInvalidUser", "setLoginError", "throwable", "", "setLoginSuccessful", "setShouldUpdatePin", "showMessage", "message", "showProgress", "Wariok_debug"})
public final class LoginActivity extends android.support.v7.app.AppCompatActivity implements com.wizarpos.emvsample.activity.login.LoginView {
    private final kotlin.Lazy loginProgressDialog$delegate = null;
    private final kotlin.Lazy presenter$delegate = null;
    private java.lang.String wallet_username;
    private java.lang.String wallet_password;
    @org.jetbrains.annotations.NotNull()
    public android.widget.Button sign_in_button;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public void showProgress() {
    }
    
    @java.lang.Override()
    public void dismissProgress() {
    }
    
    @java.lang.Override()
    public void setInvalidUser() {
    }
    
    @java.lang.Override()
    public void setInvalidPassword() {
    }
    
    @java.lang.Override()
    public void showMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @java.lang.Override()
    public void setLoginError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable) {
    }
    
    @java.lang.Override()
    public void setLoginSuccessful() {
    }
    
    @java.lang.Override()
    public void setDeviceChangedError() {
    }
    
    @java.lang.Override()
    public void setShouldUpdatePin() {
    }
    
    private final android.app.ProgressDialog getLoginProgressDialog() {
        return null;
    }
    
    private final com.wizarpos.emvsample.activity.login.LoginPresenter getPresenter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.Button getSign_in_button() {
        return null;
    }
    
    public final void setSign_in_button(@org.jetbrains.annotations.NotNull()
    android.widget.Button p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    public LoginActivity() {
        super();
    }
}