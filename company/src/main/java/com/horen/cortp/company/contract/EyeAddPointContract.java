package com.horen.cortp.company.contract;
import com.horen.cortp.company.api.ApiCompanyFactory;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;
import okhttp3.RequestBody;
import rx.Observable;
/**
 * Created by HOREN on 2018/2/26.
 */
public interface EyeAddPointContract {
    class Model implements BaseModel {
        public Observable<BaseResponse> addRelation(RequestBody body) {
            return ApiCompanyFactory.getSingleApi().addRelation(body)
                    .compose(RxResultHelper.<BaseResponse>handleResult());
        }
    }
    interface View extends BaseView {
        void commitSuccess();
    }
    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void commit(String name, String aName,String sAddress, String lat, String lng);
    }
}