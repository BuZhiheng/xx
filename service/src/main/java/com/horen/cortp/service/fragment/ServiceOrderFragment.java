package com.horen.cortp.service.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.horen.cortp.service.R;
import com.horen.cortp.service.activity.order.HundredNetOrderActivity;
import com.horen.cortp.service.bean.MapDataBean;
import com.horen.cortp.service.common.ApiServiceFactory;
import com.horen.cortp.service.common.RequestUtils;
import com.horen.maplib.IMyMapView;
import com.horen.maplib.MapFactory;
import com.jaydenxiao.common.api.HttpUrl;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.base.ScanActivity;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.basebean.ScannerRequest;
import com.jaydenxiao.common.baserx.RxResultHelper;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.GsonUtil;
import com.jaydenxiao.common.commonutils.LanguageUtil;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.SpKey;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by HOREN on 2017/10/11.
 */

@Route(path = "/home/ServiceOrderFragment")
public class ServiceOrderFragment extends BaseFragment implements View.OnClickListener {


    private LinearLayout llMapviewEmpty;
    private Button btOrderClick;
    private ImageView ivMapRefresh;
    private ImageView ivMapLocation;
    private RoundKornerLinearLayout rllMarkerView;
    private TextView tvCompanyName;
    private TextView tvCompanyAddress;
    private TextView tvBoxsNumber;

    private View mFakeStatusBar;

    private RelativeLayout mToolBar;
    private ImageView mIvSelectClick;
    private TextView mToolBarTitleTv;
    private ImageView mIvScanClick;
    public static final int REQUEST_CODE = 1002;

    private IMyMapView myMapView;
    private double latitude;
    private double longitude;

    public static String MAPBOX_ACCESS_TOKEN = "pk.eyJ1IjoiY2hlbnl5IiwiYSI6ImNqODQ0ZDU0ZDA1YzUyd3BkbGx3dGdlNmMifQ.G0jDkJHoKkZ7X3TpJul3qw";
    private boolean isChina;
    private MapDataBean mapDataBean;
    private TextView tvDistance;

    public static ServiceOrderFragment newInstance() {
        Bundle args = new Bundle();
        ServiceOrderFragment fragment = new ServiceOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_service_order;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        mFakeStatusBar = (View) rootView.findViewById(R.id.fake_status_bar);

        llMapviewEmpty = (LinearLayout) rootView.findViewById(R.id.ll_mapview_empty);
        btOrderClick = (Button) rootView.findViewById(R.id.bt_order_click);
        btOrderClick.setOnClickListener(this);
        ivMapRefresh = (ImageView) rootView.findViewById(R.id.iv_map_refresh);
        ivMapRefresh.setOnClickListener(this);
        ivMapLocation = (ImageView) rootView.findViewById(R.id.iv_map_location);
        ivMapLocation.setOnClickListener(this);
        rllMarkerView = (RoundKornerLinearLayout) rootView.findViewById(R.id.rll_marker_view);
        tvCompanyName = (TextView) rootView.findViewById(R.id.tv_company_name);
        tvCompanyAddress = (TextView) rootView.findViewById(R.id.tv_company_address);
        tvBoxsNumber = (TextView) rootView.findViewById(R.id.tv_boxs_number);
        tvDistance = (TextView) rootView.findViewById(R.id.tv_distance);

        mToolBar = (RelativeLayout) rootView.findViewById(R.id.tool_bar);
        mIvSelectClick = (ImageView) rootView.findViewById(R.id.iv_select_click);
        mIvSelectClick.setOnClickListener(this);
        mToolBarTitleTv = (TextView) rootView.findViewById(R.id.tool_bar_title_tv);
        mIvScanClick = (ImageView) rootView.findViewById(R.id.iv_scan_click);
        mIvScanClick.setOnClickListener(this);

        mToolBarTitleTv.setText(R.string.tab_text_order);
        mFakeStatusBar.setBackgroundColor(getResources().getColor(R.color.color_main_color));
        btOrderClick.setText(R.string.order_service);
        initMapView();
    }

    /**
     * 加载地图
     */
    private void initMapView() {
//        isChina = true; // 根据手机的语言选择地图
        myMapView = MapFactory.getMapView(getActivity(), isChina);
        myMapView.initMapView(new IMyMapView.OnMapInitListener() {
            @Override
            public void onInitSuccess() {
                // 获取数据，地图加载成功
                initData(true);
            }
        });
        llMapviewEmpty.addView((View) myMapView);
    }

