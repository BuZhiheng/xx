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

public interface CollectBoxCompleteContract {
    interface Model extends BaseModel {
        Observable<SendBoxOrderDetailBean> queryCompleteOrderDetail(RequestBody body);

        Observable<BaseResponse> completeOrder(RequestBody body);

        Observable<UploadSinglePhotoBean> uploadImgs(Context mContext, String plan_id, ArrayList<String> photos);
    }

    interface View extends BaseView {
        void getOrderDetailSuccess(SendBoxOrderDetailBean orderBean);

        void uploadPhotoSuccess(List<String> picUrls);

        void completeOrderSuccess(BaseResponse baseResponse);

        void onError(String msg);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void queryCompleteOrderDetail(RequestBody body);

        public abstract void completeOrder(RequestBody body);

        public abstract void uploadPhoto(String plan_id,ArrayList<String> photos);
    }
}
