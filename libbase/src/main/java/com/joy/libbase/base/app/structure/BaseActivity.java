package com.joy.libbase.base.app.structure;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by joybar on 4/19/16.
 */
public class BaseActivity extends ToolbarActivity {

	protected static String TAG;
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


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && handleKeyDown()) {
			handleBackAction();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}

	}

	protected boolean applyEventBus() {
		return false;
	}

	protected boolean handleKeyDown() {
		return true;
	}

	protected void handleBackAction() {
		finish();
	}
}
