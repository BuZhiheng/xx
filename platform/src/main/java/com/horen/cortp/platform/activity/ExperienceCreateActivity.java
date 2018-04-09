package com.horen.cortp.platform.activity;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.ExperenceCreateAdapter;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.horen.cortp.platform.contract.ExperienceCreateContract;
import com.horen.cortp.platform.presenter.ExperienceCreatePresenter;
import com.horen.cortp.platform.widget.ExperienceCreateDialog;
import com.jaydenxiao.common.commonutils.PictureUtil;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import java.io.IOException;
import java.util.ArrayList;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.ImageCaptureManager;
/**
 * Created by HOREN on 2017/12/26.
 */
public class ExperienceCreateActivity extends BaseActivity<ExperienceCreatePresenter,ExperienceCreateContract.Model> implements View.OnClickListener,ExperienceCreateContract.View {
    private TextView tvRight;
    private ImageButton actionButton;
    private ExperienceCreateDialog dialog;
    private RecyclerView recyclerView;
    private ExperenceCreateAdapter adapter;
    private ImageCaptureManager captureManager;
    private boolean fromExperience;
    @Override
    public int getLayoutId() {
        return R.layout.activity_experience_create;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        tvRight = (TextView) findViewById(R.id.right_tv);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(getText(R.string.platform_preview));
        tvRight.setOnClickListener(this);
        actionButton = (ImageButton) findViewById(R.id.btn_experience_create);
        fromExperience = getIntent().getBooleanExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,false);
        recyclerView = (RecyclerView) findViewById(R.id.rv_use_box_create);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExperenceCreateAdapter(this,fromExperience);
        recyclerView.setAdapter(adapter);
        dialog = new ExperienceCreateDialog(this, new ExperienceCreateDialog.OnSelectClickListener() {
            @Override
            public void clickTxt() {
                Intent intent = new Intent(ExperienceCreateActivity.this,ExperienceCreateTextActivity.class);
                intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE,true);
                intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,fromExperience);
                startActivityForResult(intent,CommonCode.RESULT_CODE_CREATE_TXT_SUCCESS);
            }
            @Override
            public void clickCamera() {
                try {
                    captureManager = new ImageCaptureManager(ExperienceCreateActivity.this);
                    Intent intent = captureManager.dispatchTakePictureIntent();
                    startActivityForResult(intent, PictureUtil.PIC_CAMERA);
                } catch (IOException e) {
                }
            }
            @Override
            public void clickPic() {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(false)
                        .start(ExperienceCreateActivity.this, PhotoPicker.REQUEST_CODE);
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                close();
            }
        });
        mPresenter.init(getIntent());
    }
    /**
     * 处理选择图片，返回交给presenter处理
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onResult(captureManager,requestCode,resultCode,data);
    }
    @Override
    public void createPic(ArrayList<String> photos){
        Intent intent = new Intent(this, ExperienceCreatePictureActivity.class);
        intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_PICDATA,photos);
        intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE,true);
        intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_TYPE,fromExperience);
        startActivityForResult(intent,CommonCode.RESULT_CODE_CREATE_PIC_SUCCESS);
    }
    @Override
    public void addItem(ExperienceCreate experienceCreate) {
        adapter.addData(experienceCreate);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void update(int position, ExperienceCreate experienceCreate) {
        adapter.update(position,experienceCreate);
        adapter.notifyDataSetChanged();
    }
    @Override
    public ArrayList<ExperienceCreate> getAdapterData() {
        return adapter.getData();
    }
    @Override
    public void removeItem(int position) {
        adapter.remove(position);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_experience_create){
            open();
            dialog.show();
        } else if (id == R.id.tv_experience_create_commit){
            mPresenter.commit();
        }
    }
    @Override
    public void showLoading(String title) {

    }
    @Override
    public void stopLoading() {
    }
    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showShort(msg);
    }
    /**
     * 开启动画，打开选择文字图片按钮
     * */
    private void open(){
        //http://blog.csdn.net/harvic880925/article/details/50598322;
        ObjectAnimator animator = ObjectAnimator.ofFloat(actionButton,"rotation",0,155,135);
        animator.setDuration(500);
        animator.start();
    }
    /**
     * 关闭动画，关闭选择文字图片按钮
     * */
    private void close(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(actionButton,"rotation",135,-20,0);
        animator.setDuration(500);
        animator.start();
    }
    @Override
    public void setTitle(String title) {
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv), title);
    }
    @Override
    public void finish() {
        super.finish();
    }
}