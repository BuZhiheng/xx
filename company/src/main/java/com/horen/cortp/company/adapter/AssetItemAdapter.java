package com.horen.cortp.company.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.company.R;
import com.horen.cortp.company.bean.AssetContent;

import java.util.List;

/**
 * Created by David-Notebook on 2017/7/10.
 */

public class AssetItemAdapter extends BaseQuickAdapter<AssetContent.OrgBean.RtpsBean, BaseViewHolder> {

    public AssetItemAdapter(int layoutResId, List<AssetContent.OrgBean.RtpsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AssetContent.OrgBean.RtpsBean item) {
        helper.setText(R.id.tv_box_type, item.getCtnr_type() + "");
        helper.setText(R.id.tv_all_box_number, item.getRtp_total() + "");
        helper.setText(R.id.tv_empty_box, item.getRtp_empty() + "");
        helper.setText(R.id.tv_full_box, item.getRtp_full() + "");
    }

}
