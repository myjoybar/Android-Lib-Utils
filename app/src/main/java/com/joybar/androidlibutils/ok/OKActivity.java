package com.joybar.androidlibutils.ok;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.joy.libok.OkHttpManager;
import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.download.DownloadTask;
import com.joy.libok.download.DownloadTaskListener;
import com.joy.libok.interceptors.ClearInvalidResponseInterceptor;
import com.joybar.androidlibutils.R;

import java.io.File;

/**
 * @author Joy
 * @description
 * @date 2019/5/20
 */
public class OKActivity  extends AppCompatActivity {
	private static final String TAG = "OKActivity";

	private Context mContext;
	String url1 = "http://pic32.nipic.com/20130823/13339320_183302468194_2.jpg";
	public static void launch(Context context) {
	  Intent intent = new Intent(context, OKActivity.class);
	  context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ok);
		initOkManager();
		mContext = this;
		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				download(url1);
			}
		});


	}

	private void initOkManager() {
		OKConfigData okConfigData = new OKConfigData();
		okConfigData.getInterceptors().add(new ClearInvalidResponseInterceptor());
		OkHttpManager.getInstance().init(okConfigData );
	}

	private void download(String url){
		DownloadTask downloadTask = new DownloadTask(url);
		String fileName = url.substring(url.lastIndexOf("/") + 1);
		String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() +"Libutis/" +fileName;
		downloadTask.setFilePath(filePath);
		downloadTask.doStart(new DownloadTaskListener() {
			@Override
			public void onStart(String taskId, long completeBytes, long totalBytes) {
				Log.d(TAG, "onStart ,mTotalBytes = "+totalBytes );
			}

			@Override
			public void onProgress(String taskId, long currentBytes, long totalBytes) {
				Log.d(TAG, "onProgress ,currentBytes = "+currentBytes+",totalBytes = " +totalBytes);
			}

			@Override
			public void onPause(String taskId, long currentBytes, long totalBytes) {

			}

			@Override
			public void onFinish(String taskId, File file) {
				Log.d(TAG, "onFinish ,file = "+file.getAbsolutePath());
			}

			@Override
			public void onFailure(String taskId, String error_msg) {

			}
		});
	}


}
