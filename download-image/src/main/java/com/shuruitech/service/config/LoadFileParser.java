package com.shuruitech.service.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class LoadFileParser {

    private static final Logger LOGGER  = LoggerFactory.getLogger(LoadFileParser.class);

    private static final String request_url="ecmUrl";

    private static final String REQUEST_URL_FILE="request_url.properties";


    public static Properties getProperties() throws  Exception {
        Properties properties = new Properties();

        String path = LoadFileParser.class.getClassLoader().getResource(REQUEST_URL_FILE).getPath();

        if(null== path || path.isEmpty()){
            //读取jar外部同级的配置文件
            String filePath = System.getProperty("user.dir") + "request_url.properties";

            LOGGER.info("read config path : {}" ,filePath );

            InputStream in = new BufferedInputStream(new FileInputStream(filePath));

            properties.load(in);

        }else{
            //读取jar包的配置文件
            InputStream inputStream = LoadFileParser.class.getClassLoader().getResourceAsStream(REQUEST_URL_FILE);
            properties.load(inputStream);

            LOGGER.info("read config path : {}" ,path );

        }
        return properties;
    }
}
