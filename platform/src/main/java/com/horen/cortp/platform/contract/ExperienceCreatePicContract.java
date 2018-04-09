package com.horen.cortp.platform.contract;
import android.content.Intent;
import com.horen.cortp.platform.api.ApiFactory;
import com.horen.cortp.platform.api.MyMultipartBody;
import com.horen.cortp.platform.bean.ApiResultCreateExperience;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Part;
import rx.Observer;
import rx.Subscription;
/**
 * Created by HOREN on 2017/11/15.
 */
public interface ExperienceCreatePicContract {
    class Model implements BaseModel {
        public Subscription create(Map<String, RequestBody> map, Observer<ApiResultCreateExperience> observer) {
            return ApiFactory.doHttp(ApiFactory.getSingleApi()
                    .createExperience(map),observer);
        }
    }
    interface View extends BaseView {
        void setTitle(String title);
        void setDeleteGone();
        void setPic(ArrayList<String> photos);
        void setAddGone();
        void setAddShow();
        void setCommit(ArrayList<String> finalPhotos, int resultCodeCreatePicSuccess,int position,String id);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void init(Intent intent);
        public abstract void sureClick();
        public abstract void delClick();
        public abstract void onActivityResult(int requestCode, int resultCode, Intent data);
        public abstract void removePic(int position);
    }
}