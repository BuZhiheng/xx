package com.horen.cortp.platform.contract;
import android.content.Intent;
import android.widget.EditText;

import com.horen.cortp.platform.bean.ExperienceCreate;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
/**
 * Created by HOREN on 2017/11/15.
 */
public interface ExperienceCreateTxtContract {
    class Model implements BaseModel {
    }
    interface View extends BaseView {
        void setTitle(String title);
        void setEditText(String txt);
        void setDeleteGone();
        void setCommit(ExperienceCreate experienceCreate, int resultCodeCreateTxtSuccess,int position,String id);
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void init(Intent intent);
        public abstract void sureClick(EditText editText);
        public abstract void delClick();
    }
}