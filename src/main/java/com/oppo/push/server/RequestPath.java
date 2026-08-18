package com.oppo.push.server;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/RequestPath.class */
public enum RequestPath {
    BASE_PATH("/server"),
    VERSION_PATH("/v1"),
    AUTH("/auth"),
    NOTIFICATION_UNICAST("/message/notification/unicast"),
    NOTIFICATION_UNICAST_BATCH("/message/notification/unicast_batch"),
    NOTIFICATION_SAVE_MESSAGE("/message/notification/save_message_content"),
    NOTIFICATION_BROADCAST("/message/notification/broadcast"),
    UPLOAD_BIG_PICTURE("/media/upload/big_picture"),
    UPLOAD_SMALL_PICTURE("/media/upload/small_picture");
    
    private String path;

    RequestPath(String path) {
        this.path = path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPath() {
        return this.path;
    }
}
