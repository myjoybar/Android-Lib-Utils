package com.joybar.library.net.retrofit.basemodel;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.joybar.library.net.retrofit.exception.ApiException;
import com.joybar.library.net.retrofit.exception.ResponseFormatException;
import com.joybar.library.net.retrofit.exception.UnknownException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by joybar on 5/23/16.
 */
public class GsonResponseBodyConverterBaseModel<T> implements Converter<ResponseBody, T> {

    private final TypeAdapter<T> adapter;
    private Class<?> mJsonElement;

    GsonResponseBodyConverterBaseModel(TypeAdapter<T> adapter, Class<?> jsonElement) {
        this.mJsonElement = jsonElement;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Gson gson = new Gson();
        BaseModel baseModel = gson.fromJson(response, BaseModel.class);
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(response);
            if (jsonObject.has("data") && !jsonObject.isNull("data")) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                baseModel.setDataStr(dataObject.toString());
                throwException(jsonObject);
                return (T) baseModel;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new ResponseFormatException("responseData format error");

        }


        Type t = null;
        if (baseModel.getDataStr().startsWith("[")) {
            t = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, BaseModel.class,
                    com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, ArrayList.class,
                            mJsonElement));
            return (T) gson.fromJson(response, t);
        } else {
            if (mJsonElement.getName().equals(ArrayList.class.getName())) {
                t = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, BaseModel.class,
                        com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, mJsonElement,
                                mJsonElement));

            } else {
                t = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, BaseModel.class,
                        mJsonElement);
            }
            return (T) gson.fromJson(response, t);


        }


        //  return null;
    }


    /**
     * 抛出异常,如果存在的话
     *
     * @param jsonObject responsedata
     */
    private void throwException(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.has("code")) {
            throw new ResponseFormatException("responseData format error");
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
