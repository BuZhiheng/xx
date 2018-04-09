package com.horen.cortp.service.mvp.model;

import android.content.Context;

import com.horen.cortp.service.bean.UploadSinglePhotoBean;
import com.horen.cortp.service.common.ApiServiceFactory;
import com.horen.cortp.service.mvp.contract.ExceptionTaskContract;
import com.jaydenxiao.common.baseapp.AppConfig;
import com.jaydenxiao.common.baserx.RxResultHelper;
import com.jaydenxiao.common.commonutils.SpKey;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.functions.Func1;
import top.zibin.luban.Luban;

/**
 * Created by HOREN on 2017/12/14.
 */

public class ExceptionTaskModel implements ExceptionTaskContract.Model {

    @Override
    public Observable<UploadSinglePhotoBean> uploadImgs(final Context mContext, String workorder_id, ArrayList<String> photos) {
        final Map<String, RequestBody> map = new HashMap<>();
        map.put("workorder_id", RequestBody.create(MediaType.parse("form-data"), workorder_id)); // 添加参数
        map.put("app_token", RequestBody.create(MediaType.parse("form-data"), AppConfig.getInstance().getString(SpKey.LOGIN_TOKEN, "")));
        final MultipartBody.Builder builder = new MultipartBody.Builder();
        return Observable.from(photos) // 压缩图片，之后调用上传图片的方法
                .map(new Func1<String, String>() {

                    private File file;

                    @Override
                    public String call(String path) {
                        try {
                            file = Luban.with(mContext).load(path).get(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        builder.addFormDataPart("files", "photo.png", RequestBody.create(MultipartBody.FORM, file));
                        return path;
                    }
                }).last()
                .flatMap(new Func1<String, Observable<UploadSinglePhotoBean>>() {
                    @Override
                    public Observable<UploadSinglePhotoBean> call(String path) {
                        return ApiServiceFactory.getSingleApi().uploadRepairOrder(map, builder.build());
                    }
                })
                .compose(RxResultHelper.<UploadSinglePhotoBean>handleResult());
    }
}
