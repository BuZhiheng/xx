package com.horen.cortp.company.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by David-Notebook on 2017/10/17.
 */

public class AssetTop extends BaseResponse{


    /**
     * owner : {"company_name":"CoRTP租赁运营中心","rtp_total":18,"rtp_empty":0,"company_id":"CM00000196","rtp_full":0}
     * type_group : [{"rtp_total":17,"ctnr_type":"IF1040","rtp_empty":17,"rtp_full":0},{"rtp_total":1,"ctnr_type":"OF1040","rtp_empty":1,"rtp_full":0}]
     * header : {"srv_desc":"","ret_value":"消息","srv_id":"","ret_status":"1","ret_message":"获取数据成功."}
     */

    private OwnerBean owner;
    private List<TypeGroupBean> type_group;

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public List<TypeGroupBean> getType_group() {
        return type_group;
    }

    public void setType_group(List<TypeGroupBean> type_group) {
        this.type_group = type_group;
    }

    public static class OwnerBean implements Serializable{
        /**
         * company_name : CoRTP租赁运营中心
         * rtp_total : 18
         * rtp_empty : 0
         * company_id : CM00000196
         * rtp_full : 0
         */

        private String company_name;
        private int rtp_total;
        private int rtp_empty;
        private String company_id;
        private int rtp_full;

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public int getRtp_total() {
            return rtp_total;
        }

        public void setRtp_total(int rtp_total) {
            this.rtp_total = rtp_total;
        }

        public int getRtp_empty() {
            return rtp_empty;
        }

        public void setRtp_empty(int rtp_empty) {
            this.rtp_empty = rtp_empty;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public int getRtp_full() {
            return rtp_full;
        }

        public void setRtp_full(int rtp_full) {
            this.rtp_full = rtp_full;
        }
    }

    public static class TypeGroupBean implements Serializable{
        /**
         * rtp_total : 17
         * ctnr_type : IF1040
         * rtp_empty : 17
         * rtp_full : 0
         */

        private int rtp_total;
        private String ctnr_type;
        private int rtp_empty;
        private int rtp_full;

        public int getRtp_total() {
            return rtp_total;
        }

        public void setRtp_total(int rtp_total) {
            this.rtp_total = rtp_total;
        }

        public String getCtnr_type() {
            return ctnr_type;
        }

        public void setCtnr_type(String ctnr_type) {
            this.ctnr_type = ctnr_type;
        }

        public int getRtp_empty() {
            return rtp_empty;
        }

        public void setRtp_empty(int rtp_empty) {
            this.rtp_empty = rtp_empty;
        }

        public int getRtp_full() {
            return rtp_full;
        }

        public void setRtp_full(int rtp_full) {
            this.rtp_full = rtp_full;
        }
    }
}
