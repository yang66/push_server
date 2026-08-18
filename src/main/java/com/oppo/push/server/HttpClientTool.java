package com.oppo.push.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oppo.push.server.ReturnCode;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

/* loaded from: opush-server-sdk-1.1.0.jar:com/oppo/push/server/HttpClientTool.class */
class HttpClientTool {
    private static PoolingHttpClientConnectionManager manager = null;
    private static Charset charset = Consts.UTF_8;

    HttpClientTool() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init() throws Exception {
        if (manager == null) {
            synchronized (PoolingHttpClientConnectionManager.class) {
                if (manager == null) {
                    SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial((KeyStore) null, new TrustStrategy() { // from class: com.oppo.push.server.HttpClientTool.1
                        public boolean isTrusted(X509Certificate[] chain, String authType) {
                            return true;
                        }
                    }).build();
                    HostnameVerifier hostnameVerifier = new HostnameVerifier() { // from class: com.oppo.push.server.HttpClientTool.2
                        @Override // javax.net.ssl.HostnameVerifier
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    };
                    Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.create().register(Constants.HTTPS_PROTOCOL, new SSLConnectionSocketFactory(sslContext, hostnameVerifier)).build();
                    manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                    manager.setMaxTotal(Constants.HTTP_MAX_CONNECTION);
                    manager.setDefaultMaxPerRoute(Constants.HTTP_MAX_ROUTE);
                    ConnectionConfig connectionConfig = ConnectionConfig.custom().setCharset(Charset.forName("utf-8")).build();
                    manager.setDefaultConnectionConfig(connectionConfig);
                    SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(Constants.HTTP_SOCKET_TIMEOUT).build();
                    manager.setDefaultSocketConfig(socketConfig);
                }
            }
        }
    }

    private static JSONObject getResponse(CloseableHttpResponse response) {
        String result = null;
        try {
            try {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, charset);
                if (result == null || result.trim().length() == 0) {
                    if (response != null) {
                        try {
                            response.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
                JSONObject parseObject = JSON.parseObject(result);
                if (response != null) {
                    try {
                        response.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return parseObject;
            } catch (Exception e3) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("message", result);
                jsonObject.put("code", Integer.valueOf(ReturnCode.ErrorCode.RESPONSE_PARSE_ERROR.getCode()));
                if (response != null) {
                    try {
                        response.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        return jsonObject;
                    }
                }
                return jsonObject;
            }
        } catch (Throwable th) {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                    throw th;
                }
            }
            throw th;
        }
    }

    private static List<NameValuePair> buildNameValuePairsParameters(Map<String, Object> params) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue() + ""));
        }
        return nameValuePairs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Result httpPostWithToken(RequestPath path, Map<String, Object> parameters, Sender sender) throws Exception {
        Result tryGetToken;
        Result result = httpPost(path, parameters, sender.getEnv());
        if (result != null && result.getReturnCode() != null && result.getReturnCode().getCode() == ReturnCode.ErrorCode.INVALID_AUTH_TOKEN.getCode() && (tryGetToken = Auth.getAuthResult(sender.getAppKey(), sender.getMasterSecret(), sender.getEnv())) != null && !Validate.isEmpty(tryGetToken.getToken())) {
            sender.setToken(tryGetToken.getToken());
            parameters.put("auth_token", tryGetToken.getToken());
            result = httpPost(path, parameters, sender.getEnv());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Result multipartHttpPostWithToken(RequestPath path, Map<String, Object> parameters, File uploadFile, Sender sender) throws Exception {
        Result tryGetToken;
        Result result = multipartHttpPost(path, parameters, uploadFile, sender.getEnv());
        if (result != null && result.getReturnCode() != null && result.getReturnCode().getCode() == ReturnCode.ErrorCode.INVALID_AUTH_TOKEN.getCode() && (tryGetToken = Auth.getAuthResult(sender.getAppKey(), sender.getMasterSecret(), sender.getEnv())) != null && !Validate.isEmpty(tryGetToken.getToken())) {
            sender.setToken(tryGetToken.getToken());
            result = multipartHttpPost(path, parameters, uploadFile, sender.getEnv());
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Result httpPost(RequestPath path, Map<String, Object> parameters, Environment env) throws Exception {
        HttpPost httpPost = new HttpPost(getUrl(path, env));
        parameters.put("sdk_version", "JAVA_SDK_V1.1.0");
        httpPost.setEntity(new UrlEncodedFormEntity(buildNameValuePairsParameters(parameters), charset));
        return execute(path, httpPost);
    }

    private static Result multipartHttpPost(RequestPath path, Map<String, Object> parameters, File file, Environment env) throws Exception {
        HttpPost httpPost = new HttpPost(getUrl(path, env));
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(charset);
        builder.addTextBody("auth_token", String.valueOf(parameters.get("auth_token")), ContentType.TEXT_PLAIN);
        builder.addTextBody("picture_ttl", String.valueOf(parameters.get("picture_ttl")), ContentType.TEXT_PLAIN);
        builder.addBinaryBody("file", new FileInputStream(file), ContentType.MULTIPART_FORM_DATA, file.getName());
        httpPost.setEntity(builder.build());
        return execute(path, httpPost);
    }

    private static Result execute(RequestPath path, HttpUriRequest request) throws Exception {
        Result result = new Result();
        try {
            CloseableHttpResponse response = getSSLClient().execute(request);
            if (response.getStatusLine() != null) {
                result.setStatusCode(response.getStatusLine().getStatusCode());
                result.setReason(response.getStatusLine().getReasonPhrase());
            }
            JSONObject responseBody = getResponse(response);
            if (responseBody != null) {
                result.setResult(path, responseBody);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static CloseableHttpClient getSSLClient() {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(Constants.HTTP_CONNECTION_TIMEOUT).setConnectionRequestTimeout(Constants.HTTP_CONNECTION_REQUEST_TIMEOUT).setSocketTimeout(Constants.HTTP_SOCKET_TIMEOUT).build();
        return HttpClients.custom().setConnectionManager(manager).setDefaultRequestConfig(config).build();
    }

    private static String getUrl(RequestPath path, Environment env) {
        return String.format("%s%s%s%s", getHost(env, path), RequestPath.BASE_PATH.getPath(), RequestPath.VERSION_PATH.getPath(), path.getPath());
    }

    private static String getHost(Environment env, RequestPath path) {
        switch (env) {
            case CHINA_PRODUCTION:
                if (path == RequestPath.UPLOAD_SMALL_PICTURE || path == RequestPath.UPLOAD_BIG_PICTURE) {
                    return Constants.MEDIA_HOST_PRODUCTION_CHINA;
                }
                return Constants.HOST_PRODUCTION_CHINA;
            case INTERNATIONAL:
                return Constants.HOST_PRODUCTION_INTERNATIONAL;
            default:
                return Constants.HOST_PRODUCTION_CHINA;
        }
    }
}
