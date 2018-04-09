package com.horen.cortp.platform.presenter;
import com.horen.cortp.platform.api.MyRequestBody;
import com.horen.cortp.platform.api.MySubscriber;
import com.horen.cortp.platform.bean.ApiResultPartnerList;
import com.horen.cortp.platform.contract.AllPartnerContract;
import okhttp3.RequestBody;
/**
 * Created by HOREN on 2017/12/12.
 */
public class AllPartnerPresenter extends AllPartnerContract.Presenter {
    @Override
    public void getPartner() {
        RequestBody body = new MyRequestBody.Builder()
                .add("page","1")
                .add("rows","100")
                .build();
        mRxManage.add(mModel.getPartner(body, new MySubscriber<ApiResultPartnerList>() {
            @Override
            protected void onSuccess(ApiResultPartnerList resultData) {
                if (resultData.success() && resultData.solution_companys.size() > 0){
                    mView.setPartner(resultData.solution_companys);
                }
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
}