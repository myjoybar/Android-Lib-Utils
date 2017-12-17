package com.joybar.androidlibutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joybar.library.common.log.LogLevel;
import com.joybar.library.common.log.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testLog();
    }


    private void testLog(){
        Logger.setLogEnable(true);
        Logger.setLogLevel(LogLevel.TYPE_VERBOSE);
        Logger.d("MainActivity","aaa");
        Logger.w("no thread info and only 1 method");
        Logger.i("no thread info and method info");


        Logger.json("{ \"key\": 3, \"value\": something}");
        Logger.json("MainActivity","{ \"key\": 3, \"value\": something}");

        Logger.d(Arrays.asList("foo", "bar"));

        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key1", "value2");
        Logger.d(map);

        StringBuffer stringBuffer = new StringBuffer();
        for(int i= 0;i<10000;i++){
            stringBuffer.append("A");

        }
        Logger.d(stringBuffer);
    }

}
