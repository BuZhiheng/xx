package com.horen.cortp.company.activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.horen.cortp.company.R;
import com.horen.cortp.company.adapter.CreditAdapter;
import com.horen.cortp.company.bean.CreditInfo;
import com.horen.cortp.company.contract.CreditLimitContract;
import com.horen.cortp.company.presenter.CreditLimitPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.MessageDialog;
import com.jaydenxiao.common.commonwidget.WaterProgressBar;

/**
 * Created by BuZhiheng on 2018/2/5.
 *
 * 授信额度
 */
@Route(path = CommonRoutePath.CONPANY_CREDITLIMIT_ACTIVITY)
public class CreditLimitActivity extends BaseActivity<CreditLimitPresenter,CreditLimitContract.Model> implements CreditLimitContract.View {
    private TextView tvTitle;
    private LinearLayout llReview;
    private TextView tvRemainingNum;
    private TextView tvTotalNum;
    private LinearLayout llUnReview;
    private ImageView ivUnreviewMsg;
    private TextView tvUnreviewMsg;
    private TextView tvCredit;
    private Button btnRecommit;
    private RecyclerView recyclerView;
    private CreditAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_credit_limit;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        tvTitle = (TextView) findViewById(R.id.tool_bar_title_tv);
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar), tvTitle, "");
        recyclerView = (RecyclerView) findViewById(R.id.rv_credit_limit);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        llReview = (LinearLayout) findViewById(R.id.ll_credit_reviewed);
        llUnReview = (LinearLayout) findViewById(R.id.ll_credit_unreview);

        tvRemainingNum = (TextView) findViewById(R.id.tv_credit_num);
        tvTotalNum = (TextView) findViewById(R.id.tv_credit_numtotal);

        ivUnreviewMsg = (ImageView) findViewById(R.id.iv_credit_unreview);
        tvUnreviewMsg = (TextView) findViewById(R.id.tv_credit_unreview);
        tvCredit = (TextView) findViewById(R.id.tv_credit);
        btnRecommit = (Button) findViewById(R.id.btn_credit_recommit);
        mPresenter.initCreditData();
    }
    public static void startAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, CreditLimitActivity.class);
        context.startActivity(intent);
    }
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.iv_credit_question){
            new MessageDialog(mContext)
                    .showTitle("授信额度依据")
                    .isSingleButton(true, "确定")
                    .isCancle(false)
                    .showContent("首次授信额度是根据您的企业规模，用箱量等进行综合评估的；当您一段周期内信用良好，相应的额度则可能提升，评估时间不统一\n以下行为会有助于您提升额度\n" +
                            "1、经常性的进行租箱业务\n" +
                            "2、按时进行每月账单的结算\n" +
                            "3、箱子使用人为坏损情况较少\n\n\n"+
                            "注：授信额度决定您的租箱数量，请珍惜您的信用")
                    .setOnClickListene(new MessageDialog.OnClickListener() {
                        @Override
                        public void onLeftClick() {
                        }
                        @Override
                        public void onRightClick() { // 提交成功，跳转到我的页面
                        }
                    })
                    .show();
        } else if (id == R.id.btn_credit_apply){
            mPresenter.applyCredit();
        } else if (id == R.id.btn_credit_release){
            CreateOrderFrameActivity.startAction(mContext, CreateOrderFrameActivity.FRAGMENT_TYPE_RETURN);
        } else if (id == R.id.btn_credit_recommit){
            CompanyAuthenticationActivity.startAction(this,false);
            finish();
        }
    }

    @Override
    protected void reLoadData() {
        super.reLoadData();
        mPresenter.initCreditData();
    }

    @Override
    public void showLoading(String title) {
    }
    @Override
    public void stopLoading() {
    }
    @Override
    public void showErrorTip(String msg) {
        checkError(msg);
    }
    @Override
    public void setData(float per, CreditInfo.Credit credit) {
        tvTitle.setText("授信额度");
        llReview.setVisibility(View.VISIBLE);
        llUnReview.setVisibility(View.GONE);
        tvRemainingNum.setText(credit.remainingNum+"个\n"+"剩余额度");
        tvTotalNum.setText("总额度:"+credit.totalNum+"个");

        WaterProgressBar waterProgressBar = (WaterProgressBar) findViewById(R.id.waterProgressbar);
        waterProgressBar.setProgressWithAnimation(per);
        adapter = new CreditAdapter(R.layout.item_credit_adapter,credit.rtpList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void setApplySuccess() {
        new MessageDialog(mContext)
                .showTitle("调额申请")
                .isSingleButton(true, "确定")
                .isCancle(false)
                .showContent("已收到您的调额请求，平台相关人员会及时联系您进行沟通，请保持手机畅通，谢谢！")
                .setOnClickListene(new MessageDialog.OnClickListener() {
                    @Override
                    public void onLeftClick() {
                    }
                    @Override
                    public void onRightClick() { // 提交成功，跳转到我的页面
                    }
                })
                .show();
    }

    @Override
    public void setUnReviewed(String creditMessage) {
        tvTitle.setText("资质审核");
        llReview.setVisibility(View.GONE);
        llUnReview.setVisibility(View.VISIBLE);
        tvUnreviewMsg.setText(creditMessage);
    }
    @Override
    public void setUnderReviewed(String creditMessage) {
        tvTitle.setText("资质审核");
        llReview.setVisibility(View.GONE);
        llUnReview.setVisibility(View.VISIBLE);
        tvCredit.setText("审核中");
        tvUnreviewMsg.setText(creditMessage);
        ivUnreviewMsg.setImageResource(R.drawable.ic_credit_underreview);
        btnRecommit.setVisibility(View.GONE);
    }
}