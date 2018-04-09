package com.horen.cortp.platform.activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.AllPlanDataAdapter;
import com.horen.cortp.platform.adapter.AllPlanListAdapter;
import com.horen.cortp.platform.bean.Plan;
import com.horen.cortp.platform.bean.PlanType;
import com.horen.cortp.platform.bean.PlanTypeLookup;
import com.horen.cortp.platform.contract.AllPlanContract;
import com.horen.cortp.platform.presenter.AllPlanPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.util.List;
/**
 * Created by HOREN on 2017/11/24.
 *
 * 全部万箱方案activity
 *
 * 本页面下拉刷新框架采用SmartRefreshLayout
 * 本页面下拉刷新框架采用SmartRefreshLayout并不能完美适配自定义recycleview(如xrecycleview recycleviewheader ..)
 * 解决：自己写adapter 加 header
 * Fix 2018-01-25 上述布局方法已经舍弃
 * 采用更加灵活简洁的 CoordinatorLayout + AppBarLayout（+CollapsingToolbarLayout）
 * 参考 ： https://www.jianshu.com/p/f09723b7e887
 */
public class AllPlanActivity extends BaseActivity<AllPlanPresenter,AllPlanContract.Model> implements AllPlanContract.View, View.OnClickListener {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView rvList;
    private RecyclerView rvData;
    private AllPlanDataAdapter planDataAdapter;
    private AllPlanListAdapter planListAdapter;
    private ImageView imageView;
    private TextView tvType1;
    private TextView tvType2;
    private PlanType type;
    private PlanTypeLookup typeTag;
    private PlanTypeLookup typeSize;
    private ImageView ivSearch;
    @Override
    public int getLayoutId() {
        return R.layout.activity_all_plan;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv),getString(R.string.platform_activity_allplan));
        ivSearch = (ImageView) findViewById(R.id.right_iv);
        ivSearch.setImageResource(R.drawable.ic_platform_search);
        ivSearch.setVisibility(View.VISIBLE);
        ivSearch.setOnClickListener(this);
        tvType1 = (TextView) findViewById(R.id.tv_allplan_type1);
        tvType2 = (TextView) findViewById(R.id.tv_allplan_type2);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_all_plan);
        rvList = (RecyclerView) findViewById(R.id.rv_allplan_list);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        planListAdapter = new AllPlanListAdapter(this, new AllPlanListAdapter.AllPlanListClickListener() {
            @Override
            public void click(PlanType select) {
                /**
                 * 点击一级分类
                 * 设置全局type
                 *
                 * */
                type = select;
                mPresenter.getPlanData(type,typeTag,typeSize);
                setPlanBanner(type);
                showLoading("");
            }
        });
        rvList.setAdapter(planListAdapter);
        rvData = (RecyclerView) findViewById(R.id.rv_allplan_data);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        planDataAdapter = new AllPlanDataAdapter(this, new AllPlanDataAdapter.OnTypeClickListener() {
            @Override
            public void type1Click(PlanTypeLookup select) {
                tvType1.setText(select.lookup_name);
                planDataAdapter.notifyDataSetChanged();
                typeTag = select;
                mPresenter.getPlanData(type,typeTag,typeSize);
                showLoading("");
            }
            @Override
            public void type2Click(PlanTypeLookup select) {
                tvType2.setText(select.lookup_name);
                planDataAdapter.notifyDataSetChanged();
                typeSize = select;
                mPresenter.getPlanData(type,typeTag,typeSize);
                showLoading("");
            }
        });
        rvData.setAdapter(planDataAdapter);
        imageView = (ImageView) findViewById(R.id.iv_all_plan_data_header);
        mPresenter.getPlanList();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getPlanData(type,typeTag,typeSize);
    }
    public void onClick(View view){
        int i = view.getId();
        if (i == R.id.ll_allplan_type1) {
            planDataAdapter.typeTagClick(view);
        } else if (i == R.id.ll_allplan_type2){
            planDataAdapter.typeSizeClick(view);
        } else if (i == R.id.right_iv){
            Intent intent = new Intent(this,SearchPlanActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void showLoading(String title) {
        startProgressDialog();
    }
    @Override
    public void stopLoading() {
        stopProgressDialog();
    }
    @Override
    public void showErrorTip(String msg) {
        showErrorToast(msg);
        refreshLayout.finishRefresh(0);
        refreshLayout.finishLoadmore(0);
    }
    @Override
    public void setListData(List<PlanType> list) {
        planListAdapter.setData(list);
        planListAdapter.notifyDataSetChanged();
    }
    @Override
    public void setPlanData(List<Plan> list) {
        planDataAdapter.setData(list);
        planDataAdapter.notifyDataSetChanged();
        stopLoading();
    }
    @Override
    public void setPlanBanner(final PlanType type) {
        Glide.with(this).load(type.banner_image).into(imageView);
            if (!TextUtils.isEmpty(type.banner_url)){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AllPlanActivity.this, PlatformWebViewActivity.class);
                        intent.putExtra(PlatformWebViewActivity.WEBVIEW_URL,type.banner_url);
                        startActivity(intent);
                    }
                });
            }
    }
    @Override
    public void setTypeTag(List<PlanTypeLookup> listTag) {
        planDataAdapter.setDialogTypeTag(listTag);
    }
    @Override
    public void setTypeSize(List<PlanTypeLookup> listSize) {
        planDataAdapter.setDialogTypeSize(listSize);
    }
}