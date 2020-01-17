package com.wizarpos.emvsample;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.cloudpos.jniinterface.PINPadInterface;
import com.google.gson.Gson;


import com.iisysgroup.poslib.ISO.GTMS.GtmsHost;
import com.iisysgroup.poslib.ISO.GTMS.GtmsKeyProcessor;
import com.iisysgroup.poslib.ISO.POSVAS.PosvasHost;
import com.iisysgroup.poslib.ISO.POSVAS.PosvasKeyProcessor;
import com.iisysgroup.poslib.ISO.common.IsoRefundProcessData;
import com.iisysgroup.poslib.ISO.common.IsoReversalProcessData;
import com.iisysgroup.poslib.host.Host;
import com.iisysgroup.poslib.host.HostInteractor;
import com.iisysgroup.poslib.host.dao.PosLibDatabase;
import com.iisysgroup.poslib.host.entities.KeyHolder;
import com.iisysgroup.poslib.host.entities.VasTerminalData;
import com.itex.richard.payviceconnect.model.DstvModel;
import com.itex.richard.payviceconnect.model.StartimesModel;
import com.wizarpos.emvsample.activity.DataModel;
import com.wizarpos.emvsample.activity.login.Helper;
import com.wizarpos.emvsample.activity.login.securestorage.SecureStorage;
import com.wizarpos.emvsample.card.SmartCardControl;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.db.AIDService;
import com.wizarpos.emvsample.db.AIDTable;
import com.wizarpos.emvsample.db.AdviceService;
import com.wizarpos.emvsample.db.CAPKService;
import com.wizarpos.emvsample.db.CAPKTable;
import com.wizarpos.emvsample.db.ConfigService;
import com.wizarpos.emvsample.db.DatabaseOpenHelper;
import com.wizarpos.emvsample.db.EodModel;
import com.wizarpos.emvsample.db.ExceptionFileService;
import com.wizarpos.emvsample.db.ExceptionFileTable;
import com.wizarpos.emvsample.db.RevokedCAPKService;
import com.wizarpos.emvsample.db.RevokedCAPKTable;
import com.wizarpos.emvsample.db.TransDetailInfo;
import com.wizarpos.emvsample.db.TransDetailService;
import com.wizarpos.emvsample.db.detailed.EodDoa;
import com.wizarpos.emvsample.db.detailed.TransDataBase;
import com.wizarpos.emvsample.db.detailed.TransactionDataDoa;
import com.wizarpos.emvsample.db.detailed.VasTransactionDoa;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.AirtimeDoa;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.CableTvDoa;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.DiscoDoa;
import com.wizarpos.emvsample.db.detailed.vas.vas_doa.TransferDoa;
import com.wizarpos.emvsample.parameter.BatchInfo;
import com.wizarpos.emvsample.parameter.TerminalConfig;
import com.wizarpos.emvsample.services.helper.activity.util.Models;
import com.wizarpos.emvsample.transaction.Nibss;
import com.wizarpos.jni.PinPadInterface;
import com.wizarpos.util.StringUtil;
import com.wizarpos.util.TransactionModel;

import java.lang.reflect.Method;
import java.util.Calendar;

import static com.wizarpos.emvsample.activity.FuncActivity.appState;


public class MainApp extends Application implements Constants
{
	//NIbbs data to use
	public Nibss.NIbbsData nibssData;
	public Nibss.VasData vasData;

	public String clearPin;
	public boolean balanceEnc = false;

	public boolean goneOnline = false;

	//Handler for callhome
	Handler callHomeHandler = new Handler();

	public  EodModel currEod = null;
	public static TransactionModel transactionModel;

	public DataModel.DataSubscriptionDetails dataSubscriptionDetails= null;

	public int pinpadType = PINPAD_CUSTOM_UI;  // PINPAD_SYSTEM_UI



	//Room DB
	String TAG = "app_pit_ogl";
	private byte tranType = TRAN_GOODS;
	private byte paramType = -1;   // 参数设置类型
	private byte processState = 0;  // 处理阶段
	private byte state = 0;         //
	private int  errorCode = 0;
	private byte commState = COMM_DISCONNECTED;
	private SharedPreferences terminalPref;
	private SharedPreferences batchPref;
	private Calendar mCalendar;

	private static MainApp _instance;

	public DatabaseOpenHelper dbOpenHelper = null;
	public SQLiteDatabase db = null;

