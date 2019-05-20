package com.shuruitech.hypertelepathia.servlet.client.util;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author yangyuguang
 * @date 2019.4.22
 */
public class TelepathiaHttpClient {
    /**
     * 
     * @param url
     * @param params
     * @param encoding
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public static String get(String url, Map<String, String> params, String encoding) throws ClientProtocolException, IOException {
        url = url + (null == params ? "" : serialParams(params));
        HttpGet httpGet = new HttpGet(url);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }

    private static String serialParams(Map<String, String> params) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("?");
        for (Entry<String, String> entry : params.entrySet()) {
            buffer.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        String parameters = buffer.toString();
        return parameters.substring(0, parameters.length() - 1);
    }
}
