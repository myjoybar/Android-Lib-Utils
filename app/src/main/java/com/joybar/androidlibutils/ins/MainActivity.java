package com.joybar.androidlibutils.ins;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joybar.androidlibutils.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_ins);


	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.btn_login:
			default:
				break;
		}
	}
}
