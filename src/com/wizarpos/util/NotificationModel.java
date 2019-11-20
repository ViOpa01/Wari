package com.wizarpos.util;

import com.google.gson.Gson;
import com.wizarpos.emvsample.generators.PfmNotification;
//import com.wizarpos.emvsample.generators.PfmNotification;

public class NotificationModel {
    private PfmJournal pfmJournal;
    private  PfmState pfmState;
    public NotificationModel(PfmState state, PfmJournal journal){
        this.pfmJournal = journal;
        this.pfmState = state;
    }

    public String build(String notificationReceiver){
        Gson gson = new Gson();
       /* String stateString = gson.toJson(this.pfmState, PfmState.class);
        String journalString = gson.toJson(this.pfmJournal, PfmJournal.class);
        return "{ \"state\": "+stateString+", \"journal\": "+journalString+", \"getRRN\": \"true\", \"requestType\" : \""+notificationReceiver+"\" }";
        */
//       return gson.toJson(new PfmNotification.PFMDATA(pfmState,pfmJournal,"true", notificationReceiver),PfmNotification.PFMDATA.class);
       return gson.toJson(new PfmNotification.PFMDATA(pfmState,pfmJournal),PfmNotification.PFMDATA.class);
    }


}
