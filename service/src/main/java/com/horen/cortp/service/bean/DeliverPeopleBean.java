package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HOREN on 2017/11/3.
 */

public class DeliverPeopleBean extends BaseResponse {

    private List<DataBean> deliverList;

    public List<DataBean> getData() {
        return deliverList;
    }

    public void setData(List<DataBean> data) {
        this.deliverList = data;
    }

    public static class DataBean extends BaseIndexPinyinBean implements Serializable{
        /**
         * driver_id : 001
         * driver_name : ywj
         * driver_tel : 13122101234
         */

        private String driver_id;
        private String driver_name;
        private String driver_tel;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getDriver_name() {
            return driver_name;
        }

        public void setDriver_name(String driver_name) {
            this.driver_name = driver_name;
        }

        public String getDriver_tel() {
            return driver_tel;
        }

        public void setDriver_tel(String driver_tel) {
            this.driver_tel = driver_tel;
        }

        @Override
        public String getTarget() {
            return driver_name;
        }
    }
}
