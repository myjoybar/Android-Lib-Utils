package com.joy.libbase.tracker.interfaces;

import android.content.Context;

/**
 * Created by joybar on 17/12/2017.
 */

public interface ITrackerService {

	ITrackerService create(Context context);

	void sendScreenName(String screenName);

	void sentEvent(String category, String action);

	void sentEvent(String category, String action, String label);

	void sentEvent(String category, String action, String label, long value);

	void sentEvent(String eventName);
	void sentEvent(String eventName, BundleCreator.Builder builder);


}
