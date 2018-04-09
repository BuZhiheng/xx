package com.horen.cortp.service.bean;

/**
 * Created by David-Notebook on 2017/6/15.
 */

public class MsgEvent {

    /**
     * 查看网点详情页面消失关闭Marker的Window的显示
     */
    public static final int CLOSE_MARKER_WINDOW_ORG = 110500;

    /**
     * 查看平台网点详情页面消失关闭Marker的Window的显示
     */
    public static final int CLOSE_MARKER_WINDOW_IBC = 110501;

    /**
     * 添加企业成员后刷新UI
     */
    public static final int ADD_USER_SUCCEND = 110502;

    /**
     * 添加通讯录成员后刷新UI
     */
    public static final int ADD_CONTACT_SUCCEND = 110503;

    /**
     * 解除关联企业后刷新UI
     */
    public static final int DELETE_PARTNER_SUCCENT = 110504;

    /**
     * 获取到推送的消息
     */
    public static final int SHOW_MESSAGE = 110505;

    /**
     * MainActivity-finish消息小红点
     */
    public static final int FINISH_MESSAGE = 110506;

    /**
     * 关联网点成功刷新UI
     */
    public static final int RELEVANCE_PARTNER_SUCCEND = 110507;

    /**
     * finish掉MainActivity
     */
    public static final int FINISH_MAIN_ACTIVITY = 110508;

    /**
     * 读取消息之后刷新UI
     */
    public static final int MESSAGE_STATUS_SUCCEED = 110509;

    /**
     * 快速体验完成销毁之前的注册页
     */
    public static final int FINISH_REGISTER_ACTIVITY = 110511;

    /**
     * 选择物品(箱型/配件/液袋)完成
     */
    public static final int ORDER_RES_SELECT = 110512;


    public static final int FINISH_DILIVERY_ACTIVITY = -1;

    /**
     * 刷新列表
     */
    public static final int REFRESH_ORDER_LIST = -2;
    /**
     * 刷新列表单个条目的数据
     */
    public static final int REFRESH_ORDER_INFO = -3;

    /**
     * 刷新确认损坏中数据
     */
    public static final int REFRESH_COOLECT_BOXS = -4;

    /**
     * 关闭确认损坏中页面
     */
    public static final int FINISH_COOLECT_BOXS_CONFIRM = -5;

    /**
     * 刷新下单地址列表
     */
    public static final int DEFAULT_ADDRESS_SUCCESS = 110513;

    /**
     * 更新地址簿列表
     */
    public static final int UPDATA_ADDRESS_LIST = 110514;

    /**
     * 更新联系人列表
     */
    public static final int UPDATA_CONTACTS_LIST = 110515;

    /**
     * 更新车牌号列表
     */
    public static final int UPDATA_CAR_NUMBER_LIST = 110516;

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
