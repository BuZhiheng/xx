package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.bean.Plan;
import com.horen.cortp.platform.widget.FlowLayout;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOREN on 2017/11/16.
 *
 * 热门方案adapter
 *
 */
public class AllHotPlanAdapter extends BaseRecycleViewAdapter {
    private Context context;
    private List<Plan> datas = new ArrayList<>();
    public AllHotPlanAdapter(Context context){
        super(context);
        this.context = context;
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
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_platform_item_home,parent,false);
        return new HotViewHolder(view);
    }
    @Override
    public void onBindView(RecyclerView.ViewHolder holder, int position) {
        Plan plan = datas.get(position);
        ((HotViewHolder)holder).setData(context,plan);
    }
    public void setData(List<Plan> datas) {
        if (datas == null || datas.size() == 0){
            setEmptyDesc(R.string.loading_empty);
        } else {
            this.datas = datas;
        }
    }
    public void addData(List<Plan> datas) {
        this.datas.addAll(datas);
        if (this.datas.size() == 0){
            setEmptyDesc(R.string.loading_empty);
        }
    }
    class HotViewHolder extends PlanViewBaseHolder {
        public HotViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_platform_item_home);
            iv = (ImageView) itemView.findViewById(R.id.iv_platform_item_home);
            ivBg = itemView.findViewById(R.id.iv_platform_item_bg);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_platform_item_home);
            ivRent = itemView.findViewById(R.id.iv_platform_item_home_tagrent);
            ivEye = itemView.findViewById(R.id.iv_platform_item_home_tageye);
            ivSale = itemView.findViewById(R.id.iv_platform_item_home_tagsale);
        }
    }
}