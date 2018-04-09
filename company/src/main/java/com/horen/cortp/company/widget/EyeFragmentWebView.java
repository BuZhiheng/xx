package com.horen.cortp.company.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Created by HOREN on 2018/3/22.
 *
 * 解决：天眼scrollview和webview地图滑动冲突
 */
public class EyeFragmentWebView extends WebView {
    float x1 = 0,y1 = 0,x2,y2;
    public EyeFragmentWebView(Context context) {
        super(context);
    }
    public EyeFragmentWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public EyeFragmentWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int i = event.getPointerCount();
        if (i > 1){
            //双指触控，操作地图拦截事件
            requestDisallowInterceptTouchEvent(true);
        } else {
            //单指触控，左右滑动，拦截事件，上下滑动，释放事件
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE) {
                //当手指离开的时候
                x2 = event.getX();
                y2 = event.getY();
                if(x1 - x2 > 10 || x2 - x1 > 10) {
                    //左右滑动，拦截事件
                    requestDisallowInterceptTouchEvent(true);
                } else if(y1 - y2 > 200 || y2-y1 > 200) {
                    //上下滑动，释放事件
                    requestDisallowInterceptTouchEvent(false);
                }
            }
        }
        return super.onTouchEvent(event);
    }


}