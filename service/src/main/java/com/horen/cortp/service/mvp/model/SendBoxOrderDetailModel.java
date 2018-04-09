package com.horen.cortp.service.mvp.model;

import com.horen.cortp.service.common.ApiServiceFactory;
import com.horen.cortp.service.mvp.contract.SendBoxOrderDetailContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;
import com.jaydenxiao.common.baserx.RxResultHelper;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public class SendBoxOrderDetailModel implements SendBoxOrderDetailContract.Model {

    @Override
    public Observable<SendBoxOrderDetailBean> queryOrderDetail(RequestBody body) {
        return ApiServiceFactory.getSingleApi().queryOrderDetail(body)
                .compose(RxResultHelper.<SendBoxOrderDetailBean>handleResult());
    }

    @Override
    public Observable<BaseResponse> createTransOrder(RequestBody body) {
        return ApiServiceFactory.getSingleApi().createTransOrde(body)
                .compose(RxResultHelper.<BaseResponse>handleResult());
    }
}
