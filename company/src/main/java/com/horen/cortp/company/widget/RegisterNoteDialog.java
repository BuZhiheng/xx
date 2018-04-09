package com.horen.cortp.company.widget;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.flyco.dialog.widget.base.BaseDialog;
import com.horen.cortp.company.R;
import com.jaydenxiao.common.commonwidget.CustomMsgDialog;
import com.jaydenxiao.common.imagePager.BigImagePagerActivity;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOREN on 2018/3/5.
 */
public class RegisterNoteDialog extends BaseDialog<CustomMsgDialog> {
    public RegisterNoteDialog(Context context) {
        super(context);
    }
    public RegisterNoteDialog(Context context, boolean isPopupStyle) {
        super(context, isPopupStyle);
    }
    @Override
    public View onCreateView() {
        widthScale(0.85f); // 屏幕宽度
        View inflate = View.inflate(mContext, R.layout.dialog_register_note, null);
        inflate.findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        inflate.findViewById(R.id.ll_dialog_register_company_p).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            toPhoto(0);
            }
        });
        inflate.findViewById(R.id.ll_dialog_register_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPhoto(1);
            }
        });
        return inflate;
    }
    private void toPhoto(int position){
        List<Integer> ids = new ArrayList<>();
        ids.add(R.drawable.icon_business_example);
        ids.add(R.drawable.icon_authentication_example);
        BigImagePagerActivity.startImageForResouceId((Activity) mContext,ids,position);
    }
    @Override
    public void setUiBeforShow() {
        setCanceledOnTouchOutside(false);
    }
}