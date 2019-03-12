package com.joybar.library.net.retrofit.jsonconvert;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.joybar.library.net.retrofit.exception.ApiException;
import com.joybar.library.net.retrofit.exception.ResponseFormatException;
import com.joybar.library.net.retrofit.exception.UnknownException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by joybar on 5/23/16.
 */
public class GsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {
    private static String TAG = "GsonResponseBodyConverter";

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }
    @Override
	public T convert(ResponseBody value) throws IOException {
        String result = value.string();
        JSONObject jsonObject = null;
        try {
            Log.d(TAG,"result="+result);
            jsonObject = new JSONObject(result);
            //针对获取微信登陆Token成功的解析
            if(jsonObject.has("access_token")&&jsonObject.has("refresh_token")&&jsonObject.has("openid")&&jsonObject.has("scope")){
                return adapter.fromJson(result);
            }

            //针对获取微信User信息成功的解析
            if(jsonObject.has("openid")&&jsonObject.has("nickname")&&jsonObject.has("sex")&&jsonObject.has("headimgurl")){
                return adapter.fromJson(result);
            }

            //针对获取微信登陆Token和用户信息错误提示，暂不处理
            //{"errcode":40002,"errmsg":"invalid grant_type, hints: [ req_id: mkFuea0043ns81 ]"}
            if(jsonObject.has("errcode")&&jsonObject.has("errmsg")){
                Log.d(TAG,"wx_error="+result);
                return null;
            }

            throwException(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new ResponseFormatException("responseData format error");
        }
        if (jsonObject.isNull("data")) {
            Log.d(TAG,"data==null");
            return null;
        }

        try {
            if(jsonObject.get("data") instanceof JSONObject){
                JSONObject object = (JSONObject) jsonObject.get("data");
                if(object.length()==1){
                    String key = object.keys().next();
                    Object v = object.get(key);

                    if(v.toString().startsWith("[")){
                        String data = v.toString();
                        Log.d(TAG,"New——data_josn_List="+ data);
                        return adapter.fromJson(data);
                    }else if(!(v instanceof JSONObject) ){
                        JSONObject jsonObjectNew = new JSONObject();
                        jsonObjectNew.put("data",v);
                        //singleData
                        Log.d(TAG,"New——data_string_single_data="+ jsonObjectNew.toString());
                        return adapter.fromJson(jsonObjectNew.toString());
                    }else{
                        String data = v.toString();
                        Log.d(TAG,"New——data_josnobjet="+ data);
                        return adapter.fromJson(data);
                    }
                }else{
                    //返回没有嵌套的对象
                    return adapter.fromJson(object.toString());
                }
            }
            //截取code msg;
            String data = jsonObject.getString("data");

            Log.d(TAG,"data="+ data);
            return adapter.fromJson(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
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
