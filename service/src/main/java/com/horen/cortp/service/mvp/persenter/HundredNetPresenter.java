package com.horen.cortp.service.mvp.persenter;

import com.horen.cortp.service.bean.HundredNetOrderBean;
import com.horen.cortp.service.mvp.contract.HundredNetOrderContract;
import com.jaydenxiao.common.baserx.RxSubscriber;

import okhttp3.RequestBody;

/**
 * Created by HOREN on 2017/10/31.
 */

public class HundredNetPresenter extends HundredNetOrderContract.Presenter {
    /**
     * 加载第一页数据
     * @param body
     * @param showDialog
     */
    @Override
    public void queryOrder(RequestBody body,boolean showDialog) {
        mRxManage.add(mModel.queryOrder(body)
                .subscribe(new RxSubscriber<HundredNetOrderBean>(mContext, showDialog) {
                    @Override
                    protected void _onNext(HundredNetOrderBean orderBean) {
                        mView.getOrderSuccess(orderBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getOrderError(message);
                    }
                })
        );
    }

    /**
     * 加载下一页数据
     * @param body
     */
    @Override
    public void queryOrderLoadMore(RequestBody body) {
        mRxManage.add(mModel.queryOrder(body)
                .subscribe(new RxSubscriber<HundredNetOrderBean>(mContext, false) {
                    @Override
                    protected void _onNext(HundredNetOrderBean orderBean) {
                        mView.getOrderLoadMoreSuccess(orderBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getOrderError(message);
                    }
                })
        );
    }
}
