package com.joy.libbase.base.view.listview.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class EasySingleAdapter<T> extends BaseEasyAdapter<T> {

	public EasySingleAdapter(List<T> listData) {
		super(listData);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//EasyViewHolder easyViewHolder = EasyViewHolder.getViewHolder(parent.getContext(), convertView, parent, mLayoutResId);
		T item = getItem(position);
		EasyViewHolder easyViewHolder = EasyViewHolder.getViewHolder(convertView, createItemView(position, convertView, parent, item));
		onBindData(easyViewHolder, item, position);
		View view = easyViewHolder.getConvertView();
		return view;
	}

	protected abstract void onBindData(EasyViewHolder viewHolder, T item, int position);

	protected abstract View createItemView(int position, View convertView, ViewGroup parent, T itemData);
}
