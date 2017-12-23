package com.joybar.library.io.file;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by joybar on 2017/12/22.
 */

public class SDCardUtil {

	/**
	 * 判断SDCard是否可用
	 *
	 * @return  <code>true</code> if the sdcard is enable.
	 * Else <code>false</code> .
	 */
	public static boolean isSDCardEnable()
	{
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}

	/**
	 * 获取SD卡路径
	 *
	 * @return the sdcard path
	 */
	public static String getSDCardPath()
	{
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}


	/**
	 * 获取系统存储路径
	 *
	 * @return the root directory path
	 */
	public static String getRootDirectoryPath()
	{
		return Environment.getRootDirectory().getAbsolutePath()+ File.separator;
	}


	/**
	 * 获取包名下files的路径
	 * @param context the  context
	 * @return the file of path
	 */
	public static String getFilePath(Context context){
		return context.getApplicationContext().getFilesDir().getPath()+ File.separator;
	}


	public static String getCachePath(Context context){
		return context.getApplicationContext().getCacheDir().getPath()+ File.separator;
	}

	/**
	 * 获取SD卡的剩余容量 单位byte
	 *
	 * @return the size of the sdcard
	 */
	public static long getSDCardAllSize()
	{
		if (isSDCardEnable())
		{
			StatFs stat = new StatFs(getSDCardPath());
			// 获取空闲的数据块的数量
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			// 获取单个数据块的大小（byte）
			long freeBlocks = stat.getAvailableBlocks();
			return freeBlocks * availableBlocks;
		}
		return 0;
	}
	/**
	 * 获取指定路径所在空间的剩余可用容量字节数，单位byte
	 *
	 * @param filePath the filePath
	 * @return 容量字节 SDCard可用空间，内部存储可用空间
	 */
	public static long getFreeBytes(String filePath)
	{
		// 如果是sd卡的下的路径，则获取sd卡可用容量
		if (filePath.startsWith(getSDCardPath()))
		{
			filePath = getSDCardPath();
		} else
		{// 如果是内部存储的路径，则获取内存存储的可用容量
			filePath = Environment.getDataDirectory().getAbsolutePath();
		}
		StatFs stat = new StatFs(filePath);
		long availableBlocks = (long) stat.getAvailableBlocks() - 4;
		return stat.getBlockSize() * availableBlocks;
	}

}
