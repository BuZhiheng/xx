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

public class AddDriverDialog extends BaseDialog<AddDriverDialog> {

    private onAddLinstener onAddLinstener;

    private Context context;
    private SuperButton sbComplete;
    private EditText etName;
    private EditText etPhone;

    public AddDriverDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
//        showAnim(new BounceBottomEnter());
//        dismissAnim(new SlideBottomExit());

        View inflate = View.inflate(context, R.layout.dialog_add_driver, null);

        inflate.setBackgroundDrawable(
                CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), dp2px(5)));
        sbComplete = (SuperButton) inflate.findViewById(R.id.sb_complete);
        etName = (EditText) inflate.findViewById(R.id.et_name);
        etPhone = (EditText) inflate.findViewById(R.id.et_phone);

        return inflate;
    }

    @Override
    public void setUiBeforShow() { // 根据集合的个数判断显示高度

        sbComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText().toString().trim()) || TextUtils.isEmpty(etPhone.getText().toString().trim())) {
                    ToastUitl.showShort(context.getString(R.string.service_contract_info_null));
                } else {
                    onAddLinstener.onAddBtClickLinstener(etName.getText().toString().trim(), etPhone.getText().toString().trim());
                    dismiss();
                }
            }
        });
    }

    public AddDriverDialog setOnAddBtClickLinstener(onAddLinstener onAddBtClickLinstener) {
        this.onAddLinstener = onAddBtClickLinstener;
        return this;
    }

    public interface onAddLinstener {
        void onAddBtClickLinstener(String name, String phone);
    }

}
