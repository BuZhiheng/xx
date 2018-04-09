package com.horen.cortp.company.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.company.R;
import com.horen.cortp.company.bean.ExceptionPresentationBean;

import java.util.List;

/**
 * Created by HOREN on 2017/10/16.
 */

public class ExceptionPresentationAdapter extends BaseQuickAdapter<ExceptionPresentationBean.DataBean, BaseViewHolder> {

    public ExceptionPresentationAdapter(int layoutResId, List<ExceptionPresentationBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExceptionPresentationBean.DataBean item) {
        helper.setText(R.id.tv_time, mContext.getString(R.string.report_time) + " " + item.getTime());
        helper.setText(R.id.tv_exception_desc, mContext.getString(R.string.anomaly_description) + " " + item.getMessage());
        helper.setText(R.id.tv_exception_process, mContext.getString(R.string.processing_process) + " " + item.getProcess());
        helper.setText(R.id.tv_resulet, mContext.getString(R.string.treatment_result) + " " + item.getResult());
    }
}
