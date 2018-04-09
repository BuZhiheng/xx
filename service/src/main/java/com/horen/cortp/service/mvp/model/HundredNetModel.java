package com.horen.cortp.service.mvp.model;

import com.horen.cortp.service.common.ApiServiceFactory;
import com.horen.cortp.service.bean.HundredNetOrderBean;
import com.horen.cortp.service.mvp.contract.HundredNetOrderContract;
import com.jaydenxiao.common.baserx.RxResultHelper;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public class HundredNetModel implements HundredNetOrderContract.Model {
    @Override
    public Observable<HundredNetOrderBean> queryOrder(RequestBody body) {
        return ApiServiceFactory.getSingleApi().queryOrder(body)
                .compose(RxResultHelper.<HundredNetOrderBean>handleResult());
    }
}
