package com.iisysgroup;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.iisysgroup.device.N900Device;
import com.iisysgroup.newlandtestapp.AllActionsActivity;
import com.iisysgroup.ui.dialog.CommonAlertDialog;
import com.iisysgroup.ui.sale.SimpleTransferListener;
import com.iisysgroup.utils.AppExCode;
import com.iisysgroup.utils.MyLogger;
import com.newland.mtype.DeviceRTException;
import com.newland.mtype.ProcessTimeoutException;
import com.newland.mtype.common.InnerProcessingCode;
import com.newland.mtype.common.ProcessingCode;
import com.newland.mtype.event.DeviceEventListener;
import com.newland.mtype.module.common.cardreader.CommonCardType;
import com.newland.mtype.module.common.cardreader.K21CardReader;
import com.newland.mtype.module.common.cardreader.K21CardReaderEvent;
import com.newland.mtype.module.common.cardreader.OpenCardReaderResult;
import com.newland.mtype.module.common.emv.EmvModule;
import com.newland.mtype.module.common.emv.EmvTransController;
import com.newland.mtype.module.common.pin.K21Pininput;
import com.newland.mtype.module.common.pin.KeyManageType;
import com.newland.mtype.module.common.swiper.K21Swiper;
import com.newland.mtype.module.common.swiper.SwipResult;
import com.newland.mtype.module.common.swiper.SwipResultType;
import com.newland.mtype.module.common.swiper.SwiperReadModel;
import com.newland.mtype.util.Dump;

import java.math.BigDecimal;
import java.math.RoundingMode;

import de.greenrobot.event.EventBus;

/**
 * Created by Itex-PC on 03/09/2018.
 */

public class EmvTask {
    private final Context cont;
    private N900Device n900Device;
    private KeyManageType keyManagerType;
    private SimpleTransferListener simpleTransferListener;
    private K21Swiper k21swiper;
    private K21Pininput pininput;
    private K21CardReader cardReader;
    private EmvModule emvModule;
    Activity activity;
    private boolean isRead;
    private EmvTransController controller;

    public EmvTask(Context context) throws Exception {
       this.cont =  context;
       activity = (Activity) context;
        n900Device = N900Device.getInstance();
       if(!n900Device.isDeviceAlive()){
           new Thread(new Runnable() {
               @Override
               public void run() {
                   n900Device.connectDevice();
               }
           }).start();
       }
       
       initData();
       
   }


    private void initData() throws Exception {
        keyManagerType = KeyManageType.MKSK;  // MKSK 密钥体系
        if (n900Device != null) {
            k21swiper = n900Device.getK21Swiper();
            cardReader = n900Device.getCardReaderModuleType();
            emvModule = n900Device.getEmvModuleType();
            emvModule.initEmvModule(cont);
            pininput = n900Device.getK21Pininput();
            simpleTransferListener = new SimpleTransferListener(activity, emvModule);
        }
    }

