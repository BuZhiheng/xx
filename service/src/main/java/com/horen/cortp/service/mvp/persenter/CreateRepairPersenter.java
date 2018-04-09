package com.horen.cortp.service.mvp.persenter;

import com.horen.cortp.service.bean.CreateRepairBean;
import com.horen.cortp.service.bean.RepairDetailBean;
import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.horen.cortp.service.mvp.contract.CreateRepairContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.ArrayList;

import okhttp3.RequestBody;

/**
 * Created by HOREN on 2017/12/14.
 */

public class CreateRepairPersenter extends CreateRepairContract.Presenter {
    @Override
    public void uploadImgs(String plan_id, ArrayList<String> photos) {
        mRxManage.add(mModel.uploadImgs(mContext, plan_id, photos)
                .subscribe(new RxSubscriber<UploadSinglePhotoBean>(mContext, true) {
                    @Override
                    protected void _onNext(UploadSinglePhotoBean uploadSinglePhotoBean) {
                        mView.uploadPhotoSuccess(uploadSinglePhotoBean.getFilepath());
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void createRepairOrder(RequestBody body) {
        mRxManage.add(mModel.createRepairOrder(body)
                .subscribe(new RxSubscriber<CreateRepairBean>(mContext, true) {
                    @Override
                    protected void _onNext(CreateRepairBean createRepairBean) {
                        mView.createRepairOrderSuccess(createRepairBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void saveRepairOrder(RequestBody body) {
        mRxManage.add(mModel.saveRepairOrder(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.saveRepairOrderSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void submitRepairOrder(RequestBody body) {
        mRxManage.add(mModel.submitRepairOrder(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.submitRepairOrderSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void queryRepairOrderDetailList(RequestBody body) {
        mRxManage.add(mModel.queryRepairOrderDetailList(body)
                .subscribe(new RxSubscriber<RepairDetailBean>(mContext, true) {
                    @Override
                    protected void _onNext(RepairDetailBean repairDetailBean) {
                        mView.queryRepairOrderDetailSuccess(repairDetailBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }
}
