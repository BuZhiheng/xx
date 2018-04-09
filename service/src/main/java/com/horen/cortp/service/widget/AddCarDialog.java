package com.horen.cortp.service.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.allen.library.SuperButton;
import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Created by HOREN on 2017/10/30.
 */

public class AddCarDialog extends BaseDialog<AddCarDialog> {

    private onAddLinstener onAddLinstener;

    private Context context;
    private SuperButton sbComplete;
    private EditText etCarNumber;

    public AddCarDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
//        showAnim(new BounceBottomEnter());
//        dismissAnim(new SlideBottomExit());

        View inflate = View.inflate(context, R.layout.dialog_add_car, null);

        inflate.setBackgroundDrawable(
                CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), dp2px(5)));
        sbComplete = (SuperButton) inflate.findViewById(R.id.sb_complete);
        etCarNumber = (EditText) inflate.findViewById(R.id.et_car_number);

        return inflate;
    }

    @Override
    public void setUiBeforShow() { // 根据集合的个数判断显示高度

        sbComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etCarNumber.getText().toString().trim())) {
                    ToastUitl.showShort(mContext.getString(R.string.car_number_not_null));
                } else {
                    onAddLinstener.onAddBtClickLinstener(etCarNumber.getText().toString().trim());
                    dismiss();
                }
            }
        });
    }

    public AddCarDialog setOnAddBtClickLinstener(onAddLinstener onAddBtClickLinstener) {
        this.onAddLinstener = onAddBtClickLinstener;
        return this;
    }

    public interface onAddLinstener {
        void onAddBtClickLinstener(String carNumber);
    }

}
