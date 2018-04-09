package com.horen.cortp.platform.contract;
import android.widget.EditText;

import com.horen.cortp.platform.api.ApiFactory;
import com.horen.cortp.platform.bean.ApiResultPlanData;
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
 * 全部热门万箱方案Contract
 *
 */
public interface AllHotPlanContract {
    class Model implements BaseModel {
        public Subscription getPlanData(RequestBody body, Observer<ApiResultPlanData> observer) {
            return ApiFactory.doHttp(ApiFactory.getSingleApi()
                    .getPlanData(body),observer);
        }
        public Subscription getCollectionPlanData(RequestBody body, Observer<ApiResultPlanData> observer) {
            return ApiFactory.doHttp(ApiFactory.getSingleApi()
                    .getCollectionPlanData(body),observer);
        }
    }
    interface View extends BaseView {
        void setPlanData(List<Plan> list);
        void addPlanData(List<Plan> solutions);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getCollectionPlanDataRefresh();
        public abstract void getCollectionPlanDataLoadmore();
        public abstract void getPlanDataRefresh();
        public abstract void getPlanDataLoadmore();
        public abstract void getPlanDataRefresh(EditText editText);
        public abstract void getPlanDataLoadmore(EditText editText);
    }
}