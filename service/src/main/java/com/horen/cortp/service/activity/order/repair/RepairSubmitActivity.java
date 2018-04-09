package com.horen.cortp.service.activity.order.repair;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.CommonTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.cortp.service.R;
import com.horen.cortp.service.adapter.RepairSubmitAdapter;
import com.horen.cortp.service.bean.RepairDetailBean;
import com.horen.cortp.service.common.ApiServiceFactory;
import com.horen.cortp.service.common.RequestUtils;
import com.jaydenxiao.common.commonwidget.ExpandableLayout;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baserx.RxResultHelper;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonwidget.MyScrollview;

/**
 * Created by HOREN on 2017/10/23.
 */

public class RepairSubmitActivity extends BaseActivity implements View.OnClickListener {


    /**  */
    private TextView mLeftTv;
    /**  */
    private TextView mToolBarTitleTv;
    private ImageView mRightIv;
    private TextView mRightTv;
    private Toolbar mToolBar;
    private TextView mTvOrderDeliveryStatus;
    private TextView mTvOrderTime;
    private TextView mTvOrderNumber;
    private TextView mTvOrderCompany;
    private CommonTextView mCtvRepairList;
    private RecyclerView mRvCollect;
    private MyScrollview mScrollView;
    private String workorder_id;
    private boolean isOpen = true; // 默认展开
    private ExpandableLayout expandableLayout;
    private String line_status;

    @Override
    public int getLayoutId() {
        return R.layout.activity_repair_submit;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mLeftTv = (TextView) findViewById(R.id.left_tv);
        mToolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        mRightIv = (ImageView) findViewById(R.id.right_iv);
        mRightTv = (TextView) findViewById(R.id.right_tv);
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mTvOrderDeliveryStatus = (TextView) findViewById(R.id.tv_order_delivery_status);
        mTvOrderTime = (TextView) findViewById(R.id.tv_order_time);
        mTvOrderNumber = (TextView) findViewById(R.id.tv_order_number);
        mTvOrderCompany = (TextView) findViewById(R.id.tv_order_company);
//        mCtvRepairList = (CommonTextView) findViewById(R.id.ctv_repair_list);
//        mCtvRepairList.setOnClickListener(this);
//        mRvCollect = (RecyclerView) findViewById(R.id.rv_collect);
        mScrollView = (MyScrollview) findViewById(R.id.scroll_view);


        expandableLayout = (ExpandableLayout) findViewById(R.id.expandableLayout);
        mCtvRepairList = (CommonTextView) expandableLayout.getHeaderLayout().findViewById(R.id.ctv_repair_list);
        mCtvRepairList.setOnClickListener(this);
        mRvCollect = (RecyclerView) expandableLayout.getContentLayout().findViewById(R.id.rv_collect);
        expandableLayout.show();


        workorder_id = getIntent().getStringExtra("workorder_id");
        line_status = getIntent().getStringExtra("line_status");
        setSimpleToolbar(mToolBar, mToolBarTitleTv, getString(R.string.repair_apply));
        // 白色状态栏
        setWhiteStatusBar();
        mRvCollect.setLayoutManager(new LinearLayoutManager(mContext));
        mRvCollect.setFocusable(false); // 防止scrollview嵌套recycleView不在顶部的bug
        // 维修详情
        queryRepairOrderDetail();
    }

    private void queryRepairOrderDetail() {
        mRxManager.add(ApiServiceFactory.getSingleApi()
                .queryRepairOrderDetailList(RequestUtils.queryRepairOrderDetail(workorder_id,line_status))
                .compose(RxResultHelper.<RepairDetailBean>handleResult())
                .subscribe(new RxSubscriber<RepairDetailBean>(mContext, true) {
                    @Override
                    protected void _onNext(RepairDetailBean repairDetailBean) {
                        mTvOrderNumber.setText(getString(R.string.repair_order_id) + repairDetailBean.getWorkorder_id());
                        mTvOrderTime.setText(repairDetailBean.getCreate_date());
                        mTvOrderCompany.setText(getString(R.string.repair_company) + repairDetailBean.getWorkorder_companyname());
                        mTvOrderDeliveryStatus.setText(repairDetailBean.getLine_status_value());
                        // 保修列表
                        if (repairDetailBean.getPicsInfo().size() > 0) {
                            RepairSubmitAdapter collectBoxsAdapter = new RepairSubmitAdapter(R.layout.item_collect_box_complete, repairDetailBean.getPicsInfo());
                            collectBoxsAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                            mRvCollect.setAdapter(collectBoxsAdapter);
                        }
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                })
        );
    }

    public static void startAction(Context context, String workorder_id,String line_status) {
        Intent intent = new Intent();
        intent.putExtra("workorder_id", workorder_id);
        intent.putExtra("line_status", line_status);
        intent.setClass(context, RepairSubmitActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ctv_repair_list) { // 坏损列表
            if (isOpen) { // 关闭列表，改变图标
                mCtvRepairList.setRightDrawableRight(getResources().getDrawable(R.drawable.service_down_arrow));
                expandableLayout.hide();
            } else { // 展开列表
                mCtvRepairList.setRightDrawableRight(getResources().getDrawable(R.drawable.service_up_arrow));
                expandableLayout.show();
            }
            isOpen = !isOpen;
        }
    }

}
