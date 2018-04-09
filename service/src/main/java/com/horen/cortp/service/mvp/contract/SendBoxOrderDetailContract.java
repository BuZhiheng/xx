package com.horen.cortp.service.mvp.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public interface SendBoxOrderDetailContract {
    interface Model extends BaseModel {
        Observable<SendBoxOrderDetailBean> queryOrderDetail(RequestBody body);
        Observable<BaseResponse> createTransOrder(RequestBody body);
    }

    interface View extends BaseView {
        void getOrderDetailSuccess(SendBoxOrderDetailBean orderBean);

        void getOrderDetailError(String msg);

        void createTransOrderSuccess(BaseResponse respose);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void queryOrderDetail(RequestBody body);
        public abstract void createTransOrder(RequestBody body);
    }
}
