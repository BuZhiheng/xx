package com.horen.cortp.platform.bean;
/**
 * Created by HOREN on 2017/11/15.
 *
 * 每个接口会返回此json对象，childBaseResult.success()判断接口请求失败与否
 *
 * ret_status>0 表示请求成功，否则读取ret_message
 *
 */
public class BaseResult {
    private ResultHeader header;
    public boolean success(){
        if (header != null && header.ret_status != null){
            String s = header.ret_status;
            int status = Integer.parseInt(s);
            if (status > 0){
                return true;
            }
        }
        return false;
    }
    public String getErrMsg(){
        if (header != null && header.ret_message != null){
            return header.ret_message;
        } else {
            return "error";
        }
    }
    private class ResultHeader {
        public String srv_id;
        public String srv_desc;
        public String ret_status;//ret_status>0表示成功，<0表示失败
        public String ret_value;
        public String ret_message;
    }
}