package com.joy.libbase.base.view.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class EasyMultiRvAdapter<T> extends RecyclerView.Adapter<EasyRViewHolder> {

	protected final List<T> mListData;


	public EasyMultiRvAdapter(@Nullable List<T> listData) {
		mListData = listData == null ? new ArrayList<T>() : new ArrayList<T>(listData);
	}

	@Override
	public int getItemCount() {
		return mListData.size();
	}

	@Override
	public int getItemViewType(int position) {
		return getMultiItemViewType(position, mListData.get(position));
	}


	@NonNull
	@Override
	public EasyRViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
		View itemView = createItemView(viewGroup, viewType);
		return new EasyRViewHolder<T>(itemView, viewType);
	}

	@Override
	public void onBindViewHolder(@NonNull EasyRViewHolder viewHolder, int position) {
		onBindData(viewHolder, mListData.get(position), position, viewHolder.getCurrentItemViewType());
	}

	protected abstract void onBindData(EasyRViewHolder viewHolder, Object item, int position, int itemViewType);

	protected abstract View createItemView(ViewGroup parent, int viewType);

	protected abstract int getMultiViewTypeCount();

	protected abstract int getMultiItemViewType(int position, T item);


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
