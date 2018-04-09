package com.horen.cortp.platform.adapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.horen.cortp.platform.R;
/**
 * Created by HOREN on 2017/12/01.
 *
 * 用箱体验adapter
 *
 */
public class UseBoxFeelAdapter extends RecyclerView.Adapter<UseBoxFeelAdapter.ViewHolder> {
    private Context context;
    public UseBoxFeelAdapter(Context context){
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_use_box_feel_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512124692276&di=ae7e4fed6d8cd37eb61c3e3e4e091f13&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ffd039245d688d43fe757c123771ed21b0ff43b60.jpg")
                .asBitmap()
                .placeholder(R.drawable.ic_platform_hot)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(holder.imageView){
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(10); //设置圆角弧度
                        holder.imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
    @Override
    public int getItemCount() {
        return 20;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_use_box_feel_item);
        }
    }
}