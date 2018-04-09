package com.horen.cortp.platform.bean;

import java.io.Serializable;

/**
 * Created by HOREN on 2017/12/8.
 *
 * 用箱体验创建
 *
 */
public class ExperienceCreate implements Serializable{
    public String company_id;//公司ID
    public String company_name;//公司

    public String experience_id;//'编号
    public String experience_image;//图片
    public String experience_sort;//排序
    public String experience_text;//文字
    public String experience_title;//标题
    public String experience_url;//URL

    public String use_location;//使用地点
    public String use_time;//使用时间
}