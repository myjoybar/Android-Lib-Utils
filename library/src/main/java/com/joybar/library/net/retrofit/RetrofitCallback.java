package com.joybar.library.net.retrofit;

import android.content.Context;

import com.joybar.library.net.retrofit.errorcode.ErrorCode;
import com.joybar.library.net.retrofit.exception.ApiException;
import com.joybar.library.net.retrofit.exception.AuthenticationException;
import com.joybar.library.net.retrofit.exception.ResponseFormatException;
import com.joybar.library.net.retrofit.exception.UnknownException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by joybar on 5/23/16.
 */
public abstract class RetrofitCallback<T> implements Callback<T> {

	private Context mContext;
	private boolean mLoading;

	public RetrofitCallback() {
		this(null);
	}

	public RetrofitCallback(Context context) {
		this.mContext = context;
	}

	protected boolean showToast() {
		return false;
	}

	public abstract void onSuccess(T t);

	public abstract void onFailure(Error error);

	@Override
	public void onResponse(Call<T> call, Response<T> response) {
		if (null != response) {
			if (response.isSuccessful() && response.body() != null) {
				onSuccess(response.body());
			} else {
				Error error = parseCode(response.code());
				onFailure(error);
				if (showToast()) {
					// show toast error.msg
				}
			}
		}


	}

	@Override
	public void onFailure(Call<T> call, Throwable throwable) {
		Error error = parseThrowable(throwable);
		onFailure(error);
		if (showToast()) {
			// show toast error.msg
		}
	}


	private Error parseThrowable(Throwable throwable) {
		Error error = new Error();
		if (throwable instanceof ResponseFormatException) {
			error.code = ErrorCode.FORMAT;
			error.msg = "内容与往常不一致,请稍后重试.";
		} else if (throwable instanceof ApiException) {
			error.code = ErrorCode.API;
			error.msg = throwable.getMessage();
		} else if (throwable instanceof UnknownException) {
			error.code = ErrorCode.UNKNOWN;
			error.msg = "系统发生了未知的错误,请稍后重试.";
		} else if (throwable instanceof AuthenticationException) {
			error.code = ErrorCode.AUTHENTICATION;
			error.msg = "用户信息似乎失效,请重新登陆.";
		}
		return error;
	}

	/**
	 * 解析ResponseCode
	 *
	 * @param code
	 * @return
	 */
	private Error parseCode(int code) {
		Error error = new Error();
		error.code = code;
		if (error.code >= 400 && error.code < 500) {
			//4xx client error
			error.msg = "发生了一些意外,请稍后重试.";
		} else {
			//5xx server error
			error.msg = "服务器正在开小差,请稍后重试.";
		}
		return error;
	}

	public static class Error {
		public int code;
		public String msg;
	}

}
