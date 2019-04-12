package com.wizarpos.emvsample;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iisysgroup.poslib.ISO.GTMS.GtmsHost;
import com.iisysgroup.poslib.ISO.POSVAS.PosvasHost;
import com.iisysgroup.poslib.ISO.common.IsoRefundProcessData;
import com.iisysgroup.poslib.ISO.common.IsoReversalProcessData;
import com.iisysgroup.poslib.TAMS.TamsHost;
import com.iisysgroup.poslib.host.Host;
import com.iisysgroup.poslib.host.HostInteractor;
import com.iisysgroup.poslib.host.dao.PosLibDatabase;
import com.iisysgroup.poslib.host.dao.VasTerminalDataDao;
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
import com.wizarpos.emvsample.parameter.BatchInfo;
import com.wizarpos.emvsample.parameter.TerminalConfig;
import com.wizarpos.emvsample.printer.PrinterException;
import com.wizarpos.emvsample.printer.PrinterHelper;
import com.wizarpos.emvsample.transaction.Nibss;

import java.lang.reflect.Method;
import java.util.Calendar;



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
	public boolean enableContactlessCard = false;
	public boolean promptCardCanRemoved = false;
	public boolean promptOfflineDataAuthSucc = false;
	public boolean resetCardError = false;
	
	public int cardType = -1;
	public boolean msrError = false;
	
	public SmartCardControl contactUserCard;

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
	
	public int printReceipt = 0;
	// 读卡设备信息
	public boolean icInitFlag = false;       // IC卡是否已初始化
	public boolean idleFlag = false;
	// 密码键盘
	public boolean pinpadOpened = false;
	public boolean needClearPinpad = false;
	public boolean refund = false;
	public IsoRefundProcessData isoRefundProcessData = null;
	public IsoReversalProcessData iisoReverSal = null;
   public  int refundAmount = 0;
	public boolean purchaseWithCashBack = true;
	public boolean revarsal = false;
	public boolean withdrawal = false;
	public int reversalAmout;
	public HostInteractor hostInteractor;
	public PosLibDatabase poslibdb = null;


	public static MainApp getInstance()
    {
		if (null == _instance)
		    _instance = new MainApp();
		return _instance;
    }


    @Override
    public void onCreate()
    {
		super.onCreate();
        MultiDex.install(this);
    	try{
			poslibdb = Room.databaseBuilder(this, PosLibDatabase.class, "poslib.db")
					.fallbackToDestructiveMigration()
					.build();
		}catch (Exception e){
    		Log.d("okh", e.toString());
		}
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


		String hostKey = getString(R.string.key_host_type);
		Host host;
		if (sharedPreferences.getString(hostKey, "").equalsIgnoreCase("TAMS")){
			host =  new TamsHost(this);
		}
		else if (sharedPreferences.getString(hostKey, "").equalsIgnoreCase("POSVAS")){
			host =  new PosvasHost(this);
		}
		else  {
			host =  new GtmsHost(this);
		}

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

//		contactlessUserCard = new SmartCardControl(SmartCardControl.CARD_CONTACTLESS);
		loadData();
		initData();

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
					try {
						PrinterHelper.getInstance().printConfiguration(nibssData);
					} catch (PrinterException e) {
						e.printStackTrace();
					}
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
			capkService.createDefaultCAPK();
		}
	}
    
    private void loadData(String tid, String mid, String merchantN)
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
		
		terminalConfig.loadTerminalConfig(tid,mid,merchantN);
		batchInfo.loadBatch();
		if(aidService.getAIDCount() == 0)
		{
			aidService.createDefaultAID();
		}
		
		if(capkService.getCAPKCount() == 0)
		{
			capkService.createDefaultCAPK();
		}
    }


	public void setupNibbsData(String termi,final Nibss.Nibs<String> t) {
		Nibss nibss = new Nibss(this);
		final String tem = termi;
		nibss.configureTerminal(tem, new Nibss.Nibs<Nibss.NIbbsData>() {
			@Override
			public void complete(final Nibss.NIbbsData res) {
				nibssData = res;
				try {
					PrinterHelper.getInstance().printConfiguration(res);
				} catch (PrinterException e) {
					e.printStackTrace();
				}
				new Thread(new Runnable() {
					@Override
					public void run() {
						String infi = new Gson().toJson(res, Nibss.NIbbsData.class);
						ConfigService configService = new ConfigService(db);
						String former = configService.get();
						if(!former.isEmpty()){
							configService.update(infi);
						}else{
							configService.save(infi);
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
    	if(debug)Log.d(APP_TAG, "getErrorCode = " + errorCode  );
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
        Toast.makeText(context, "Configuring please wait", Toast.LENGTH_SHORT).show();

		setupNibbsData(termId,new Nibss.Nibs<String>() {
			@Override
			public void complete(String res) {
//				pd.dismiss();
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
