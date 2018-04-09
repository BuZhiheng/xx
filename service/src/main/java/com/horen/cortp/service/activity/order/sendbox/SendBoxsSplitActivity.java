package com.horen.cortp.service.activity.order.sendbox;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.horen.cortp.service.R;
import com.horen.cortp.service.adapter.SplitOrderAdapter;
import com.horen.cortp.service.bean.CarNumberBean;
import com.horen.cortp.service.bean.DeliverPeopleBean;
import com.horen.cortp.service.bean.MsgEvent;
import com.horen.cortp.service.common.Constract;
import com.horen.cortp.service.common.RequestUtils;
import com.horen.cortp.service.mvp.contract.SendBoxOrderDetailContract;
import com.horen.cortp.service.mvp.model.SendBoxOrderDetailModel;
import com.horen.cortp.service.mvp.persenter.SendBoxOrderDetailPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.basebean.DeliverContact;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by HOREN on 2017/10/19.
 */

public class SendBoxsSplitActivity extends BaseActivity<SendBoxOrderDetailPresenter, SendBoxOrderDetailModel> implements SendBoxOrderDetailContract.View, View.OnClickListener {


    private SplitOrderAdapter splitOrderAdapter;
    private String order_type;
    private String plan_id;
    private String line_status;

    private Toolbar toolBar;
    private TextView leftTv;
    private TextView toolBarTitleTv;
    private ImageView rightIv;
    private TextView rightTv;


    private TextView tvOrderNumber;
    private TextView tvOrderTime;
    private TextView tvOrderCompany;
    private TextView tvOrderDeliveryStatus;
    private TextView tvToCompanyname;
    private TextView tvToAddress;
    private TextView tvToContact;
    private TextView tvToTel;
    private TextView tvCollectWarehouse;
    private RecyclerView rvGoods;
    private TextView tvRequestTime;
    private TextView tvDeliveryPerson;
    private TextView tvDeliveryPeople;
    private TextView tvNumber;
    private TextView tvDeliveryPhone;
    private TextView tvCarNumber;
    private TextView tvDeliveryCarNumber;
    private Button btSplitOrder;
    private Button btStartDelivery;


    private int SELECT_CONTACT = 1001;
    private int SELECT_CAR_NUMBER = 1002;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_box_split;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        leftTv = (TextView) findViewById(R.id.left_tv);
        toolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        rightIv = (ImageView) findViewById(R.id.right_iv);
        rightTv = (TextView) findViewById(R.id.right_tv);


        tvOrderNumber = (TextView) findViewById(R.id.tv_order_number);
        tvOrderTime = (TextView) findViewById(R.id.tv_order_time);
        tvOrderCompany = (TextView) findViewById(R.id.tv_order_company);
        tvOrderDeliveryStatus = (TextView) findViewById(R.id.tv_order_delivery_status);
        tvToCompanyname = (TextView) findViewById(R.id.tv_to_companyname);
        tvToAddress = (TextView) findViewById(R.id.tv_to_address);
        tvToContact = (TextView) findViewById(R.id.tv_to_contact);
        tvToTel = (TextView) findViewById(R.id.tv_to_tel);
        tvCollectWarehouse = (TextView) findViewById(R.id.tv_collect_warehouse);
        rvGoods = (RecyclerView) findViewById(R.id.rv_goods);
        tvRequestTime = (TextView) findViewById(R.id.tv_request_time);
        tvDeliveryPerson = (TextView) findViewById(R.id.tv_delivery_person);
        tvDeliveryPeople = (TextView) findViewById(R.id.tv_delivery_people);
        tvDeliveryPeople.setOnClickListener(this);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        tvDeliveryPhone = (TextView) findViewById(R.id.tv_delivery_phone);
        tvDeliveryPhone.setOnClickListener(this);
        tvCarNumber = (TextView) findViewById(R.id.tv_car_number);
        tvDeliveryCarNumber = (TextView) findViewById(R.id.tv_delivery_car_number);
        tvDeliveryCarNumber.setOnClickListener(this);
        btSplitOrder = (Button) findViewById(R.id.bt_split_order);
        btSplitOrder.setOnClickListener(this);
        btStartDelivery = (Button) findViewById(R.id.bt_start_delivery);
        btStartDelivery.setOnClickListener(this);


