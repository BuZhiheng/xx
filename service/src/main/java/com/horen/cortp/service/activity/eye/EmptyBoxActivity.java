package com.horen.cortp.service.activity.eye;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.model.LatLng;
import com.horen.cortp.service.R;
import com.horen.cortp.service.bean.EmptyBoxBean;
import com.horen.cortp.service.common.Constract;
import com.horen.maplib.IMyMapView;
import com.horen.maplib.MapFactory;
import com.horen.maplib.MyLocation;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.commonutils.CUtils;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOREN on 2018/1/19.
 */

@Route(path = CommonRoutePath.EMPTY_BOX_ACTIVITY)
public class EmptyBoxActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mToolBar;
    private TextView mToolBarTitleTv;
    private ImageView mRightIv;
    private TextView mRightTv;
    private EditText mEtSearch;
    private LinearLayout mLlMapviewEmpty;
    private Button mBtOrderClick;
    private LinearLayout mRllMarkerView;
    private TextView mTvAllEmptyBoxsNumber;
    private LinearLayout mLlContent;
    private TextView mTvCompanyName;
    private TextView mTvCompanyAddress;
    private TextView mTvBoxsNumber;
    private LinearLayout mLlOpen;
    private ImageView mIvOpen;
    private ImageView mIvMapLocation;

    private boolean isOpen = false; // 默认关闭
    private int mHeight;
    private boolean isChina;
    private IMyMapView myMapView;
    private double latitude;
    private double longitude;
    private List<LatLng> latLngs;
    private List<EmptyBoxBean> emptyBoxBeans;

    @Override
    public int getLayoutId() {
        return R.layout.service_activity_empty_box;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mToolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        mRightIv = (ImageView) findViewById(R.id.right_iv);
        mRightIv.setOnClickListener(this);
        mRightTv = (TextView) findViewById(R.id.right_tv);
        mRightTv.setOnClickListener(this);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mLlMapviewEmpty = (LinearLayout) findViewById(R.id.ll_mapview_empty);
        mBtOrderClick = (Button) findViewById(R.id.bt_order_click);
        mBtOrderClick.setOnClickListener(this);
        mRllMarkerView = (LinearLayout) findViewById(R.id.rll_marker_view);
        mTvAllEmptyBoxsNumber = (TextView) findViewById(R.id.tv_all_empty_boxs_number);
        mLlContent = (LinearLayout) findViewById(R.id.ll_content);
        mLlContent.setOnClickListener(this);
        mTvCompanyName = (TextView) findViewById(R.id.tv_company_name);
        mTvCompanyAddress = (TextView) findViewById(R.id.tv_company_address);
        mTvBoxsNumber = (TextView) findViewById(R.id.tv_boxs_number);
        mLlOpen = (LinearLayout) findViewById(R.id.ll_open);
        mLlOpen.setOnClickListener(this);
        mIvOpen = (ImageView) findViewById(R.id.iv_open);
        mIvMapLocation = (ImageView) findViewById(R.id.iv_map_location);
        mIvMapLocation.setOnClickListener(this);
        mHeight = DisplayUtil.getViewHeight(mLlContent);
        setSimpleToolbar(mToolBar, mToolBarTitleTv, CUtils.getString(mContext, R.string.empty_box));

        latLngs = new ArrayList<>();
//        latLngs.add(new LatLng(31.185183, 121.381580));
//        latLngs.add(new LatLng(31.345183, 119.401580));
//        latLngs.add(new LatLng(31.545183, 120.361580));
        latLngs.add(new LatLng(31.41460, 120.98693));
//        latLngs.add(new LatLng(29.205183, 121.671580));
        emptyBoxBeans = new ArrayList<>();
        emptyBoxBeans.add(new EmptyBoxBean(latLngs.get(0).latitude, latLngs.get(0).longitude, "上海鸿硏物流科技有限公司", "上海闵行区田林路1360号", 4, "史振香", "18274569875"));
        initMapView();
    }


    /**
     * 加载地图
     */
    private void initMapView() {
//        isChina = LanguageUtil.isZh(); // 根据手机的语言选择地图
        myMapView = MapFactory.getMapView(mContext, isChina);
        MyLocation.getSingleLocation(mContext, new MyLocation.OnLocationListener() {
            /**
             * 先获取当前经纬度
             *  再初始化地图(MapBox需要在onMapReady回调以后才能操作地图)
             * 定位成功&地图初始化完成 之后 请求数据，操作地图
             * */
            @Override
            public void location(final double lat, final double lng) {
                myMapView.initMapView(new IMyMapView.OnMapInitListener() {
                    @Override
                    public void onInitSuccess() {
                        latitude = lat;
                        longitude = lng;
                        // 获取数据，地图加载成功
                        initData();
                    }
                });
            }
        });
        mLlMapviewEmpty.addView((View) myMapView);
    }


    private void initData() {
        // 用户
        mTvCompanyName.setText(emptyBoxBeans.get(0).getCompany_name());
        // 公司地址
        mTvCompanyAddress.setText(emptyBoxBeans.get(0).getCompany_address());
        // 空箱数
        mTvBoxsNumber.setText(emptyBoxBeans.get(0).getEmpty_boxs_number() + "");
        // 总空箱数
        int total = 0;
        for (EmptyBoxBean emptyBoxBean : emptyBoxBeans) {
            total += emptyBoxBean.getEmpty_boxs_number();
        }
        mTvAllEmptyBoxsNumber.setText(total + "");

        myMapView.clearMap(); // 清除mapview
        mylocation();
        // 移动屏幕到中心点
        myMapView.moveTo(latitude, longitude);
        for (int i = 0; i < latLngs.size(); i++) {
            View inflate = View.inflate(mContext, R.layout.marker_org_normal, null);
            TextView tvOrg = (TextView) inflate.findViewById(R.id.tv_org);
            tvOrg.setBackgroundResource(R.drawable.icon_empty_box);
            myMapView.addMarker(latLngs.get(i).latitude, latLngs.get(i).longitude, inflate, i + 1, true);
        }
        // 两百公里圆
        myMapView.drwaCircle(latitude, longitude, Color.parseColor("#00000000"), Color.parseColor("#04A504"), 200000);
        // 一百公里圆
        myMapView.drwaCircle(latitude, longitude, Color.parseColor("#00000000"), Color.parseColor("#53F100"), 100000);
        // 地图单击
        myMapView.setMapClickListener(new IMyMapView.OnMapClickListener() {
            @Override
            public void onMapClick() { // 隐藏布局
//                rllMarkerView.setVisibility(View.GONE);
            }
        });
        // markerView点击
        myMapView.setOverlayClickListener(new IMyMapView.OnOverlayClickListener() {
            @Override
            public void onClick(int position) {
                onMarkerClick(position);
            }
        });
    }


    /**
     * 当前位置
     */
    private void mylocation() {
        View inflate1 = View.inflate(mContext, R.layout.marker_org_normal, null);
        TextView tvOrg1 = (TextView) inflate1.findViewById(R.id.tv_org);
        tvOrg1.setBackgroundResource(R.drawable.icon_location);
        myMapView.addMarker(latitude, longitude, inflate1, 0, false);
    }

    /**
     * markerView点击
     *
     * @param position
     */
    private void onMarkerClick(int position) {
        if (position == 0) { // 我的位置
            myMapView.moveToMarker(latitude, longitude); // 我的位置
            return;
        }
        mLlContent.setVisibility(View.VISIBLE);
        myMapView.moveToMarker(latLngs.get(position - 1).latitude, latLngs.get(position - 1).longitude);
        // 用户
        mTvCompanyName.setText(emptyBoxBeans.get(position - 1).getCompany_name());
        // 公司地址
        mTvCompanyAddress.setText(emptyBoxBeans.get(position - 1).getCompany_address());
        // 空箱数
        mTvBoxsNumber.setText(emptyBoxBeans.get(position - 1).getEmpty_boxs_number() + "");
    }


    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        hintKbTwo();
    }

    //此方法只是关闭软键盘 可以在finish之前调用一下
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_open) { // 点击开启关闭
            mLlContent.setVisibility(isOpen ? View.GONE : View.VISIBLE); // 是否展示信息
            if (isOpen) {
                mLlContent.setVisibility(View.GONE);
            } else {
                mLlContent.setVisibility(View.VISIBLE);
            }
            mIvOpen.setImageResource(isOpen ? R.drawable.service_icon_colse : R.drawable.service_icon_open);
            isOpen = !isOpen;
        } else if (view.getId() == R.id.right_iv) {// 搜索框出现，返回键隐藏，取消建出现。
            mEtSearch.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mRightTv.setVisibility(View.VISIBLE);
            mRightIv.setVisibility(View.GONE);
            mToolBarTitleTv.setVisibility(View.GONE);
        } else if (view.getId() == R.id.right_tv) {
            mEtSearch.setVisibility(View.GONE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mRightTv.setVisibility(View.GONE);
            mRightIv.setVisibility(View.VISIBLE);
            mToolBarTitleTv.setVisibility(View.VISIBLE);
            hintKbTwo();
        } else if (view.getId() == R.id.iv_map_location) {
            myMapView.moveTo(latitude, longitude);
        } else if (view.getId() == R.id.ll_content || view.getId() == R.id.bt_order_click) { // 跳转列表
            Intent intent = new Intent(mContext, EmptyBoxListActivity.class);
            intent.putExtra("emptyBoxBeans", (Serializable) emptyBoxBeans);
            startActivity(intent);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myMapView != null)
            myMapView.onDestroyMap();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (myMapView != null)
            myMapView.onPauseMap();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (myMapView != null)
            myMapView.onResumeMap();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (myMapView != null)
            myMapView.onSaveInstanceStateMap(outState);
    }
}
