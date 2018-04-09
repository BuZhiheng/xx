package com.horen.cortp.service.mvp.persenter;

import android.text.TextUtils;
import android.widget.EditText;

import com.horen.cortp.service.R;
import com.horen.cortp.service.adapter.BoxsInfoAdapter;
import com.horen.cortp.service.adapter.CollectBoxsCompleteGoodsAdapter;
import com.horen.cortp.service.adapter.CollectGoodsAdapter;
import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.horen.cortp.service.mvp.contract.CollectBoxCollectingContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.basebean.SendBoxOrderDetailBean;
import com.jaydenxiao.common.baserx.RxSubscriber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by HOREN on 2017/10/31.
 */

public class CollectBoxsCollectingPresenter extends CollectBoxCollectingContract.Presenter {

    /**
     * 订单详情
     *
     * @param body
     */
    @Override
    public void queryOrderDetail(RequestBody body) {
        mRxManage.add(mModel.queryDeliveringOrderDetail(body)
                .subscribe(new RxSubscriber<SendBoxOrderDetailBean>(mContext, true) {
                    @Override
                    protected void _onNext(SendBoxOrderDetailBean orderBean) {
                        mView.getOrderDetailSuccess(orderBean);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    /**
     * 保存数据
     *
     * @param body
     */
    @Override
    public void saveFetchOrder(RequestBody body) {
        mRxManage.add(mModel.saveFetchOrder(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.saveFetchOrderSuccess();
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    /**
     * 提交数据
     *
     * @param body
     */
    @Override
    public void submitFetchOrder(RequestBody body) {
        mRxManage.add(mModel.submitFetchOrder(body)
                .subscribe(new RxSubscriber<BaseResponse>(mContext, true) {
                    @Override
                    protected void _onNext(BaseResponse baseResponse) {
                        mView.submitFetchOrderSuccess();
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    /**
     * 上传签收单
     */
    @Override
    public void uploadPhoto(String plan_id, ArrayList<String> photos) {
        mRxManage.add(mModel.uploadImgs(mContext, plan_id, photos)
                .subscribe(new RxSubscriber<UploadSinglePhotoBean>(mContext, true) {
                    @Override
                    protected void _onNext(UploadSinglePhotoBean uploadSinglePhotoBean) {
                        mView.uploadPhotoSuccess(uploadSinglePhotoBean.getFilepath());
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.onError(message);
                    }
                })
        );
    }

    /**
     * 提交数据
     *
     * @param etLoseNumber
     * @param type
     * @param splitOrderAdapter
     * @param completeGoodsAdapter
     * @param collectBoxsAdapter
     * @param detailBean
     */
    @Override
    public void submit(EditText etLoseNumber, String type, CollectGoodsAdapter splitOrderAdapter, CollectBoxsCompleteGoodsAdapter completeGoodsAdapter, BoxsInfoAdapter collectBoxsAdapter, SendBoxOrderDetailBean detailBean) {
        String damage_qty = "";
        if (type.equals("0")) { // 收箱中
            if (splitOrderAdapter != null) {
                // 过滤出有数量的货物列表
                List<SendBoxOrderDetailBean.ProductsBean> productsBeans = new ArrayList<>();
                for (SendBoxOrderDetailBean.ProductsBean productsBean : splitOrderAdapter.getData()) {
                    if (productsBean.getReject_qty() > 0)
                        productsBeans.add(productsBean); // 配送数量不为0，需要配送
                }
                if (productsBeans.size() > 0) { // 收货物品个数不能为0
                    mView.submitCheckSuccess("", damage_qty, splitOrderAdapter.getData(), collectBoxsAdapter.getData());
                } else {
                    mView.showErrorTip(mContext.getString(R.string.select_collect_goods));
                }
            }
        } else { //确认损坏中修改
            if (completeGoodsAdapter != null) {
                // 判断丢失箱数量相加坏损箱数量不能大于总箱数
                if (checkChangeData(detailBean.getProducts(), "", damage_qty)) {
                    mView.submitCheckSuccess("", damage_qty, detailBean.getProducts(), collectBoxsAdapter.getData());
                } else {
                    mView.showErrorTip(mContext.getString(R.string.collect_boxs_check_tip));
                }

            }
        }
    }

    /**
     * 保存数据
     *
     * @param etLoseNumber
     * @param type
     * @param splitOrderAdapter
     * @param completeGoodsAdapter
     * @param collectBoxsAdapter
     * @param detailBean
     */
    @Override
    public void save(EditText etLoseNumber, String type, CollectGoodsAdapter splitOrderAdapter, CollectBoxsCompleteGoodsAdapter completeGoodsAdapter, BoxsInfoAdapter collectBoxsAdapter, SendBoxOrderDetailBean detailBean) {
        String loss_qty = etLoseNumber.getText().toString().trim();
        String damage_qty = "0";
        if (type.equals("0")) { // 收箱中
            if (splitOrderAdapter != null) {

                // 过滤出有数量的货物列表
                List<SendBoxOrderDetailBean.ProductsBean> productsBeans = new ArrayList<>();
                for (SendBoxOrderDetailBean.ProductsBean productsBean : splitOrderAdapter.getData()) {
                    if (productsBean.getReject_qty() > 0)
                        productsBeans.add(productsBean); // 配送数量不为0，需要配送
                }
                if (productsBeans.size() > 0) { // 收货物品个数不能为0
                    mView.saveCheckSuccess(loss_qty, damage_qty, splitOrderAdapter.getData(), collectBoxsAdapter.getData());
                } else {
                    mView.showErrorTip(mContext.getString(R.string.select_collect_goods));
                }
            }
        } else { //确认损坏中修改
            if (completeGoodsAdapter != null) {
                mView.saveCheckSuccess(loss_qty, damage_qty, detailBean.getProducts(), collectBoxsAdapter.getData());
            }
        }
    }

    @Override
    public boolean checkData(List<SendBoxOrderDetailBean.ProductsBean> listByMap, String loss_qty, String damage_qty) {
        // 记录用户输入的所有收货数量
        int count = 0;
        for (SendBoxOrderDetailBean.ProductsBean productsBean : listByMap) {
            count += productsBean.getReject_qty();
        }
        // 取出两个输入框的数量判断
        if (TextUtils.isEmpty(loss_qty)) {
            loss_qty = "0";
        }
        if (TextUtils.isEmpty(damage_qty)) {
            damage_qty = "0";
        }
        Integer loseNumber = Integer.valueOf(loss_qty);
        Integer damagenumber = Integer.valueOf(damage_qty);
        return loseNumber + damagenumber <= count ? true : false;
    }

    @Override
    public boolean checkChangeData(List<SendBoxOrderDetailBean.ProductsBean> products, String loss_qty, String damage_qty) {
        // 记录用户输入的所有收货数量
        int count = 0;
        for (SendBoxOrderDetailBean.ProductsBean productsBean : products) {
            count += productsBean.getReceive_qty();
        }
        // 取出两个输入框的数量判断
        if (TextUtils.isEmpty(loss_qty)) {
            loss_qty = "0";
        }
        if (TextUtils.isEmpty(damage_qty)) {
            damage_qty = "0";
        }
        Integer loseNumber = Integer.valueOf(loss_qty);
        Integer damagenumber = Integer.valueOf(damage_qty);
        // 取出两个输入框的数量判断
        return loseNumber + damagenumber <= count ? true : false;
    }


    /**
     * 根据map返回key和value的list
     *
     * @param map
     * @return
     */
    public static List<SendBoxOrderDetailBean.ProductsBean> getListByMap(LinkedHashMap<Integer, SendBoxOrderDetailBean.ProductsBean> map) {
        List<SendBoxOrderDetailBean.ProductsBean> list = new ArrayList<>();

        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            list.add(map.get(key));
        }
        return list;
    }

}
