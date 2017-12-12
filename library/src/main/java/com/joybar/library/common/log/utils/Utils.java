package com.joybar.library.common.log.utils;

import com.joybar.library.common.log.config.LogConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joybar on 2017/12/12.
 */

public class Utils {


	// 分割线方位
	public static final int DIVIDER_TOP = 1;
	public static final int DIVIDER_BOTTOM = 2;

	/**
	 * 打印分割线
	 *
	 * @param dir
	 * @return
	 */
	public static String printDividingLine(int dir) {
		switch (dir) {
			case DIVIDER_TOP:
				return "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
			case DIVIDER_BOTTOM:
				return "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
			default:
				break;
		}
		return "";
	}

	/**
	 * 长字符串转化为List
	 *
	 * @param msg
	 * @return
	 */
	public static List<String> largeStringToList(String msg) {
		List<String> stringList = new ArrayList<>();
		int index = 0;
		int maxLength = LogConfig.LINE_MAX;
		int countOfSub = msg.length() / maxLength;
		if (countOfSub > 0) {
			for (int i = 0; i < countOfSub; i++) {
				String sub = msg.substring(index, index + maxLength);
				stringList.add(sub);
				index += maxLength;
			}
			stringList.add(msg.substring(index, msg.length()));
		} else {
			stringList.add(msg);
		}
		return stringList;
	}
}
