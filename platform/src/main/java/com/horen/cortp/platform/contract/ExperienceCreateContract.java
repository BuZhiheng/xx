package com.horen.cortp.platform.contract;
import android.content.Intent;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import java.util.ArrayList;
import me.iwf.photopicker.utils.ImageCaptureManager;
/**
 * Created by HOREN on 2017/11/15.
 */
public interface ExperienceCreateContract {
    class Model implements BaseModel {
    }
    interface View extends BaseView {
        void setTitle(String title);
        void createPic(ArrayList<String> photos);
        void addItem(ExperienceCreate experienceCreate);
        void update(int position, ExperienceCreate experienceCreate);
        ArrayList<ExperienceCreate> getAdapterData();
        void finish();
        void removeItem(int position);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void init(Intent b);
        public abstract void commit();
        public abstract void onResult(ImageCaptureManager captureManager, int requestCode, int resultCode, Intent data);
    }
}