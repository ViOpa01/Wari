package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wizarpos.emvsample.BaseCard;
import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.db.AIDService;
import com.wizarpos.emvsample.db.AdviceService;
import com.wizarpos.emvsample.db.CAPKService;
import com.wizarpos.emvsample.db.ExceptionFileService;
import com.wizarpos.emvsample.db.RevokedCAPKService;
import com.wizarpos.emvsample.db.TransDetailService;
import com.wizarpos.jni.ContactICCardReaderInterface;
import com.wizarpos.jni.ContactlessICCardReaderInterface;
import com.wizarpos.util.NumberUtil;
import com.wizarpos.util.StringUtil;

import java.lang.ref.WeakReference;

public class CardFunctions extends BaseCard {

    protected static WeakReferenceHandler mHandler = new WeakReferenceHandler(null);
    protected static boolean contactOpened = false;
    protected static boolean contactlessOpened = false;


    public CardFunctions(Activity activity){
        appState = activity;
        BaseCardonCreate();

        if(icInitFlag == false)
        {
            if(ContactICCardReaderInterface.init() >= 0)
            {
                Log.d(APP_TAG, "ContactICCardReaderInterface.init() OK");
               icInitFlag = true;
            }
            if(ContactlessICCardReaderInterface.init() >= 0)
            {
                Log.d(APP_TAG, "ContactlessICCardReaderInterface.init() OK");
               icInitFlag = true;
            }
        }

        //Initialize the data
        initData();
        idleFlag = true;
        if(emvParamLoadFlag == false)
        {
            loadEMVParam();
            byte[] version = new byte[32];
            int len = emv_get_version_string(version, version.length);
        }
        else{
            if(emvParamChanged == true)
            {
                //TODO fix setEMVTermInfo
                //setEMVTermInfo();
            }
        }
        //Set Handler
        mHandler.setFunActivity(this);

        mHandler.setFunActivity(this);

        if(icInitFlag != true)
        {
            idleFlag = false;
            //TOdo do go2Error from idleActivity
           // go2Error(R.string.error_init_ic);
            return;
        }
        waitContactCard();


    }



    public native static byte loadEMVKernel(byte[] libDir,int DirLength);
    public native static byte exitEMVKernel();
    // Card Functions
    public native static int open_reader(int reader);
    public native static void close_reader(int reader);
    public native static int poweron_card();
    public native static int get_card_type();
    public native static int get_card_atr(byte[] atr);
    public native static int transmit_card(byte[] cmd, int cmdLength, byte[] respData, int respDataLength);

    /**
     * @param enable
     *    0 - don't detach card when close contactless reader；
     *    1 - waiting detach card when close contactless reader
     *    2 - do not control
     * */
    public native static void set_contactless_detach_enable(int enable);
    // EMV Functions
    public native static void emv_kernel_initialize();                                                          // 0
    public native static int emv_is_tag_present(int tag);                              	                        // 1
    public native static int emv_get_tag_data(int tag, byte[] data, int dataLength);	                        // 2
    public native static int emv_get_tag_list_data(int[] tagList, int tagCount, byte[] data, int dataLength);	// 3
    public native static int emv_set_tag_data(int tag, byte[] data, int dataLength);                     		// 4
    public native static int emv_preprocess_qpboc();                                                            // 5
    public native static void emv_trans_initialize();                                                           // 6
    public native static int emv_get_version_string(byte[] data, int dataLength);					            // 7
    public native static int emv_set_trans_amount(byte[] amount); 							                    // 8  ASC 以分为单位
    public native static int emv_set_other_amount(byte[] amount);						                        // 9
    public native static int emv_set_trans_type(byte transType);							                    //10
    public native static int emv_set_kernel_type(byte kernelType);							                    //11
    public native static int emv_process_next();												                //12
    public native static int emv_is_need_advice();							                                    //13
    public native static int emv_is_need_signature();							                                //14
    public native static int emv_set_force_online(int flag);							                        //15
    public native static int emv_get_card_record(byte[] data, int dataLength);							        //16
    public native static int emv_get_candidate_list(byte[] data, int dataLength);							    //17
    public native static int emv_set_candidate_list_result(int index);							                //18
    public native static int emv_set_id_check_result(int result);							                    //19
    public native static int emv_set_online_pin_entered(int result);							                //20
    public native static int emv_set_pin_bypass_confirmed(int result);							                //21
    public native static int emv_set_online_result(int result, byte[] respCode, byte[] issuerRespData, int issuerRespDataLength); // 22

