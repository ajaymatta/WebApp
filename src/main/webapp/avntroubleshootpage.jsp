<%@ page language="java"  %>

<%@ page import="org.apache.http.*" %>
<%@ page import="org.apache.http.HttpEntity" %>
<%@ page import="org.apache.http.HttpException" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.client.methods.HttpGet" %>
<%@ page import="org.apache.http.entity.StringEntity" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.message.BasicHeader" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.client.HttpClient" %>
<%@ page import="org.apache.http.params.HttpConnectionParams" %>
<%@ page import="org.apache.http.protocol.HTTP"%>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.InputStream" %>



<html>

<body>

<%
HttpClient httpClient = new DefaultHttpClient();
HttpGet httpget = new HttpGet("http://demo9051112.mockable.io");
HttpResponse httpresponse = httpClient.execute(httpget);

 HttpEntity entity = httpresponse.getEntity();
        InputStream instream = entity.getContent();
StringBuilder sb = new StringBuilder();
BufferedReader r = new BufferedReader(new InputStreamReader(instream), 1000);
for (String line = r.readLine(); line != null; line = r.readLine()) {
    sb.append(line);
}
instream.close();

out.print("Rest Api Response " +sb.toString()); 
JSONObject myResponse = new JSONObject(sb.toString());

out.print("<br> " +"JSON Response of key name link : "+myResponse.getString("link"));


 %>  

</body>
</html>