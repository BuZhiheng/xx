package com.horen.cortp.service.mvp.contract;

import android.content.Context;

import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public interface ExceptionTaskContract {
    interface Model extends BaseModel {
        Observable<UploadSinglePhotoBean> uploadImgs(Context mContext, String plan_id, ArrayList<String> photos);
    }

    interface View extends BaseView {

        void uploadPhotoSuccess(List<String> picUrls);

        void onError(String msg);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void uploadImgs(String plan_id, ArrayList<String> photos);
    }
}
