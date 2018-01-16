package com.joybar.library.common.wiget;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.joybar.library.R;

/**
 * Created by joybar on 2018/1/16.
 */

public class SnackBarUtils {

	private static int bgColor;
	private static int textColor;
	private static int actionTextColor;

	public static void setBgColor(int bgColorID) {
		bgColor = bgColorID;
	}

	public static void setTextColor(int textColorID) {
		textColor = textColorID;
	}

	public static void setActionTextColor(int actionTextColorID) {
		actionTextColor = actionTextColorID;
	}

	public static void showShort(View view, String text) {
		Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
		View snackBarView = snackbar.getView();
		if (-1 != textColor) {
			((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(textColor);
		}
		if (-1 != bgColor) {
			snackBarView.setBackgroundColor(bgColor);
		}
		snackbar.show();
	}

	public static void showLong(View view, String text) {
		Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
		View snackBarView = snackbar.getView();
		if (-1 != textColor) {
			((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(textColor);
		}
		if (-1 != bgColor) {
			snackBarView.setBackgroundColor(bgColor);
		}
		snackbar.show();
	}

	public static void showShort(View view, String text, int textColor, int bgColor) {
		Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
		View snackBarView = snackbar.getView();
		((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(textColor);
		snackBarView.setBackgroundColor(bgColor);
		snackbar.show();
	}

	public static void showLong(View view, String text, int textColor, int bgColor) {
		Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
		View snackBarView = snackbar.getView();
		((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(textColor);
		snackBarView.setBackgroundColor(bgColor);
		snackbar.show();
	}

	public static void showShort(View view, String text, int textColor, int bgColor, CharSequence actionText, int actionTextColor, View
			.OnClickListener listener) {
		Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
		View snackBarView = snackbar.getView();
		((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(textColor);
		snackBarView.setBackgroundColor(bgColor);
		if (actionText != null && actionText.length() > 0 && listener != null) {
			snackbar.setActionTextColor(actionTextColor);
			snackbar.setAction(actionText, listener);
		}
		snackbar.show();
	}


	public static void showLong(View view, String text, int textColor, int bgColor, CharSequence actionText, int actionTextColor, View
			.OnClickListener listener) {
		Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
		View snackBarView = snackbar.getView();
		((TextView) snackBarView.findViewById(R.id.snackbar_text)).setTextColor(textColor);
		snackBarView.setBackgroundColor(bgColor);
		if (!TextUtils.isEmpty(actionText) && listener != null) {
			snackbar.setActionTextColor(actionTextColor);
			snackbar.setAction(actionText, listener);
		}
		snackbar.show();
	}

}
