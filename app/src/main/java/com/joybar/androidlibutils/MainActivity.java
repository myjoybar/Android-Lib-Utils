package com.joybar.androidlibutils;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.joy.libbase.io.mmkv.MMKVManager;
import com.joybar.androidlibutils.data1.Student;
import com.joybar.library.common.log.L;
import com.joybar.library.common.wiget.SnackBarUtils;
import com.joybar.library.io.file.FileUtil;
import com.joybar.library.io.file.SDCardUtil;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "PermissionManager";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		testLog();
		testFile();
		testSnackBar();
		testMMKV();
	}


	private void testMMKV() {
		MMKVManager.getInstance().init(this);
		MMKVManager.getInstance().put("key1", "11");
		MMKVManager.getInstance().put("key2", true);
		MMKVManager.getInstance().put("key3", 111);
		MMKVManager.getInstance().put("key4", 20f);
		MMKVManager.getInstance().put("key5", 55d);

		MMKVManager.getInstance().put("key_id", "key_6", "value id");

		Student student = new Student();
		student.setAge(27);
		student.setName("Tom");

		MMKVManager.getInstance().saveObj("key_obj", student);


		Log.d("MMKVManager", "key1= " + MMKVManager.getInstance().getString("key1", ""));
		Log.d("MMKVManager", "key2= " + MMKVManager.getInstance().getBoolean("key2", false) + "");
		Log.d("MMKVManager", "key3= " + MMKVManager.getInstance().getInt("key3", 0) + "");
		Log.d("MMKVManager", "key4= " + MMKVManager.getInstance().getFloat("key4", 0) + "");
		Log.d("MMKVManager", "key5= " + MMKVManager.getInstance().getDouble("key5", 0) + "");
		Log.d("MMKVManager", "key_id key6= " + MMKVManager.getInstance().getString("key_id", "key_6", "") + "");

		Student student1 = (Student) MMKVManager.getInstance().getObj("key_obj");
		Log.d("MMKVManager", "key_obj= " + student1.toString() );

	}


	private void testLog() {
//        L.setLogEnable(true);
//        L.setLogLevel(LogLevel.TYPE_VERBOSE);
//        L.d("MainActivity","aaa");
//        L.w("no thread info and only 1 method");
//        L.i("no thread info and method info");
//
//
//        L.json("{ \"key\": 3, \"value\": something}");
//        L.json("MainActivity","{ \"key\": 3, \"value\": something}");
//
//        L.d(Arrays.asList("foo", "bar"));
//
//        Map<String, String> map = new HashMap<>();
//        map.put("key", "value");
//        map.put("key1", "value2");
//        L.d(map);
//
//        StringBuffer stringBuffer = new StringBuffer();
//        for(int i= 0;i<10000;i++){
//            stringBuffer.append("A");
//
//        }
//        L.d(stringBuffer);


	}

	private void testFile() {
		L.d(TAG, "SDCardUtil.isSDCardEnable=" + SDCardUtil.isSDCardEnable());
		L.d(TAG, "SDCardUtil.getSDCardPath=" + SDCardUtil.getSDCardPath());
		L.d(TAG, "SDCardUtil.getRootDirectoryPath=" + SDCardUtil.getRootDirectoryPath());
		L.d(TAG, "SDCardUtil.getSDCardAllSize=" + SDCardUtil.getSDCardAllSize());
		L.d(TAG, "SDCardUtil.getFilePath=" + SDCardUtil.getFilePath(this));
		L.d(TAG, "SDCardUtil.getCachePath=" + SDCardUtil.getCachePath(this));


		FileUtil.saveFile("test", SDCardUtil.getSDCardPath() + "testUtil", "test.txt");
		String s = FileUtil.readFile(SDCardUtil.getSDCardPath() + "testUtil", "test.txt");
		String s1 = FileUtil.readFile(SDCardUtil.getSDCardPath() + "testUtil/test.txt");

		L.d(TAG, "s=" + s);
		L.d(TAG, "s1=" + s1);
	}

	private void testRetrofit() {
	}

	private void testSnackBar() {
		SnackBarUtils.setTextColor(Color.parseColor("#F2C122"));
		SnackBarUtils.setBgColor(Color.parseColor("#CCCCCC"));
		SnackBarUtils.showLong(findViewById(R.id.tv), "aaaa");
	}


}
