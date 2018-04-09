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
import com.horen.cortp.platform.adapter.FireflysAdapter;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.Fireflys;
import com.horen.cortp.platform.contract.FireflysContract;
import com.horen.cortp.platform.presenter.FireflysPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.basebean.UserInfo;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.List;
/**
 * Created by HOREN on 2017/11/28.
 *
 * 萤火虫新箱构想
 *
 */
@Route(path = CommonRoutePath.PLATFORM_ACTIVITY_FIRESFLY)
public class FireflysActivity extends BaseActivity<FireflysPresenter,FireflysContract.Model> implements FireflysContract.View, View.OnClickListener {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private FireflysAdapter adapter;
    private TextView tvRight;
    private TextView tvMy;
    private TextView tvMore;
    @Override
    public int getLayoutId() {
        return R.layout.activity_fireflys;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv),getString(R.string.platform_activity_newboxfuture));
        tvMy = (TextView) findViewById(R.id.tv_fireflys_my);
        tvMore = (TextView) findViewById(R.id.tv_fireflys_more);
        tvRight = (TextView) findViewById(R.id.right_tv);
        tvRight.setVisibility(View.GONE);
        tvRight.setText(getText(R.string.platform_add_firesfly));
        tvRight.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_common);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FireflysAdapter(this);
        recyclerView.setAdapter(adapter);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_common);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setEnableLoadmore(false);
    }
    /**
     * 每次resume重新加载页面
     * 保证点击量，收藏数的同步显示
     * */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
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
    public void setData(List<Fireflys> datas) {
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
        stopLoading();
    }
    @Override
    public void onClick(View view) {
        if (view == tvRight){
            if (!UserInfo.isLogin()){
                showErrorTip(mContext.getString(R.string.share_nologin));
                return;
            }
            Intent intent = new Intent(this,ExperienceCreateActivity.class);
            intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,false);
            startActivity(intent);
        } else if (view == tvMy){
            mPresenter.getDataMine();
        } else if (view == tvMore){
            mPresenter.getDataMore();
        }
    }
    public void setMine(){
        tvMy.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,2));
        tvMore.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
        tvMy.setBackgroundResource(R.color.color_main_color);
        tvMore.setBackgroundResource(R.color.cortpGray);
    }
    public void setMore(){
        tvMy.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
        tvMore.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,2));
        tvMy.setBackgroundResource(R.color.cortpGray);
        tvMore.setBackgroundResource(R.color.color_main_color);
    }
}