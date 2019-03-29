package com.joy.libbase.base.view.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class EasyRViewHolder<T> extends RecyclerView.ViewHolder {

	private final SparseArray<View> mViews;
	private final View mConvertView;

	private int itemViewType;


	public EasyRViewHolder(@NonNull View itemView) {
		super(itemView);
		mViews = new SparseArray<>();
		this.mConvertView = itemView;
	}

	public EasyRViewHolder(@NonNull View itemView, int itemViewType) {
		super(itemView);
		mViews = new SparseArray<>();
		this.mConvertView = itemView;
		this.itemViewType = itemViewType;
	}

	public View getConvertView() {
		return mConvertView;
	}

	public int getCurrentItemViewType() {
		return itemViewType;
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
