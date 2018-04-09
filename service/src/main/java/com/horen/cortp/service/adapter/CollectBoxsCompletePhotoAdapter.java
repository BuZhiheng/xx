package com.horen.cortp.service.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.jaydenxiao.common.commonutils.GlideRoundTransform;
import com.jaydenxiao.common.imagePager.BigImagePagerActivity;

import java.util.List;

/**
 * Created by HOREN on 2017/10/25.
 */

public class CollectBoxsCompletePhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public CollectBoxsCompletePhotoAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        Glide.with(mContext)
                .load(item)
                .dontAnimate()
                .error(com.jaydenxiao.common.R.drawable.icon_error_normal)
                .bitmapTransform(new GlideRoundTransform(mContext, DisplayUtil.dip2px(2))) // 圆角
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.iv_photo));
        helper.setOnClickListener(R.id.iv_photo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigImagePagerActivity.startImagePagerActivity((Activity) mContext, mData, helper.getLayoutPosition());
            }
        });
    }
}
