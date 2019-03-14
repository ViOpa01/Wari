package com.iisysgroup.newlandtestapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iisysgroup.device.N900Device;
import com.iisysgroup.ui.dialog.CommonAlertDialog;
import com.iisysgroup.ui.dialog.LoadingDialog;
import com.iisysgroup.ui.sale.SimpleTransferListener;
import com.iisysgroup.utils.AppExCode;
import com.iisysgroup.utils.Const;
import com.iisysgroup.utils.EventMsg;
import com.iisysgroup.utils.FormatUtils;
import com.iisysgroup.utils.MyLogger;
import com.iisysgroup.utils.PrintUtils;
import com.newland.mtype.DeviceRTException;
import com.newland.mtype.ModuleType;
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
import com.newland.mtype.module.common.printer.Printer;
import com.newland.mtype.module.common.swiper.K21Swiper;
import com.newland.mtype.module.common.swiper.SwipResult;
import com.newland.mtype.module.common.swiper.SwipResultType;
import com.newland.mtype.module.common.swiper.SwiperReadModel;
import com.newland.mtype.util.Dump;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;

public class AllActionsActivity extends AppCompatActivity implements View.OnClickListener{

    private Long totalNum;
    private boolean isRead = false;
    private EmvTransController controller;
    private String cardNo = "";
    private boolean hasNext = true;
    private String from="";
    private long amount;
    private int result;
    private String printType = "";
    private String channelId="";

    Button pay, print, barcode, qrc;

    private N900Device n900Device;
    private Printer printer;
    private LoadingDialog loadingDialog;
    private KeyManageType keyManagerType;
    private K21Swiper k21swiper;
    private K21CardReader cardReader;
    private EmvModule emvModule;
    private K21Pininput pininput;
    private CommonAlertDialog dialog;
    private CommonAlertDialog dialog2;
    private SimpleTransferListener simpleTransferListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_actions);

        totalNum= 1L;

        pay = findViewById(R.id.payment);
        print = findViewById(R.id.print);
        barcode = findViewById(R.id.barcode);
        qrc = findViewById(R.id.qrc);

        pay.setOnClickListener(this);
        print.setOnClickListener(this);
        barcode.setOnClickListener(this);
        qrc.setOnClickListener(this);

        //Initialize device
        n900Device = N900Device.getInstance();
        if(!n900Device.isDeviceAlive()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    n900Device.connectDevice();
                }
            }).start();
        }

        EventBus.getDefault().register(this);
        initData();
    }


    public void onEventMainThread(EventMsg event) {
        if (event.msg == Const.EvenBUSType.READ_CARD_SUCCSE) {
            cardNo = (String) event.data;
            Message msg = new Message();
            msg.what = Const.COFIRM_CARD;
            msg.obj=cardNo;
            hander.sendMessage(msg);
        }
        if (event.msg == Const.EvenBUSType.READ_CARD_ERR) {
            showToast("Get card NO. failde");
        }
    }

    private void initData() {
        try{
            amount = 1L;
            from = "Pay";

            keyManagerType = KeyManageType.MKSK;  // MKSK 密钥体系
            if (n900Device!=null){
                k21swiper = n900Device.getK21Swiper();
                cardReader = n900Device.getCardReaderModuleType();
                emvModule = n900Device.getEmvModuleType();
                emvModule.initEmvModule(this);
                pininput = n900Device.getK21Pininput();
                printer = n900Device.getPrinter();
                printer.init();
                dialog = new CommonAlertDialog(this);
                simpleTransferListener = new SimpleTransferListener(this, emvModule);
            }else {
                finish();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.payment:
                openCardRead();
                break;
            case R.id.print:
                showLoadingDialog("Printing");
                printType = getString(R.string.cash);
                printOrder();
                break;
            case R.id.barcode: break;
            case R.id.qrc: break;
        }
    }

    private void openCardRead() {
        isRead = true;
        try {
            MyLogger.jLog().e("请刷卡或者插入IC卡..." + "\r\n");
            showToast("Read card as follows");
            EventHolder listener = new EventHolder();
            cardReader.openCardReader("请刷卡或者插入IC卡",
                    new ModuleType[]{ModuleType.COMMON_SWIPER,
                            ModuleType.COMMON_ICCARDREADER,
                            ModuleType.COMMON_RFCARDREADER},
                    false, true, 90, TimeUnit.SECONDS, listener
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printOrder() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(printType.equals(getString(R.string.cash)))
                        result = PrintUtils.printerCashMsg(AllActionsActivity.this, printer, FormatUtils.formatAmountToYuan(totalNum));
                    else
                        result = PrintUtils.printerMsg(AllActionsActivity.this,printer, cardNo, FormatUtils.formatAmountToYuan(amount),from);

                    if (result==0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dismissLoadingDialog();
                                EventBus.getDefault().post(new EventMsg(Const.EvenBUSType.TRANS_SUCCSE,"cash"));
                                //AllActionsActivity.this.finish();
                            }
                        });
                    }else {
                        dismissLoadingDialog();
                    }
                } catch ( Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
                    showToast("Read Card  Failed timeout");
                    return;
                }

                if (openCardReaderEvent.isUserCanceled()) {
                    MyLogger.jLog().e("用户撤销刷卡操作！！!....!");
                    showToast("Read Card  Canceled");
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
                            showToast("Swip failed! . Please try again");
                            throw new DeviceRTException(AppExCode.GET_TRACKTEXT_FAILED, "swip failed!");
                        }
                        SwipResult swipRslt = null;
                        //通过密文的方式读取磁道数据，要先添加磁道密钥
