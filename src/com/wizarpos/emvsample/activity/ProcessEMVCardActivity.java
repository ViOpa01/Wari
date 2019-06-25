package com.wizarpos.emvsample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wizarpos.emvsample.R;
import com.wizarpos.emvsample.transaction.TransDefine;
import com.wizarpos.util.AppUtil;
import com.wizarpos.util.ByteUtil;
import com.wizarpos.util.StringUtil;

import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_candidate_list;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_tag_data;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_get_tag_list_data;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_is_need_advice;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_is_need_signature;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_is_tag_present;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_process_next;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_currency_symbol;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_kernel_type;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_online_pin_entered;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_online_result;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_other_amount;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_tag_data;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_trans_amount;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_set_trans_type;
import static com.cloudpos.jniinterface.EMVJNIInterface.emv_trans_initialize;

public class ProcessEMVCardActivity extends FuncActivity
{
	private int defaultTagList[] = {    0x57,
										0x5A,
										0x5F20,
										0x5F24,
										0x5F25,
										0x5F28,
										0x5F2A,
										0x5F34,
										0x82,
										0x84,
										0x8A,
										0x8E,
										0x95,
										0x9A,
										0x9B,
										0x9C,
										0x9F01,
										0x9F02,
										0x9F03,
										0x9F07,
										0x9F09,
										0x9F0D,
										0x9F0E,
										0x9F0F,
										0x9F10,
										0x9F15,
										0x9F16,
										0x9F1A,
										0x9F1C,
										0x9F1E,
										0x9F21,
										0x9F26,
										0x9F27,
										0x9F33,
										0x9F34,
										0x9F35,
										0x9F36,
										0x9F37,
										0x9F39,
										0x9F41,
										0x9F4C,
										0x9F5D,
										0x9F63,
										0x9F66,
										0x9F6C,
										0x9F74,
										0xDF31   };


	private int nibsTag[] = { 0x9F1A ,0x82 , 0x5F2A , 0x9F02 , 0x9C, 0x9A, 0x95, 0x9F36 , 0x9F37 ,
	0x9F10 , 0x9F34 , 0x9F35, 0x5F34, 0x9F33, 0x9F27 , 0x9F26};

/*	private int confirmTagList[] = {	0x9F1C,
										0x9F27,  // Cryptogram Information Data
										0x95,    // Terminal Verification Results
										0x9B,    // TSI
										0x9F26,
										0x9F4C,
										0x9F74,
										0xDF31  // Issuer Script Results
								   };*/
	
	private TextView textTitle  = null;
	private Button   buttonBack = null;
    private Button   buttonMore = null;
    
	private TextView textTransType = null;
	private TextView textLine1 = null;
	
    private Thread mEMVThread = null;
    private Thread mEMVProcessNextThread = null;
    private Thread mEMVProcessNextThread2 = null;

