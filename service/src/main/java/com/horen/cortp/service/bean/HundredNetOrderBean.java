package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by HOREN on 2017/10/31.
 */

public class HundredNetOrderBean extends BaseResponse {


    /**
     * data : {"pagesize":2147483647,"rows":[{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p001","service_qty":200,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p002","service_qty":200,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p003","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p004","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p005","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p006","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p007","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p008","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p17110900001","service_qty":450,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"}],"total":9,"totalpage":1}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pagesize : 2147483647
         * rows : [{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p001","service_qty":200,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p002","service_qty":200,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p003","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p004","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p005","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p006","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p007","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p008","service_qty":100,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"},{"create_date":"2017-11-02 09:46:02","line_status":"3","line_status_value":"待配送","order_companyname":"上海旺旺公司","order_type":"1","plan_id":"p17110900001","service_qty":450,"to_orgaddress":"上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼"}]
         * total : 9
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
             * create_date : 2017-11-02 09:46:02
             * line_status : 3
             * line_status_value : 待配送
             * order_companyname : 上海旺旺公司
             * order_type : 1
             * plan_id : p001
             * service_qty : 200
             * to_orgaddress : 上海市漕河泾新兴技术开发区田林路487号宝石园20号11楼
             */

            private String create_date;
            private String line_status;
            private String line_status_value;
            private String order_companyname;
            private String order_type;
            private String plan_id;
            private int service_qty;
            private String to_address;
            private String from_address;
            private String company_address;

            public String getCompany_address() {
                return company_address;
            }

            public void setCompany_address(String company_address) {
                this.company_address = company_address;
            }

            public String getTo_address() {
                return to_address;
            }

            public void setTo_address(String to_address) {
                this.to_address = to_address;
            }

            public String getFrom_address() {
                return from_address;
            }

            public void setFrom_address(String from_address) {
                this.from_address = from_address;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getLine_status() {
                return line_status;
            }

            public void setLine_status(String line_status) {
                this.line_status = line_status;
            }

            public String getLine_status_value() {
                return line_status_value;
            }

            public void setLine_status_value(String line_status_value) {
                this.line_status_value = line_status_value;
            }

            public String getOrder_companyname() {
                return order_companyname;
            }

            public void setOrder_companyname(String order_companyname) {
                this.order_companyname = order_companyname;
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

            public int getService_qty() {
                return service_qty;
            }

            public void setService_qty(int service_qty) {
                this.service_qty = service_qty;
            }

            public String getTo_orgaddress() {
                return to_address;
            }

            public void setTo_orgaddress(String to_orgaddress) {
                this.to_address = to_orgaddress;
            }
        }
    }
}
