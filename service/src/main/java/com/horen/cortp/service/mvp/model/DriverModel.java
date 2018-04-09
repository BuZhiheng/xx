package com.horen.cortp.service.mvp.model;

import com.horen.cortp.service.mvp.contract.DriverContract;
import com.horen.cortp.service.bean.DeliverPeopleBean;
import com.horen.cortp.service.common.ApiServiceFactory;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/12/1.
 */

public class DriverModel implements DriverContract.Model {
    @Override
    public Observable<DeliverPeopleBean> queryDeliverList(RequestBody body) {
        return ApiServiceFactory.getSingleApi().queryDeliverList(body)
                .compose(RxResultHelper.<DeliverPeopleBean>handleResult());
    }

    @Override
    public Observable<DeliverPeopleBean> loadMore(RequestBody body) {
        return ApiServiceFactory.getSingleApi().queryDeliverList(body)
                .compose(RxResultHelper.<DeliverPeopleBean>handleResult());
    }

    @Override
    public Observable<BaseResponse> insertDeliverList(RequestBody body) {
        return ApiServiceFactory.getSingleApi().insertDeliverList(body)
                .compose(RxResultHelper.<BaseResponse>handleResult());
    }

    @Override
    public Observable<BaseResponse> deleteDeliver(RequestBody body) {
        return ApiServiceFactory.getSingleApi().deleteDeliver(body)
                .compose(RxResultHelper.<BaseResponse>handleResult());
    }
}
