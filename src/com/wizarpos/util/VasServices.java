package com.wizarpos.util;

import android.support.v4.util.ArrayMap;

import com.wizarpos.emvsample.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bamitale @Itex on 12/14/2015.
 */
public class VasServices {

    public static final String GLO = "Glo";
    public static final String MTN = "Mtn";
    public static final String AIRTEL = "Airtel";
    public static final String ETISALAT = "9mobile";
    public static final String LEKKI_LCC = "Lekki Lcc Toll";
    public static final String STARTIMES = "Star Times";
    public static final String EKO_ELECTRIC = "Eko Electricity DC";
    public static final String IKEJA_ELECTRIC = "Ikeja Electric";
    public static final String IBADAN_ELECTRIC = "Ibadan Electricity";
    public static final String ENUGU_ELECTRIC = "Enugu Electric";
    public static final String PORTHARCOURT_ELECTRIC = "Portharcourt Electricity";
    public static final String ABUJA_ELECTRIC = "Abuja Electric";
    public static final String DSTV = "DSTV";
    public static final String GOTV = "GOTV";
    public static final String WAEC = "WAEC ";
    public static final String SMILE = "Smile 4G LTE";
    public static final String GENESIS = "Genesis Movie Tickets";
    public static final String EKO_ELECTRICITY_PREPAID = "Eko Electricity DC Prepaid";
    public static final String EKO_ELECTRICITY_POSTPAID = "Eko Electricity DC Postpaid";
    public static final String IKEJA_ELECTRICITY_PREPAID = "Ikeja Electric Prepaid Token";
    public static final String IKEJA_ELECTRICITY_POSTPAID  = "Ikeja Electric Postpaid";
    public static final String IBADAN_ELECTRICITY_PREPAID = "Ibadan Eletric Prepaid Token";
    public static final String IBADAN_ELECTRICITY_PREPAID_SMARTCARD = "Ibadan Eletric Prepaid SmartCard";
    public static final String IBADAN_ELECTRICITY_POSTPAID  = "Ibadan Eletric Postpaid";
    public static final String ENUGU_ELECTRICITY_PREPAID = "Enugu Eletric Prepaid";
    public static final String ENUGU_ELECTRICITY_POSTPAID = "Enugu Eletric Postpaid";
    public static final String ABUJA_ELECTRICITY_PREPAID = "Abuja Eletric Prepaid";
    public static final String ABUJA_ELECTRICITY_POSTPAID  = "Abuja Eletric Postpaid";
    public static final String PORTHARCOURT_ELECTRICITY_PREPAID = "Portharcourt Eletric Prepaid";
    public static final String PORTHARCOURT_ELECTRICITY_POSTPAID = "Portharcourt Eletric Postpaid";
    public static final String CASH = "cash";
    public static final String CARD = "card";
    public static final String PREPAID = "prepaid";
    public static final String POSTPAID = "postpaid";
    public static final String ABUJA_POSTPAID = "2";
    public static final String ABUJA_PREPAID = "0";
    public static final String IKEJA_PREPAID="token";
    public static final String IKEJA_POSTPAID="postpaid";
    public static final String IBADAN_PREPAID="prepaid";
    public static final String IBADAN_POSTPAID="postpaid";
    public static final String  EKO_PREPAID="prepaid";
    public static final String  EKO_POSTPAID="postpaid";
    public static final String  PORTHARCOURT_PREPAID="prepaid";
    public static final String  PORTHARCOURT_POSTPAID="postpaid";
    public static final String  ENUGU_PREPAID="prepaid";
    public static final String  ENUGU_POSTPAID="postpaid";




    public static final String VTU = " Virtual Top-up", PIN = " Pin", DATA = " Data";


    public static final Map<String, String> VAS_SERVICE_INPUT_TEXT_DESC = new ArrayMap<>();
    public static final Map<String, Service> SERVICES = new HashMap<>();