    public native static int emv_aidparam_clear();                             							        //23
    public native static int emv_aidparam_add(byte[] data, int dataLength);							            //24
    public native static int emv_capkparam_clear();							                                    //25
    public native static int emv_capkparam_add(byte[] data, int dataLength);    			                    //26
    public native static int emv_terminal_param_set(byte[] TerminalParam);							            //27
    public native static int emv_terminal_param_set2(byte[] TerminalParam, int paramLength);
    public native static int emv_exception_file_clear();							                            //28
    public native static int emv_exception_file_add(byte[] exceptFile);							                //29
    public native static int emv_revoked_cert_clear();							                                //30
    public native static int emv_revoked_cert_add(byte[] revokedCert);							                //31

    public native static int emv_log_file_clear();                                                              //32
    public native static int emv_set_kernel_attr(byte[] data, int dataLength);
    public native static int emv_set_currency_symbol(byte[] data, int dataLength);


    public void loadEMVParam()
    {
        //lib path
        String tmpEmvLibDir = "";
        tmpEmvLibDir = appState.getDir("", 0).getAbsolutePath();
        tmpEmvLibDir = tmpEmvLibDir.substring(0, tmpEmvLibDir.lastIndexOf('/')) + "/lib/libEMVKernal.so";

        if (loadEMVKernel(tmpEmvLibDir.getBytes(),tmpEmvLibDir.getBytes().length) == 0)
        {
            emv_kernel_initialize();
            emv_set_kernel_attr(new byte[]{0x20}, 1);

            if(loadCAPK() == -2)
            {
                capkChecksumErrorDialog(appState);
            }
            loadAID();
            loadExceptionFile();
            loadRevokedCAPK();
            //setEMVTermInfo();

            emv_set_force_online(terminalConfig.getforceOnline());


            emvParamLoadFlag = true;
        }
    }


