package com.horen.cortp.company.bean;
import com.jaydenxiao.common.basebean.BaseResponse;
import java.util.List;
/**
 * Created by HOREN on 2018/2/6.
 * 授信额度页面
 * creditType=1 审核通过 显示rtpList
 * 2 审核驳回
 * 3 审核中
 */
public class CreditInfo extends BaseResponse {
    public Credit creditInfo;
    public class Credit{
        public String creditType;
        public String creditMessage;
        public String remainingNum;
        public String totalNum;
        public String leasing_eachdays;
        public List<RtpInfo> rtpList;
    }
    public class RtpInfo{
        public String totalNum;
        public String remainingNum;
        public String rtpType;
    }
}