package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

/**
 * Created by HOREN on 2017/12/14.
 */

public class CreateRepairBean extends BaseResponse {

    /**
     * create_date : 2017-12-14 14:46:07
     * line_status : 待维修
     * workorder_companyname : CoRTP租赁运营中心
     * workorder_id : EF130000000008
     */

    private String create_date;
    private String line_status;
    private String line_status_value;

    public String getLine_status_value() {
        return line_status_value;
    }

    public void setLine_status_value(String line_status_value) {
        this.line_status_value = line_status_value;
    }

    private String workorder_companyname;
    private String workorder_id;
    private String workorder_type;

    public String getWorkorder_type() {
        return workorder_type;
    }

    public void setWorkorder_type(String workorder_type) {
        this.workorder_type = workorder_type;
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

    public String getWorkorder_companyname() {
        return workorder_companyname;
    }

    public void setWorkorder_companyname(String workorder_companyname) {
        this.workorder_companyname = workorder_companyname;
    }

    public String getWorkorder_id() {
        return workorder_id;
    }

    public void setWorkorder_id(String workorder_id) {
        this.workorder_id = workorder_id;
    }
}
