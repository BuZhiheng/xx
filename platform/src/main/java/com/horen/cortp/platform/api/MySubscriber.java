package com.horen.cortp.platform.api;
import com.horen.cortp.platform.bean.BaseResult;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.commonutils.NetWorkUtils;
import rx.Subscriber;
/**
 * Created by HOREN on 2017/12/19.
 */
public abstract class MySubscriber<T extends BaseResult> extends Subscriber<T> {
    @Override
    public void onCompleted() {
    }
    @Override
    public void onError(Throwable e) {
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
//            onNetError();
            //最终用户会接受几种不同 error 此处是网络链接错误
            onError(BaseApplication.getAppContext().getString(com.jaydenxiao.common.R.string.error_please_check_network));
        } else {
            //此处是服务器请求error 服务器不稳定 (HTTP请求失败)
            onError(BaseApplication.getAppContext().getString(com.jaydenxiao.common.R.string.error_unknow));
        }
    }
    @Override
    public void onNext(T o) {
        if (o.success()){
            onSuccess(o);
        } else {
            //此处是服务器正常请求，返回错误信息，由服务器定义错误字符
            onError(o.getErrMsg());
        }
    }
    protected abstract void onSuccess(T t);
    protected abstract void onError(String s);
    protected void onNetError(){
        //activity 视情况处理此 error
    }
}