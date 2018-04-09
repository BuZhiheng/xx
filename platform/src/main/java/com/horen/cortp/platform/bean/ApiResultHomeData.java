package com.horen.cortp.platform.bean;
import java.util.List;
/**
 * Created by HOREN on 2017/11/15.
 *
 * 万箱首页返回数据结构
 *
 */
public class ApiResultHomeData extends BaseResult {
    public List<HomeBanner> solution_homebanners;//首页轮播图列表
    public List<Partner> solution_homecompanys;//首页盟友列表
    public List<PlanTypeList> solution_homehotsolutions;//首页热门方案列表
}