package com.employeeapi.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_DELETE_Employee extends TestBase
{
	//https://dummy.restapiexample.com/api/v1/delete/2
	
	@BeforeClass
	public void deleteEMployee() throws InterruptedException
	{
		logger.info("*************TC005_DELETE_Employee*************");
		
		RestAssured.baseURI="https://dummy.restapiexample.com";
		httprequest = RestAssured.given();
		
		//getting list of all the employees
		response = httprequest.request(Method.GET,"/api/v1/employees");
		
		Thread.sleep(5000);
		
		//storing the first record from the employee list
		String empID = response.body().jsonPath().get("[0].id");
		
		//deleting the first record that found in the employee list
		response = httprequest.request(Method.DELETE,"/api/v1/delete/"+empID);
		
		Thread.sleep(5000);
	}
	
	@Test(priority=1)
	void checkResponseBody()
	{
		logger.info("*************Checking Response Body*************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ===> "+responseBody);
		Assert.assertEquals(response.body().jsonPath().get("status"), "success");
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
		
		if(Integer.parseInt(content_length)<20)
		{
			logger.warn("Content length is less than 100");
		}
		Assert.assertTrue(Integer.parseInt(content_length)>20);
	}
	
	@AfterClass
	void tearDown() throws InterruptedException
	{
		logger.info("*************Finished TC005_DELETE_Employee*************");
		Thread.sleep(5000);
	}
}
