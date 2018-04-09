package com.horen.cortp.company.presenter;
import com.horen.cortp.company.api.ApiRequestPram;
import com.horen.cortp.company.bean.CreditInfo;
import com.horen.cortp.company.contract.CreditLimitContract;
import com.jaydenxiao.common.basebean.BaseResponse;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.SPUtils;
import com.jaydenxiao.common.commonutils.SpKey;
import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Created by HOREN on 2018/2/5.
 */
public class CreditLimitPresenter extends CreditLimitContract.Presenter {
    @Override
    public void initCreditData() {
        mRxManage.add(mModel.getCreditData(ApiRequestPram.getDefaultPram()).subscribe(new RxSubscriber<CreditInfo>(mContext, true) {
            @Override
            protected void _onNext(CreditInfo info) {
                CreditInfo.Credit credit = info.creditInfo;
                if ("C".equals(credit.creditType)){//审核通过
                    float f = Float.parseFloat(credit.remainingNum);
                    float fT = Float.parseFloat(credit.totalNum);
                    float per = (f*100/fT);
                    credit.remainingNum = (int)f+"";
                    credit.totalNum = (int)fT+"";
                    mView.setData(per, credit);
                } else if ("D".equals(credit.creditType)){//审核驳回
                    mView.setUnReviewed(credit.creditMessage);
                } else if ("A".equals(credit.creditType)||"B".equals(credit.creditType)){//审核中
                    mView.setUnderReviewed(credit.creditMessage);
                }
                SPUtils.setSharedStringData(mContext, SpKey.CREDIT_INFO, credit.creditType);
                SPUtils.setSharedStringData(mContext, SpKey.LEASING_EACHDAYS, credit.leasing_eachdays); // 保持周期
            }
            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void applyCredit() {
        mRxManage.add(mModel.reapplyCrsadeditData(ApiRequestPram.getDefaultPram()).subscribe(new RxSubscriber<BaseResponse>(mContext, false) {
            @Override
            protected void _onNext(BaseResponse info) {
                mView.setApplySuccess();
            }
            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}