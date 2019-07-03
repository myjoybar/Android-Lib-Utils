package com.joy.libok.request;

import android.text.TextUtils;

import com.joy.libok.OkHttpManager;
import com.joy.libok.response.callback.CommonOKCallback;
import com.joy.libok.response.callback.IResponseCallBackHandler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostRequestBuilder<T extends PostRequestBuilder> extends
    BaseRequestBuilder<PostRequestBuilder> {

  private static final MediaType MEDIA_TYPE_JSON = MediaType
      .parse("application/json; charset=utf-8");//mdiatype 这个需要和服务端保持一致
  protected String bodyContent = "";//和mMapParams 互斥
  private MediaType mediaType;

  public PostRequestBuilder(String url) {
    super(url);
  }


  public T addMediaType(MediaType mediaType) {
    this.mediaType = mediaType;
    return (T) this;

  }


  public T addBodyContent(String bodyContent) {
    this.bodyContent = bodyContent;
    return (T) this;

  }

  @Override
  public Response execute() throws IOException {
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(urlBuilder(getUrl()).build());
    RequestBody body = RequestBody.create(getMediaType(), getBodyContent());
    requestBuilder.post(body);
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    Response response = call.execute();
    return response;
  }

  @Override
  public void execute(IResponseCallBackHandler responseCallBackHandler) {
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(urlBuilder(getUrl()).build());
    RequestBody body = RequestBody.create(getMediaType(), getBodyContent());
    requestBuilder.post(body);
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    call.enqueue(new CommonOKCallback(responseCallBackHandler));

  }

  private String getBodyContent() {
    if (TextUtils.isEmpty(bodyContent)) {
     // throw new IllegalArgumentException(String.format("the content can not be null !"));
    }
    return bodyContent;
  }

//  private HttpUrl.Builder urlBuilder(String url) {
//    HttpUrl.Builder queryParameterBuilder = HttpUrl.parse(url).newBuilder();
//    if (mMapParams != null && !mMapParams.isEmpty()) {
//      for (String key : mMapParams.keySet()) {
//        queryParameterBuilder.addQueryParameter(key, mMapParams.get(key));
//      }
//    }
//    return queryParameterBuilder;
//  }

  private MediaType getMediaType() {
    if (null == mediaType) {
      mediaType = MEDIA_TYPE_JSON;
    }
    return mediaType;
  }

}
