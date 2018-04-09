package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.horen.cortp.platform.activity.PlatformWebViewActivity;
import com.horen.cortp.platform.bean.HomeBanner;
import com.jaydenxiao.common.commonutils.GlideUtils;

/**
 * Created by HOREN on 2017/11/23.
 */
public class HomeBannerAdapter implements Holder<HomeBanner> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
    @Override
    public void UpdateUI(final Context context, final int position, final HomeBanner data) {
        GlideUtils.loadBanner(context,data.banner_image,imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(data.banner_url)){
                    Intent intent = new Intent(context, PlatformWebViewActivity.class);
                    intent.putExtra(PlatformWebViewActivity.WEBVIEW_URL,data.banner_url);
                    context.startActivity(intent);
                }
            }
        });
    }
}