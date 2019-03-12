package com.joy.libbase.base.app.performance;

import android.os.StrictMode;

/**
 * Log tag: StrictMode
 * https://blog.csdn.net/fight__fight/article/details/78469516
 */
public class StrictModeManager {

	private static class StrictModeManagerHolder {
		private static StrictModeManager INSTANCE = new StrictModeManager();

	}

	public static StrictModeManager getInstance() {
		return StrictModeManagerHolder.INSTANCE;
	}

	/**
	 * 开启所有的detectXX系列方法
	 */
	public void monitorAllThreadPolicy(){
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//				.detectAll()//开启所有的detectXX系列方法
				.detectDiskReads() //磁盘读写检查
				.detectDiskWrites() //磁盘读写检查
				.detectNetwork() //检查UI线程中是否有网络请求操作
				.penaltyDialog()//弹出违规提示框
				.penaltyLog()//在Logcat中打印违规日志
				.build());
	}


	/**
	 * 检测Activity泄露
	 */
	public void monitorActivityLeaks(){

		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectActivityLeaks()//检测Activity泄露
				.penaltyLog()//在Logcat中打印违规日志
				.build());
	}

	/**
	 * 检测数据库
	 */
	public void monitorSqlLite(){
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects()
				.detectLeakedClosableObjects()
				.penaltyLog()
				.build());
	}


}
