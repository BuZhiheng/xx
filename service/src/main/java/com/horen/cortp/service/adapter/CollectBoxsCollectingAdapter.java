package com.horen.cortp.service.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by HOREN on 2017/10/20.
 */

public class CollectBoxsCollectingAdapter extends BaseQuickAdapter<SendBoxOrderDetailBean.ProductsBean, BaseViewHolder> {
    private LinkedHashMap<Integer, SendBoxOrderDetailBean.ProductsBean> map;
    private boolean isCustomInput;

    public CollectBoxsCollectingAdapter(int layoutResId, List<SendBoxOrderDetailBean.ProductsBean> data) {
        super(layoutResId, data);
        map = new LinkedHashMap<>();
    }

    @Override
    protected void convert(final BaseViewHolder helper, SendBoxOrderDetailBean.ProductsBean item) {
        helper.setText(R.id.tv_box_type, item.getProduct_name() + " " + item.getProduct_color() + " " + item.getReceive_qty() + "个");
        final AppCompatCheckBox checkBox = helper.getView(R.id.checkbox);
        final EditText editText = helper.getView(R.id.et_number);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (isCustomInput) {// checkBOx没有选中，用户主动输入Edittext
                        mData.get(helper.getLayoutPosition()).setReject_qty(Integer.valueOf(editText.getText().toString().trim()));
                        map.put(helper.getLayoutPosition(), mData.get(helper.getLayoutPosition())); // 记录选中的个数
                        isCustomInput = false;
                    } else {
                        map.put(helper.getLayoutPosition(), mData.get(helper.getLayoutPosition())); // 记录选中的个数
                        helper.setText(R.id.et_number, mData.get(helper.getLayoutPosition()).getReceive_qty() + ""); // 默认为planQty的个数
                    }
                } else {
                    map.remove(helper.getLayoutPosition()); // 移除未选中
                    helper.setText(R.id.et_number, "");
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) { // 没有内容，取消选中
                    checkBox.setChecked(false);
                    map.remove(helper.getLayoutPosition()); // 移除未选中
                } else {
                    if (!checkBox.isChecked()) { // check没有选中，用户去改动EditText的内容
                        isCustomInput = true; // 用户主动选中
                        checkBox.setChecked(true); // 选中checkBox
                    }

                    // 判断输入的值不能大于总个数
                    if (Integer.valueOf(s.toString()) > mData.get(helper.getLayoutPosition()).getReceive_qty()) {
                        ToastUitl.showShort(mContext.getString(R.string.not_large) + mData.get(helper.getLayoutPosition()).getReceive_qty() + mContext.getString(R.string.individual));
                        editText.setText(mData.get(helper.getLayoutPosition()).getReceive_qty() + "");
                        editText.setSelection(editText.getText().toString().length()); // 移动光标到最后
                    } else if (Integer.valueOf(s.toString()) == 0) {
                        editText.setText("");
                        ToastUitl.showShort(R.string.input_sure_number);
                    } else {
                        mData.get(helper.getLayoutPosition()).setReject_qty(Integer.valueOf(s.toString()));
                        map.put(helper.getLayoutPosition(), mData.get(helper.getLayoutPosition())); // 记录选中的个数
                    }
                }
            }
        });

        checkBox.setChecked(true);  // 默认全选
    }


    public LinkedHashMap<Integer, SendBoxOrderDetailBean.ProductsBean> getSelectSplitBox() {
        return map;
    }
}
