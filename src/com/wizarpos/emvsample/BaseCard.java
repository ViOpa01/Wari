package com.wizarpos.emvsample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wizarpos.emvsample.card.SmartCardControl;
import com.wizarpos.emvsample.constant.Constants;
import com.wizarpos.emvsample.db.AIDService;
import com.wizarpos.emvsample.db.AIDTable;
import com.wizarpos.emvsample.db.AdviceService;
import com.wizarpos.emvsample.db.CAPKService;
import com.wizarpos.emvsample.db.CAPKTable;
import com.wizarpos.emvsample.db.DatabaseOpenHelper;
import com.wizarpos.emvsample.db.ExceptionFileService;
import com.wizarpos.emvsample.db.ExceptionFileTable;
import com.wizarpos.emvsample.db.RevokedCAPKService;
import com.wizarpos.emvsample.db.RevokedCAPKTable;
import com.wizarpos.emvsample.db.TransDetailInfo;
import com.wizarpos.emvsample.db.TransDetailService;
import com.wizarpos.emvsample.parameter.BatchInfo;
import com.wizarpos.emvsample.parameter.TerminalConfig;

import java.lang.reflect.Method;
import java.util.Calendar;

public class BaseCard implements Constants
{
	public Activity appState;
    private byte tranType = TRAN_GOODS;
    private byte paramType = -1;   // 参数设置类型
	private byte processState = 0;  // 处理阶段
	private byte state = 0;         // 
    private int  errorCode = 0;
    private byte commState = COMM_DISCONNECTED;
    private SharedPreferences terminalPref; 
    private SharedPreferences batchPref;  
    private Calendar mCalendar;
    
    private static BaseCard _instance;

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
	public int printVasReceipt = 0;
	// 读卡设备信息
	public boolean icInitFlag = false;       // IC卡是否已初始化
	public boolean idleFlag = false;
	// 密码键盘
	public boolean pinpadOpened = false;
	public boolean needClearPinpad = false;
	
    public static BaseCard getInstance()
    {
		if (null == _instance)
		    _instance = new BaseCard();
		return _instance;
    }


    public void BaseCardonCreate()
    {
		if (null == _instance)
		    _instance = BaseCard.this;
		contactUserCard = new SmartCardControl(SmartCardControl.CARD_CONTACT, 0);
//		contactlessUserCard = new SmartCardControl(SmartCardControl.CARD_CONTACTLESS);
		loadData();
		initData();


		//显示上下导航栏 全系统有效
		try {
			@SuppressLint("WrongConstant") Object service = appState.getSystemService("statusbar");
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
    	dbOpenHelper = new DatabaseOpenHelper(appState);
		db = dbOpenHelper.getWritableDatabase();
		
    	terminalPref  = appState.getSharedPreferences("terminalConfig", Context.MODE_PRIVATE);
		terminalConfig = new TerminalConfig(terminalPref);
		
	    batchPref     = appState.getSharedPreferences("batchInfo", Context.MODE_PRIVATE);
	    batchInfo = new BatchInfo(batchPref);
		
		transDetailService = new TransDetailService(appState);
		adviceService = new AdviceService(appState);
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
}
