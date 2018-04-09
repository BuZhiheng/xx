package com.horen.cortp.company.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

/**
 * Created by HOREN on 2017/9/27.
 */

public class UploadPhotoBean extends BaseResponse{

    /**
     * photourl : http://172.20.247.145:8080/uploadimg/6030223da69f4413a4c525d849c6aff2.png
     * app_token : ba321cffe9fb17a5759c2c2e15bf06b8c0eed1cf
     * user_id : HU00000883
     * photo : 6030223da69f4413a4c525d849c6aff2.png
     */

    private String photourl;
    private String app_token;
    private String user_id;
    private String photo;

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
