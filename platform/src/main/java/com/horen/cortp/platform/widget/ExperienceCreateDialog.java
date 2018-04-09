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
public class ExperienceCreateDialog extends Dialog implements View.OnClickListener {
    private OnSelectClickListener listener;
    public ExperienceCreateDialog(@NonNull Context context, OnSelectClickListener listener) {
        this(context, R.style.DialogExperienceCreate);
        this.listener = listener;
    }
    public ExperienceCreateDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
    protected ExperienceCreateDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        window.requestFeature(window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_experience_create);
        findViewById(R.id.ll_dialog_experience_txt).setOnClickListener(this);
        findViewById(R.id.ll_dialog_experience_camera).setOnClickListener(this);
        findViewById(R.id.ll_dialog_experience_pic).setOnClickListener(this);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.y = DisplayUtil.dip2px(60);
        window.setAttributes(lp);
        window.getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        //如果gravity = LEFT lp.x 表示距离屏幕边框右侧距离
        //如果gravity = RIGHT lp.x 表示距离屏幕边框左侧距离
        findViewById(R.id.fl_dialog_experience_create).setOnTouchListener(new View.OnTouchListener() {
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
        window.setWindowAnimations(R.style.DialogExperienceCreateAnimation);
    }
    @Override
    public void onClick(View v) {
        if (listener == null){
            return;
        }
        if (v.getId() == R.id.ll_dialog_experience_txt){
            listener.clickTxt();
        } else if (v.getId() == R.id.ll_dialog_experience_camera){
            listener.clickCamera();
        } else if (v.getId() == R.id.ll_dialog_experience_pic){
            listener.clickPic();
        }
        dismiss();
    }
    public interface OnSelectClickListener {
        void clickTxt();
        void clickCamera();
        void clickPic();
    }
}