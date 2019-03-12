package com.joy.libbase.common.proxy;

/**
 * Created by joybar on 2019/1/30.
 */

/**
 * Created by joybar on 2019/1/4.
 */
public class ProxyCreator<T> {

	private final Class<?> mInterfaceClass;
	private final Object mTarget;
	private String[] mCallMethodNames;
	private String[] mIgnoreMethodNames;
	private ProxyHandler mProxyHandler;

	public ProxyCreator(Class<?> interfaceClass, Object target) {
		this.mInterfaceClass = interfaceClass;
		this.mTarget = target;
	}

	public static ProxyCreator on(Class<?> interfaceClass, Object onTarget) {
		return new ProxyCreator(interfaceClass, onTarget);
	}

	public ProxyCreator callAll() {
		return this;
	}

	public ProxyCreator call(String... callMethodNames) {
		this.mCallMethodNames = callMethodNames;
		return this;
	}


	public ProxyCreator but(String... ignoreMethodNames) {
		this.mIgnoreMethodNames = ignoreMethodNames;
		return this;
	}

	public Object create() {
		return create(null);
	}

	public Object create(HookListener hookListener) {
		mProxyHandler = new ProxyHandler(mTarget, hookListener, mCallMethodNames, mIgnoreMethodNames);
		Object object = java.lang.reflect.Proxy.newProxyInstance(mInterfaceClass.getClassLoader(), new Class[]{mInterfaceClass}, mProxyHandler);
		return object;

	}

}
