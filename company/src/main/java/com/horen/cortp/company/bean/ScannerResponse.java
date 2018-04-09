package com.horen.cortp.company.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by Zhao on 2017/11/20/020.
 */

public class ScannerResponse extends BaseResponse {

    private OrderinfoBean orderinfo;
    private List<ProductsBean> products;
    private List<PicsInfoBean> picsInfo;

    public List<PicsInfoBean> getPicsInfo() {
        return picsInfo;
    }

    public void setPicsInfo(List<PicsInfoBean> picsInfo) {
        this.picsInfo = picsInfo;
    }

    public OrderinfoBean getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(OrderinfoBean orderinfo) {
        this.orderinfo = orderinfo;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class OrderinfoBean {
        /**
         * create_date : 2017-11-03 08:42:25
         * driver_id : 琼B88688
         * driver_name : 赵丽颖
         * driver_tel : 15639700001
         * line_status : 4
         * order_companyid : oc001
         * order_companyname : 上海旺旺公司
         * order_id : cn20171103_01
         * order_lineid : cn20171103_01_01
         * order_type : 1
         * plan_id : p001
         * plan_lineid : p001_1
         * to_companyid : tc001
         * to_companyname : 收货公司上海旺旺公司
         * to_orgaddress :
         * to_orgcontact : yy
         * to_orgtel : 13122107251
         * vehicle_id : 琼B88688
         */
        private String picPath;

        public String getPicPath() {
            return picPath;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        private String create_date;
        private String driver_id;
        private String driver_name;
        private String driver_tel;
        private String line_status;
        private String line_status_value;
        private String order_companyid;
        private String order_companyname;
        private String order_id;
        private String order_lineid;
        private String order_type;
        private String plan_id;
        private String plan_lineid;
        private String to_companyid;
        private String to_companyname;
        private String to_address;
        private String to_contact;
        private String to_tel;
        private String vehicle_id;
        private String to_orgname;
        private String loss_qty;
        private String damage_qty;
        private String receive_date;
        private String audit_date;

        public String getAudit_date() {
            return audit_date;
        }

        public void setAudit_date(String audit_date) {
            this.audit_date = audit_date;
        }

        public String getReq_deliverdate() {
            return req_deliverdate;
        }

        public void setReq_deliverdate(String req_deliverdate) {
            this.req_deliverdate = req_deliverdate;
        }

        private String req_deliverdate;

        public String getReceive_date() {
            return receive_date;
        }

        public void setReceive_date(String receive_date) {
            this.receive_date = receive_date;
        }

        public String getLoss_qty() {
            return loss_qty;
        }

        public void setLoss_qty(String loss_qty) {
            this.loss_qty = loss_qty;
        }

        public String getDamage_qty() {
            return damage_qty;
        }

        public void setDamage_qty(String damage_qty) {
            this.damage_qty = damage_qty;
        }

        public String getTo_orgname() {
            return to_orgname;
        }

        public void setTo_orgname(String to_orgname) {
            this.to_orgname = to_orgname;
        }

        public String getLine_status_value() {
            return line_status_value;
        }

        public void setLine_status_value(String line_status_value) {
            this.line_status_value = line_status_value;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
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

        public String getLine_status() {
            return line_status;
        }

        public void setLine_status(String line_status) {
            this.line_status = line_status;
        }

        public String getOrder_companyid() {
            return order_companyid;
        }

        public void setOrder_companyid(String order_companyid) {
            this.order_companyid = order_companyid;
        }

        public String getOrder_companyname() {
            return order_companyname;
        }

        public void setOrder_companyname(String order_companyname) {
            this.order_companyname = order_companyname;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_lineid() {
            return order_lineid;
        }

        public void setOrder_lineid(String order_lineid) {
            this.order_lineid = order_lineid;
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

        public String getPlan_lineid() {
            return plan_lineid;
        }

        public void setPlan_lineid(String plan_lineid) {
            this.plan_lineid = plan_lineid;
        }

        public String getTo_companyid() {
            return to_companyid;
        }

        public void setTo_companyid(String to_companyid) {
            this.to_companyid = to_companyid;
        }

        public String getTo_companyname() {
            return to_companyname;
        }

        public void setTo_companyname(String to_companyname) {
            this.to_companyname = to_companyname;
        }

        public String getTo_orgaddress() {
            return to_address;
        }

        public void setTo_orgaddress(String to_orgaddress) {
            this.to_address = to_orgaddress;
        }

        public String getTo_orgcontact() {
            return to_contact;
        }

        public void setTo_orgcontact(String to_orgcontact) {
            this.to_contact = to_orgcontact;
        }

        public String getTo_orgtel() {
            return to_tel;
        }

        public void setTo_orgtel(String to_orgtel) {
            this.to_tel = to_orgtel;
        }

        public String getVehicle_id() {
            return vehicle_id;
        }

        public void setVehicle_id(String vehicle_id) {
            this.vehicle_id = vehicle_id;
        }
    }

    public static class ProductsBean {
        /**
         * plan_qty : 20
         * product_color : 红色
         * product_id : pid001
         * product_name : if1420
         * service_qty : 0
         */

        private int plan_qty;
        private String product_color;
        private String product_id;
        private String product_name;
        private int service_qty;
        private int receive_qty;
        private int reject_qty;
        private int currentNumber;
        private int total_reject_qty;

        public int getTotal_reject_qty() {
            return total_reject_qty;
        }

        public void setTotal_reject_qty(int total_reject_qty) {
            this.total_reject_qty = total_reject_qty;
        }

        public int getCurrentNumber() {
            return currentNumber;
        }

        public void setCurrentNumber(int currentNumber) {
            this.currentNumber = currentNumber;
        }

        public int getReceive_qty() {
            return receive_qty;
        }

        public void setReceive_qty(int receive_qty) {
            this.receive_qty = receive_qty;
        }

        public int getReject_qty() {
            return reject_qty;
        }

        public void setReject_qty(int reject_qty) {
            this.reject_qty = reject_qty;
        }

        public int getPlan_qty() {
            return plan_qty;
        }

        public void setPlan_qty(int plan_qty) {
            this.plan_qty = plan_qty;
        }

        public String getProduct_color() {
            return product_color;
        }

        public void setProduct_color(String product_color) {
            this.product_color = product_color;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public int getService_qty() {
            return service_qty;
        }

        public void setService_qty(int service_qty) {
            this.service_qty = service_qty;
        }
    }

    public static class PicsInfoBean {
        /**
         * damagePics : ["http://www","http://www"]
         * damageType : 1
         * damagerRemark : 箱子破了
         * filepath : http://www
         * id : 001
         * reference_id :
         */

        private String damageType;
        private String damageRemark;
        private String id;
        private String reference_id;
        private List<String> damagePics;

        public String getDamageType() {
            return damageType;
        }

        public void setDamageType(String damageType) {
            this.damageType = damageType;
        }

        public String getDamagerRemark() {
            return damageRemark;
        }

        public void setDamagerRemark(String damagerRemark) {
            this.damageRemark = damagerRemark;
        }



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReference_id() {
            return reference_id;
        }

        public void setReference_id(String reference_id) {
            this.reference_id = reference_id;
        }

        public List<String> getDamagePics() {
            return damagePics;
        }

        public void setDamagePics(List<String> damagePics) {
            this.damagePics = damagePics;
        }
    }
}
