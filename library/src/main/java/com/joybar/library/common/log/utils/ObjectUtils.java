package com.joybar.library.common.log.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by joybar on 2017/12/12.
 */

public class ObjectUtils {

	public static final String STRING_OBJECT_NULL = "Object[object is null]";
	// 换行符
	public static final String BR = System.getProperty("line.separator");

	public static String LINE_SEPARATOR = BR;
	// 解析属性最大层级
	public static final int MAX_CHILD_LEVEL = 2;

	public static String objectToString(Object object) {
		return objectToString(object, 0);
	}

	public static String objectToString(Object object, int childLevel) {
		if (object == null) {
			return STRING_OBJECT_NULL;
		}
		if (childLevel > MAX_CHILD_LEVEL) {
			return object.toString();
		}

		if (Collection.class.isAssignableFrom(object.getClass())) {
			Collection collection = (Collection) object;
			String simpleName = collection.getClass().getName();
			String msg = "%s size = %d [" + LINE_SEPARATOR;
			msg = String.format(msg, simpleName, collection.size());
			if (!collection.isEmpty()) {
				Iterator<Object> iterator = collection.iterator();
				int flag = 0;
				while (iterator.hasNext()) {
					String itemString = "[%d]:%s%s";
					Object item = iterator.next();
					msg += String.format(itemString, flag, objectToString(item), flag++ < collection.size() - 1 ? "," + LINE_SEPARATOR :
							LINE_SEPARATOR);
				}
			}
			return msg + "]";
		}

		if (Map.class.isAssignableFrom(object.getClass())) {
			Map map = (Map) object;
			String msg = map.getClass().getName() + " [" + LINE_SEPARATOR;
			Set<Object> keys = map.keySet();
			for (Object key : keys) {
				String itemString = "%s -> %s" + LINE_SEPARATOR;
				Object value = map.get(key);
				if (value != null) {
					if (value instanceof String) {
						value = "\"" + value + "\"";
					} else if (value instanceof Character) {
						value = "\'" + value + "\'";
					}
				}
				msg += String.format(itemString, objectToString(key), objectToString(value));
			}
			return msg + "]";

		}
		if (object.getClass().isArray()) {
			return toString(object);
		}

		return object.toString();

	}

	public static String toString(Object object) {
		if (object == null) {
			return "null";
		}
		if (!object.getClass().isArray()) {
			return object.toString();
		}
		if (object instanceof boolean[]) {
			return Arrays.toString((boolean[]) object);
		}
		if (object instanceof byte[]) {
			return Arrays.toString((byte[]) object);
		}
		if (object instanceof char[]) {
			return Arrays.toString((char[]) object);
		}
		if (object instanceof short[]) {
			return Arrays.toString((short[]) object);
		}
		if (object instanceof int[]) {
			return Arrays.toString((int[]) object);
		}
		if (object instanceof long[]) {
			return Arrays.toString((long[]) object);
		}
		if (object instanceof float[]) {
			return Arrays.toString((float[]) object);
		}
		if (object instanceof double[]) {
			return Arrays.toString((double[]) object);
		}
		if (object instanceof Object[]) {
			return Arrays.deepToString((Object[]) object);
		}
		return "Couldn't find a correct type for the array object";
	}
}
