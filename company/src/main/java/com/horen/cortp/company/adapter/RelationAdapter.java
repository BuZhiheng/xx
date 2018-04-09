package com.horen.cortp.company.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.company.R;
import com.horen.cortp.company.bean.Relation;

import java.util.List;

/**
 * Created by Zhao on 2017/12/5/005.
 */

public class RelationAdapter extends BaseQuickAdapter<Relation.OrgPartnersBean, BaseViewHolder> {

    public RelationAdapter(int layoutResId, List<Relation.OrgPartnersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Relation.OrgPartnersBean item) {
        helper.setText(R.id.tv_item_partner_company_name, item.getOrg_name());
        helper.setText(R.id.tv_item_partner_company_address, item.getOrg_address());
    }

}
