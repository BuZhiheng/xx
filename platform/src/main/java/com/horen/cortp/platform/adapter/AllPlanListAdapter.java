package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.bean.PlanType;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOREN on 2017/11/16.
 *
 * 全部方案一级菜单adapter
 *
 */
public class AllPlanListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PlanType> datas = new ArrayList<>();
    private AllPlanListClickListener listener;
    public AllPlanListAdapter(Context context,AllPlanListClickListener listener){
        this.context = context;
        this.listener = listener;
    }
    public void setData(List<PlanType> datas){
        this.datas = datas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_all_plan_item_list,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holderItem = (ViewHolder) holder;
        final PlanType type = datas.get(position);
        holderItem.tvName.setText(type.solution_typename);
        if (type.isSelect){
            holderItem.imageView.setBackgroundResource(R.color.main_blue);
        } else {
            holderItem.imageView.setBackgroundResource(R.color.white);
        }
        holderItem.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (PlanType data : datas) {
                    data.isSelect = false;
                }
                datas.get(position).isSelect = true;
                notifyDataSetChanged();
                if (listener != null){
                    listener.click(type);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_all_plan_list);
            imageView = (ImageView) itemView.findViewById(R.id.iv_all_plan_list);
        }
    }
    public interface AllPlanListClickListener {
        void click(PlanType type);
    }
}