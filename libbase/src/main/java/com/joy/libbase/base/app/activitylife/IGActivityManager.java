package com.joy.libbase.base.app.activitylife;

import android.app.Activity;

/**
 * Created by joybar on 2019/2/26.
 */

public class IGActivityManager {

	Activity mActivity;

	private static class IGActivityManagerHolder {
		private static IGActivityManager INSTANCE = new IGActivityManager();

	}

	public static IGActivityManager getInstance() {
		return IGActivityManagerHolder.INSTANCE;
	}

	public void setCurrentActivity(Activity activity) {
		this.mActivity = activity;

	}

	public Activity getActivity() {
		return mActivity;
	}
}
