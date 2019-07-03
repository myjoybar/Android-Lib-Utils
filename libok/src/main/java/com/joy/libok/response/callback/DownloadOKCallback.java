package com.joy.libok.response.callback;

import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import com.joy.libok.handler.OKGlobalHandler;
import com.joy.libok.response.responsehandler.DownloadResponseHandler;
import com.joy.libok.test.log.LLog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadOKCallback<T> implements Callback {

  private static String TAG = "DownloadOKCallback";
  private final DownloadResponseHandler mResponseHandler;
  private Long mCompleteBytes = 0l;
  private String mFilePath;

  public DownloadOKCallback(Long completeBytes, String filePath,
      DownloadResponseHandler responseHandler) {
    mResponseHandler = responseHandler;
    mCompleteBytes = completeBytes;
    mFilePath = filePath;

  }


  @Override
  public void onFailure(Call call, final IOException e) {
    LLog.d(TAG,
        String.format("request url %s fail, error msg %s", call.request().url(), e.getMessage()));
    OKGlobalHandler.getInstance().post(new Runnable() {
      @Override
      public void run() {
        mResponseHandler.onFailure(0, e.getMessage());
      }
    });
  }


  @Override
  public void onResponse(final Call call, final Response response) {
    if (response.isSuccessful()) {
      LLog.d(TAG, "onResponse isSuccessful");
      OKGlobalHandler.getInstance().post(new Runnable() {
        @Override
        public void run() {
        //  mResponseHandler.onStart(response.body().contentLength());
        }
      });
      if (response.header("Content-Range") == null
          || response.header("Content-Range").length() == 0) {
        //返回的没有Content-Range 不支持断点下载 需要重新下载
        mCompleteBytes = 0L;
      }
      String requestUrl = call.request().url().toString();

      try {
        saveFile(response, requestUrl, mFilePath, mCompleteBytes);
        final File file = new File(mFilePath);
        OKGlobalHandler.getInstance().post(new Runnable() {
          @Override
          public void run() {
            mResponseHandler.onFinish(file);
          }
        });
      } catch (final IOException e) {
        e.printStackTrace();
        OKGlobalHandler.getInstance().post(new Runnable() {
          @Override
          public void run() {
            OKGlobalHandler.getInstance().post(new Runnable() {
              @Override
              public void run() {
                mResponseHandler.onFailure(response.code(), e.getMessage());
              }
            });
          }
        });
      }


    } else {
      if (call.isCanceled()) {     //判断是主动取消还是别动出错
        OKGlobalHandler.getInstance().post(new Runnable() {
          @Override
          public void run() {
            mResponseHandler.onCancel();
          }
        });
      } else {

        final ResponseBody responseBody = response.body();
        try {
          final String responseStr = responseBody.string();
          OKGlobalHandler.getInstance().post(new Runnable() {
            @Override
            public void run() {
              mResponseHandler.onFailure(response.code(), responseStr);
            }
          });

        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          responseBody.close();
        }
      }


    }


  }

  private void saveFile(Response response, String requestUrl, String filePath, Long completeBytes)
      throws IOException {
    if (TextUtils.isEmpty(requestUrl)) {
      LLog.d(TAG, "the requestUrl is empty");
      return;
    }
    LLog.d(TAG, String.format("requestUrl = %s", requestUrl));

    InputStream is = null;
    byte[] buf = new byte[4 * 1024];           //每次读取4kb
    int len;
    RandomAccessFile file = null;

    try {
      is = response.body().byteStream();

      file = new RandomAccessFile(filePath, "rwd");
      if (completeBytes > 0L) {
        file.seek(completeBytes);
      }

      long complete_len = 0;
      final long total_len = response.body().contentLength();
      while ((len = is.read(buf)) != -1) {
        file.write(buf, 0, len);
        complete_len += len;

        //已经下载完成写入文件的进度
        final long final_complete_len = complete_len;
        OKGlobalHandler.getInstance().post(new Runnable() {
          @Override
          public void run() {
            if (mResponseHandler != null) {
              //使用DownloadProgressInterceptor
             // mResponseHandler.onProgress(final_complete_len, total_len);
            }
          }
        });
      }
    } finally {
      try {
        if (is != null) {
          is.close();
        }
      } catch (IOException e) {
      }
      try {
        if (file != null) {
          file.close();
        }
      } catch (IOException e) {
      }
    }
  }

}
