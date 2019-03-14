package com.iisysgroup.ui.sale;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.iisysgroup.device.N900Device;
import com.iisysgroup.utils.Const;
import com.iisysgroup.utils.EventMsg;
import com.iisysgroup.utils.MyLogger;
import com.newland.me.SupportMSDAlgorithm;
import com.newland.mtype.common.MESeriesConst;
import com.newland.mtype.module.common.emv.EmvModule;
import com.newland.mtype.module.common.emv.EmvTransController;
import com.newland.mtype.module.common.emv.EmvTransInfo;
import com.newland.mtype.module.common.emv.EmvTransInfo.AIDSelect;
import com.newland.mtype.module.common.emv.SecondIssuanceRequest;
import com.newland.mtype.module.common.emv.level2.EmvCardholderCertType;
import com.newland.mtype.module.common.emv.level2.EmvLevel2ControllerExtListener;
import com.newland.mtype.module.common.pin.WorkingKey;
import com.newland.mtype.module.common.swiper.K21Swiper;
import com.newland.mtype.module.common.swiper.SwipResult;
import com.newland.mtype.tlv.TLVPackage;
import com.newland.mtype.util.ISOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.greenrobot.event.EventBus;

import static com.iisysgroup.utils.Const.PIN_FINISH;


/**
 * Created by YJF on 2015/8/14 0014. Emv流程控制监听和QPBOC流程控制监听
 */
public class SimpleTransferListener implements EmvLevel2ControllerExtListener {
	private Activity baseActivity;
	private static int[] L_55TAGS = new int[26];
	private static int[] L_SCRIPTTAGS = new int[21];
	private static int[] L_REVTAGS = new int[5];
	private int isECSwitch = 0;
	private static WaitThreat waitPinInputThreat = new WaitThreat();
	private static byte[] pinBlock = null;
	private int index;
	private String encryptAlgorithm;
	private Dialog amt_dialog;
	private EditText edit_amt_input;
	private Button btn_sure, btn_cancel;
	private CharSequence temp;
	private N900Device n900Device;
	private EmvModule emvModule;

