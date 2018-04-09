package com.horen.cortp.service.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;

import java.util.List;

/**
 * Created by HOREN on 2017/10/25.
 */

public class PhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PhotoAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (!TextUtils.isEmpty(item)) {
            Glide.with(mContext)
                    .load(item)
                    .placeholder(R.drawable.icon_error_normal)
                    .error(com.jaydenxiao.common.R.drawable.icon_error_normal)
                    .centerCrop()
//                    .bitmapTransform(new GlideRoundTransform(mContext, DisplayUtil.dip2px(2))) // 圆角
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into((ImageView) helper.getView(R.id.iv_photo));
        } else { // 占位图片
            Glide.with(mContext)
                    .load(R.drawable.service_add_photo_normal)
//                    .bitmapTransform(new GlideRoundTransform(mContext, DisplayUtil.dip2px(2))) // 圆角
                    .into((ImageView) helper.getView(R.id.iv_photo));
        }
    }
}
