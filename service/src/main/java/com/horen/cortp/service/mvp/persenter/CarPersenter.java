package com.horen.cortp.service.mvp.persenter;

import com.horen.cortp.service.bean.CarNumberBean;
import com.horen.cortp.service.mvp.contract.CarContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxSubscriber;

import okhttp3.RequestBody;

/**
 * Created by HOREN on 2017/12/1.
 */

public class CarPersenter extends CarContract.Presenter {
    @Override
    public void queryVehicleList(RequestBody body,boolean showDialog) {
        mRxManage.add(mModel.queryVehicleList(body)
                .subscribe(new RxSubscriber<CarNumberBean>(mContext, showDialog) {
                    @Override
                    protected void _onNext(CarNumberBean carNumberBean) {
                        mView.queryVehicleListSuccess(carNumberBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void insertVehicleList(RequestBody body) {
        mRxManage.add(mModel.insertVehicleList(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.insertVehicleListSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void deleteVehicle(RequestBody body) {
        mRxManage.add(mModel.deleteVehicle(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.deleteVehicleSuccess(baseResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    @Override
    public void LoadMore(RequestBody body) {
        mRxManage.add(mModel.queryVehicleList(body)
                .subscribe(new RxSubscriber<CarNumberBean>(mContext, true) {
                    @Override
                    protected void _onNext(CarNumberBean carNumberBean) {
                        mView.loadMoreSuccess(carNumberBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }
}
