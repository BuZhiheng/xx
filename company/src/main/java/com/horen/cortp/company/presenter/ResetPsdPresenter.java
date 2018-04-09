package com.horen.cortp.company.presenter;
import android.widget.EditText;
import com.horen.cortp.company.api.ApiCompanyFactory;
import com.horen.cortp.company.api.ApiRequestPram;
import com.horen.cortp.company.contract.ResetPsdContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.FormatUtil;
/**
 * Created by HOREN on 2018/2/5.
 */
public class ResetPsdPresenter extends ResetPsdContract.Presenter {
    private String phone = "";
    private String code = "";
    @Override
    public void sendCode(EditText etPhone) {
        final String s = etPhone.getText().toString().trim();
        if (FormatUtil.isMobileNO(s)){
            //处理网络请求，发送验证码
            mView.showLoadingSendCode();
            mRxManage.add(ApiCompanyFactory.getSingleApi().getForGotPwdVerify(
                    ApiRequestPram.getForGotPwdVerify(s, "+86"))
                    .compose(RxResultHelper.handleResult())
                    .subscribe(new RxSubscriber<BaseResponse>(mContext, false) {
                        @Override
                        protected void _onNext(BaseResponse o) {
                            mView.showInputCodeView();
                            phone = s;
                        }
                        @Override
                        protected void _onError(String message) {
                            mView.showPhoneError(message);
                        }
                    }));
        } else {
            mView.showPhoneError("您所输入的号码格式错误");
        }
    }
    @Override
    public void reSendCode() {
        //处理网络请求，重新验证码
        mRxManage.add(ApiCompanyFactory.getSingleApi().getForGotPwdVerify(
                ApiRequestPram.getForGotPwdVerify(phone, "+86"))
                .compose(RxResultHelper.handleResult())
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse o) {
                        mView.showInputCodeView();
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showErrorTip(message);
                    }
                }));
    }
    @Override
    public void verifyCode(final String s) {
        //验证验证码
        mRxManage.add(ApiCompanyFactory.getSingleApi()
                .verifyCodeResetPsd(ApiRequestPram.verifyCodeResetPsd(phone, s))
                .compose(RxResultHelper.<BaseResponse>handleResult())
                .subscribe(new RxSubscriber<BaseResponse>(mContext, "校验中...") {
                    @Override
                    protected void _onNext(BaseResponse o) {
                        mView.showInputPasswordView();
                        code = s;
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.verifyCodeError("验证码输入错误");
                    }
                }));
    }
    @Override
    public void commit(EditText etPassword) {
        String s = etPassword.getText().toString().trim();
        //处理网络请求，设置密码，跳转到下级页面
        mView.showLoadingCommit();
        mRxManage.add(ApiCompanyFactory.getSingleApi()
                .activationForGotPwd(ApiRequestPram.activationForGotPwd(phone, code, s, "+86"))
                .compose(RxResultHelper.<BaseResponse>handleResult())
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse o) {
                        mView.showErrorTip("密码设置成功");
                        mView.toLogin();
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showPsdCommitError(message);
                    }
                }));
    }
}