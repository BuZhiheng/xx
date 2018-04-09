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
import com.horen.cortp.platform.bean.PlanTypeLookup;
import com.horen.cortp.platform.widget.FlowLayout;
import com.horen.cortp.platform.widget.SelectPlanTypeDialog;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by HOREN on 2017/11/16.
 *
 * 全部方案显示data adapter
 *
 */
public class AllPlanDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int DATA_TYPE_EMPTY = 2;
    private final int DATA_TYPE_ITEM = 3;
    private Context context;
    private NodataViewHolder nodataViewHolder;
    private OnTypeClickListener listener;
    private SelectPlanTypeDialog dialogTag;
    private SelectPlanTypeDialog dialogSize;
    private boolean isEmpty = false;
    private List<Plan> datas = new ArrayList<>();
    public AllPlanDataAdapter(Context context, OnTypeClickListener listener){
        this.context = context;
        this.listener = listener;
    }
    public void setData(List<Plan> datas){
        this.datas = datas;
        if (datas == null || datas.size() == 0){
            isEmpty = true;
            if (nodataViewHolder != null){
                nodataViewHolder.textView.setText(context.getString((R.string.loading_empty)));
            }
        } else {
            isEmpty = false;
        }
    }
    public void setDialogTypeTag(List<PlanTypeLookup> datas){
        dialogTag = new SelectPlanTypeDialog(context, new SelectPlanTypeDialog.OnSelectClickListener() {
            @Override
            public void click(PlanTypeLookup select) {
                dialogTag.dismiss();
                listener.type1Click(select);
            }
        },datas);
    }
    public void setDialogTypeSize(List<PlanTypeLookup> datas){
        dialogSize = new SelectPlanTypeDialog(context, new SelectPlanTypeDialog.OnSelectClickListener() {
            @Override
            public void click(PlanTypeLookup select) {
                dialogSize.dismiss();
                listener.type2Click(select);
            }
        },datas);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (nodataViewHolder == null){
            View viewEmpty = LayoutInflater.from(context).inflate(R.layout.rv_default_no_data_allplan,parent,false);
            nodataViewHolder = new NodataViewHolder(viewEmpty);
        }
        View view;
        if (viewType == DATA_TYPE_EMPTY){
            return nodataViewHolder;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.activity_all_plan_item_data,parent,false);
            return new PlanViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (!isEmpty){
            PlanViewHolder holderItem = (PlanViewHolder) holder;
            Plan plan = datas.get(position);
            holderItem.setData(context,plan);
        }
    }
    @Override
    public int getItemCount() {
        if (isEmpty){
            return 1;
        }
        return datas.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (isEmpty){
            return DATA_TYPE_EMPTY;
        }
        return DATA_TYPE_ITEM;
    }
    public void typeTagClick(View view){
        if (dialogTag != null){
            dialogTag.setLayout(view);
            dialogTag.show();
        }
    }
    public void typeSizeClick(View view){
        if (dialogSize != null){
            dialogSize.setLayout(view);
            dialogSize.show();
        }
    }
    class PlanViewHolder extends PlanViewBaseHolder {
        public PlanViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_all_plan_data);
            tvfavorite = (TextView) itemView.findViewById(R.id.tv_all_plan_data_favorite);
            tvLook = (TextView) itemView.findViewById(R.id.tv_all_plan_data_look);
            flowLayout = (FlowLayout) itemView.findViewById(R.id.flow_all_plan_data);
            iv = (ImageView) itemView.findViewById(R.id.iv_all_plan_data);
            ivLogo = (ImageView) itemView.findViewById(R.id.iv_all_plan_data_logo);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_all_plan_data);
        }
    }
    class NodataViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public NodataViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_rv_nodata);
        }
    }
    public interface OnTypeClickListener {
        void type1Click(PlanTypeLookup select);
        void type2Click(PlanTypeLookup select);
    }
}