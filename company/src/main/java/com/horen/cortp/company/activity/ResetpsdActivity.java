package com.horen.cortp.company.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.horen.cortp.company.R;
import com.horen.cortp.company.contract.ResetPsdContract;
import com.horen.cortp.company.presenter.ResetPsdPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.baseapp.AppManager;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.commonutils.FormatUtil;
import com.jaydenxiao.common.commonutils.SpKey;
import com.jaydenxiao.common.commonutils.TimeCountUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.CustomTitleBar;
import com.jaydenxiao.common.commonwidget.PinEntryEditText;
import com.jaydenxiao.common.commonwidget.RippleButton;
import com.jaydenxiao.common.commonwidget.VerificationAction;
/**
 * Created by BuZhiheng on 2018/2/4.
 */
public class ResetpsdActivity extends BaseActivity<ResetPsdPresenter,ResetPsdContract.Model> implements ResetPsdContract.View, View.OnClickListener {
    private LinearLayout llPhone;
    private LinearLayout llCode;
    private LinearLayout llPassword;
    private PinEntryEditText pinEntry;
    private EditText etPhone;
    private EditText etPassword;
    private RippleButton btLoginNext;
    private RippleButton btLoginCommit;
    private TextView tvCodeTime;
    private TextView tvCodeMsg;
    private TextView tvError;
    private TextView tvPsdNumber;
    private TextView tvCodeNotice;
    private TextView tvPsdNotice;
    private TextView tvTitle;

    private TimeCountUtil mTimeCountUtil;
    private String title;
    @Override
    public int getLayoutId() {
        isIgnoreNetWorkCheck();
        return R.layout.activity_resetpsd;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        title = getIntent().getStringExtra("title");
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.custom_bar);
        customTitleBar.setBackGroundColor(R.color.bg);
        customTitleBar.setTitleText(title);
        initToolbar(customTitleBar.getToolbar(), R.color.bg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            customTitleBar.setElevation(0.0f);
        }
        llPhone = (LinearLayout) findViewById(R.id.ll_resetpsd_phone);
        llCode = (LinearLayout) findViewById(R.id.ll_resetpsd_code);
        llPassword = (LinearLayout) findViewById(R.id.ll_resetpsd_password);
        pinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPassword = (EditText) findViewById(R.id.et_resetpsd_password);
        btLoginNext = (RippleButton) findViewById(R.id.bt_resetpsd_next);
        btLoginCommit = (RippleButton) findViewById(R.id.bt_resetpsd_commit);
        tvCodeTime = (TextView) findViewById(R.id.tv_resetpsd_resend);
        tvCodeMsg = (TextView) findViewById(R.id.tv_resetpsd_msg);
        tvError = (TextView) findViewById(R.id.tv_resetpsd_error);
        tvPsdNumber = (TextView) findViewById(R.id.tv_resetpsd_phone_number);
        tvCodeNotice = (TextView) findViewById(R.id.tv_resetpsd_phone_code);
        tvPsdNotice = (TextView) findViewById(R.id.tv_resetpsd_phone_psd);
        tvTitle = (TextView) findViewById(R.id.tv_resetpsd_title);
        if ("修改密码".equals(title)){
            tvTitle.setText("请填写您要修改密码的账号");
        } else {
            tvTitle.setText("请填写您要找回密码的账号");
        }
        findViewById(R.id.tv_resetpsd_resend).setOnClickListener(this);
        pinEntry.setOnVerificationCodeChangedListener (new VerificationAction.OnVerificationCodeChangedListener () {
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
    }
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_resetpsd_resend) {
            mPresenter.reSendCode();
        }
    }
    public static void startAction(Context context,String title) {
        Intent intent = new Intent();
        intent.putExtra("title",title);
        intent.setClass(context, ResetpsdActivity.class);
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
        ToastUitl.showLong(msg);
    }
    @Override
    public void showInputCodeView() {
        tvCodeNotice.setText(FormatUtil.phoneSetMiddleGone(etPhone.getText().toString()));
        tvPsdNumber.setText(FormatUtil.phoneSetMiddleGone(etPhone.getText().toString()));
        tvPsdNotice.setText("进行"+title+"操作");
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
    public void showPhoneError(String message) {
        btLoginNext.showRedButton();
        btLoginNext.setRedText(message);
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

    @Override
    public void toLogin() {
        //清除TOKEN
        AppConfig.getInstance().remove(SpKey.LOGIN_TOKEN);
        //跳转Login
        ARouter.getInstance().build(CommonRoutePath.LOGIN_COMMON_ACTIVITY).navigation();
        //finish
        AppManager.getAppManager().finishAllActivity();
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