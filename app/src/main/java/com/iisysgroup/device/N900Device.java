package com.iisysgroup.device;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Handler;

import com.iisysgroup.newlandtestapp.App;
import com.iisysgroup.utils.MyLogger;
import com.newland.me.ConnUtils;
import com.newland.me.DeviceManager;
import com.newland.mtype.ConnectionCloseEvent;
import com.newland.mtype.Device;
import com.newland.mtype.ExModuleType;
import com.newland.mtype.ModuleType;
import com.newland.mtype.event.DeviceEventListener;
import com.newland.mtype.module.common.buzzer.Buzzer;
import com.newland.mtype.module.common.cardreader.K21CardReader;
import com.newland.mtype.module.common.emv.EmvModule;
import com.newland.mtype.module.common.iccard.ICCardModule;
import com.newland.mtype.module.common.light.IndicatorLight;
import com.newland.mtype.module.common.pin.K21Pininput;
import com.newland.mtype.module.common.printer.Printer;
import com.newland.mtype.module.common.rfcard.K21RFCardModule;
import com.newland.mtype.module.common.scanner.BarcodeScanner;
import com.newland.mtype.module.common.scanner.BarcodeScannerManager;
import com.newland.mtype.module.common.security.SecurityModule;
import com.newland.mtype.module.common.serialport.SerialModule;
import com.newland.mtype.module.common.sm.SmModule;
import com.newland.mtype.module.common.storage.Storage;
import com.newland.mtype.module.common.swiper.K21Swiper;
import com.newland.mtypex.nseries.NSConnV100ConnParams;

public class N900Device extends AbstractDevice {
	/**
	 * 驱动名字
	 */
	private static final String K21_DRIVER_NAME = "com.newland.me.K21Driver";
 
	private static N900Device n900Device=null;
	private static DeviceManager deviceManager ;

	public static N900Device getInstance() {
		if (n900Device == null) {
			synchronized (N900Device.class) {
				if (n900Device == null) {
					n900Device = new N900Device();
				}
			}
		}                                      
		return n900Device;
	}
	

	public static DeviceManager getDeviceManager() {
		return deviceManager;
	}

	public static void setDeviceManager(DeviceManager deviceManager) {
		N900Device.deviceManager = deviceManager;
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD) @Override
	public void connectDevice() {
		MyLogger.jLog().e("设备连接中..");
		MyLogger.jLog().e("----厂商----"+ Build.MANUFACTURER);
		MyLogger.jLog().e("----型号----"+ Build.MODEL);
		MyLogger.jLog().e("----软件版本号----"+ Build.DISPLAY);
		MyLogger.jLog().e("----SN----"+ Build.SERIAL);
		try {
			deviceManager = ConnUtils.getDeviceManager();
			MyLogger.jLog().e("----驱动主版本号----"+deviceManager.getDriverMajorVersion());
			MyLogger.jLog().e("----驱动子子子版本号----"+deviceManager.getDriverMinorVersion());
			MyLogger.jLog().e("----获取可连接状态----"+deviceManager.getDeviceConnState());
//	 x新设备		deviceManager.init(App.getContext(), K21_DRIVER_NAME, new NS3ConnParams(),

		      //NSConnV100 不支持多个设备连接    NS3  区别，N3S 支持多个设备并发超值，（不同设备可以，同一个设备还是不行）N3 需要固件的支持。目前 2.1.09  2.2.28的支持
		    deviceManager.init(App.getContext(), K21_DRIVER_NAME, new NSConnV100ConnParams(),
					                   new DeviceEventListener<ConnectionCloseEvent>() {
				@Override
				public void onEvent(ConnectionCloseEvent event, Handler handler) {
					if (event.isSuccess()) {
						MyLogger.jLog().e("设备被客户主动断开！");
					}
					if (event.isFailed()) {
						MyLogger.jLog().e("设备链接异常断开！");
					}
				}
				@Override
				public Handler getUIHandler() {
					return null;
				}
			});
			MyLogger.jLog().e("N900设备控制器已初始化!！");

			deviceManager.connect();
			deviceManager.getDevice().setBundle(new NSConnV100ConnParams());
			MyLogger.jLog().e("设备连接成功！");
		} catch (Exception e1) {
			e1.printStackTrace();
			MyLogger.jLog().e("链接异常,请检查设备或重新连接...！");
		}
	}

	@Override
	public void disconnect() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					if (deviceManager != null) {
						deviceManager.disconnect();				
						deviceManager = null;
						MyLogger.jLog().e("设备断开成功...");
					}
				} catch (Exception e) {
					MyLogger.jLog().e("设备断开异常...");
				}
			}
		}).start();
	}

	@Override
	public boolean isDeviceAlive() {
		boolean ifConnected = ( deviceManager== null ? false : deviceManager.getDevice().isAlive());
        return ifConnected;
	}

	@Override
	public K21CardReader getCardReaderModuleType() {
		K21CardReader cardReader=(K21CardReader) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_CARDREADER);
		return cardReader;
	}

	@Override
	public EmvModule getEmvModuleType() {
		EmvModule emvModule=(EmvModule) deviceManager.getDevice().getExModule("EMV_INNERLEVEL2");
		return emvModule;
	}

	 
	@Override
	public ICCardModule getICCardModule() {
		ICCardModule iCCardModule=(ICCardModule) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_ICCARDREADER);
		return iCCardModule;
	}

	@Override
	public IndicatorLight getIndicatorLight() {
		IndicatorLight indicatorLight=(IndicatorLight) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_INDICATOR_LIGHT);
		return indicatorLight;
	}

	@Override
	public K21Pininput getK21Pininput() {
		K21Pininput k21Pininput=(K21Pininput) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_PININPUT);
		return k21Pininput;
	}

	@Override
	public Printer getPrinter() {
		Printer printer=(Printer) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_PRINTER);
		printer.init();
		return printer;
	}

	@Override
	public K21RFCardModule getRFCardModule() {
		K21RFCardModule rFCardModule=(K21RFCardModule) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_RFCARDREADER);
		return rFCardModule;
	}

	@Override
	public BarcodeScanner getBarcodeScanner() {
		BarcodeScannerManager barcodeScannerManager=(BarcodeScannerManager) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_BARCODESCANNER);
		BarcodeScanner scanner = barcodeScannerManager.getDefault();
		return scanner;
	}

	@Override
	public SecurityModule getSecurityModule() {
		SecurityModule securityModule=(SecurityModule) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_SECURITY);
		return securityModule;
	}

	@Override
	public Storage getStorage() {
		Storage storage=(Storage) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_STORAGE);
		return storage;
	}

	@Override
	public K21Swiper getK21Swiper() {
		K21Swiper k21Swiper=(K21Swiper) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_SWIPER);
		return k21Swiper;
	}

	@Override
	public Device getDevice() {
		return deviceManager.getDevice();
	}
	
	@Override
	public SerialModule getUsbSerial() {
		SerialModule k21Serial=(SerialModule) deviceManager.getDevice().getExModule(ExModuleType.USBSERIAL);
		return k21Serial;
	}

	@Override
	public SmModule getSmModule() {
		SmModule smModule = (SmModule)deviceManager.getDevice().getStandardModule(ModuleType.COMMON_SM);
		return smModule;
	}
	@Override
	public Buzzer getBuzzer() {
		Buzzer buzzer =(Buzzer) deviceManager.getDevice().getStandardModule(ModuleType.COMMON_BUZZER);
		return buzzer;
	}
	
}
