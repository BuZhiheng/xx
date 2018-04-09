package com.horen.cortp.platform.contract;
import com.horen.cortp.platform.api.ApiFactory;
import com.horen.cortp.platform.bean.ApiResultFireflyList;
import com.horen.cortp.platform.bean.Fireflys;
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
public interface FireflysContract {
    class Model implements BaseModel {
        public Subscription getData(RequestBody body, Observer<ApiResultFireflyList> observer) {
            return ApiFactory.doHttp(ApiFactory.getSingleApi()
                    .getFireflys(body),observer);
        }
    }
    interface View extends BaseView {
        void setData(List<Fireflys> datas);
        void setMine();
        void setMore();
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getDataMine();
        public abstract void getDataMore();
        public abstract void onResume();
    }
}