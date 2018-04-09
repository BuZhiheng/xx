package com.horen.cortp.service.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;

import java.util.List;

/**
 * Created by HOREN on 2017/10/25.
 */

public class NewCollectBoxsCompleteAdapter extends BaseQuickAdapter<SendBoxOrderDetailBean.PicsInfoBean, BaseViewHolder> {

    public NewCollectBoxsCompleteAdapter(int layoutResId, List<SendBoxOrderDetailBean.PicsInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SendBoxOrderDetailBean.PicsInfoBean item) {
        RecyclerView recyclerPhoto = helper.getView(R.id.recycler_photo);
        helper.setText(R.id.tv_damaged_type, item.getDamageType()); // 损坏类型
        helper.setText(R.id.tv_remake, item.getDamagerRemark()); // 备注
        helper.setText(R.id.tv_box_number, item.getReference_id()); // 箱号
        if (item.getDamagePics() != null) {
            if (item.getDamagePics().size() <= 5 && item.getDamagePics().size() > 0) {
                recyclerPhoto.setLayoutManager(new StaggeredGridLayoutManager(item.getDamagePics().size(), StaggeredGridLayoutManager.VERTICAL));
            } else { // 大于5
                recyclerPhoto.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
            }
        }
        // 添加一个假数据，显示图片
        CollectBoxsCompletePhotoAdapter adapter = new CollectBoxsCompletePhotoAdapter(R.layout.item_iv, item.getDamagePics());
        recyclerPhoto.setAdapter(adapter);

    }
}
