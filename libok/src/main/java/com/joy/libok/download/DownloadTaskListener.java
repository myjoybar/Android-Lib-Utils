package com.joy.libok.download;

import java.io.File;

/**
 * Created by tsy on 2016/11/24.
 */

public interface DownloadTaskListener {

    void onStart(String taskId, long completeBytes, long totalBytes);


    void onProgress(String taskId, long currentBytes, long totalBytes);


    void onPause(String taskId, long currentBytes, long totalBytes);


    void onFinish(String taskId, File file);


    void onFailure(String taskId, String error_msg);
}
