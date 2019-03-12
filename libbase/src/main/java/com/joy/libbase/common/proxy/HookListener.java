package com.joy.libbase.common.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by joybar on 2019/1/30.
 */

public interface HookListener {


	boolean isInterceptedBeforeHookedMethod(Method method, Object[] args);

	Object hookedMethod(Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;


	void afterHookedMethod(Method method, Object[] args);
}
