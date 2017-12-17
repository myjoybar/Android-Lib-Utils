package com.joybar.library.common.log.config;

import com.joybar.library.common.log.LogLevel;

/**
 * Created by joybar on 2017/12/12.
 */

public class LogConfig {

	public static final String TAG = "Logger"; //默认Tag
	public static final String TAG_PREFIX = "CTLOG_"; //Tag前缀
	public static final int LINE_MAX = 1024 * 3;// 每行最大日志长度
	public static boolean SHOW_TOP = true;
	public static boolean SHOW_BOTTOM = true;
	public static int LOG_LEVEL = LogLevel.TYPE_VERBOSE;
	public static boolean LOG_ENABLE = true;

}
