package debug;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.SDKInitializer;
import com.horen.cortp.service.R;
import com.horen.cortp.service.common.RequestUtils;
import com.jaydenxiao.common.api.Api;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.basebean.UserInfo;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.SpKey;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HOREN on 2017/12/11.
 */

public class ServiceApp extends BaseApplication {

    // 全局刷新头部
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new MaterialHeader(context)
                        .setColorSchemeColors(getAppResources().getColor(R.color.color_main_color));
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 百度地图初始化
        SDKInitializer.initialize(getApplicationContext());
        // 二维码扫描初始化
        ZXingLibrary.initDisplayOpinion(this);
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }

}
