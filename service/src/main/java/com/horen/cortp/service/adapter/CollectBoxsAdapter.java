package com.horen.cortp.service.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by HOREN on 2017/10/25.
 */

public class CollectBoxsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public static final int IMAGE_PICKER = 100;
    public static final int IMAGE_PICKER_SINGLE = 101;
    private BaseActivity context;
    private int currentPositon;
    private ArrayList<PhotoAdapter> photoAdapters;
    private ArrayList<String> imageItems;
    private ArrayList<StaggeredGridLayoutManager> staggeredGridLayoutManagers;
    private ArrayList<ImageView> imageViews;

    public CollectBoxsAdapter(BaseActivity context, int layoutResId, List<String> data) {
        super(layoutResId, data);
        this.context = context;
        photoAdapters = new ArrayList<>();
        staggeredGridLayoutManagers = new ArrayList<>();
        imageViews = new ArrayList<>();
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        RecyclerView recyclerPhoto = helper.getView(R.id.recycler_photo);
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // 默认是1行
        staggeredGridLayoutManagers.add(layout);
        recyclerPhoto.setLayoutManager(layout);
        // 添加一个假数据，显示图片
        imageItems = new ArrayList<>();
        imageItems.add("");
        PhotoAdapter adapter = new PhotoAdapter(R.layout.item_iv, imageItems);
        photoAdapters.add(adapter);
        recyclerPhoto.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() { // 当损坏照片被点击的时候
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                currentPositon = helper.getLayoutPosition(); // 记录当前点击的是第几个位置
                PhotoPicker.builder()
                        .setPhotoCount(5)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(context, IMAGE_PICKER);
            }
        });


        helper.setOnClickListener(R.id.iv_damage_boxs_number, new View.OnClickListener() { // 损坏箱号
            @Override
            public void onClick(View v) {
                currentPositon = helper.getLayoutPosition(); // 记录当前点击的是第几个位置
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(context, IMAGE_PICKER_SINGLE);
            }
        });

        imageViews.add((ImageView) helper.getView(R.id.iv_damage_boxs_number));


    }

    public void setImagesAdapter(ArrayList<String> images) {
        // 刷新adapter
        photoAdapters.get(currentPositon).setNewData(images);
        if (images.size() > 0) {
            staggeredGridLayoutManagers.get(currentPositon).setSpanCount(images.size()); // 列数
        }
    }

    public void setBoxNumberPicture(String path) {
        // 加载图片
        Uri uri = Uri.fromFile(new File(path));
        Glide.with(mContext)
                .load(uri)
                .centerCrop()
                .thumbnail(0.1f)
                .into(imageViews.get(currentPositon));
    }
}
