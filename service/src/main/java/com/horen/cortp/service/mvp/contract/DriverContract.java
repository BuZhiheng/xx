package com.horen.cortp.service.mvp.contract;

import com.horen.cortp.service.bean.DeliverPeopleBean;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.jaydenxiao.common.basebean.BaseResponse;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public interface DriverContract {
    interface Model extends BaseModel {
        Observable<DeliverPeopleBean> queryDeliverList(RequestBody body);

        Observable<DeliverPeopleBean> loadMore(RequestBody body);

        Observable<BaseResponse> insertDeliverList(RequestBody body);

        Observable<BaseResponse> deleteDeliver(RequestBody body);
    }

    interface View extends BaseView {
        void queryDeliverSuccess(DeliverPeopleBean deliverPeopleBean);

        void loadMoreSuccess(DeliverPeopleBean deliverPeopleBean);

        void insertDeliverListSuccess(BaseResponse baseResponse);

        void deleteDeliverSuccess(BaseResponse baseResponse);

        void onError(String msg);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void queryDeliverList(RequestBody body,boolean showDialog);

        public abstract void loadMore(RequestBody body);

        public abstract void insertDeliverList(RequestBody body);

        public abstract void deleteDeliver(RequestBody body);
    }
}
