package com.horen.cortp.company.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.horen.cortp.company.R;
import com.horen.cortp.company.api.ApiCompanyFactory;
import com.horen.cortp.company.api.ApiRequestPram;
import com.horen.cortp.company.bean.Relation;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxResultHelper;
import com.jaydenxiao.common.baserx.RxSubscriber;

/**
 * Created by Zhao on 2017/12/5/005.
 */

public class RelationDetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView toolBarTitleTv;
    private Toolbar toolBar;
    private TextView tvPartnerShortName;
    private TextView tvPartnerFullName;
    private TextView tvPartnerAddress;
    private Button btPartnerRemove;

    private Relation.OrgPartnersBean partnersBean;

    public static void startAction(Context context, Relation.OrgPartnersBean partnersBean) {
        Intent intent = new Intent();
        intent.setClass(context, RelationDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("partnersBean", partnersBean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_relation_details;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        toolBarTitleTv = (TextView) findViewById(R.id.tool_bar_title_tv);
        tvPartnerShortName = (TextView) findViewById(R.id.tv_partner_short_name);
        tvPartnerFullName = (TextView) findViewById(R.id.tv_partner_full_name);
        tvPartnerAddress = (TextView) findViewById(R.id.tv_partner_address);
        btPartnerRemove = (Button) findViewById(R.id.bt_partner_remove);
        btPartnerRemove.setOnClickListener(this);

        setSimpleToolbar(toolBar, toolBarTitleTv, getString(R.string.mine_list_relation));

        initData();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_partner_remove) {//取消关联
            removeRelation();
        }
    }

    private void initData() {
        partnersBean = (Relation.OrgPartnersBean) getIntent().getSerializableExtra("partnersBean");
        if (null == partnersBean) return;
        //
        tvPartnerShortName.append(partnersBean.getOrg_shortname());
        tvPartnerFullName.append(partnersBean.getOrg_name());
        tvPartnerAddress.append(partnersBean.getOrg_address());
    }

    /**
     * 取消关联
     */
    private void removeRelation() {
//        mRxManager.add(ApiCompanyFactory.getSingleApi().removeRelation(ApiRequestPram.removeRelation(partnersBean.getPartner_id()))
//                .compose(RxResultHelper.<BaseResponse>handleResult())
//                .subscribe(new RxSubscriber<BaseResponse>(this, true) {
//                    @Override
//                    protected void _onNext(BaseResponse o) {
//                        showShortToast(o.getHeader().getRet_message() + "");
//                        //
//                        finish();
//                    }
//
//                    @Override
//                    protected void _onError(String message) {
//                        showErrorToast(message);
//                    }
//                }));
    }
}
