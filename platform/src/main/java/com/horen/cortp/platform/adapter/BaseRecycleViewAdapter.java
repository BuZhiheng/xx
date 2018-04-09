package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.horen.cortp.platform.R;
/**
 * Created by HOREN on 2017/12/18.
 */
public abstract class BaseRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_NODATA = -1;
    private Context context;
    private NodataViewHolder nodataViewHolder;
    public BaseRecycleViewAdapter(Context context){
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_NODATA){
            if (nodataViewHolder == null){
                View view = LayoutInflater.from(context).inflate(R.layout.rv_default_no_data,parent,false);
                nodataViewHolder = new NodataViewHolder(view);
            }
            return nodataViewHolder;
        } else {
            return onCreateView(parent, viewType);
        }
    }
    public void setEmptyDesc(int descId){
        if (nodataViewHolder != null){
            nodataViewHolder.textView.setText(context.getString(descId));
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getCount() > 0){
            onBindView(holder,position);
        }
    }
    @Override
    public int getItemCount() {
        if (getCount() == 0){
            return 1;
        }
        return getCount();
    }
    @Override
    public int getItemViewType(int position){
        if (getCount() == 0){
            return VIEW_TYPE_NODATA;
        } else {
            return getItemType(position);
        }
    }
    public abstract int getCount();
    public abstract int getItemType(int position);
    public abstract RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType);
    public abstract void onBindView(RecyclerView.ViewHolder holder, int position);
    class NodataViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public NodataViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_rv_nodata);
        }
    }
}