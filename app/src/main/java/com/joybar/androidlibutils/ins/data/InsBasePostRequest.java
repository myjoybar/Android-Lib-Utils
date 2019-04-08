package com.joybar.androidlibutils.ins.data;

import android.util.Log;

import com.google.gson.internal.$Gson$Types;
import com.joy.libbase.json.GsonUtil;
import com.joy.libok.OkHttpManager;
import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.InsGsonResponseHandler;
import com.joybar.androidlibutils.ins.sign.IgSignatureUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class InsBasePostRequest <T ,R extends InsBaseResponseData> extends InsBaseRequest<R> {
	private static final String TAG = "InsBasePostRequest";
	final T mRequestData;

	public InsBasePostRequest(T requestData) {
		mRequestData = requestData;
	}


	@Override
	void execute() {

		String payload = IgSignatureUtils.buildBodySignContent(GsonUtil.parseBeanToStr(mRequestData));
		OkHttpManager.getInstance()
				.post(getRequestUrl())
				.addBodyContent(payload)
				.addHeaders(IGConfig.getHeadersPHp3())
				.execute(new InsGsonResponseHandler<R>(getType()) {
					@Override
					public void onSuccess(int statusCode, R insBaseData) {
						Log.d(TAG, String.format("request %s success, statusCode = %s", getActionUrl(), statusCode));
						if (null != mInsRequestCallBack) {
							mInsRequestCallBack.onSuccess(statusCode, insBaseData);
						}
					}

					@Override
					public void onFailure(int errorCode, String errorMsg) {
						super.onFailure(errorCode, errorMsg);
						Log.d(TAG, String.format("request %s success ,errorCode= %s , errorMsg = %s", getActionUrl(), errorCode,
								errorMsg));
						if (null != mInsRequestCallBack) {
							mInsRequestCallBack.onFailure(errorCode, errorMsg);
						}
					}
				});
	}

	protected Type getType(){
		Class<?> classZ = getClass();
		Type type = classZ.getGenericSuperclass();    //反射获取带泛型的class
		if (type instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameter = (ParameterizedType) type;      //获取所有泛型
		return $Gson$Types.canonicalize(parameter.getActualTypeArguments()[1]);  //将泛型转为type,这里共有两个泛型
	}


}
