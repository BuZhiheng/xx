package com.horen.cortp.service.adapter;

import android.content.Intent;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.service.R;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.ScanActivity;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by HOREN on 2017/10/25.
 */

public class NewCollectBoxsAdapter extends BaseQuickAdapter<SendBoxOrderDetailBean.PicsInfoBean, BaseViewHolder> {
    public static final int IMAGE_PICKER = 100;
    public static final int IMAGE_PICKER_SINGLE = 101;
    private BaseActivity context;
    private int currentPositon;
    private ArrayList<PhotoAdapter> photoAdapters;
    private ArrayList<StaggeredGridLayoutManager> staggeredGridLayoutManagers;
    private ArrayList<ImageView> imageViews;
    private ArrayList<TextView> textViews;

    public NewCollectBoxsAdapter(BaseActivity context, int layoutResId, List<SendBoxOrderDetailBean.PicsInfoBean> data) {
        super(layoutResId, data);
        this.context = context;
        photoAdapters = new ArrayList<>();
        staggeredGridLayoutManagers = new ArrayList<>();
        imageViews = new ArrayList<>();
        textViews = new ArrayList<>();
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SendBoxOrderDetailBean.PicsInfoBean item) {
        if (item.getReference_id() == null) item.setReference_id("");
        EditText etRemake = helper.getView(R.id.et_remake);
        RecyclerView recyclerPhoto = helper.getView(R.id.recycler_photo);
        textViews.add((TextView) helper.getView(R.id.tv_box_number)); // 箱号
        AppCompatSpinner spinner = helper.getView(R.id.spinner);


        item.setId(TextUtils.isEmpty(item.getId()) ? "0" : item.getId()); // 为空设置一个默认值
        helper.setText(R.id.tv_box_number, TextUtils.isEmpty(item.getReference_id()) ? "" : item.getReference_id());
        StaggeredGridLayoutManager layout;
        // 添加一个假数据，显示添加图片的按钮
        List<String> pics = new ArrayList<>();
        pics.add("");
        if (item.getDamagePics() == null || item.getDamagePics().size() == 0) {
            item.setDamagePics(pics);
        }
        // 图片显示列数
        if (item.getDamagePics() != null && item.getDamagePics().size() > 0 && item.getDamagePics().size() <= 5) {
            layout = new StaggeredGridLayoutManager(item.getDamagePics().size(), StaggeredGridLayoutManager.VERTICAL);
        } else if (item.getDamagePics() != null && item.getDamagePics().size() > 5) {
            layout = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        } else {
            layout = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL); // 默认是1行
        }
        staggeredGridLayoutManagers.add(layout);
        recyclerPhoto.setLayoutManager(layout);
        PhotoAdapter adapter = new PhotoAdapter(R.layout.item_iv, item.getDamagePics());
        photoAdapters.add(adapter);
        recyclerPhoto.setAdapter(adapter);
        // 上传多张损坏照片
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
        // 上传损坏箱号
        helper.setOnClickListener(R.id.tv_box_number, new View.OnClickListener() { // 损坏箱号
            @Override
            public void onClick(View v) {
                currentPositon = helper.getLayoutPosition(); // 记录当前点击的是第几个位置
                // 扫描二维码
                Intent intent = new Intent(context, ScanActivity.class);
                context.startActivityForResult(intent, /*CollectBoxsCollectingActivity.REQUEST_CODE*/1002);
            }
        });
        // 侧滑删除
        helper.setOnClickListener(R.id.btnDelete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.remove(helper.getLayoutPosition()); // 删除数据
                imageViews.remove(helper.getLayoutPosition());
                photoAdapters.remove(helper.getLayoutPosition());
                staggeredGridLayoutManagers.remove(helper.getLayoutPosition());
                textViews.remove(helper.getLayoutPosition());
                notifyItemRemoved(helper.getLayoutPosition()); // 列表删除
            }
        });

        // 扫描箱号
        helper.setOnClickListener(R.id.iv_damage_boxs_number, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPositon = helper.getLayoutPosition(); // 记录当前点击的是第几个位置
                // 扫描二维码
                Intent intent = new Intent(context, ScanActivity.class);
                context.startActivityForResult(intent, /*CollectBoxsCollectingActivity.REQUEST_CODE*/1002);
            }
        });

        etRemake.setText(item.getDamagerRemark());
        etRemake.addTextChangedListener(new TextWatcher() { // 记录备注信息
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setDamagerRemark(s.toString());
            }
        });
        // 损坏类型
        final String[] stringArray = mContext.getResources().getStringArray(R.array.name); // 类型
        spinner.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, stringArray));
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(item.getDamageType())) {
                spinner.setSelection(i);
                break;
            } else {
                spinner.setSelection(0);
            }
        }
        item.setDamageType(TextUtils.isEmpty(item.getDamageType()) ? stringArray[0] : item.getDamageType()); // 如果服务器损坏类型为空，默认选择第一个损坏类型
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item.setDamageType(stringArray[position]); // 损坏类型
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作
            }
        });


        ImageView imageView = (ImageView) helper.getView(R.id.iv_damage_boxs_number);
        if (TextUtils.isEmpty(item.getReference_id())) {
            imageView.setVisibility(View.VISIBLE);
        }
        imageView.setImageResource(R.drawable.service_icon_scan_boxs);
        imageViews.add(imageView);
    }

    /**
     * 损坏照片
     *
     * @param images
     */

    public void setImagesAdapter(List<String> images) {
        mData.get(currentPositon).setDamagePics(images);
        // 刷新adapter
        photoAdapters.get(currentPositon).setNewData(images);
        if (images.size() > 0) {
            staggeredGridLayoutManagers.get(currentPositon).setSpanCount(images.size()); // 列数
        }
    }

    /**
     * 损坏箱号
     *
     * @param path
     */
    public void setBoxNumberPicture(String path) {
        mData.get(currentPositon).setReference_id(path);
        if (!TextUtils.isEmpty(path)) {
            imageViews.get(currentPositon).setVisibility(View.INVISIBLE);
        } else {
            imageViews.get(currentPositon).setVisibility(View.VISIBLE);
        }
    }

    /**
     * 损坏箱号
     *
     * @param boxNumber
     */
    public void setBoxNumber(String boxNumber) {
        // 隐藏图片
        mData.get(currentPositon).setReference_id(boxNumber);
        if (!TextUtils.isEmpty(boxNumber)) {
            imageViews.get(currentPositon).setVisibility(View.INVISIBLE);
        } else {
            imageViews.get(currentPositon).setVisibility(View.VISIBLE);
        }
        textViews.get(currentPositon).setVisibility(View.VISIBLE);
        textViews.get(currentPositon).setText(boxNumber);
    }
}
