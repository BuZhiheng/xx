package com.horen.cortp.platform.presenter;
import android.support.v7.widget.RecyclerView;
import com.horen.cortp.platform.api.MyRequestBody;
import com.horen.cortp.platform.api.MySubscriber;
import com.horen.cortp.platform.bean.ApiResultHomeData;
import com.horen.cortp.platform.bean.ApiResultPartnerList;
import com.horen.cortp.platform.bean.Plan;
import com.horen.cortp.platform.bean.PlanTypeList;
import com.horen.cortp.platform.contract.PlatformHomeContract;
import com.jaydenxiao.common.baserx.MsgEvent;
import com.jaydenxiao.common.baserx.RxSubscriber;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedList;
import java.util.List;
import okhttp3.RequestBody;
/**
 * Created by HOREN on 2017/11/15.
 */
public class PlatformPresenter extends PlatformHomeContract.Presenter {
    private int page = 2;
    private List<Object> datas = new LinkedList<>();
    @Override
    public void getData() {
        page = 2;
        RequestBody body = new MyRequestBody
                .Builder()
                .add("page","1")
                .add("rows","100")
                .build();
        mRxManage.add(mModel.getData(body, new RxSubscriber<ApiResultHomeData>(mContext,false) {
            @Override
            public void _onNext(ApiResultHomeData homeData) {
                if (homeData.success()){
                    if (homeData.solution_homebanners != null && homeData.solution_homebanners.size() > 0){
                        mView.setBanner(homeData.solution_homebanners);
                    }
                    if (homeData.solution_homehotsolutions != null &&homeData.solution_homehotsolutions.size() > 0){
                        List<PlanTypeList> sol = homeData.solution_homehotsolutions;
                        for (int i=0;i<sol.size();i++){
                            PlanTypeList typeList = sol.get(i);
                            typeList.tabIndex = i;
                            datas.add(typeList);
                            List<Plan> plans = typeList.type_solutions;
                            for (int j=0;j<plans.size();j++){
                                datas.add(plans.get(j));
                            }
                        }
                        mView.setData(datas);
                        for (int d=0;d<datas.size();d++){
                            Object o = datas.get(d);
                            if (o instanceof PlanTypeList){
                                PlanTypeList p = (PlanTypeList) o;
                                mView.setTab(p.solution_typename,d);
                            }
                        }
                    }
                } else {
                    mView.stopLoading();
                }
            }
            @Override
            protected void _onError(String s) {
                mView.showErrorTip(s);
            }
            @Override
            protected void _onTimeOut(String msg) {
                //通知mainactivity显示服务器超时
                mView.stopLoading();
                EventBus.getDefault().post(new MsgEvent().setType(MsgEvent.MAIN_SERVICE_TIMEOUT));
            }
        }));
    }
    @Override
    public void getDataLoadPartner() {
        RequestBody body = new MyRequestBody.Builder()
                .add("page",page+"")
                .add("rows","10")
                .build();
        page++;
        mRxManage.add(mModel.getDataPartner(body, new MySubscriber<ApiResultPartnerList>() {
            @Override
            protected void onSuccess(ApiResultPartnerList resultData) {
                if (resultData.success() && resultData.solution_companys != null && resultData.solution_companys.size() > 0){
                    mView.addPartner(resultData.solution_companys);
                } else {
                    mView.stopLoading();
                }
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
    @Override
    public void changeTabLayout(RecyclerView recyclerView, int firstItemPosition) {
        Object o = datas.get(firstItemPosition);
        if (o instanceof PlanTypeList){
            PlanTypeList planTypeList = (PlanTypeList) o;
            Integer tag = (Integer) recyclerView.getTag();
            if (tag == null || tag != planTypeList.tabIndex){
                mView.updateTabLayout(planTypeList.tabIndex);
                recyclerView.setTag(planTypeList.tabIndex);
            }
        }
    }
}