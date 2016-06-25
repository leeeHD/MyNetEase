package com.it520.activity.news.adapter;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseNewsAdapter<T> extends BaseAdapter {

	protected ArrayList<T> mDatas = new ArrayList<T>();

	public BaseNewsAdapter() {
	}

	public BaseNewsAdapter(List<T> datas) {
		setData(datas);
	}

	public BaseNewsAdapter(T[] datas) {
		setData(datas);
	}

	public void setData(List<T> datas) {
		this.mDatas.clear();
		if (datas != null) {
			this.mDatas.addAll(datas);
		}
	}

	public void setData(T[] datas) {
		this.mDatas.clear();
		if (datas != null) {
			for (T t : datas) {
				this.mDatas.add(t);
			}
		}
	}

	public void addData(List<T> datas) {
		if (datas != null && datas.size() > 0) {
			this.mDatas.addAll(datas);
		}
	}

	public void addData(T[] datas) {
		if (datas != null && datas.length > 0) {
			for (T data : datas) {
				this.mDatas.add(data);
			}
		}
	}

	public void addData(T data) {
		if (data != null) {
			this.mDatas.add(data);
		}
	}
	
	public void addDataToFirst(T data) {
        if (data != null) {
            this.mDatas.add(0,data);
        }
    }

	public void addDataToFirst(List<T> list){
		 if (list != null) {
	            this.mDatas.addAll(0,list);
	        }
	}
	public void addData(int index, T data) {
		if (index >= 0 && index <= mDatas.size()) {
			if (data != null) {
				this.mDatas.add(index, data);
			}
		}
	}

	public void addData(int index, List<T> datas) {
		if (index >= 0 && index <= mDatas.size()) {
			if (datas != null && datas.size() > 0) {
				this.mDatas.addAll(index, datas);
			}
		}
	}

	public void removeData(T data) {
		if (data != null) {
			this.mDatas.remove(data);
		}
	}

	public void removeData(int index) {
		if (index >= 0 && index < mDatas.size()) {
			this.mDatas.remove(index);
		}
	}

	public void clearData() {
		this.mDatas.clear();
	}

	public ArrayList<T> getDatas() {
		return this.mDatas;
	}

	public void modifyData(T data) {
		if (this.mDatas.contains(data)) {
			int index = this.mDatas.indexOf(data);
			if (index != -1) {
				this.mDatas.remove(index);
				this.mDatas.add(index, data);
			}
		}
	}
	
	@Override
	public int getCount() {
		return this.mDatas.size();
	}

	public T getLastItem(){
		try{
			T t = mDatas.get(0);
			return t;
		}catch(Exception e){
			return null;
		}
		
	}
	@Override
	public T getItem(int position) {
		if (position >= 0 && position < this.mDatas.size()) {
			return this.mDatas.get(position);
		} else {
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
