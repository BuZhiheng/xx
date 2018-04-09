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
import com.horen.cortp.platform.bean.Partner;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOREN on 2017/11/16.
 *
 * 全部万箱盟友adapter
 *
 */
public class AllPartnerAdapter extends BaseRecycleViewAdapter {
    private Context context;
    private List<Partner> datas = new ArrayList<>();
    public AllPartnerAdapter(Context context){
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
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_platform_item_partner,parent,false);
        return new PartnerViewHolder(view);
    }
    @Override
    public void onBindView(RecyclerView.ViewHolder holder, int position) {
        ((PartnerViewHolder)holder).setData(context,datas.get(position));
    }
    public void setData(List<Partner> datas) {
        this.datas = datas;
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