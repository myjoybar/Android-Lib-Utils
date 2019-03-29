package com.joy.libbase.config.interfaces;

import android.app.Application;

public abstract class LibConfigManagerService {

	public void initAll(Application application){
		initTracker(application);
		initLog();
		registerLifeCycle(application);
		registerComponentCallbacks(application);
		initLibConfigManager(application);
		initCrashTracker(application);
	}

	protected abstract void initLibConfigManager(Application application);

	protected abstract void initCrashTracker(Application application);

	protected abstract void initTracker(Application application);

	protected abstract void initLog();

	protected abstract void registerLifeCycle(Application application);

	protected abstract void registerComponentCallbacks(Application application);


}
