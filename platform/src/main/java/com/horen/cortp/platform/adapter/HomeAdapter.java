package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.bean.Partner;
import com.horen.cortp.platform.bean.Plan;
import com.horen.cortp.platform.bean.PlanTypeList;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOREN on 2017/11/16.
 *
 * 万箱首页adapter(banner hotplan partner)
 *
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_NAME_HOT = 0;
    public static final int VIEW_TYPE_NAME_PARTNER = 1;
    private final int VIEW_TYPE_ITEM_HOT = 2;
    private final int VIEW_TYPE_ITEM_PARTNER = 3;
    private List<Object> datas = new ArrayList<>();
    private Context context;
    public HomeAdapter(Context context){
        this.context = context;
    }
    public void setData(List<Object> datas){
        this.datas = datas;
    }
    public void addPartner(List<Partner> datas){
        this.datas.addAll(datas);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_NAME_HOT:
                View viewHot = LayoutInflater.from(context).inflate(R.layout.fragment_platform_item_allhot,parent,false);
                NameHotViewHolder nameHotViewHolder = new NameHotViewHolder(viewHot);
                nameHotViewHolder.frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(context, PlatformTestActivity.class);
//                        context.startActivity(intent);
                    }
                });
                return nameHotViewHolder;
            case VIEW_TYPE_NAME_PARTNER:
                View viewPartner = LayoutInflater.from(context).inflate(R.layout.fragment_platform_item_allpartner,parent,false);
                NamePartnerViewHolder namePartnerViewHolder = new NamePartnerViewHolder(viewPartner);
                namePartnerViewHolder.frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(context, AllPartnerActivity.class);
                        //context.startActivity(intent);
                    }
                });
                return namePartnerViewHolder;
            case VIEW_TYPE_ITEM_HOT:
                View viewItemHot = LayoutInflater.from(context).inflate(R.layout.fragment_platform_item_home,parent,false);
                return new HotViewHolder(viewItemHot);
            case VIEW_TYPE_ITEM_PARTNER:
                View viewItemPartner = LayoutInflater.from(context).inflate(R.layout.fragment_platform_item_partner,parent,false);
                return new PartnerViewHolder(viewItemPartner);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NameHotViewHolder){
            final PlanTypeList typeList = (PlanTypeList) datas.get(position);
            NameHotViewHolder holderItem = (NameHotViewHolder) holder;
            holderItem.textView.setText(typeList.solution_typename+context.getString(R.string.platform_package_solution));
        } else if (holder instanceof HotViewHolder){
            final Plan plan = (Plan) datas.get(position);
            HotViewHolder holderItem = (HotViewHolder) holder;
            holderItem.setData(context,plan);
        }
    }
    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    @Override
    public int getItemViewType(int position) {
        Object data = datas.get(position);
        if (data instanceof PlanTypeList) {
            return VIEW_TYPE_NAME_HOT;
        } else {
            if (data instanceof Plan){
                return VIEW_TYPE_ITEM_HOT;
            }
        }
        return -1;
    }
    class NameHotViewHolder extends RecyclerView.ViewHolder {
        LinearLayout frameLayout;
        TextView textView;
        public NameHotViewHolder(View itemView) {
            super(itemView);
            frameLayout = (LinearLayout) itemView.findViewById(R.id.ll_to_allhot);
            textView = itemView.findViewById(R.id.tv_platform_item_hot);
        }
    }
    class NamePartnerViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameLayout;
        public NamePartnerViewHolder(View itemView) {
            super(itemView);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.fl_to_allpartner);
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
    class PartnerViewHolder extends PartnerViewBaseHolder {
        public PartnerViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_platform_item_partner);
            iv = (ImageView) itemView.findViewById(R.id.iv_platform_item_partner);
            tvName = (TextView) itemView.findViewById(R.id.tv_platform_item_partner);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_platform_item_partner_desc);
            tvCountry = (TextView) itemView.findViewById(R.id.tv_platform_item_partner_country);
        }
    }
}