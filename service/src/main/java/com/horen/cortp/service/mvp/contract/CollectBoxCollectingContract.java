package com.horen.cortp.service.mvp.contract;

import android.content.Context;
import android.widget.EditText;

import com.horen.cortp.service.adapter.BoxsInfoAdapter;
import com.horen.cortp.service.adapter.CollectBoxsCompleteGoodsAdapter;
import com.horen.cortp.service.adapter.CollectGoodsAdapter;
import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by HOREN on 2017/10/31.
 */

public interface CollectBoxCollectingContract {
    interface Model extends BaseModel {
        Observable<SendBoxOrderDetailBean> queryDeliveringOrderDetail(RequestBody body);

        Observable<BaseResponse> saveFetchOrder(RequestBody body);

        Observable<BaseResponse> submitFetchOrder(RequestBody body);

        Observable<UploadSinglePhotoBean> uploadImgs(Context mContext, String plan_id, ArrayList<String> photos);

    }

    interface View extends BaseView {
        void getOrderDetailSuccess(SendBoxOrderDetailBean orderBean);

        void saveFetchOrderSuccess();

        void submitFetchOrderSuccess();

        void uploadPhotoSuccess(List<String> picUrls);


        void onError(String msg);

        void submitCheckSuccess(String loss_qty, String damage_qty, List<SendBoxOrderDetailBean.ProductsBean> listByMap, List<SendBoxOrderDetailBean.PicsInfoBean> data);

        void saveCheckSuccess(String loss_qty, String damage_qty, List<SendBoxOrderDetailBean.ProductsBean> listByMap, List<SendBoxOrderDetailBean.PicsInfoBean> data);


    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void queryOrderDetail(RequestBody body);

        public abstract void saveFetchOrder(RequestBody body);

        public abstract void submitFetchOrder(RequestBody body);

        public abstract void uploadPhoto(String plan_id, ArrayList<String> photos);

        public abstract void submit(EditText etLoseNumber, String type, CollectGoodsAdapter splitOrderAdapter, CollectBoxsCompleteGoodsAdapter completeGoodsAdapter, BoxsInfoAdapter collectBoxsAdapter, SendBoxOrderDetailBean detailBean);

        public abstract void save(EditText etLoseNumber, String type, CollectGoodsAdapter splitOrderAdapter, CollectBoxsCompleteGoodsAdapter completeGoodsAdapter, BoxsInfoAdapter collectBoxsAdapter, SendBoxOrderDetailBean detailBean);

        public abstract boolean checkData(List<SendBoxOrderDetailBean.ProductsBean> listByMap, String loss_qty, String damage_qty);

        public abstract boolean checkChangeData(List<SendBoxOrderDetailBean.ProductsBean> listByMap, String loss_qty, String damage_qty);
    }
}
