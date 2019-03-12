package com.joy.libbase.tracker;

import android.content.Context;
import android.os.Bundle;

import com.joy.libbase.tracker.interfaces.BundleCreator;
import com.joy.libbase.tracker.interfaces.ITrackerService;
import com.joy.libbase.tracker.testlog.TestTrackerLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrackerService implements ITrackerService {
	public static final String TAG = "TrackerService";
	List<ITrackerService> mITrackerServiceList = new ArrayList<>(0);

	public void setITrackerServiceList(List<ITrackerService> ITrackerServiceList) {
		mITrackerServiceList = ITrackerServiceList;
	}

	private static final class TrackerServiceHolder {
		public static final TrackerService INSTANCE = new TrackerService();
	}

	public static TrackerService getInstance() {
		return TrackerServiceHolder.INSTANCE;
	}


	@Override
	public ITrackerService create(Context context) {
		return null;
	}

	@Override
	public void sendScreenName(String screenName) {

		if (mITrackerServiceList != null && mITrackerServiceList.size() != 0) {
			for (int i = 0; i < mITrackerServiceList.size(); i++) {
				mITrackerServiceList.get(i).sendScreenName(screenName);
			}
		}
	}

	@Override
	public void sentEvent(String category, String action) {

		if (mITrackerServiceList != null && mITrackerServiceList.size() != 0) {
			for (int i = 0; i < mITrackerServiceList.size(); i++) {
				mITrackerServiceList.get(i).sentEvent(category, action);
			}
		}
	}

	@Override
	public void sentEvent(String category, String action, String label) {

		if (mITrackerServiceList != null && mITrackerServiceList.size() != 0) {
			for (int i = 0; i < mITrackerServiceList.size(); i++) {
				mITrackerServiceList.get(i).sentEvent(category, action, label);
			}
		}
	}

	@Override
	public void sentEvent(String category, String action, String label, long value) {

		if (mITrackerServiceList != null && mITrackerServiceList.size() != 0) {
			for (int i = 0; i < mITrackerServiceList.size(); i++) {
				mITrackerServiceList.get(i).sentEvent(category, action, label, value);
			}
		}
	}

	@Override
	public void sentEvent(String eventName) {

		if (mITrackerServiceList != null && mITrackerServiceList.size() != 0) {
			for (int i = 0; i < mITrackerServiceList.size(); i++) {
				mITrackerServiceList.get(i).sentEvent(eventName, BundleCreator.create());
			}
		}
	}

	@Override
	public void sentEvent(String eventName, BundleCreator.Builder builder) {

		if (mITrackerServiceList != null && mITrackerServiceList.size() != 0) {
			for (int i = 0; i < mITrackerServiceList.size(); i++) {
				mITrackerServiceList.get(i).sentEvent(eventName, builder);
			}
		}
	}


	HashMap<String, Object> getMap(Bundle bundle) {
		HashMap<String, Object> map = new HashMap<>();
		for (String key : bundle.keySet()) {
			TestTrackerLog.print(TAG, "bundle to map key=" + key + ",value=" + bundle.get(key));
			map.put(key, bundle.get(key));
		}
		return map;
	}

}
