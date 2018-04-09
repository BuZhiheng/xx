package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.activity.ExperienceCreateActivity;
import com.horen.cortp.platform.activity.PlatformWebViewActivity;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.Fireflys;
import com.horen.cortp.platform.platformutils.PlatformUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOREN on 2017/12/01.
 *
 * 萤火虫
 *
 * 新箱构想
 *
 */
public class FireflysAdapter extends BaseRecycleViewAdapter {
    private Context context;
    private List<Fireflys> datas = new ArrayList<>();
    public FireflysAdapter(Context context){
        super(context);
        this.context = context;
    }
    public void setData(List<Fireflys> datas){
        this.datas = datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }
    @Override
    public int getItemType(int position) {
        return 0;
    }
    @Override
    public RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_fireflys_item,parent,false);
        return new FireflysViewHolder(view);
    }
    @Override
    public void onBindView(RecyclerView.ViewHolder holder, int position) {
        final Fireflys fireflys = datas.get(position);
        FireflysViewHolder fireflysViewHolder = (FireflysViewHolder) holder;
        fireflysViewHolder.tvTitle.setText(fireflys.firefly_title);
        fireflysViewHolder.tvDesc.setText(fireflys.firefly_text);
        if ("0".equals(fireflys.tag)){
            fireflysViewHolder.tvTag.setText(context.getString(R.string.platform_inreview));
            fireflysViewHolder.imageView.setVisibility(View.GONE);
            fireflysViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ExperienceCreateActivity.class);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,false);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE,false);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_ID,"");
                    context.startActivity(intent);
                }
            });
        } else {
            fireflysViewHolder.tvTag.setText(context.getString(R.string.platform_introduced));
            fireflysViewHolder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context).load(fireflys.firefly_image).into(fireflysViewHolder.imageView);
            fireflysViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlatformWebViewActivity.class);
                    intent.putExtra(PlatformWebViewActivity.WEBVIEW_URL, PlatformUtil.getUrlH5AddDevicetoken(fireflys.firefly_url));
                    context.startActivity(intent);
                }
            });
        }
    }
    class FireflysViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView tvTitle;
        TextView tvDesc;
        TextView tvTag;
        ImageView imageView;
        public FireflysViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.ll_firefly_item);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_firefly_item_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_firefly_item_desc);
            tvTag = (TextView) itemView.findViewById(R.id.tv_firefly_item_tag);
            imageView = (ImageView) itemView.findViewById(R.id.iv_firefly_item1);
        }
    }
}