package com.horen.cortp.platform.activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.ExperenceCreatePictureAdapter;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.horen.cortp.platform.contract.ExperienceCreatePicContract;
import com.horen.cortp.platform.presenter.ExperienceCreatePicPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import java.util.ArrayList;
import me.iwf.photopicker.PhotoPicker;
/**
 * Created by HOREN on 2017/12/27.
 *
 * 万箱体验：创建/修改 图片
 *
 * 新箱构想：创建/修改 图片
 *
 */
public class ExperienceCreatePictureActivity extends BaseActivity<ExperienceCreatePicPresenter,ExperienceCreatePicContract.Model> implements ExperienceCreatePicContract.View, View.OnClickListener {
    private TextView tvRight;
    private Button btnDelete;
    private RecyclerView recyclerView;
    private ExperenceCreatePictureAdapter adapter;
    private ArrayList<String> photos;
    @Override
    public int getLayoutId() {
        return R.layout.activity_experience_create_picture;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        //http://blog.csdn.net/itjianghuxiaoxiong/article/details/52135748
        tvRight = (TextView) findViewById(R.id.right_tv);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(getText(R.string.platform_sure));
        tvRight.setOnClickListener(this);
        btnDelete = (Button) findViewById(R.id.btn_experience_create_pic_delete);
        recyclerView = (RecyclerView) findViewById(R.id.rv_use_box_create_picture);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);
        Intent intent = getIntent();
        adapter = new ExperenceCreatePictureAdapter(this, new ExperenceCreatePictureAdapter.OnDelClickListener() {
            @Override
            public void onDel(int position,String img) {
                photos.remove(position);
                adapter.notifyItemRemoved(position);
                mPresenter.removePic(position);
                if (position != photos.size()){
                    adapter.notifyItemRangeChanged(position,photos.size()+1-position);
                }
                setAddShow();
            }
            @Override
            public void onPic() {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(false)
                        .start(ExperienceCreatePictureActivity.this, PhotoPicker.REQUEST_CODE);
            }
            @Override
            public void onAdd(int size) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(false)
                        .setSelected(photos)
                        .start(ExperienceCreatePictureActivity.this, PhotoPicker.REQUEST_CODE);
            }
        },intent.getBooleanExtra(CommonCode.INTENT_EXPERIENCE_CREATE,false));
        recyclerView.setAdapter(adapter);
        mPresenter.init(intent);
    }
    @Override
    public void showLoading(String title) {
        startProgressDialog();
    }
    @Override
    public void stopLoading() {
        stopProgressDialog();
    }
    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showShort(msg);
        stopProgressDialog();
    }
    @Override
    public void setTitle(String title) {
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv),title);
    }
    @Override
    public void setDeleteGone() {
        btnDelete.setVisibility(View.GONE);
    }
    @Override
    public void setPic(ArrayList<String> photos) {
        this.photos = photos;
        adapter.setData(photos);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void setAddGone() {
        adapter.setAddViewGone();
    }
    @Override
    public void setAddShow() {
        adapter.setAddViewShow();
    }
    @Override
    public void setCommit(ArrayList<String> finalPhotos, int resultCodeCreatePicSuccess,int position,String id) {
        Intent intent = new Intent();
        intent.putExtra(ExperienceCreate.class.getName(),finalPhotos);
        intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_INDEX,position);
        intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_ID,id);
        setResult(resultCodeCreatePicSuccess,intent);
        finish();
    }
    /**
     * 处理选择图片，返回交给presenter处理
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onClick(View view) {
        if (view == tvRight){
            mPresenter.sureClick();
        } else if (view == btnDelete){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.platform_note)
                    .setMessage(R.string.platform_delete_experience_txt_dialog)
                    .setPositiveButton(R.string.platform_sure, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mPresenter.delClick();
                        }
                    })
                    .setNegativeButton(R.string.platform_cancel, null)
                    .show();
        }
    }
}