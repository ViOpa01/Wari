package com.wizarpos.emvsample;

import com.iisysgroup.poslib.ISO.POSVAS.PosvasKeyProcessor;
import com.iisysgroup.poslib.commons.TripleDES;

public class getMasterKey extends PosvasKeyProcessor {
    public static final String CLR_KEY_1_TEST = "5D25072F04832A2329D93E4F91BA23A2";
    public static final String CLR_KEY_2_TEST = "86CBCDE3B0A22354853E04521686863D";
//    public static final String CLR_KEY_1_LIVE = sanitize("D 3 7 F 3 D C 8 5 8 1 5 9 4 5 1E F E 5 C 4 6 E B A D 3 4 6 D 6");
//    public static final String CLR_KEY_2_LIVE = sanitize("08B3 B A 2 6 0 8 B 3 1 5 5 76 D 1 F 1 6 E 5 A B 4 3 B 3 A E");

    public static final String CLR_KEY_1_LIVE = sanitize("6DD69715CDBA10737CEF3E0E0D38544C");
    public static final String CLR_KEY_2_LIVE = sanitize("5852B67F76DCCBA4D58AA18589D37575");

    public getMasterKey() {
    }

    public static String getMasterKey(String eMasterKey, boolean isTestPlatform) {
        return isTestPlatform ? TripleDES.threeDesDecrypt(CLR_KEY_1_TEST, CLR_KEY_2_TEST, eMasterKey) : TripleDES.threeDesDecrypt(CLR_KEY_1_LIVE, CLR_KEY_2_LIVE, eMasterKey);
    }

    static String sanitize(String str) {
        return str.replaceAll(" ", "").trim();
    }
}