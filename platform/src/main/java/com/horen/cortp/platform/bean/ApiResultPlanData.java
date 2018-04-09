package com.horen.cortp.platform.bean;

import java.util.List;
/**
 * Created by HOREN on 2017/12/11.
 *
 * 获取方案数据，返回方案数据结构
 *
 */
public class ApiResultPlanData extends BaseResult {
    public List<Plan> solutions;
    public List<Plan> favorite_solutions;
}