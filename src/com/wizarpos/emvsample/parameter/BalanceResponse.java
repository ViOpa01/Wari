package com.wizarpos.emvsample.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceResponse {
    @SerializedName("account balance")
    @Expose
    private String accountBalance;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("account type")
    @Expose
    private String accountType;
    @SerializedName("currency code")
    @Expose
    private String currencyCode;
    @SerializedName("amount sign")
    @Expose
    private String amountSign;
    @SerializedName("amount type")
    @Expose
    private String amountType;

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAmountSign() {
        return amountSign;
    }

    public void setAmountSign(String amountSign) {
        this.amountSign = amountSign;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

}
