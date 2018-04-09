package com.horen.cortp.company.contract;
import com.horen.cortp.company.api.ApiCompanyFactory;
import com.horen.cortp.company.bean.CreditInfo;
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
public interface CreditLimitContract {
    class Model implements BaseModel {
        public Observable<CreditInfo> getCreditData(RequestBody body) {
            return ApiCompanyFactory.getSingleApi().getUserCreditInfo(body)
                    .compose(RxResultHelper.<CreditInfo>handleResult());
        }
        public Observable<BaseResponse> reapplyCrsadeditData(RequestBody body) {
            return ApiCompanyFactory.getSingleApi().reapplyCreditData(body)
                    .compose(RxResultHelper.<BaseResponse>handleResult());
        }
    }
    interface View extends BaseView {
        void setData(float per, CreditInfo.Credit credit);
        void setApplySuccess();
        void setUnReviewed(String creditMessage);
        void setUnderReviewed(String creditMessage);
    }
    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void initCreditData();
        public abstract void applyCredit();
    }
}