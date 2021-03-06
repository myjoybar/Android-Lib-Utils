package com.joy.libok.request;

import com.joy.libok.OkHttpManager;
import com.joy.libok.response.callback.CommonOKCallback;
import com.joy.libok.response.callback.IResponseCallBackHandler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class GetRequestBuilder extends BaseRequestBuilder<GetRequestBuilder> {

  public GetRequestBuilder(String url) {
    super(url);
  }

  @Override
  public Response execute() throws IOException {
   // String requestUrl = url + getBuildParaMapUrl( getMapParams(), true);
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(urlBuilder(getUrl()).build());
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    Response response = call.execute();
    return response;
  }

  @Override
  public void execute(IResponseCallBackHandler responseCallBackHandler) {
   // String requestUrl = url + getBuildParaMapUrl( getMapParams(), true);
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(urlBuilder(getUrl()).build());
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    call.enqueue(new CommonOKCallback(responseCallBackHandler));

  }


}
