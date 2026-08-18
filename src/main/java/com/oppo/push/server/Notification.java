package com.oppo.push.server;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Notification.class */
public class Notification {
    private String appMessageId;
    private Integer style;
    private String bigPictureId;
    private String smallPictureId;
    private String title;
    private String subTitle;
    private String content;
    private String channelId;
    private Integer clickActionType;
    private String clickActionActivity;
    private String clickActionUrl;
    private String actionParameters;
    private Integer showTimeType;
    private Long showStartTime;
    private Long showEndTime;
    private Boolean offLine;
    private Integer offLineTtl;
    private Integer pushTimeType = 0;
    private Long pushStartTime;
    private String timeZone;
    private Boolean fixSpeed;
    private Long fixSpeedRate;
    private Integer networkType;
    private String callBackUrl;
    private String callBackParameter;
    private Integer showTtl;
    private Integer notifyId;
    private Integer badgeMessageCount;
    private Integer badgeOperationType;
    private String auditResponse;
    private String category;

    public void setAppMessageId(String appMessageId) {
        this.appMessageId = appMessageId;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public void setBigPictureId(String bigPictureId) {
        this.bigPictureId = bigPictureId;
    }

    public void setSmallPictureId(String smallPictureId) {
        this.smallPictureId = smallPictureId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setClickActionType(Integer clickActionType) {
        this.clickActionType = clickActionType;
    }

    public void setClickActionActivity(String clickActionActivity) {
        this.clickActionActivity = clickActionActivity;
    }

    public void setClickActionUrl(String clickActionUrl) {
        this.clickActionUrl = clickActionUrl;
    }

    public void setActionParameters(String actionParameters) {
        this.actionParameters = actionParameters;
    }

    public void setShowTimeType(Integer showTimeType) {
        this.showTimeType = showTimeType;
    }

    public void setShowStartTime(Long showStartTime) {
        this.showStartTime = showStartTime;
    }

    public void setShowEndTime(Long showEndTime) {
        this.showEndTime = showEndTime;
    }

    public void setOffLine(Boolean offLine) {
        this.offLine = offLine;
    }

    public void setOffLineTtl(Integer offLineTtl) {
        this.offLineTtl = offLineTtl;
    }

    private void setPushTimeType(Integer pushTimeType) {
        this.pushTimeType = pushTimeType;
    }

    private void setPushStartTime(Long pushStartTime) {
        this.pushStartTime = pushStartTime;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    private void setFixSpeed(Boolean fixSpeed) {
        this.fixSpeed = fixSpeed;
    }

    private void setFixSpeedRate(Long fixSpeedRate) {
        this.fixSpeedRate = fixSpeedRate;
    }

    public void setNetworkType(Integer networkType) {
        this.networkType = networkType;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public void setCallBackParameter(String callBackParameter) {
        this.callBackParameter = callBackParameter;
    }

    public void setShowTtl(Integer showTtl) {
        this.showTtl = showTtl;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public void setBadgeMessageCount(Integer badgeMessageCount) {
        this.badgeMessageCount = badgeMessageCount;
    }

    public void setBadgeOperationType(Integer badgeOperationType) {
        this.badgeOperationType = badgeOperationType;
    }

    public void setAuditResponse(String auditResponse) {
        this.auditResponse = auditResponse;
    }

    public String getAppMessageId() {
        return this.appMessageId;
    }

    public Integer getStyle() {
        return this.style;
    }

    public String getBigPictureId() {
        return this.bigPictureId;
    }

    public String getSmallPictureId() {
        return this.smallPictureId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getContent() {
        return this.content;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public Integer getClickActionType() {
        return this.clickActionType;
    }

    public String getClickActionActivity() {
        return this.clickActionActivity;
    }

    public String getClickActionUrl() {
        return this.clickActionUrl;
    }

    public String getActionParameters() {
        return this.actionParameters;
    }

    public Integer getShowTimeType() {
        return this.showTimeType;
    }

    public Long getShowStartTime() {
        return this.showStartTime;
    }

    public Long getShowEndTime() {
        return this.showEndTime;
    }

    public Boolean getOffLine() {
        return this.offLine;
    }

    public Integer getOffLineTtl() {
        return this.offLineTtl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Integer getPushTimeType() {
        return this.pushTimeType;
    }

    private Long getPushStartTime() {
        return this.pushStartTime;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    private Boolean getFixSpeed() {
        return this.fixSpeed;
    }

    private Long getFixSpeedRate() {
        return this.fixSpeedRate;
    }

    public Integer getNetworkType() {
        return this.networkType;
    }

    public String getCallBackUrl() {
        return this.callBackUrl;
    }

    public String getCallBackParameter() {
        return this.callBackParameter;
    }

    public Integer getShowTtl() {
        return this.showTtl;
    }

    public Integer getNotifyId() {
        return this.notifyId;
    }

    public Integer getBadgeMessageCount() {
        return this.badgeMessageCount;
    }

    public Integer getBadgeOperationType() {
        return this.badgeOperationType;
    }

    public String getAuditResponse() {
        return this.auditResponse;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString() {
        return "Notification{appMessageId='" + this.appMessageId + "', style=" + this.style + ", bigPictureId='" + this.bigPictureId + "', smallPictureId='" + this.smallPictureId + "', title='" + this.title + "', subTitle='" + this.subTitle + "', content='" + this.content + "', channelId='" + this.channelId + "', clickActionType=" + this.clickActionType + ", clickActionActivity='" + this.clickActionActivity + "', clickActionUrl='" + this.clickActionUrl + "', actionParameters='" + this.actionParameters + "', showTimeType=" + this.showTimeType + ", showStartTime=" + this.showStartTime + ", showEndTime=" + this.showEndTime + ", offLine=" + this.offLine + ", offLineTtl=" + this.offLineTtl + ", pushTimeType=" + this.pushTimeType + ", pushStartTime=" + this.pushStartTime + ", timeZone='" + this.timeZone + "', fixSpeed=" + this.fixSpeed + ", fixSpeedRate=" + this.fixSpeedRate + ", networkType=" + this.networkType + ", callBackUrl='" + this.callBackUrl + "', callBackParameter='" + this.callBackParameter + "', showTtl=" + this.showTtl + ", notifyId=" + this.notifyId + ", badgeMessageCount=" + this.badgeMessageCount + ", badgeOperationType=" + this.badgeOperationType + ", auditResponse='" + this.auditResponse + "'}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validate() {
        Validate.nonNull(this.title, "title");
        Validate.validateLength(this.title, "title", 50);
        Validate.nonNull(this.content, "content");
        Validate.validateLength(this.content, "content", 200);
        if (this.offLine != null && this.offLine.booleanValue() && this.offLineTtl != null) {
            Validate.validateSize(this.offLineTtl.intValue(), "off_line_ttl", 864000);
        }
        if (!Validate.isEmpty(this.subTitle)) {
            Validate.validateLength(this.subTitle, "sub_title", 10);
        }
        if (this.style != null) {
            Validate.validateStyle(this.style);
        }
        if (!Validate.isEmpty(this.clickActionActivity)) {
            Validate.validateLength(this.clickActionActivity, "click_action_activity", 500);
        }
        if (!Validate.isEmpty(this.clickActionUrl)) {
            Validate.validateLength(this.clickActionUrl, "click_action_url", 2000);
        }
        if (!Validate.isEmpty(this.callBackParameter)) {
            Validate.validateLength(this.callBackParameter, "call_back_parameter", 50);
        }
        if (this.showTtl != null) {
            if (this.showTtl.intValue() < 21600 || this.showTtl.intValue() > 172800) {
                Validate.validateSize(this.showTtl.intValue(), "show_ttl", Integer.MIN_VALUE);
            }
        }
    }
}
