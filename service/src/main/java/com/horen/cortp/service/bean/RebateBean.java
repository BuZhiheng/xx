package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by HOREN on 2017/12/13.
 */

public class RebateBean extends BaseResponse {


    private List<PayFeeToServerDataBean> payFeeToServerData;

    private String total_income;

    public String getTotal_income() {
        return total_income;
    }

    public void setTotal_income(String total_income) {
        this.total_income = total_income;
    }

    public List<PayFeeToServerDataBean> getPayFeeToServerData() {
        return payFeeToServerData;
    }

    public void setPayFeeToServerData(List<PayFeeToServerDataBean> payFeeToServerData) {
        this.payFeeToServerData = payFeeToServerData;
    }

    public static class PayFeeToServerDataBean {
        /**
         * job_arap : 0.0
         * job_qty : 0
         * order_type : 11
         * serviceOrderCount : 10
         */

        private double job_arap;
        private int job_qty;

        public int getCompany_count() {
            return company_count;
        }

        public void setCompany_count(int company_count) {
            this.company_count = company_count;
        }

        private int company_count;
        private String order_type;
        private int serviceOrderCount;

        public double getJob_arap() {
            return job_arap;
        }

        public void setJob_arap(double job_arap) {
            this.job_arap = job_arap;
        }

        public int getJob_qty() {
            return job_qty;
        }

        public void setJob_qty(int job_qty) {
            this.job_qty = job_qty;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public int getServiceOrderCount() {
            return serviceOrderCount;
        }

        public void setServiceOrderCount(int serviceOrderCount) {
            this.serviceOrderCount = serviceOrderCount;
        }
    }
}
