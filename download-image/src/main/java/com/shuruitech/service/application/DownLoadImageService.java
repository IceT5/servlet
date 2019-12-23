package com.shuruitech.service.application;

import com.alibaba.fastjson.JSON;
import com.shuruitech.service.config.LoadFileParser;
import com.shuruitech.service.util.HttpClient;
import com.shuruitech.service.util.ImageUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.util.*;

/**
 * @author  yang
 */
public class DownLoadImageService {

    private static final Logger LOGGER  = LoggerFactory.getLogger(DownLoadImageService.class);


    private static final String ECM_URL="ecmUrl";


    public static void main(String[] args) {

        String requestUrl = readRequestUrl();

        if(requestUrl.isEmpty()){
            LOGGER.warn("requestUrl is empty");
            return;
        }

        //keyvalue=123456&compno=1&cardtype=01010010
        Map<String, String> content = new HashMap<>();
        content.put("keyvalue",args[0]);
        content.put("compno",args[1]);
        content.put("cardtype",args[2]);

        LOGGER.info("start to request, args:  keyVaule:{}, compno: {}, cardType:{}",args[0],args[1],args[2]);

        String response = "";
        try{
            response = HttpClient.get(requestUrl, content);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(response.isEmpty()){
            LOGGER.warn("fail request");
            return ;
        }

//        String response = "<?xml version=\"1.0\" encoding=\"GB2312\" ?> " +
//                "<images>" +
//                "<image>" +
//                "  <serialno>20090604092204515001</serialno> " +
//                "  <companycode>1</companycode>" +
//                "  <scancompanycode>1</scancompanycode>" +
//                "  <instcode>0001</instcode> " +
//                "  <opercode>0001</opercode> " +
//                "  <keyvalue>123456</keyvalue>   " +
//                "  <cardtype>01010010</cardtype>  " +
//                "  <grouptype>91000000</grouptype> " +
//                "  <scantime>2009-06-04 09:22:04</scantime> " +
//                "  <itemid /> " +
//                "  <cardname>mp3</cardname> " +
//                "  <saveecmtime>2009-06-04 09:22:04</saveecmtime> " +
//                "  <imagepages>1</imagepages> " +
//                "<imageurl>" +
//                " <![CDATA[" +
//                "http://10.129.33.200/CMQuery/NewViewResultServlet?serialno=20090604092204515001&cardtype=01010010&compno=1&IP=10.1" +
//                "  ]]> " +
//                "  </imageurl>" +
//                "  </image>" +
//                "  </images>";

        String imageName = args[0]+"-"+args[1]+"-"+args[2];
        List<String> imageUrls = parseXmlToList2(response);
        LOGGER.info("get image url info: {} " ,JSON.toJSON(imageUrls));

        saveImage(imageUrls,imageName);

    }


    private static String readRequestUrl(){
        String requestUrl = "";

        try {
            Properties properties = LoadFileParser.getProperties();
            requestUrl = properties.getProperty(ECM_URL).trim();
        }catch (Exception e){
            e.printStackTrace();
        }

        LOGGER.info("request url: "+requestUrl);
        return  requestUrl;
    }


    @SuppressWarnings("unchecked")
    private static List<String>  parseXmlToList2(String xml) {

        List<String> imageUrls =  new ArrayList<String>();
        try {
            StringReader read = new StringReader(xml);
            InputSource source = new InputSource(read);
            SAXBuilder sb = new SAXBuilder();
            // 通过输入源构造一个Document
            Document doc = (Document) sb.build(source);
            Element root = doc.getRootElement();// 指向根节点
            List<Element> images= root.getChildren("image");
            if(!images.isEmpty()){
                for (Element image: images) {
                    String imageUrl = image.getChildText("imageurl");
                    if(null!=imageUrl&&!imageUrl.isEmpty()){
                        imageUrls.add(imageUrl.trim());
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageUrls;
    }


    private static void saveImage(List<String> imageUrls,String fileName){

        String jarPath = System.getProperty("user.dir");
        int i= 0;
        for (String imageUrl: imageUrls) {
            String imageName = jarPath+ File.separator+fileName+"-"+i+".jpg";

            ImageUtil.saveImage(imageUrl,imageName);
            LOGGER.info("save image {}" +imageName);
            i++;
        }
    }


}
