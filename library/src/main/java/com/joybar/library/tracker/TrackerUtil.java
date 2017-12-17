package com.joybar.library.tracker;

import android.content.Context;

/**
 * Created by joybar on 2017/7/6.
 */

public class TrackerUtil {

    public static void createTracker(@TrackerConfig.TrackerType int trackerType, Context context, String trackID) {
        TrackerClient.getInstance().setTrackerType(trackerType);
        TrackerClient.getInstance().create(context,trackID);
    }

    public static void sendScreenName(String screenName) {
        TrackerClient.getInstance().sendScreenName(screenName);
    }

    public static void sentEvent(String category, String action) {
        TrackerClient.getInstance().sentEvent(category, action);
    }

    public static void sentEvent(String category, String action, String label) {
        TrackerClient.getInstance().sentEvent(category, action, label);
    }

    public static void sentEvent(String category, String action, String label, long value) {
        TrackerClient.getInstance().sentEvent(category, action, label, value);
    }

}
