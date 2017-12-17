package com.joybar.library.tracker;

import android.content.Context;

import com.joybar.library.tracker.google.TrackerGoogle;
import com.joybar.library.tracker.interfaces.ITrackerService;

/**
 * Created by joybar on 17/12/2017.
 */

public class TrackerClient implements ITrackerService {

    private ITrackerService trackerService;
    private int trackerType;


    private static final class TrackerClientHolder {
        public static final TrackerClient INSTANCE = new TrackerClient();
    }

    public static TrackerClient getInstance() {
        return TrackerClientHolder.INSTANCE;
    }


    public void setTrackerType(@TrackerConfig.TrackerType int trackerType) {
        this.trackerType = trackerType;
    }

    @Override
    public ITrackerService create(Context context, String trackID) {
        if (trackerType == TrackerConfig.TYPE_GOOGLE) {
            return trackerService = TrackerGoogle.getInstance().create(context, trackID);
        } else if (trackerType == TrackerConfig.TYPE_UMENG) {
            return trackerService = TrackerGoogle.getInstance().create(context, trackID);
        }
        throw new IllegalArgumentException("You must set trackerType before create it,have you set it?");
    }

    @Override
    public void sendScreenName(String screenName) {
        trackerService.sendScreenName(screenName);
    }

    @Override
    public void sentEvent(String category, String action) {
        trackerService.sentEvent(category,action);
    }

    @Override
    public void sentEvent(String category, String action, String label) {
        trackerService.sentEvent(category,action,label);
    }

    @Override
    public void sentEvent(String category, String action, String label, long value) {
        trackerService.sentEvent(category,action,label,value);
    }
}
