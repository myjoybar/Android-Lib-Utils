package com.joy.libok.request;


import com.joy.libok.OkHttpManager;
import com.joy.libok.interceptors.DownloadProgressInterceptor;
import com.joy.libok.response.callback.DownloadOKCallback;
import com.joy.libok.response.callback.IResponseCallBackHandler;
import com.joy.libok.response.responsehandler.DownloadResponseHandler;
import com.joy.libok.test.log.LLog;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by joybar on 2019/2/12.
 */
public class DownloadBuilder extends BaseRequestBuilder<DownloadBuilder> {

  private static final String TAG = "DownloadBuilder";
  private String mFilePath = "";

  private Long mCompleteBytes = 0L;    //已经完成的字节数 用于断点续传

  private boolean isForcedUpdated;

  public DownloadBuilder(String url) {
    super(url);
    tag(url);
  }

  public DownloadBuilder setFilePath(String mFilePath) {
    this.mFilePath = mFilePath;
    return this;
  }

  public DownloadBuilder setCompleteBytes(Long completeBytes) {
    if (completeBytes > 0L) {
      this.mCompleteBytes = completeBytes;
    }
    return this;
  }

  public DownloadBuilder setForcedUpdated(boolean forcedUpdated) {
    isForcedUpdated = forcedUpdated;
    return this;
  }

  @Override
  public Response execute() throws IOException {

   // String requestUrl = url + getBuildParaMapUrl(mMapParams, true);
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(urlBuilder(getUrl()).build());
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    Response response = call.execute();
    return response;

  }


  @Override
  public void execute(final IResponseCallBackHandler responseCallBackHandler) {

    if (!isForcedUpdated && checkFilePathExists(mFilePath, mCompleteBytes)) {
      DownloadResponseHandler downloadResponseHandler = (DownloadResponseHandler) responseCallBackHandler;
      File file = new File(mFilePath);
      downloadResponseHandler.onFinish(file);
      return;
    }

   // String requestUrl = url + getBuildParaMapUrl(mMapParams, true);
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(urlBuilder(getUrl()).build());
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkBuilder()
        .addNetworkInterceptor(new DownloadProgressInterceptor(responseCallBackHandler))
        .build()
        .newCall(request);
    //Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    call.enqueue(new DownloadOKCallback(mCompleteBytes, mFilePath, (DownloadResponseHandler) responseCallBackHandler));


  }

  private boolean checkFilePathExists(String filePath, Long completeBytes) {
    File file = new File(filePath);
    if (file.exists()) {
      LLog.d(TAG, String.format("the file：%s exists", file.getName()));
      return true;
    } else {
      LLog.d(TAG, String.format("the file：%s not exists", file.getName()));
    }

    if (completeBytes > 0L) {       //如果设置了断点续传 则必须文件存在
      LLog.d(TAG, String.format("the Breakpoint downloading file：%s not exists", file.getName()));
    }

    if (filePath.endsWith(File.separator)) {
      LLog.d(TAG, String.format("create file：%s failure", filePath));
    }

    //判断目标文件所在的目录是否存在
    if (!file.getParentFile().exists()) {
      if (!file.getParentFile().mkdirs()) {
        LLog.d(TAG, String.format("create file dir：%s failure", filePath));
      }
    }
    return false;
  }

}
