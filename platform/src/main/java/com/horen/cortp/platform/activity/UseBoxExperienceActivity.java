package com.horen.cortp.platform.activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.UseBoxExperienceAdapter;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.BoxExperience;
import com.horen.cortp.platform.contract.UseExperienceContract;
import com.horen.cortp.platform.presenter.UseExperiencePresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.basebean.UserInfo;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import java.util.List;
/**
 * Created by HOREN on 2017/11/28.
 *
 * 用箱体验activity
 *
 */
@Route(path = CommonRoutePath.PLATFORM_ACTIVITY_EXPERIENCE)
public class UseBoxExperienceActivity extends BaseActivity<UseExperiencePresenter,UseExperienceContract.Model> implements UseExperienceContract.View, View.OnClickListener {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private UseBoxExperienceAdapter adapter;
    private TextView tvRight;
    private TextView tvMy;
    private TextView tvMore;
    @Override
    public int getLayoutId() {
        return R.layout.activity_experience;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv),getString(R.string.platform_activity_useboxfeel));
        tvMy = (TextView) findViewById(R.id.tv_experience_my);
        tvMore = (TextView) findViewById(R.id.tv_experience_more);
        tvRight = (TextView) findViewById(R.id.right_tv);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(getText(R.string.platform_add_firesfly));
        tvRight.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_common);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UseBoxExperienceAdapter(this);
        recyclerView.setAdapter(adapter);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_common);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setEnableLoadmore(false);
    }
    @Override
    public void showLoading(String title) {
        startProgressDialog();
    }
    @Override
    public void stopLoading() {
        refreshLayout.finishRefresh(CommonCode.COMMON_REFRESH_DURING);
        refreshLayout.finishLoadmore(CommonCode.COMMON_REFRESH_DURING);
        stopProgressDialog();
    }
    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showShort(msg);
        stopLoading();
    }
    @Override
    public void setData(List<BoxExperience> datas) {
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
        stopLoading();
    }
    @Override
    public void setMine() {
        tvMy.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,2));
        tvMore.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
        tvMy.setBackgroundResource(R.color.color_main_color);
        tvMore.setBackgroundResource(R.color.cortpGray);
    }
    @Override
    public void setMore() {
        tvMy.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
        tvMore.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,2));
        tvMy.setBackgroundResource(R.color.cortpGray);
        tvMore.setBackgroundResource(R.color.color_main_color);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onClick(View view) {
        if (view == tvRight){
            if (!UserInfo.isLogin()){
                showErrorTip(mContext.getString(R.string.share_nologin));
                return;
            }
            Intent intent = new Intent(this,ExperienceCreateActivity.class);
            intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,true);
            startActivity(intent);
        } else if (view == tvMy){
            mPresenter.getDataMine();
        } else if (view == tvMore){
            mPresenter.getDataMore();
        }
    }
}