package cn.hivemedia.common.utils;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZengXiong
 * @Description 极光推送
 * @Date 2019/01/09 14:32
 */
public class JPush {
    private static Logger logger = LoggerFactory.getLogger(JPush.class);

    private static String APP_KEY = "11ec56f211b8ed70620fa1d7";
    private static String MASTER_SECRET = "ee28729e771555e66669c953";

    public static void main(String[] args) {
        //设置推送参数
        Map<String, String> param = new HashMap<>();
        //设备id,指定设备推送id
        param.put("id", "13165ffa4e594a31ed0");
        //设置内容
        param.put("msg", "测试测试,收到请联系发送人");
        JPush.jpushAll(param);
    }

    /**
     * 推送Android
     *
     * @param param 自定义参数
     */
    public static PushResult jpushAndroid(Map<String, String> param) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        //构造payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                //.setAudience(Audience.registrationId(param.get("id")))//registrationId指定用户
                .setNotification(Notification.android(param.get("msg"), "这是title", param))
                //发送内容
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                //这里是指定开发环境,不用设置也没关系,自定义信息
                .setMessage(Message.content(param.get("msg")))
                .build();

        try {
            return jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("Android Connection error. Should retry later. ", e);
            return null;
        } catch (APIRequestException e) {
            logger.error("Android Error response from JPush server. Should review and fix it. ", e);
            logger.info("Android HTTP Status: " + e.getStatus());
            logger.info("Android Error Code: " + e.getErrorCode());
            logger.info("Android Error Message: " + e.getErrorMessage());
            logger.info("Android Msg ID: " + e.getMsgId());
            return null;
        }
    }

    /**
     * 推送IOS
     *
     * @param param 自定义参数
     */
    public static PushResult jpushIOS(Map<String, String> param) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                //.setAudience(Audience.registrationId(param.get("id")))//registrationId指定用户
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(param.get("msg"))
                                .setBadge(+1)
                                //这里是设置提示音(更多可以去官网看看)
                                .setSound("happy")
                                .addExtras(param)
                                .build())
                        .build())
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .setMessage(Message.newBuilder().setMsgContent(param.get("msg")).addExtras(param).build())
                .build();

        try {
            return jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("IOS Connection error. Should retry later. ", e);
            return null;
        } catch (APIRequestException e) {
            logger.error("IOS Error response from JPush server. Should review and fix it. ", e);
            logger.info("IOS HTTP Status: " + e.getStatus());
            logger.info("IOS Error Code: " + e.getErrorCode());
            logger.info("IOS Error Message: " + e.getErrorMessage());
            logger.info("IOS Msg ID: " + e.getMsgId());
            return null;
        }
    }

    /**
     * 推送全平台
     *
     * @param param 自定义参数
     */
    public static PushResult jpushAll(Map<String, String> param) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(param.get("id")))
                .setNotification(Notification.newBuilder()
                        //发送ios
                        .addPlatformNotification(IosNotification.newBuilder()
                                //消息体
                                .setAlert(param.get("msg"))
                                .setBadge(+1)
                                //ios提示音
                                .setSound("happy")
                                //附加参数
                                .addExtras(param)
                                .build())
                        //发送android
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                //附加参数
                                .addExtras(param)
                                //消息体
                                .setAlert(param.get("msg"))
                                .build())
                        .build())
                //指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式)
                .setOptions(Options.newBuilder().setApnsProduction(true).build())
                //自定义信息
                .setMessage(Message.newBuilder().setMsgContent(param.get("msg")).addExtras(param).build())
                .build();
        try {
            return jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            return null;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            return null;
        }
    }
}
