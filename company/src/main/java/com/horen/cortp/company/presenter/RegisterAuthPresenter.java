package com.horen.cortp.company.presenter;
import android.widget.EditText;
import com.horen.cortp.company.api.ApiCompanyFactory;
import com.horen.cortp.company.api.ApiRequestPram;
import com.horen.cortp.company.contract.RegisterAuthContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.FormatUtil;
/**
 * Created by HOREN on 2018/2/5.
 */
public class RegisterAuthPresenter extends RegisterAuthContract.Presenter {
    private String phone = "";
    @Override
    public void sendCode(EditText etPhone) {
        final String s = etPhone.getText().toString().trim();
        if (FormatUtil.isMobileNO(s)){
            //处理网络请求，发送验证码
            mView.showLoadingSendCode();
            mRxManage.add(ApiCompanyFactory.getSingleApi().getRegisterAuthVerify(
                    ApiRequestPram.getRegisterAuthVerify(s))
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
            mView.showPhoneError("请输入正确的手机号码");
        }
    }
    @Override
    public void reSendCode() {
        //处理网络请求，发送验证码
        mRxManage.add(ApiCompanyFactory.getSingleApi().getRegisterAuthVerify(
                ApiRequestPram.getRegisterAuthVerify(phone))
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
                .verifyCode(ApiRequestPram.verifyCode(phone, s))
                .compose(RxResultHelper.<BaseResponse>handleResult())
                .subscribe(new RxSubscriber<BaseResponse>(mContext, "校验中...") {
                    @Override
                    protected void _onNext(BaseResponse o) {
                        mView.showInputPasswordView();
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
        if (s.length() < 6){
            mView.showPsdCommitError("密码长度为6-18位");
            return;
        }
//        if (FormatUtil.isNumeric(s)){//全是数字
//            mView.showPsdCommitError("密码不能全是数字");
//            return;
//        }
        mView.toUploadAuth();
        //处理网络请求，设置密码，跳转到下级页面
//        mRxManage.add(ApiCompanyFactory.getSingleApi()
//                .registerAuthVerify(ApiRequestPram.registerAuthVerify(phone, code, s))
//                .compose(RxResultHelper.<BaseResponse>handleResult())
//                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
//                    @Override
//                    protected void _onNext(BaseResponse o) {
//                        mView.toUploadAuth();
//                        mView.showInputPasswordView();
//                    }
//                    @Override
//                    protected void _onError(String message) {
//                        mView.showErrorTip(message);
//                    }
//                }));

    }
}