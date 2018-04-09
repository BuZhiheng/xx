package com.horen.cortp.company.presenter;
import android.text.TextUtils;
import com.horen.cortp.company.api.ApiRequestPram;
import com.horen.cortp.company.bean.ScanLogin;
import com.horen.cortp.company.contract.ScanLoginContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.GsonUtil;
/**
 * Created by HOREN on 2018/2/24.
 */
public class ScanLoginPresenter extends ScanLoginContract.Presenter {
    private ScanLogin login;
    @Override
    public void login() {
        if (login == null || login.codeUuid == null){
            return;
        }
        mRxManage.add(mModel.scanloginApplyLogin(ApiRequestPram.checkLoginInfo(login.codeUuid)).subscribe(new RxSubscriber<BaseResponse>(mContext, false) {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                mView.success();
            }
            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void checkCode(String qrCode) {
        login = GsonUtil.getGson().fromJson(qrCode,ScanLogin.class);
        mRxManage.add(mModel.scanloginApplyInfo(ApiRequestPram.checkLoginInfo(login.codeUuid)).subscribe(new RxSubscriber<BaseResponse>(mContext, false) {
            @Override
            protected void _onNext(BaseResponse baseResponse) {
                mView.codeIsValid();
            }
            @Override
            protected void _onError(String message) {
                mView.codeIsNoValid(message);
            }
        }));
    }
}