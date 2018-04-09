package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by HOREN on 2017/12/12.
 */

public class MapDataBean extends BaseResponse {


    private List<OwnerListBean> ownerList;

    public List<OwnerListBean> getOwnerList() {
        return ownerList;
    }

    public ComdPdInfoBean getComdPdInfo() {
        return comdPdInfo;
    }

    public void setComdPdInfo(ComdPdInfoBean comdPdInfo) {
        this.comdPdInfo = comdPdInfo;
    }

    public void setOwnerList(List<OwnerListBean> ownerList) {
        this.ownerList = ownerList;
    }

    public static class OwnerListBean {
        /**
         * company_id :
         * company_name : Horen
         * distance : 5
         * org_id :
         * org_latitude : 31.164677
         * org_longitude : 121.393217
         * org_name : Horen
         * owner_id :
         * owner_name :
         * sum : 0
         */

        private String company_id;
        private String company_name;
        private String distance;
        private String org_id;
        private String org_latitude;
        private String org_longitude;
        private String org_address;

        public String getOrg_address() {
            return org_address;
        }

        public void setOrg_address(String org_address) {
            this.org_address = org_address;
        }

        private String org_name;
        private String owner_id;
        private String owner_name;
        private String sum;

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getOrg_latitude() {
            return org_latitude;
        }

        public void setOrg_latitude(String org_latitude) {
            this.org_latitude = org_latitude;
        }

        public String getOrg_longitude() {
            return org_longitude;
        }

        public void setOrg_longitude(String org_longitude) {
            this.org_longitude = org_longitude;
        }

        public String getOrg_name() {
            return org_name;
        }

        public void setOrg_name(String org_name) {
            this.org_name = org_name;
        }

        public String getOwner_id() {
            return owner_id;
        }

        public void setOwner_id(String owner_id) {
            this.owner_id = owner_id;
        }

        public String getOwner_name() {
            return owner_name;
        }

        public void setOwner_name(String owner_name) {
            this.owner_name = owner_name;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }
    }


    private ComdPdInfoBean comdPdInfo;
    public static class ComdPdInfoBean {
        /**
         * org_name : 仓储中心-上海地区
         * company_id : CM00000260
         * owner_name :
         * org_longitude : 0.0
         * sum : 0
         * org_contact : 杨卫佳
         * org_tel : 15122222222
         * org_address : 科技绿洲
         * rtpFull : 0
         * org_id : HC00000905
         * org_latitude : 0.0
         * distance : 0.0
         * company_name : 仓储中心-上海地区
         * owner_id :
         * rtpEmpty : 0
         */

        private String org_name;
        private String company_id;
        private String owner_name;
        private String org_longitude;
        private int sum;
        private String org_contact;
        private String org_tel;
        private String org_address;
        private int rtpFull;
        private String org_id;
        private String org_latitude;
        private double distance;
        private String company_name;
        private String owner_id;
        private int rtpEmpty;

        public String getOrg_name() {
            return org_name;
        }

        public void setOrg_name(String org_name) {
            this.org_name = org_name;
        }

        public String getCompany_id() {
            return company_id;
        }

        public void setCompany_id(String company_id) {
            this.company_id = company_id;
        }

        public String getOwner_name() {
            return owner_name;
        }

        public void setOwner_name(String owner_name) {
            this.owner_name = owner_name;
        }

        public String getOrg_longitude() {
            return org_longitude;
        }

        public void setOrg_longitude(String org_longitude) {
            this.org_longitude = org_longitude;
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public String getOrg_contact() {
            return org_contact;
        }

        public void setOrg_contact(String org_contact) {
            this.org_contact = org_contact;
        }

        public String getOrg_tel() {
            return org_tel;
        }

        public void setOrg_tel(String org_tel) {
            this.org_tel = org_tel;
        }

        public String getOrg_address() {
            return org_address;
        }

        public void setOrg_address(String org_address) {
            this.org_address = org_address;
        }

        public int getRtpFull() {
            return rtpFull;
        }

        public void setRtpFull(int rtpFull) {
            this.rtpFull = rtpFull;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getOrg_latitude() {
            return org_latitude;
        }

        public void setOrg_latitude(String org_latitude) {
            this.org_latitude = org_latitude;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getOwner_id() {
            return owner_id;
        }

        public void setOwner_id(String owner_id) {
            this.owner_id = owner_id;
        }

        public int getRtpEmpty() {
            return rtpEmpty;
        }

        public void setRtpEmpty(int rtpEmpty) {
            this.rtpEmpty = rtpEmpty;
        }
    }
}
