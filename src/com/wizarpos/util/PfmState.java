package com.wizarpos.util;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PfmState implements Serializable {

    @SerializedName("serial")
    @Expose
    private String serial;
    @SerializedName("ctime")
    @Expose
    private String ctime;
    @SerializedName("bl")
    @Expose
    private Integer bl;
    @SerializedName("cs")
    @Expose
    private String cs;
    @SerializedName("ps")
    @Expose
    private String ps;
    @SerializedName("tid")
    @Expose
    private String tid;
    @SerializedName("coms")
    @Expose
    private String coms;
    @SerializedName("cloc")
    @Expose
    private String cloc;
    @SerializedName("ss")
    @Expose
    private String ss;
    @SerializedName("tmn")
    @Expose
    private String tmn;
    @SerializedName("tmanu")
    @Expose
    private String tmanu;
    @SerializedName("hb")
    @Expose
    private String hb;
    @SerializedName("sv")
    @Expose
    private String sv;
    @SerializedName("lTxnAt")
    @Expose
    private String lTxnAt;
    @SerializedName("pads")
    @Expose
    private String pads;
    @SerializedName("sim")
    @Expose
    private String sim;
    @SerializedName("customField")
    @Expose
    private String customField;
    private final static long serialVersionUID = 2687588311019338894L;

    /**
     * No args constructor for use in serialization
     *
     */
    public PfmState() {
    }

    /**
     *
     * @param customField
     * @param cloc
     * @param tmanu
     * @param hb
     * @param ctime
     * @param cs
     * @param bl
     * @param pads
     * @param sim
     * @param sv
     * @param ss
     * @param tid
     * @param tmn
     * @param serial
     * @param coms
     * @param lTxnAt
     * @param ps
     */
    public PfmState(String serial, String ctime, Integer bl, String cs, String ps, String tid, String coms, String cloc, String ss, String tmn, String tmanu, String hb, String sv, String lTxnAt, String pads, String sim) {
        super();
        this.serial = serial;
        this.ctime = ctime;
        this.bl = bl;
        this.cs = cs;
        this.ps = ps;
        this.tid = tid;
        this.coms = coms;
        this.cloc = cloc;
        this.ss = ss;
        this.tmn = tmn;
        this.tmanu = tmanu;
        this.hb = hb;
        this.sv = sv;
        this.lTxnAt = lTxnAt;
        this.pads = pads;
        this.sim = sim;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public Integer getBl() {
        return bl;
    }

    public void setBl(Integer bl) {
        this.bl = bl;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getComs() {
        return coms;
    }

    public void setComs(String coms) {
        this.coms = coms;
    }

    public String getCloc() {
        return cloc;
    }

    public void setCloc(String cloc) {
        this.cloc = cloc;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    public String getTmn() {
        return tmn;
    }

    public void setTmn(String tmn) {
        this.tmn = tmn;
    }

    public String getTmanu() {
        return tmanu;
    }

    public void setTmanu(String tmanu) {
        this.tmanu = tmanu;
    }

    public String getHb() {
        return hb;
    }

    public void setHb(String hb) {
        this.hb = hb;
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getLTxnAt() {
        return lTxnAt;
    }

    public void setLTxnAt(String lTxnAt) {
        this.lTxnAt = lTxnAt;
    }

    public String getPads() {
        return pads;
    }

    public void setPads(String pads) {
        this.pads = pads;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }



}
