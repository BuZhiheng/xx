package com.horen.cortp.company.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;

/**
 * Created by HOREN on 2018/3/22.
 */
public class EyeFragmentScrollView extends ScrollView {
    public EyeFragmentScrollView(Context context) {
        super(context);
    }
    public EyeFragmentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public EyeFragmentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        View view = getChildAt(0);
//        if (view == null)
//            return;
//        int diff = (view.getBottom() - (getHeight() + getScrollY()));
//                System.out.println("t= " + t
//                + ",view.gethhhhhhhhh()=" + view.getHeight()
//                + ",this.getHeight()=" + getHeight()
//                + ",this.getScrollY()=" + getScrollY());
//        if (diff == 0) {
//            //滑动到底部
//            requestDisallowInterceptTouchEvent(false);
//        } else {
//            requestDisallowInterceptTouchEvent(true);
//        }
    }
}