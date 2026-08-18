package com.oppo.push.server;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Target.class */
public class Target {
    private TargetType targetType = TargetType.REGISTRATION_ID;
    private String targetValue;

    public TargetType getTargetType() {
        return this.targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public String getTargetValue() {
        return this.targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public static Target build(String targetValue) {
        Target target = new Target();
        target.setTargetValue(targetValue);
        return target;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validate() {
        if (Validate.isEmpty(this.targetValue)) {
            throw new IllegalArgumentException("target value is null");
        }
        if (this.targetType == TargetType.REGISTRATION_ID) {
            String[] registrationIds = this.targetValue.split(";");
            for (String registrationId : registrationIds) {
                if (!Validate.validateRegistrationId(registrationId)) {
                    throw new IllegalArgumentException(String.format("registration_id[%s] format error", registrationId));
                }
            }
        }
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static void main(String[] args) {
        Target target = build("OPPO_CN_8798e41bc5036f3df027b168a1919206;OPPO_CN_8798e41bc5036f3df027b168a1919206");
        target.validate();
    }
}
