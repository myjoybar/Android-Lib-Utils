package com.joy.libbase.base.app.structure;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by joybar on 4/19/16.
 */
public class BaseActivity extends ToolbarActivity {

    public static String TAG;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mContext = this;
        if (applyEventBus()) {
            EventBus.getDefault().register(this);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (applyEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected boolean applyEventBus() {
        return false;
    }
}
