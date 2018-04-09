package com.horen.cortp.platform.api;
import com.jaydenxiao.common.api.Api;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * Created by HOREN on 2017/11/15.
 */
public class ApiFactory {
    private static PlatformApi api;
    private ApiFactory(){
    }
    public static PlatformApi getSingleApi(){
        if (api == null){
            api = Api.getApiService(PlatformApi.class);
        }
        return api;
    }
    public static Subscription doHttp(Observable observable, Observer observer){
        /**
         *
         * 最终 HTTP 请求
         * 封装后 model 通过调用此静态方法实现网络请求
         * presenter 传参 new Subscriber() 订阅 Subscription
         * 将 Subscription 添加到 RxManager 实现统一管理
         *
         * */
        return observable.subscribeOn(Schedulers.io())
//                .compose(new Observable.Transformer() {
//                    @Override
//                    public Object call(Object o) {
//                        return o;
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}