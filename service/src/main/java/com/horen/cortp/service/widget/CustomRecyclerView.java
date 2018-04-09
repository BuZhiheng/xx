package com.horen.cortp.service.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by HOREN on 2017/12/26.
 */

public class CustomRecyclerView extends RecyclerView {
    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int mLastX, mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        //Log.i(TAG, "onInterceptTouchEvent() called with: ev = [" + ev + "]");
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (isHorizontalScroll(x, y)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;


        return super.dispatchTouchEvent(ev);
    }

    //是否在水平滑动
    private boolean isHorizontalScroll(int x, int y) {
        return Math.abs(y - mLastY) < Math.abs(x - mLastX);
    }
}
