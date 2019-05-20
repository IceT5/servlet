package com.shuruitech.hypertelepathia.servlet.client;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.alibaba.fastjson.JSON;
import com.shuruitech.hypertelepathia.servlet.client.util.TelepathiaHttpClient;

/**
 * 
 * @author yangyuguang
 * @date 2019.4.22
 */
public class TelepathiaClient {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws JDOMException {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", "杨宇光");
        try {
            String context = TelepathiaHttpClient.get("http://localhost:8080/hyper-telepathia-taikang-servlet/login",
                    params, "utf-8");
            SAXBuilder builder = new SAXBuilder();
            StringReader reader = new StringReader(context);
            Document document = builder.build(reader);
            Element root = document.getRootElement();
            List<Element> city = root.getChildren("city");
            for (Element element : city) {
                System.out.println(element.getChildText("name"));
                System.out.println(element.getChildText("address"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
