package com.qa.justtotest;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class TestApi {
	 static TestBase testBase;
	 static String serviceUrl;
	static String apiUrl;
	static String url;
	static RestClient restClient;
	static CloseableHttpResponse closebaleHttpResponse;
	static Properties prop;

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
	//create base class object
		
		testBase = new TestBase();
		
		//Access base class prop 
		
		prop=testBase.prop;
		serviceUrl =prop.getProperty("URL");
		apiUrl =prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		
		
		url = serviceUrl + apiUrl;
		restClient = new RestClient();
		closebaleHttpResponse = restClient.get(url);
		
		//a. Status Code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->"+ statusCode);
		
		Assert.assertEquals(statusCode,testBase.RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//b. Json String:
				String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
				
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from API---> "+ responseJson);
				//single value assertion:
				//per_page:
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("value of per page is-->"+ perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 3);
				
				//total:
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("value of total is-->"+ totalValue);		
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		

	}

}
