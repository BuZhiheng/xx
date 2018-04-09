package com.horen.cortp.company.widget;

import android.view.View;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-6.
 */

public class DEFocuChangeListener implements View.OnFocusChangeListener {
    private ClearView mDEView;

    public DEFocuChangeListener(ClearView DEView) {
        mDEView = DEView;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (!mDEView.getText().toString().isEmpty()) {
                mDEView.hideDelete();
                mDEView.showDelete();
            } else {
                mDEView.hideDelete();
            }
        } else {
            mDEView.hideDelete();
        }
    }
}
