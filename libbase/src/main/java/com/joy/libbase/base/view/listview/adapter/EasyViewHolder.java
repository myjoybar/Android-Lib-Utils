package com.joy.libbase.base.view.listview.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EasyViewHolder {

	private final SparseArray<View> mViews;
	private final View mConvertView;


	public EasyViewHolder(Context context, ViewGroup parent, int layoutId) {
		mViews = new SparseArray<>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	public EasyViewHolder(View itemView) {
		mViews = new SparseArray<>();
		mConvertView = itemView;
		mConvertView.setTag(this);
	}

	public static EasyViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId) {
		if (convertView == null) {
			return new EasyViewHolder(context, parent, layoutId);
		}
		return (EasyViewHolder) convertView.getTag();

	}

	public static EasyViewHolder getViewHolder(View convertView, View itemView) {
		if (convertView == null) {
			return new EasyViewHolder(itemView);
		}
		return (EasyViewHolder) convertView.getTag();
	}

	public View getConvertView() {
		return mConvertView;
	}

	public <T extends View> T retrieveView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}


}
