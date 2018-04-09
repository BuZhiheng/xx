package com.horen.cortp.service.bean;

/**
 * Created by HOREN on 2017/11/20.
 */

public class ScanBean  {
    private String service_token;
    private String service_companyid;
    private String order_type;
    private String plan_id;
    private String line_status;


    public ScanBean(String service_token, String service_companyid, String order_type, String plan_id, String line_status) {
        this.service_token = service_token;
        this.service_companyid = service_companyid;
        this.order_type = order_type;
        this.plan_id = plan_id;
        this.line_status = line_status;
    }

    public String getService_token() {
        return service_token;
    }

    public void setService_token(String service_token) {
        this.service_token = service_token;
    }

    public String getService_companyid() {
        return service_companyid;
    }

    public void setService_companyid(String service_companyid) {
        this.service_companyid = service_companyid;
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

    public String getLine_status() {
        return line_status;
    }

    public void setLine_status(String line_status) {
        this.line_status = line_status;
    }
}
