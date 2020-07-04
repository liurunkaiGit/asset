package com.ruoyi.assetspackage.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	/**
	 * get请求
	 * @param requestUrl
	 * @return
	 */
	public static JSONObject doGet(String requestUrl) throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String responseContent = null;
		JSONObject result = null;
		try {
			//创建Get请求
			HttpGet httpGet = new HttpGet(requestUrl);
			//执行Get请求，
			response = httpClient.execute(httpGet);
			//得到响应体
			HttpEntity entity = response.getEntity();
			//获取响应内容
			responseContent = EntityUtils.toString(entity, "UTF-8");
			//转换为map
			result = JSON.parseObject(responseContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * post请求
	 * @param json
	 * @param url
	 * @return
	 */
	public static JSONObject doPost(String json, String url) throws Exception{
		JSONObject jsonObj=null;
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/json");
		post.addHeader("Authorization", "Basic YWRtaW46");
		String result = "";
		try {
			StringEntity s = new StringEntity(json, "utf-8");
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			post.setEntity(s);
			// 发送请求
			HttpResponse httpResponse = HttpClients.createDefault().execute(post);
			// 获取响应输入流
			InputStream inStream = httpResponse.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
			StringBuilder strber = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
				strber.append(line + "\n");
			inStream.close();

			result = strber.toString();
			System.out.println(result);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				System.out.println("==========请求服务器成功==============");
				jsonObj = JSONObject.parseObject(result);
			} else {
				System.out.println("=============请求服务端失败===========");
				jsonObj = JSONObject.parseObject(result);
			}
		} catch (Exception e) {
			System.out.println("===========请求异常=================");
			throw new RuntimeException(e);
		}
		return jsonObj;
	}


	/**
	 *自定义请求方式
	 * @param requestURL
	 * @param method
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static JSONObject sendMsg(String requestURL, String method, String json) throws Exception {
		URL get_url = new URL(requestURL);
		HttpURLConnection httpURLConnection = (HttpURLConnection) get_url.openConnection();
		// 设置请求方式为post
		httpURLConnection.setRequestMethod(method);
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);

		// post请求缓存设为false
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setRequestProperty("Content-type", "application/json;charset=utf-8");
		httpURLConnection.connect();
		OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
		if (method.equals("POST")) {
			out.append(json);
		}
		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
		// 读取数据操作
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = reader.readLine()) != null) {
			buffer.append(str);
		}
		//转换成json
		JSONObject jsonObj = JSONObject.parseObject(buffer.toString());
		reader.close();
		return jsonObj;

	}


}

