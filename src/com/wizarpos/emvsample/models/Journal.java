package com.wizarpos.emvsample.models;

public class Journal
{
    public String stan;
    public String mPan;
    public String rrn;
    public String acode;
    public String amount;
    public String timestamp;
    public String mti;
    public String ps;
    public String resp;
    public Boolean tap;
    public String rep;
    public String vm ;
    public String ostan;
    public String orrn;
    public String oacode;
    public String mid;
    public String vasCategory;
    public String vasProduct;
    public String mcc;
    public String transMethod;
    public String customField;


    public Journal(String stan, String mPan, String rrn, String acode, String amount, String timestamp, String mti, String ps, String resp, Boolean tap, String rep, String vm, String ostan, String orrn, String oacode, String mid, String vasCategory,
            String vasProduct, String mcc, String transMethod,String customField) {
        this.stan = stan;
        this.mPan = mPan;
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
        this.vasCategory=vasCategory;
        this.vasProduct=vasProduct;
        this.mcc = mcc;
        this.transMethod = transMethod;
        this.customField=customField;
      }

//            "stan": "194238",
//            "mPan": "439983XXXXXX4686",
//            "rrn": "000019856870",
//            "acode": "083849",
//            "amount": 8,
//            "timestamp": "",
//            "mti": "0200",
//            "ps": "001000",
//            "resp": "00",
//            "tap": "true",
//            "rep": "",
//            "vm": "OfflinePin",
//            "ostan": "",
//            "orrn": "",
//            "oacode": "",
//            "mid": "2082RI000606212",
//            "vasCategory": "IKEDC",
//            "vasProduct": "Sub Product Typpe",
//            "mcc": "5555",
//            "transMethod": "Card",
//            "customField": ""




}
