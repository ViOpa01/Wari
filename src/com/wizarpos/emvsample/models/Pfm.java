package com.wizarpos.emvsample.models;

import com.google.gson.annotations.SerializedName;

public class Pfm
{
    @SerializedName("journal")
    public com.itex.richard.payviceconnect.model.Journal journal;
    @SerializedName("state")
    public com.itex.richard.payviceconnect.model.State state;

    public Pfm(com.itex.richard.payviceconnect.model.State stateGenerator, com.itex.richard.payviceconnect.model.Journal journal)
    {
        this.journal = journal;
        this.state = stateGenerator;
    }
}
