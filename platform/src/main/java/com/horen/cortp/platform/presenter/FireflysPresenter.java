package com.horen.cortp.platform.presenter;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.api.MyRequestBody;
import com.horen.cortp.platform.api.MySubscriber;
import com.horen.cortp.platform.bean.ApiResultFireflyList;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.horen.cortp.platform.bean.Fireflys;
import com.horen.cortp.platform.contract.FireflysContract;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.basebean.UserInfo;
import okhttp3.RequestBody;
/**
 * Created by HOREN on 2017/12/12.
 */
public class FireflysPresenter extends FireflysContract.Presenter {
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
                .build();
        mRxManage.add(mModel.getData(body, new MySubscriber<ApiResultFireflyList>() {
            @Override
            protected void onSuccess(ApiResultFireflyList resultData) {
                if (resultData.success() && resultData.solution_fireflys.size() > 0){
                    int size = AppConfig.getInstance().getInt(ExperienceCreate.class.getName()+"isExperience",0);
                    if (size > 0){
                        Fireflys fireflys = new Fireflys();
                        fireflys.firefly_title = mContext.getString(R.string.platform_test_mid);
                        fireflys.firefly_text = mContext.getString(R.string.platform_test);
                        fireflys.tag = "0";
                        resultData.solution_fireflys.clear();
                        resultData.solution_fireflys.add(fireflys);
                        mView.setData(resultData.solution_fireflys);
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
                .build();
        mRxManage.add(mModel.getData(body, new MySubscriber<ApiResultFireflyList>() {
            @Override
            protected void onSuccess(ApiResultFireflyList resultData) {
                if (resultData.success() && resultData.solution_fireflys.size() > 0){
                    mView.setData(resultData.solution_fireflys);
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
    public void onResume() {
        if (getMine){
            getDataMine();
        } else {
            getDataMore();
        }
    }
}