package com.joy.libbase.base.view.listview.adapter;

import android.view.View;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseEasyAdapter<T> extends BaseAdapter {

	protected final List<T> mListData;


	public BaseEasyAdapter(List<T> listData) {
		mListData = listData == null ? new ArrayList<T>() : new ArrayList<T>(listData);
	}


	@Override
	public int getCount() {
		return mListData.size();
	}

	@Override
	public T getItem(int position) {
		if (position >= mListData.size() || position < 0) {
			return null;
		}
		return mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	public void updateSingleItem(int position, AbsListView absListView) {
		/**第一个可见的位置**/
		int firstVisiblePosition = absListView.getFirstVisiblePosition();
		/**最后一个可见的位置**/
		int lastVisiblePosition = absListView.getLastVisiblePosition();
		/**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
		if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
			/**获取指定位置view对象**/
			View view = absListView.getChildAt(position - firstVisiblePosition);
			getView(position, view, absListView);
		}

	}

	public void updateSingleItem(int position, AbsListView absListView, UpdateSingleItemCallBack updateSingleItemCallBack) {
		/**第一个可见的位置**/
		int firstVisiblePosition = absListView.getFirstVisiblePosition();
		/**最后一个可见的位置**/
		int lastVisiblePosition = absListView.getLastVisiblePosition();
		/**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
		if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
			/**获取指定位置view对象**/
			View view = absListView.getChildAt(position - firstVisiblePosition);
			updateSingleItemCallBack.onUpdateSingleItem(view);
		}

	}

	protected List<T> getListData() {
		return mListData;
	}

	public void add(T elem) {
		mListData.add(elem);
		notifyDataSetChanged();
	}

	public void addAll(List<T> elem) {
		mListData.addAll(elem);
		notifyDataSetChanged();
	}

	public void set(T oldElem, T newElem) {
		set(mListData.indexOf(oldElem), newElem);
	}

	public void set(int index, T elem) {
		mListData.set(index, elem);
		notifyDataSetChanged();
	}

	public void remove(T elem) {
		mListData.remove(elem);
		notifyDataSetChanged();
	}

	public void remove(int index) {
		mListData.remove(index);
		notifyDataSetChanged();
	}

	public void replaceAll(List<T> elem) {
		mListData.clear();
		mListData.addAll(elem);
		notifyDataSetChanged();
	}


	public void clear() {
		mListData.clear();
		notifyDataSetChanged();
	}

	public boolean contains(T elem) {
		return mListData.contains(elem);
	}

	public interface UpdateSingleItemCallBack {
		void onUpdateSingleItem(View convertView);
	}

}
