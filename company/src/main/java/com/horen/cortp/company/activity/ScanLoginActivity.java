package com.horen.cortp.company.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import com.horen.cortp.company.R;
import com.horen.cortp.company.contract.ScanLoginContract;
import com.horen.cortp.company.presenter.ScanLoginPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.CustomTitleBar;
import com.jaydenxiao.common.commonwidget.RippleButton;
/**
 * Created by HOREN on 2018/2/24.
 */
public class ScanLoginActivity extends BaseActivity<ScanLoginPresenter,ScanLoginContract.Model> implements ScanLoginContract.View{
    private RippleButton btnCommit;
    private static String code;
    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_login;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.custom_bar);
        initToolbar(customTitleBar.getToolbar(), true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            customTitleBar.setElevation(0.0f);
        }
        btnCommit = (RippleButton) findViewById(R.id.bt_login_commit);
        mPresenter.checkCode(code);
    }
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.bt_login_cancel){
            finish();
        }
    }
    public static void startAction(Context context, String result) {
        code = result;
        Intent intent = new Intent();
        intent.setClass(context, ScanLoginActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void showLoading(String title) {
    }
    @Override
    public void stopLoading() {
    }
    @Override
    public void showErrorTip(String msg) {
        btnCommit.showRedButton(msg);
    }
    @Override
    public void success() {
        ToastUitl.showShort("登录成功");
        finish();
    }
    @Override
    public void codeIsValid() {
        btnCommit.showGreenButton();
        btnCommit.setOnGreenBTClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCommit.showLoadingButton();
                mPresenter.login();
            }
        });
    }

    @Override
    public void codeIsNoValid(String msg) {
        btnCommit.setGrayText(msg);
    }
}