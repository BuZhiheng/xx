package com.jaydenxiao.common.api;

/**
 * Created by HOREN on 2017-06-07.
 */

public class HttpUrl {

    /**
     * 服务器地址
     */
    //正式服务器
    public static final String SERVER = "http://www.cortp.com";
//    开发服务器
//    public static final String SERVER = "http://61.153.224.202:9990";
//    public static final String SERVER = "http://172.20.246.125:8080/icortpweb";// 杨卫佳
//    public static final String SERVER = "http://192.168.0.107:8080/icortpweb";// 杨卫佳(家)
//    public static final String SERVER = "http://172.20.249.123:8080/cortpweb";// 郭
//    public static final String SERVER = "http://172.20.249.250:8080/cortpweb";// 张
//    public static final String SERVER = "http://172.20.249.84:8080/cortpweb";// Tony
//    public static final String SERVER = "http://172.20.255.195:8282/cortpweb";// 弗兰克（张）
//    public static final String SERVER = "http://172.20.255.116:8080/cortpweb";// 郭工本机
    //测试服务器地址
//    public static final String SERVER = "http://61.153.224.202:9095";
//    public static final String SERVER = "http://172.20.255.149:8888/cortpweb";
//    public static final String SERVER = "http://121.43.164.164:9995";
//    public static final String SERVER = "http://121.43.164.164";

    /**
     * 异常报告_H5
     */
    public static final String GET_EXCEPTION_REPORT_DETAIL = SERVER + "/expheader/to_ex_line";

    /**
     * 关于箱箱_H5
     */
    public static final String ABOUT_WEB = SERVER + "/help/about";
//    public static final String ABOUT_WEB = "http://172.20.255.149:8888/cortpweb/help/about";//tony

    /**
     * 扫码签收_H5
     */
    public static final String SCAN_SIGN = SERVER + "/ConfirmReceipt/index.html";
//    public static final String SCAN_SIGN = "http://172.20.247.160/ConfirmReceipt/";//Jimmie

    /**
     * 维修详情_H5
     */
    public static final String KEEP_DETAILS = SERVER + "/AbnormalReport/index.html";
//    public static final String KEEP_DETAILS = "http://192.168.30.123/AbnormalReport/index.html";//Jimmie

    /**
     * 新版天眼_H5
     */
    public static final String NEW_EYE_HOME = SERVER + "/eye/index.html";

    /**
     * 服务协议_H5
     */
    public static final String SERVICE_AGREEMENT = SERVER + "/protocol/index.html";
//    public static final String KEEP_DETAILS = ;//Jimmie

    /**
     * 企业端查看物流详情_H5
     */
    public static final String LOGISTICS_DETAIL = SERVER + "/track/index.html";
//    public static final String LOGISTICS_DETAIL = "http://192.168.30.123/cortp-track/index.html";//Jimmie
}
