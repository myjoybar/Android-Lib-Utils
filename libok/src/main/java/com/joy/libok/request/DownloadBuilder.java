package com.joy.libok.request;

import com.joy.libok.OkHttpManager;
import com.joy.libok.response.callback.DownloadOKCallback;
import com.joy.libok.response.callback.IResponseCallBackHandler;
import com.joy.libok.response.responsehandler.DownloadResponseHandler;
import com.joy.libok.test.log.LLog;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Joy
 * @description
 * @date 2019/5/20
 */
public class DownloadBuilder extends BaseRequestBuilder<DownloadBuilder> {

  private static final String TAG = "DownloadBuilder";
  private String mFilePath = "";       //文件路径 （如果设置该字段则上面2个就不需要）

  private Long mCompleteBytes = 0L;    //已经完成的字节数 用于断点续传

  private Map<String, String> mHeaders; //临时header

  public DownloadBuilder(String url) {
    super(url);
    tag(url);
  }

  public DownloadBuilder setFilePath(String mFilePath) {
    this.mFilePath = mFilePath;
    return this;
  }

  public DownloadBuilder setCompleteBytes(Long completeBytes) {
    if(completeBytes > 0L) {
      this.mCompleteBytes = completeBytes;
    }
    return this;
  }

  @Override
  public Response execute() throws IOException {
    try {
      checkFilePath(mFilePath, mCompleteBytes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String requestUrl = url + getBuildParaMapUrl(mMapParams, true);
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(requestUrl);
    Request request = requestBuilder.build();
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    Response response = call.execute();
    return response;

  }


  @Override
  public void execute(final IResponseCallBackHandler responseCallBackHandler) {
    try {
      checkFilePath(mFilePath, mCompleteBytes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String requestUrl = url + getBuildParaMapUrl(mMapParams, true);
    Request.Builder requestBuilder = new Request.Builder();
    requestBuilder = addHeaders(requestBuilder, mMapHeaders);
    requestBuilder.url(requestUrl);
    Request request = requestBuilder.build();
//    Call call = OkHttpManager.getInstance().getOkBuilder()
//        .addNetworkInterceptor(new DownloadProgressInterceptor(responseCallBackHandler))
//        .build()
//        .newCall(request);
    Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
    call.enqueue(new DownloadOKCallback(mCompleteBytes,mFilePath,(DownloadResponseHandler)responseCallBackHandler));


  }

  private void checkFilePath(String filePath, Long completeBytes) throws Exception {
    File file = new File(filePath);
    if (file.exists()) {
      LLog.d(TAG, String.format("the file：%s exists", file.getName()));
      return;
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
  }

}
