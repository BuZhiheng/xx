package com.horen.cortp.service.mvp.contract;

import com.horen.cortp.service.bean.HundredNetOrderBean;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public interface HundredNetOrderContract {
    interface Model extends BaseModel {
        Observable<HundredNetOrderBean> queryOrder(RequestBody body);
    }

    interface View extends BaseView {
        void getOrderSuccess(HundredNetOrderBean orderBean);

        void getOrderLoadMoreSuccess(HundredNetOrderBean orderBean);

        void getOrderError(String msg);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void queryOrder(RequestBody body, boolean showDialog);

        public abstract void queryOrderLoadMore(RequestBody body);
    }
}
