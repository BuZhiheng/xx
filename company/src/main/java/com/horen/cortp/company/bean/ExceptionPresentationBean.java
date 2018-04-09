package com.horen.cortp.company.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HOREN on 2017/10/18.
 */

public class ExceptionPresentationBean extends BaseResponse {

    /**
     * data : [{"ex_id":"======","message":"555555555555","process":"777777","result":"丢失","time":"2017-10-17"},{"ex_id":"======","message":"555555555555","process":"777777","result":"丢失","time":"2017-10-17"}]
     * header : {"ret_message":"获取数据成功.","ret_status":"1","ret_value":"消息","srv_desc":"","srv_id":""}
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * ex_id : ======
         * message : 555555555555
         * process : 777777
         * result : 丢失
         * time : 2017-10-17
         */

        private String ex_id;
        private String message;
        private String process;
        private String result;
        private String time;

        public String getEx_id() {
            return ex_id;
        }

        public void setEx_id(String ex_id) {
            this.ex_id = ex_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