	private static Handler pinEventHandler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case  PIN_FINISH:
				if (msg.obj != null) {
					pinBlock = (byte[]) msg.obj;
				}
				waitPinInputThreat.notifyThread();
				break;
			default:
				break;
			}
		}

	};

	public static Handler getPinEventHandler() {
		return pinEventHandler;
	}

	public static void setPinEventHandler(Handler pinEventHandler) {
		SimpleTransferListener.pinEventHandler = pinEventHandler;
	} 
	static {
		L_55TAGS[0] = 0x9f26;
		L_55TAGS[1] = 0x9F10;
		L_55TAGS[2] = 0x9F27;
		L_55TAGS[3] = 0x9F37;
		L_55TAGS[4] = 0x9F36;
		L_55TAGS[5] = 0x95;
		L_55TAGS[6] = 0x9A;
		L_55TAGS[7] = 0x9C;
		L_55TAGS[8] = 0x9F02;
		L_55TAGS[9] = 0x5F2A;
		L_55TAGS[10] = 0x82;
		L_55TAGS[11] = 0x9F1A;
		L_55TAGS[12] = 0x9F03;
		L_55TAGS[13] = 0x9F33;
		L_55TAGS[14] = 0x9F34;
		L_55TAGS[15] = 0x9F35;
		L_55TAGS[16] = 0x9F1E;
		L_55TAGS[17] = 0x84;
		L_55TAGS[18] = 0x9F09;
		L_55TAGS[19] = 0x9F41;
		L_55TAGS[20] = 0x8a;
		L_55TAGS[21] = 0x9f63;
		L_55TAGS[22] = 0x50;
		L_55TAGS[23] = 0x4f;
		L_55TAGS[24] = 0x9f12;
		L_55TAGS[25] = 0x9B;

		L_SCRIPTTAGS[0] = 0x9F33;
		L_SCRIPTTAGS[1] = 0x9F34;
		L_SCRIPTTAGS[2] = 0x9F35;
		L_SCRIPTTAGS[3] = 0x95;
		L_SCRIPTTAGS[4] = 0x9F37;
		L_SCRIPTTAGS[5] = 0x9F1E;
		L_SCRIPTTAGS[6] = 0x9F10;
		L_SCRIPTTAGS[7] = 0x9F26;
		L_SCRIPTTAGS[8] = 0x9F27;
		L_SCRIPTTAGS[9] = 0x9F36;
		L_SCRIPTTAGS[10] = 0x82;
		L_SCRIPTTAGS[11] = 0xDF31;
		L_SCRIPTTAGS[12] = 0x9F1A;
		L_SCRIPTTAGS[13] = 0x9A;
		L_SCRIPTTAGS[14] = 0x9C;
		L_SCRIPTTAGS[15] = 0x9F02;
		L_SCRIPTTAGS[16] = 0x5F2A;
		L_SCRIPTTAGS[17] = 0x84;
		L_SCRIPTTAGS[18] = 0x9F09;
		L_SCRIPTTAGS[19] = 0x9F41;
		L_SCRIPTTAGS[20] = 0x9F63;

		L_REVTAGS[0] = 0x95;
		L_REVTAGS[1] = 0x9F1E;
		L_REVTAGS[2] = 0x9F10;
		L_REVTAGS[3] = 0x9F36;
		L_REVTAGS[4] = 0xDF31;
	}

	public SimpleTransferListener(Activity activity , EmvModule emvModule)  {
		this.baseActivity=activity;
		this.emvModule = emvModule;
//		if (((MyApplication) (baseActivity).getApplication()).isDukpt()) {
//			index = Const.DUKPTIndexConst.DEFAULT_DUKPT_INDEX;
//			encryptAlgorithm = MESeriesConst.TrackEncryptAlgorithm.BY_DUKPT_MODEL;
//		} else {
			index =  Const.DataEncryptWKIndexConst.DEFAULT_TRACK_WK_INDEX;
			encryptAlgorithm = MESeriesConst.TrackEncryptAlgorithm.BY_UNIONPAY_MODEL;

//		}
	}

	@Override  // 当emv  交易正常结束时发生。
	public void onEmvFinished(boolean isSuccess, EmvTransInfo context) throws Exception {
		int executeRslt = context.getExecuteRslt();
		String resultMsg = null;
		switch (executeRslt) {
		case 0:
		case 1:
			resultMsg = "交易接受";
			break;
		case 2:
			resultMsg = "交易拒绝";
			break;
		case 3:
			resultMsg = "请求联机";
			break;
		case -2105:
			resultMsg = "交易金额超过限额";
			break;
		default:
			resultMsg = "交易失败";
			break;
		}
		MyLogger.jLog().e(">>>>【交易完成】，交易结果:" + resultMsg + "\r\n" );
		int errorCode = context.getErrorcode();
		switch (errorCode) {
		case -6:
			resultMsg = "找不到支持的应用";
			break;
		case -11:
			resultMsg = "脱机数据认证失败";
			break;
		case -13:
			resultMsg = "持卡人认证失败";
			break;
		case -18:
			resultMsg = "卡片锁定";
			break;
		case -1531:
		case -2116:
			resultMsg = "卡片已过期";
			break;
		case -1532:
		case -2115:
			resultMsg = "卡片未生效";
			break;
		case -1822:
			resultMsg = "电子现金余额不足";
			break;
		case -1903:
			resultMsg = "EC圈存金额超出限额";
			break;
		case -1904:
		case -1905:
			resultMsg = "脚本执行错误";
			break;
		case -1901:
			resultMsg = "脚本超限";
			break;
		case -2105:
			resultMsg = "预处理输入金额超出限额";
			break;
		case -2120:
		case -1441:
			resultMsg = "纯电子现金卡不能联机";
			break;
		case -2121:
			resultMsg = "卡片拒绝";
			break;
		default:
			resultMsg = null;
			break;
		}
		if (null != resultMsg) {
			MyLogger.jLog().e(">>>>具体错误原因:"+errorCode + resultMsg + "\r\n");
		}
		int[] emvTags = new int[4];
		emvTags[0] = 0x5a;
		emvTags[1] = 0x5F34;
		emvTags[2] = 0x5f24;
		emvTags[3] = 0x57;
		//根据tag 标签获取emv内核数据
		String data = emvModule.fetchEmvData(emvTags);
		//TAG Len Value 数据包
		TLVPackage tlv = ISOUtils.newTlvPackage();
		tlv.unpack(ISOUtils.hex2byte(data));
		String cardNo = tlv.getString(0x5a);
		String cardSN = tlv.getString(0x5F34);// 卡序列号，等效于context.getCardSequenceNumber()
		String expiredDate = tlv.getString(0x5F24);// 过期日期,等效于context.getCardExpirationDate()
		String track2 = tlv.getString(0x57); // 二磁道数据，等效于context.getTrack_2_eqv_data()
		if (!TextUtils.isEmpty(cardNo)){
			EventBus.getDefault().post(new EventMsg(Const.EvenBUSType.READ_CARD_SUCCSE,cardNo));
		}else {
			EventBus.getDefault().post(new EventMsg(Const.EvenBUSType.READ_CARD_ERR,cardNo));
		}
		if (cardSN == null) {
			cardSN = "000";
		} else {
			cardSN = ISOUtils.padleft(cardSN, 3, '0');
		}
		String serviceCode = "";
		if (null != track2) {
			serviceCode = track2.substring(track2.indexOf('D') + 5, track2.indexOf('D') + 8);
		}
		
		MyLogger.jLog().e("卡号:" + cardNo + "\r\n");
		MyLogger.jLog().e("卡序列号:" + cardSN + "\r\n");
		MyLogger.jLog().e("卡有效期:" + expiredDate + "\r\n");
		MyLogger.jLog().e("服务码:" + serviceCode + "\r\n");
		MyLogger.jLog().e("二磁道明文:" + track2 + "\r\n");
//		((MyApplication) (baseActivity).getApplication()).setIcCardNum(context.getCardNo());
//		((MyApplication) (baseActivity).getApplication()).setEmvTransInfo(context); // 消费报文
		if (null != track2) {
			n900Device = N900Device.getInstance();
			K21Swiper swiper = n900Device.getK21Swiper();
			SwipResult swipRslt = swiper.calculateTrackData(track2, null, new WorkingKey(index), SupportMSDAlgorithm.getMSDAlgorithm(encryptAlgorithm));
			MyLogger.jLog().e(">>>>二磁道密文:" + (swipRslt.getSecondTrackData() == null ? null : ISOUtils.hexString(swipRslt.getSecondTrackData())) + "\r\n");
		}
		String data55 = emvModule.fetchEmvData(L_55TAGS);
		MyLogger.jLog().e(">>>>55域:" + data55 + "\r\n");
//		((MyApplication) (baseActivity).getApplication()).setIC55Data(data55); // 消费报文

		String dataScript = emvModule.fetchEmvData(L_SCRIPTTAGS);
		MyLogger.jLog().e(">>>>脚本:" + dataScript + "\r\n");
		String revData = emvModule.fetchEmvData(L_REVTAGS);
		MyLogger.jLog().e(">>>>冲正数据:" + revData + "\r\n");
		 
	}

	@Override
	public void onError(EmvTransController arg0, Exception arg1) {
		MyLogger.jLog().e("emv交易失败:" + arg1.getMessage() + "\r\n");
		arg1.printStackTrace();
	}

	@Override
	public void onFallback(EmvTransInfo arg0) throws Exception {
		MyLogger.jLog().e("ic卡交易环境不满足:交易降级..." + "\r\n");
	}

	/**
	 * 当设备要求联机交易时发生
	 */
	@Override
	public void onRequestOnline(EmvTransController controller, EmvTransInfo context) throws Exception {
		int emvResult = context.getEmvrsltCode();
		String resultMsg = null;
		switch (emvResult) {
		case 3:
			resultMsg = "pboc联机";
			break;
		case 15:
			resultMsg = "非接qpboc联机";
			break;
		}
		MyLogger.jLog().e(">>>>请求联机onRequestOnline，执行结果:" + resultMsg + "\r\n");

		int[] emvTags = new int[4];
		emvTags[0] = 0x5a;
		emvTags[1] = 0x5F34;
		emvTags[2] = 0x5f24;
		emvTags[3] = 0x57;

		String data = emvModule.fetchEmvData(emvTags);
		TLVPackage tlv = ISOUtils.newTlvPackage();
		tlv.unpack(ISOUtils.hex2byte(data));
		/**
		 * getString  的tag信息在 emv 包下面的 linkEmvTransInfo信息中有标注说明
		 */
		String cardNo = tlv.getString(0x5a);
		String cardSN = tlv.getString(0x5F34);// 卡序列号，等效于context.getCardSequenceNumber()
		String expiredDate = tlv.getString(0x5F24);// 过期日期,等效于context.getCardExpirationDate()
		String track2 = tlv.getString(0x57); // 二磁道数据，等效于context.getTrack_2_eqv_data()
		if (cardSN == null) {
			cardSN = "000";
		} else {
			cardSN = ISOUtils.padleft(cardSN, 3, '0');
		}
		String serviceCode = "";
		if (null != track2) {
			serviceCode = track2.substring(track2.indexOf('D') + 5, track2.indexOf('D') + 8);
		}
		MyLogger.jLog().e("卡号:" + cardNo + "\r\n");
		MyLogger.jLog().e("卡序列号:" + cardSN + "\r\n");
		MyLogger.jLog().e("卡有效期:" + expiredDate + "\r\n");
		MyLogger.jLog().e("服务码:" + serviceCode + "\r\n");
		MyLogger.jLog().e("二磁道明文:" + track2 + "\r\n");
//		((MyApplication) (baseActivity).getApplication()).setIcCardNum(context.getCardNo());
//		((MyApplication) (baseActivity).getApplication()).setEmvTransInfo(context); // 消费报文

		if (null != track2) {
			n900Device = N900Device.getInstance( );
			K21Swiper swiper = n900Device.getK21Swiper();
			SwipResult swipRslt = swiper.calculateTrackData(track2, null, new WorkingKey(index), SupportMSDAlgorithm.getMSDAlgorithm(encryptAlgorithm));
			MyLogger.jLog().e(">>>>二磁道密文:" + (swipRslt.getSecondTrackData() == null ? null : ISOUtils.hexString(swipRslt.getSecondTrackData())) + "\r\n");
		}
		String data55 = emvModule.fetchEmvData(L_55TAGS);
		MyLogger.jLog().e(">>>>55域:" + data55 + "\r\n");
//		((MyApplication) (baseActivity).getApplication()).setIC55Data(data55); // 消费报文

		String dataScript = emvModule.fetchEmvData(L_SCRIPTTAGS);
		MyLogger.jLog().e(">>>>脚本:" + dataScript + "\r\n");
		String revData = emvModule.fetchEmvData(L_REVTAGS);
		MyLogger.jLog().e(">>>>冲正数据:" + revData + "\r\n");
		// [步骤1]：从该处context中获取ic卡卡片信息后，发送银联8583交易
		SecondIssuanceRequest request = new SecondIssuanceRequest();
		request.setAuthorisationResponseCode("00");// 交易应答码：取自银联8583规范39域值,该参数按交易实际值填充
		// request.setAuthorisationCode(arg0);//授权码：取自银联8583规范38域值,该参数按交易实际值填充
		// request.setIssuerAuthenticationData(arg0);//发卡行认证数据:取自银联8583规范55域0x91值,该参数按交易实际值填充
		// request.setIssuerScriptTemplate1(arg0);//发卡行脚本1：取自银联8583规范55域0x71值,该参数按交易实际值填充
		// request.setIssuerScriptTemplate2(arg0);//发卡行脚本2:取自银联8583规范55域0x72值,该参数按交易实际值填充
		// [步骤2].ic卡联机交易成功或者非接圈存交易，调用二次授权接口，等回调onemvfinished结束流程。
		controller.secondIssuance(request);

		// [并列步骤2].联机交易失败或者非接交易(除圈存外)调用emv结束方法，结束流程。
		// controller.doEmvFinish(true/false);
		// controller.doEmvFinish(true);
	}

	// 多应用卡片会回调该方法进行应用选择
	@Override
	public void onRequestSelectApplication(EmvTransController arg0, EmvTransInfo arg1) throws Exception {
		MyLogger.jLog().e("多应用卡片，要进行应用选择！" + "\r\n");
		Map<byte[], AIDSelect> map = arg1.getAidSelectMap();
		List<String> nameList = new ArrayList<String>();
		List<byte[]> aidList = new ArrayList<byte[]>();

		for (Entry<byte[], AIDSelect> entry : map.entrySet()) {
			nameList.add(entry.getValue().getName());
			aidList.add(entry.getValue().getAid());
			MyLogger.jLog().e("aidName:" + entry.getValue().getName());
			MyLogger.jLog().e("aid:" + entry.getValue().getAid());
		}
		// 默认选择第一个应用
		arg0.selectApplication(aidList.get(0));
	}

	
	@Override
	public void onRequestTransferConfirm(EmvTransController controller, EmvTransInfo arg1) throws Exception {
		MyLogger.jLog().e("交易确认完成" + "\r\n");
		controller.transferConfirm(true);
	}

	/***
	 * 当设备要求app完成一个密码输入过程时发生
     * 若在设备上完成密码输入，则该事件不触发
	 */
	// IM81和N900  N910 会触发，  、ME31不会触发
	@Override
	public void onRequestPinEntry(final EmvTransController emvTransController, EmvTransInfo emvTransInfo) throws Exception {
		if (emvTransInfo.getCardNo() != null) {
			EventBus.getDefault().post(new EventMsg(Const.EvenBUSType.READ_CARD_SUCCSE,emvTransInfo.getCardNo()));
			MyLogger.jLog().e("CardNo为 " +emvTransInfo.getCardNo()+ "\r\n");
			return;
		} else {
			EventBus.getDefault().post(new EventMsg(Const.EvenBUSType.READ_CARD_ERR));
			MyLogger.jLog().e("CardNo为空" + "\r\n");
			return;
		}

//		if (emvTransInfo.getCardNo() != null) {
//			MyLogger.jLog().e("请输入6位的密码..." + "\r\n");
//			doPinInput(emvTransInfo.getCardNo());
//			waitPinInputThreat.waitForRslt();
//			MyLogger.jLog().e("密码:" + (pinBlock == null ? "null" : ISOUtils.hexString(pinBlock)));
//			emvTransController.sendPinInputResult(pinBlock);
//		} else {
//			MyLogger.jLog().e("swipResult为空了！" + "\r\n");
//		}
	}
	/**
	 *  输密码
	 * @throws Exception
	 */
	public void doPinInput(String anc) throws Exception {
		//模拟输入了密码
		Message pinFinishMsg = new Message();
		pinFinishMsg.what = Const.PIN_FINISH;
		pinFinishMsg.obj = new byte[]{(byte) 0xEE,(byte)0xAB,(byte)0x12,(byte)0xEE,(byte)0xAB,0x12} ;
		SimpleTransferListener.getPinEventHandler().sendMessage(pinFinishMsg);
	}

	/**
	 * 是否拦截acctType select事件
	 * 
	 * @return
	 */
	@Override
	public boolean isAccountTypeSelectInterceptor() {
		return true;
	}

	/**
	 * 是否拦截持卡人证件确认事件
	 * 
	 * @return
	 */
	@Override
	public boolean isCardHolderCertConfirmInterceptor() {
		return true;
	}

	/**
	 * 是否拦截电子现金确认事件
	 * 
	 * @return
	 */
	@Override
	public boolean isEcSwitchInterceptor() {
		return false;
	}

	/**
	 * 是否拦截使用外部的序列号处理器
	 * 
	 * @return
	 */
	@Override
	public boolean isTransferSequenceGenerateInterceptor() {
		return true;
	}

	/**
	 * 是否拦截消息显示事件
	 * 
	 * @return
	 */
	@Override
	public boolean isLCDMsgInterceptor() {
		return true;
	}

	/**
	 * 账号类型选择
	 * <p>
	 * 返回int范围
	 * <p>
	 * <ol>
	 * <li>default</li>
	 * <li>savings</li>
	 * <li>Cheque/debit</li>
	 * <li>Credit</li>
	 * </ol>
	 * 
	 * @return 1-4：选择范围， －1：失败
	 */
	@Override
	public int accTypeSelect() {
		return 1;
	}

	/**
	 * 持卡人证件确认
	 * <p>
	 * 
	 * @return true:确认正确， false:确认失败
	 */
	@Override
	public boolean cardHolderCertConfirm(EmvCardholderCertType certType, String certno) {
		return true;
	}

	/**
	 * 电子现金/emv选择
	 * <p>
	 * 交易返回：
	 * <p>
	 * <ul>
	 * <li>1：继续电子现金交易</li>
	 * <li>0：不进行电子现金交易</li>
	 * <li>－1:用户中止</li>
	 * <li>－3:超时</li>
	 * </ul>
	 */
	@Override
	public int ecSwitch() {
		try {
			final WaitThreat waitThreat = new WaitThreat();
			final Builder builder = new Builder(baseActivity);
			builder.setMessage("是否使用电子现金消费？");
			builder.setPositiveButton("是", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
//					SharedPreferencesUtil.setIntParam(baseActivity, "isECSwitch", 1);
					dialog.dismiss();
					isECSwitch = 1;
					waitThreat.notifyThread();
				}
			});
			builder.setNegativeButton("否", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
//					SharedPreferencesUtil.setIntParam(baseActivity, "isECSwitch", 0);
					dialog.dismiss();
					isECSwitch = 0;
					waitThreat.notifyThread();
				}
			});
			baseActivity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					builder.show();
					builder.setCancelable(false);
				}
			});
			waitThreat.waitForRslt();
			// 电子现金消费返回1，否则返回0
			return isECSwitch;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	/**
	 * 流水号加1并返回
	 * @return
	 */
	@Override
	public int incTsc() {
		return 0;
	}

	/**
	 * 显示信息
	 * 
	 * @param title
	 *            标题
	 * @param msg
	 *            消息
	 * @param yesnoShowed
	 *            是否出现yesno
	 * @param waittingTime
	 *            等待时间
	 * @return 如果yesnoShow == true, 返回1 表示确认，返回0表示取消 如果yesnoShow == false,
	 *         返回值无意义
	 */
	@Override
	public int lcdMsg(String title, String msg, boolean yesnoShowed, int waittingTime) {
		return 1;
	}

	// 线程等待、唤醒
	public static class WaitThreat {
		Object syncObj = new Object();

		void waitForRslt() throws InterruptedException {
			synchronized (syncObj) {
				syncObj.wait();
			}
		}

		void notifyThread() {
			synchronized (syncObj) {
				syncObj.notify();
			}
		}
	}

	@Override
	public void onRequestAmountEntry(final EmvTransController controller, EmvTransInfo context) {
		MyLogger.jLog().e("金额回调请求..." + "\r\n");



	}
}
