package com.horen.cortp.platform.activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.horen.cortp.platform.R;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.share.ShareDialog;
import com.jaydenxiao.common.share.Shared;
/**
 * Created by BuZhiheng on 2016/4/7.
 *
 * platform端网页带分享activity
 *
 * intent.putStringExtra(WEBVIEW_URL,"http://www.baidu.com")
 *
 */
public class PlatformWebViewActivity extends BaseActivity implements View.OnClickListener {
    private WebView webView;
    private final String TITLE_DISMISS = "detail/company";//URL里包含此字符串，notitle
    private final String TITLE_SHOW = "detail/solution";//URL里包含此字符串，显示title，显示share
    private final String TITLE_SHOW_NOSHARE1 = "consult/toapply";//URL里包含此字符串，显示title，不显示share
    private final String TITLE_SHOW_NOSHARE2 = "consult/toresult";//URL里包含此字符串，显示title，不显示share
    public static String WEBVIEW_URL = "WEBVIEW_URL";
    private Toolbar toolbar;
    private TextView textView;
    private ShareDialog dialog;
    private ImageView ivShare;
    private String url;
    @Override
    public int getLayoutId() {
        return R.layout.activity_platform_web_view;
    }
    @Override
    public void initPresenter() {
    }
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        textView = (TextView) findViewById(R.id.tool_bar_title_tv);
        ivShare = (ImageView) findViewById(R.id.right_iv);
        ivShare.setImageResource(R.drawable.ic_platform_share);
        ivShare.setOnClickListener(this);
        setSimpleToolbar(toolbar,textView,"");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView != null && webView.canGoBack()){
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
        webView = (WebView) findViewById(R.id.wv_platform);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        //设置 缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 开启 DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });//播放视频
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                checkTitle(url);
                startProgressDialog();
                webView.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                /**
                 * 当页面加载完毕，获取webview的title显示在标题栏
                 * */
                checkTitle(url);
                textView.setText(view.getTitle());
                stopProgressDialog();
            }
        });
        webView.addJavascriptInterface(this, "android");
        url = getIntent().getStringExtra(WEBVIEW_URL);
        if (url == null){
            return;
        }
        checkTitle(url);
        webView.loadUrl(url);
        startProgressDialog();
    }
    /**
     * 与H5约定好的
     * 当URL里包含detail/company字符串，则不显示title
     * 当URL里包含detail/solution字符串，则显示title显示分享
     * 否则显示title不显示分享
     * */
    private void checkTitle(String url){
//        ToastUitl.showShort(url);
        //http://blog.csdn.net/zdj_develop/article/details/64920441
        //https://www.jianshu.com/p/5cc3bd23be7b
        if (url.contains(TITLE_DISMISS)){
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            toolbar.setVisibility(View.GONE);
        } else if (url.contains(TITLE_SHOW)){
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            ivShare.setVisibility(View.VISIBLE);
        } else {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            ivShare.setVisibility(View.GONE);
        }
    }

    @Override
    protected void reLoadData() {
        super.reLoadData();
        checkTitle(url);
        webView.loadUrl(url);
        startProgressDialog();
    }

    /**
     * 当webview正在播放MP3或MP4时，关闭webview会继续播放声音
     * 加上此代码会解决这一问题
     * */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            webView.getClass().getMethod("onPause").invoke(webView, (Object[]) null);
            webView.removeAllViews();
            webView.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 点击物理返回键，若网页能够回退则回退，不能回退则关闭当前webview
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView != null && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * QQ分享需要获取onActivityResult来判断分享成功或失败
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog.onActivityResult(requestCode,resultCode,data);
        dialog.dismiss();
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.right_iv){
            webView.loadUrl("javascript:shareAction()");
        }
    }
    /**
     * 网页调用此方法，关闭当前webview
     * */
    @JavascriptInterface
    public void backAction(){
        finish();
    }
    /**
     * 通知用户去登录
     * */
    @JavascriptInterface
    public void favoriteAction(){
        ToastUitl.showShort(getString(R.string.share_nologin));
        ARouter.getInstance().build(CommonRoutePath.LOGIN_COMMON_ACTIVITY).navigation();
    }
    /**
     * js统一调用此方法，调起APP分享功能
     * */
    @JavascriptInterface
    public void shareAction(String title,String imgUrl,String desc,String url){
        Shared shared = new Shared();
        shared.setTitle(title);
        shared.setDesc(desc);
        shared.setImgUrl(imgUrl);
        shared.setUrl(url);
        dialog = new ShareDialog(this);
        dialog.setShared(shared);
        dialog.show();
//        ToastUitl.showShort(title+""+url);
    }
    /**
     * js调用此方法，跳转到下单页面
     * */
    @JavascriptInterface
    public void order(){
        ARouter.getInstance().build(CommonRoutePath.CREATE_ORDER_FRAME_ACTIVITY).navigation();
    }
    @JavascriptInterface
    public void gohome(){
        finish();
    }
}