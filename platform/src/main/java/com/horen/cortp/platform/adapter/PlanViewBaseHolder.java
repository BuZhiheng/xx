package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.activity.PlatformWebViewActivity;
import com.horen.cortp.platform.bean.Plan;
import com.horen.cortp.platform.platformutils.PlatformUtil;
import com.horen.cortp.platform.widget.FlowLayout;
import com.jaydenxiao.common.commonutils.GlideUtils;

/**
 * Created by BuZhiheng on 2017/12/13.
 *
 * 封装方案item viewholder
 * (布局文件大小可能不一样，需要继承此viewholder 构造方法绑定view即可)
 *
 * 首页热门方案 全部方案 全部热门方案 共用此viewholder
 *
 * 调用setData方法 绑定数据
 *
 */
public class PlanViewBaseHolder extends RecyclerView.ViewHolder {
    LinearLayout linearLayout;
    TextView tvName;
    TextView tvfavorite;
    TextView tvLook;
    ImageView iv;
    ImageView ivLogo;
    ImageView ivBg;
    FlowLayout flowLayout;

    ImageView ivRent;
    ImageView ivEye;
    ImageView ivSale;
    public PlanViewBaseHolder(View itemView) {
        super(itemView);
    }
    public void setData(final Context context, final Plan plan){
        if (this instanceof AllPlanDataAdapter.PlanViewHolder){
            tvName.setText(plan.solution_name+"\n"+plan.product_text);
            tvfavorite.setText(plan.nums_favorite);
            tvLook.setText(plan.nums_read);
            flowLayout.removeAllViews();
            if (!TextUtils.isEmpty(plan.product_tag)){
                String[] sourceStrArray = plan.product_tag.split(",");
                for (int i=0;i<sourceStrArray.length;i++){
                    View flowView = LayoutInflater.from(context).inflate(R.layout.activity_all_plan_item_data_flowtext,null,false);
                    TextView tvFlow = (TextView) flowView.findViewById(R.id.tv_all_plan_flowtext);
                    tvFlow.setText(sourceStrArray[i]);
                    PlatformUtil.setFlowBackDrawable(i,tvFlow);
                    flowLayout.addView(flowView);
                }
            }
            Glide.with(context).load(plan.solution_companylogo).into(ivLogo);
            Glide.with(context).load(plan.solution_logo).into(iv);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url;
                    if ("2".equals(plan.h5_mode)){
                        url = plan.solution_pc_url;//海外版URL不需要拼接
                    } else {
                        url = PlatformUtil.getPlanUrlH5(plan.solution_h5_url);//国内版URL需要拼接
                    }
                    Intent intent = new Intent(context, PlatformWebViewActivity.class);
                    intent.putExtra(PlatformWebViewActivity.WEBVIEW_URL,url);
                    context.startActivity(intent);
                }
            });
        } else {
//            tvName.setText(plan.product_text);
            Glide.with(context).load(plan.solution_logo).into(iv);
            GlideUtils.loadRoundIV(context,plan.solution_bglogo,ivBg,5);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url;
                    if ("2".equals(plan.h5_mode)){
                        url = plan.solution_pc_url;//海外版URL不需要拼接
                    } else {
                        url = PlatformUtil.getPlanUrlH5(plan.solution_h5_url);//国内版URL需要拼接
                    }
                    Intent intent = new Intent(context, PlatformWebViewActivity.class);
                    intent.putExtra(PlatformWebViewActivity.WEBVIEW_URL,url);
                    context.startActivity(intent);
                }
            });
            ivRent.setVisibility(View.GONE);
            ivSale.setVisibility(View.GONE);
            ivEye.setVisibility(View.GONE);
//            if (!TextUtils.isEmpty(plan.service_type)){
//                if (plan.service_type.contains("租")){
//                    ivRent.setVisibility(View.VISIBLE);
//                }
//                if (plan.service_type.contains("售")){
//                    ivSale.setVisibility(View.VISIBLE);
//                }
//                if (plan.service_type.contains("眼")){
//                    ivEye.setVisibility(View.VISIBLE);
//                }
//            }
        }
    }
}