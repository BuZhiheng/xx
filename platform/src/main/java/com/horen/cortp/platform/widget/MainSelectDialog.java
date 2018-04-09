package com.horen.cortp.platform.widget;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.horen.cortp.platform.R;
import com.jaydenxiao.common.commonutils.DisplayUtil;
/**
 * Created by HOREN on 2017/11/29.
 *
 * 首页 dialog 点击选择扫一扫/用箱体验/新箱构想
 *
 */
public abstract class MainSelectDialog extends Dialog implements View.OnClickListener {
    private OnSelectClickListener listener;
    private int index = 0;
    public MainSelectDialog(@NonNull Context context, OnSelectClickListener listener,int current) {
        this(context, R.style.DialogSelectMainScan);
        this.listener = listener;
        index = current;
    }
    public MainSelectDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
    protected MainSelectDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    public abstract void scan();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        window.requestFeature(window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_main_select);
        findViewById(R.id.ll_dialog_select_scan).setOnClickListener(this);
        findViewById(R.id.ll_dialog_select_firefly).setOnClickListener(this);
        findViewById(R.id.ll_dialog_select_experience).setOnClickListener(this);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = DisplayUtil.dip2px(10);
        lp.y = DisplayUtil.dip2px(50);
        window.setAttributes(lp);
        window.getAttributes().gravity = Gravity.RIGHT | Gravity.TOP;
        //如果gravity = LEFT lp.x 表示距离屏幕边框右侧距离
        //如果gravity = RIGHT lp.x 表示距离屏幕边框左侧距离
        findViewById(R.id.ll_dialog_select).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();//解决：触摸小箭头左侧dialog不会消失
                return false;
            }
        });
        window.getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();//解决：紧贴dialog四周有大概5dp的全透明边框，各种方法去不掉。。。无奈给decor加上touch
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        if (listener == null){
            return;
        }
        if (v.getId() == R.id.ll_dialog_select_scan){
            listener.clickScan();
        } else if (v.getId() == R.id.ll_dialog_select_experience){
            listener.clickExperience();
        } else if (v.getId() == R.id.ll_dialog_select_firefly){
            listener.clickFirefly();
        }
        dismiss();
    }
    public interface OnSelectClickListener {
        void clickScan();
        void clickFirefly();
        void clickExperience();
    }
    @Override
    public void show() {
        if (index == 0){
            super.show();
        } else {
            scan();
        }
    }
}