        order_type = getIntent().getStringExtra("order_type");
        plan_id = getIntent().getStringExtra("plan_id");
        line_status = getIntent().getStringExtra("line_status");
        rvGoods.setLayoutManager(new LinearLayoutManager(mContext));
        rvGoods.setFocusable(false); // 防止scrollview嵌套recycleView不在顶部的bug
        // 标题
        if (order_type.equals(Constract.SEND_BOX)) {
            setSimpleToolbar(toolBar, toolBarTitleTv, getString(R.string.send_boxs_order));
        } else if (order_type.equals(Constract.SUPPLIES)) {
            setSimpleToolbar(toolBar, toolBarTitleTv, getString(R.string.supplies_order));
        }
        // 请求数据
        mPresenter.queryOrderDetail(RequestUtils.OrderInfo(order_type, plan_id, line_status)); // 请求数据
    }


    /**
     * 根据map返回key和value的list
     *
     * @param map
     * @return
     */
    public static List<SendBoxOrderDetailBean.ProductsBean> getListByMap(LinkedHashMap<Integer, SendBoxOrderDetailBean.ProductsBean> map) {
        List<SendBoxOrderDetailBean.ProductsBean> list = new ArrayList<>();

        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            list.add(map.get(key));
        }
        return list;
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void getOrderDetailSuccess(SendBoxOrderDetailBean orderBean) {
        splitOrderAdapter = new SplitOrderAdapter(R.layout.item_split_order, orderBean.getProducts());
        rvGoods.setAdapter(splitOrderAdapter);
        // 订单信息
        tvOrderNumber.setText(getString(R.string.order_number) + orderBean.getOrderinfo().getPlan_id());
        tvOrderTime.setText(orderBean.getOrderinfo().getCreate_date());
        tvOrderCompany.setText(orderBean.getOrderinfo().getOrder_companyname());
        tvOrderCompany.setText(orderBean.getOrderinfo().getOrder_companyname());
        tvOrderDeliveryStatus.setText(orderBean.getOrderinfo().getLine_status_value());
        tvToCompanyname.setText(orderBean.getOrderinfo().getTo_orgname());
        tvToAddress.setText(orderBean.getOrderinfo().getTo_orgaddress());
        tvToContact.setText(getString(R.string.contacts) + orderBean.getOrderinfo().getTo_orgcontact());
        tvToTel.setText(getString(R.string.contact_number) + orderBean.getOrderinfo().getTo_orgtel());
        tvRequestTime.setText(getString(R.string.request_delivery_time) + orderBean.getOrderinfo().getReq_deliverdate());
        tvCollectWarehouse.setText(getString(R.string.pickup_warehouse) + orderBean.getOrderinfo().getFrom_orgname());
    }

    @Override
    public void getOrderDetailError(String msg) {
        showErrorToast(msg);
    }

    @Override
    public void createTransOrderSuccess(BaseResponse respose) {
        finish(); // 刷新数据
        // 通知送箱订单页面关闭，并且刷新首页
        EventBus.getDefault().post(new MsgEvent().setType(MsgEvent.FINISH_DILIVERY_ACTIVITY));
    }

    public static void startAction(Context context, String order_type, String plan_id, String line_status) {
        Intent intent = new Intent();
        intent.setClass(context, SendBoxsSplitActivity.class);
        intent.putExtra("order_type", order_type);
        intent.putExtra("plan_id", plan_id);
        intent.putExtra("line_status", line_status);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_CONTACT && resultCode == -1) { // 选择联系人
            DeliverPeopleBean.DataBean bean = (DeliverPeopleBean.DataBean) data.getSerializableExtra("DeliverPeopleBean");
            tvDeliveryPeople.setText(bean.getDriver_name());
            tvDeliveryPhone.setText(bean.getDriver_tel());
        } else if (requestCode == SELECT_CAR_NUMBER && resultCode == -1) { // 选择车牌号
            CarNumberBean.DataBean bean = (CarNumberBean.DataBean) data.getSerializableExtra("CarNumberBean");
            tvDeliveryCarNumber.setText(bean.getVehicle_id());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_delivery_people) {
            startActivityForResult(SelectContactsActivity.class, SELECT_CONTACT);
        } else if (v.getId() == R.id.tv_delivery_phone) {
            startActivityForResult(SelectContactsActivity.class, SELECT_CONTACT);
        } else if (v.getId() == R.id.tv_delivery_car_number) {
            startActivityForResult(SelectCarNumberActivity.class, SELECT_CAR_NUMBER);
        } else if (v.getId() == R.id.bt_split_order) {
            finish();
        } else if (v.getId() == R.id.bt_start_delivery) {
            if (splitOrderAdapter != null) {
                LinkedHashMap<Integer, SendBoxOrderDetailBean.ProductsBean> map = splitOrderAdapter.getSelectSplitBox();
                if (map.size() > 0) {
                    if (TextUtils.isEmpty(tvDeliveryPeople.getText().toString()) || TextUtils.isEmpty(tvDeliveryPhone.getText().toString()) || TextUtils.isEmpty(tvDeliveryCarNumber.getText().toString())) {
                        showShortToast(R.string.contacts_info_tip);
                        return;
                    }
                    List<SendBoxOrderDetailBean.ProductsBean> listByMap = getListByMap(map);
                    mPresenter.createTransOrder(RequestUtils.createTransOrder(new DeliverContact(tvDeliveryCarNumber.getText().toString(), tvDeliveryPeople.getText().toString(), tvDeliveryPhone.getText().toString())
                            , order_type, plan_id, line_status, listByMap));
                } else {
                    showShortToast(getString(R.string.pls_select_split_goods));
                }
            }
        }
    }
}
