package com.horen.cortp.service.mvp.persenter;

import com.horen.cortp.service.mvp.contract.SendBoxOrderDetailContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;
import com.jaydenxiao.common.baserx.RxSubscriber;

import okhttp3.RequestBody;

/**
 * Created by HOREN on 2017/10/31.
 */

public class SendBoxOrderDetailPresenter extends SendBoxOrderDetailContract.Presenter {

    @Override
    public void queryOrderDetail(RequestBody body) {
        mRxManage.add(mModel.queryOrderDetail(body)
                .subscribe(new RxSubscriber<SendBoxOrderDetailBean>(mContext, true) {
                    @Override
                    protected void _onNext(SendBoxOrderDetailBean orderBean) {
                        mView.getOrderDetailSuccess(orderBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getOrderDetailError(message);
                    }
                })
        );
    }

    @Override
    public void createTransOrder(RequestBody body) {
        mRxManage.add(mModel.createTransOrder(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.createTransOrderSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getOrderDetailError(message);
                    }
                })
        );
    }
}
