package com.oppo.push.server;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Result.class */
public class Result {
    private int statusCode;
    private String reason;
    private ReturnCode returnCode;
    private String messageId;
    private String taskId;
    private String token;
    private Long createTime;
    private List<UnicastBatchResult> unicastBatchResults;
    private List<BroadcastErrorResult> broadcastErrorResults;
    private String bigPictureId;
    private String smallPictureId;

    public int getStatusCode() {
        return this.statusCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return this.reason;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setReason(String reason) {
        this.reason = reason;
    }

    public ReturnCode getReturnCode() {
        return this.returnCode;
    }

    private void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessageId() {
        return this.messageId;
    }

    private void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTaskId() {
        return this.taskId;
    }

    private void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getToken() {
        return this.token;
    }

    private void setToken(String token) {
        this.token = token;
    }

    public Long getCreateTime() {
        return this.createTime;
    }

    private void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<UnicastBatchResult> getUnicastBatchResults() {
        return this.unicastBatchResults;
    }

    private void setUnicastBatchResults(List<UnicastBatchResult> unicastBatchResults) {
        this.unicastBatchResults = unicastBatchResults;
    }

    public List<BroadcastErrorResult> getBroadcastErrorResults() {
        return this.broadcastErrorResults;
    }

    private void setBroadcastErrorResults(List<BroadcastErrorResult> broadcastErrorResults) {
        this.broadcastErrorResults = broadcastErrorResults;
    }

    private BroadcastErrorResult newBroadCastErrorResult() {
        return new BroadcastErrorResult();
    }

    private UnicastBatchResult newUnicastBatchResult() {
        return new UnicastBatchResult();
    }

    public String getBigPictureId() {
        return this.bigPictureId;
    }

    private void setBigPictureId(String bigPictureId) {
        this.bigPictureId = bigPictureId;
    }

    public String getSmallPictureId() {
        return this.smallPictureId;
    }

    private void setSmallPictureId(String smallPictureId) {
        this.smallPictureId = smallPictureId;
    }

    /* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Result$BroadcastErrorResult.class */
    public class BroadcastErrorResult {
        private String errorCode;
        private String targetValue;

        public BroadcastErrorResult() {
        }

        public String getErrorCode() {
            return this.errorCode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getTargetValue() {
            return this.targetValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTargetValue(String targetValue) {
            this.targetValue = targetValue;
        }

        public String toString() {
            return "BroadcastErrorResult{errorCode='" + this.errorCode + "', targetValue='" + this.targetValue + "'}";
        }
    }

    /* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Result$UnicastBatchResult.class */
    public class UnicastBatchResult {
        private String messageId;
        private String targetValue;
        private Integer errorCode;
        private String errorMessage;

        public UnicastBatchResult() {
        }

        public String getMessageId() {
            return this.messageId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public String getTargetValue() {
            return this.targetValue;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTargetValue(String targetValue) {
            this.targetValue = targetValue;
        }

        public Integer getErrorCode() {
            return this.errorCode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String toString() {
            return "UnicastBatchResult{messageId='" + this.messageId + "', targetValue='" + this.targetValue + "', errorCode=" + this.errorCode + ", errorMessage='" + this.errorMessage + "'}";
        }
    }

    private void setAuthResult(JSONObject data) {
        if (data != null) {
            setToken(data.getString("auth_token"));
            setCreateTime(data.getLong("create_time"));
        }
    }

    private void setUnicastResult(JSONObject data) {
        if (data != null) {
            setMessageId(data.getString("messageId"));
        }
    }

    private void setSaveMessageResult(JSONObject data) {
        if (data != null) {
            setMessageId(data.getString("message_id"));
        }
    }

    private void setUploadBigPicResult(JSONObject data) {
        if (data != null) {
            setBigPictureId(data.getString("big_picture_id"));
        }
    }

    private void setUploadSmallPicResult(JSONObject data) {
        if (data != null) {
            setSmallPictureId(data.getString("small_picture_id"));
        }
    }

    private void setBroadcastResult(JSONObject data) {
        if (data == null || data.size() == 0) {
            return;
        }
        List<BroadcastErrorResult> errorResults = new ArrayList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getKey().equals("messageId") || entry.getKey().equals("message_id")) {
                setMessageId((String) entry.getValue());
            } else if (entry.getKey().equals("task_id")) {
                setTaskId((String) entry.getValue());
            } else if (Validate.validateErrorCode(entry.getKey())) {
                BroadcastErrorResult errorResult = newBroadCastErrorResult();
                errorResult.setErrorCode(entry.getKey());
                errorResult.setTargetValue(entry.getValue().toString());
                errorResults.add(errorResult);
            }
        }
        setBroadcastErrorResults(errorResults);
    }

    private void setUnicastBatchResults(JSONObject responseBody) {
        if (responseBody.get("data") == null || (responseBody.get("data") instanceof JSONObject)) {
            return;
        }
        JSONArray dataArray = responseBody.getJSONArray("data");
        List<UnicastBatchResult> unicastBatchResults = new ArrayList<>();
        if (dataArray != null && dataArray.size() > 0) {
            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject jsonObject = dataArray.getJSONObject(i);
                if (jsonObject != null) {
                    UnicastBatchResult unicastBatchResult = newUnicastBatchResult();
                    unicastBatchResult.setMessageId(jsonObject.getString("messageId"));
                    unicastBatchResult.setTargetValue(jsonObject.getString("registrationId"));
                    unicastBatchResult.setErrorCode(jsonObject.getInteger("errorCode"));
                    unicastBatchResult.setErrorMessage(jsonObject.getString("errorMessage"));
                    unicastBatchResults.add(unicastBatchResult);
                }
            }
            setUnicastBatchResults(unicastBatchResults);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setResult(RequestPath path, JSONObject responseBody) {
        Integer code = responseBody.getInteger("code");
        String message = responseBody.getString("message");
        if (code != null) {
            setReturnCode(ReturnCode.valueOf(code.intValue(), message));
        }
        switch (path) {
            case AUTH:
                setAuthResult(responseBody.getJSONObject("data"));
                return;
            case NOTIFICATION_UNICAST:
                setUnicastResult(responseBody.getJSONObject("data"));
                return;
            case NOTIFICATION_SAVE_MESSAGE:
                setSaveMessageResult(responseBody.getJSONObject("data"));
                return;
            case NOTIFICATION_BROADCAST:
                setBroadcastResult(responseBody.getJSONObject("data"));
                return;
            case NOTIFICATION_UNICAST_BATCH:
                setUnicastBatchResults(responseBody);
                return;
            case UPLOAD_SMALL_PICTURE:
                setUploadSmallPicResult(responseBody.getJSONObject("data"));
                return;
            case UPLOAD_BIG_PICTURE:
                setUploadBigPicResult(responseBody.getJSONObject("data"));
                return;
            default:
                return;
        }
    }

    public String toString() {
        return "Result{statusCode=" + this.statusCode + ", reason='" + this.reason + "', returnCode=" + this.returnCode + ", messageId='" + this.messageId + "', taskId='" + this.taskId + "', token='" + this.token + "', createTime=" + this.createTime + ", unicastBatchResults=" + this.unicastBatchResults + ", broadcastErrorResults=" + this.broadcastErrorResults + ", bigPictureId='" + this.bigPictureId + "', smallPictureId='" + this.smallPictureId + "'}";
    }
}
