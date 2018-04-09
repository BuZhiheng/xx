package com.horen.cortp.company.api;
import com.jaydenxiao.common.api.Api;

/**
 * Created by HOREN on 2017/11/16.
 */
public class ApiCompanyFactory {
    private static ApiServiceCompany api;
    private ApiCompanyFactory(){
    }
    public static ApiServiceCompany getSingleApi(){
        if (api == null){
            api = Api.getApiService(ApiServiceCompany.class);
        }
        return api;
    }
}