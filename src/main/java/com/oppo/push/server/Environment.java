package com.oppo.push.server;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Environment.class */
public enum Environment {
    CHINA_PRODUCTION("CHINA_PRODUCTION"),
    INTERNATIONAL("INTERNATIONAL");
    
    private String env;

    Environment(String env) {
        this.env = env;
    }

    public String getEnv() {
        return this.env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
