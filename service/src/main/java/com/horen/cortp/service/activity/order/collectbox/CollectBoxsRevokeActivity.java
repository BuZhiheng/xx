package com.horen.cortp.service.activity.order.collectbox;

import android.content.Context;
import android.content.Intent;

import com.horen.cortp.service.R;
import com.jaydenxiao.common.base.BaseActivity;

/**
 * Created by HOREN on 2017/10/23.
 */

public class CollectBoxsRevokeActivity extends BaseActivity {


//    @BindView(R.id.left_tv)
//    TextView leftTv;
//    @BindView(R.id.tool_bar_title_tv)
//    TextView toolBarTitleTv;
//    @BindView(R.id.right_iv)
//    ImageView rightIv;
//    @BindView(R.id.right_tv)
//    TextView rightTv;
//    @BindView(R.id.tool_bar)
//    Toolbar toolBar;
//    @BindView(R.id.tv_order_number)
//    TextView tvOrderNumber;
//    @BindView(R.id.tv_order_time)
//    TextView tvOrderTime;
//    @BindView(R.id.tv_order_company)
//    TextView tvOrderCompany;
//    @BindView(R.id.tv_order_delivery_status)
//    TextView tvOrderDeliveryStatus;
//    @BindView(R.id.tv_to_companyname)
//    TextView tvToCompanyname;
//    @BindView(R.id.tv_to_address)
//    TextView tvToAddress;
//    @BindView(R.id.tv_to_contact)
//    TextView tvToContact;
//    @BindView(R.id.tv_to_tel)
//    TextView tvToTel;
//    @BindView(R.id.rv_goods)
//    RecyclerView rvGoods;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collect_boxs_revoke;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        String order_type = getIntent().getStringExtra("order_type");
        String plan_id = getIntent().getStringExtra("plan_id");
        String line_status = getIntent().getStringExtra("line_status");
        // 标题
//        setSimpleToolbar(toolBar, toolBarTitleTv, getString(R.string.collect_box_order));
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
        intent.setClass(context, CollectBoxsRevokeActivity.class);
        intent.putExtra("order_type", order_type);
        intent.putExtra("plan_id", plan_id);
        intent.putExtra("line_status", line_status);
        context.startActivity(intent);
    }

}
