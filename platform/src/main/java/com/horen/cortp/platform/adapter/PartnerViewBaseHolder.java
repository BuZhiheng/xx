package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.horen.cortp.platform.activity.PlatformWebViewActivity;
import com.horen.cortp.platform.bean.Partner;
import com.horen.cortp.platform.platformutils.PlatformUtil;
/**
 * Created by BuZhiheng on 2017/12/13.
 *
 * 封装盟友item viewholder
 * (布局文件大小可能不一样，需要继承此viewholder 构造方法绑定view即可)
 *
 * 首页万箱盟友 全部盟友 共用此viewholder
 *
 * 调用setData方法 绑定数据
 *
 */
public class PartnerViewBaseHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    ImageView iv;
    TextView tvName;
    TextView tvDesc;
    TextView tvCountry;
    public PartnerViewBaseHolder(View itemView) {
        super(itemView);
    }
    public void setData(final Context context, final Partner partner){
        tvName.setText(partner.company_name);
        if (!TextUtils.isEmpty(partner.company_tag) && partner.company_tag.length() > 30){
            tvDesc.setText(partner.company_tag.substring(0,29)+"...");
        } else {
            tvDesc.setText(partner.company_tag);
        }
        tvCountry.setText(partner.company_country);
        Glide.with(context).load(partner.company_logo).into(iv);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlatformWebViewActivity.class);
                intent.putExtra(PlatformWebViewActivity.WEBVIEW_URL, PlatformUtil.getPartnerUrlH5(partner.h5_url));
                context.startActivity(intent);
            }
        });
    }
}