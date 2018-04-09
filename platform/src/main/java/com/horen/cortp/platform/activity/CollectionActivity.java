package com.horen.cortp.platform.activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.AllHotPlanAdapter;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.Plan;
import com.horen.cortp.platform.contract.AllHotPlanContract;
import com.horen.cortp.platform.presenter.AllHotPlanPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.commonwidget.CortpFooter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.List;
/**
 * Created by HOREN on 2017/11/28.
 *
 * 我的收藏
 *
 */
@Route(path = CommonRoutePath.PLATFORM_ACTIVITY_COLLECTION)
public class CollectionActivity extends BaseActivity<AllHotPlanPresenter,AllHotPlanContract.Model> implements AllHotPlanContract.View{
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private AllHotPlanAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_common_refresh_recycle;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv),getString(R.string.platform_activity_collection));
        recyclerView = (RecyclerView) findViewById(R.id.rv_common);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllHotPlanAdapter(this);
        recyclerView.setAdapter(adapter);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_common);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new CortpFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.getCollectionPlanDataRefresh();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                mPresenter.getCollectionPlanDataLoadmore();
            }
        });
    }
    /**
     * 每次resume重新加载页面
     * 保证点击量，收藏数的同步显示
     * */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getCollectionPlanDataRefresh();
    }
    @Override
    public void showLoading(String title) {
    }
    @Override
    public void stopLoading() {
        refreshLayout.finishRefresh(CommonCode.COMMON_REFRESH_DURING);
        refreshLayout.finishLoadmore(CommonCode.COMMON_REFRESH_DURING);
    }
    @Override
    public void showErrorTip(String msg) {
        showErrorToast(msg);
        stopLoading();
    }
    @Override
    public void setPlanData(List<Plan> list) {
        adapter.setData(list);
        adapter.notifyDataSetChanged();
        stopLoading();
    }
    @Override
    public void addPlanData(List<Plan> list) {
        adapter.addData(list);
        adapter.notifyDataSetChanged();
        stopLoading();
    }
}