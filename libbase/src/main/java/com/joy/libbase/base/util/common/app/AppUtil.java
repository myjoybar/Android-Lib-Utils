package com.joy.libbase.base.util.common.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by joybar on 2018/1/9.
 */

public class AppUtil {
	/**
	 * 通过包名拉起app
	 *
	 * @param context
	 */
	public static void oppAppByPckName(Context context) {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = null;
		intent = packageManager.getLaunchIntentForPackage("com.vict.fsd");
		if (intent != null) {
			context.startActivity(intent);
		}

	}

	/**
	 * 检查包是否存在
	 *
	 * @param context
	 * @param packname
	 * @return
	 */
	public static boolean checkPackInfo(Context context, String packname) {
		PackageInfo packageInfo = null;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(packname, 0);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return packageInfo != null;
	}


	/**
	 * 判断B应用是否在后台运行并直接打开
	 *
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static Intent getAppOpenIntentByPackageName(Context context, String packageName) {
		//Activity完整名
		String mainAct = null;
		//根据包名寻找
		PackageManager pkgMag = context.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_NEW_TASK);

		List<ResolveInfo> list = pkgMag.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
		for (int i = 0; i < list.size(); i++) {
			ResolveInfo info = list.get(i);
			if (info.activityInfo.packageName.equals(packageName)) {
				mainAct = info.activityInfo.name;
				break;
			}
		}
		if (TextUtils.isEmpty(mainAct)) {
			return null;
		}
		intent.setComponent(new ComponentName(packageName, mainAct));
		return intent;
	}


	public static Context getPackageContext(Context context, String packageName) {
		Context pkgContext = null;
		if (context.getPackageName().equals(packageName)) {
			pkgContext = context;
		} else {
			// 创建第三方应用的上下文环境
			try {
				pkgContext = context.createPackageContext(packageName, Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		return pkgContext;
	}

	public static boolean openPackage(Context context, String packageName) {
		Context pkgContext = getPackageContext(context, packageName);
		Intent intent = getAppOpenIntentByPackageName(context, packageName);
		if (pkgContext != null && intent != null) {
			pkgContext.startActivity(intent);
			return true;
		}
		return false;
	}

	public static void openBrowser(String url, Context context) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_uri_browsers = Uri.parse(url);
		intent.setData(content_uri_browsers);
		context.startActivity(intent);
	}

}

