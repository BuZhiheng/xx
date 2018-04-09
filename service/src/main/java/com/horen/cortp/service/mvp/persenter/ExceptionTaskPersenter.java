package com.horen.cortp.service.mvp.persenter;

import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.horen.cortp.service.mvp.contract.ExceptionTaskContract;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.ArrayList;

/**
 * Created by HOREN on 2017/12/14.
 */

public class ExceptionTaskPersenter extends ExceptionTaskContract.Presenter {
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

}
