package com.joy.libbase.config;


import android.app.Application;
import android.support.annotation.Nullable;

import com.joy.libbase.base.app.performance.LeakCanaryManager;
import com.joy.libbase.base.app.performance.StrictModeManager;
import com.joy.libbase.base.util.common.check.CheckUtils;
import com.joy.libbase.config.instance.log.ILogService;
import com.joy.libbase.config.interfaces.ILibConfigService;
import com.joy.libbase.test.log.LLog;
import com.joy.libbase.tracker.interfaces.ITrackerService;
import com.joy.libok.configdata.OKConfigData;

public class LibConfigManager {
	public static final String TAG = "Lib_";
	private Application mLibApplication;
	private ILibConfigService mLibConfigService;


	private static final class LibConfigManagerHolder {
		public static final LibConfigManager INSTANCE = new LibConfigManager();
	}

	public static LibConfigManager getInstance() {
		return LibConfigManagerHolder.INSTANCE;
	}

	public void init(@Nullable Application application, @Nullable ILibConfigService mLibConfigService) {
		CheckUtils.checkNotNull(application);
		CheckUtils.checkNotNull(mLibConfigService);
		this.mLibApplication = application;
		this.mLibConfigService = mLibConfigService;
		initLeakCanary();
		initStrictMode();
		LLog.setENABLE(mLibConfigService.isEnableLog());
		if (null != getLogService()) {
			LLog.i(TAG, "i am in libbase, init LibConfigManagerHolder");
		}

	}


	private void initStrictMode() {
		if (mLibConfigService.isOpenStrictMode()) {
			StrictModeManager.getInstance().monitorActivityLeaks();
			StrictModeManager.getInstance().monitorAllThreadPolicy();
			StrictModeManager.getInstance().monitorSqlLite();
		}
	}

	private void initLeakCanary() {
		if (mLibConfigService.isInitLeakCanary()) {
			LeakCanaryManager.getInstance().init(mLibApplication);
		}
	}

	public ITrackerService getTrackerService() {
		if (null != mLibConfigService) {
			return mLibConfigService.getITrackerService();
		}
		return null;

	}

	public ILogService getLogService() {
		if (null != mLibConfigService) {
			return mLibConfigService.getLogService();
		}
		return null;
	}


	public OKConfigData getOkConfigData() {
		if (null != mLibConfigService) {
			return mLibConfigService.getOkConfigData();
		}
		return new OKConfigData();
	}


}
