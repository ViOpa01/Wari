package com.wizarpos.emvsample.activity.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0003H&\u00a8\u0006\u0010"}, d2 = {"Lcom/wizarpos/emvsample/activity/login/LoginView;", "", "dismissProgress", "", "setDeviceChangedError", "setInvalidPassword", "setInvalidUser", "setLoginError", "throwable", "", "setLoginSuccessful", "setShouldUpdatePin", "showMessage", "message", "", "showProgress", "NIBSS_debug"})
public abstract interface LoginView {
    
    public abstract void showProgress();
    
    public abstract void dismissProgress();
    
    public abstract void setInvalidUser();
    
    public abstract void setInvalidPassword();
    
    public abstract void showMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message);
    
    public abstract void setLoginError(@org.jetbrains.annotations.NotNull()
    java.lang.Throwable throwable);
    
    public abstract void setLoginSuccessful();
    
    public abstract void setDeviceChangedError();
    
    public abstract void setShouldUpdatePin();
}