package com.joy.libbase.base.view.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class EasySingleRvAdapter<T> extends RecyclerView.Adapter<EasyRViewHolder> {

	protected final List<T> mListData;

	public  EasySingleRvAdapter(List<T> listData) {
		mListData = listData == null ? new ArrayList<T>() : new ArrayList<T>(listData);
	}

	@Override
	public int getItemCount() {
		return mListData.size();
	}

	@NonNull
	@Override
	public EasyRViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
		//View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(mLayoutResId, viewGroup,false);
		View itemView = createItemView(viewGroup, viewType);
		return new EasyRViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull EasyRViewHolder easyRViewHolder, int i) {
		onBindData(easyRViewHolder, mListData.get(i), i);
	}

	protected abstract void onBindData(EasyRViewHolder viewHolder, T item, int position);

	protected abstract View createItemView(ViewGroup parent, int viewType);

	public List<T> getListData() {
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
