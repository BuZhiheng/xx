package com.horen.cortp.company.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.company.R;
import com.horen.cortp.company.bean.EmptyBoxDetailBean;

import java.util.List;

/**
 * Created by HOREN on 2017/10/17.
 */

public class EmptyBoxAddressAdapter extends BaseQuickAdapter<EmptyBoxDetailBean.DataBean, BaseViewHolder> {

    public EmptyBoxAddressAdapter(int layoutResId, List<EmptyBoxDetailBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EmptyBoxDetailBean.DataBean item) {
        helper.setText(R.id.tv_address, item.getOrgName()); // 网点名称
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new BaseQuickAdapter<EmptyBoxDetailBean.DataBean.MapListBean, BaseViewHolder>(R.layout.item_asset_item, item.getMapList()) {

            @Override
            protected void convert(BaseViewHolder helper, EmptyBoxDetailBean.DataBean.MapListBean item) {
                helper.setText(R.id.tv_box_type, item.getCtnrType());
                helper.setText(R.id.tv_all_box_number, String.valueOf(item.getBoxSize()));
                helper.setText(R.id.tv_empty_box, String.valueOf(item.getEmptyBox()));
                helper.setText(R.id.tv_full_box, String.valueOf(item.getFullBox()));
            }
        });
    }
}
