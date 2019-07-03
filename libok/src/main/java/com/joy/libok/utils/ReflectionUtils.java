package com.joy.libok.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 主要根据请求的时间戳判断，是否需要丢弃response，实质上是把code置为一个无效值
 * Created by joybar on 2019/2/12.
 */
public class ReflectionUtils {

	/**
	 * 获取私有成员变量的值
	 *
	 * @param instance
	 * @param filedName
	 * @return
	 */
	public static Object getPrivateField(Object instance, String filedName) throws NoSuchFieldException, IllegalAccessException {
		Field field = instance.getClass().getDeclaredField(filedName);
		field.setAccessible(true);
		return field.get(instance);
	}

	/**
	 * 设置私有成员的值
	 *
	 * @param instance
	 * @param fieldName
	 * @param value
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static void setPrivateField(Object instance, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
		Field field = instance.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(instance, value);
	}

	/**
	 * 访问私有方法
	 *
	 * @param instance
	 * @param methodName
	 * @param classes
	 * @param objects
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static Object invokePrivateMethod(Object instance, String methodName, Class[] classes, String objects) throws NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		Method method = instance.getClass().getDeclaredMethod(methodName, classes);
		method.setAccessible(true);
		return method.invoke(instance, objects);

	}
}
