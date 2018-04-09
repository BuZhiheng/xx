package com.horen.cortp.platform.contract;
import com.horen.cortp.platform.api.ApiFactory;
import com.horen.cortp.platform.bean.ApiResultPartnerList;
import com.horen.cortp.platform.bean.ApiResultPlanData;
import com.horen.cortp.platform.bean.Partner;
import com.horen.cortp.platform.bean.Plan;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.List;

import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
/**
 * Created by HOREN on 2017/11/15.
 *
 * 全部万箱盟友
 *
 */
public interface AllPartnerContract {
    class Model implements BaseModel {
        public Subscription getPartner(RequestBody body, Observer<ApiResultPartnerList> observer) {
            return ApiFactory.doHttp(ApiFactory.getSingleApi()
                    .getAllParnter(body),observer);
        }
    }
    interface View extends BaseView {
        void setPartner(List<Partner> list);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getPartner();
    }
}