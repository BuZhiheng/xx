package com.horen.cortp.company.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
 * Created by Zhao on 2017/11/7/007.
 */

public class OrderSpinnerAdapter implements SpinnerAdapter {

    private Context mContext;

    private String[] mList;

    public OrderSpinnerAdapter(Context mContext,String[] mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mList.length - 1;
    }

    @Override
    public Object getItem(int position) {
        return mList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setText(mList[position]);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        return textView;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setText(mList[position]);
        textView.setPadding(60, 30, 60, 30);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        return textView;
    }
}