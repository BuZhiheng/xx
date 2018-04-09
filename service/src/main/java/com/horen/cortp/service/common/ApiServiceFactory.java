package com.horen.cortp.service.common;
import com.jaydenxiao.common.api.Api;
/**
 * Created by HOREN on 2017/11/16.
 */
public class ApiServiceFactory {
    private static ServiceClientService api;
    private ApiServiceFactory(){
    }
    public static ServiceClientService getSingleApi(){
        if (api == null){
            api = Api.getApiService(ServiceClientService.class);
        }
        return api;
    }
}