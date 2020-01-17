package com.wizarpos.emvsample.models;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003$%&B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0005H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0006\u0010\u0018\u001a\u00020\u0005J\b\u0010\u0019\u001a\u00020\u0005H\u0002J\b\u0010\u001a\u001a\u00020\u0005H\u0003J\b\u0010\u001b\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\n \u001d*\u0004\u0018\u00010\u00050\u0005H\u0002J\b\u0010\u001e\u001a\u00020\u0005H\u0002J\b\u0010\u001f\u001a\u00020\u0005H\u0002J\b\u0010 \u001a\u00020\u0005H\u0002J\b\u0010!\u001a\u00020\u0005H\u0002J\b\u0010\"\u001a\u00020#H\u0002R\u0014\u0010\u0007\u001a\u00020\bX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\'"}, d2 = {"Lcom/wizarpos/emvsample/models/PfmStateGenerator;", "", "context", "Landroid/content/Context;", "terminalId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "REQUEST_ACCESS_COARSE_LOCATION", "", "getREQUEST_ACCESS_COARSE_LOCATION", "()I", "getContext", "()Landroid/content/Context;", "getTerminalId", "()Ljava/lang/String;", "generateState", "Lcom/itex/richard/payviceconnect/model/State;", "getBatteryIntent", "Landroid/content/Intent;", "getBatteryLevel", "getChargingStatus", "Lcom/wizarpos/emvsample/models/PfmStateGenerator$CHARGING_STATUS;", "getCommMethod", "Lcom/wizarpos/emvsample/models/PfmStateGenerator$COMMS_METHOD;", "getCurrentTime", "getLastTransactionTime", "getLocation", "getPads", "getSerialNumber", "kotlin.jvm.PlatformType", "getSignalStrength", "getSoftwareNumber", "getTerminalManufacturer", "getTerminalModelName", "hasBattery", "", "CHARGING_STATUS", "COMMS_METHOD", "locationGenerator", "wari-wari_online_debug"})
public final class PfmStateGenerator {
    private final int REQUEST_ACCESS_COARSE_LOCATION = 234;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String terminalId = null;
    
    public final int getREQUEST_ACCESS_COARSE_LOCATION() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.itex.richard.payviceconnect.model.State generateState() {
        return null;
    }
    
    private final java.lang.String getSerialNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentTime() {
        return null;
    }
    
    private final android.content.Intent getBatteryIntent() {
        return null;
    }
    
    private final java.lang.String getBatteryLevel() {
        return null;
    }
    
    private final com.wizarpos.emvsample.models.PfmStateGenerator.CHARGING_STATUS getChargingStatus() {
        return null;
    }
    
    private final com.wizarpos.emvsample.models.PfmStateGenerator.COMMS_METHOD getCommMethod() {
        return null;
    }
    
    private final java.lang.String getSignalStrength() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final java.lang.String getLocation() {
        return null;
    }
    
    private final java.lang.String getTerminalModelName() {
        return null;
    }
    
    private final java.lang.String getTerminalManufacturer() {
        return null;
    }
    
    private final boolean hasBattery() {
        return false;
    }
    
    private final java.lang.String getSoftwareNumber() {
        return null;
    }
    
    private final java.lang.String getLastTransactionTime() {
        return null;
    }
    
    private final java.lang.String getPads() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTerminalId() {
        return null;
    }
    
    public PfmStateGenerator(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String terminalId) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/wizarpos/emvsample/models/PfmStateGenerator$CHARGING_STATUS;", "", "(Ljava/lang/String;I)V", "CHARGING", "NOTCHARGING", "UNKNOWN", "FULLYCHARGED", "wari-wari_online_debug"})
    public static enum CHARGING_STATUS {
        /*public static final*/ CHARGING /* = new CHARGING() */,
        /*public static final*/ NOTCHARGING /* = new NOTCHARGING() */,
        /*public static final*/ UNKNOWN /* = new UNKNOWN() */,
        /*public static final*/ FULLYCHARGED /* = new FULLYCHARGED() */;
        
        CHARGING_STATUS() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/wizarpos/emvsample/models/PfmStateGenerator$COMMS_METHOD;", "", "(Ljava/lang/String;I)V", "WIFI", "GPRS", "OTHERS", "wari-wari_online_debug"})
    public static enum COMMS_METHOD {
        /*public static final*/ WIFI /* = new WIFI() */,
        /*public static final*/ GPRS /* = new GPRS() */,
        /*public static final*/ OTHERS /* = new OTHERS() */;
        
        COMMS_METHOD() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/wizarpos/emvsample/models/PfmStateGenerator$locationGenerator;", "", "()V", "wari-wari_online_debug"})
    public static final class locationGenerator {
        
        public locationGenerator() {
            super();
        }
    }
}