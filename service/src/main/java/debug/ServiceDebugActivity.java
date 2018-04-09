package debug;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.horen.cortp.service.R;
import com.horen.cortp.service.fragment.CarFragment;
import com.horen.cortp.service.fragment.DriverFragment;
import com.horen.cortp.service.fragment.ServiceOrderFragment;
import com.jaydenxiao.common.api.HttpUrl;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.ScanActivity;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.basebean.ScannerRequest;
import com.jaydenxiao.common.basebean.TabEntity;
import com.jaydenxiao.common.commonutils.GsonUtil;
import com.jaydenxiao.common.commonutils.LanguageUtil;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.SpKey;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by HOREN on 2017/10/11.
 */

public class ServiceDebugActivity extends BaseActivity implements View.OnClickListener {


    private SupportFragment[] mFragments = new SupportFragment[3];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
//    public static final int FOURTH = 3;

    public static final int REQUEST_CODE = 1002;

    /**
     * 初始化tab栏数据
     */
    private int[] mIconUnselectIds = {
            R.mipmap.home_wanxiang_default, R.mipmap.home_baiwang_default,
            R.mipmap.home_tianyan_default};
    private int[] mIconSelectIds = {
            R.mipmap.home_wanxiang_click, R.mipmap.home_baiwang_click,
            R.mipmap.home_tianyan_click};


    /**
     * 存储初始化TabLayout所需要的数据
     */
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles;


    private RelativeLayout drawerLayout;
    private RelativeLayout toolBar;
    private ImageView ivSelectClick;
    private TextView toolBarTitleTv;
    private ImageView ivScanClick;
    private FrameLayout flTabContainer;
    private CommonTabLayout commonTabLayout;


    @Override
    public int getLayoutId() {
        return R.layout.activity_service_client_debug;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        drawerLayout = (RelativeLayout) findViewById(R.id.drawerLayout);
        toolBar = (RelativeLayout) findViewById(R.id.tool_bar);
        ivSelectClick = (ImageView) findViewById(R.id.iv_select_click);
        ivSelectClick.setOnClickListener(this);
        toolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        ivScanClick = (ImageView) findViewById(R.id.iv_scan_click);
        ivScanClick.setOnClickListener(this);
        flTabContainer = (FrameLayout) findViewById(R.id.fl_tab_container);
        commonTabLayout = (CommonTabLayout) findViewById(R.id.common_tab_layout);


        mTitles = new String[]{getString(R.string.tab_text_index), getString(R.string.tab_text_order),
                getString(R.string.tab_text_wisdom)};
        toolBarTitleTv.setText(mTitles[SECOND]); // 首页标题
        initabLayout();
        // 默认首页
//        showBadge("3");
    }

    /**
     * 初始化tablayout
     */
    private void initabLayout() {
        // 初始化数据
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        // 设置数据集到Tablayout中
        commonTabLayout.setTabData(mTabEntities);
        // 默认选中第一个Tab
        commonTabLayout.setCurrentTab(SECOND);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                toolBarTitleTv.setText(mTitles[position]);
                showHideFragment(mFragments[position]);
//                changeLeftRightButton(position);
            }

            @Override
            public void onTabReselect(int position) { // 重复选择
            }
        });

    }


    private static Boolean isExit = false;

    @Override
    public void onBackPressedSupport() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            showShortToast(R.string.hint_exit);
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[FIRST] = CarFragment.newInstance();
            mFragments[SECOND] = (SupportFragment) ARouter.getInstance().build("/home/ServiceOrderFragment").navigation();
            mFragments[THIRD] = DriverFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_tab_container, SECOND,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            mFragments[FIRST] = findFragment(CarFragment.class);
            mFragments[SECOND] = findFragment(ServiceOrderFragment.class);
            mFragments[THIRD] = findFragment(DriverFragment.class);
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ServiceDebugActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {

                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    LogUtils.loge("二维码扫描结果:" + result);
                    try {
                        ScannerRequest scannerRequest = GsonUtil.getGson().fromJson(result, ScannerRequest.class);
                        scannerRequest.setApp_token(AppConfig.getInstance().getString(SpKey.LOGIN_TOKEN, ""));
                        scannerRequest.setLocale(LanguageUtil.getLanguageParam());
                        scannerRequest.setClient_type("1");
                        //H5
                        String url = HttpUrl.SCAN_SIGN;
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("url", url);
                        bundle1.putSerializable("scannerRequest", scannerRequest);
                        ARouter.getInstance().build(CommonRoutePath.WEB_COMPANYWEBVIEWACTIVITY).with(bundle1).navigation();
                    } catch (Exception e) {
                        e.printStackTrace();
                        showShortToast(result + "");
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_select_click) {
            ARouter.getInstance().build("/serverclient/MeActivity").navigation();
        } else if (v.getId() == R.id.iv_scan_click) {
            startActivityForResult(ScanActivity.class, REQUEST_CODE);
        }
    }
}