	public TransDetailInfo trans = new TransDetailInfo();
	public boolean needCard = false;
	public boolean isPurchase = false;
	public boolean enableContactlessCard = false;
	public boolean promptCardCanRemoved = false;
	public boolean promptOfflineDataAuthSucc = false;
	public boolean resetCardError = false;

	public int cardType = -1;
	public boolean msrError = false;

	public SmartCardControl contactUserCard;
	public int logo =0;
	public String product ="";
	public String dataAmount ="";
	public String clearPinKey ="";
	public String clearSessionKey ="";


	public String accountName ;

	public String accountNumber;
	public String recivingBank ;
	public boolean isWithdrawal = false;




	public boolean acceptMSR = true;
	public boolean acceptContactCard = true;
	public boolean acceptContactlessCard = true;
	public boolean promptCardIC = false;

	public byte recordType = 0x00;
	public BatchInfo batchInfo;
	public TerminalConfig terminalConfig;

	public boolean emvParamLoadFlag = false;
	public boolean emvParamChanged = false;

	public TransDetailService transDetailService;
	public AdviceService adviceService;
	public AIDService aidService;
	public CAPKService capkService;
	public RevokedCAPKService revokedCAPKService;
	public ExceptionFileService exceptionFileService;
	public int aidNumber = 0;
	public byte[] aidList = new byte[300];
	public byte pollCardState = 0;

	public AIDTable[] aids;
	public int aidsIndex = 0;
	public boolean aidsInfoChanged = false;

	public CAPKTable[] capks;
	public int capksIndex = 0;
	public boolean capkInfoChanged = false;
	public String failedCAPKInfo = "";

	public ExceptionFileTable[] exceptionFiles;
	public int exceptionFilesIndex = 0;
	public boolean exceptionFileInfoChanged = false;

	public RevokedCAPKTable[] revokedCapks;
	public int revokedCapksIndex = 0;
	public boolean revokedCapkInfoChanged = false;

	public int currentYear;
	public int currentMonth;
	public int currentDay;
	public int currentHour;
	public int currentMinute;
	public int currentSecond;
	public String stringCipherNewUserKey=null;
	public String PlainKeyInjected=null;

	public int printReceipt = 0;
	public int printVasReceipt = 0;
	// 读卡设备信息
	public boolean icInitFlag = false;       // IC卡是否已初始化
	public boolean idleFlag = false;
	// 密码键盘
	public boolean pinpadOpened = false;
	public boolean needClearPinpad = false;

	//	 Services
	public boolean refund = false;
	public IsoRefundProcessData isoRefundProcessData = null;
	public IsoReversalProcessData iisoReverSal = null;
	public  int refundAmount = 0;
	public boolean purchaseWithCashBack = false;
	public boolean revarsal = false;
	public boolean transfer = false;
	public boolean withdrawal = false;
	public boolean airtime = false;
	public boolean data = false;
	public int reversalAmout;
	public boolean electricityBills=false;
	public Models.GeneralElectricityDetails generalElectricityDetails;
	public boolean cableTv=false;
	public boolean startimes=false;
	public boolean gotv=false;
	public boolean dstv=false;
	public String starTimesAmount="";
	public String multichoiceAmount="";
	public String multichoiceAccount="";
	public String vasTransactionstatus ="DECLINED";
	public boolean isWallet =false;
	public boolean isVas=false;
	public boolean isTransfer =false;
	public StartimesModel.payRequest startimesPayRequest;
	public DstvModel.PayDetails dstvPayRequest;
	public Bitmap bankLogo;




	public   HostInteractor hostInteractor;
	public PosLibDatabase poslibdb = null;


	public static MainApp getInstance()
	{
		if (_instance == null) {
			_instance = new MainApp();
			Log.d("Main App", " >>>> _instance" );

		}
		return _instance;
	}

//	val TransactionDb by lazy {
//	AllTransDataBase.getInstance(this).getTransactionDataDoa();
//}
//
//
//	val vasDb by lazy {
//	AllTransDataBase.getInstance(this).getVasDataBase();
//}
//
//	val eodDb by lazy {
//	AllTransDataBase.getInstance(this).getEodDataBase();
//}

//
	public EodDoa eodDb;

	public VasTransactionDoa vasDb;
	public TransactionDataDoa transactionDb;


	public AirtimeDoa airtimeDb;

	public CableTvDoa cableTvDb;
	public DiscoDoa discoDb;
	public TransferDoa transferDb;



	private Boolean isKeyInjected;
	private Boolean isPrepped;





