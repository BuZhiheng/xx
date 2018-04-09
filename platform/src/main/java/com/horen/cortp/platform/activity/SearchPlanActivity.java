package com.horen.cortp.platform.activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.AllHotPlanAdapter;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.Plan;
import com.horen.cortp.platform.contract.AllHotPlanContract;
import com.horen.cortp.platform.presenter.AllHotPlanPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.CortpFooter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
/**
 * Created by HOREN on 2017/12/14.
 */
public class SearchPlanActivity extends BaseActivity<AllHotPlanPresenter,AllHotPlanContract.Model> implements AllHotPlanContract.View{
    private EditText editText;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private AllHotPlanAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_serch_plan;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        editText = (EditText) findViewById(R.id.et_search_plan);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 修改回车键功能
                if (keyCode == KeyEvent.KEYCODE_ENTER  && event.getAction() == KeyEvent.ACTION_DOWN ) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SearchPlanActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //接下来在这里做你自己想要做的事，实现自己的业务。
                    mPresenter.getPlanDataRefresh(editText);
                }
                return false;
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.rv_search_plan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllHotPlanAdapter(this);
        recyclerView.setAdapter(adapter);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_search_plan);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new CortpFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.getPlanDataRefresh(editText);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshLayout) {
                mPresenter.getPlanDataLoadmore(editText);
            }
        });
    }
    public void onClick(View view){
        if (view.getId() == R.id.iv_search_plan_clear){
            editText.setText("");
        } else if (view.getId() == R.id.tv_search_plan_cancel){
            finish();
        }
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