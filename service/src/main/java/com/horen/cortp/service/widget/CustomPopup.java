package com.horen.cortp.service.widget;

/**
 * Created by HOREN on 2017/10/30.
 */

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyco.dialog.view.TriangleView;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.basebean.OrderPopupBean;
import com.jaydenxiao.common.commonutils.DisplayUtil;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class CustomPopup extends BasePopupWindow {

    private RecyclerView recyclerView;
    private List<OrderPopupBean> list;
    private onSelectLinstener onSelectLinstener;
    private TriangleView mTriangleView;

    private RelativeLayout.LayoutParams mTriangleLayoutParams;

    public CustomPopup(Activity context, List<OrderPopupBean> list, onSelectLinstener onSelectLinstener) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.list = list;
        this.onSelectLinstener = onSelectLinstener;
        setAdapter(list);

        mTriangleLayoutParams = (RelativeLayout.LayoutParams) mTriangleView.getLayoutParams();
        mTriangleView.setColor(Color.parseColor("#BB000000"));
        mTriangleView.setGravity(Gravity.TOP);

        mTriangleLayoutParams.width = DisplayUtil.dip2px(20);
        mTriangleLayoutParams.height = DisplayUtil.dip2px(10);
        mTriangleView.setLayoutParams(mTriangleLayoutParams);
    }

    private void setAdapter(List<OrderPopupBean> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(new BaseQuickAdapter<OrderPopupBean, BaseViewHolder>(R.layout.item_popup_select, list) {
            @Override
            protected void convert(BaseViewHolder helper, final OrderPopupBean item) {
                helper.setText(R.id.tv_select, item.getName());
                helper.setOnClickListener(R.id.tv_select, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onSelectLinstener != null)
                            onSelectLinstener.onItemSelectLinstener(item.getName(), item.getType());

                        dismiss();
                    }
                });
            }
        });
    }


    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }

    @Override
    public Animator initShowAnimator() {
        return null;
    }

    @Override
    public Animator initExitAnimator() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        View popupById = createPopupById(R.layout.popup_select);
        recyclerView = (RecyclerView) popupById.findViewById(R.id.recycler_view);
        mTriangleView = (TriangleView) popupById.findViewById(com.flyco.dialog.R.id.triangle_view);
        return popupById;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    public interface onSelectLinstener {
        void onItemSelectLinstener(String name, String type);
    }

    @Override
    public void showPopupWindow(View v) {
        super.showPopupWindow(v);
    }
}
