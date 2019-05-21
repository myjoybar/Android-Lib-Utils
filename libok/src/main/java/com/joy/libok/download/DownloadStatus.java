package com.joy.libok.download;

/**
 * Created by tsy on 2016/11/25.
 */

public class DownloadStatus {
    public static final String STATUS_DEFAULT = "initial";        //初始状态
    public static final String STATUS_WAIT = "waiting";            //队列等待中
    public static final String STATUS_PAUSE ="pause";          //暂停
    public static final String STATUS_DOWNLOADING = "downloading";      //下载中
    public static final String STATUS_FINISH = "finished";          //下载完成
    public static final String STATUS_FAIL = "fail";             //下载失败
}
