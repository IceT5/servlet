package com.shuruitech.hypertelepathia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuruitech.hypertelepathia.servlet.pojo.City;
import com.shuruitech.hypertelepathia.servlet.util.TelepathiaHttpServerUtil;

/**
 * 
 * @author yangyuguang
 * @date 2019.4.22
 */
public class TaikangServerServlet extends HttpServlet {

	private static final long serialVersionUID = -2719441036664004410L;

	private List<City> citys = new ArrayList<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		City c1 = new City("杭州", "浙江");
		City c2 = new City("郑州", "河南");
		City c3 = new City("哈尔滨", "黑龙江");
		citys.add(c1);
		citys.add(c2);
		citys.add(c3);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// sovle the get garbled
		try {
			String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "utf-8");
			response.setCharacterEncoding("utf-8");
			System.out.println(username);
			response.setHeader("content-type", "text/xml;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
					"<images>\r\n" + 
					"	<image>\r\n" + 
					"		<serialno>20090604092204515001</serialno>\r\n" + 
					"		<companycode>1</companycode>\r\n" + 
					"		<scancompanycode>1</scancompanycode>\r\n" + 
					"		<instcode>0001</instcode>\r\n" + 
					"		<opercode>0001</opercode>\r\n" + 
					"		<keyvalue>123456</keyvalue>\r\n" + 
					"		<cardtype>01010010</cardtype>\r\n" + 
					"		<grouptype>91000000</grouptype>\r\n" + 
					"		<scantime>2009-06-04 09:22:04</scantime>\r\n" + 
					"		<itemid/>\r\n" + 
					"		<cardname>mp3</cardname>\r\n" + 
					"		<saveecmtime>2009-06-04 09:22:04</saveecmtime>\r\n" + 
					"		<imagepages>1</imagepages>\r\n" + 
					"		<imageurl>\r\n" + 
					"  <![CDATA[\r\n" + 
					"http://10.129.33.200/CMQuery/NewViewResultServlet?serialno=20090604092204515001&cardtype=01010010&compno=1&IP=10.1\r\n" + 
					"  ]]>\r\n" + 
					"		</imageurl>\r\n" + 
					"	</image>\r\n" + 
					"</images>");
//            for (City city : citys) {
//                writer.append("<city>");
//                writer.append("<name>").append(city.getName()).append("</name>");
//                writer.append("<address>").append(city.getAddress()).append("</address>");
//                writer.append("</city>");
//            }
			response.setStatus(200);
			TelepathiaHttpServerUtil.log(request, response);
		} catch (Exception e) {
			response.setStatus(500);
			response.sendRedirect("2;error.jsp");
			TelepathiaHttpServerUtil.log(request, response);
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// solve the post garbled
		System.out.println(request.getAttribute("username"));
		request.setCharacterEncoding("utf-8");
		System.out.println("经过post");

	}

}
