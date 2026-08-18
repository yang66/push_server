package com.oppo.push.server;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/TargetType.class */
public enum TargetType {
    REGISTRATION_ID(2);
    
    private int value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getValue() {
        return this.value;
    }

    TargetType(int value) {
        this.value = value;
    }
}
