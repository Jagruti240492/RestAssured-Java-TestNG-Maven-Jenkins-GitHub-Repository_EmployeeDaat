package com.employeeapi.testcases;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_GET_Single_Employee extends TestBase
{
	//https://dummy.restapiexample.com/api/v1/employee/1
	
	@BeforeClass
	public void getEmployee() throws InterruptedException
	{
		logger.info("*************Started TC002_GET_Single_Employee*************");
		RestAssured.baseURI = "https://dummy.restapiexample.com";
		httprequest = RestAssured.given();
		response = httprequest.request(Method.GET,"/api/v1/employee/"+emp_ID);
		
		Thread.sleep(5000);
	}
	
	@Test(priority=1)
	void checkResponseBody()
	{
		logger.info("*************Checking Response Body*************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ===> "+responseBody);
		Assert.assertTrue(responseBody.contains(emp_ID));
	}
	
	@Test(priority=2)
	void checkStatusCode()
	{
		logger.info("*************Checking Status Code*************");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ===> "+statusCode);
		Assert.assertEquals(statusCode,200);
	}
	
	@Test(priority=3)
	void checkResponseTime()
	{
		logger.info("*************Checking Response Time*************");
		long responseTime = response.getTime();
		logger.info("Response Time ===> "+responseTime);
		if(responseTime>2000)
		{
			logger.warn("Response time is greater than 2000 ms");
		}
		
		Assert.assertTrue(responseTime<2000);
	}
	
	@Test(priority=4)
	void checkStatusLine()
	{
		logger.info("*************Checking Status Line*************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line ===> "+statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}
	
	@Test(priority=5)
	void checkContentType()
	{
		logger.info("*************Checking Content Type*************");
		String content_type = response.header("Content-Type");
		logger.info("Content Type ===> "+content_type);
		Assert.assertEquals(content_type,"application/json");
	}
	
	@Test(priority=6)
	void checkServerType()
	{
		logger.info("*************Checking Server Type*************");
		String server_type = response.header("Server");
		logger.info("Server Type ===> "+server_type);
		Assert.assertEquals(server_type,"nginx");
	}
	
	@Test(priority=7)
	void checkContentLength()
	{
		logger.info("*************Checking Content Length*************");
		String content_length = response.header("Content-Length");
		logger.info("Content Length ===> "+content_length);
		
		if(Integer.parseInt(content_length)<100)
		{
			logger.warn("Content length is less than 100");
		}
		Assert.assertTrue(Integer.parseInt(content_length)>100);
	}
	
	@AfterClass
	void tearDown() throws InterruptedException
	{
		logger.info("*************Finished TC002_GET_Single_Employee*************");
		Thread.sleep(5000);
	}
	

}

