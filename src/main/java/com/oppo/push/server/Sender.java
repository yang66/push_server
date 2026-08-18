package com.oppo.push.server;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Sender.class */
public class Sender {
    private String appKey;
    private String masterSecret;
    private String token;
    private Long createTime;
    private Environment env;

    public Sender(String appKey, String masterSecret, Environment env) throws Exception {
        this.env = Environment.CHINA_PRODUCTION;
        this.env = env;
        init(appKey, masterSecret);
    }

    public Sender(String appKey, String masterSecret) throws Exception {
        this.env = Environment.CHINA_PRODUCTION;
        init(appKey, masterSecret);
    }

    private void init(String appKey, String masterSecret) throws Exception {
        this.appKey = appKey;
        this.masterSecret = masterSecret;
        try {
            HttpClientTool.init();
            setToken(this.appKey, this.masterSecret);
        } catch (Exception e) {
            throw e;
        }
    }

    private void setToken(String appKey, String masterSecret) throws Exception {
        Result result = Auth.getAuthResult(appKey, masterSecret, getEnv());
        if (result != null && !Validate.isEmpty(result.getToken())) {
            this.token = result.getToken();
        }
    }

    public Result unicastNotification(Notification notification, Target target) throws Exception {
        notification.validate();
        Map<String, Object> message = MessageTool.newMessage(target);
        message.put("notification", MessageTool.getNotification(notification));
        Map<String, Object> body = MessageTool.newBody(this.token);
        body.put("message", JSON.toJSONString(message));
        return HttpClientTool.httpPostWithToken(RequestPath.NOTIFICATION_UNICAST, body, this);
    }

    public Result broadcastNotification(String messageId, Target target) throws Exception {
        Map<String, Object> body = MessageTool.newBody(target, this.token);
        MessageTool.setMessageId(body, messageId);
        return HttpClientTool.httpPostWithToken(RequestPath.NOTIFICATION_BROADCAST, body, this);
    }

    public Result uploadBigPicture(Integer pictureTtl, File file) throws Exception {
        if (this.env == Environment.INTERNATIONAL) {
            throw new RuntimeException("INTERNATIONAL not supported upload picture");
        }
        Map<String, Object> body = MessageTool.newBody(this.token);
        MessageTool.setPictureTtl(body, pictureTtl);
        Validate.validatePictureType(file);
        Validate.validateFileMaxSize(file, 1048576L);
        return HttpClientTool.multipartHttpPostWithToken(RequestPath.UPLOAD_BIG_PICTURE, body, file, this);
    }

    public Result uploadSmallPicture(Integer pictureTtl, File file) throws Exception {
        if (this.env == Environment.INTERNATIONAL) {
            throw new RuntimeException("INTERNATIONAL not supported upload picture");
        }
        Map<String, Object> body = MessageTool.newBody(this.token);
        MessageTool.setPictureTtl(body, pictureTtl);
        Validate.validatePictureType(file);
        Validate.validateFileMaxSize(file, 51200L);
        return HttpClientTool.multipartHttpPostWithToken(RequestPath.UPLOAD_SMALL_PICTURE, body, file, this);
    }

    public Result saveNotification(Notification notification) throws Exception {
        notification.validate();
        Map<String, Object> body = MessageTool.newBody(this.token);
        body.putAll(MessageTool.getNotification(notification));
        return HttpClientTool.httpPostWithToken(RequestPath.NOTIFICATION_SAVE_MESSAGE, body, this);
    }

    public Result unicastBatchNotification(Map<Target, Notification> notificationMessages) throws Exception {
        Map<String, Object> body = MessageTool.newBody(this.token);
        List<Map<String, Object>> messages = new ArrayList<>();
        for (Map.Entry<Target, Notification> entry : notificationMessages.entrySet()) {
            Target target = entry.getKey();
            Notification notification = entry.getValue();
            try {
                notification.validate();
                Map<String, Object> message = MessageTool.newMessage(target);
                message.put("notification", MessageTool.getNotification(notification));
                messages.add(message);
            } catch (IllegalArgumentException e) {
                String string = String.format("%s    target:%s  notification:%s ", e.getMessage(), JSON.toJSON(target), notification);
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException(string, e);
                throw illegalArgumentException;
            }
        }
        body.put("messages", JSON.toJSONString(messages));
        return HttpClientTool.httpPostWithToken(RequestPath.NOTIFICATION_UNICAST_BATCH, body, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getAppKey() {
        return this.appKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getMasterSecret() {
        return this.masterSecret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Environment getEnv() {
        return this.env;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Sender$Builder.class */
    public static class Builder {
        private String appKey;
        private String masterSecret;
        private Environment env = Environment.CHINA_PRODUCTION;

        public Builder appKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public Builder masterSecret(String masterSecret) {
            this.masterSecret = masterSecret;
            return this;
        }

        public Builder env(Environment env) {
            this.env = env;
            return this;
        }

        public Builder httpMaxConnection(int httpMaxConnection) {
            Constants.HTTP_MAX_CONNECTION = httpMaxConnection;
            return this;
        }

        public Builder httpMaxRoute(int httpMaxRoute) {
            Constants.HTTP_MAX_ROUTE = httpMaxRoute;
            return this;
        }

        public Builder httpRetryTime(int httpRetryTime) {
            Constants.HTTP_RETRY_TIME = httpRetryTime;
            return this;
        }

        public Builder httpSocketTimeout(int httpSocketTimeout) {
            Constants.HTTP_SOCKET_TIMEOUT = httpSocketTimeout;
            return this;
        }

        public Builder httpConnectionTimeout(int httpConnectionTimeout) {
            Constants.HTTP_CONNECTION_TIMEOUT = httpConnectionTimeout;
            return this;
        }

        public Builder httpConnectRequestTimeout(int httpConnectRequestTimeout) {
            Constants.HTTP_CONNECTION_REQUEST_TIMEOUT = httpConnectRequestTimeout;
            return this;
        }

        public Sender build() throws Exception {
            return new Sender(this.appKey, this.masterSecret, this.env);
        }
    }
}
