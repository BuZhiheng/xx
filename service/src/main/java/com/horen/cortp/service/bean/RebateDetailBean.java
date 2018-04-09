package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by HOREN on 2017/12/13.
 */

public class RebateDetailBean extends BaseResponse {


    /**
     * payFeeToServerDetail : {"pagesize":10,"rows":[{"create_date":"2017-12-12 09:38:51","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"249ef30a-e320-4b21-8977-5bd0f4b0639d","receive_qty":200,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 11:09:28","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"24d45d4c-9c80-41d9-8766-f6febbbfb53d","receive_qty":107,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 09:47:11","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"7645891f-7281-4722-8277-e7b80b58863d","receive_qty":11,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 10:22:40","from_address":"上海市上海市杨浦区","job_arap":0,"order_type":"11","plan_id":"9dcc1af1-9d4a-4389-bcbe-aeeca3ce5571","receive_qty":512,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 09:35:53","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"d75a323f-bb45-44cc-8349-5431fb1d74b5","receive_qty":0,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市浦东新区宁国路"},{"create_date":"2017-12-12 15:25:15","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"e2da6d49-33dd-45bf-8e70-56cda1ccd5be","receive_qty":100,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 15:56:04","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"f5b91fb7-028b-4625-ad38-423c45c9f764","receive_qty":0,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"}],"total":7,"totalpage":1}
     */

    private PayFeeToServerDetailBean payFeeToServerDetail;
    private String single_total_income;

    public String getSingle_total_income() {
        return single_total_income;
    }

    public void setSingle_total_income(String single_total_income) {
        this.single_total_income = single_total_income;
    }

    public PayFeeToServerDetailBean getPayFeeToServerDetail() {
        return payFeeToServerDetail;
    }

    public void setPayFeeToServerDetail(PayFeeToServerDetailBean payFeeToServerDetail) {
        this.payFeeToServerDetail = payFeeToServerDetail;
    }

    public static class PayFeeToServerDetailBean {
        /**
         * pagesize : 10
         * rows : [{"create_date":"2017-12-12 09:38:51","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"249ef30a-e320-4b21-8977-5bd0f4b0639d","receive_qty":200,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 11:09:28","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"24d45d4c-9c80-41d9-8766-f6febbbfb53d","receive_qty":107,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 09:47:11","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"7645891f-7281-4722-8277-e7b80b58863d","receive_qty":11,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 10:22:40","from_address":"上海市上海市杨浦区","job_arap":0,"order_type":"11","plan_id":"9dcc1af1-9d4a-4389-bcbe-aeeca3ce5571","receive_qty":512,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 09:35:53","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"d75a323f-bb45-44cc-8349-5431fb1d74b5","receive_qty":0,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市浦东新区宁国路"},{"create_date":"2017-12-12 15:25:15","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"e2da6d49-33dd-45bf-8e70-56cda1ccd5be","receive_qty":100,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"},{"create_date":"2017-12-12 15:56:04","from_address":"上海市上海市虹口区海宁路246号","job_arap":0,"order_type":"11","plan_id":"f5b91fb7-028b-4625-ad38-423c45c9f764","receive_qty":0,"service_companyid":"CM00000260","service_companyname":"仓储中心-上海地区","to_address":"上海市上海市浦东新区"}]
         * total : 7
         * totalpage : 1
         */

        private int pagesize;
        private int total;
        private int totalpage;
        private List<RowsBean> rows;

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * create_date : 2017-12-12 09:38:51
             * from_address : 上海市上海市虹口区海宁路246号
             * job_arap : 0.0
             * order_type : 11
             * plan_id : 249ef30a-e320-4b21-8977-5bd0f4b0639d
             * receive_qty : 200
             * service_companyid : CM00000260
             * service_companyname : 仓储中心-上海地区
             * to_address : 上海市上海市浦东新区
             */

            private String create_date;
            private String from_address;
            private double job_arap;
            private String order_type;
            private String plan_id;
            private int receive_qty;
            private String service_companyid;
            private String service_companyname;
            private String to_address;
            private String order_companyname;

            public String getOrder_companyname() {
                return order_companyname;
            }

            public void setOrder_companyname(String order_companyname) {
                this.order_companyname = order_companyname;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getFrom_address() {
                return from_address;
            }

            public void setFrom_address(String from_address) {
                this.from_address = from_address;
            }

            public double getJob_arap() {
                return job_arap;
            }

            public void setJob_arap(double job_arap) {
                this.job_arap = job_arap;
            }

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public String getPlan_id() {
                return plan_id;
            }

            public void setPlan_id(String plan_id) {
                this.plan_id = plan_id;
            }

            public int getReceive_qty() {
                return receive_qty;
            }

            public void setReceive_qty(int receive_qty) {
                this.receive_qty = receive_qty;
            }

            public String getService_companyid() {
                return service_companyid;
            }

            public void setService_companyid(String service_companyid) {
                this.service_companyid = service_companyid;
            }

            public String getService_companyname() {
                return service_companyname;
            }

            public void setService_companyname(String service_companyname) {
                this.service_companyname = service_companyname;
            }

            public String getTo_address() {
                return to_address;
            }

            public void setTo_address(String to_address) {
                this.to_address = to_address;
            }
        }
    }
}
