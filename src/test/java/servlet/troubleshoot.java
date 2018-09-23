package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;
import org.apache.log4j.Logger;

public class troubleshoot extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res)
		    throws ServletException, IOException{
			final Logger logger = Logger.getLogger(troubleshoot.class);
			try{							
				res.setContentType("text/html");
				PrintWriter out=res.getWriter();
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet("https://6hi3bq7i97.execute-api.ap-southeast-1.amazonaws.com/prod/troubleshootmockservice");
				HttpResponse httpresponse = httpClient.execute(httpget);

				 HttpEntity entity = httpresponse.getEntity();
				        InputStream instream = entity.getContent();
				StringBuilder sb = new StringBuilder();
				BufferedReader r = new BufferedReader(new InputStreamReader(instream), 1000);
				for (String line = r.readLine(); line != null; line = r.readLine()) {
				    sb.append(line);
				}
				instream.close();

				System.out.println("Rest Api Response " +sb.toString());
				logger.debug("This is debug : " + sb.toString());
				JSONObject myResponse = new JSONObject(sb.toString());
				out.print(myResponse.optString("link"));
			}
			catch(Exception e) {
				logger.error("This is debug : " + e);
				System.out.println("Exception occurred : "+e);
			}
	}

}
