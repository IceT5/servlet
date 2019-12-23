package com.shuruitech.hypertelepathia.servlet.client;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.http.client.ClientProtocolException;
import org.jdom.JDOMException;

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

			File file = new File("f://aa");
			boolean success = downloadCompressedPicture(file,
					"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564223403556&di=89107561f960746a059a687740cc0aa4&imgtype=0&src=http%3A%2F%2Fpic.k73.com%2Fup%2Fsoft%2F2016%2F0102%2F092635_44907394.jpg");
			String context = TelepathiaHttpClient.get(
					"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564223403556&di=89107561f960746a059a687740cc0aa4&imgtype=0&src=http%3A%2F%2Fpic.k73.com%2Fup%2Fsoft%2F2016%2F0102%2F092635_44907394.jpg",
					params, "utf-8");
			System.out.println(context);

//			String context = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "<images>\r\n" + "	<image>\r\n"
//					+ "		<serialno>20090604092204515001</serialno>\r\n" + "		<companycode>1</companycode>\r\n"
//					+ "		<scancompanycode>1</scancompanycode>\r\n" + "		<instcode>0001</instcode>\r\n"
//					+ "		<opercode>0001</opercode>\r\n" + "		<keyvalue>123456</keyvalue>\r\n"
//					+ "		<cardtype>01010010</cardtype>\r\n" + "		<grouptype>91000000</grouptype>\r\n"
//					+ "		<scantime>2009-06-04 09:22:04</scantime>\r\n" + "		<itemid/>\r\n"
//					+ "		<cardname>mp3</cardname>\r\n" + "		<saveecmtime>2009-06-04 09:22:04</saveecmtime>\r\n"
//					+ "		<imagepages>1</imagepages>\r\n" + "		<imageurl>\r\n" + "  <![CDATA[\r\n"
//					+ "http://10.129.33.200/CMQuery/NewViewResultServlet?serialno=20090604092204515001&cardtype=01010010&compno=1&IP=10.1\r\n"
//					+ "  ]]>\r\n" + "		</imageurl>\r\n" + "	</image>\r\n" + "</images>";
//			System.out.println(context);
//			SAXBuilder builder = new SAXBuilder();
//			StringReader reader = new StringReader(context);
//			Document document = builder.build(reader);
//			Element root = document.getRootElement();
//			Element child = root.getChild("image");
//			Element child2 = child.getChild("imageurl");
//			String text = child2.getText();
//			System.out.println("==========");
//			System.out.println(text.trim());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static boolean downloadCompressedPicture(File file, String urlstr) {
		URL url = null;
		try {
			url = new URL(urlstr);
			// 1.获取url的输入流 dataInputStream
			DataInputStream dataInputStream = new DataInputStream(url.openStream());
			// 2.加一层BufferedInputStream
			BufferedInputStream bufferedInputStream = new BufferedInputStream(dataInputStream);
			// 3.构造原始图片流 preImage
			BufferedImage preImage = ImageIO.read(bufferedInputStream);
			// 4.获得原始图片的长宽 width/height
			int width = preImage.getWidth();
			int height = preImage.getHeight();
			// 5.构造压缩后的图片流 image 长宽各为原来的1/2
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			// 6.给image创建Graphic ,在Graphic上绘制压缩后的图片
			Graphics graphic = image.createGraphics();
			graphic.drawImage(preImage, 0, 0, width, height, null);
			// 7.为file生成对应的文件输出流
			// 将image传给输出流
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			// 8.将image写入到file中
			ImageIO.write(image, "bmp", bufferedOutputStream);
			// 9.关闭输入输出流
			bufferedInputStream.close();
			bufferedOutputStream.close();

			return true;
		} catch (IOException e) {
			System.out.println(e);
		}

		return false;
	}
}
