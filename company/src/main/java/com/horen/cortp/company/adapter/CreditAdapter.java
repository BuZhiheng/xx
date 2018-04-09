package com.horen.cortp.company.adapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.cortp.company.R;
import com.horen.cortp.company.bean.CreditInfo;
import java.util.List;
/**
 * Created by BuZhiheng on 2018/02/06.
 */
public class CreditAdapter extends BaseQuickAdapter<CreditInfo.RtpInfo, BaseViewHolder> {
    public CreditAdapter(int layoutResId, List<CreditInfo.RtpInfo> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder holder, CreditInfo.RtpInfo item) {
        holder.setText(R.id.tv_item_cortp, item.rtpType + "");
        try{
            int t = Integer.parseInt(item.totalNum);
            int r = Integer.parseInt(item.remainingNum);
            holder.setText(R.id.tv_item_count, t-r + "");
        } catch (Exception e){
        }
    }
}