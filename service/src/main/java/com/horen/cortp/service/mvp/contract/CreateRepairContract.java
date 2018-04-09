package com.horen.cortp.service.mvp.contract;

import android.content.Context;

import com.horen.cortp.service.bean.CreateRepairBean;
import com.horen.cortp.service.bean.RepairDetailBean;
import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public interface CreateRepairContract {
    interface Model extends BaseModel {
        Observable<CreateRepairBean> createRepairOrder(RequestBody body);

        Observable<RepairDetailBean> queryRepairOrderDetailList(RequestBody body);

        Observable<BaseResponse> saveRepairOrder(RequestBody body);

        Observable<BaseResponse> submitRepairOrder(RequestBody body);

        Observable<UploadSinglePhotoBean> uploadImgs(Context mContext, String plan_id, ArrayList<String> photos);

    }

    interface View extends BaseView {
        void createRepairOrderSuccess(CreateRepairBean createRepairBean);

        void uploadPhotoSuccess(List<String> picUrls);

        void saveRepairOrderSuccess(BaseResponse response);

        void submitRepairOrderSuccess(BaseResponse response);

        void queryRepairOrderDetailSuccess(RepairDetailBean repairDetailBean);


        void onError(String msg);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void uploadImgs(String plan_id, ArrayList<String> photos);

        public abstract void createRepairOrder(RequestBody body);

        public abstract void saveRepairOrder(RequestBody body);

        public abstract void submitRepairOrder(RequestBody body);

        public abstract void queryRepairOrderDetailList(RequestBody body);

    }
}
