package com.horen.cortp.service.activity.order.sendbox;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.horen.cortp.service.bean.DeliverPeopleBean;
import com.horen.cortp.service.bean.MsgEvent;
import com.horen.cortp.service.common.ApiServiceFactory;
import com.horen.cortp.service.common.RequestUtils;
import com.horen.cortp.service.widget.DividerItemDecoration;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baserx.RxResultHelper;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.KeybordS;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.weavey.loading.lib.LoadingLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOREN on 2017/10/25.
 */

public class SelectContactsActivity extends BaseActivity implements View.OnClickListener {

    private BaseQuickAdapter adapter;
    private List<DeliverPeopleBean.DataBean> dataBeans = new ArrayList<>();

    private Toolbar toolBar;
    private TextView leftTv;
    private TextView toolBarTitleTv;
    private ImageView rightIv;
    private TextView rightTv;


    private IndexBar mIndexBar;
    private TextView mTvSideBarHint;
    private Button add_constans;

    private SuspensionDecoration mDecoration;

    private EditText etSearchOrder;
    private RecyclerView recyclerContacts;

    private static final int MESSAGE_SEARCH = 0x1;
    private static long INTERVAL = 1000; // 输入变化时间间隔

    private Handler mHandler = new Handler() { // 延迟搜索
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MESSAGE_SEARCH) {
                // 搜索
                queryDeliver();
            }
        }
    };


    private String driver_info;
    private LoadingLayout loadingLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_contacts;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        leftTv = (TextView) findViewById(R.id.left_tv);
        toolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        rightIv = (ImageView) findViewById(R.id.right_iv);
        rightTv = (TextView) findViewById(R.id.right_tv);
        add_constans = (Button) findViewById(R.id.add_constans);
        add_constans.setOnClickListener(this);

        etSearchOrder = (EditText) findViewById(R.id.et_search_order);
        recyclerContacts = (RecyclerView) findViewById(R.id.recycler_contacts);
        loadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);


        setSimpleToolbar(toolBar, toolBarTitleTv, getString(R.string.select_contracts));
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerContacts.setLayoutManager(layoutManager);
        List<DeliverPeopleBean.DataBean> list = new ArrayList<>();
        adapter = new BaseQuickAdapter<DeliverPeopleBean.DataBean, BaseViewHolder>(R.layout.item_contacts, list) {
            @Override
            protected void convert(BaseViewHolder helper, DeliverPeopleBean.DataBean item) {
                helper.setText(R.id.tv_contact_name, item.getDriver_name());
                helper.setText(R.id.tv_contact_phone, item.getDriver_tel());
            }
        };

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KeybordS.closeKeybord(etSearchOrder, mContext);
                Intent intent = new Intent();
                intent.putExtra("DeliverPeopleBean", dataBeans.get(position));
                setResult(-1, intent);
                finish();
            }
        });

        recyclerContacts.setAdapter(adapter);
        // 分组item
        recyclerContacts.addItemDecoration(mDecoration = new SuspensionDecoration(this, dataBeans));
        mDecoration.setColorTitleBg(getResources().getColor(R.color.bg)) // 标题背景
                .setColorTitleFont(getResources().getColor(R.color.text_title_color)); // 标题颜色
        //如果add两个，那么按照先后顺序，依次渲染。
        recyclerContacts.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        //indexbar初始化
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(layoutManager);//设置RecyclerView的LayoutManager

        getDeliverList();
        etSearchOrder.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    KeybordS.closeKeybord(etSearchOrder, mContext);
                    driver_info = v.getText().toString().trim();
                    // 搜索
                    queryDeliver();
                    return true;
                }
                return false;
            }
        });

        etSearchOrder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                driver_info = s.toString();
                if (mHandler.hasMessages(MESSAGE_SEARCH)) { // 输入之后1s发送消息，搜索
                    mHandler.removeMessages(MESSAGE_SEARCH);
                }
                mHandler.sendEmptyMessageDelayed(MESSAGE_SEARCH, INTERVAL);
            }
        });
    }


    /**
     * 配送人列表
     */
    private void getDeliverList() {
        mRxManager.add(ApiServiceFactory.getSingleApi().queryDeliverList(RequestUtils.getDefaultPram())
                .compose(RxResultHelper.<DeliverPeopleBean>handleResult())
                .subscribe(new RxSubscriber<DeliverPeopleBean>(mContext, true) {
                    @Override
                    protected void _onNext(DeliverPeopleBean deliverPeopleBean) {
                        dataBeans = deliverPeopleBean.getData();
                        if (deliverPeopleBean.getData().size() > 0) {
                            loadingLayout.setStatus(LoadingLayout.Success);
                            adapter.setNewData(deliverPeopleBean.getData());
                            mIndexBar.initIndexDatas();
                            mIndexBar.setmSourceDatas(dataBeans)//设置数据
                                    .invalidate();
                            mDecoration.setmDatas(dataBeans);
                        } else {
                            loadingLayout.setStatus(LoadingLayout.Empty);
                            adapter.setNewData(null);
                            adapter.setEmptyView(R.layout.empty_page, (ViewGroup) recyclerContacts.getParent());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        showErrorToast(message);
                        loadingLayout.setStatus(LoadingLayout.Empty);
                        adapter.setNewData(null);
                        adapter.setEmptyView(R.layout.empty_page, (ViewGroup) recyclerContacts.getParent());
                    }
                })
        );
    }


    /**
     * 搜索配送人
     */
    private void queryDeliver() {
        mRxManager.add(ApiServiceFactory.getSingleApi().queryDeliver(RequestUtils.queryDeliver(driver_info))
                .compose(RxResultHelper.<DeliverPeopleBean>handleResult())
                .subscribe(new RxSubscriber<DeliverPeopleBean>(mContext, false) {
                    @Override
                    protected void _onNext(DeliverPeopleBean deliverPeopleBean) {
                        dataBeans = deliverPeopleBean.getData();
                        if (deliverPeopleBean.getData().size() > 0) {
                            loadingLayout.setStatus(LoadingLayout.Success);
                            adapter.setNewData(deliverPeopleBean.getData());
                            mIndexBar.initIndexDatas();
                            mIndexBar.setmSourceDatas(dataBeans)//设置数据
                                    .invalidate();
                            mDecoration.setmDatas(dataBeans);
                        } else {
                            loadingLayout.setStatus(LoadingLayout.Empty);
                            adapter.setNewData(null);
                            adapter.setEmptyView(R.layout.empty_page, (ViewGroup) recyclerContacts.getParent());
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        showErrorToast(message);
                        loadingLayout.setStatus(LoadingLayout.Empty);
                        adapter.setNewData(null);
                        adapter.setEmptyView(R.layout.empty_page, (ViewGroup) recyclerContacts.getParent());
                    }
                })
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 接受通知，刷新列表数据
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MsgEvent event) {
        switch (event.type) {
            case MsgEvent.UPDATA_CONTACTS_LIST: // 刷新数据
                getDeliverList();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_constans) {
            startActivity(AddContactsActivity.class);
        }
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        KeybordS.closeKeybord(etSearchOrder,mContext);
    }
}