	@Override
	public void handleMessageSafe(Message msg) {
		/*这里是处理信息的方法*/
		switch (msg.what)
		{
		case EMV_PROCESS_NEXT_COMPLETED_NOTIFIER:
			if(debug)Log.d(APP_TAG, "EMV_PROCESS_NEXT_COMPLETED_NOTIFIER, emvStatus = " + appState.trans.getEMVStatus() + ", emvRetCode = " + appState.trans.getEMVRetCode());
			byte[] tagData;
			int tagDataLength = 0;
			switch (appState.trans.getEMVStatus())
			{
			case STATUS_CONTINUE:
				switch (appState.trans.getEMVRetCode())
				{
				case EMV_CANDIDATE_LIST:
					appState.aidNumber = emv_get_candidate_list(appState.aidList, appState.aidList.length);

					selectEMVAppList();
					break;
				case EMV_APP_SELECTED:
					if( appState.getTranType() == QUERY_CARD_RECORD || appState.trans.getTransAmount() > 0)
					{
						mEMVProcessNextThread = new EMVProcessNextThread();
						mEMVProcessNextThread.start();
					}
					else{
						inputAmount();
					}
					break;
				case EMV_READ_APP_DATA:
					if(emv_is_tag_present(0x9F79) >= 0 )
					{
						tagData = new byte[6];
						emv_get_tag_data(0x9F79, tagData, 6);
						appState.trans.setECBalance(ByteUtil.bcdToInt(tagData));
					}

					tagData = new byte[100];
					if(emv_is_tag_present(0x5A) >= 0)
					{
						tagDataLength = emv_get_tag_data(0x5A, tagData, tagData.length);
						appState.trans.setPAN(StringUtil.toString(AppUtil.removeTailF(ByteUtil.bcdToAscii(tagData, 0, tagDataLength))));
					}
					// Track2
					if( emv_is_tag_present(0x57) >= 0)
					{
						tagDataLength = emv_get_tag_data(0x57, tagData, tagData.length);
						appState.trans.setTrack2Data(StringUtil.toString(AppUtil.removeTailF(ByteUtil.bcdToAscii(tagData, 0, tagDataLength))));
					}
					// CSN
					if( emv_is_tag_present(0x5F34) >= 0)
					{
						tagDataLength = emv_get_tag_data(0x5F34, tagData, tagData.length);
						appState.trans.setCSN(tagData[0]);
					}
					// Expiry
					if( emv_is_tag_present(0x5F24) >= 0)
					{
						tagDataLength = emv_get_tag_data(0x5F24, tagData, tagData.length);
						appState.trans.setExpiry(StringUtil.toHexString(tagData, 0, 3, false).substring(0, 4));
					}
					//confirmCard();
					Toast.makeText(this, "Please Enter Pin", Toast.LENGTH_SHORT).show();
					mEMVProcessNextThread = new EMVProcessNextThread();
					mEMVProcessNextThread.start();

					break;
				case EMV_DATA_AUTH:
					byte[] TSI = new byte[2];
					byte[] TVR = new byte[5];
					emv_get_tag_data(0x9B, TSI, 2); // TSI
					emv_get_tag_data(0x95, TVR, 5); // TVR
					if(   (TSI[0] & (byte)0x80) == (byte)0x80
						&& (TVR[0] & (byte)0x40) == (byte)0x00
						&& (TVR[0] & (byte)0x08) == (byte)0x00
						&& (TVR[0] & (byte)0x04) == (byte)0x00
						)
					{
						appState.promptOfflineDataAuthSucc = true;
					}

					Toast.makeText(this, "Please Enter Pin", Toast.LENGTH_SHORT).show();
					mEMVProcessNextThread = new EMVProcessNextThread();
					mEMVProcessNextThread.start();
					break;
				case EMV_OFFLINE_PIN:
					textLine1.setText("PLEASE INPUT PIN ON THE PINPAD");
					mEMVProcessNextThread = new EMVProcessNextThread();
					mEMVProcessNextThread.start();
					Log.d("okh", "offline pin");
					break;

				case EMV_ONLINE_ENC_PIN:
					inputPIN();
//					mEMVProcessNextThread = new EMVProcessNextThread();
//					mEMVProcessNextThread.start();
					Log.d("okh", "online pin");
					//appState.trans.setEMVOnlineFlag(true);
					break;
				case EMV_PIN_BYPASS_CONFIRM:
					confirmBypassPin();
					Log.d("okh", "confirm");
					break;
				case EMV_PROCESS_ONLINE:
					Log.d("okh", "process online");
					getEMVCardInfo();
					appState.trans.setEMVOnlineFlag(true);
						processOnline();
//                    textLine1.setText("PLEASE INPUT PIN ON THE PINPAD");
//                    mEMVProcessNextThread = new EMVProcessNextThread();
//                    mEMVProcessNextThread.start();
					break;
				default:
					mEMVProcessNextThread = new EMVProcessNextThread();
					mEMVProcessNextThread.start();
					break;
				}
				break;
			case STATUS_COMPLETION:
				appState.terminalConfig.incTrace();
				appState.trans.setNeedSignature(emv_is_need_signature());

				tagData = new byte[50];
				if( emv_is_tag_present(0x95) >= 0)
				{
					tagDataLength = emv_get_tag_data(0x95, tagData, tagData.length);
					appState.terminalConfig.setLastTVR(StringUtil.toHexString(tagData, 0, tagDataLength, false));
				}
				if( emv_is_tag_present(0x9B) >= 0)
				{
					tagDataLength = emv_get_tag_data(0x9B, tagData, tagData.length);
					appState.terminalConfig.setLastTSI(StringUtil.toHexString(tagData, 0, tagDataLength, false));
				}

				//getEMVCardInfo();
				if ((TransDefine.transInfo[appState.getTranType()].flag & T_NOCAPTURE) == 0)
				{
					if( appState.trans.getEMVRetCode() == APPROVE_OFFLINE )
					{
						if(appState.terminalConfig.getUploadType() == 0)
						{
							if(   appState.trans.getEMVOnlineFlag() == true
								&& appState.trans.getEMVOnlineResult() == ONLINE_FAIL
								)
							{
								saveAdvice();
							}
							offlineSuccess();
						}
						else{
							// 需判断是否联机
							if(   appState.trans.getEMVOnlineFlag() == true
								&& appState.trans.getEMVOnlineResult() == ONLINE_FAIL
								)
							{
								// Reversal
								appState.setProcessState(PROCESS_REVERSAL);
								processOnline();
							}
							else{
								// Confirm
								appState.setProcessState(PROCESS_CONFIMATION);
								getEMVCardInfo();
								processOnline();
							}
							return;
						}

					}
					else if(appState.trans.getEMVRetCode() == APPROVE_ONLINE)
					{
						if(appState.terminalConfig.getUploadType() == 0)
						{
							transSuccess();
						}
						else{
							appState.setProcessState(PROCESS_CONFIMATION);
							getEMVCardInfo();
							processOnline();
							return;
						}
					}
					else{
						if(   appState.trans.getEMVOnlineFlag() == true
							&& appState.trans.getEMVOnlineResult() == ONLINE_FAIL
							)
						{
							// 通讯失败
							if(appState.terminalConfig.getUploadType() == 0)
							{
								saveAdvice();
							}
							else{
								appState.setProcessState(PROCESS_REVERSAL);
								getEMVCardInfo();
								processOnline();
								return;
							}
						}
						else if(   appState.trans.getEMVOnlineFlag() == true
							&& appState.trans.getEMVOnlineResult() == ONLINE_SUCCESS
							)
						{
							if(emv_is_need_advice() == 1)
							{
								if(appState.terminalConfig.getUploadType() == 0)
								{
									saveAdvice();
								}
								else{
									appState.setProcessState(PROCESS_ADVICE_ONLINE);
									getEMVCardInfo();
									processOnline();
									return;
								}
							}
							else{
								if(appState.terminalConfig.getUploadType() == 0)
								{
									saveAdvice();
								}
								else{
									appState.setProcessState(PROCESS_REVERSAL);
									getEMVCardInfo();
									processOnline();
									return;
								}
							}
						}
						else{
							if(emv_is_need_advice() == 1)
							{
								if(appState.terminalConfig.getUploadType() == 0)
								{
									saveAdvice();
								}
								else{
									appState.setProcessState(PROCESS_ADVICE_ONLINE);
									getEMVCardInfo();
									processOnline();
									return;
								}
							}
						}
					}
					appState.setProcessState(PROCESS_NORMAL);
				}
				setResult(RESULT_OK, getIntent());
				finish();
				break;
			default:
				switch (appState.trans.getEMVRetCode())
				{
//    					case ERROR_NO_APP:
//    					case ERROR_INIT_APP:
//    						//appState.trans.setEmvCardError(true);
//    						//setResult(RESULT_OK, getIntent());
//    						appState.setErrorCode(R.string.error_no_app);
//    						finish();
//    						break;
				case ERROR_OTHER_CARD:
					appState.trans.setEmvCardError(true);
					//setResult(RESULT_OK, getIntent());
					appState.setErrorCode(R.string.error_other_card);
					finish();
					break;
				case ERROR_EXPIRED_CARD:
					appState.setErrorCode(R.string.error_expiry_card);
					finish();
					break;
				case ERROR_CARD_BLOCKED:
					appState.setErrorCode(R.string.error_card_blocked);
					finish();
					break;
				case ERROR_APP_BLOCKED:
					appState.setErrorCode(R.string.error_app_blocked);
					finish();
					break;
				case ERROR_SERVICE_NOT_ALLOWED:
					appState.setErrorCode(R.string.error_not_accepted);
					finish();
					break;
				case ERROR_PINENTERY_TIMEOUT:
					appState.setErrorCode(R.string.error_pin_timeout);
					finish();
					break;
				default:
					appState.setErrorCode(R.string.error_ic );
					finish();
					break;
				}
				break;
			}
			break;
		case PREPROCESS_ERROR_NOTIFIER:
			if(appState.getErrorCode() == 0)
				appState.setErrorCode(R.string.error_pre_process);
			finish();
			break;
		}
	}

