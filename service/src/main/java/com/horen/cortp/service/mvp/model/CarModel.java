package com.horen.cortp.service.mvp.model;

import com.horen.cortp.service.bean.CarNumberBean;
import com.horen.cortp.service.common.ApiServiceFactory;
import com.horen.cortp.service.mvp.contract.CarContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/12/1.
 */

public class CarModel implements CarContract.Model {
    @Override
    public Observable<CarNumberBean> queryVehicleList(RequestBody body) {
        return ApiServiceFactory.getSingleApi().queryVehicleList(body)
                .compose(RxResultHelper.<CarNumberBean>handleResult());
    }

    @Override
    public Observable<CarNumberBean> LoadMore(RequestBody body) {
        return ApiServiceFactory.getSingleApi().queryVehicleList(body)
                .compose(RxResultHelper.<CarNumberBean>handleResult());
    }

    @Override
    public Observable<BaseResponse> insertVehicleList(RequestBody body) {
        return ApiServiceFactory.getSingleApi().insertVehicleList(body)
                .compose(RxResultHelper.<BaseResponse>handleResult());
    }

    @Override
    public Observable<BaseResponse> deleteVehicle(RequestBody body) {
        return ApiServiceFactory.getSingleApi().deleteVehicle(body)
                .compose(RxResultHelper.<BaseResponse>handleResult());
    }
}
