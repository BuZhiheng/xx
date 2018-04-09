package com.horen.cortp.service.adapter;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.horen.cortp.service.bean.HundredNetOrderBean;
import com.horen.cortp.service.common.Constract;

import java.util.List;

/**
 * Created by HOREN on 2017/10/19.
 */

public class HundreOrderAdapter extends BaseQuickAdapter<HundredNetOrderBean.DataBean.RowsBean, BaseViewHolder> {
    private String order_type;

    public HundreOrderAdapter(int layoutResId, List<HundredNetOrderBean.DataBean.RowsBean> data, String order_type) {
        super(layoutResId, data);
        this.order_type = order_type;
    }

    @Override
    protected void convert(BaseViewHolder helper, HundredNetOrderBean.DataBean.RowsBean item) {
        helper.setText(R.id.tv_company_name, item.getOrder_companyname());
        helper.setText(R.id.tv_planid, mContext.getString(R.string.order_number) + item.getPlan_id());
        helper.setText(R.id.tv_box_number, mContext.getString(R.string.scanner_box_num) + item.getService_qty()); // 箱子数量
        SuperButton superButton = helper.getView(R.id.stb_state);
        superButton.setText(item.getLine_status_value());
        helper.setText(R.id.tv_start_address, item.getFrom_address()); // 开始地址
        helper.setText(R.id.tv_end_address, item.getTo_address()); // 送达地址
        helper.setText(R.id.tv_time, item.getCreate_date()); // 时间

//        // 收箱，送箱
//        switch (order_type) {
//            case Constract.COLLECT_BOX: // 收箱
//                helper.setText(R.id.tv_start_address, item.getFrom_address()); // 开始地址
//                helper.setText(R.id.tv_end_address, item.getTo_address()); // 送达地址
//                break;
//            case Constract.SEND_BOX: // 送箱
//                helper.setText(R.id.tv_start_address, item.getFrom_address()); // 开始地址
//                helper.setText(R.id.tv_end_address, item.getTo_address()); // 送达地址
//                break;
//        }
        switch (item.getLine_status()) { // 状态信息
            case Constract.WAITING_DELIVERY: // 待配送
                superButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.green_start))
                        .setShapeGradientEndColor(mContext.getResources().getColor(R.color.green_end))
                        .setUseShape();
                break;
            case Constract.DELIVERYING: // 配送中  收箱中
                superButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.green_start))
                        .setShapeGradientEndColor(mContext.getResources().getColor(R.color.green_end))
                        .setUseShape();
                break;
            case Constract.DELIVERY_CANCLE: // 已取消
                superButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.gray_start))
                        .setShapeGradientEndColor(mContext.getResources().getColor(R.color.gray_end))
                        .setUseShape();
                break;
            case Constract.DELIVERY_COMPLETE: // 已完成
                superButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.blue_start))
                        .setShapeGradientEndColor(mContext.getResources().getColor(R.color.blue_end))
                        .setUseShape();
                break;

            case Constract.COLLECT_BOXS_CONFIRMING: // 确认中
                superButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.green_start))
                        .setShapeGradientEndColor(mContext.getResources().getColor(R.color.green_end))
                        .setUseShape();
                break;
        }
    }
}
