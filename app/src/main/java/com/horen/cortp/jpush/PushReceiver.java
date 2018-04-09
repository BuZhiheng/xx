package com.horen.cortp.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.jaydenxiao.common.baseapp.AppManager;
import com.jaydenxiao.common.commonutils.GsonUtil;
import com.jaydenxiao.common.commonutils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            Logger.d(TAG, "[PushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Logger.d(TAG, "[PushReceiver] 接收Registration Id : " + regId);
                //send the Registration Id to your server...
                SPUtils.setSharedStringData(context, "registrationID", regId);
            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Logger.d(TAG, "[PushReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
                startCompleteActivity(context, bundle.getString(JPushInterface.EXTRA_MESSAGE));
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
                Logger.d(TAG, "[PushReceiver] 接收到推送下来的通知");
                int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                Logger.d(TAG, "[PushReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Logger.d(TAG, "[PushReceiver] 用户点击打开了通知");
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Logger.d(TAG, "[PushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Logger.w(TAG, "[PushReceiver]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Logger.d(TAG, "[PushReceiver] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {

        }

    }

    /**
     * 跳转
     *
     * @param context
     * @param json
     */
    private void startCompleteActivity(Context context, String json) {
//        if (!AppManager.getAppManager().isOpenActivity(ServiceClientMainActivity.class)) { // 如果没有打开过主页面，先启动主页面
//            Intent intent1 = new Intent(context, ServiceClientMainActivity.class);
//            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent1);
//        }
//        ScanBean scanBean = GsonUtil.getGson().fromJson(json, ScanBean.class);
//        // 刷新首页列表数据
//        EventBus.getDefault().post(new MsgEvent().setType(MsgEvent.REFRESH_ORDER_LIST).setOrderType(scanBean.getOrder_type()));
//        switch (scanBean.getOrder_type()) {
//            case Constract.SEND_BOX: // 送箱
//                // 判断是否打开了配送中页面，如果打开了关闭
//                if (AppManager.getAppManager().isOpenActivity(SendBoxsDeliveryActivity.class)) {
//                    AppManager.getAppManager().finishActivity(SendBoxsDeliveryActivity.class);
//                }
//                // 打开已完成页面
//                Intent intent2 = new Intent(context, SendBoxsCompleteActivity.class);
//                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent2.putExtra("order_type", scanBean.getOrder_type());
//                intent2.putExtra("plan_id", scanBean.getPlan_id());
//                intent2.putExtra("line_status", scanBean.getLine_status());
//                intent2.putExtra("isPushSuccess", true);
//                context.startActivity(intent2);
//                break;
//
//            case Constract.SUPPLIES: // 耗材
//                // 判断是否打开了配送中页面，如果打开了关闭
//                if (AppManager.getAppManager().isOpenActivity(SendBoxsDeliveryActivity.class)) {
//                    AppManager.getAppManager().finishActivity(SendBoxsDeliveryActivity.class);
//                }
//                // 打开已完成页面
//                Intent intent4 = new Intent(context, SendBoxsCompleteActivity.class);
//                intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent4.putExtra("order_type", scanBean.getOrder_type());
//                intent4.putExtra("plan_id", scanBean.getPlan_id());
//                intent4.putExtra("line_status", scanBean.getLine_status());
//                intent4.putExtra("isPushSuccess", true);
//                context.startActivity(intent4);
//                break;
//
//            case Constract.COLLECT_BOX: // 2.收箱
//                // 判断是否打开了确认中页面，如果打开了关闭
//                if (AppManager.getAppManager().isOpenActivity(CollectBoxsConfirmingActivity.class)) {
//                    AppManager.getAppManager().finishActivity(CollectBoxsConfirmingActivity.class);
//                }
//                // 打开已完成页面
//                Intent intent3 = new Intent(context, CollectBoxsCompleteActivity.class);
//                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent3.putExtra("order_type", scanBean.getOrder_type());
//                intent3.putExtra("plan_id", scanBean.getPlan_id());
//                intent3.putExtra("line_status", scanBean.getLine_status());
//                intent3.putExtra("isPushSuccess", true);
//                context.startActivity(intent3);
//                break;
//        }
    }

    /**
     * 打印所有的 intent extra 数据
     *
     * @param bundle
     * @return
     */
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Logger.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();

                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Logger.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    /**
     * send msg to MessageActivity
     *
     * @param bundle
     */
    private void processCustomMessage(Bundle bundle) {
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        //发送消息
//        EventBus.getDefault().post(new MsgEvent().setType(MsgEvent.SHOW_MESSAGE).setObj(bundle));
    }
}
