package com.horen.cortp.platform.presenter;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.horen.cortp.platform.contract.ExperienceCreateTxtContract;

import static android.app.Activity.RESULT_OK;
import static com.horen.cortp.platform.api.CommonCode.RESULT_CODE_CREATE_TXT_SUCCESS;

/**
 * Created by HOREN on 2017/12/28.
 *
 * 万箱体验：创建/修改 文字
 *
 * 新箱构想：创建/修改 文字
 *
 * 根据intent isFromExperience 和 isCreate判断（init）
 */
public class ExperienceCreateTxtPresenter extends ExperienceCreateTxtContract.Presenter {
    private boolean isFromExperience;
    private boolean isCreate;
    private int position = -1;
    @Override
    public void init(Intent intent) {
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
        } else {
            //如果是修改,读取修改文字
            String txt = intent.getStringExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TXTDATA);
            position = intent.getIntExtra(CommonCode.INTENT_EXPERIENCE_CREATE_INDEX,-1);
            mView.setEditText(txt);
        }
    }
    @Override
    public void sureClick(EditText editText) {
        String txt = editText.getText().toString();
        if (TextUtils.isEmpty(txt)){
            mView.showErrorTip(mContext.getString(R.string.platform_imput_hint_experience_create));
            return;
        }
//        if (isCreate){
//            if (isFromExperience){
//                //创建用箱体验文字描述
//                mView.showErrorTip("创建用箱体验文字描述");
//            } else {
//                //创建新箱构想文字描述
//                mView.showErrorTip("创建新箱构想文字描述");
//            }
//        } else {
//            if (isFromExperience){
//                //修改用箱体验文字描述
//                mView.showErrorTip("修改用箱体验文字描述");
//            } else {
//                //修改新箱构想文字描述
//                mView.showErrorTip("修改新箱构想文字描述");
//            }
//        }
        ExperienceCreate experienceCreate = new ExperienceCreate();
        experienceCreate.experience_text = txt;
        mView.setCommit(experienceCreate,RESULT_OK,position,"");//更新，回传id
    }
    @Override
    public void delClick() {
        mView.setCommit(null,RESULT_OK,position,"");//删除，不需要回传id
    }
}