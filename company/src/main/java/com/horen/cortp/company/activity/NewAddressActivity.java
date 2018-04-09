package com.horen.cortp.company.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.horen.cortp.company.R;
import com.horen.cortp.company.api.ApiRequestPram;
import com.horen.cortp.company.bean.AddressBook;
import com.horen.cortp.company.bean.Relation;
import com.horen.cortp.company.contract.NewAddressContract;
import com.horen.cortp.company.presenter.NewAddressPresenter;
import com.jaydenxiao.common.baserx.MsgEvent;
import com.horen.cortp.company.widget.LimitEditText;
import com.horen.cortp.company.widget.SelectOrgDialog;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.EditTextUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by David-Notebook on 2017/10/28.
 */

public class NewAddressActivity extends BaseActivity<NewAddressPresenter, NewAddressContract.Model>
        implements NewAddressContract.View, View.OnClickListener {

    private LinearLayout llSelectOrg;
    private TextView tvNewAddressCompany;
    private LimitEditText etNewAddressName;
    private EditText etNewAddressMobile;
    private EditText etNewAddressArea;
    private EditText etNewAddressDetails;
    private TextView textView2;
    private SwitchCompat switchCompay;
    private SuperButton btOrderSubmit;

    private boolean isNewAddress;//当前是否是新增页面
    private SelectOrgDialog selectOrgDialog;

    /**
     * 新增一个地址
     *
     * @param context
     */
    public static void startAction(Context context, String title) {
        Intent intent = new Intent();
        intent.setClass(context, NewAddressActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 编辑一个地址
     *
     * @param context
     */
    public static void startAction(Context context, String title, AddressBook.CompanyaddressBean companyaddressBean) {
        Intent intent = new Intent();
        intent.setClass(context, NewAddressActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("companyaddressBean", companyaddressBean);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        isIgnoreNetWorkCheck();
        return R.layout.activity_new_address;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        showTitle(getIntent().getStringExtra("title"));

        llSelectOrg = (LinearLayout) findViewById(R.id.ll_select_org);
        tvNewAddressCompany = (TextView) findViewById(R.id.tv_new_address_company);
        etNewAddressName = (LimitEditText) findViewById(R.id.et_new_address_name);
        etNewAddressMobile = (EditText) findViewById(R.id.et_new_address_mobile);
        etNewAddressArea = (EditText) findViewById(R.id.et_new_address_area);
        etNewAddressDetails = (EditText) findViewById(R.id.et_new_address_details);
        textView2 = (TextView) findViewById(R.id.textView2);
        switchCompay = (SwitchCompat) findViewById(R.id.switch_compay);
        btOrderSubmit = (SuperButton) findViewById(R.id.bt_order_submit);

        llSelectOrg.setOnClickListener(this);
        btOrderSubmit.setOnClickListener(this);

        selectOrgDialog = new SelectOrgDialog(mContext);
        selectOrgDialog.setCallBackListener(new SelectOrgDialog.PriorityListener() {
            @Override
            public void callBack(Relation.OrgPartnersBean orgPartnersBean) {
                etNewAddressArea.setText(orgPartnersBean.getCountry_id());
                etNewAddressDetails.setText(orgPartnersBean.getOrg_address());
                tvNewAddressCompany.setText(orgPartnersBean.getOrg_name());
                tvNewAddressCompany.setTag(orgPartnersBean.getOrg_id());
//                switchCompay.setChecked(false);//1是默认

                etNewAddressName.setSelection(etNewAddressName.getText().toString().length());
            }

            @Override
            public void clickStartActivity() {
                //跳转至-新增下游网点
                startActivityForResult(EyeAddPointActivity.class, 0);
            }
        });

        mPresenter.init(getIntent());

        if (TextUtils.equals(getString(R.string.new_address_book), getTitleBar().getTvTitle().getText().toString())) {
            isNewAddress = true;
        } else {
            isNewAddress = false;
            //编辑页面
            btOrderSubmit.setEnabled(true);
        }

        //监听输入框
        EditTextUtils editTextUtils = new EditTextUtils();
        editTextUtils.addEdittexts(etNewAddressName, etNewAddressMobile, etNewAddressArea, etNewAddressDetails)
                .addEdittextInputLinstener(new EditTextUtils.EdittextInputLinstener() {
                    @Override
                    public void onSuccess() {
                        btOrderSubmit.setEnabled(true);
                    }

                    @Override
                    public void onError() {
                        btOrderSubmit.setEnabled(false);
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == EyeAddPointActivity.RESULT_SUCCESS) {
            mPresenter.getListData(true);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.bt_order_submit) {//保存地址
            //判断为新增或者更新
            if (isNewAddress) {
                newSave();
            } else {
                updateSave();
            }
        } else if (i == R.id.ll_select_org) {//选择网点
//            DialogOrgListActivity.startResultAction(this, partner);
            selectOrgDialog.show();
            selectOrgDialog.setNewData(mPresenter.getOrgList());
        }
    }

    /**
     * 新增页面保存功能
     */
    private void newSave() {
        //不传
        String address_book_id = "";
        String contact_zone = "";
        //
        String org_id = tvNewAddressCompany.getTag().toString().trim();
        String area = etNewAddressArea.getText().toString().trim();
        String contact_name = etNewAddressName.getText().toString().trim();
        String contact_tel = etNewAddressMobile.getText().toString().trim();
        String detail = etNewAddressDetails.getText().toString().trim();
        String org_name = tvNewAddressCompany.getText().toString().trim();
        String type = switchCompay.isChecked() ? "1" : "0";//1是默认
        //
        mPresenter.createAddress(ApiRequestPram.updataAddress(address_book_id, area, contact_name
                , contact_tel, contact_zone, detail, org_id, org_name, type));
    }

    /**
     * 编辑页面保存功能
     */
    private void updateSave() {
        String address_book_id = mPresenter.getCompanyaddressBean().getAddress_book_id();
        String org_id = mPresenter.getCompanyaddressBean().getOrg_id();
        String contact_zone = mPresenter.getCompanyaddressBean().getContact_zone();

        String area = etNewAddressArea.getText().toString().trim();
        String contact_name = etNewAddressName.getText().toString().trim();
        String contact_tel = etNewAddressMobile.getText().toString().trim();
        String detail = etNewAddressDetails.getText().toString().trim();
        String org_name = tvNewAddressCompany.getText().toString().trim();
        String type = switchCompay.isChecked() ? "1" : "0";//1是默认
        //
        mPresenter.updateAddress(ApiRequestPram.updataAddress(address_book_id, area, contact_name
                , contact_tel, contact_zone, detail, org_id, org_name, type));
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        showShortToast(msg);
    }

    @Override
    public void setEditTextData(AddressBook.CompanyaddressBean data) {
        etNewAddressName.setText(data.getContact_name());
        etNewAddressMobile.setText(data.getContact_del());
        etNewAddressArea.setText(data.getArea());
        etNewAddressDetails.setText(data.getAddress());
        tvNewAddressCompany.setText(data.getOrg_name());
        switchCompay.setChecked(TextUtils.equals("1", data.getType()) ? true : false);//1是默认
        etNewAddressName.setSelection(etNewAddressName.getText().toString().length());
        tvNewAddressCompany.setTag("");
    }

    @Override
    public void refreshEvent() {
        //更新下单地址
        EventBus.getDefault().post(new MsgEvent().setType(MsgEvent.DEFAULT_ADDRESS_SUCCESS));
        //刷新地址簿列表
        EventBus.getDefault().post(new MsgEvent().setType(MsgEvent.UPDATA_ADDRESS_LIST));
        //
        finish();
    }

    @Override
    public void setTextData(Relation.OrgPartnersBean data) {
        etNewAddressArea.setText(data.getCountry_id());
        etNewAddressDetails.setText(data.getOrg_address());
        tvNewAddressCompany.setText(data.getOrg_name());
        tvNewAddressCompany.setTag(data.getOrg_id());
        etNewAddressName.setSelection(etNewAddressName.getText().toString().length());
        data.setSelected(true);
    }

}
