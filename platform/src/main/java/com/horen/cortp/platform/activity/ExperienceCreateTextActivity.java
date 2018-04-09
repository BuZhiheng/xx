package com.horen.cortp.platform.activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.api.CommonCode;
import com.horen.cortp.platform.bean.ExperienceCreate;
import com.horen.cortp.platform.contract.ExperienceCreateTxtContract;
import com.horen.cortp.platform.presenter.ExperienceCreateTxtPresenter;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
/**
 * Created by HOREN on 2017/12/27.
 *
 * 万箱体验：创建/修改 文字
 *
 * 新箱构想：创建/修改 文字
 *
 * 根据intent isFromExperience 和 isCreate判断（init）
 */
public class ExperienceCreateTextActivity extends BaseActivity<ExperienceCreateTxtPresenter,ExperienceCreateTxtContract.Model> implements ExperienceCreateTxtContract.View, View.OnClickListener {
    private TextView tvRight;
    private EditText editText;
    private Button btnDelete;
    @Override
    public int getLayoutId() {
        return R.layout.activity_experience_create_text;
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }
    @Override
    public void initView() {
        tvRight = (TextView) findViewById(R.id.right_tv);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(getText(R.string.platform_sure));
        tvRight.setOnClickListener(this);
        btnDelete = (Button) findViewById(R.id.btn_experience_create_txt_delete);
        editText = (EditText) findViewById(R.id.et_experience_create_txt);
        mPresenter.init(getIntent());
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
    @Override
    public void setTitle(String title) {
        setSimpleToolbar((Toolbar) findViewById(R.id.tool_bar),(TextView) findViewById(R.id.tool_bar_title_tv),title);
    }
    @Override
    public void setEditText(String txt) {
        editText.setText(txt);
    }
    @Override
    public void setDeleteGone() {
        btnDelete.setVisibility(View.GONE);
    }
    @Override
    public void setCommit(ExperienceCreate experienceCreate, int resultCodeCreateTxtSuccess,int position,String id) {
        Intent intent = new Intent();
        intent.putExtra(ExperienceCreate.class.getName(),experienceCreate);
        intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_INDEX,position);
        intent.putExtra(CommonCode.INTENT_EXPERIENCE_CREATE_ID,id);
        setResult(resultCodeCreateTxtSuccess,intent);
        finish();
    }
    @Override
    public void onClick(View view) {
        if (view == tvRight){
            mPresenter.sureClick(editText);
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