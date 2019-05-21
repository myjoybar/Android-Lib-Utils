package com.joy.libok.response.responsehandler;

import com.joy.libok.response.callback.IResponseCallBackHandler;

import java.io.File;

import okhttp3.Response;


/**
 *
 */
public abstract class DownloadResponseHandler implements IResponseCallBackHandler {


    public void onStart(long totalBytes) {

    }

    public abstract void onFinish(File downloadFile);
    public abstract void onFailure(String error_msg);

    @Override
    public void onCancel() {

    }



    @Override
    public void onStart() {

    }

    @Override
    public void onSuccess(Response response) {

    }

    @Override
    public void onFailure(int errorCode, String errorMsg) {

    }


    @Override
    public void onFinish() {

    }
}
