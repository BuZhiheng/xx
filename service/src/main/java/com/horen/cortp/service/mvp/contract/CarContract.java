package com.horen.cortp.service.mvp.contract;

import com.horen.cortp.service.bean.CarNumberBean;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.jaydenxiao.common.basebean.BaseResponse;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public interface CarContract {
    interface Model extends BaseModel {
        Observable<CarNumberBean> queryVehicleList(RequestBody body);

        Observable<CarNumberBean> LoadMore(RequestBody body);

        Observable<BaseResponse> insertVehicleList(RequestBody body);

        Observable<BaseResponse> deleteVehicle(RequestBody body);


    }

    interface View extends BaseView {
        void queryVehicleListSuccess(CarNumberBean carNumberBean);

        void insertVehicleListSuccess(BaseResponse baseResponse);

        void loadMoreSuccess(BaseResponse baseResponse);

        void deleteVehicleSuccess(BaseResponse baseResponse);

        void onError(String msg);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void queryVehicleList(RequestBody body,boolean showDialog);

        public abstract void insertVehicleList(RequestBody body);

        public abstract void deleteVehicle(RequestBody body);

        public abstract void LoadMore(RequestBody body);
    }
}
