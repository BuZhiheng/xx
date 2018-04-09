package com.horen.cortp.service.mvp.contract;

import android.content.Context;

import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public interface SendBoxDeliveringContract {
    interface Model extends BaseModel {
        // 详情
        Observable<SendBoxOrderDetailBean> queryDeliveringOrderDetail(RequestBody body);

        // 异常拒收
        Observable<BaseResponse> abnormalReject(RequestBody body);

        // 上传图片
        Observable<UploadSinglePhotoBean> uploadImgs(Context mContext, String plan_id, ArrayList<String> photos);

        // 提交
        Observable<BaseResponse> completeOrder(RequestBody body);
    }

    interface View extends BaseView {
        void getOrderDetailSuccess(SendBoxOrderDetailBean orderBean);

        void getAbnormalRejectSuccess(BaseResponse orderBean);

        void uploadPhotoSuccess(List<String> picUrls);

        void completeOrderSuccess(BaseResponse orderBean);

        void getError(String msg);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void queryDeliveringOrderDetail(RequestBody body);

        public abstract void abnormalReject(RequestBody body);

        public abstract void uploadPhoto(String plan_id, ArrayList<String> photos);

        public abstract void completeOrder(RequestBody body);
    }
}
