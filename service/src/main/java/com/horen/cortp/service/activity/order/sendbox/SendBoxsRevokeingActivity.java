package com.horen.cortp.service.activity.order.sendbox;

import android.content.Context;
import android.content.Intent;

import com.horen.cortp.service.R;
import com.jaydenxiao.common.base.BaseActivity;

/**
 * Created by HOREN on 2017/10/23.
 */

public class SendBoxsRevokeingActivity extends BaseActivity {




    @Override
    public int getLayoutId() {
        return R.layout.activity_send_boxs_revoke;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
//        String order_type = getIntent().getStringExtra("order_type");
//        String plan_id = getIntent().getStringExtra("plan_id");
//        String line_status = getIntent().getStringExtra("line_status");
//        tvRevokeExamine.setVisibility(View.GONE);
//
//        // 标题
//        if (order_type.equals(Constract.SEND_BOX)) {
//            setSimpleToolbar(toolBar, toolBarTitleTv, getString(R.string.send_boxs_order));
//        } else if (order_type.equals(Constract.SUPPLIES)) {
//            setSimpleToolbar(toolBar, toolBarTitleTv, getString(R.string.supplies_order));
//        }
//        List<String> list = new ArrayList<>();
//        rvGoods.setLayoutManager(new LinearLayoutManager(mContext));
//        rvGoods.setFocusable(false); // 防止scrollview嵌套recycleView不在顶部的bug
//        for (int i = 0; i < 3; i++) {
//            list.add("");
//        }
//        rvGoods.setAdapter(new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_delivery_box, list) {
//            @Override
//            protected void convert(BaseViewHolder helper, String item) {
//            }
//        });
    }

    public static void startAction(Context context, String order_type, String plan_id, String line_status) {
        Intent intent = new Intent();
        intent.setClass(context, SendBoxsRevokeingActivity.class);
        intent.putExtra("order_type", order_type);
        intent.putExtra("plan_id", plan_id);
        intent.putExtra("line_status", line_status);
        context.startActivity(intent);
    }

}
