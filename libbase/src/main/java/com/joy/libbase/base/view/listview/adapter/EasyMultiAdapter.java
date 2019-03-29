package com.joy.libbase.base.view.listview.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class EasyMultiAdapter<T> extends BaseEasyAdapter<T> {

	public EasyMultiAdapter(@Nullable List<T> listData) {
		super(listData);
	}

	@Override
	public int getItemViewType(int position) {
		return getMultiItemViewType(position, mListData.get(position));
	}

	@Override
	public int getViewTypeCount() {
		return getMultiViewTypeCount();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int itemViewType = getItemViewType(position);
		//final EasyViewHolder easyViewHolder = EasyViewHolder.getViewHolder(parent.getContext(), convertView, parent, layoutResId);
		T item = getItem(position);
		EasyViewHolder easyViewHolder = EasyViewHolder.getViewHolder(convertView, createItemView(itemViewType, position, convertView, parent, item));
		onBindData(easyViewHolder, item, position, itemViewType);
		View view = easyViewHolder.getConvertView();
		return view;
	}

	protected abstract void onBindData(EasyViewHolder viewHolder, T item, int position, int itemViewType);

	protected abstract int getMultiViewTypeCount();

	protected abstract int getMultiItemViewType(int position, T item);

	protected abstract View createItemView(int itemViewType, int position, View convertView, ViewGroup parent, T itemData);

}
