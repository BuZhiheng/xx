package com.horen.cortp.service.mvp.persenter;

import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.horen.cortp.service.mvp.contract.SendBoxDeliveringContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.ArrayList;

import okhttp3.RequestBody;

/**
 * @author HOREN
 * @date 2017/10/31
 */

public class SendBoxDeliveringPresenter extends SendBoxDeliveringContract.Presenter {

    /**
     * 订单详情
     *
     * @param body
     */
    @Override
    public void queryDeliveringOrderDetail(RequestBody body) {
        mRxManage.add(mModel.queryDeliveringOrderDetail(body)
                .subscribe(new RxSubscriber<SendBoxOrderDetailBean>(mContext, true) {
                    @Override
                    protected void _onNext(SendBoxOrderDetailBean orderBean) {
                        mView.getOrderDetailSuccess(orderBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getError(message);
                    }
                })
        );
    }

    /**
     * 异常拒收
     *
     * @param body
     */
    @Override
    public void abnormalReject(RequestBody body) {
        mRxManage.add(mModel.abnormalReject(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, false) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.getAbnormalRejectSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getError(message);
                    }
                })
        );
    }

    /**
     * 上传签收单
     */
    @Override
    public void uploadPhoto(String plan_id, ArrayList<String> photos) {
        mRxManage.add(mModel.uploadImgs(mContext, plan_id, photos)
                .subscribe(new RxSubscriber<UploadSinglePhotoBean>(mContext, true) {
                    @Override
                    protected void _onNext(UploadSinglePhotoBean uploadSinglePhotoBean) {
                        mView.uploadPhotoSuccess(uploadSinglePhotoBean.getFilepath());
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getError(message);
                    }
                })
        );
    }

    /**
     * 签收已完成
     *
     * @param body
     */
    @Override
    public void completeOrder(RequestBody body) {
        mRxManage.add(mModel.completeOrder(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.completeOrderSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.getError(message);
                    }
                })
        );
    }
}
