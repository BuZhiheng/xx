package com.horen.cortp.platform.presenter;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.api.MyRequestBody;
import com.horen.cortp.platform.api.MySubscriber;
import com.horen.cortp.platform.bean.ApiResultBoxExperience;
import com.horen.cortp.platform.bean.BoxExperience;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.horen.cortp.platform.contract.UseExperienceContract;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.basebean.UserInfo;

import okhttp3.RequestBody;
/**
 * Created by HOREN on 2017/12/12.
 */
public class UseExperiencePresenter extends UseExperienceContract.Presenter {
    private boolean getMine = false;
    @Override
    public void getDataMine() {
        if (!UserInfo.isLogin()){
            mView.showErrorTip(mContext.getString(R.string.share_nologin));
            return;
        }
        getMine = true;
        mView.showLoading("");
        RequestBody body = new MyRequestBody.Builder()
                .add("company_id","")
                .add("company_name","")
                .build();
        mRxManage.add(mModel.getData(body, new MySubscriber<ApiResultBoxExperience>() {
            @Override
            protected void onSuccess(ApiResultBoxExperience resultData) {
                if (resultData.success() && resultData.solution_experiences.size() > 0){
                    int size = AppConfig.getInstance().getInt(ExperienceCreate.class.getName()+"isExperience",0);
                    if (size > 0){
                        BoxExperience experience = new BoxExperience();
                        experience.company_name = mContext.getString(R.string.platform_test_mid);
                        experience.experience_title = mContext.getString(R.string.platform_test);
                        experience.use_time = "";
                        experience.tag = "0";
                        resultData.solution_experiences.clear();
                        resultData.solution_experiences.add(experience);
                        mView.setData(resultData.solution_experiences);
                    } else {
                        mView.stopLoading();
                    }
                    mView.setMine();
                }
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
    @Override
    public void getDataMore() {
        getMine = false;
        mView.showLoading("");
        RequestBody body = new MyRequestBody.Builder()
                .add("company_id","")
                .add("company_name","")
                .build();
        mRxManage.add(mModel.getData(body, new MySubscriber<ApiResultBoxExperience>() {
            @Override
            protected void onSuccess(ApiResultBoxExperience resultData) {
                if (resultData.success() && resultData.solution_experiences.size() > 0){
                    mView.setData(resultData.solution_experiences);
                    mView.setMore();
                }
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
    @Override
    public void resume() {
        if (getMine){
            getDataMine();
        } else {
            getDataMore();
        }
    }
}