package com.horen.cortp.platform.presenter;
import android.widget.EditText;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.api.MyRequestBody;
import com.horen.cortp.platform.api.MySubscriber;
import com.horen.cortp.platform.bean.ApiResultPlanData;
import com.horen.cortp.platform.contract.AllHotPlanContract;
/**
 * Created by HOREN on 2017/12/12.
 */
public class AllHotPlanPresenter extends AllHotPlanContract.Presenter {
    private int page = 1;
    @Override
    public void getCollectionPlanDataRefresh() {
        MyRequestBody.Builder builder = new MyRequestBody.Builder();
        page = 1;
        builder.add("page",page+"");
        builder.add("rows","10");
        builder.add("flag_hot","1");
        mRxManage.add(mModel.getCollectionPlanData(builder.build(), new MySubscriber<ApiResultPlanData>() {
            @Override
            protected void onSuccess(ApiResultPlanData apiResultPlanData) {
                mView.setPlanData(apiResultPlanData.favorite_solutions);
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
    @Override
    public void getCollectionPlanDataLoadmore() {
        MyRequestBody.Builder builder = new MyRequestBody.Builder();
        page ++;
        builder.add("page",page+"");
        builder.add("rows","10");
        builder.add("flag_hot","1");
        mRxManage.add(mModel.getCollectionPlanData(builder.build(), new MySubscriber<ApiResultPlanData>() {
            @Override
            protected void onSuccess(ApiResultPlanData apiResultPlanData) {
                if(apiResultPlanData.success() && apiResultPlanData.favorite_solutions != null && apiResultPlanData.favorite_solutions.size() > 0){
                    mView.addPlanData(apiResultPlanData.favorite_solutions);
                } else {
                    mView.stopLoading();
                }
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
    @Override
    public void getPlanDataRefresh() {
        getPlanDataRefresh(null);
    }
    @Override
    public void getPlanDataLoadmore() {
        getPlanDataLoadmore(null);
    }
    @Override
    public void getPlanDataRefresh(EditText editText) {
        MyRequestBody.Builder builder = new MyRequestBody.Builder();
        page = 1;
        builder.add("page",page+"");
        builder.add("rows","10");
        if (editText == null){
            builder.add("flag_hot","1");
        } else {
            builder.add("solution_name",editText.getText().toString());
        }
        mRxManage.add(mModel.getPlanData(builder.build(), new MySubscriber<ApiResultPlanData>() {
            @Override
            protected void onSuccess(ApiResultPlanData apiResultPlanData) {
                mView.setPlanData(apiResultPlanData.solutions);
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
    @Override
    public void getPlanDataLoadmore(EditText editText) {
        MyRequestBody.Builder builder = new MyRequestBody.Builder();
        page ++;
        builder.add("page",page+"");
        builder.add("rows","10");
        if (editText == null){
            builder.add("flag_hot","1");
        } else {
            builder.add("solution_name",editText.getText().toString());
        }
        mRxManage.add(mModel.getPlanData(builder.build(), new MySubscriber<ApiResultPlanData>() {
            @Override
            protected void onSuccess(ApiResultPlanData apiResultPlanData) {
                mView.addPlanData(apiResultPlanData.solutions);
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
}