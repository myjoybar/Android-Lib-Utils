package com.joy.libbase.net.retrofit.jsonconvert;

import com.google.gson.TypeAdapter;
import com.joy.libbase.net.retrofit.exception.ApiException;
import com.joy.libbase.net.retrofit.exception.ResponseFormatException;
import com.joy.libbase.net.retrofit.exception.UnknownException;
import com.joy.libbase.test.log.LLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by joybar on 5/23/16.
 */
public class GsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {
    private static String TAG = " GsonResponseBodyConverter";
    private final TypeAdapter<T> adapter;
    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }
    @Override
	public T convert(ResponseBody value) throws IOException {
        String result = value.string();
        JSONObject jsonObject = null;
        try {
            LLog.d(TAG,"GsonResponseBodyConverter result="+result);
            jsonObject = new JSONObject(result);
           // throwException(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new ResponseFormatException("GsonResponseBodyConverter format error");
        }
        if (jsonObject.isNull("data")) {
            return null;
        }
        return adapter.fromJson(result);
    }


    /**
     *  抛出异常,如果存在的话
     * @param jsonObject
     * @throws JSONException
     */
    private void throwException(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.has("code")) {
            throw new ResponseFormatException("GsonResponseBodyConverter responseData format error");
        }
        int code = jsonObject.getInt("code");
        switch (code) {
            case 0://接口异常
                throw new ApiException(jsonObject.getString("message"));
            case 1://正常
                break;
            default://未知
                throw new UnknownException("unknown error , code is " + code);
        }

    }
}
