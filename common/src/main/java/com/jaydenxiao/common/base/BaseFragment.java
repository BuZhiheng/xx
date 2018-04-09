package com.jaydenxiao.common.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.dialog.widget.MaterialDialog;
import com.jaydenxiao.common.R;
import com.jaydenxiao.common.baseapp.CommonRoutePath;
import com.jaydenxiao.common.baserx.RxManager;
import com.jaydenxiao.common.commonutils.TUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.jaydenxiao.common.commonwidget.CortpFooter;
import com.jaydenxiao.common.commonwidget.LoadingDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * des:基类fragment
 * Created by xsf
 * on 2016.07.12:38
 */

/***************使用例子*********************/
//1.mvp模式
//public class SampleFragment extends BaseFragment<NewsChanelPresenter, NewsChannelModel>implements NewsChannelContract.View {
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//        mPresenter.setVM(this, mModel);
//    }
//
//    @Override
//    public void initView() {
//    }
//}
//2.普通模式
//public class SampleFragment extends BaseFragment {
//    @Override
//    public int getLayoutResource() {
//        return R.layout.activity_news_channel;
//    }
//
//    @Override
//    public void initPresenter() {
//    }
//
//    @Override
//    public void initView() {
//    }
//}
public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends SupportFragment {
    protected View rootView;
    public T mPresenter;
    public E mModel;
    public RxManager mRxManager;

    Unbinder unbinder1;

    private LoadingDialog loadingDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        mRxManager = new RxManager();
        unbinder1 = ButterKnife.bind(this, rootView);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
        }
        loadingDialog = new LoadingDialog();
        return rootView;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initPresenter();
        initView();
    }

    //获取布局文件
    protected abstract int getLayoutResource();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    protected abstract void initView();

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启加载进度条
     */
    public void startProgressDialog() {
        loadingDialog.showDialogForLoading(getActivity());
    }

    /**
     * 开启加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        loadingDialog.showDialogForLoading(getActivity(), msg, true);
    }

    /**
     * 停止加载进度条
     */
    public void stopProgressDialog() {
        loadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(), R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error, R.drawable.ic_wifi_off);
    }

    /**
     * 操作成功提醒
     */
    public void showSuccessTip() {
//        ToastUitl.showToastWithImg(getString(R.string.success), R.drawable.icon_success_new);
        showShortToast(getString(R.string.success));
    }

    public void showSuccessTip(String msg) {
//        ToastUitl.showToastWithImg(msg, R.drawable.icon_success_new);
        showShortToast(msg);
    }

    /**
     * 操作失败提醒
     */
    public void showErrorToast() {
//        ToastUitl.showToastWithImg(getString(R.string.error), R.drawable.icon_error);
        showShortToast(getString(R.string.error));
    }

    public void showErrorToast(String error) {
//        ToastUitl.showToastWithImg(error, R.drawable.icon_error);
        showShortToast(error);
    }

    /**
     * Show message dialog.
     *
     * @param message message.
     */
    public void showMessageDialog(String message) {
        new MaterialDialog(getContext())
                .btnNum(1)
                .title(getString(R.string.hint))
                .content(message)//
                .btnText(getString(R.string.know))//
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.onDestroy();
        unbinder1.unbind();
        mRxManager.clear();
    }

    /**
     * 设置刷新控件
     */
    public void initRefresh(SmartRefreshLayout refreshLayout) {
        refreshLayout.setEnableHeaderTranslationContent(false); // 内容不偏移
        refreshLayout.setRefreshFooter(new CortpFooter(getActivity()));
        refreshLayout.setEnableAutoLoadmore(true);
    }
    public void toLogin(){
        //跳转登录页面_登录成功之后关闭该页面
        Bundle bundle = new Bundle();
        bundle.putBoolean("isJump", false);
        ARouter.getInstance().build(CommonRoutePath.LOGIN_COMMON_ACTIVITY).with(bundle).navigation();
    }
}