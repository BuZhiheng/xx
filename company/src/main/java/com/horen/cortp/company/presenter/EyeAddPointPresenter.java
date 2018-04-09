package com.horen.cortp.company.presenter;
import com.horen.cortp.company.api.ApiRequestPram;
import com.horen.cortp.company.contract.EyeAddPointContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxSubscriber;
/**
 * Created by HOREN on 2018/2/26.
 */
public class EyeAddPointPresenter extends EyeAddPointContract.Presenter {
    @Override
    public void commit(String name, String aName, String sAddress, String lat, String lng) {
        mRxManage.add(mModel.addRelation(ApiRequestPram.addRelation(name,aName,sAddress,lat,lng)).subscribe(new RxSubscriber<BaseResponse>(mContext, false) {
            @Override
            protected void _onNext(BaseResponse info) {
                mView.commitSuccess();
            }
            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}