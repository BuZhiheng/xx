package com.horen.cortp.platform.bean;
import java.util.List;
/**
 * Created by HOREN on 2017/12/11.
 *
 * 全部方案，返回万箱分类
 *
 */
public class ApiResultPlanType extends BaseResult {
    public List<PlanTypeLookup> lookup_productags;//产品形态
    public List<PlanTypeLookup> lookup_productsizes;//产品尺寸
    public List<PlanType> lookup_solutiontypes;//方案分类（行业）
}