package com.horen.cortp.platform.contract;
import com.horen.cortp.platform.api.ApiFactory;
import com.horen.cortp.platform.bean.ApiResultPlanData;
import com.horen.cortp.platform.bean.ApiResultPlanType;
import com.horen.cortp.platform.bean.Plan;
import com.horen.cortp.platform.bean.PlanType;
import com.horen.cortp.platform.bean.PlanTypeLookup;
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
 * 全部万箱方案Contract
 *
 */
public interface AllPlanContract {
    class Model implements BaseModel {
        public Subscription getPlanList(RequestBody body, Observer<ApiResultPlanType> observer) {
            return ApiFactory.doHttp(ApiFactory.getSingleApi()
                    .getPlanTypeList(body),observer);
        }
        public Subscription getPlanData(RequestBody body, Observer<ApiResultPlanData> observer) {
            return ApiFactory.doHttp(ApiFactory.getSingleApi()
                    .getPlanData(body),observer);
        }
    }
    interface View extends BaseView {
        void setListData(List<PlanType> list);
        void setPlanData(List<Plan> list);
        void setPlanBanner(PlanType type);
        void setTypeTag(List<PlanTypeLookup> listTag);
        void setTypeSize(List<PlanTypeLookup> listSize);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getPlanList();
        public abstract void getPlanData(PlanType type,PlanTypeLookup typeTag,PlanTypeLookup typeSize);
    }
}