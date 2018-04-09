package com.horen.cortp.service.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.dialog.widget.base.BaseDialog;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.List;

/**
 * Created by HOREN on 2017/10/30.
 */

public class CustomBaseDialog extends BaseDialog<CustomBaseDialog> {

    RecyclerView recyclerView;
    SuperButton tvSubmit;
    SuperButton stb_left;
    LinearLayout llContainer;
    private onSubmitLinstener onSubmitLinstener;
    private List<SendBoxOrderDetailBean.ProductsBean> list;

    private Context context;

    public CustomBaseDialog(Context context, List<SendBoxOrderDetailBean.ProductsBean> list) {
        super(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
//        showAnim(new BounceBottomEnter());
//        dismissAnim(new SlideBottomExit());

        View inflate = View.inflate(context, R.layout.dialog_send_box_delivery, null);

        recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        tvSubmit = (SuperButton) inflate.findViewById(R.id.tv_submit);
        stb_left = (SuperButton) inflate.findViewById(R.id.stb_left);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new BaseQuickAdapter<SendBoxOrderDetailBean.ProductsBean, BaseViewHolder>(R.layout.item_exception_rejection, list) {
            @Override
            protected void convert(final BaseViewHolder helper, SendBoxOrderDetailBean.ProductsBean item) {
                helper.setText(R.id.tv_box_type, item.getProduct_name() + " " + item.getProduct_color() + " " + item.getReceive_qty() + "个");
//                helper.setText(R.id.tv_box_color, item.getProduct_color());
//                helper.setText(R.id.tv_box_number, item.getReceive_qty() + "个");
                // edittext监听
                final EditText etNumber = helper.getView(R.id.et_number);
                if (item.getCurrentNumber() != 0) { // 如果之前存在输入的数据，重新显示
                    etNumber.setText(item.getCurrentNumber() + "");
                } else {
                    etNumber.setText("");
                }
                etNumber.setText(item.getReject_qty() > 0 ? item.getReject_qty() + "" : "");
                etNumber.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (!TextUtils.isEmpty(s.toString())) {
                            // 判断输入的值不能大于总个数
                            if (Integer.valueOf(s.toString()) > mData.get(helper.getLayoutPosition()).getReceive_qty() - 1) {
                                ToastUitl.showShort(context.getString(R.string.number_tip));
                                etNumber.setText(mData.get(helper.getLayoutPosition()).getReceive_qty() - 1 + "");
                                etNumber.setSelection(etNumber.getText().toString().length()); // 移动光标到最后
                            } else if (Integer.valueOf(s.toString()) == 0) {
                                if (mData.get(helper.getLayoutPosition()).getReceive_qty() == 1) { // 如果货物列表只有1个，那么不能拒收
                                    etNumber.setText("");
                                    ToastUitl.showShort(context.getString(R.string.number_tip));
                                } else {
                                    etNumber.setText("");
                                    ToastUitl.showShort(context.getString(R.string.input_sure_number));
                                }
                            } else {
                                mData.get(helper.getLayoutPosition()).setReject_qty(Integer.valueOf(s.toString()));
//                                mData.get(helper.getLayoutPosition()).setTotal_reject_qty(Integer.valueOf(s.toString()));
                                mData.get(helper.getLayoutPosition()).setCurrentNumber(Integer.valueOf(s.toString())); // 将当前条目输入的个数记录到对象中，用于回显
                            }
                        } else {
                            mData.get(helper.getLayoutPosition()).setCurrentNumber(0);
                            mData.get(helper.getLayoutPosition()).setReject_qty(0);
                        }
                    }
                });
            }
        });

        return inflate;
    }

    @Override
    public void setUiBeforShow() { // 根据集合的个数判断显示高度
        ViewGroup.LayoutParams lp;
        lp = recyclerView.getLayoutParams();
        // 异常拒收头部：45dp
        // 顶部确定按钮：45dp
        // Item高度：45dp
        // 获取Dialog的宽度 px
        float screenWidth = DisplayUtil.getScreenWidth(BaseApplication.getAppContext()) * 0.85f;
        if (list.size() * DisplayUtil.dip2px(50) + DisplayUtil.dip2px(50) + DisplayUtil.dip2px(50) > screenWidth) { // 小于dialog宽度，设置自适应高度
            lp.width = (int) screenWidth;
            lp.height = (int) screenWidth - DisplayUtil.dip2px(50) - DisplayUtil.dip2px(50);
            recyclerView.setLayoutParams(lp);
        }

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断异常拒收数量是否都为空
                boolean isNoReject = true; // 如果没有输入异常拒收，不可以点确认
                for (SendBoxOrderDetailBean.ProductsBean productsBean : list) {
                    if (productsBean.getReject_qty() != 0)
                        isNoReject = false; // 存在异常拒收货物
                }

//                if (isNoReject) { // 没有输入异常拒收数量，弹框消失
////                    ToastUitl.showShort(context.getString(R.string.reject_number_no_null));
//                    dismiss();
//                    return;
//                }

                if (onSubmitLinstener != null)
                    onSubmitLinstener.onSubmitBtClickLinstener(list);
                dismiss();
            }
        });

        stb_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public CustomBaseDialog setOnSubmitBtClickLinstener(onSubmitLinstener onSubmitBtClickLinstener) {
        this.onSubmitLinstener = onSubmitBtClickLinstener;
        return this;
    }

    public interface onSubmitLinstener {
        void onSubmitBtClickLinstener(List<SendBoxOrderDetailBean.ProductsBean> list);
    }

}
