package com.oppo.push.server;

import com.alibaba.fastjson.JSON;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/Auth.class */
public class Auth {
    private static final String SHA_256 = "SHA-256";

    Auth() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Result getAuthResult(String appKey, String masterSecret, Environment env) throws Exception {
        Map<String, Object> body = new HashMap<>(3);
        long timestamp = System.currentTimeMillis();
        body.put("app_key", appKey);
        body.put("timestamp", Long.valueOf(timestamp));
        body.put("sign", getSign(appKey, timestamp, masterSecret));
        return getAuthResultWithRetry(RequestPath.AUTH, body, env);
    }

    private static Result getAuthResultWithRetry(RequestPath path, Map<String, Object> body, Environment env) throws Exception {
        Result result = null;
        int tryTime = 0;
        while (tryTime < Constants.HTTP_RETRY_TIME) {
            try {
                result = HttpClientTool.httpPost(path, body, env);
            } catch (Exception e) {
                e.printStackTrace();
                if (tryTime >= Constants.HTTP_RETRY_TIME - 1) {
                    throw e;
                }
                tryTime++;
            }
            if (result != null && !Validate.isEmpty(result.getToken())) {
                return result;
            }
            tryTime++;
        }
        Object[] objArr = new Object[1];
        objArr[0] = result == null ? "get token result is null" : JSON.toJSONString(result);
        throw new RuntimeException(String.format("get token error: %s", objArr));
    }

    private static String getSign(String appKey, long timestamp, String masterSecret) throws Exception {
        String plaintext = String.format("%s%s%s", appKey, Long.valueOf(timestamp), masterSecret);
        return encrypt(plaintext, SHA_256);
    }

    private static String encrypt(String plaintext, String encryptType) throws Exception {
        String cipherText = null;
        if (plaintext != null && plaintext.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(encryptType);
                messageDigest.update(plaintext.getBytes());
                byte[] byteBuffer = messageDigest.digest();
                StringBuilder strHexString = new StringBuilder(byteBuffer.length * 2);
                for (byte b : byteBuffer) {
                    String hex = Integer.toHexString(255 & b);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                cipherText = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return cipherText;
    }
}
