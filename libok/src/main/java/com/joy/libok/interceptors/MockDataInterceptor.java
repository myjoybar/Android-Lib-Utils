package com.joy.libok.interceptors;

import java.io.IOException;
import java.util.HashMap;

import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.test.log.LLog;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Joy on 2019-06-21
 */
public class MockDataInterceptor implements Interceptor {

  private OKConfigData mOKConfigData;

  public MockDataInterceptor(OKConfigData OKConfigData) {
    mOKConfigData = OKConfigData;
  }


  @Override
  public Response intercept(Chain chain) throws IOException {
    if (null != mOKConfigData) {
      HashMap<String, String> mockDataMap = mOKConfigData.getMockDataMap();
      if (null != mockDataMap) {
        HttpUrl httpUrl = chain.request().url();
        String url = httpUrl.url().toString();
        LLog.d("MockDataInterceptor", "mock url = " + url);
        if (mockDataMap.containsKey(url)) {
          return new Response.Builder()
              .code(200)
              .message("mock response")
              .request(chain.request())
              .protocol(Protocol.HTTP_2)
              .body(ResponseBody.create(MediaType.parse("application/json"), mockDataMap.get(url)))
              .addHeader("content-type", "application/json")
              .build();
        }
      }
    }
    Response response = chain.proceed(chain.request());
    return response;
  }
}