//                        swipRslt = k21swiper.readEncryptResult(new SwiperReadModel[]{SwiperReadModel.READ_SECOND_TRACK, SwiperReadModel.READ_THIRD_TRACK},
//                                new WorkingKey(Const.DataEncryptWKIndexConst.DEFAULT_TRACK_WK_INDEX),
//                                SupportMSDAlgorithm.getMSDAlgorithm(MESeriesConst.TrackEncryptAlgorithm.BY_UNIONPAY_MODEL));

                        //以明文的方式读取
                        swipRslt = k21swiper.readPlainResult(new SwiperReadModel[]{SwiperReadModel.READ_SECOND_TRACK, SwiperReadModel.READ_THIRD_TRACK});

                        if (null != swipRslt && swipRslt.getRsltType() == SwipResultType.SUCCESS) {
                            byte[] secondTrack = swipRslt.getSecondTrackData();
                            byte[] thirdTrack = swipRslt.getThirdTrackData();
                            String cardNo = swipRslt.getAccount().getAcctNo();
                            confirmCard(cardNo);
                            AllActionsActivity.this.cardNo = cardNo;
                            MyLogger.jLog().e("卡号为" + cardNo + "\r\n");
                            MyLogger.jLog().e("二磁道:" + (secondTrack == null ? "null" : Dump.getHexDump(secondTrack)) + "\r\n");
                            MyLogger.jLog().e("三磁道:" + (thirdTrack == null ? "null" : Dump.getHexDump(thirdTrack)) + "\r\n");
                            MyLogger.jLog().e("刷卡成功" + "\r\n");
                        } else {
                            MyLogger.jLog().e("刷卡结果空了,请重刷" + "\r\n");
                            showToast("Read Card is empty. Please try again");
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

    private void confirmCard(final String cardNo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (dialog == null) {
                        dialog = new CommonAlertDialog(AllActionsActivity.this);
                    }
                    dialog.setTitle("Confirm card NO.");
                    dialog.setMessage(cardNo);
                    dialog.setBtnConfirmTitle("OK", new CommonAlertDialog.OnClickListener() {
                        @Override
                        public void onClick(View view, DialogInterface dialogs) {
                            dialog.dismiss();
                            showLoadingDialog("Connecting.....");
                            Message msg = new Message();
                            msg.what = Const.PRINT;
                            hander.sendMessageDelayed(msg,2000);

                        }
                    });
                    dialog.setBtnCancelTitle("Cancel", null);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Handler hander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!AllActionsActivity.this.isDestroyed()&&!AllActionsActivity.this.isFinishing()) {
                switch (msg.what) {
                    case Const.PRINT:
                        dismissLoadingDialog();
                        if (from.equalsIgnoreCase("Pay")) {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            showLoadingDialog("Printing.....");
                            printOrder();
                        }else if(from.equalsIgnoreCase("main")) {
                            showBalanceDialog("Balance Inquiry","$9999999.99");
                        }else if(from.equalsIgnoreCase("refund")||from.equalsIgnoreCase("revroked")) {
                            showLoadingDialog("Printing.....");
                            printOrder();
                        }
                        break;
                    case Const.PRINT_NEXT:
                        int result = Integer.parseInt(msg.obj + "");
                        if (result == 0) {
                            if (hasNext) {
                                hasNext = false;
                                dismissLoadingDialog();
                                confirmPrintAlert();
                            } else {
                                dismissLoadingDialog();
                                backFont();
                            }
                        } else if (result == -1) {
                            showToast("print failed ....");
//                            getPromptDialog(CardPayActivity.this, "", ");
                            //AllActionsActivity.this.finish();
                        } else {
                            dismissLoadingDialog();
                        }
                        break;
                    case Const.COFIRM_CARD:
                        confirmCard(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        }
    };

    private void confirmPrintAlert() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (dialog == null) {
                        dialog = new CommonAlertDialog(AllActionsActivity.this);
                    }
                    dialog.setTitle("Prompt");
                    dialog.setMessage("Please tear of the bill");
                    dialog.setBtnConfirmTitle("Print next", new CommonAlertDialog.OnClickListener() {
                        @Override
                        public void onClick(View view, DialogInterface dialoga) {
                            dialog.dismiss();
                            showLoadingDialog( "Printing .....");
                            printOrder();
                        }
                    });

                    dialog.setBtnCancelTitle("Out of print", new CommonAlertDialog.OnClickListener() {
                        @Override
                        public void onClick(View view, DialogInterface dialog) {
                            dismissLoadingDialog();
                            dialog.dismiss();
                            showToast("Out of print");
                            backFont();
                            //CardPayActivity.this.finish();
                        }
                    });
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showBalanceDialog(String title,   String msg) {
        CommonAlertDialog dialog = new CommonAlertDialog(this);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setBtnConfirmTitle("OK",  new CommonAlertDialog.OnClickListener() {
            @Override
            public void onClick(View view, DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        dialog.setBtnCancelTitle("Cancel", new CommonAlertDialog.OnClickListener() {
            @Override
            public void onClick(View view, DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cardReader!=null&&n900Device.isDeviceAlive()){
            cardReader.cancelCardRead();
            cardReader.closeCardReader();
            cardReader=null;
        }
        if (dialog!=null){
            dialog.dismiss();
            dialog = null;
        }

        if (dialog2!=null){
            dialog2.dismiss();
            dialog2 = null;
        }
        if (loadingDialog!=null){
            loadingDialog.onDestoryed();
            loadingDialog=null;
        }
        hander=null;
        EventBus.getDefault().unregister(this);
    }

    public  void backFont(){
        if (from.equalsIgnoreCase("Pay")) {
            /*Intent intent =new Intent(AllActionsActivity.this, MainActivity.class);
            startActivity(intent);*/
            EventBus.getDefault().post(new EventMsg(Const.EvenBUSType.TRANS_SUCCSE,channelId));
        }else if(from.equalsIgnoreCase("refund")||from.equalsIgnoreCase("revroked")) {
            EventBus.getDefault().post(new EventMsg(Const.EvenBUSType.REFUND_REVER_SUCCSE,from));
        }
        //AllActionsActivity.this.finish();
    }

    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void showLoadingDialog(String msg) {
        // 初始化提示框
        if (loadingDialog==null){
            loadingDialog = new LoadingDialog(this,R.style.loading_dialog );
        }
        if (this.isDestroyed() || this.isFinishing()) {
            loadingDialog.dismiss();
            return;
        }
        loadingDialog.setContentText(msg);
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
