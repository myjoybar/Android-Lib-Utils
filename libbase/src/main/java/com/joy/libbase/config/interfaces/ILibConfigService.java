package com.joy.libbase.config.interfaces;

import com.joy.libbase.config.instance.log.ILogService;
import com.joy.libbase.tracker.interfaces.ITrackerService;
import com.joy.libok.configdata.OKConfigData;

public interface ILibConfigService {
	boolean isEnableLog();

	boolean isInitLeakCanary();
	boolean isOpenStrictMode();

	ITrackerService getITrackerService();
	ILogService getLogService();


	OKConfigData getOkConfigData();

}
