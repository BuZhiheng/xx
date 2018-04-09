package com.horen.cortp.company.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

/**
 * Created by Zhao on 2017/11/9/009.
 */

public class CancelQty extends BaseResponse {

    /**
     * header : {"srv_id":"","srv_desc":"","ret_status":"1","ret_value":"消息","ret_message":"获取数据成功."}
     * cancel_qty : 1
     */

    private String cancel_qty;


    public String getCancel_qty() {
        return cancel_qty;
    }

    public void setCancel_qty(String cancel_qty) {
        this.cancel_qty = cancel_qty;
    }

}