    /**
     * 初始化地图数据
     */
    private void initData(boolean showDialog) {
        // 获取网络数据
        ApiServiceFactory.getSingleApi()
                .getOrgDistanceInfo(RequestUtils.getDefaultPram())
                .compose(RxResultHelper.<MapDataBean>handleResult())
                .subscribe(new RxSubscriber<MapDataBean>(getActivity(), showDialog) {
                    @Override
                    protected void _onNext(MapDataBean databean) {
                        mapDataBean = databean;
                        latitude = Double.valueOf(databean.getComdPdInfo().getOrg_latitude());
                        longitude = Double.valueOf(databean.getComdPdInfo().getOrg_longitude());
                        // 得到地图数据
                        setMapData();
                    }

                    @Override
                    protected void _onError(String message) {
                        // 移动屏幕到中心点
                        myMapView.moveTo(latitude, longitude);
                        mylocation();
                        // 两百公里圆
                        myMapView.drwaCircle(latitude, longitude, Color.parseColor("#00000000"), Color.parseColor("#04A504"), 200000);
//                      // 一百公里圆
                        myMapView.drwaCircle(latitude, longitude, Color.parseColor("#00000000"), Color.parseColor("#53F100"), 100000);
                    }
                });


    }

    /**
     * 设置地图数据
     */
    private void setMapData() {
        myMapView.clearMap(); // 清除mapview
        mylocation();
        // 移动屏幕到中心点
        myMapView.moveTo(latitude, longitude);
        for (int i = 0; i < mapDataBean.getOwnerList().size(); i++) {
            MapDataBean.OwnerListBean bean = mapDataBean.getOwnerList().get(i);
            if (Integer.valueOf(bean.getSum()) == 0) { // 箱子个数为0
                View inflate = View.inflate(getActivity(), R.layout.marker_org_normal, null);
                TextView tvOrg = (TextView) inflate.findViewById(R.id.tv_org);
                tvOrg.setText(Integer.valueOf(bean.getSum()) + "");
                tvOrg.setBackgroundResource(R.drawable.icon_org_zero);
                myMapView.addMarker(Double.valueOf(bean.getOrg_latitude()), Double.valueOf(bean.getOrg_longitude()), inflate, i + 1, true);
            } else {
                View inflate = View.inflate(getActivity(), R.layout.marker_org_normal, null);
                TextView tvOrg = (TextView) inflate.findViewById(R.id.tv_org);
                if (Integer.valueOf(bean.getSum()) > 99) {
                    tvOrg.setText("99+");
                } else {
                    tvOrg.setText(Integer.valueOf(bean.getSum()) + "");
                }
                myMapView.addMarker(Double.valueOf(bean.getOrg_latitude()), Double.valueOf(bean.getOrg_longitude()), inflate, i + 1, true);
            }
        }
//        // 两百公里圆
        myMapView.drwaCircle(latitude, longitude, Color.parseColor("#00000000"), Color.parseColor("#04A504"), 200000);
//        // 一百公里圆
        myMapView.drwaCircle(latitude, longitude, Color.parseColor("#00000000"), Color.parseColor("#53F100"), 100000);
//        // markerView点击
        myMapView.setOverlayClickListener(new IMyMapView.OnOverlayClickListener() {
            @Override
            public void onClick(int position) {
                onMarkerClick(position);
            }
        });
        // 地图单击
        myMapView.setMapClickListener(new IMyMapView.OnMapClickListener() {
            @Override
            public void onMapClick() { // 隐藏布局
                rllMarkerView.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 当前位置
     */
    private void mylocation() {
        View inflate1 = View.inflate(getActivity(), R.layout.marker_org_normal, null);
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
        MapDataBean.OwnerListBean bean = mapDataBean.getOwnerList().get(position - 1);
        myMapView.moveToMarker(Double.valueOf(bean.getOrg_latitude()), Double.valueOf(bean.getOrg_longitude())); // markerview
        // 显示
        rllMarkerView.setVisibility(View.VISIBLE);
        tvBoxsNumber.setText(getString(R.string.scanner_box_num) + bean.getSum()); // 箱子个数
        tvCompanyName.setText(getString(R.string.account) + bean.getOrg_name()); // 公司名
        tvCompanyAddress.setText(getString(R.string.service_company_address) + bean.getOrg_address()); // 公司地址
        tvDistance.setText(bean.getDistance() + "KM");

        if (!isChina) return;
        // 移除所有的marker
        myMapView.clearMap();
        mylocation();
        for (int i = 0; i < mapDataBean.getOwnerList().size(); i++) {
            MapDataBean.OwnerListBean ownerListBean = mapDataBean.getOwnerList().get(i); // 网点对象
            if (Integer.valueOf(ownerListBean.getSum()) == 0) { // 箱子个数为0
                View inflate = null;
                if (i == position - 1) { // 箱子个数为0，并且当前点击了该Marker
                    inflate = View.inflate(getActivity(), R.layout.marker_org_select, null);
                    TextView tvOrg = (TextView) inflate.findViewById(R.id.tv_org);
                    tvOrg.setText(ownerListBean.getSum() + "");
                    tvOrg.setBackgroundResource(R.drawable.icon_org_zero_select);
                } else {
                    inflate = View.inflate(getActivity(), R.layout.marker_org_normal, null);
                    TextView tvOrg = (TextView) inflate.findViewById(R.id.tv_org);
                    tvOrg.setText(ownerListBean.getSum() + "");
                    tvOrg.setBackgroundResource(R.drawable.icon_org_zero);
                }
                myMapView.addMarker(Double.valueOf(ownerListBean.getOrg_latitude()), Double.valueOf(ownerListBean.getOrg_longitude()), inflate, i + 1, false);
            } else if (position - 1 == i) { // 选择marker
                View inflate = View.inflate(getActivity(), R.layout.marker_org_select, null);
                TextView tvOrg = (TextView) inflate.findViewById(R.id.tv_org);
                if (Integer.valueOf(ownerListBean.getSum()) > 99) {
                    tvOrg.setText("99+");
                } else {
                    tvOrg.setText(ownerListBean.getSum() + "");
                }
                myMapView.addMarker(Double.valueOf(ownerListBean.getOrg_latitude()), Double.valueOf(ownerListBean.getOrg_longitude()), inflate, i + 1, false);
            } else { // 未选中marker
                View inflate = View.inflate(getActivity(), R.layout.marker_org_normal, null);
                TextView tvOrg = (TextView) inflate.findViewById(R.id.tv_org);
                if (Integer.valueOf(ownerListBean.getSum()) > 99) {
                    tvOrg.setText("99+");
                } else {
                    tvOrg.setText(ownerListBean.getSum() + "");
                }
                myMapView.addMarker(Double.valueOf(ownerListBean.getOrg_latitude()), Double.valueOf(ownerListBean.getOrg_longitude()), inflate, i + 1, false);
            }
        }
        // 两百公里圆
        myMapView.drwaCircle(latitude, longitude, Color.parseColor("#00000000"), Color.parseColor("#04A504"), 200000);
        // 一百公里圆
        myMapView.drwaCircle(latitude, longitude, Color.parseColor("#00000000"), Color.parseColor("#53F100"), 100000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myMapView != null && isChina) // mapBox调用onDestroy方法无响应（原因未知）
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_order_click) {//订单
            HundredNetOrderActivity.startAction(getActivity(), 0);
        } else if (v.getId() == R.id.iv_map_location) {//
            myMapView.moveTo(latitude, longitude);
        } else if (v.getId() == R.id.iv_map_refresh) {//
            if (isChina) {
                initData(false);
            } else {
                myMapView.moveTo(latitude, longitude);
            }
        } else if (v.getId() == R.id.iv_select_click) {// 个人信息
            ARouter.getInstance().build("/serverclient/MeActivity").navigation();
        } else if (v.getId() == R.id.iv_scan_click) {// 扫码
            startActivityForResult(ScanActivity.class, REQUEST_CODE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    LogUtils.loge("二维码扫描结果:" + result);
                    try {
                        ScannerRequest scannerRequest = GsonUtil.getGson().fromJson(result, ScannerRequest.class);
                        scannerRequest.setApp_token(AppConfig.getInstance().getString(SpKey.LOGIN_TOKEN, ""));
                        scannerRequest.setLocale(LanguageUtil.getLanguageParam());
                        scannerRequest.setClient_type("1");
                        //H5
                        String url = HttpUrl.SCAN_SIGN;
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("url", url);
                        bundle1.putSerializable("scannerRequest", scannerRequest);
                        ARouter.getInstance().build("/web/CompanyWebViewActivity").with(bundle1).navigation();
                    } catch (Exception e) {
                        e.printStackTrace();
                        showShortToast(result + "");
                    }

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                }
            }
        }
    }
}
