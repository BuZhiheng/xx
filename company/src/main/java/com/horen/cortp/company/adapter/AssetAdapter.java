package com.horen.cortp.company.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.company.R;
import com.horen.cortp.company.bean.AssetContent;

import java.util.List;

/**
 * Created by Zhao on 2017/12/5/005.
 */

public class AssetAdapter extends BaseQuickAdapter<AssetContent.OrgBean, BaseViewHolder> {

    public AssetAdapter(int layoutResId, List<AssetContent.OrgBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AssetContent.OrgBean item) {
        List<AssetContent.OrgBean.RtpsBean> dataList = item.getRtps();

        helper.setText(R.id.tv_address, item.getOrg_name());

        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);// 设置固定大小
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        AssetItemAdapter itemAdapter = new AssetItemAdapter(R.layout.item_asset_item
                , dataList);
        recyclerView.setAdapter(itemAdapter);
    }

}
