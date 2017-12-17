package com.joybar.library.tracker.umeng;

import android.content.Context;

import com.joybar.library.tracker.interfaces.ITrackerService;

/**
 * Created by joybar on 17/12/2017.
 */

public class TrackerUmeng implements ITrackerService {

    private static final class TrackerUmengHolder {
        public static final TrackerUmeng INSTANCE = new TrackerUmeng();
    }

    public static TrackerUmeng getInstance() {
        return TrackerUmengHolder.INSTANCE;
    }

    @Override
    public ITrackerService create(Context context, String trackID) {
        return null;
    }

    @Override
    public void sendScreenName(String screenName) {

    }

    @Override
    public void sentEvent(String category, String action) {

    }

    @Override
    public void sentEvent(String category, String action, String label) {

    }

    @Override
    public void sentEvent(String category, String action, String label, long value) {

    }
}
