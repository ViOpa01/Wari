package com.wizarpos.util;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PfmJournal implements Serializable
{

    @SerializedName("stan")
    @Expose
    private String stan;
    @SerializedName("mPan")
    @Expose
    private String mPan;
    @SerializedName("cardName")
    @Expose
    private String cardName;
    @SerializedName("cardExpiry")
    @Expose
    private String cardExpiry;
    @SerializedName("rrn")
    @Expose
    private String rrn;
    @SerializedName("acode")
    @Expose
    private String acode;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("mti")
    @Expose
    private String mti;
    @SerializedName("ps")
    @Expose
    private String ps;
    @SerializedName("resp")
    @Expose
    private String resp;
    @SerializedName("tap")
    @Expose
    private String tap;
    @SerializedName("rep")
    @Expose
    private String rep;
    @SerializedName("vm")
    @Expose
    private String vm;
    @SerializedName("ostan")
    @Expose
    private String ostan;
    @SerializedName("orrn")
    @Expose
    private String orrn;
    @SerializedName("oacode")
    @Expose
    private String oacode;
    @SerializedName("mid")
    @Expose
    private String mid;
    @SerializedName("vasCategory")
    @Expose
    private String vasCategory;
    @SerializedName("vasProduct")
    @Expose
    private String vasProduct;
    @SerializedName("mcc")
    @Expose
    private String mcc;
    @SerializedName("transMethod")
    @Expose
    private String transMethod;
    @SerializedName("merchantName")
    @Expose
    private String merchantName;
    @SerializedName("customField")
    @Ignore
    private String customField;
    private final static long serialVersionUID = 4977020815875770656L;

    /**
     * No args constructor for use in serialization
     *
     */
    public PfmJournal() {
    }

    /**
     *
     * @param vm
     * @param vasProduct
     * @param cardExpiry
     * @param rrn
     * @param customField
     * @param mcc
     * @param orrn
     * @param acode
     * @param mti
     * @param mPan
     * @param oacode
     * @param amount
     * @param timestamp
     * @param transMethod
     * @param vasCategory
     * @param cardName
     * @param stan
     * @param ostan
     * @param merchantName
     * @param mid
     * @param tap
     * @param rep
     * @param ps
     * @param resp
     */
    public PfmJournal(String stan, String mPan, String cardName, String cardExpiry, String rrn, String acode, Integer amount, String timestamp, String mti, String ps, String resp, String tap, String rep, String vm, String ostan, String orrn, String oacode, String mid, String vasCategory, String vasProduct, String mcc, String transMethod, String merchantName, String customField) {
        super();
        this.stan = stan;
        this.mPan = mPan;
        this.cardName = cardName;
        this.cardExpiry = cardExpiry;
        this.rrn = rrn;
        this.acode = acode;
        this.amount = amount;
        this.timestamp = timestamp;
        this.mti = mti;
        this.ps = ps;
        this.resp = resp;
        this.tap = tap;
        this.rep = rep;
        this.vm = vm;
        this.ostan = ostan;
        this.orrn = orrn;
        this.oacode = oacode;
        this.mid = mid;
        this.vasCategory = vasCategory;
        this.vasProduct = vasProduct;
        this.mcc = mcc;
        this.transMethod = transMethod;
        this.merchantName = merchantName;
        this.customField = customField;
    }

    public String getstan() {
        return stan;
    }

    public void setstan(String stan) {
        this.stan = stan;
    }

    public String getMPan() {
        return mPan;
    }

    public void setMPan(String mPan) {
        this.mPan = mPan;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {
        this.mti = mti;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getTap() {
        return tap;
    }

    public void setTap(String tap) {
        this.tap = tap;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getVm() {
        return vm;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }

    public String getOstan() {
        return ostan;
    }

    public void setOstan(String ostan) {
        this.ostan = ostan;
    }

    public String getOrrn() {
        return orrn;
    }

    public void setOrrn(String orrn) {
        this.orrn = orrn;
    }

    public String getOacode() {
        return oacode;
    }

    public void setOacode(String oacode) {
        this.oacode = oacode;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getVasCategory() {
        return vasCategory;
    }

    public void setVasCategory(String vasCategory) {
        this.vasCategory = vasCategory;
    }

    public String getVasProduct() {
        return vasProduct;
    }

    public void setVasProduct(String vasProduct) {
        this.vasProduct = vasProduct;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getTransMethod() {
        return transMethod;
    }

    public void setTransMethod(String transMethod) {
        this.transMethod = transMethod;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }



}
