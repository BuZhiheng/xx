package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by HOREN on 2017/12/15.
 */

public class RepairDetailBean extends BaseResponse {

    /**
     * create_date : 2017-12-15 09:23:17
     * line_status : 待维修
     * picsInfo : [{"damagePics":["http://172.20.246.125:8080/uploadimg/61352d7f93f8491faa7fe1f7234f9950.png","http://172.20.246.125:8080/uploadimg/2e18d724a34641558979fe6d1363449f.png","http://172.20.246.125:8080/uploadimg/1b060ee1902241acbd38748377b2d396.png","http://172.20.246.125:8080/uploadimg/059ee2251c4d4c40a0a675a4a6710bae.png"],"damageRemark":"看看","damageType":"上盖损坏","filepath":"http://172.20.246.125:8080/uploadimg/61352d7f93f8491faa7fe1f7234f9950.png","id":"TP000000000831","order_id":"EF130000000062","reference_id":"http://tp.mmxchina.com/?extinfo=PF0TXT1V"}]
     * workorder_companyname : CoRTP租赁运营中心
     * workorder_id : EF130000000062
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
    private List<PicsInfoBean> picsInfo;

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

    public List<PicsInfoBean> getPicsInfo() {
        return picsInfo;
    }

    public void setPicsInfo(List<PicsInfoBean> picsInfo) {
        this.picsInfo = picsInfo;
    }


}
