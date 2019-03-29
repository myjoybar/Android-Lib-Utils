package com.joy.libbase.base.app.structure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joy.libbase.base.app.performance.LeakCanaryManager;
import com.joy.libbase.config.LibConfigManager;
import com.joy.libbase.tracker.interfaces.BundleCreator;
import com.joy.libbase.tracker.interfaces.ITrackerService;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.eventbus.EventBus;


/***
 *   Created by joybar on 2019/2/26.
 *
 ***/
public class BaseFragment extends Fragment {
    public static final String PAGE_ENTER = "FragmentEnter";
    public static final String ACTIVITY_NAME = "item_name";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (applyEventBus()) {
            EventBus.getDefault().register(this);
        }
        if(applyLeakCanary()){
            initLeakCanary();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ITrackerService trackerService = null;
        if ((trackerService = LibConfigManager.getInstance().getTrackerService()) != null) {
            String simpleClassName  = this.getClass().getSimpleName();
            BundleCreator.Builder builder = BundleCreator.create();
            builder.put(ACTIVITY_NAME, simpleClassName);
            trackerService.sentEvent(PAGE_ENTER, builder);

        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (applyEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }


    protected boolean applyEventBus() {
        return false;
    }

    protected boolean applyLeakCanary() {
        return false;
    }

    private void  initLeakCanary(){
        RefWatcher refWatcher = LeakCanaryManager.getInstance().getRefWatcher();
        refWatcher.watch(this);
    }

}
