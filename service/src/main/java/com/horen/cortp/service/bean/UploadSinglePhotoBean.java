package com.horen.cortp.service.bean;

import com.jaydenxiao.common.basebean.BaseResponse;

import java.util.List;

/**
 * Created by HOREN on 2017/11/5.
 */

public class UploadSinglePhotoBean extends BaseResponse {

    private List<String> filepath;

    public List<String> getFilepath() {
        return filepath;
    }

    public void setFilepath(List<String> filepath) {
        this.filepath = filepath;
    }
}
