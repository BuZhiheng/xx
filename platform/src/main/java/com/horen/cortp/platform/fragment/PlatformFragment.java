package com.horen.cortp.platform.fragment;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.activity.AllPlanActivity;
import com.horen.cortp.platform.activity.FireflysActivity;
import com.horen.cortp.platform.activity.UseBoxExperienceActivity;
import com.horen.cortp.platform.adapter.HomeAdapter;
import com.horen.cortp.platform.adapter.HomeBannerAdapter;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.basebean.UserInfo;
import com.jaydenxiao.common.commonwidget.HomeBannerIndexAdapter;
import com.horen.cortp.platform.bean.HomeBanner;
import com.horen.cortp.platform.bean.Partner;
import com.horen.cortp.platform.contract.PlatformHomeContract;
import com.horen.cortp.platform.presenter.PlatformPresenter;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonutils.AnimationUtils;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonwidget.CircleRecyclerView;
import com.jaydenxiao.common.commonwidget.CircleRecyclerViewModeY;
import com.jaydenxiao.common.listener.MenuOpenListener;
import java.util.List;
/**
 * Created by BuZhiheng on 2017/11/10.
 *
 * 万箱首页fragment
 *
 */
public class PlatformFragment extends BaseFragment<PlatformPresenter, PlatformHomeContract.Model> implements PlatformHomeContract.View, View.OnClickListener, AppBarLayout.OnOffsetChangedListener, TabLayout.OnTabSelectedListener {
    private CircleRecyclerView recyclerView;
    private HomeAdapter adapter;
    private ConvenientBanner banner;
    private AppBarLayout appBarLayout;
    private LinearLayout layoutTitle;
    private LinearLayout layoutIndex;
    private MenuOpenListener listener;
    private LinearLayoutManager mLinearLayoutManager;
    private TabLayout tabLayout;
    private ImageView ivCursor;
    private FrameLayout frameLayout;
    private boolean isBottom;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_platform;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    protected void initView() {
        appBarLayout = rootView.findViewById(R.id.appbar_platform_home);
        layoutTitle = rootView.findViewById(R.id.ll_platform_home_titlebar);
        layoutIndex = rootView.findViewById(R.id.ll_home_banner_index);
        recyclerView = rootView.findViewById(R.id.rv_platform_home);
        frameLayout = rootView.findViewById(R.id.fl_platform);
        tabLayout = rootView.findViewById(R.id.tab_platform_home);
        tabLayout.addOnTabSelectedListener(this);
        ivCursor = rootView.findViewById(R.id.iv_platform_cursor);
        mLinearLayoutManager = new LinearLayoutManager(_mActivity);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new HomeAdapter(_mActivity);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setViewMode(new CircleRecyclerViewModeY());//设置方案滚动效果
        recyclerView.setAdapter(adapter);
        banner = rootView.findViewById(R.id.banner_plarform);
        rootView.findViewById(R.id.ll_home_allplan).setOnClickListener(this);
        rootView.findViewById(R.id.ll_home_experience).setOnClickListener(this);
        rootView.findViewById(R.id.ll_home_firefly).setOnClickListener(this);
        rootView.findViewById(R.id.iv_platform_home_mine).setOnClickListener(this);
        rootView.findViewById(R.id.iv_platform_home_scanner).setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //获取第一个可见view的位置
                int firstItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                mPresenter.changeTabLayout(recyclerView,firstItemPosition);
            }
        });
        appBarLayout.addOnOffsetChangedListener(this);
        startProgressDialog();
        mPresenter.getData();
        if (isBottom){
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
            params.bottomMargin = 0;
            frameLayout.setLayoutParams(params);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void showLoading(String title) {
        startProgressDialog();
    }
    @Override
    public void stopLoading() {
        stopProgressDialog();
    }
    @Override
    public void showErrorTip(String msg) {
        showErrorToast(msg);
        stopLoading();
    }
    @Override
    public void setData(List<Object> datas) {
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
        stopLoading();
    }
    @Override
    public void addPartner(List<Partner> partners) {
        adapter.addPartner(partners);
        adapter.notifyDataSetChanged();
        stopLoading();
    }
    @Override
    public void setBanner(List<HomeBanner> banners) {
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new HomeBannerAdapter();
            }
        },banners);
        banner.startTurning(3900);
        banner.setOnPageChangeListener(new HomeBannerIndexAdapter(getContext(),banners.size(),layoutIndex));
    }
    @Override
    public void setTab(String tabStr,int position) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_platform_home_tablayout_txt,null);
        TextView textView = view.findViewById(R.id.tv_tab_title);
        textView.setText(tabStr);//设置tab上的文字
        TabLayout.Tab tab = tabLayout.newTab();
        tab.setCustomView(view);
        tab.setTag(position);
        tabLayout.addTab(tab);
        if (position == 0){
            updateTabLayout(tabLayout.getTabAt(0));//当初始化tab的时候，设置游标
        }
    }
    public void updateTabLayout(int index){
        updateTabLayout(tabLayout.getTabAt(index));
    }
    @Override
    public void stopBanner() {
        if (banner != null){
            banner.stopTurning();
        }
    }
    @Override
    public void startBanner() {
        if (banner != null){
            banner.startTurning(3900);
        }
    }
    public void updateTabLayout(TabLayout.Tab tab) {
        ivCursor.setTag(tab.getPosition());//设置游标当前位置
        int tabW = tab.getCustomView().getWidth();//tab item 的宽度
        int tabH = tab.getCustomView().getHeight();//tab item 的高度
        int[] p = new int[2];
        tab.getCustomView().getLocationInWindow(p);
        int tabL = p[0];//获取选中tab的绝对横坐标
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ivCursor.getLayoutParams();
        params.width = tabW;
        params.height = tabH;
        ivCursor.setLayoutParams(params);//设置游标宽高
        float fromX = ivCursor.getTranslationX();
        AnimationUtils.scaleView(ivCursor,200);//游标缩放动画
        AnimationUtils.translationViewX(ivCursor,fromX,tabL,200);//游标平移动画
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tabAt = tabLayout.getTabAt(i);//获得每一个tab
            TextView textView = tabAt.getCustomView().findViewById(R.id.tv_tab_title);
            textView.setTextColor(Color.parseColor("#333333"));//设置tab上的文字
        }
        TextView textView = tab.getCustomView().findViewById(R.id.tv_tab_title);
        textView.setTextColor(Color.parseColor("#FFFFFFFF"));//设置tab上的文字
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_home_allplan){
            Intent intent = new Intent(_mActivity, AllPlanActivity.class);
            startActivity(intent);
        } else if (id == R.id.ll_home_experience){
            Intent intent = new Intent(_mActivity, UseBoxExperienceActivity.class);
            startActivity(intent);
        } else if (id == R.id.ll_home_firefly){
            Intent intent = new Intent(_mActivity, FireflysActivity.class);
            startActivity(intent);
        } else if (id == R.id.iv_platform_home_mine){
            if (UserInfo.isLogin() && listener != null){
                listener.open();
            } else {
                ARouter.getInstance().build(CommonRoutePath.LOGIN_COMMON_ACTIVITY).navigation();
            }
        } else if (id == R.id.iv_platform_home_scanner){
            if (UserInfo.isLogin() && listener != null){
                listener.toScanCode();
            } else {
                ARouter.getInstance().build(CommonRoutePath.LOGIN_COMMON_ACTIVITY).navigation();
            }
        }
    }
    public void setOnMenuOpenListener(MenuOpenListener listener){
        this.listener = listener;
    }
    public void setBottom(){
        isBottom = true;
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mLinearLayoutManager.scrollToPositionWithOffset((Integer) tab.getTag(), 0);
        updateTabLayout(tab);
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int px = DisplayUtil.dip2px(80);
        float f = (float)-verticalOffset/px;
        if (f > 1) {
            f = 1;
        }
        layoutTitle.getBackground().setAlpha((int) (f * 255));
        // 通知标题栏刷新显示
    }
}