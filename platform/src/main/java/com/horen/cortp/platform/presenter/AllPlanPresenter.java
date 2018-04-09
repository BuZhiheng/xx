package com.horen.cortp.platform.presenter;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.api.MyRequestBody;
import com.horen.cortp.platform.api.MySubscriber;
import com.horen.cortp.platform.bean.ApiResultPlanData;
import com.horen.cortp.platform.bean.ApiResultPlanType;
import com.horen.cortp.platform.bean.PlanType;
import com.horen.cortp.platform.bean.PlanTypeLookup;
import com.horen.cortp.platform.contract.AllPlanContract;
import java.util.List;
import okhttp3.RequestBody;
/**
 * Created by HOREN on 2017/12/12.
 */
public class AllPlanPresenter extends AllPlanContract.Presenter {
    @Override
    public void getPlanList() {
        RequestBody body = new MyRequestBody.Builder()
                .add("solution_type","")
                .add("solution_typename","")
                .add("page","1")
                .add("rows","100")
                .build();
        mRxManage.add(mModel.getPlanList(body, new MySubscriber<ApiResultPlanType>() {
            @Override
            protected void onSuccess(ApiResultPlanType resultData) {
                if (resultData.success()){
                    List<PlanType> listType = resultData.lookup_solutiontypes;
                    List<PlanTypeLookup> listTag = resultData.lookup_productags;
                    List<PlanTypeLookup> listSize = resultData.lookup_productsizes;
                    if (listType != null && listType.size() > 0){
                        listType.get(0).isSelect = true;//设置当前默认选择第一个
                        mView.setListData(listType);//设置列表adapter
                        mView.setPlanBanner(listType.get(0));
                        if (listTag != null ){
                            mView.setTypeTag(listTag);
                        }
                        if (listSize != null){
                            mView.setTypeSize(listSize);
                        }
//                        getPlanData(listType.get(0),null,null);//请求默认方案数据
                    }
                }
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
    public void getPlanData(PlanType type,PlanTypeLookup typeTag,PlanTypeLookup typeSize) {
        MyRequestBody.Builder builder = new MyRequestBody.Builder();
        builder.add("flag_hot","")//热门表示0：非热门 1：热门 4096：全部
                .add("solution_id","")//方案编码
                .add("solution_keyword","")//方案关键字
                .add("solution_name","")//方案名称
                .add("solution_title","")//方案标题
                .add("page","1")
                .add("rows","100");
        if (type != null){
            builder.add("solution_typename",type.solution_typename);//方案行业
        }
        if (typeTag != null){
            builder.add("product_tag",typeTag.lookup_name);//产品形态
        }
        if (typeSize != null){
            builder.add("product_size",typeSize.lookup_name);//产品尺寸
        }
        RequestBody body = builder.build();
        mRxManage.add(mModel.getPlanData(body, new MySubscriber<ApiResultPlanData>() {
            @Override
            protected void onSuccess(ApiResultPlanData resultData) {
                mView.setPlanData(resultData.solutions);
            }
            @Override
            protected void onError(String s) {
                mView.showErrorTip(s);
            }
        }));
    }
}