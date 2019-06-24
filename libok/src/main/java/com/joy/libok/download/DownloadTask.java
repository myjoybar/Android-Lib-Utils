package com.joy.libok.download;

import android.os.Environment;
import android.text.TextUtils;

import com.joy.libok.OkHttpManager;
import com.joy.libok.response.responsehandler.DownloadResponseHandler;
import com.joy.libok.test.log.LLog;

import java.io.File;

/**
 * @author Joy
 * @description
 * @date 2019/5/20
 */
public class DownloadTask {
	private static final String TAG = "DownloadTask2";
	static final String sDCardPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	private String mUrl;        //下载url
	private String mFilePath;   //保存文件path
	private long mCompleteBytes;    //断点续传 已经完成的bytes
	private long mCurrentBytes;     //当前总共下载了的bytes
	private long mTotalBytes;       //文件总bytes
	private String mStatus;       //Task状态
	private long mNextSaveBytes = 0L;       //距离下次保存下载进度的bytes

	private DownloadTaskListener mDownloadTaskListener;    //task监听事件
	private DownloadResponseHandler mDownloadResponseHandler;       //下载监听


	public void setFilePath(String filePath) {
		mFilePath = filePath;
	}

	public DownloadTask(String url) {
		this.mUrl = url;
		setStatus(DownloadStatus.STATUS_DEFAULT);//初始默认状态
		//myokhttp的下载监听
		mDownloadResponseHandler = new DownloadResponseHandler() {
			@Override
			public void onStart(long totalBytes) {
				LLog.d(TAG, "load onStart mTotalBytes= " + mTotalBytes);
				mTotalBytes = mCompleteBytes + totalBytes;      //下载总bytes等于上次下载的bytes加上这次断点续传的总bytes

				if (null != mDownloadTaskListener) {
					mDownloadTaskListener.onStart(mUrl, mCompleteBytes, mTotalBytes);
				}

			}

			@Override
			public void onFinish(File download_file) {
				LLog.d(TAG, "load  onFinish ");
				setStatus(DownloadStatus.STATUS_FINISH);
				mCurrentBytes = mTotalBytes;
				mCompleteBytes = mTotalBytes;

				if (null != mDownloadTaskListener) {
					mDownloadTaskListener.onFinish(mUrl, download_file);
				}

			}


			@Override
			public void onProgress(long currentBytes, long totalBytes) {
				LLog.d(TAG, String.format("mStatus = %s,totalBytes = %s,currentBytes = %s", mStatus, totalBytes, currentBytes));
				if (mStatus == DownloadStatus.STATUS_DOWNLOADING) {
					mNextSaveBytes += mCompleteBytes + currentBytes - mCurrentBytes;        //叠加每次增加的bytes
					mCurrentBytes = mCompleteBytes + currentBytes;      //当前已经下载好的bytes
					if (null != mDownloadTaskListener) {
						mDownloadTaskListener.onProgress(mUrl, mCurrentBytes, mTotalBytes);
					}
				} else if (mStatus == DownloadStatus.STATUS_PAUSE) {
					mCompleteBytes = mCurrentBytes;
					OkHttpManager.getInstance().cancelCallByTag(mUrl);
				} else {
					mCompleteBytes = mCurrentBytes;
					OkHttpManager.getInstance().cancelCallByTag(mUrl);
				}
			}


			@Override
			public void onCancel() {
				if (null != mDownloadTaskListener) {
					mDownloadTaskListener.onPause(mUrl, mCurrentBytes, mTotalBytes);
				}

			}

			@Override
			public void onFailure(String error_msg) {
				setStatus(DownloadStatus.STATUS_FAIL);
				if (null != mDownloadTaskListener) {
					mDownloadTaskListener.onFailure(mUrl, error_msg);
				}
			}
		};

	}

	public void doStart(DownloadTaskListener mDownloadTaskListener) {
		doStart(mDownloadTaskListener,false);
	}

	public void doStart(DownloadTaskListener mDownloadTaskListener,boolean forcedUpdated) {
		if (mStatus == DownloadStatus.STATUS_DOWNLOADING || mStatus == DownloadStatus.STATUS_FINISH) {
			return;
		}
		setStatus(DownloadStatus.STATUS_DOWNLOADING);
		this.mDownloadTaskListener = mDownloadTaskListener;
		OkHttpManager.getInstance()
				.download(mUrl)
				.setFilePath(getFilePath(mUrl))
				.setCompleteBytes(mCompleteBytes)
				.setForcedUpdated(forcedUpdated)
				.execute(mDownloadResponseHandler);

	}

	private String getFilePath(String url) {
		String filePath = mFilePath;
		if (TextUtils.isEmpty(mFilePath)) {
			if (!TextUtils.isEmpty(url)) {
				String fileName = url.substring(url.lastIndexOf("/") + 1);
				filePath = sDCardPath + fileName;
				//filePath = sDCardPath +"Libutis/" +fileName;
			}
		}
		LLog.d(TAG, "the init filePath = "+filePath);
		return filePath;
	}


	private void setStatus(String status){
		mStatus = status;
	}
}
