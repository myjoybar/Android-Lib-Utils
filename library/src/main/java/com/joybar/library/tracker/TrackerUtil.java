package com.joybar.library.tracker;

import android.content.Context;

/**
 * Created by joybar on 2017/7/6.
 */

public class TrackerUtil {

    /**
     * you must call this createTracker before use send event
     * @param trackerType the trackerType
     * @param context the context
     * @param trackID  the trackID which you get from google backend
     */
    public static void createTracker(@TrackerConfig.TrackerType int trackerType, Context context, String trackID) {
        TrackerClient.getInstance().setTrackerType(trackerType);
        TrackerClient.getInstance().create(context,trackID);
    }

    /**
     *
     * @param screenName the screenName
     */
    public static void sendScreenName(String screenName) {
        TrackerClient.getInstance().sendScreenName(screenName);
    }

    /**
     *
     * @param category the category
     * @param action the action
     */
    public static void sentEvent(String category, String action) {
        TrackerClient.getInstance().sentEvent(category, action);
    }

    /**
     *
     * @param category the category
     * @param action the action
     * @param label the label
     */
    public static void sentEvent(String category, String action, String label) {
        TrackerClient.getInstance().sentEvent(category, action, label);
    }

    /**
     *
     * @param category the category
     * @param action the action
     * @param label the label
     * @param value the value
     */
    public static void sentEvent(String category, String action, String label, long value) {
        TrackerClient.getInstance().sentEvent(category, action, label, value);
    }

}
