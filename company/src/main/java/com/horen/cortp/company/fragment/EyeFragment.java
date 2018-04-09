package com.horen.cortp.company.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.horen.cortp.company.R;
import com.horen.cortp.company.activity.EyeAssetActivity;
import com.jaydenxiao.common.baserx.MsgEvent;
import com.horen.cortp.company.widget.CortpHeader;
import com.horen.cortp.company.widget.JavaScriptObject;
import com.jaydenxiao.common.api.HttpUrl;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.basebean.UserInfo;
import com.jaydenxiao.common.commonutils.AppUtil;
import com.jaydenxiao.common.commonutils.CUtils;
import com.jaydenxiao.common.commonutils.SpKey;
import com.jaydenxiao.common.listener.MenuOpenListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Zhao on 2017/11/22/022.
 */

public class EyeFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener {
    private LinearLayout llLogin;
    private ImageView ivSelectClick;
    private WebView webView;
    private String apptoken;
    private String companyId;
    private SmartRefreshLayout refreshLayout;
    private LinearLayout scrollview;

    public static EyeFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        EyeFragment fragment = new EyeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_company_eye;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        webView = (WebView) rootView.findViewById(R.id.webview);
        refreshLayout = (SmartRefreshLayout) rootView.findViewById(R.id.refreshLayout);
        llLogin = rootView.findViewById(R.id.ll_eye_login);
        scrollview = rootView.findViewById(R.id.scrollView);
        ivSelectClick = (ImageView) rootView.findViewById(R.id.iv_select_click);
        TextView tv_assets = (TextView) rootView.findViewById(R.id.tv_assets);
        tv_assets.setOnClickListener(this);
        ivSelectClick.setOnClickListener(this);
        initWebView(webView);
        webView.addJavascriptInterface(new JavaScriptObject(getActivity()), "android");
        apptoken = AppConfig.getInstance().getString(SpKey.LOGIN_TOKEN, "");
        companyId = AppConfig.getInstance().getString(SpKey.COMPANYID, "");
        webView.loadUrl(HttpUrl.NEW_EYE_HOME
                + "?app_token=" + apptoken
                + "&company_id=" + companyId);

        CortpHeader refreshHeader = new CortpHeader(getActivity());
        refreshHeader.setMainColorBg(CUtils.getColor(getActivity(), R.color.color_main_color));
        refreshLayout.setRefreshHeader(refreshHeader);
        refreshLayout.setOnRefreshListener(this);
        if (UserInfo.isLogin()) {
            llLogin.setVisibility(View.GONE);
        } else {
            rootView.findViewById(R.id.btn_eye_login).setOnClickListener(this);
        }
    }

    /**
     * 初始化WebView
     */
    public void initWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        // 开启 DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        // 提供给H5的方法
        webView.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕大小
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过Js打开新窗口
        webView.clearCache(true);//支持缓存
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        apptoken = AppConfig.getInstance().getString(SpKey.LOGIN_TOKEN, "");
        companyId = AppConfig.getInstance().getString(SpKey.COMPANYID, "");
        if (webView != null)
            webView.loadUrl("javascript:RefreshHTML('" + apptoken +
                    "','" + companyId + "')");
        refreshlayout.finishRefresh(500);
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            scrollview.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_main_color));
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MsgEvent event) {
        switch (event.type) {
            case MsgEvent.LOGIN_SUCCESS://登录成功
                llLogin.setVisibility(View.GONE);
                refreshLayout.autoRefresh();
                if (isResumed()) // 页面可见出现弹框
                    AppUtil.checkAuditStatusDialog(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // -------------------- EventBus结束 --------------------- //
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_assets) {//资产总览
            EyeAssetActivity.startAction(getActivity());
        } else if (i == R.id.iv_select_click) {//我的
            if (UserInfo.isLogin() && onMenuOpenListener != null) {
                onMenuOpenListener.open();
            } else {
                toLogin();
            }
        } else if (i == R.id.btn_eye_login) {
            //llLogin显示，就能跳转登录
            toLogin();
        }
    }

    private MenuOpenListener onMenuOpenListener;

    public void setOnMenuOpenListener(MenuOpenListener onMenuOpenListener) {
        this.onMenuOpenListener = onMenuOpenListener;
    }

}