package com.horen.cortp.platform.presenter;
import android.content.Intent;
import android.text.TextUtils;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.horen.cortp.platform.contract.ExperienceCreateContract;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.commonutils.PictureUtil;
import java.util.ArrayList;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.ImageCaptureManager;
import static android.app.Activity.RESULT_OK;
/**
 * Created by HOREN on 2017/12/28.
 */
public class ExperienceCreatePresenter extends ExperienceCreateContract.Presenter {
    private boolean isExperience;
    private boolean isCreate;
    private String postId = "";
    @Override
    public void init(Intent intent) {
        isExperience = intent.getBooleanExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,false);
        if (isExperience){
            //用箱体验
            mView.setTitle(mContext.getString(R.string.platform_activity_useboxfeel));
        } else {
            //新箱构想
            mView.setTitle(mContext.getString(R.string.platform_activity_newboxfuture));
        }
        isCreate = intent.getBooleanExtra(CommonCode.INTENT_EXPERIENCE_CREATE,true);
        if (!isCreate){
            postId = intent.getStringExtra(CommonCode.INTENT_EXPERIENCE_CREATE_ID);
            String name;
            if (isExperience){
                name = ExperienceCreate.class.getName()+"isExperience";
            } else {
                name = ExperienceCreate.class.getName();
            }
            int size = AppConfig.getInstance().getInt(name,0);
            if (size == 0){
                return;
            }
            for (int i=0;i<size;i++){
                String str = AppConfig.getInstance().getString(name+i,"");
                ExperienceCreate create = new ExperienceCreate();
                if (str.contains("/")){
                    create.experience_image = str;
                } else {
                    create.experience_text = str;
                }
                mView.addItem(create);
            }
        }
    }
    @Override
    public void commit() {
        ArrayList<ExperienceCreate> arrayList = mView.getAdapterData();
        if (arrayList == null || arrayList.size() == 0){
            mView.showErrorTip(mContext.getString(R.string.platform_please_upload_msg));
            return;
        }
        String name;
        if (isExperience){
            name = ExperienceCreate.class.getName()+"isExperience";
        } else {
            name = ExperienceCreate.class.getName();
        }
        AppConfig.getInstance().putInt(name,arrayList.size());
        for (int i=0;i<arrayList.size();i++){
            ExperienceCreate create = arrayList.get(i);
            if (TextUtils.isEmpty(create.experience_text)){
                AppConfig.getInstance().putString(name+i,create.experience_image);
            } else {
                AppConfig.getInstance().putString(name+i,create.experience_text);
            }
        }
        mView.showErrorTip(mContext.getString(R.string.platform_upload_success_wait_inreview));
        mView.finish();
    }
    @Override
    public void onResult(ImageCaptureManager captureManager, int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK){
            return;
        }
        if (requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                mView.createPic(photos);
            }
        } else if (requestCode == PictureUtil.PIC_CAMERA){
            if(captureManager.getCurrentPhotoPath() != null) {
                captureManager.galleryAddPic();
                // 照片地址
                String imagePaht = captureManager.getCurrentPhotoPath();
                ArrayList<String> photos = new ArrayList<>();
                photos.add(imagePaht);
                mView.createPic(photos);
            }
        } else if (requestCode == CommonCode.RESULT_CODE_CREATE_TXT_SUCCESS){
            ExperienceCreate experienceCreate = (ExperienceCreate) data.getSerializableExtra(ExperienceCreate.class.getName());
            int position = data.getIntExtra(CommonCode.INTENT_EXPERIENCE_CREATE_INDEX,-1);
            if (experienceCreate == null){//删除
                mView.removeItem(position);
            } else {//更新页面
                postId = data.getStringExtra(CommonCode.INTENT_EXPERIENCE_CREATE_ID);
                if (position >= 0){
                    mView.update(position,experienceCreate);
                } else {
                    mView.addItem(experienceCreate);
                }
            }
        } else if (requestCode == CommonCode.RESULT_CODE_CREATE_PIC_SUCCESS){
            ArrayList<String> experienceCreates = data.getStringArrayListExtra(ExperienceCreate.class.getName());
            int position = data.getIntExtra(CommonCode.INTENT_EXPERIENCE_CREATE_INDEX,-1);
            if (experienceCreates == null){//删除
                mView.removeItem(position);
            } else {//更新页面
                postId = data.getStringExtra(CommonCode.INTENT_EXPERIENCE_CREATE_ID);
                if (position >= 0){
                    ExperienceCreate create = new ExperienceCreate();
                    create.experience_image = experienceCreates.get(0);
                    mView.update(position,create);
                } else {
                    for (String img : experienceCreates){
                        ExperienceCreate create = new ExperienceCreate();
                        create.experience_image = img;
                        mView.addItem(create);
                    }
                }
            }
        }
    }
}