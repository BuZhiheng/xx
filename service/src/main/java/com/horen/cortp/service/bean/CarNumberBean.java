package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HOREN on 2017/11/3.
 */

public class CarNumberBean extends BaseResponse {

    private List<DataBean> vehicleList;

    public List<DataBean> getData() {
        return vehicleList;
    }

    public void setData(List<DataBean> data) {
        this.vehicleList = data;
    }

    public static class DataBean    extends BaseIndexPinyinBean implements Serializable {
        /**
         * vehicle_id : v001
         */

        private String vehicle_id;

        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getVehicle_id() {
            return vehicle_id;
        }

        public void setVehicle_id(String vehicle_id) {
            this.vehicle_id = vehicle_id;
        }

        @Override
        public String getTarget() {
            return vehicle_id;
        }
    }
}
