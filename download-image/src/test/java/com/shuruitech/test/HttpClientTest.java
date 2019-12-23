package com.shuruitech.test;

import com.alibaba.fastjson.JSON;
import com.shuruitech.service.util.HttpClient;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {

    @Test
    public void testGet(){

        Map<String, String> content = new HashMap<>();
        content.put("keyvalue","aa");
        content.put("compno","cc");
        content.put("cardtype","ddd");


        String response = "";
        try{
            response = HttpClient.get("http://192.168.5.1:9090/login", content);
            System.out.println(JSON.toJSON(response));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