    static {
        //AIRTEL
//        SERVICES.put(AIRTEL, new Service(AIRTEL, R.drawable.airtel, Service.Type.AIRTIME, new Service.Product[]{
//                new Service.Product(AIRTEL + VTU, "AIRTELVTU", null),
//                new Service.Product(AIRTEL + DATA, "AIRTELDATA", "AIRTELDATA"),
//                new Service.Product(AIRTEL + PIN, "AIRTELPIN", "AIRTELPIN")
//        }));
//
//        //ETISALAT
//        SERVICES.put(ETISALAT, new Service(ETISALAT, R.drawable.etisalat, Service.Type.AIRTIME, new Service.Product[]{
//                new Service.Product(ETISALAT + VTU, "ETISALATVTU", null),
//                new Service.Product(ETISALAT + DATA, "ETISALATDATA", "ETISALATDATA"),
//                new Service.Product(ETISALAT + PIN, "ETISALATPIN", "ETISALATPIN")
//        }));
////
//
//        //GLO
//        SERVICES.put(GLO, new Service(GLO, R.drawable.glo, Service.Type.AIRTIME, new Service.Product[]{
//                new Service.Product(GLO + VTU, "GLOVTU", null),
//                new Service.Product(GLO + DATA, "GLODATA", "GLODATA"),
//                new Service.Product(GLO + PIN, "GLOPIN", "GLOPIN")
//        }));
//
//        //MTN
//        SERVICES.put(MTN, new Service(MTN, R.drawable.mtn, Service.Type.AIRTIME, new Service.Product[]{
//                new Service.Product(MTN + VTU, "MTNVTU", null),
//                new Service.Product(MTN + DATA,"MTNDATA", "MTNDATA"),
//                new Service.Product(MTN + PIN, "MTNPIN", "MTNPIN")
//
//        }));
//
        //WAEC
//        SERVICES.put(WAEC, new Service(WAEC, R.drawable.waec, Service.Type.PIN, new Service.Product[]{
//                new Service.Product(WAEC + " ServiceResult" + PIN, "WEACPIN", "WEACPIN")
//        }));

        //LCC
//        SERVICES.put(LEKKI_LCC, new Service(LEKKI_LCC, R.drawable.lcc, new Service.Product[]{
//                new Service.Product(LEKKI_LCC + " Recharge", "LCC", null)
//        }));


        //EKO ELECTRICTIY DC OPTIONS
        SERVICES.put(EKO_ELECTRIC, new Service(EKO_ELECTRIC, R.drawable.ekedc, new Service.Product[]{
                new Service.Product(EKO_ELECTRICITY_PREPAID, EKO_PREPAID, null),
                new Service.Product(EKO_ELECTRICITY_POSTPAID, EKO_POSTPAID, null)
        }));



        //IKEJA ELECTRIC DC OPTIONS
        SERVICES.put(IKEJA_ELECTRIC, new Service(IKEJA_ELECTRIC, R.drawable.ikedc, new Service.Product[]{
                new Service.Product(IKEJA_ELECTRICITY_PREPAID  ,IKEJA_PREPAID , null),
                new Service.Product(IKEJA_ELECTRICITY_POSTPAID, IKEJA_POSTPAID, null)
        }));



        //IBADAN ELECTRIC DC OPTIONS
        SERVICES.put(IBADAN_ELECTRIC, new Service(IBADAN_ELECTRIC,R.drawable.ibedc,new Service.Product[]{
                new Service.Product(IBADAN_ELECTRICITY_PREPAID, IBADAN_PREPAID,null),
//                new Service.Product("Ibadan Eletric Prepaid SmartCard", "IBADANPREPAIDSMTCRD",null),
                new Service.Product(IBADAN_ELECTRICITY_POSTPAID, IBADAN_POSTPAID,null)


        }));


        //ENUGU ELECTRIC DC OPTIONS
        SERVICES.put(ENUGU_ELECTRIC, new Service(ENUGU_ELECTRIC,R.drawable.eedc,new Service.Product[]{
                new Service.Product(ENUGU_ELECTRICITY_PREPAID,ENUGU_PREPAID,null ),
                new Service.Product(ENUGU_ELECTRICITY_PREPAID,ENUGU_PREPAID,null )
        }));

        //ABUJA ELECTRIC DC OPTIONS

        SERVICES.put(ABUJA_ELECTRIC, new Service(ABUJA_ELECTRIC,R.drawable.aedc,new Service.Product[]{
                new Service.Product(ABUJA_ELECTRICITY_PREPAID,ABUJA_PREPAID,null ),
                new Service.Product(ABUJA_ELECTRICITY_POSTPAID,ABUJA_POSTPAID ,null )
        }));

        //PORTHARCOURT ELECTRIC DC OPTIONS
//        public static final String PORTHARCOURT_ELECTRICITY_POSTPAID = "Portharcourt Eletric Postpaid";
//        public static final String PORTHARCOURT_ELECTRICITY_POSTPAID = "Portharcourt Eletric Postpaid";
        SERVICES.put(PORTHARCOURT_ELECTRIC, new Service(PORTHARCOURT_ELECTRIC,R.drawable.phdc,new Service.Product[]{
                new Service.Product(PORTHARCOURT_ELECTRICITY_PREPAID,PORTHARCOURT_PREPAID,null ),
                new Service.Product(PORTHARCOURT_ELECTRICITY_POSTPAID,PORTHARCOURT_POSTPAID,null )
        }));





        //DSTV
        SERVICES.put(DSTV, new Service(DSTV, R.drawable.dstv, Service.Type.PLAN, new Service.Product[]{
                new Service.Product("DSTV Subscription", DSTV, "DSTVPROD"),
        }));

        //GOTV
        SERVICES.put(GOTV, new Service(GOTV, R.drawable.gotv, Service.Type.PLAN, new Service.Product[]{
                new Service.Product("GOTV Subscription", GOTV, "GOTVPROD"),
        }));

        //STAR TIMES SERVICE OPTIONS
        SERVICES.put(STARTIMES, new Service(STARTIMES, R.drawable.startime, new Service.Product[]{
                new Service.Product("Star Times Cable TV", "STARTIMES", null)
        }));


        //SMILE SERVICE OPTIONS
        SERVICES.put(SMILE, new Service(SMILE, R.drawable.smile, Service.Type.PLAN_VALUE, new Service.Product[]{
                new Service.Product(SMILE + " Top-up", "SMILE", null),
                new Service.Product(SMILE + " Bundles", "SMILEBUN", "SMILEBUNGET")
        }));

        //Genesis
//        SERVICES.put(GENESIS, new Service(GENESIS, R.drawable.waec, Service.Type.VALUE, new Service.Product[]{
//                new Service.Product(GENESIS + " Tickets NO", "GENESIS", "GENESIS")
//        }));


        VAS_SERVICE_INPUT_TEXT_DESC.put(SMILE, "Smile Account ID");
        VAS_SERVICE_INPUT_TEXT_DESC.put(LEKKI_LCC, "LCC Account Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(DSTV, "DStv Smart Card Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(GOTV, "GOtv Smart Card Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(STARTIMES, "Smart Card Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(EKO_ELECTRIC, "EKEDC Meter Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(IKEJA_ELECTRIC, "IKEDC Meter Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(IBADAN_ELECTRIC, "IBDC Meter Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(ENUGU_ELECTRIC, "EEDC Meter Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(ABUJA_ELECTRIC, "ADEC Meter Number");
        VAS_SERVICE_INPUT_TEXT_DESC.put(PORTHARCOURT_ELECTRIC, "PHDC Meter Number");


    }

    public enum ServiceType {TOP_UP, OTHERS}

}
