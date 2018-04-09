package com.jaydenxiao.common.commonutils;

/**
 * Created by HOREN on 2017/10/25.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.jaydenxiao.common.R;

/**
 * Author:Hikin
 * Data:2016/12/12
 */

public class GlideUtils {
    public static void loadBanner(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.icon_error_normal)
                .error(R.drawable.icon_error_banner)
                .crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public static void loadUrl(Context context, String url, ImageView iv) {    //使用Glide，使用占位图
        Glide.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.drawable.icon_error_normal)
                .error(R.drawable.icon_error_normal)
                .into(iv);
    }

    public static void loadCenterCrop(Context context, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图
        Glide.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(R.drawable.icon_error_normal)
                .error(R.drawable.icon_error_normal)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    /**
     * 加载圆角图标
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void loadRoundIV(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.icon_error_normal)
                .error(R.drawable.icon_error_normal)
                .transform(new CenterCrop(context), new GlideRoundTransform(context, 4)
                )
                .into(iv);
    }
    public static void loadRoundIV(Context context, String url, ImageView iv, int dp) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.icon_error_normal)
                .error(R.drawable.icon_error_normal)
                .transform(new GlideRoundTransform(context, dp)
                )
                .into(iv);
    }
    public static void load(Activity activity, String url, ImageView iv) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!activity.isDestroyed()) {
                Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
            }
        }
    }


    public static void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    public static void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (!activity.isDestroyed()) {
                Glide.with(activity).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
            }
        }
    }
}