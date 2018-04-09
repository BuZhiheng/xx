package com.horen.cortp.service.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.horen.cortp.service.R;
import com.horen.cortp.service.bean.DeliverPeopleBean;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HOREN on 2017/12/1.
 */

public class DriverAdapter extends BaseQuickAdapter<DeliverPeopleBean.DataBean, BaseViewHolder> {

    private boolean isDelete;

    public boolean isDelete() {
        return isDelete;
    }

    private DriverAdapter.DeleteLinstener onDeleteListner;

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public DriverAdapter(int layoutResId, List<DeliverPeopleBean.DataBean> data) {
        super(layoutResId, data);
    }

    public void setOnDeleteListner(DriverAdapter.DeleteLinstener onDeleteListner) {
        this.onDeleteListner = onDeleteListner;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final DeliverPeopleBean.DataBean item) {
        final SwipeMenuLayout swipeMenuLayout = helper.getView(R.id.swipe_layout);
        if (helper.getLayoutPosition() == 0) {
            helper.getConvertView().setPadding(0, DisplayUtil.dip2px(10), 0, 0);
        }
        helper.setText(R.id.tv_contact_name, mContext.getString(R.string.contacts) + item.getDriver_name());
        helper.setText(R.id.tv_contact_phone, mContext.getString(R.string.contact_number) + item.getDriver_tel());

        final AppCompatCheckBox checkBox = helper.getView(R.id.checkbox);
        if (isDelete) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            item.setSelect(false); // 状态置为不删除
            checkBox.setVisibility(View.GONE);
        }
        // 回显状态
        checkBox.setButtonDrawable(item.isSelect() ? R.drawable.icon_driver_select : R.drawable.icon_driver_normal);
        // 监听radioButton的状态改变
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setSelect(isChecked);
                checkBox.setButtonDrawable(item.isSelect() ? R.drawable.icon_driver_select : R.drawable.icon_driver_normal);
            }
        });

        helper.setOnClickListener(R.id.stb_default, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeMenuLayout.smoothClose();
            }
        });
        helper.setOnClickListener(R.id.sbt_change, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeMenuLayout.smoothClose();
            }
        });
        helper.setOnClickListener(R.id.stb_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeMenuLayout.smoothClose();
                // 出现确认弹框
                showDeleteDialog(helper.getLayoutPosition());
            }
        });


        helper.setOnClickListener(R.id.content, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDelete) { // 删除过程中
                    item.setSelect(!item.isSelect());
                    checkBox.setButtonDrawable(item.isSelect() ? R.drawable.icon_driver_select : R.drawable.icon_driver_normal);
                }
            }
        });

    }

    /**
     * 批量删除多个
     */
    public void deleteMulti() {
        List<String> deleteId = new ArrayList<>();
        List<DeliverPeopleBean.DataBean> list = new ArrayList<>();
        list.addAll(mData); // 复制集合数据
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isSelect()) {
                deleteId.add(mData.get(i).getDriver_id()); // 记录需要删除的司机id
                list.remove(mData.get(i)); // 移除需要删除的联系人
            }
        }
        // 设置新数据
        setNewData(list);

        isDelete = false; // 勾选按钮隐藏

        if (onDeleteListner != null) onDeleteListner.onDeleteListner(mData.size(), deleteId);

        notifyDataSetChanged();
    }

    public interface DeleteLinstener {
        void onDeleteListner(int count, List<String> deleteId);
    }

    private void showDeleteDialog(final int layoutPosition) {
        final NormalDialog dialog = new NormalDialog(mContext);
        dialog.isTitleShow(false)//
                .cornerRadius(5)//
                .content(mContext.getString(R.string.capacity_resourc_delete_vehicle))//
                .contentGravity(Gravity.CENTER)//
                .btnTextSize(15.5f, 15.5f)//
                .widthScale(0.85f)//
                .btnText(mContext.getString(R.string.cancle_service), mContext.getString(R.string.delete_driver_confirm))
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                        // 调用系统的拨打电话页面
                        // 移除当前条目
                        // 删除回调
                        if (onDeleteListner != null)
                            onDeleteListner.onDeleteListner(mData.size() - 1, Arrays.asList(mData.get(layoutPosition).getDriver_id()));
                        mData.remove(layoutPosition);
                        notifyItemRemoved(layoutPosition);
                    }
                });
    }
}
