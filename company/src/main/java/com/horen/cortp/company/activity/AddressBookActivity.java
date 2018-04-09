package com.horen.cortp.company.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.horen.cortp.company.R;
import com.horen.cortp.company.adapter.AddressAdapter;
import com.horen.cortp.company.api.ApiRequestPram;
import com.horen.cortp.company.bean.AddressBook;
import com.horen.cortp.company.contract.AddressContract;
import com.horen.cortp.company.model.AddressModel;
import com.horen.cortp.company.presenter.AddressPresenter;
import com.jaydenxiao.common.baserx.MsgEvent;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.MessageDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David-Notebook on 2017/10/28.
 */
public class AddressBookActivity extends BaseActivity<AddressPresenter, AddressModel>
        implements AddressContract.View, OnRefreshListener, View.OnClickListener {
    public final static int RESULT_SUCCESS = 66;

    private TextView tvAddressAdd;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;

    private AddressAdapter adapter;

    public static void startAction(Context context, String mine) {
        Intent intent = new Intent();
        intent.setClass(context, AddressBookActivity.class);
        intent.putExtra("mine", mine);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Fragment fragment, AddressBook addressBook) {
        Intent intent = new Intent();
        intent.setClass(fragment.getContext(), AddressBookActivity.class);
        intent.putExtra("addressBook", addressBook);
        fragment.startActivityForResult(intent, 0);
    }

    public static void startActivityForResult(Fragment fragment) {
        Intent intent = new Intent();
        intent.setClass(fragment.getContext(), AddressBookActivity.class);
        fragment.startActivityForResult(intent, 0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_address_book;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void reLoadData() {
        super.reLoadData();
        mPresenter.init(getIntent());
    }

    @Override
    public void initView() {
        showTitle(getString(R.string.address_book));
        EventBus.getDefault().register(this);

        tvAddressAdd = (TextView) findViewById(R.id.tv_address_add);
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        tvAddressAdd.setOnClickListener(this);

        refreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressAdapter(R.layout.item_address, new ArrayList<AddressBook.CompanyaddressBean>());
        recyclerView.setAdapter(adapter);
        initListener();

        mPresenter.init(getIntent());
    }

    private void initListener() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, final int position) {
                final AddressBook.CompanyaddressBean companyaddressBean = adapter.getData().get(position);
                int i = view.getId();
                if (i == R.id.view_default_click) {//设置为默认地址
                    mPresenter.setAddressDefault(ApiRequestPram.setDefaultAddress(companyaddressBean.getAddress_book_id()));
                } else if (i == R.id.ll_edit_click) {//编辑地址
                    NewAddressActivity.startAction(AddressBookActivity.this, getString(R.string.edit_address_book), companyaddressBean);
                } else if (i == R.id.ll_address_remove) {//删除地址
                    new MessageDialog(mContext)
                            .showTitle("\n删除\n")
                            .isShowContent(false)
                            .setButtonTexts("是", "否")
                            .setButtonTextsColors(Color.parseColor("#6FBA2C"), Color.parseColor("#666666"))
                            .setOnClickListene(new MessageDialog.OnClickListener() {
                                @Override
                                public void onLeftClick() {
                                    //确定
                                    mPresenter.deleteAddress(position, companyaddressBean.getAddress_book_id());
                                }

                                @Override
                                public void onRightClick() {
                                }
                            })
                            .show();
                }
            }
        });
        //选择地址
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
                //Mine页面跳编辑
                if (mPresenter.isEdit()) {
                    NewAddressActivity.startAction(AddressBookActivity.this, getString(R.string.edit_address_book), adapter.getData().get(position));
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("addressBean", adapter.getData().get(position));
                    setResult(RESULT_SUCCESS, resultIntent);
                    finish();
                }
            }
        });
    }

    // -------------------- EventBus开始 --------------------- //

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MsgEvent event) {
        switch (event.type) {
            case MsgEvent.UPDATA_ADDRESS_LIST:
                autoRefresh();
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // -------------------- EventBus结束 --------------------- //

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_address_add) {
            NewAddressActivity.startAction(this, getString(R.string.new_address_book));
        }
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
    public void setListNewData(List<AddressBook.CompanyaddressBean> data) {
        adapter.setNewData(data);
        //设置默认选中
        setDefaultView();
    }

    @Override
    public void removeItem(int position, String msg) {
        showShortToast(msg);
        adapter.remove(position);
        if (adapter.getData().size() == 0) {
            setEmptyView();
        }
    }

    @Override
    public void setEmptyView() {
        adapter.setNewData(null);
        adapter.setEmptyView(R.layout.empty_page, (ViewGroup) recyclerView.getParent());
    }

    @Override
    public void autoRefresh() {
        refreshLayout.autoRefresh();
    }

    @Override
    public void finishRefresh() {
        refreshLayout.finishRefresh();
    }

    @Override
    public void refreshEvent() {
        //刷新上一个页面
        EventBus.getDefault().post(new MsgEvent().setType(MsgEvent.DEFAULT_ADDRESS_SUCCESS));
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPresenter.getAddressBook(ApiRequestPram.getDefaultPram());
    }

    /**
     * 设置默认选中
     */
    private void setDefaultView() {
        adapter.getmCheckStates().clear();
        List<AddressBook.CompanyaddressBean> beanList = adapter.getData();
        if (beanList != null && beanList.size() > 0) {
            if (TextUtils.equals("1", beanList.get(0).getType())) {
                adapter.getmCheckStates().put(0, true);
            }
        }
    }
}
