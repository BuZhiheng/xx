package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by HOREN on 2017/12/20.
 */

public class ServiceEyeBannerBean extends BaseResponse {

    private List<SolutionHomebannersBean> solution_homebanners;

    public List<SolutionHomebannersBean> getSolution_homebanners() {
        return solution_homebanners;
    }

    public void setSolution_homebanners(List<SolutionHomebannersBean> solution_homebanners) {
        this.solution_homebanners = solution_homebanners;
    }

    public static class SolutionHomebannersBean {
        /**
         * banner_id : 25385672772697318
         * banner_image : http://www.cortp.com/uploads/solution/homebanner/bannerpic1.jpg
         * banner_sort : 1
         * banner_text :
         * banner_title : 天眼轮播图
         * banner_type : 1004
         * banner_url :
         * create_date : 2017-12-11 06:49:30
         * creator :
         * status : 1
         * update_date : 2017-12-14 16:01:25
         * updator :
         * valid_fromdate : 2017-12-14 16:01:25
         */

        private String banner_id;
        private String banner_image;
        private int banner_sort;
        private String banner_text;
        private String banner_title;
        private String banner_type;
        private String banner_url;
        private String create_date;
        private String creator;
        private String status;
        private String update_date;
        private String updator;
        private String valid_fromdate;

        public String getBanner_id() {
            return banner_id;
        }

        public void setBanner_id(String banner_id) {
            this.banner_id = banner_id;
        }

        public String getBanner_image() {
            return banner_image;
        }

        public void setBanner_image(String banner_image) {
            this.banner_image = banner_image;
        }

        public int getBanner_sort() {
            return banner_sort;
        }

        public void setBanner_sort(int banner_sort) {
            this.banner_sort = banner_sort;
        }

        public String getBanner_text() {
            return banner_text;
        }

        public void setBanner_text(String banner_text) {
            this.banner_text = banner_text;
        }

        public String getBanner_title() {
            return banner_title;
        }

        public void setBanner_title(String banner_title) {
            this.banner_title = banner_title;
        }

        public String getBanner_type() {
            return banner_type;
        }

        public void setBanner_type(String banner_type) {
            this.banner_type = banner_type;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(String update_date) {
            this.update_date = update_date;
        }

        public String getUpdator() {
            return updator;
        }

        public void setUpdator(String updator) {
            this.updator = updator;
        }

        public String getValid_fromdate() {
            return valid_fromdate;
        }

        public void setValid_fromdate(String valid_fromdate) {
            this.valid_fromdate = valid_fromdate;
        }
    }
}