	@Override
	public void onCreate()
	{
		super.onCreate();
		MultiDex.install(this);





		//decrypt pin key with master key to get clear pin key and then encrypt with the static master key
//		KeyHolder res=mainApp.nibssData.getKeyHolder();
//		String clearmasterKey;
//		String clearPinKey = null;
//		String encryptedPinKey;
//
//		Log.i("complete", "getPinKey: "+  res.getPinKey());
//		Log.i("complete", "getMasterKey: "+  res.getMasterKey());
//		Log.i("complete", "getSessionKey: "+  res.getSessionKey());
//		clearmasterKey = PosvasKeyProcessor.getMasterKey(res.getMasterKey(), res.isTestPlatform());
//		encryptedPinKey = res.getPinKey();
////                Log.d("complete", "masterKey: "+res.getKeyHolder().getMasterKey());
//		Log.d("complete", "clearmasterKey: "+clearmasterKey);
//		try {
//
//			clearPinKey =PosvasKeyProcessor.decryptKey(res.getPinKey(),clearmasterKey);
//			Log.d("complete", "clearPinKey : "+ clearPinKey);
//
//		}catch (Throwable e){
//
//		}
//
//		String stringCipherNewUserKey = StringUtil.tripleDesEncrypt("81847D3F139805A564CD6C28E52624B7",clearPinKey);
//
//		Log.d("complete", "stringCipherNewUserKey : "+ stringCipherNewUserKey);
//
//
//		byte arryCipherNewUserKey[] =StringUtil.StrToHexByte(stringCipherNewUserKey);
//
//		Log.d("complete", "arryCipherNewUserKey : "+ arryCipherNewUserKey);
//
//		PinPadInterface.updateUserKey(3,0,arryCipherNewUserKey,16);


		eodDb =  TransDataBase.Companion.getInstance(this).getEodDataBase();
		vasDb =  TransDataBase.Companion.getInstance(this).getVasDataBase();
		transactionDb= TransDataBase.Companion.getInstance(this).getTransactionDataDoa();
		airtimeDb =  TransDataBase.Companion.getInstance(this).getAirtimeTable();
		cableTvDb =  TransDataBase.Companion.getInstance(this).getCableTable();
		discoDb= TransDataBase.Companion.getInstance(this).getDiscoTable();
		transferDb= TransDataBase.Companion.getInstance(this).getTransferTable();
//
//



		try{
			poslibdb = Room.databaseBuilder(this, PosLibDatabase.class, "poslib.db")
					.fallbackToDestructiveMigration()
					.build();
			Log.d("Main App", " >>>>okh  Successfully setup  Poslibdb" );

		}catch (Exception e){
			Log.d(" >>>>okh   exception for settingUp db ", e.toString());
		}
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		transactionModel = null;
		String hostKey = getString(R.string.key_host_type);
		Host host;
//		if (sharedPreferences.getString(hostKey, "").equalsIgnoreCase("TAMS")){
//			host =  new TamsHost(this);
//		}
//		else if (sharedPreferences.getString(hostKey, "").equalsIgnoreCase("POSVAS")){
		host =  new PosvasHost(this);
//		host =  new GtmsHost(this);
//		}
//		else  {
//			host =  new GtmsHost(this);
//		}

		hostInteractor = HostInteractor.getInstance(host);


		if (null == _instance)
			_instance = MainApp.this;


		try {
			SecureStorage.init(this)
					.setEncryptionMethod(SecureStorage.Builder.EncryptionMethod.ENCRYPTED)
					.setPassword("4321dcbA")
					.setStoreName(TAG)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try{
			contactUserCard = new com.wizarpos.emvsample.card.SmartCardControl(SmartCardControl.CARD_CONTACT, 0);
		}catch (Exception e){

		}
//		Log.d("Main App", " >>>>okh  Successfully setup  Poslibdb = " + new Gson().toJson(poslibdb) );


//		contactlessUserCard = new SmartCardControl(SmartCardControl.CARD_CONTACTLESS);
		loadData();
		initData();



		isKeyInjected=SecureStorage.retrieve(Helper.IS_KEY_INJECTED,false);
		isPrepped=SecureStorage.retrieve(Helper.IS_PREPPING,false);



		new Thread(new Runnable() {
			@Override
			public void run() {
				ConfigService configService = new ConfigService(db);
				String configString = configService.get();
				if(configService == null || configString.isEmpty()){
					nibssData = null;
					vasData = null;
				}else{
					Log.i("okh",configString);
					nibssData = new Gson().fromJson(configString,Nibss.NIbbsData.class);
//					try {
//						PrinterHelper.getInstance().printConfiguration(nibssData);
//					} catch (PrinterException e) {
//						e.printStackTrace();
//					}
				}
			}
		}).start();

		//显示上下导航栏 全系统有效
		try {
			@SuppressLint("WrongConstant") Object service = getSystemService("statusbar");
			Class statusBarManager = null;
			statusBarManager = Class.forName("android.app.StatusBarManager");
			Method method = statusBarManager.getMethod("hideBars", int.class);
			method.invoke(service,0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void loadData()
	{
		//Setup NibbsData


		dbOpenHelper = new DatabaseOpenHelper(getBaseContext());
		db = dbOpenHelper.getWritableDatabase();

		terminalPref  = getSharedPreferences("terminalConfig", Context.MODE_PRIVATE);
		terminalConfig = new TerminalConfig(terminalPref);

		batchPref     = getSharedPreferences("batchInfo", Context.MODE_PRIVATE);
		batchInfo = new BatchInfo(batchPref);

		transDetailService = new TransDetailService(getBaseContext());
		adviceService = new AdviceService(getBaseContext());
		aidService = new AIDService(db);
		capkService = new CAPKService(db);
		revokedCAPKService = new RevokedCAPKService(db);
		exceptionFileService = new ExceptionFileService(db);

		terminalConfig.loadTerminalConfig();
		batchInfo.loadBatch();
		if(aidService.getAIDCount() == 0)
		{
			aidService.createDefaultAID();
		}

		if(capkService.getCAPKCount() == 0)
		{
			capkService.createDefaultCapk();
		}
	}

//	private void loadData(String tid, String mid, String merchantN)
//	{
//		//Setup NibbsData
//
//
//		dbOpenHelper = new DatabaseOpenHelper(getBaseContext());
//		db = dbOpenHelper.getWritableDatabase();
//
//		terminalPref  = getSharedPreferences("terminalConfig", Context.MODE_PRIVATE);
//		terminalConfig = new TerminalConfig(terminalPref);
//
//		batchPref     = getSharedPreferences("batchInfo", Context.MODE_PRIVATE);
//		batchInfo = new BatchInfo(batchPref);
//
//		transDetailService = new TransDetailService(getBaseContext());
//		adviceService = new AdviceService(getBaseContext());
//		aidService = new AIDService(db);
//		capkService = new CAPKService(db);
//		revokedCAPKService = new RevokedCAPKService(db);
//		exceptionFileService = new ExceptionFileService(db);
//
//		terminalConfig.loadTerminalConfig(tid,mid,merchantN);
//		batchInfo.loadBatch();
//		if(aidService.getAIDCount() == 0)
//		{
//			aidService.createDefaultAID();
//		}
//
//		if(capkService.getCAPKCount() == 0)
//		{
//			capkService.createDefaultCapk();
//		}
//	}


	public void setupNibbsData(String termi,final Nibss.Nibs<String> t) {
		Nibss nibss = new Nibss(this);
		final String tem = termi;
		nibss.configureTerminal(tem, new Nibss.Nibs<Nibss.NIbbsData>() {
			@Override
			public void complete(final Nibss.NIbbsData res) {
				nibssData = res;
//				try {
//					PrinterHelper.getInstance().printConfiguration(res);
//				} catch (PrinterException e) {
//					e.printStackTrace();
//				}


				new Thread(new Runnable() {
					@Override
					public void run() {
						String infi = new Gson().toJson(res, Nibss.NIbbsData.class);

						try{
							ConfigService configService = new ConfigService(db);
							String former = configService.get();
							if(!former.isEmpty()){
								configService.update(infi);
							}else{
								configService.save(infi);
							}
						}catch (Exception e){

						}

					}
				}).start();
			}

			@Override
			public void error(String e) {
				nibssData = null;
				t.error("false");
			}
		});
	}








	public void initData()
	{
		tranType = TRAN_GOODS;    // 交易类型
		paramType = -1;
		processState = 0;  // 处理阶段
		state = 0;         //
		errorCode = 0;
		cardType = -1;
		idleFlag = false;
		promptCardCanRemoved = false;
		promptOfflineDataAuthSucc = false;
		printReceipt = 0;
		printVasReceipt = 0;
		resetCardError = false;

		trans.init();
		trans.setTrace(terminalConfig.getTrace());


	}

	// tranType
	public byte getTranType()
	{
		return tranType;
	}

	public void setTranType(byte tranType)
	{
		this.tranType = tranType;
	}

	// paramType
	public byte getParamType()
	{
		return paramType;
	}

	public void setParamType(byte paramState)
	{
		this.paramType = paramState;
	}

	// processState
	public byte getProcessState()
	{
		return processState;
	}

	public void setProcessState(byte processState)
	{
		this.processState = processState;
	}

	// state
	public byte getState()
	{
		return state;
	}

	public void setState(byte state)
	{
		this.state = state;
	}

	// errorCode
	public int getErrorCode()
	{
		if(debug)Log.d(APP_TAG, "MainApp Method  getErrorCode = " + errorCode  );
		return errorCode;
	}

	public void setErrorCode(int errorCode)
	{
		if(debug)Log.d(APP_TAG, "setErrorCode = " + errorCode);
		this.errorCode = errorCode;
	}

	// commState
	public byte getCommState()
	{
		return commState;
	}

	public void setCommState(byte state)
	{
		commState = state;
	}

	public void getCurrentDateTime()
	{
		long time = System.currentTimeMillis();
		/*透过Calendar对象来取得小时与分钟*/
		mCalendar = Calendar.getInstance();
		mCalendar.setTimeInMillis(time);
		currentYear = mCalendar.get(Calendar.YEAR);
		currentMonth = mCalendar.get(Calendar.MONTH)+1;
		currentDay = mCalendar.get(Calendar.DAY_OF_MONTH);
		currentHour = mCalendar.get(Calendar.HOUR);
		if(mCalendar.get(Calendar.AM_PM) == Calendar.PM)
		{
			currentHour += 12;
		}
		currentMinute = mCalendar.get(Calendar.MINUTE);
		currentSecond = mCalendar.get(Calendar.SECOND);
	}

	public void prep(String termId, final Context context, final Nibss.Nibs<String> callback){
//		final ProgressDialog pd = new ProgressDialog(context, R.style.AlertDialogCustom);
//		pd.setMessage("Preping please wait");
//		pd.show();

		SecureStorage.store(Helper.IS_PREPPING,true);

		Toast.makeText(context, "Configuring please wait", Toast.LENGTH_SHORT).show();

		setupNibbsData(termId,new Nibss.Nibs<String>() {
			@Override
			public void complete(String res) {
//				pd.dismiss();
//				updateKey();

//				SecureStorage.store(Helper.IS_PREPPING,false);

				Toast.makeText(context, "Succesfully Configured", Toast.LENGTH_SHORT).show();
				callback.complete("Success");

			}

			@Override
			public void error(String e) {
				//pd.dismiss();
				Toast.makeText(context, "Not Configured...Please Retry", Toast.LENGTH_SHORT).show();
				callback.error(e);
			}
		});
	}



	private void updateKey(){


		Log.i(">>>> complete pinblock1", "isKeyInjected: " + isKeyInjected);
		Log.i(">>>> complete pinblock1", "isPrepped: " + isPrepped);



		if(isKeyInjected && isPrepped) {


			KeyHolder res = appState.nibssData.getKeyHolder();
			String clearmasterKey;
			String clearPinKey = null;
			String encryptedPinKey;

			Log.i(">>>> complete pinblock1", "getPinKey: " + res.getPinKey());
			Log.i(">>>> complete pinblock1", "getMasterKey: " + res.getMasterKey());
			Log.i(">>>> complete pinblock1", "getSessionKey: " + res.getSessionKey());
			clearmasterKey = GtmsKeyProcessor.getMasterKey(res.getMasterKey(), res.isTestPlatform());
			encryptedPinKey = res.getPinKey();
			Log.d(">>>> complete", "clearmasterKey: " + clearmasterKey);
			try {


//				{"tid":"20331L14","mid":"203315000001987","merchantName":"ITEX INTERGRATED SER   LA           LANG","sessionKey":"6E3101202A2331701AC845BFEF611C9E","pinKey":"1692E0C189E63B7A34266D201F751C94","masterKey":"731C079EF1C8F8198AB562B08675C151","currencyCode":"566","countryCode":"566","mcc":"8061"}


				clearPinKey = GtmsKeyProcessor.decryptKey(res.getPinKey(), clearmasterKey);


//					appState.clearPinKey = clearPinKey;
//
//					SecureStorage.store(Helper.CLEAR_PIN_KEY,clearPinKey);
				VasTerminalData vasTerminalDetails = new Gson().fromJson(SecureStorage.retrieve(Helper.VAS_COMMUNICATOR,""), VasTerminalData.class);


				appState.clearPinKey = vasTerminalDetails.getPinKey();

				SecureStorage.store(Helper.CLEAR_PIN_KEY,vasTerminalDetails.getPinKey());



				String clearSessionKey = GtmsKeyProcessor.decryptKey(res.getSessionKey(), clearmasterKey);
				Log.d(">>>> complete pinblock1", "clearSessionKey : " + clearSessionKey);

//					appState.clearSessionKey = clearSessionKey;
//					SecureStorage.store(Helper.CLEAR_SESSION_KEY,clearSessionKey);

				appState.clearSessionKey = vasTerminalDetails.getSessionKey();
				SecureStorage.store(Helper.CLEAR_SESSION_KEY,vasTerminalDetails.getSessionKey());


//					Log.d(">>>> complete pinblock1", "clearPinKey : " + clearPinKey);
				Log.d(">>>> complete pinblock1", "appState.clearPinKey  : " + appState.clearPinKey );
				Log.d(">>>> complete pinblock1", "appState.clearSessionKey : " + appState.clearSessionKey);


			} catch (Throwable e) {

			}
//				String stringCipherNewUserKey = StringUtil.tripleDesEncrypt("31313131313131313232323232323232", clearPinKey);

			String stringCipherNewUserKey = StringUtil.tripleDesEncrypt("31313131313131313232323232323232", appState.clearPinKey);

			appState.stringCipherNewUserKey = stringCipherNewUserKey;


			Log.d(">>>> complete", "stringCipherNewUserKey : " + appState.stringCipherNewUserKey);
			Log.d(">>>> complete", "static key : " + appState.PlainKeyInjected);


			byte arryCipherNewUserKey[] = StringUtil.StrToHexByte(stringCipherNewUserKey);

			Log.d(">>>> complete", "arryCipherNewUserKey : " + arryCipherNewUserKey);

			PINPadInterface.open();
			int value = com.cloudpos.jniinterface.PINPadInterface.updateUserKey(1, 0, arryCipherNewUserKey, 16);

			Log.d(">>>>>> pinblock1 PinPadInterface.updateUserKey ", String.valueOf(value));


			byte[] checkvalue = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

			Log.d(">>>>>> pinblock1  complete pinblock1", "byte of masterkey : " + checkvalue);

			String checkvalueString = StringUtil.bytes2HexString(checkvalue);

			Log.d(">>>>>> pinblock1  complete pinblock1", "byte of string checkvalueString  : " + checkvalueString);

			String outcome = StringUtil.tripleDesEncrypt(clearPinKey, checkvalueString);


			Log.d(">>>> complete pinblock1", "checkvalue  : " + outcome);
			SecureStorage.store(Helper.IS_KEY_INJECTED,true);

			SecureStorage.store(Helper.IS_PREPPING,false);



			PINPadInterface.close();

		}
	}





//	public void StartStopCallHome(boolean startStop){
//		 final int INTERVAL = 1000 * 60 * 10; //10 minutes
//         Log.i("okh", "Call was called with " + startStop);
//		final Runnable mHandlerTask = new Runnable()
//		{
//			@Override
//			public void run() {
//				callHome(new Nibss.Nibs<Boolean>() {
//					@Override
//					public void complete(Boolean res) {
//						if(res){
//							Log.i("okh", "Callhome sucessfull");
//						}
//						else {
//							Log.i("okh", "Callhome not sucessfull");
//						}
//					}
//
//					@Override
//					public void error(String e) {
//						Log.i("okh", e);
//					}
//				});
//			}
//		};
//		callHomeHandler.postDelayed(mHandlerTask, INTERVAL);
//
//		   if(startStop){
//			   mHandlerTask.run();
//		   }else{
//			   callHomeHandler.removeCallbacks(mHandlerTask);
//		   }
//
//
//
//
//
//	}

//	private  void callHome(final Nibss.Nibs<Boolean> result){
//    		Nibss nibss = new Nibss(this);
//    		if(nibssData == null){
//    			result.error("Terminal not prep");
//			}else{
//    			nibss.callHome(this, nibssData.getConnectionData(), nibssData.getKeyHolder(), nibssData.getConfigData(), new Nibss.Nibs<Boolean>() {
//					@Override
//					public void complete(Boolean res) {
//						result.complete(res);
//					}
//
//					@Override
//					public void error(String e) {
//						result.error(e);
//					}
//				});
//			}
//	}



}
