package com.horen.cortp.company.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by David-Notebook on 2017/6/19.
 */

public class CompanyUser extends BaseResponse{


    private List<UsersBean> users;

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * org_name : RTP&IoT融合研发中心
         * company_id : CM00000142
         * role_id : EOS_ADMIM
         * status : 2
         * updator : ed4be6bb-f232-11e5-88b7-9c5c8e01181d
         * facility_type : 4
         * user_nickname : adminapp
         * creator : ed4be6bb-f232-11e5-88b7-9c5c8e01181d
         * org_id : HC00000695
         * user_name : adminapp
         * create_date : 2017-06-14 12:30:44
         * company_name : RTP&IoT融合研发中心
         * lock_status : 0
         * phone_pre : 86
         * user_mobile :
         * update_date : 2017-06-19 10:52:41
         * flag_orgadmin : 0
         * flag_bindmobile : 0
         * user_id : HU00000791
         * language :
         * flag_bindmail : 0
         * user_mail : wangfm@horenplastic.com
         * flag_scanner : 0
         */

        private String org_name;
        private String company_id;
        private String role_id;
        private String status;
        private String updator;
        private String facility_type;
        private String user_nickname;
        private String creator;
        private String org_id;
        private String user_name;
        private String create_date;
        private String company_name;
        private String lock_status;
        private String phone_pre;
        private String user_mobile;
        private String update_date;
        private String flag_orgadmin;
        private String flag_bindmobile;
        private String user_id;
        private String language;
        private String flag_bindmail;
        private String user_mail;
        private String flag_scanner;

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

        public String getRole_id() {
            return role_id;
        }

        public void setRole_id(String role_id) {
            this.role_id = role_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpdator() {
            return updator;
        }

        public void setUpdator(String updator) {
            this.updator = updator;
        }

        public String getFacility_type() {
            return facility_type;
        }

        public void setFacility_type(String facility_type) {
            this.facility_type = facility_type;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getLock_status() {
            return lock_status;
        }

        public void setLock_status(String lock_status) {
            this.lock_status = lock_status;
        }

        public String getPhone_pre() {
            return phone_pre;
        }

        public void setPhone_pre(String phone_pre) {
            this.phone_pre = phone_pre;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getFlag_orgadmin() {
            return flag_orgadmin;
        }

        public void setFlag_orgadmin(String flag_orgadmin) {
            this.flag_orgadmin = flag_orgadmin;
        }

        public String getFlag_bindmobile() {
            return flag_bindmobile;
        }

        public void setFlag_bindmobile(String flag_bindmobile) {
            this.flag_bindmobile = flag_bindmobile;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getFlag_bindmail() {
            return flag_bindmail;
        }

        public void setFlag_bindmail(String flag_bindmail) {
            this.flag_bindmail = flag_bindmail;
        }

        public String getUser_mail() {
            return user_mail;
        }

        public void setUser_mail(String user_mail) {
            this.user_mail = user_mail;
        }

        public String getFlag_scanner() {
            return flag_scanner;
        }

        public void setFlag_scanner(String flag_scanner) {
            this.flag_scanner = flag_scanner;
        }
    }
}
