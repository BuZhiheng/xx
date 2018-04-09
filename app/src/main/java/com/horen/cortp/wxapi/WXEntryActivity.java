package com.horen.cortp.wxapi;
import com.horen.cortp.R;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * Created by BuZhiheng on 2017/12/01.
 *
 * 微信  登录&分享  会回调此activity的onResp方法
 *
 * 此activity必须命名为WXEntryActivity
 * 此activity必须在包名下的wxapi子包下
 *
 *
 * */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler{
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, getString(R.string.share_id_wx),true);
        api.registerApp(getString(R.string.share_id_wx));
        api.handleIntent(getIntent(), this);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
    @Override
    public void onReq(BaseReq req) {
        switch (req.getType()) {
        case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
            break;
        case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
            break;
        default:
            break;
        }
    }
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.getType()){
            case 1://微信登陆
//                if (resp.errCode == BaseResp.ErrCode.ERR_OK){
//                    SendAuth.Resp r = (SendAuth.Resp) resp;
//                    if (r.code != null){
//                        //此处为微信登录接口
//                    }
//                } else {
//                    //执行 已取消||操作失败 不会走分享成功
//                    share(resp);
//                }
                break;
            case 2://微信分享
                share(resp);
                break;
        }
        finish();
    }
    private void share(BaseResp resp){
        String result;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = getString(R.string.share_success);
                ToastUitl.showShort(result);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "已取消";
                break;
            default:
                result = "操作失败";
                break;
        }
        finish();
    }
}