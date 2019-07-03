package com.joy.libok.request;

import java.io.IOException;
import java.util.Map;

import com.joy.libok.OkHttpManager;
import com.joy.libok.response.callback.CommonOKCallback;
import com.joy.libok.response.callback.IResponseCallBackHandler;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public class PostFormRequestBuilder<T extends PostFormRequestBuilder> extends
    BaseRequestBuilder<PostFormRequestBuilder> {

  private static final MediaType MEDIA_TYPE_JSON = MediaType
      .parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致
  public PostFormRequestBuilder(String url) {
    super(url);
  }


  @Override
  public Response execute() throws IOException {
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(getUrl());
    FormBody.Builder encodingBuilder = new FormBody.Builder();
    appendParams(encodingBuilder,  getMapParams());
    requestBuilder.post(encodingBuilder.build());
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    Response response = call.execute();
    return response;
  }

  @Override
  public void execute(IResponseCallBackHandler responseCallBackHandler) {
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(getUrl());
    FormBody.Builder encodingBuilder = new FormBody.Builder();
    appendParams(encodingBuilder,  getMapParams());
    requestBuilder.post(encodingBuilder.build());
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    call.enqueue(new CommonOKCallback(responseCallBackHandler));

  }

  private void appendParams(FormBody.Builder builder, Map<String, String> params) {
    if (params != null && !params.isEmpty()) {
      for (String key : params.keySet()) {
        builder.add(key, params.get(key));
      }
    }
  }


}