    private class EventHolder implements DeviceEventListener<K21CardReaderEvent> {
        @Override
        public void onEvent(K21CardReaderEvent openCardReaderEvent, Handler handler) {
            isRead = false;
            try {
                if (openCardReaderEvent.isFailed()) {
                    // TODO
                    MyLogger.jLog().e("isFailed  读卡超时....!" + openCardReaderEvent.getException());
                    if (openCardReaderEvent.getException() instanceof ProcessTimeoutException) {
                        MyLogger.jLog().e("读卡超时....!");
                    } else {
                        MyLogger.jLog().e("读卡结束，识别到非发起的卡类型" + "\r\n");
                    }
                    return;
                }

                if (openCardReaderEvent.isUserCanceled()) {
                    MyLogger.jLog().e("用户撤销刷卡操作！！!....!");
                    return;
                }
                OpenCardReaderResult cardResult = openCardReaderEvent.getOpenCardReaderResult();
                CommonCardType[] openedModuleTypes = cardResult.getResponseCardTypes();
                if (openedModuleTypes.length > 1) {
                    MyLogger.jLog().e("should return only one type of cardread action!but is " + openedModuleTypes.length);
                    throw new DeviceRTException(AppExCode.GET_TRACKTEXT_FAILED, "should return only one type of cardread action!but is " + openedModuleTypes.length);
                }
                switch (openedModuleTypes[0]) {
                    case MSCARD:
                        MyLogger.jLog().e("当前的卡为:磁条卡" + "\r\n");
                        boolean isCorrent = cardResult.isMSDDataCorrectly();
                        if (!isCorrent) {
                            throw new DeviceRTException(AppExCode.GET_TRACKTEXT_FAILED, "swip failed!");
                        }
                        SwipResult swipRslt = null;
                        swipRslt = k21swiper.readPlainResult(new SwiperReadModel[]{SwiperReadModel.READ_SECOND_TRACK, SwiperReadModel.READ_THIRD_TRACK});

                        if (null != swipRslt && swipRslt.getRsltType() == SwipResultType.SUCCESS) {
                            byte[] secondTrack = swipRslt.getSecondTrackData();
                            byte[] thirdTrack = swipRslt.getThirdTrackData();
                            String cardNo = swipRslt.getAccount().getAcctNo();
                            //confirmCard(cardNo);
                            //AllActionsActivity.this.cardNo = cardNo;
                            MyLogger.jLog().e("卡号为" + cardNo + "\r\n");
                            MyLogger.jLog().e("二磁道:" + (secondTrack == null ? "null" : Dump.getHexDump(secondTrack)) + "\r\n");
                            MyLogger.jLog().e("三磁道:" + (thirdTrack == null ? "null" : Dump.getHexDump(thirdTrack)) + "\r\n");
                            MyLogger.jLog().e("刷卡成功" + "\r\n");
                        } else {
                            MyLogger.jLog().e("刷卡结果空了,请重刷" + "\r\n");
                           // showToast("Read Card is empty. Please try again");
                        }
                        break;
                    case ICCARD:
                        MyLogger.jLog().e("当前的卡为IC卡" + "\r\n");
                        try {
                            controller = emvModule.getEmvTransController(simpleTransferListener);
                            if (controller != null) {
                                MyLogger.jLog().e("IC Card start emv..." + "\r\n");
                                BigDecimal tradeAmount = null;
                                tradeAmount = new BigDecimal(Long.valueOf("999999999998")).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
                                controller.startEmv(
                                        ProcessingCode.GOODS_AND_SERVICE,
                                        InnerProcessingCode.USING_STANDARD_PROCESSINGCODE,
                                        tradeAmount,
                                        new BigDecimal("0"),
                                        true,
                                        true);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            MyLogger.jLog().e("IC Card emv 流程异常..." + e + "\r\n");
                        }
                        break;
                    case RFCARD:
                        controller = emvModule.getEmvTransController(simpleTransferListener);
                        BigDecimal tradeAmount = null;
                        tradeAmount = new BigDecimal(Long.valueOf("999999999998")).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
                        if (controller != null) {
                            MyLogger.jLog().e("RF Card start emv..." + "\r\n");
                            controller.startEmv(
                                    ProcessingCode.GOODS_AND_SERVICE,
                                    InnerProcessingCode.SIMPLE_PROCESSINGCODE,
                                    new BigDecimal("0"),
                                    new BigDecimal("0"),
                                    false);
                        }
                        break;
                    default:
                        throw new DeviceRTException(AppExCode.GET_TRACKTEXT_FAILED, "not support cardreader module:" + openedModuleTypes[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                MyLogger.jLog().e("读卡器开启异常" + "\r\n");
                MyLogger.jLog().e(e.getMessage() + "\r\n");
            }
        }
        public Handler getUIHandler() {
            return null;
        }
    }
      
}
