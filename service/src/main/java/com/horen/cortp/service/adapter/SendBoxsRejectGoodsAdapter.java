package com.horen.cortp.service.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;

import java.util.List;

/**
 * Created by HOREN on 2017/10/25.
 */

public class SendBoxsRejectGoodsAdapter extends BaseQuickAdapter<SendBoxOrderDetailBean.ProductsBean, BaseViewHolder> {

    public SendBoxsRejectGoodsAdapter(int layoutResId, List<SendBoxOrderDetailBean.ProductsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SendBoxOrderDetailBean.ProductsBean item) {
        helper.setText(R.id.tv_box_type, item.getProduct_name());
        helper.setText(R.id.tv_box_color, item.getProduct_color());
        helper.setText(R.id.tv_box_number,  item.getReject_qty() + mContext.getString(R.string.ge) );
    }
}
