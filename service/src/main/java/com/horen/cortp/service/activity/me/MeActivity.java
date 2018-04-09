package com.horen.cortp.service.activity.me;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.CommonTextView;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.basebean.UserInfo;
import com.jaydenxiao.common.commonutils.ACache;
import com.jaydenxiao.common.commonutils.GlideUtils;
import com.jaydenxiao.common.commonutils.SpKey;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HOREN on 2017/11/30.
 */
@Route(path = "/serverclient/MeActivity")
public class MeActivity extends BaseActivity implements View.OnClickListener {
    /**  */
    private TextView mLeftTv;
    /**  */
    private TextView mToolBarTitleTv;
    private ImageView mRightIv;
    private TextView mRightTv;
    private Toolbar mToolBar;
    private CircleImageView mUserCiv;
    private CommonTextView mTvFanyong;
    private CommonTextView mTvYunli;
    private CommonTextView mTvKefu;
    private CommonTextView mTvSetting;
    private UserInfo userInfo;
    private TextView mTvUserName;
    private CommonTextView tv_collect;

    @Override
    public int getLayoutId() {
        return R.layout.activity_me;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void initView() {
        userInfo = (UserInfo) ACache.get(mContext).getAsObject(SpKey.USERINFO);
        mLeftTv = (TextView) findViewById(R.id.left_tv);
        mLeftTv.setOnClickListener(this);
        mToolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        mToolBarTitleTv.setOnClickListener(this);
        mRightIv = (ImageView) findViewById(R.id.right_iv);
        mRightTv = (TextView) findViewById(R.id.right_tv);
        mTvUserName = (TextView) findViewById(R.id.tv_user_name);
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mUserCiv = (CircleImageView) findViewById(R.id.user_civ);
        mUserCiv.setOnClickListener(this);
        mTvFanyong = (CommonTextView) findViewById(R.id.tv_fanyong);
        tv_collect = (CommonTextView) findViewById(R.id.tv_collect);
        tv_collect.setOnClickListener(this);
        mTvFanyong.setOnClickListener(this);
        mTvYunli = (CommonTextView) findViewById(R.id.tv_yunli);
        mTvYunli.setOnClickListener(this);
        mTvKefu = (CommonTextView) findViewById(R.id.tv_kefu);
        mTvKefu.setOnClickListener(this);
        mTvSetting = (CommonTextView) findViewById(R.id.tv_setting);
        mTvSetting.setOnClickListener(this);
        // 设置标题栏
        setSimpleToolbar(mToolBar, mToolBarTitleTv, "");
        // 头像图片
        GlideUtils.loadCenterCrop(mContext, userInfo.getUser().getPhoto(), mUserCiv);

        mTvUserName.setText(userInfo.getUser().getUser_name());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.user_civ) {
            startActivity(AccountDetailActivity.class);
        } else if (view.getId() == R.id.tv_fanyong) {
            startActivity(RebateActivity.class);
        } else if (view.getId() == R.id.tv_yunli) {
            startActivity(CapacityActivity.class);

        } else if (view.getId() == R.id.tv_kefu) {
            showDialog();
        } else if (view.getId() == R.id.tv_setting) {
            startActivity(SettingsActivity.class);
        } else if (view.getId() == R.id.tv_collect) {
            ARouter.getInstance().build(CommonRoutePath.PLATFORM_ACTIVITY_COLLECTION).navigation(); // 我的收藏
        }
    }

    private void showDialog() {
        final NormalDialog dialog = new NormalDialog(mContext);
        dialog.isTitleShow(false)//
                .cornerRadius(5)//
                .content("02134122709")// 客服电话
                .contentGravity(Gravity.CENTER)//
                .btnTextSize(15.5f, 15.5f)//
                .widthScale(0.85f)//
                .btnText(getString(R.string.cancle_service), getString(R.string.call_phone))
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                        // 调用系统的拨打电话页面
                        call("02134122709");
                    }
                });
    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    public void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userInfo = (UserInfo) ACache.get(mContext).getAsObject(SpKey.USERINFO);
        // 头像图片
        if (mUserCiv != null)
            GlideUtils.loadCenterCrop(mContext, userInfo.getUser().getPhoto(), mUserCiv);
    }
}
