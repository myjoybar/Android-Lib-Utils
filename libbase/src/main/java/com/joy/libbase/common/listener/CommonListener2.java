package com.joy.libbase.common.listener;

public abstract class CommonListener2<T, R> {

	public abstract void onSuccess(T data, R result);

	public void onFail(String message) {

	}

	public void onFail(int errorCode, String message) {

	}

}
