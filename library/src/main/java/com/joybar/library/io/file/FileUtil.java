package com.joybar.library.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by joybar on 19/11/2017.
 */

public class FileUtil {


	/**
	 * 保存文件
	 * @param data 数据内容
	 * @param fullPathName 绝对路径
	 * @return true 保存成功，false 保存失败
	 */
	public static boolean saveFile(String data,String fullPathName){
		int lastIndex = fullPathName.lastIndexOf(File.separator);
		String path = fullPathName.substring(0,lastIndex);
		String fileName = fullPathName.substring(lastIndex+1,fullPathName.length());
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(file, fileName);
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(data.getBytes());
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 保存文件
	 * @param data 数据内容
	 * @param path 绝对路径
	 * @param fileName 文件名
	 * @return true 保存成功，false 保存失败
	 */
	public static boolean saveFile(String data,String path,String fileName){
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(file, fileName);
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(data.getBytes());
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 读取文件
	 * @param fullPathName
	 * @return
	 */
	public static String readFile(String fullPathName) {
		File file = new File(fullPathName);
		if (!file.isFile()) {
			try {
				throw new Exception("this file: " +fullPathName+" is not a file");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] b = new byte[in.available()];
			int read = in.read(b);
			while (read != -1) {
				sb.append(new String(b));
				read = in.read(b);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sb.toString();
	}
	/**
	 * 读取文件
	 * @param path
	 * @return
	 */
	public static String readFile(String path,String fileName) {
		File file = new File(path,fileName);
		if (!file.isFile()) {
			try {
				throw new Exception("this file: " +path+" is not a file");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream in = new FileInputStream(file);
			byte[] b = new byte[in.available()];
			int read = in.read(b);
			while (read != -1) {
				sb.append(new String(b));
				read = in.read(b);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return sb.toString();
	}



	/**
	 * 删除路径下的：文件和文件夹，包括当前文件夹
	 * @param absoluteFilePath ：绝对路径
	 * @return true:删除成功，false：删除失败
	 * 注意：当前路径不存在时，也返回true
	 */
	public static boolean deleteFile(String absoluteFilePath) {
		File file = new File(absoluteFilePath);
		try {
			if(!file.exists()){
				return true;
			}
			if (file.isFile()) {
				file.delete();
				return true;
			}
			if (!absoluteFilePath.endsWith(File.separator)){
				absoluteFilePath = absoluteFilePath + File.separator;
			}
			if (file.isDirectory()) {
				if (file.listFiles().length == 0) {
					file.delete();
				} else {
					File[] files = file.listFiles();
					for (File dirFile : files) {
						deleteFile(dirFile.getAbsolutePath());
					}
				}
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
