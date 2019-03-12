package com.joy.libbase.base.app.activitylife;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


/**
 * Created by joybar on 2019/2/26.
 */

public class CustomActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

	private int activityCount = 0;

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		// tracker
	}

	@Override
	public void onActivityStarted(Activity activity) {
		if (activityCount == 0) { //后台切换到前台

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

		}
	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {
		//IGActivityManager.getInstance().setCurrentActivity(null);
	}
}
