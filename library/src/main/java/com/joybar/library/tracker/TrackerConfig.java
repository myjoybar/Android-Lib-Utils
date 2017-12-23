package com.joybar.library.tracker;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by joybar on 17/12/2017.
 */

public class TrackerConfig {

    public final static int TYPE_GOOGLE = 1;
    public final static int TYPE_UMENG = 2;

    @IntDef({TYPE_GOOGLE, TYPE_UMENG})
    @Retention(RetentionPolicy.SOURCE)

    public @interface TrackerType {
    }
}
