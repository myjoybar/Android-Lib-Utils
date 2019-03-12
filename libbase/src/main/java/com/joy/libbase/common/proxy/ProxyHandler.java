package com.joy.libbase.common.proxy;


import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by joybar on 2019/1/4.
 */
public class ProxyHandler<T> implements InvocationHandler {

	private HookListener mHookListener;
	private SoftReference mSoftReferenceTarget;
	// private WeakReference weakReferenceTarget;
	private String[] mCallMethodNames;
	private String[] mIgnoreMethodNames;

	public ProxyHandler(T target, HookListener hookListener, String[] callMethodNames, String[] ignoreMethodNames) {
		this.mHookListener = hookListener;
		this.mSoftReferenceTarget = new SoftReference(target);
		// this.weakReferenceTarget = new WeakReference(target);
		this.mCallMethodNames = callMethodNames;
		this.mIgnoreMethodNames = ignoreMethodNames;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (null == mSoftReferenceTarget || null == mSoftReferenceTarget.get()) {
			return null;
		}
		String methodName = method.getName();
		Object obj = null;
		if (isHookMethod(methodName) && mHookListener != null && !mHookListener.isInterceptedBeforeHookedMethod(method, args)) {
			obj = mHookListener.hookedMethod(mSoftReferenceTarget.get(), method, args);
			mHookListener.afterHookedMethod(method, args);
		} else {
			obj = method.invoke(mSoftReferenceTarget.get(), args);
		}
		return obj;
	}

	boolean isHookMethod(String methodName) {
		if (null == mCallMethodNames && mIgnoreMethodNames == null) {
			return true;
		}
		if (null != mCallMethodNames) {
			int lengthCallMethodNames = mCallMethodNames.length;
			for (int i = 0; i < lengthCallMethodNames; i++) {
				if (methodName.equals(mCallMethodNames[i])) {
					return true;
				}
			}
		}
		if (null != mIgnoreMethodNames) {
			int lengthIgnoreMethodNames = mIgnoreMethodNames.length;
			for (int i = 0; i < lengthIgnoreMethodNames; i++) {
				if (methodName.equals(mIgnoreMethodNames[i])) {
					return false;
				}
			}
		}

		if (mCallMethodNames != null && mIgnoreMethodNames == null) {
			return false;
		}
		if (mCallMethodNames == null && mIgnoreMethodNames != null) {
			return true;
		}

		return true;

	}

}
