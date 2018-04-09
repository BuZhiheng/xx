package debug;

import com.horen.cortp.service.R;
import com.horen.cortp.service.common.RequestUtils;
import com.jaydenxiao.common.api.Api;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.basebean.UserInfo;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ACache;
import com.jaydenxiao.common.commonutils.SpKey;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HOREN on 2017/12/13.
 */

public class LaunchActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.item_iv;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        // 模拟登陆,得到apptoken
        login();

    }

    private void login() {
        Api.getDefault().login(RequestUtils.login("yangweijia", "123456", "", 4))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<UserInfo>(mContext, false) {

                    @Override
                    protected void _onNext(UserInfo test) {
                        //保存用户TOKEN
                        AppConfig.getInstance().putString(SpKey.LOGIN_TOKEN, test.getUser().getToken());
                        AppConfig.getInstance().putString(SpKey.COMPANYID, test.getUser().getCompany_id());
                        startActivity(ServiceDebugActivity.class);
                        ACache.get(mContext).put(SpKey.USERINFO,test);
                        finish();
                    }

                    @Override
                    protected void _onError(String message) {

                    }
                });
    }
}
