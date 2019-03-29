package com.joy.libbase.config.interfaces;

import com.joy.libbase.config.instance.log.ILogService;
import com.joy.libbase.net.retrofit.config.OKConfigData;
import com.joy.libbase.tracker.interfaces.ITrackerService;

public interface ILibConfigService {
	boolean isEnableLog();

	boolean isInitLeakCanary();
	boolean isOpenStrictMode();

	ITrackerService getITrackerService();
	ILogService getLogService();


	OKConfigData getOkConfigData();

}
