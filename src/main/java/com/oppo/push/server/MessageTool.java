package com.oppo.push.server;

import java.util.HashMap;
import java.util.Map;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/MessageTool.class */
class MessageTool {
    MessageTool() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> newMessage(Target target) {
        target.validate();
        Map<String, Object> body = new HashMap<>(16);
        body.put("target_type", Integer.valueOf(target.getTargetType().getValue()));
        body.put("target_value", target.getTargetValue());
        return body;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> newBody(Target target, String token) {
        target.validate();
        Map<String, Object> body = new HashMap<>(16);
        body.put("target_type", Integer.valueOf(target.getTargetType().getValue()));
        body.put("target_value", target.getTargetValue());
        body.put("auth_token", token);
        return body;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> newBody(String token) {
        Map<String, Object> body = new HashMap<>(16);
        body.put("auth_token", token);
        return body;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getNotification(Notification message) {
        Map<String, Object> parameters = new HashMap<>(32);
        parameters.put("title", message.getTitle());
        parameters.put("content", message.getContent());
        if (message.getStyle() != null) {
            parameters.put("style", message.getStyle());
        }
        if (!Validate.isEmpty(message.getBigPictureId())) {
            parameters.put("big_picture_id", message.getBigPictureId());
        }
        if (!Validate.isEmpty(message.getSmallPictureId())) {
            parameters.put("small_picture_id", message.getSmallPictureId());
        }
        if (!Validate.isEmpty(message.getSubTitle())) {
            parameters.put("sub_title", message.getSubTitle());
        }
        if (message.getChannelId() != null) {
            parameters.put("channel_id", message.getChannelId());
        }
        if (!Validate.isEmpty(message.getAppMessageId())) {
            parameters.put("app_message_id", message.getAppMessageId());
        }
        if (message.getClickActionType() != null) {
            parameters.put("click_action_type", message.getClickActionType());
        }
        if (!Validate.isEmpty(message.getClickActionActivity())) {
            parameters.put("click_action_activity", message.getClickActionActivity());
        }
        if (!Validate.isEmpty(message.getClickActionUrl())) {
            parameters.put("click_action_url", message.getClickActionUrl());
        }
        if (!Validate.isEmpty(message.getActionParameters())) {
            parameters.put("action_parameters", message.getActionParameters());
        }
        if (message.getShowTimeType() != null) {
            parameters.put("show_time_type", message.getShowTimeType());
        }
        if (message.getShowStartTime() != null) {
            parameters.put("show_start_time", message.getShowStartTime());
        }
        if (message.getShowEndTime() != null) {
            parameters.put("show_end_time", message.getShowEndTime());
        }
        if (message.getOffLine() != null) {
            parameters.put("off_line", message.getOffLine());
        }
        if (message.getOffLineTtl() != null) {
            parameters.put("off_line_ttl", message.getOffLineTtl());
        }
        if (message.getPushTimeType() != null) {
            parameters.put("push_time_type", message.getPushTimeType());
        }
        if (!Validate.isEmpty(message.getTimeZone())) {
            parameters.put("time_zone", message.getTimeZone());
        }
        if (message.getNetworkType() != null) {
            parameters.put("network_type", message.getNetworkType());
        }
        if (!Validate.isEmpty(message.getCallBackUrl())) {
            parameters.put("call_back_url", message.getCallBackUrl());
        }
        if (!Validate.isEmpty(message.getCallBackParameter())) {
            parameters.put("call_back_parameter", message.getCallBackParameter());
        }
        if (message.getShowTtl() != null) {
            parameters.put("show_ttl", message.getShowTtl());
        }
        if (message.getNotifyId() != null) {
            parameters.put("notify_id", message.getNotifyId());
        }
        if (message.getBadgeMessageCount() != null) {
            parameters.put("badge_message_count", message.getBadgeMessageCount());
        }
        if (message.getBadgeOperationType() != null) {
            parameters.put("badge_operation_type", message.getBadgeOperationType());
        }
        if (!Validate.isEmpty(message.getAuditResponse())) {
            parameters.put("auditResponse", message.getAuditResponse());
        }
        return parameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setMessageId(Map<String, Object> body, String messageId) {
        body.put("message_id", Validate.nonNull(messageId, "message_id"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setPictureTtl(Map<String, Object> body, Integer pictureTtl) {
        body.put("picture_ttl", Validate.validatePositiveInteger(pictureTtl, "picture_ttl"));
    }
}
