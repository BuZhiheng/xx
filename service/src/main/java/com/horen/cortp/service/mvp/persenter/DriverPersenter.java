package com.horen.cortp.service.mvp.persenter;

import com.horen.cortp.service.bean.DeliverPeopleBean;
import com.horen.cortp.service.mvp.contract.DriverContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxSubscriber;

import okhttp3.RequestBody;

/**
 * Created by HOREN on 2017/12/1.
 */

public class DriverPersenter extends DriverContract.Presenter {
    @Override
    public void queryDeliverList(RequestBody body,boolean showDialog) {
        mRxManage.add(mModel.queryDeliverList(body)
                .subscribe(new RxSubscriber<DeliverPeopleBean>(mContext, showDialog) {
                    @Override
                    protected void _onNext(DeliverPeopleBean deliverPeopleBean) {
                        mView.queryDeliverSuccess(deliverPeopleBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void loadMore(RequestBody body) {
        mRxManage.add(mModel.queryDeliverList(body)
                .subscribe(new RxSubscriber<DeliverPeopleBean>(mContext, true) {
                    @Override
                    protected void _onNext(DeliverPeopleBean deliverPeopleBean) {
                        mView.loadMoreSuccess(deliverPeopleBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void insertDeliverList(RequestBody body) {

        mRxManage.add(mModel.insertDeliverList(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.insertDeliverListSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void deleteDeliver(RequestBody body) {
        mRxManage.add(mModel.deleteDeliver(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.deleteDeliverSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }
}
