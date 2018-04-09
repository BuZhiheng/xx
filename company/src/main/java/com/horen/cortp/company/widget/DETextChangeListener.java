package com.horen.cortp.company.widget;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * email :brioal@foxmail.com
 * github : https://github.com/Brioal
 * Created by brioal on 17-1-6.
 */

public class DETextChangeListener implements TextWatcher {
    private ClearView mDEView;

    public DETextChangeListener(ClearView DEView) {
        mDEView = DEView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String text = mDEView.getText().toString();
        if (text.isEmpty()) {
            mDEView.hideDelete();
        } else {
            mDEView.hideDelete();
            mDEView.showDelete();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
