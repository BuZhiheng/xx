package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.activity.PhotoViewPagerActivity;
import com.jaydenxiao.common.commonutils.GlideUtils;
import java.util.ArrayList;
/**
 * Created by HOREN on 2017/12/27.
 */
public class ExperenceCreatePictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_ITEM = 0;
    private final int TYPE_ADD = 1;
    private Context context;
    private ArrayList<String> datas = new ArrayList<>();
    private OnDelClickListener listener;
    private boolean isCreate = false;
    private AddHolder addHolder;
    public void setData(ArrayList<String> datas){
        this.datas = datas;
    }
    public ExperenceCreatePictureAdapter(Context context,OnDelClickListener listener,boolean isCreate){
        this.context = context;
        this.listener = listener;
        this.isCreate = isCreate;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.activity_experience_create_picture_item,parent,false);
            return new Holder(view);
        } else {
            if (addHolder == null){
                View view = LayoutInflater.from(context).inflate(R.layout.activity_experience_create_picture_add,parent,false);
                addHolder = new AddHolder(view);
                addHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            listener.onAdd(9-datas.size());
                        }
                    }
                });
                setAddViewGone();
//                if (!isCreate){
//                    setAddViewGone();
//                } else if (datas.size() >= 9){
//                    setAddViewGone();
//                }
            }
            return addHolder;
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof Holder){
            final String img = datas.get(position);
            Holder holder = (Holder) viewHolder;
            GlideUtils.loadUrl(context,datas.get(position),holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PhotoViewPagerActivity.class);
                    intent.putExtra(PhotoViewPagerActivity.PHOTO_URL,datas);
                    intent.putExtra(PhotoViewPagerActivity.PHOTO_POSITION,position);
                    context.startActivity(intent);
                }
            });
//            if (!isCreate){
//                holder.imageViewDel.setImageResource(R.drawable.ic_platform_experience_updatapic);
//            }
            holder.imageViewDel.setImageResource(R.drawable.ic_platform_experience_updatapic);
            holder.imageViewDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onPic();
//                        if (isCreate){
//                            listener.onDel(position,img);
//                        } else {
//                            listener.onPic();
//                        }
                    }
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return datas.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()){
            return TYPE_ADD;
        }
        return TYPE_ITEM;
    }
    public void setAddViewShow(){
        if (addHolder != null){
            addHolder.ivAdd.setVisibility(View.VISIBLE);
        }
    }
    public void setAddViewGone(){
        if (addHolder != null){
            addHolder.ivAdd.setVisibility(View.GONE);
        }
    }
    class Holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView imageViewDel;
        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_experience_create_picture_item);
            imageViewDel = itemView.findViewById(R.id.iv_experience_create_picture_item_del);
        }
    }
    class AddHolder extends RecyclerView.ViewHolder{
        ImageView ivAdd;
        public AddHolder(View itemView) {
            super(itemView);
            ivAdd = itemView.findViewById(R.id.iv_experience_create_pic_add);
        }
    }
    public interface OnDelClickListener {
        void onDel(int position,String img);
        void onPic();
        void onAdd(int size);
    }
}