	@Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_process_emv_card);
		initToolbar();

        // title
//        textTitle = (TextView)findViewById(R.id.tAppTitle);
//		setTitle(textTitle);
//	    buttonBack = (Button)findViewById(R.id.btn_back);
//        buttonBack.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
//
//        buttonMore = (Button)findViewById(R.id.btn_more);
//        buttonMore.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_blank));
        
        textTransType = (TextView)findViewById(R.id.tProcessEMVCard_TransType);
        textTransType.setText(TransDefine.transInfo[appState.getTranType()].id_display_en);
        
        textLine1 = (TextView)findViewById(R.id.tProcessEMVCard_Line1);
        textLine1.setText("PROCESSING CARD，PLS WAITING...");

		mHandler.setFunActivity(this);
		mEMVThread = new EMVThread();
        mEMVThread.start();
    }

	private void initToolbar() {
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Purchase");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}



	@Override
	public boolean onSupportNavigateUp() {
		finish();
		return super.onSupportNavigateUp();
	}

	@Override
    protected void onStart() { 
        super.onStart(); 
        textLine1.setText("PROCESSING CARD，PLS WAITING...");
    } 
    
    @Override 
    protected void onResume() { 
        super.onResume(); 
    } 
    
    @Override 
    protected void onPause() { 
        super.onPause(); 
    }
    
    @Override 
    protected void onStop() { 
        super.onStop(); 
    }
    
    @Override
    public void onBackPressed(){
		finish();
    }
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(debug)Log.d(APP_TAG, "ProcessEMVCard onActivityResult, requesCode=" + requestCode + ",resultCode=" + resultCode);
		if(appState.getProcessState() != PROCESS_NORMAL)
		{
			if(   appState.getProcessState() == PROCESS_REVERSAL
			   && appState.trans.getEMVRetCode() == APPROVE_OFFLINE
			  )
			{
				appState.setProcessState(PROCESS_CONFIMATION);
				getEMVCardInfo();
				processOnline();
			}
			else if(   emv_is_need_advice() == 1
					&& appState.getProcessState() != PROCESS_ADVICE_ONLINE
				   )
			{
				appState.setProcessState(PROCESS_ADVICE_ONLINE);
				getEMVCardInfo();
				processOnline();
			}
			else{
				appState.setProcessState(PROCESS_NORMAL);
				setResult(Activity.RESULT_OK, getIntent());
				exit();
			}
			return;
		}
		else{
			if(appState.getErrorCode() > 0)
			{
				if( requestCode == STATE_PROCESS_ONLINE )
				{
					appState.trans.setEMVOnlineResult(ONLINE_FAIL);
					emv_set_online_result(appState.trans.getEMVOnlineResult(), appState.trans.getResponseCode(), new byte[]{' '}, 0);
			        mEMVProcessNextThread = new EMVProcessNextThread(); 
			        mEMVProcessNextThread.start();
					return;
				}
				exit();
				return;
			}
			if(resultCode != Activity.RESULT_OK)
			{
				
				if( requestCode == STATE_PROCESS_ONLINE )
				{
					appState.trans.setEMVOnlineResult(ONLINE_FAIL);
					emv_set_online_result(ONLINE_FAIL, appState.trans.getResponseCode(), new byte[]{' '}, 0);
				    mEMVProcessNextThread = new EMVProcessNextThread(); 
				    mEMVProcessNextThread.start();
					return;
				}
				setResult(resultCode, getIntent());
				exit();
				return;
			}
			if(requestCode == STATE_INPUT_AMOUNT)
			{
				setEMVTransAmount(Integer.toString(appState.trans.getTransAmount()));
				emv_set_other_amount(new byte[]{'0', 0x00});
			}
			else if(requestCode == STATE_INPUT_PIN)
			{
				if(appState.trans.getPinEntryMode() == CAN_PIN)
				{
					emv_set_online_pin_entered(1);
				}
				else{
					emv_set_online_pin_entered(0);
				}
			}
		}
        mEMVProcessNextThread = new EMVProcessNextThread(); 
        mEMVProcessNextThread.start();
	}
	
