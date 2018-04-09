package com.horen.cortp.company.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by Zhao on 2017/12/28/028.
 */

public class RelationSystem extends BaseResponse{

    private List<RelationListBean> relationList;

    public List<RelationListBean> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<RelationListBean> relationList) {
        this.relationList = relationList;
    }

    public static class RelationListBean {
        /**
         * org_name : 嘉兴仓储中心
         * org_id : HC00000771
         */

        private String org_name;
        private String org_id;

        public String getOrg_name() {
            return org_name;
        }

        public void setOrg_name(String org_name) {
            this.org_name = org_name;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }
    }
}
