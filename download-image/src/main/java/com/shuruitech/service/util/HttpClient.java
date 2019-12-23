package com.shuruitech.service.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpClient {

    private final static CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    public static String post(String url, String jsonString) {

        HttpPost post = new HttpPost(url);

        post.setHeader("Content-type", "application/json; charset=utf-8");

        StringEntity entity = new StringEntity(jsonString, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");

        entity.setContentType("application/json");
        post.setEntity(entity);


        CloseableHttpResponse response;

        try {
            response = HTTP_CLIENT.execute(post);
            HttpEntity responseEntity = response.getEntity();
            String body = null;
            if (entity != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }

            if (Objects.nonNull(body)) {
                return body;
            }
        } catch (Exception e) {
            LOGGER.error("get response by http client error", e);
        }

        return null;
    }

    private static final int SUCCESS_CODE = 200;

    public static String get(String url) {

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
        httpGet.setHeader(new BasicHeader("Accept", "application/json;charset=utf-8"));

        try {
            CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (SUCCESS_CODE == statusCode) {
                HttpEntity responseEntity = response.getEntity();
                String body = null;
                if (responseEntity != null) {
                    // 按指定编码转换结果实体为String类型
                    body = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
                }
                if (Objects.nonNull(body)) {
                    return body;
                }
            }
        } catch (Exception e) {
            LOGGER.error("get response by http client error", e);
        }
        return null;
    }


    public static String get(String url, Map<String, String> content) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : content.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
            URIBuilder builder = new URIBuilder(url);
            builder.setParameters(pairs);
            HttpGet httpGet = new HttpGet(builder.build());
            httpGet.setHeader(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
            httpGet.setHeader(new BasicHeader("Accept", "application/json;charset=utf-8"));

            CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            if (SUCCESS_CODE == statusCode) {
                HttpEntity responseEntity = response.getEntity();
                String body = null;
                if (responseEntity != null) {
                    // 按指定编码转换结果实体为String类型
                    body = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
                }
                if (Objects.nonNull(body)) {
                    return body;
                }
            }
        } catch (Exception e) {
            LOGGER.error("get response by http client error", e);
        }
        return null;
    }
}
