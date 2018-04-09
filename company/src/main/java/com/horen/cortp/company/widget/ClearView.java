package com.horen.cortp.company.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.horen.cortp.company.R;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-4.
 */

public class ClearView extends AppCompatEditText {
    private boolean isListener = false;
    private Drawable mDrawable;

    public ClearView(Context context) {
        this(context, null);
    }

    public ClearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDrawable = getCompoundDrawables()[2];
        initListener();
        hideDelete();
    }

    private void initListener() {
        setOnFocusChangeListener(new DEFocuChangeListener(this));
        addTextChangedListener(new DETextChangeListener(this));
    }

    //显示删除按钮并且添加监听
    public void showDelete() {
        if (mDrawable == null) {
            mDrawable = getResources().getDrawable(R.drawable.login_empty);
        }
        final Drawable[] icons = getCompoundDrawables();
        icons[2] = mDrawable;
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        setCompoundDrawables(icons[0], icons[1], icons[2], icons[3]);
        isListener = true;
    }

    //隐藏删除按钮并且删除监听
    public void hideDelete() {
        final Drawable[] icons = getCompoundDrawables();
        icons[2] = null;
        setCompoundDrawables(icons[0], icons[1], icons[2], icons[3]);
        isListener = false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (getCompoundDrawables()[2] == null) {
            return super.onTouchEvent(event);
        }
        setError(null);
        if (!getText().toString().isEmpty()) {
            showDelete();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (isListener) {
                    //判断是否在范围内
                    if (x >= (getWidth() - getTotalPaddingRight()) && x <= (getWidth() - getPaddingRight()) && y >= getPaddingTop() && y <= getHeight() - getPaddingBottom()) {
                        //删除内容
                        setText("");
                        return true;
                    }
                } else {

                    return super.onTouchEvent(event);
                }
        }
        return super.onTouchEvent(event);
    }
}