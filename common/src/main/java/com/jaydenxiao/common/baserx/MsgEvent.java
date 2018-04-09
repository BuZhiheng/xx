package com.jaydenxiao.common.baserx;

/**
 * Created by David-Notebook on 2017/6/15.
 */

public class MsgEvent {

    /**
     * 刷新下单地址列表
     */
    public static final int DEFAULT_ADDRESS_SUCCESS = 110501;

    /**
     * 更新地址簿列表
     */
    public static final int UPDATA_ADDRESS_LIST = 110502;


    /**
     * 更新资产总览地图数据
     */
    public static final int UPDATA_EYE_ASSETS_MAP_DATA = 110503;

    /**
     * 已完成登录操作
     */
    public static final int LOGIN_SUCCESS = 110504;
    /**
     * 万箱通知首页服务器超时
     */
    public static final int MAIN_SERVICE_TIMEOUT = 110505;

    /**
     * 设置消息的类型
     */
    public int type;
    public String orderType;

    /**
     * 设置传递的对象
     */
    public Object obj;

    public MsgEvent setType(int type) {
        this.type = type;
        return this;
    }

    public String getOrderType() {
        return orderType;
    }

    public MsgEvent setOrderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public MsgEvent setObj(Object obj) {
        this.obj = obj;
        return this;
    }

}