    private void capkChecksumErrorDialog(Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error");
        builder.setMessage("CAPK:" + failedCAPKInfo + "\nChecksum Error");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private  boolean loadAID() {
        aids = aidService.query();
        emv_aidparam_clear();
        byte[] aidInfo = null;
        for(int i=0; i< aids.length; i++)
        {
            if(aids[i] != null)
            {
                aidInfo = aids[i].getDataBuffer();
                if(emv_aidparam_add(aidInfo, aidInfo.length) < 0)
                    return false;
            }
            else
            {
                break;
            }
        }
        return true;
    }

    private  int loadCAPK()
    {
        capks = capkService.query();
        emv_capkparam_clear();
        byte[] capkInfo = null;
        for(int i=0; i< capks.length; i++)
        {
            if(capks[i] != null)
            {
                capkInfo = capks[i].getDataBuffer();
                int ret = emv_capkparam_add(capkInfo, capkInfo.length);
                if( ret < 0)
                {
                    failedCAPKInfo = capks[i].getRID() + "_" + capks[i].getCapki();
                    return ret;
                }
            }
            else
            {
                break;
            }
        }
        return 0;
    }

    private  boolean loadExceptionFile()
    {
        exceptionFiles = exceptionFileService.query();
        emv_exception_file_clear();
        byte[] exceptionFileInfo = null;
        for(int i=0; i< exceptionFiles.length; i++)
        {
            if(exceptionFiles[i] != null)
            {
                exceptionFileInfo = exceptionFiles[i].getDataBuffer();
                if(emv_exception_file_add(exceptionFileInfo) < 0)
                    return false;
            }
            else
            {
                break;
            }
        }
        return true;
    }

    private  boolean loadRevokedCAPK()
    {
       revokedCapks = revokedCAPKService.query();
        emv_revoked_cert_clear();
        byte[] revokedCAPKInfo = null;
        for(int i=0; i< revokedCapks.length; i++)
        {
            if(revokedCapks[i] != null)
            {
                revokedCAPKInfo = revokedCapks[i].getDataBuffer();
                if(revokedCAPKInfo != null)
                {
                    if(emv_revoked_cert_add(revokedCAPKInfo) < 0)
                        return false;
                }
            }
            else
            {
                break;
            }
        }
        return true;
    }


    protected static class WeakReferenceHandler extends Handler {

        private WeakReference<CardFunctions> mActivity;
        public WeakReferenceHandler(CardFunctions activity){
            mActivity = new WeakReference<CardFunctions>(activity);
        }

        public void setFunActivity(CardFunctions activity){
            mActivity = new WeakReference<CardFunctions>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            CardFunctions activity = mActivity.get();
            if(activity != null){
                activity.handleMessage(msg);
            }
        }
    }

    private void handleMessage(Message msg) {
        /*这里是处理信息的方法*/
        switch (msg.what)
        {
            case CARD_INSERT_NOTIFIER:
                Bundle bundle = msg.getData();
                int nEventID = bundle.getInt("nEventID");
                int nSlotIndex = bundle.getInt("nSlotIndex");
                if(debug)Log.d(APP_TAG, "get CONTACT_CARD_EVENT_NOTIFIER,event[" + nEventID + "]slot[" + nSlotIndex + "]" );
                if(   nSlotIndex == 0
                        && nEventID == SMART_CARD_EVENT_INSERT_CARD
                        )
                {
                   // cancelMSRThread();
                    resetCardError = false;
                    trans.setCardEntryMode(INSERT_ENTRY);
                    needCard = false;
                    //sale();
                }
                break;
            case CARD_ERROR_NOTIFIER:
                //cancelMSRThread();
               trans.setEmvCardError(true);
                resetCardError = true;
                needCard = true;
                //sale();
                break;
        }
    }

    protected void waitContactCard()
    {
        contactOpened = true;
        open_reader(1);
    }

    public void emvProcessCallback(byte[] data)
    {
        if(debug)Log.d(APP_TAG, "emvProcessNextCompleted");
        trans.setEMVStatus(data[0]);
        trans.setEMVRetCode(data[1]);

        Message msg = new Message();
        msg.what = EMV_PROCESS_NEXT_COMPLETED_NOTIFIER;
        mHandler.sendMessage(msg);
    }

    public  void cardEventOccured(int eventType)
    {
        if(debug)Log.d(APP_TAG, "get cardEventOccured");
        Message msg = new Message();
        if(eventType == SMART_CARD_EVENT_INSERT_CARD)
        {
            cardType = get_card_type();
            if(debug)Log.d(APP_TAG, "cardType = " + cardType);

            if(cardType == CARD_CONTACT)
            {
                msg.what = CARD_INSERT_NOTIFIER;
                mHandler.sendMessage(msg);
            }
            else if(cardType == CARD_CONTACTLESS)
            {
                msg.what = CARD_TAPED_NOTIFIER;
                mHandler.sendMessage(msg);
            }
            else{
                cardType = -1;
            }
        }
        else if(eventType == SMART_CARD_EVENT_POWERON_ERROR)
        {
            cardType = -1;
            msg.what = CARD_ERROR_NOTIFIER;
            mHandler.sendMessage(msg);
        }
        else if(eventType == SMART_CARD_EVENT_REMOVE_CARD)
        {
            cardType = -1;
        }
        else if(eventType == SMART_CARD_EVENT_CONTALESS_HAVE_MORE_CARD)
        {
            cardType = -1;
            msg.what = CONTACTLESS_HAVE_MORE_CARD_NOTIFIER;
            mHandler.sendMessage(msg);
        }

    }

    public void setEMVTermInfo()
    {
        //terminal_country_code[2];             // 9F1A: Terminal Country Code  0  + 2
        //TID[8];								// 9F1C                         2  + 8
        //IFD[8];                               // 9F1E: IFD Serial Number      10 + 8
        //transaction_currency_code[2];			// 5F2A                         18 + 2
        //terminal_capabilities[3];             // 9F33                         20 + 3
        //terminal_type[1];						// 9F35                         23 + 1
        //transaction_currency_exponent[1];		// 5F36                         24 + 1
        //additional_terminal_capabilities[5];  // 9F40                         25 + 5
        //merchantNameLength;                                                   30 + 1
        //merchantName[20];                     // 9F4E                         31 +20
        //ttq                                   // 9F66首字节                                                                         51 + 1
        //statusCheckSupport                                                    52 + 1
        //ECTermTransLimit[6];	                // 9F7B                         53 + 6
        //contactlessLimit[6];                                                  59 + 6
        //contactlessFloorLimit[6];                                             65 + 6
        //cvmLimit[6];                                                          71 + 6
        //zeroCheck                                                             77
        //contactLessLimitEnable                                                78
        //contactLessFloorLimitEnable                                           79
        //cvmLimitEnable                                                        80
        //TerminalFloorLimit                                                    81 + 4
        //TerminalFloorLimitEnable                                              85
        //ctlOnDeviceCVMLimit                                                   86 + 6
        //ctlNoOnDeviceCVMLimit                                                 92 + 6

        byte[] termInfo = new byte[98];
        System.arraycopy(StringUtil.hexString2bytes(terminalConfig.getCountryCode()), 0, termInfo, 0, 2);
        System.arraycopy(terminalConfig.getTID().getBytes(), 0, termInfo, 2, 8);
        System.arraycopy(terminalConfig.getIFD().getBytes(), 0, termInfo, 10, terminalConfig.getIFD().length());  // 8
        System.arraycopy(StringUtil.hexString2bytes(terminalConfig.getCurrencyCode()), 0, termInfo, 18, 2);
        System.arraycopy(StringUtil.hexString2bytes(terminalConfig.getTerminalCapabilities()), 0, termInfo, 20, 3);
        termInfo[23] = StringUtil.hexString2bytes(terminalConfig.getTerminalType())[0];
        termInfo[24] = terminalConfig.getCurrencyExponent();
        System.arraycopy(StringUtil.hexString2bytes(terminalConfig.getAdditionalTerminalCapabilities()), 0, termInfo, 25, 5);
        termInfo[30] = (byte)terminalConfig.getMerchantName1().length();
        System.arraycopy(terminalConfig.getMerchantName1().getBytes(), 0, termInfo, 31, termInfo[30]); // 20
        termInfo[51] = terminalConfig.getTTQ();
        termInfo[52] = terminalConfig.getStatusCheckSupport();
        System.arraycopy(NumberUtil.intToBcd(terminalConfig.getEcLimit(), 6), 0, termInfo, 53, 6);
        System.arraycopy(NumberUtil.intToBcd(terminalConfig.getContactlessLimit(), 6), 0, termInfo, 59, 6);
        System.arraycopy(NumberUtil.intToBcd(terminalConfig.getContactlessFloorLimit(), 6), 0, termInfo, 65, 6);
        System.arraycopy(NumberUtil.intToBcd(terminalConfig.getCvmLimit(), 6), 0, termInfo, 71, 6);

        termInfo[77] = 1;
        termInfo[78] = 1;
        termInfo[79] = 1;
        termInfo[80] = 1;
        System.arraycopy(NumberUtil.intToBcd(999999, 4), 0, termInfo, 81, 4);
        termInfo[85] = 1;

        System.arraycopy(NumberUtil.intToBcd(999999, 6), 0, termInfo, 86, 6);
        System.arraycopy(NumberUtil.intToBcd(999999, 6), 0, termInfo, 92, 6);

        emv_terminal_param_set2(termInfo, 98);
    }

    void setEMVTransAmount(String strAmt)
    {
        byte[] amt = new byte[strAmt.length() + 1];
        System.arraycopy(strAmt.getBytes(), 0, amt, 0, strAmt.length());
        emv_set_trans_amount(amt);
    }
}
