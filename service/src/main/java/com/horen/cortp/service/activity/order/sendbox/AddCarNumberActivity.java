package com.horen.cortp.service.activity.order.sendbox;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.horen.cortp.service.R;
import com.horen.cortp.service.bean.MsgEvent;
import com.horen.cortp.service.common.ApiServiceFactory;
import com.horen.cortp.service.common.RequestUtils;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.KeybordS;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by HOREN on 2017/12/29.
 */

public class AddCarNumberActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvNameType;
    private EditText mEtCarNumber;
    private AppCompatCheckBox mCheckbox;

    private Toolbar mToolBar;
    private TextView mLeftTv;
    private TextView mToolBarTitleTv;
    private ImageView mRightIv;
    private TextView mRightTv;


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_car_number;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {


        mTvNameType = (TextView) findViewById(R.id.tv_name_type);
        mEtCarNumber = (EditText) findViewById(R.id.et_car_number);
        mCheckbox = (AppCompatCheckBox) findViewById(R.id.checkbox);


        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mLeftTv = (TextView) findViewById(R.id.left_tv);
        mToolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        mRightIv = (ImageView) findViewById(R.id.right_iv);
        mRightTv = (TextView) findViewById(R.id.right_tv);

        mRightTv.setVisibility(View.VISIBLE);
        mRightTv.setText(mContext.getString(R.string.change_pwd_submit));

        mRightTv.setOnClickListener(this);

        setSimpleToolbar(mToolBar,mToolBarTitleTv,getString(R.string.service_add_car_number));
    }

    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(mEtCarNumber.getText().toString().trim())) {
            showShortToast(getString(R.string.car_number_not_null));
            return;
        }
        addCarNumber(mEtCarNumber.getText().toString().trim());
    }

    /**
     * 添加车牌号
     *
     * @param carNumber
     */
    private void addCarNumber(String carNumber) {
        mRxManager.add(ApiServiceFactory.getSingleApi().insertVehicleList(RequestUtils.insertVehicleList(carNumber))
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse carNumberBean) {
                        // 关闭页面，通知上一个页面刷新数据
                        finish();
                        EventBus.getDefault().post(new MsgEvent().setType(MsgEvent.UPDATA_CAR_NUMBER_LIST));
                        showToastWithImg(getString(R.string.add_success), R.drawable.icon_success_new);
                    }

                    @Override
                    protected void _onError(String message) {
                        showErrorToast(message);
                    }
                })
        );
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        KeybordS.closeKeybord(mEtCarNumber,mContext);
    }
}
