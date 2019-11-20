package com.wizarpos.emvsample.db;

import com.iisysgroup.poslib.host.entities.TransactionResult;

import java.util.List;

public class EodModel {
    private TransactionResult results;
    private  String ammount;
    private int id;

    public  EodModel(TransactionResult res, String amt){
        this.results = res;
        this.ammount = amt;
    }

    public TransactionResult getResults() {
        return results;
    }

    public String getAmmount() {
        return ammount;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int curId){
        this.id = curId;
    }
}
