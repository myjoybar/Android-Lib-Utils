package com.joy.libbase.base.app.activitylife;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.joy.libbase.config.LibConfigManager;
import com.joy.libbase.tracker.interfaces.BundleCreator;
import com.joy.libbase.tracker.interfaces.ITrackerService;


/**
 * Created by joybar on 2019/2/26.
 */

public class CustomActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

	private AppBackgroundChangeCallBack mAppBackgroundChangeCallBack;
	public int activityCount = 0;

	public static final String PAGE_ENTER = "ActivityEnter";
	public static final String ACTIVITY_NAME = "item_name";

	public CustomActivityLifecycleCallbacks(AppBackgroundChangeCallBack appBackgroundChangeCallBack) {
		mAppBackgroundChangeCallBack = appBackgroundChangeCallBack;
	}

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		ITrackerService trackerService = null;
		if ((trackerService = LibConfigManager.getInstance().getTrackerService()) != null) {
			String simpleClassName = activity.getClass().getSimpleName();
			BundleCreator.Builder builder = BundleCreator.create();
			builder.put(ACTIVITY_NAME, simpleClassName);
			trackerService.sentEvent(PAGE_ENTER, builder);

		}

	}

	@Override
	public void onActivityStarted(Activity activity) {
		if (activityCount == 0) { //后台切换到前台
			if (null != mAppBackgroundChangeCallBack) {
				mAppBackgroundChangeCallBack.onBackgroundToForeground();
			}
		}
		activityCount++;
	}

	@Override
	public void onActivityResumed(Activity activity) {
		//IGActivityManager.getInstance().setCurrentActivity(activity);

	}

	@Override
	public void onActivityPaused(Activity activity) {

	}

	@Override
	public void onActivityStopped(Activity activity) {
		activityCount--;
		if (activityCount == 0) { //前台切换到后台
			if (null != mAppBackgroundChangeCallBack) {
				mAppBackgroundChangeCallBack.onForegroundToBackground();
			}
		}
	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {
		//IGActivityManager.getInstance().setCurrentActivity(null);
	}


	public interface AppBackgroundChangeCallBack {
		void onForegroundToBackground();

		void onBackgroundToForeground();

	}

}
