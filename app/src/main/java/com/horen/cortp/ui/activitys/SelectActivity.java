package com.horen.cortp.ui.activitys;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.horen.cortp.R;
import com.horen.cortp.ui.companyclient.CompanyClientMainActivity;
import com.horen.cortp.ui.warehouseclient.WareHouseMainActivity;
import com.jaydenxiao.common.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * Created by HOREN on 2017/11/23.
 */

public class SelectActivity extends BaseActivity {
    @BindView(R.id.bt_company)
    Button btCompany;
    @BindView(R.id.bt_service)
    Button btService;
    @BindView(R.id.bt_ware_house)
    Button btWareHouse;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        btCompany.setText("企业端");
        btService.setText("服务端");
        btWareHouse.setText("仓库端");
    }


    @OnClick({R.id.bt_company, R.id.bt_service, R.id.bt_ware_house})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_company:
                CompanyClientMainActivity.startAction(mContext);
                finish();
                break;
            case R.id.bt_service:
                CompanyClientMainActivity.startAction(mContext);
                finish();
                break;
            case R.id.bt_ware_house:
                WareHouseMainActivity.startAction(mContext);
                finish();
                break;
        }
    }

    public static void startAction(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SelectActivity.class);
        context.startActivity(intent);
    }
}
