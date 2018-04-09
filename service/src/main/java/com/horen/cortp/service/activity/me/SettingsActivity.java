package com.horen.cortp.service.activity.me;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.allen.library.CommonTextView;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.baseapp.AppManager;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.basebean.LoginInfo;
import com.jaydenxiao.common.commonutils.ACache;
import com.jaydenxiao.common.commonutils.SpKey;

/**
 * Created by HOREN on 2017/11/30.
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener {
    /**  */
    private TextView mLeftTv;
    /**  */
    private TextView mToolBarTitleTv;
    private ImageView mRightIv;
    private TextView mRightTv;
    private Toolbar mToolBar;
    private CommonTextView mTvChangePw;
    private CommonTextView mTvVersion;
    private TextView tvLoginOut;

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mLeftTv = (TextView) findViewById(R.id.left_tv);
        mToolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        mRightIv = (ImageView) findViewById(R.id.right_iv);
        mRightTv = (TextView) findViewById(R.id.right_tv);
        tvLoginOut = (TextView) findViewById(R.id.tv_login_out);
        tvLoginOut.setOnClickListener(this);
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mTvChangePw = (CommonTextView) findViewById(R.id.tv_change_pw);
        mTvChangePw.setOnClickListener(this);
        mTvVersion = (CommonTextView) findViewById(R.id.tv_version);
        mTvVersion.setOnClickListener(this);

        setSimpleToolbar(mToolBar, mToolBarTitleTv, getString(R.string.settings));

        mTvVersion.setRightTextString("V" + GetVersion(mContext));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_change_pw) {
            startActivity(ChangePwActivity.class);
        } else if (v.getId() == R.id.tv_version) {
        } else if (v.getId() == R.id.tv_login_out) {
            finishApp();
        }
    }

    // 取得版本号
    public static String GetVersion(Context context) {
        try {
            PackageInfo manager = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return manager.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }

    /**
     * 退出登录
     */
    public void finishApp() {
        LoginInfo loginInfo = (LoginInfo) ACache.get(mContext).getAsObject(SpKey.LOGIN_INFO);
        if (loginInfo != null) {
            //取消自动登录
            loginInfo.setAuto_login(false);
            loginInfo.setUser_password(""); // 去掉密码
            ACache.get(mContext).put(SpKey.LOGIN_INFO, loginInfo);
        }
        //清除TOKEN
        AppConfig.getInstance().remove(SpKey.LOGIN_TOKEN);
        //Login
        ARouter.getInstance().build(CommonRoutePath.LOGIN_COMMON_ACTIVITY).navigation();
        //finish
        AppManager.getAppManager().finishAllActivity();
    }

}
