package com.horen.cortp.platform.contract;
import com.horen.cortp.platform.api.ApiFactory;
import com.horen.cortp.platform.bean.ApiResultBoxExperience;
import com.horen.cortp.platform.bean.BoxExperience;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import java.util.List;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscription;
/**
 * Created by HOREN on 2017/11/15.
 */
public interface UseExperienceContract {
    class Model implements BaseModel {
        public Subscription getData(RequestBody body, Observer<ApiResultBoxExperience> observer) {
            return ApiFactory.doHttp(ApiFactory.getSingleApi()
                    .getUseExperience(body),observer);
        }
    }
    interface View extends BaseView {
        void setData(List<BoxExperience> datas);
        void setMine();
        void setMore();
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getDataMine();
        public abstract void getDataMore();
        public abstract void resume();
    }
}