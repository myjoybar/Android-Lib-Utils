package com.joy.libbase.base.app.performance;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class LeakCanaryManager {

	private static RefWatcher mRefWatcher;

	private static class LeakCanaryManagerHolder {
		private static LeakCanaryManager INSTANCE = new LeakCanaryManager();

	}

	public static LeakCanaryManager getInstance() {
		return LeakCanaryManagerHolder.INSTANCE;
	}

	public void init(Application application) {
		mRefWatcher = setupLeakCanary(application);
	}


	private RefWatcher setupLeakCanary(Application application) {
		if (LeakCanary.isInAnalyzerProcess(application)) {
			return RefWatcher.DISABLED;
		}
		return LeakCanary.install(application);
	}

	public RefWatcher getRefWatcher() {
		return mRefWatcher;
	}


}
