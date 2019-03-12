package com.joy.libbase.io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by joybar on 19/11/2017.
 */

public class FileUtil {

	/**
	 * save data to file
	 * @param data the save data
	 * @param fullPathName  "/res/test.txt"
	 * @return  <code>true</code> if save successfully.
	 * Else <code>false</code> fail to save.
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
	 * save data to file
	 * @param data the save data
	 * @param path "/res/"
	 * @param fileName "text.txt"
	 * @return  <code>true</code> if save successfully.
	 * Else <code>false</code> fail to save.
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
	 * read the file
	 * @param fullPathName  "/res/test.txt"
	 * @return  <code>true</code> if read successfully.
	 * Else <code>false</code> fail to read.
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
	 * read the file
	 * @param path "/res/"
	 * @param fileName "text.txt"
	 * @return  <code>true</code> if read successfully.
	 * Else <code>false</code> fail to read.
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
	 * delete all files in this file path
	 * @param absoluteFilePath the absoluteFilePath
	 * @return  <code>true</code> if delete successfully.
	 * Else <code>false</code> fail to delete.
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
