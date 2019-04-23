package com.joy.libbase.base.util.common.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by joybar on 2018/1/9.
 */

public class AppMarketUtil {

	public interface OpenAppMarketCallback {
		void openResultCallback(boolean success);
	}

	public static void gotoAppShop(Context context, String packageName) {
		try {
			Uri uri = Uri.parse("market://details?id=" + packageName);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
		} catch (Exception e) {

		}
	}

	public static void gotoAppShop(Context context, String packageName, OpenAppMarketCallback openAppMarketCallback) {
		try {
			Uri uri = Uri.parse("market://details?id=" + packageName);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			if (null != openAppMarketCallback) {
				openAppMarketCallback.openResultCallback(true);
			}
		} catch (Exception e) {
			if (null != openAppMarketCallback) {
				openAppMarketCallback.openResultCallback(false);
			}
		}
	}



}
