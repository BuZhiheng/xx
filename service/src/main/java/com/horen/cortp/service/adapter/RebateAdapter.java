package com.horen.cortp.service.adapter;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.horen.cortp.service.bean.RebateBean;
import com.horen.cortp.service.common.Constract;
import com.jaydenxiao.common.commonutils.DisplayUtil;

import java.util.List;

/**
 * Created by HOREN on 2017/12/13.
 */

public class RebateAdapter extends BaseQuickAdapter<RebateBean.PayFeeToServerDataBean, BaseViewHolder> {
    public RebateAdapter(int layoutResId, List<RebateBean.PayFeeToServerDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RebateBean.PayFeeToServerDataBean item) {

        if (helper.getLayoutPosition() == 0) {
            helper.getConvertView().setPadding(0, DisplayUtil.dip2px(10), 0, 0);
        }

        SuperButton stateButton = helper.getView(R.id.stb_state);
        helper.setText(R.id.tv_order_number, mContext.getString(R.string.order_service_count) + item.getServiceOrderCount()); // 服务订单次数
//        helper.setText(R.id.tv_order_all_number, mContext.getString(R.string.service_order_count) + item.getJob_qty()); // 收箱总数量
        helper.setText(R.id.tv_phone_number, mContext.getString(R.string.service_people_count) + item.getCompany_count()); // 服务客户数量
        helper.setText(R.id.tv_all_collect_money, mContext.getString(R.string.service_money) +  " " + item.getJob_arap()); // 累计收入

        if (item.getOrder_type().equals(Constract.COLLECT_BOX)) { // 收箱返佣
            helper.setText(R.id.tv_order_all_number, mContext.getString(R.string.collect_boxs_number) + item.getJob_qty()); // 收箱总数量
            stateButton.setText(R.string.rebate_collect);
            stateButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.blue_start))
                    .setShapeGradientEndColor(mContext.getResources().getColor(R.color.blue_end))
                    .setUseShape();
        } else if (item.getOrder_type().equals(Constract.SEND_BOX)) { // 送箱返佣
            stateButton.setText(R.string.rebate_sendbox);
            helper.setText(R.id.tv_order_all_number, mContext.getString(R.string.send_boxs_number) + item.getJob_qty()); // 送箱总数量
            stateButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.blue_start))
                    .setShapeGradientEndColor(mContext.getResources().getColor(R.color.blue_end))
                    .setUseShape();
        } else if (item.getOrder_type().equals(Constract.SUPPLIES)) { // 耗材返佣
            helper.setText(R.id.tv_order_all_number, mContext.getString(R.string.supplits_boxs_number) + item.getJob_qty()); // 送箱总数量
            stateButton.setText(R.string.rebate_supplies);
            stateButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.green_start))
                    .setShapeGradientEndColor(mContext.getResources().getColor(R.color.green_end))
                    .setUseShape();
        } else if (item.getOrder_type().equals(Constract.REPAIR)) { // 维修返佣
            helper.setText(R.id.tv_order_all_number, mContext.getString(R.string.repair_boxs_number) + item.getJob_qty()); // 修箱总数量
            stateButton.setText(R.string.rebate_repair);
            stateButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.green_start))
                    .setShapeGradientEndColor(mContext.getResources().getColor(R.color.green_end))
                    .setUseShape();
        } else if (item.getOrder_type().equals(Constract.CLEAN_BOX)) { // 洗箱返佣
            helper.setText(R.id.tv_order_all_number, mContext.getString(R.string.service_clean_boxs_number) + item.getJob_qty()); // 修箱总数量
            stateButton.setText(R.string.rebate_clean_boxs);
            stateButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.blue_start))
                    .setShapeGradientEndColor(mContext.getResources().getColor(R.color.blue_end))
                    .setUseShape();
        } else if (item.getOrder_type().equals(Constract.EXCEPTION)) { // 异常处理返佣
            helper.setText(R.id.tv_order_all_number, mContext.getString(R.string.exception_boxs_number) + item.getJob_qty()); // 修箱总数量
            stateButton.setText(R.string.rebete_exception);
            stateButton.setShapeGradientStartColor(mContext.getResources().getColor(R.color.red_start))
                    .setShapeGradientEndColor(mContext.getResources().getColor(R.color.red_end))
                    .setUseShape();
        }
    }
}
