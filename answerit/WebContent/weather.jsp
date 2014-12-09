<%@page import="org.json.simple.JSONValue,java.util.Map,java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.net.URL,java.net.URLConnection,java.io.InputStream,org.apache.commons.io.IOUtils,org.json.simple.JSONArray,org.json.simple.JSONObject,org.json.simple.parser.JSONParser,org.json.simple.parser.ParseException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Weather</title>
<link rel="stylesheet" href="cont.css" type="text/css">
</head>
<body>
<%

try
{
URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+request.getParameter("pl"));
URLConnection con = url.openConnection();
InputStream in = con.getInputStream();
String encoding = con.getContentEncoding();
encoding = encoding == null ? "UTF-8" : encoding;
String body = IOUtils.toString(in, encoding);
//out.println(body);

Map icmap = new HashMap();
icmap.put("light rain", "11.png");
icmap.put("broken clouds", "6.png");
icmap.put("few clouds", "5.png");
icmap.put("Sky is Clear", "1.png");


out.println("<table cols='2' id='wea'>");


JSONParser parser = new JSONParser();
Object obj = parser.parse(body);

JSONObject jsonObject = (JSONObject) obj;
String name = (String) jsonObject.get("name");
//out.print("<br>"+name);
JSONObject ob= (JSONObject) jsonObject.get("main");
//out.print("<br>"+ob.get("temp")+"<br>");


double d=Double.valueOf(ob.get("temp").toString());
d=d-274;
d=Math.rint(d);
//out.print("<br>"+d+"C");

/*double age = (double) ob.get("temp");
System.out.println(age);*/

	JSONArray ob1= (JSONArray) jsonObject.get("weather");
JSONObject we=(JSONObject) ob1.get(0);
//out.print("<img src='/img/"+icmap.get(we.get("description"))+"'>");
	out.println("<tr><td colspan=2 style='padding-left:18%;text-align:center;'><p><b><font size='25px'>"+d+"&deg;c</b></font><img src='img/"+icmap.get(we.get("description"))+"' width='80px' height='80px' align='center'>  <b>"+we.get("description")+"</b></p></td></tr>");
	//out.println("<tr><td colspan=2 align=center><b>"+d+"C </b></td></tr>");
	//out.println("<br>"+we.get("description"));
	
	
	URL url1 = new URL("http://maps.googleapis.com/maps/api/geocode/json?address="+request.getParameter("pl")+"&sensor=false");
	URLConnection con1 = url1.openConnection();
	InputStream in1 = con1.getInputStream();
	String encoding1 = con1.getContentEncoding();
	encoding1 = encoding1 == null ? "UTF-8" : encoding1;
	String body1 = IOUtils.toString(in1, encoding1);
	
	Object obj1 = parser.parse(body1);
	 
	JSONObject jsonObject1 = (JSONObject) obj1;
	
	 
		String sta = (String) jsonObject1.get("status");
		if(sta.equals("OK"))
		{
			String coun[],co;
		 	JSONArray res= (JSONArray) jsonObject1.get("results");
			System.out.println("Len " +res.size());
			JSONObject nam= (JSONObject) res.get(0);
			co=(String)nam.get("formatted_address");
			coun=co.split("\\,");
			if(coun.length>2)
			{
				out.println("<tr><td><b>City  </td><td>"+coun[0]+"</b></td></tr>");
				out.println("<tr><td><b>State  </td><td>"+coun[1]+"</b></td></tr>");
				out.println("<tr><td><b>Country  </td><td>"+coun[2]+"</b></td></tr>");
			}
			else
			{
				out.println("<tr><td><b>State  </td><td>"+coun[0]+"</b></td></tr>");
				out.println("<tr><td><b>Country  </td><td>"+coun[1]+"</b></td></tr>");
			}
			
			
		}
	
	
	out.print("</table>");



}
catch( Exception e )
{
  System.out.println( "GetResponse.GetResponse - error opening or reading URL: " + e );
}
%>
</body>
</html>