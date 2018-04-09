package com.horen.cortp.service.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.horen.cortp.service.bean.PicsInfoBean;
import com.horen.cortp.service.bean.RepairDetailBean;

import java.util.List;

/**
 * Created by HOREN on 2017/10/25.
 */

public class RepairSubmitAdapter extends BaseQuickAdapter<PicsInfoBean, BaseViewHolder> {

    private int mHeight = 0;

    public RepairSubmitAdapter(int layoutResId, List<PicsInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PicsInfoBean item) {
        RecyclerView recyclerPhoto = helper.getView(R.id.recycler_photo);
        // 损坏类型
        helper.setText(R.id.tv_damaged_type, TextUtils.isEmpty(item.getDamageType()) ? mContext.getString(R.string.service_null) : item.getDamageType());
        helper.setText(R.id.tv_remake, item.getDamageRemark()); // 备注
        helper.setText(R.id.tv_box_number, item.getReference_id()); // 箱号
        recyclerPhoto.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        // 添加一个假数据，显示图片
        CollectBoxsCompletePhotoAdapter adapter = new CollectBoxsCompletePhotoAdapter(R.layout.service_item_iv, item.getDamagePics());
        recyclerPhoto.setAdapter(adapter);
        helper.setVisible(R.id.ll_content, item.isOpen() ? true : false); // 是否展示信息
        helper.setImageResource(R.id.iv_open, item.isOpen() ? R.drawable.service_icon_open : R.drawable.service_icon_colse);

        final LinearLayout llContent = helper.getView(R.id.ll_content);
        llContent.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // TODO Auto-generated method stub
                        llContent.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        mHeight = llContent.getHeight();
                    }
                });

        helper.setOnClickListener(R.id.ll_open, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setOpen(!item.isOpen());
                helper.setVisible(R.id.ll_content, item.isOpen() ? true : false); // 是否展示信息
                if (item.isOpen()) {
                    animOpen(llContent);
                } else {
                    animClose(llContent);
                }
                helper.setImageResource(R.id.iv_open, item.isOpen() ? R.drawable.service_icon_open : R.drawable.service_icon_colse);
            }
        });
    }


    private void animOpen(final View view) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator va = createDropAnim(view, 0, mHeight);
        va.start();
    }

    private void animClose(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator va = createDropAnim(view, origHeight, 0);
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        va.start();
    }

    /**
     * 使用动画的方式来改变高度解决visible不一闪而过出现
     *
     * @param view
     * @param start 初始状态值
     * @param end   结束状态值
     * @return
     */
    private ValueAnimator createDropAnim(final View view, int start, int end) {
        ValueAnimator va = ValueAnimator.ofInt(start, end);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();//根据时间因子的变化系数进行设置高度
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);//设置高度
            }

        }
        );

        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                view.setLayoutParams(layoutParams);//设置高度
            }
        });
        return va;
    }
}
