package com.horen.cortp.service.bean;

import java.util.List;

/**
 * Created by HOREN on 2017/12/15.
 */

public class WorkorderPhotosBean {

    private String detail_note;
    private String product_id;
    private String product_sn;
    private String workorder_type;
    private String problem_type;

    public String getProblem_type() {
        return problem_type;
    }

    public void setProblem_type(String problem_type) {
        this.problem_type = problem_type;
    }

    public String getDetail_note() {
        return detail_note;
    }

    public void setDetail_note(String detail_note) {
        this.detail_note = detail_note;
    }

    public WorkorderPhotosBean(String detail_note, String product_sn, String problem_type, List<String> photo_url_list) {
        this.detail_note = detail_note;
        this.product_sn = product_sn;
        this.problem_type = problem_type;
        this.photo_url_list = photo_url_list;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_sn() {
        return product_sn;
    }

    public void setProduct_sn(String product_sn) {
        this.product_sn = product_sn;
    }

    public String getWorkorder_type() {
        return workorder_type;
    }

    public void setWorkorder_type(String workorder_type) {
        this.workorder_type = workorder_type;
    }

    public List<String> getPhoto_url_list() {
        return photo_url_list;
    }

    public void setPhoto_url_list(List<String> photo_url_list) {
        this.photo_url_list = photo_url_list;
    }

    private List<String> photo_url_list;



}
