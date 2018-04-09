package com.horen.cortp.service.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.imagePager.BigImagePagerActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by HOREN on 2017/10/25.
 */

public class CollectBoxsCompleteAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CollectBoxsCompleteAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        RecyclerView recyclerPhoto = helper.getView(R.id.recycler_photo);
        if (mData.size() <= 5) {
            recyclerPhoto.setLayoutManager(new StaggeredGridLayoutManager(mData.size(), StaggeredGridLayoutManager.VERTICAL));
        } else { // 大于5
            recyclerPhoto.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
        }
        // 添加一个假数据，显示图片
        CollectBoxsCompletePhotoAdapter adapter = new CollectBoxsCompletePhotoAdapter(R.layout.item_iv, mData);
        recyclerPhoto.setAdapter(adapter);

        helper.setOnClickListener(R.id.iv_damage_boxs_number, new View.OnClickListener() { // 损坏箱号
            @Override
            public void onClick(View v) {
                BigImagePagerActivity.startImagePagerActivity((Activity) mContext, Arrays.asList("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2937931279,857851210&fm=27&gp=0.jpg"),0);
            }
        });
    }
}
