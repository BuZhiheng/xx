package com.horen.cortp.platform.activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.UseBoxFeelAdapter;
import com.jaydenxiao.common.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
/**
 * Created by HOREN on 2017/11/28.
 *
 * 用箱体验activity
 *
 */
public class UseBoxFeelActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    @Override
    public int getLayoutId() {
        return R.layout.activity_common_refresh_recycle;
    }
    @Override
    public void initPresenter() {
    }
    @Override
    public void initView() {
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv),getString(R.string.platform_activity_useboxfeel));
        recyclerView = (RecyclerView) findViewById(R.id.rv_common);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UseBoxFeelAdapter(this));
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_common);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
    }
}