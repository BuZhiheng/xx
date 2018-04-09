package com.horen.cortp.platform.api;
import com.horen.cortp.platform.bean.ApiResultBoxExperience;
import com.horen.cortp.platform.bean.ApiResultCreateExperience;
import com.horen.cortp.platform.bean.ApiResultFireflyList;
import com.horen.cortp.platform.bean.ApiResultHomeData;
import com.horen.cortp.platform.bean.ApiResultPartnerList;
import com.horen.cortp.platform.bean.ApiResultPlanData;
import com.horen.cortp.platform.bean.ApiResultPlanType;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;
/**
 * Created by HOREN on 2017/11/15.
 */
public interface PlatformApi {
    /**
     * 获取主页数据
     * */
    @POST("solution/gethomedata/v2")
    Observable<ApiResultHomeData> getHomeData(@Body RequestBody body);
    /**
     * 获取万箱方案列三级分类
     * */
    @POST("solution/getlookups")
    Observable<ApiResultPlanType> getPlanTypeList(@Body RequestBody body);
    /**
     * 获取万箱方案数据列表
     * */
    @POST("solution/getlines")
    Observable<ApiResultPlanData> getPlanData(@Body RequestBody body);
    /**
     * 获取收藏方案列表
     * */
    @POST("solution/favorite/getmyfavorites")
    Observable<ApiResultPlanData> getCollectionPlanData(@Body RequestBody body);
    /**
     * 获取万箱盟友
     * */
    @POST("solution/company/getlines")
    Observable<ApiResultPartnerList> getAllParnter(@Body RequestBody body);
    /**
     * 萤火虫新箱构想
     * */
    @POST("solution/firefly/getlines")
    Observable<ApiResultFireflyList> getFireflys(@Body RequestBody body);
    /**
     * 用箱体验
     * */
    @POST("solution/experience/getlines")
    Observable<ApiResultBoxExperience> getUseExperience(@Body RequestBody body);

    /**
     * 用箱体验
     * */
    @Multipart
    @POST("user/uploadphoto")
    Observable<ApiResultCreateExperience> createExperience(@PartMap Map<String, RequestBody> map);
}