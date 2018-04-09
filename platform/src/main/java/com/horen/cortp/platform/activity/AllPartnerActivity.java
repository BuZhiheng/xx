package com.horen.cortp.platform.activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.AllPartnerAdapter;
import com.horen.cortp.platform.bean.Partner;
import com.horen.cortp.platform.contract.AllPartnerContract;
import com.horen.cortp.platform.presenter.AllPartnerPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import java.util.List;
/**
 * Created by HOREN on 2017/11/28.
 *
 * 全部万箱盟友activity
 *
 */
public class AllPartnerActivity extends BaseActivity<AllPartnerPresenter,AllPartnerContract.Model> implements AllPartnerContract.View{
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private AllPartnerAdapter adapter;
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
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv),getString(R.string.platform_activity_partner));
        recyclerView = (RecyclerView) findViewById(R.id.rv_common);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AllPartnerAdapter(this);
        recyclerView.setAdapter(adapter);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_common);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        mPresenter.getPartner();
    }
    @Override
    public void showLoading(String title) {
    }
    @Override
    public void stopLoading() {
    }
    @Override
    public void showErrorTip(String msg) {
        showErrorToast(msg);
        refreshLayout.finishRefresh(0);
        refreshLayout.finishLoadmore(0);
    }
    @Override
    public void setPartner(List<Partner> list) {
        adapter.setData(list);
        adapter.notifyDataSetChanged();
//        ToastUitl.showShort(list.size()+"");
    }
}