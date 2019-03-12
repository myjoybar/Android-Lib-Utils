package com.joy.libbase.base.util.ui.tips;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * Created by joybar on 4/22/16.
 */
public class ToastUtil {
	private static Toast toast = null;
	/**
	 * @param context 内容器实体
	 * @param text    提示文字内容
	 */
	public static void showLong(Context context, String text) {
		if (!TextUtils.isEmpty(text)) {
			toastShow(context.getApplicationContext(), text, Toast.LENGTH_LONG);
		}
	}

	/**
	 * @param context 内容器实体
	 * @param text    提示文字
	 */
	public static void showShort(Context context, String text) {
		if (!TextUtils.isEmpty(text)) {
			toastShow(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
		}
	}

	/**
	 * @param context  内容器实体
	 * @param text     提示文字
	 * @param duration 提示时间
	 */
	public static void toastShow(Context context, String text, int duration) {
		if (toast == null) {
			toast = Toast.makeText(context, text, duration);
		} else {
			toast.setText(text);
		}
		toast.show();
	}

}
