package com.horen.cortp.company.contract;
import com.horen.cortp.company.api.ApiCompanyFactory;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;
import okhttp3.RequestBody;
import rx.Observable;
/**
 * Created by Chen on 2017/4/17.
 */
public interface ScanLoginContract {
    class Model implements BaseModel {
        public Observable<BaseResponse> scanloginApplyInfo(RequestBody body) {
            return ApiCompanyFactory.getSingleApi().scanloginApplyInfo(body)
                    .compose(RxResultHelper.<BaseResponse>handleResult());
        }
        public Observable<BaseResponse> scanloginApplyLogin(RequestBody body) {
            return ApiCompanyFactory.getSingleApi().scanloginApplyLogin(body)
                    .compose(RxResultHelper.<BaseResponse>handleResult());
        }
    }
    interface View extends BaseView {
        void success();
        void codeIsValid();
        void codeIsNoValid(String msg);
    }
    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void login();
        public abstract void checkCode(String code);
    }
}