package com.horen.cortp.company.model;

import com.horen.cortp.company.api.ApiCompanyFactory;
import com.horen.cortp.company.bean.CancelQty;
import com.horen.cortp.company.bean.OrderList;
import com.horen.cortp.company.contract.OrderListContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Chen on 2017/4/27.
 */

public class OrderListModel implements OrderListContract.Model {

    @Override
    public Observable<OrderList> getOrderList(RequestBody body) {
        return ApiCompanyFactory.getSingleApi().getOrderList(body)
                .compose(RxResultHelper.<OrderList>handleResult());
    }

    @Override
    public Observable<CancelQty> getCancelOrderQty(RequestBody body) {
        return ApiCompanyFactory.getSingleApi().getCancelOrderQty(body)
                .compose(RxResultHelper.<CancelQty>handleResult());
    }

    @Override
    public Observable<BaseResponse> cancelOrder(RequestBody body) {
        return ApiCompanyFactory.getSingleApi().cancelOrder(body)
                .compose(RxResultHelper.handleResult());
    }
}
