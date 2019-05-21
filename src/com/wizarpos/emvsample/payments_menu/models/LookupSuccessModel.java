package com.wizarpos.emvsample.payments_menu.models;

import com.iisysgroup.androidlite.models.withdrawal.description;

public class LookupSuccessModel {

    private int status;
    private int convenienceFee;
    private int amountSettled;
    private int amountCharged;
    private String message;
    private String beneficiaryName;
    private String account;
    private String vendorBankCode;
    private description description;
    private String productCode;
    private Boolean error;

    public LookupSuccessModel(int status, int convenienceFee, int amountSettled, int amountCharged, String message, String beneficiaryName, String account, String vendorBankCode, com.iisysgroup.androidlite.models.withdrawal.description description, String productCode) {
        this.status = status;
        this.convenienceFee = convenienceFee;
        this.amountSettled = amountSettled;
        this.amountCharged = amountCharged;
        this.message = message;
        this.beneficiaryName = beneficiaryName;
        this.account = account;
        this.vendorBankCode = vendorBankCode;
        this.description = description;
        this.productCode = productCode;
    }

    public LookupSuccessModel(int status, Boolean error , String message, String description){
        this.status = status;
        this.error = error;
        this.message = message;
        this.description = null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(int convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public int getAmountSettled() {
        return amountSettled;
    }

    public void setAmountSettled(int amountSettled) {
        this.amountSettled = amountSettled;
    }

    public int getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(int amountCharged) {
        this.amountCharged = amountCharged;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getVendorBankCode() {
        return vendorBankCode;
    }

    public void setVendorBankCode(String vendorBankCode) {
        this.vendorBankCode = vendorBankCode;
    }

    public com.iisysgroup.androidlite.models.withdrawal.description getDescription() {
        return description;
    }

    public void setDescription(com.iisysgroup.androidlite.models.withdrawal.description description) {
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
