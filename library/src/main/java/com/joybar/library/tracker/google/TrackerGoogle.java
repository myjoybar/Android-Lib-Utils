package com.joybar.library.tracker.google;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.joybar.library.tracker.interfaces.ITrackerService;

/**
 * Created by joybar on 17/12/2017.
 */

public class TrackerGoogle implements ITrackerService {


    private Tracker mTracker;

    private static final class TrackerGoogleHolder {
        public static final TrackerGoogle INSTANCE = new TrackerGoogle();
    }

    public static TrackerGoogle getInstance() {
        return TrackerGoogleHolder.INSTANCE;
    }


    @Override
    synchronized public ITrackerService create(Context context, String trackID) {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
            // Get tracker.
            mTracker = analytics.newTracker(trackID);
        }
        return (ITrackerService) mTracker;
    }


    @Override
    public void sendScreenName(String screenName) {
        if (null != mTracker) {
            mTracker.setScreenName(screenName);
            mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }

    @Override
    public void sentEvent(String category, String action) {
        if (null != mTracker) {
            mTracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action)
                    .setLabel("").setValue(0).build());
        }
    }

    @Override
    public void sentEvent(String category, String action, String label) {
        if (null != mTracker) {
            mTracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action)
                    .setLabel(label).setValue(0).build());
        }
    }

    @Override
    public void sentEvent(String category, String action, String label, long value) {
        if (null != mTracker) {
            mTracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action)
                    .setLabel(label).setValue(value).build());
        }
    }


}
