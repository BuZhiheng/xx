package com.horen.cortp.ui.companyclient;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.horen.cortp.R;
import com.horen.cortp.company.activity.CompanyAuthenticationActivity;
import com.horen.cortp.company.contract.RegisterAuthContract;
import com.horen.cortp.company.presenter.RegisterAuthPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.FormatUtil;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.SpKey;
import com.jaydenxiao.common.commonutils.TimeCountUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.CustomTitleBar;
import com.jaydenxiao.common.commonwidget.PinEntryEditText;
import com.horen.cortp.company.widget.RegisterNoteDialog;
import com.jaydenxiao.common.commonwidget.RippleButton;
import com.jaydenxiao.common.commonwidget.VerificationAction;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by BuZhiheng on 2018/2/4.
 */
public class RegisterAuthActivity extends BaseActivity<RegisterAuthPresenter, RegisterAuthContract.Model> implements RegisterAuthContract.View {
    @BindView(R.id.ll_register_auth_phone)
    LinearLayout llPhone;
    @BindView(R.id.ll_register_auth_code)
    LinearLayout llCode;
    @BindView(R.id.ll_register_auth_password)
    LinearLayout llPassword;
    @BindView(R.id.txt_pin_entry)
    PinEntryEditText pinEntry;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_register_password)
    EditText etPassword;
    @BindView(R.id.bt_register_next)
    RippleButton btLoginNext;
    @BindView(R.id.bt_register_commit)
    RippleButton btLoginCommit;
    @BindView(R.id.tv_register_auth_resend)
    TextView tvCodeTime;
    @BindView(R.id.tv_register_auth_msg)
    TextView tvCodeMsg;
    @BindView(R.id.tv_register_auth_error)
    TextView tvError;
    @BindView(R.id.tv_register_auth_phone_msg)
    TextView tvCodePhone;
    private CustomTitleBar customTitleBar;
    private TimeCountUtil mTimeCountUtil;

    @Override
    public int getLayoutId() {
        isIgnoreNetWorkCheck();
        return R.layout.activity_register_auth;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        customTitleBar = (CustomTitleBar) findViewById(R.id.custom_bar);
        initToolbar(customTitleBar.getToolbar(), true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            customTitleBar.setElevation(0.0f);
        }
        pinEntry.setOnVerificationCodeChangedListener(new VerificationAction.OnVerificationCodeChangedListener() {
            @Override
            public void onVerCodeChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void onInputCompleted(CharSequence s) {
                mPresenter.verifyCode(s.toString());
//                showInputPasswordView();
            }
        });
        etPhone.addTextChangedListener(new NewTextWatcher(etPhone));
        etPassword.addTextChangedListener(new NewTextWatcher(etPassword));
        mTimeCountUtil = new TimeCountUtil(tvCodeTime, 60 * 1000, 1000);
        mTimeCountUtil.setOnTimeOutListener(new TimeCountUtil.OnTimeOutListener() {
            @Override
            public void timeOut() {
                tvCodeMsg.setVisibility(View.GONE);
                tvCodeTime.setText("重新发送验证码");
            }
        });
        btLoginNext.setOnGreenBTClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendCode(etPhone);
//                showInputCodeView();
            }
        });
        btLoginCommit.setOnGreenBTClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.commit(etPassword);
            }
        });
        new RegisterNoteDialog(this).show();
    }
    @OnClick({R.id.bt_register_next, R.id.tv_register_auth_resend, R.id.bt_register_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_register_next:

                break;
            case R.id.tv_register_auth_resend:
                mPresenter.reSendCode();
                break;
            case R.id.bt_register_commit:

                break;
            default:
                break;
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, RegisterAuthActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading(String title) {
        startProgressDialog();
    }

    @Override
    public void stopLoading() {
        stopProgressDialog();
    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showShort(msg);
    }

    @Override
    public void showInputCodeView() {
        customTitleBar.setTitleText("");
        tvCodePhone.setText(FormatUtil.phoneSetMiddleEmpty(etPhone.getText().toString()));
        btLoginNext.setEnabled(false);
        etPhone.setEnabled(false);
        llPhone.setVisibility(View.GONE);
        llPhone.removeAllViews();
        llCode.setVisibility(View.VISIBLE);
        llCode.invalidate();
        mTimeCountUtil.start();
        tvCodeMsg.setVisibility(View.VISIBLE);
        pinEntry.requestFocus();
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(pinEntry,0);
        }
    }

    @Override
    public void showInputPasswordView() {
        llPassword.setVisibility(View.VISIBLE);
        llCode.setVisibility(View.GONE);
        customTitleBar.setLeftImgGone();
        pinEntry.setFocusable(false);
        pinEntry.setFocusableInTouchMode(false);
        setToolbarNoBack();
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(etPassword,0);
        }
    }

    @Override
    public void toUploadAuth() { // 保存账号密码
        SPUtils.setSharedStringData(mContext, SpKey.ACCOUNT, etPhone.getText().toString().trim());
        SPUtils.setSharedStringData(mContext, SpKey.PASSWORD, etPassword.getText().toString().trim());
        CompanyAuthenticationActivity.startAction(mContext,true);
        finish();
    }

    @Override
    public void showPhoneError(String message) {
        btLoginNext.showRedButton(message);
    }

    @Override
    public void showPsdCommitError(String error) {
        btLoginCommit.showRedButton();
        btLoginCommit.setRedText(error);
    }

    @Override
    public void verifyCodeError(String message) {
        tvError.setText(message);
        tvError.setVisibility(View.VISIBLE);
        tvError.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (tvError != null){
                    tvError.setVisibility(View.GONE);
                }
            }
        },2000);
    }
    @Override
    public void showLoadingSendCode() {
        btLoginNext.showLoadingButton();
    }
    @Override
    public void showLoadingCommit() {
        btLoginCommit.showLoadingButton();
    }
    class NewTextWatcher implements TextWatcher {
        private EditText editText;

        public NewTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            /**
             * 简单逻辑先放此处
             * presenter先只处理网络请求相关
             * */
            if (editText == etPhone) {
                String str = etPhone.getText().toString().trim();
                if (str.length() > 0) {
                    btLoginNext.showGreenButton();
                } else {
                    btLoginNext.showGrayButton();
                }
            }
            if (editText == etPassword) {
                String str = etPassword.getText().toString().trim();
                if (str.length() > 5) {
                    btLoginCommit.showGreenButton();
                } else {
                    btLoginCommit.showGrayButton();
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimeCountUtil.cancel();
    }
}