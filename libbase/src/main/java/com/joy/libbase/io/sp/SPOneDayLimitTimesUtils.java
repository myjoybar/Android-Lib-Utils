package com.joy.libbase.io.sp;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joybar on 2018/1/16.
 */

public class SPOneDayLimitTimesUtils {

	private static final String SP_AD_ONE_DAY_LIMIT_TIMES_FILE = "sp_one_day_limit_times_file";

	public static void saveRecordTimesByAdRecordTAG(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(SP_AD_ONE_DAY_LIMIT_TIMES_FILE + key, Context.MODE_PRIVATE);
		int times = isToday(sp.getLong(key + "_millisec", 0)) ? sp.getInt(key + "_times", 0) : 0;
		sp.edit().putInt(key + "_times", times + 1).putLong(key + "_millisec", System.currentTimeMillis()).apply();
	}

	public static int getAlreadyRecordTimesByRecordTAG(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(SP_AD_ONE_DAY_LIMIT_TIMES_FILE + key, Context.MODE_PRIVATE);
		return isToday(sp.getLong(key + "_millisec", 0)) ? sp.getInt(key + "_times", 0) : 0;
	}

	private static boolean isToday(long timeStamp) {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(timeStamp));
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return date.equals(today);
	}

}
