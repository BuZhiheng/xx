package com.horen.cortp.platform.presenter;
import android.content.Intent;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.api.MyMultipartBody;
import com.horen.cortp.platform.api.MySubscriber;
import com.horen.cortp.platform.bean.ApiResultCreateExperience;
import com.horen.cortp.platform.contract.ExperienceCreatePicContract;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.basebean.UserInfo;
import com.jaydenxiao.common.commonutils.ACache;
import com.jaydenxiao.common.commonutils.SpKey;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import static android.app.Activity.RESULT_OK;
/**
 * Created by HOREN on 2017/12/28.
 *
 * 万箱体验：创建/修改 图片
 *
 * 新箱构想：创建/修改 图片
 *
 * 根据intent isFromExperience 和 isCreate判断（init）
 */
public class ExperienceCreatePicPresenter extends ExperienceCreatePicContract.Presenter {
    private boolean isFromExperience;
    private boolean isCreate;
    private int position = -1;
    private ArrayList<String> finalPhotos;
    @Override
    public void init(Intent intent) {
        finalPhotos = intent.getStringArrayListExtra(CommonCode.INTENT_EXPERIENCE_CREATE_PICDATA);
        mView.setPic(finalPhotos);
        isFromExperience = intent.getBooleanExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,false);
        if (isFromExperience){
            //用箱体验
            mView.setTitle(mContext.getString(R.string.platform_activity_useboxfeel));
        } else {
            //新箱构想
            mView.setTitle(mContext.getString(R.string.platform_activity_newboxfuture));
        }
        isCreate = intent.getBooleanExtra(CommonCode.INTENT_EXPERIENCE_CREATE,false);
        if (isCreate){
            //如果是创建,设置删除按钮消失
            mView.setDeleteGone();
            mView.setAddGone();
        } else {
            //如果是修改,设置增加按钮消失
            position = intent.getIntExtra(CommonCode.INTENT_EXPERIENCE_CREATE_INDEX,-1);
            mView.setAddGone();
        }
    }
    @Override
    public void sureClick() {
//        if (isCreate){
//            if (isFromExperience){
//                mView.showErrorTip("创建用箱体验图片");
//            } else {
//                mView.showErrorTip("创建新箱构想图片");
//            }
//        } else {
//            if (isFromExperience){
//                mView.showErrorTip("修改用箱体验图片");
//            } else {
//                mView.showErrorTip("修改新箱构想图片");
//            }
//        }
        mView.setCommit(finalPhotos,RESULT_OK,position,"");//更新成功，回传id
    }
    @Override
    public void delClick() {
        mView.setCommit(null,RESULT_OK,position,"");//删除不需回传id
        mView.showErrorTip(mContext.getString(R.string.platform_delete_success));
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK){
            return;
        }
        if (requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                finalPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                mView.setPic(finalPhotos);
                mView.setAddGone();
//                if (isCreate){
//                    mView.setPic(finalPhotos);
//                    if (finalPhotos.size() < 9){
//                        mView.setAddShow();
//                    } else {
//                        mView.setAddGone();
//                    }
//                } else {
//                    mView.setPic(finalPhotos);
//                }
            }
        }
    }
    @Override
    public void removePic(int position) {
    }
    private void update(){
//        ToastUitl.showShort("update");
        UserInfo userInfo = (UserInfo) ACache.get(BaseApplication.getAppContext()).getAsObject(SpKey.USERINFO);
//        MyMultipartBody.Part part1 = MyMultipartBody.Part.createFormData("user_id",userInfo.getUser_id());
//        MyMultipartBody.Part part2 = MyMultipartBody.Part.createFormData("app_token", AppConfig.getInstance().getString(SpKey.LOGIN_TOKEN, ""));
//        final MyMultipartBody.Builder builder = new MyMultipartBody.Builder();
//        builder.addPart(part1);
//        builder.addPart(part2);
//        final List<MyMultipartBody.Part> parts = builder.build().parts();
        final Map<String, RequestBody> map = new HashMap<>();
        map.put("user_id", MyMultipartBody.create(userInfo.getUser_id())); // 添加参数
        map.put("app_token", MyMultipartBody.create(AppConfig.getInstance().getString(SpKey.LOGIN_TOKEN, "")));
        final MultipartBody.Builder builder = new MultipartBody.Builder();

        Luban.with(mContext).load(finalPhotos.get(0)).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {
                mView.showLoading("");
            }
            @Override
            public void onSuccess(File file) {
                RequestBody imageBody = MyMultipartBody.create(file);
                builder.addFormDataPart("photo", file.getName()+"1", imageBody);//photo 后台接收图片流的参数名
                builder.addFormDataPart("photo", file.getName()+"2", imageBody);//photo 后台接收图片流的参数名
                map.put("photo",builder.build());
                mRxManage.add(mModel.create(map, new MySubscriber<ApiResultCreateExperience>() {
                    @Override
                    protected void onSuccess(ApiResultCreateExperience result) {
                        if (result.success()){
                            mView.showErrorTip(mContext.getString(R.string.platform_upload_success));
                        } else {
                            mView.showErrorTip(mContext.getString(R.string.platform_upload_failure)+result.getErrMsg());
                        }
                    }
                    @Override
                    protected void onNetError() {
                    }
                    @Override
                    public void onError(String s) {
                        mView.showErrorTip(mContext.getString(R.string.platform_upload_failure)+s);
                    }
                }));
            }
            @Override
            public void onError(Throwable e) {
                mView.showErrorTip(mContext.getString(R.string.platform_upload_failure)+e.getMessage());
            }
        }).launch();
    }
}