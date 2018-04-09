package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.activity.ExperienceCreateActivity;
import com.horen.cortp.platform.activity.PlatformWebViewActivity;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.BoxExperience;
import com.horen.cortp.platform.platformutils.PlatformUtil;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOREN on 2017/12/01.
 *
 * 用箱体验adapter
 *
 */
public class UseBoxExperienceAdapter extends BaseRecycleViewAdapter {
    private Context context;
    private List<BoxExperience> datas = new ArrayList<>();
    public UseBoxExperienceAdapter(Context context){
        super(context);
        this.context = context;
    }
    public void setData(List<BoxExperience> datas){
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
        View view = LayoutInflater.from(context).inflate(R.layout.activity_experience_item,parent,false);
        return new ExperienceViewHolder(view);
    }
    @Override
    public void onBindView(RecyclerView.ViewHolder viewHolder, final int position) {
        final ExperienceViewHolder holder = (ExperienceViewHolder) viewHolder;
        final BoxExperience experience = datas.get(position);
        holder.tvCompany.setText(experience.company_name);
        holder.tvName.setText(experience.experience_title);
        holder.tvTime.setText(experience.use_time);
        if ("0".equals(experience.tag)){
            holder.tvTag.setText(context.getString(R.string.platform_inreview));
            holder.tvDesc.setText(context.getString(R.string.platform_inreview));
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ExperienceCreateActivity.class);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,true);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE,false);
                    intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_ID,"");
                    context.startActivity(intent);
                }
            });
        } else {
            holder.tvTag.setText(context.getString(R.string.platform_introduced));
            holder.tvDesc.setText("");
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlatformWebViewActivity.class);
                    intent.putExtra(PlatformWebViewActivity.WEBVIEW_URL, PlatformUtil.getUrlH5AddDevicetoken(experience.experience_url));
                    context.startActivity(intent);
                }
            });
        }
    }
    class ExperienceViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView tvCompany;
        TextView tvName;
        TextView tvDesc;
        TextView tvTime;
        TextView tvTag;
        public ExperienceViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.ll_box_experience_item);
            tvCompany = (TextView) itemView.findViewById(R.id.tv_box_experience_company);
            tvName = (TextView) itemView.findViewById(R.id.tv_box_experience_name);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_box_experience_desc);
            tvTime = (TextView) itemView.findViewById(R.id.tv_box_experience_time);
            tvTag = (TextView) itemView.findViewById(R.id.tv_box_experience_tag);
        }
    }
}