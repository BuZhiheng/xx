package com.horen.cortp.company.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.company.R;
import com.horen.cortp.company.bean.RelationSystem;

import java.util.List;

/**
 * Created by Zhao on 2017/12/5/005.
 */

public class RelationSystemAdapter extends BaseQuickAdapter<RelationSystem.RelationListBean, BaseViewHolder> {

    public RelationSystemAdapter(int layoutResId, List<RelationSystem.RelationListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RelationSystem.RelationListBean item) {
        helper.setText(R.id.tv_relation_system_name, item.getOrg_name());
        helper.addOnClickListener(R.id.tv_relation_system_click);
    }

}