//	private void setIccRevData()
//	{
//		int offset = 0;
//		byte[] tagData = new byte[50];
//		byte[] iccData = new byte[100];
//		int tagDataLength = 0;
//		
//		if( emv_is_tag_present(0x9F10) >= 0)
//		{
//			tagDataLength = emv_get_tag_data(0x9F10, tagData, tagData.length);
//			iccData[offset]   = (byte)0x9F;
//			iccData[offset+1] = (byte)0x10;
//			iccData[offset+2] = (byte)tagDataLength;
//			System.arraycopy(tagData, 0, iccData, offset + 3, tagDataLength);
//			offset += (3 + tagDataLength);
//		}
//		if( emv_is_tag_present(0x9F1E) >= 0)
//		{
//			tagDataLength = emv_get_tag_data(0x9F1E, tagData, tagData.length);
//			iccData[offset]   = (byte)0x9F;
//			iccData[offset+1] = (byte)0x1E;
//			iccData[offset+2] = (byte)tagDataLength;
//			System.arraycopy(tagData, 0, iccData, offset + 3, tagDataLength);
//			offset += (3 + tagDataLength);
//		}
//		if(appState.trans.getEMVOnlineResult() == ONLINE_SUCCESS)
//		{
//			if( emv_is_tag_present(0x9F36) >= 0)
//			{
//				tagDataLength = emv_get_tag_data(0x9F36, tagData, tagData.length);
//				iccData[offset]   = (byte)0x9F;
//				iccData[offset+1] = (byte)0x36;
//				iccData[offset+2] = (byte)tagDataLength;
//				System.arraycopy(tagData, 0, iccData, offset + 3, tagDataLength);
//				offset += (3 + tagDataLength);
//			}
//		}
//		appState.trans.setICCRevData(iccData, 0, offset);
//	}
	
	private void getEMVCardInfo()
	{
		byte[] tagData = new byte[100];
		int tagDataLength = 0;

		byte[] iccData = new byte[1200];
		int offset = 0; 
		if(appState.getProcessState() == PROCESS_CONFIMATION)
		{
			//offset = emv_get_tag_list_data(confirmTagList, confirmTagList.length, iccData, iccData.length);
		}
		else{
			offset = emv_get_tag_list_data(nibsTag, nibsTag.length, iccData, iccData.length);
			Log.i("emv tag list", ByteUtil.arrayToHexStr(iccData, offset));
		}
		appState.trans.setICCData(iccData, 0, offset);
		Log.i("iccdata", appState.trans.getICCData());
		
		// Application Label 50
		if( emv_is_tag_present(0x50) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x50, tagData, tagData.length);
			byte[] appLabel = new byte[tagDataLength];
			System.arraycopy(tagData, 0, appLabel, 0, appLabel.length);
			appState.trans.setAppLabel(StringUtil.toString(appLabel));
		}

		// AIP
		if( emv_is_tag_present(0x82) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x82, tagData, tagData.length);
			appState.trans.setAIP(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}


		//Card holder name
		if(emv_is_tag_present(0x5F20) >= 0){
			tagDataLength = emv_get_tag_data(0x5F20,tagData, tagData.length);
			appState.trans.setCardHolderName(StringUtil.convertHexToString(StringUtil.toHexString(tagData, 0, tagDataLength, false)));
		}
		
		// TVR
		if( emv_is_tag_present(0x95) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x95, tagData, tagData.length);
			appState.trans.setTVR(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}
		
		// TSI
		if( emv_is_tag_present(0x9B) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9B, tagData, tagData.length);
			appState.trans.setTSI(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}
		
		
		// Application Identifier terminal
		if( emv_is_tag_present(0x9F06) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F06, tagData, tagData.length);
			appState.trans.setAID(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}

		// IAD
		if( emv_is_tag_present(0x9F10) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F10, tagData, tagData.length);
			appState.trans.setIAD(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}
		
		// ApplicationPreferredName  9F12 
		if( emv_is_tag_present(0x9F12) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F12, tagData, tagData.length);
			byte[] appName = new byte[tagDataLength];
			System.arraycopy(tagData, 0, appName, 0, appName.length);
			appState.trans.setAppName(StringUtil.toString(appName));
		}
		
		if(emv_is_tag_present(0x9F26) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F26, tagData, tagData.length);
			appState.trans.setAC(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}
		
		if(emv_is_tag_present(0x9F37) >= 0)
		{
			tagDataLength = emv_get_tag_data(0x9F37, tagData, tagData.length);
			appState.trans.setUnpredictableNumber(StringUtil.toHexString(tagData, 0, tagDataLength, false));
		}
		
		if(   emv_is_tag_present(0x9F79) >= 0 
	       && appState.trans.getECBalance() < 0		
	      )
		{
			tagDataLength = emv_get_tag_data(0x9F79, tagData, tagData.length);
			byte[] amt = new byte[tagDataLength];
			System.arraycopy(tagData, 0, amt, 0, amt.length);
			appState.trans.setECBalance(ByteUtil.bcdToInt(amt));
		}
	}
	
	public static void setEMVData()
	{
		if(appState.getTranType() == QUERY_CARD_RECORD)
		{
			emv_set_trans_amount(new byte[]{'0', 0x00});
			emv_set_other_amount(new byte[]{'0', 0x00});
			if(appState.recordType == 0x00)
			{
				emv_set_trans_type(EMV_TRANS_CARD_RECORD);
			}
			else
			{
				emv_set_trans_type(EMV_TRANS_LOAD_RECORD);
			}
		}
		else{
			emv_set_tag_data(0x9A,   StringUtil.hexString2bytes(appState.trans.getTransDate().substring(2)), 3);
			emv_set_tag_data(0x9F21, StringUtil.hexString2bytes(appState.trans.getTransTime()), 3);
			emv_set_tag_data(0x9F41, StringUtil.hexString2bytes(StringUtil.fillZero(Integer.toString(appState.trans.getTrace()), 8)), 4);
			
			emv_set_trans_type(EMV_TRANS_GOODS_SERVICE);
		}
		emv_set_currency_symbol("NGN".getBytes(), 3);
	}
	
    class EMVThread extends Thread
    {
    	public void run() 
    	{ 
    		super.run();
    		emv_trans_initialize();
       		emv_set_kernel_type(appState.trans.getEMVKernelType());
			setEMVTransAmount(Integer.toString(appState.trans.getTransAmount()));
       		setEMVData();

			//pre-process
			if(appState.trans.getEMVKernelType() == QPBOC_KERNAL && !preProcessQpboc())
			{
				Message msg = new Message();
				msg.what = PREPROCESS_ERROR_NOTIFIER;
				mHandler.sendMessage(msg);
				return;
			}
       		emv_process_next();
    	}
    }
    
    class EMVProcessNextThread extends Thread
    {
    	public void run() 
    	{ 
    		super.run();
       		emv_process_next();
    	}
    }
}
