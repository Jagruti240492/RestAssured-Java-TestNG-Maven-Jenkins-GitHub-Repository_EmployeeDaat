package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase
{
	public static RequestSpecification httprequest;
	public static Response response;
	public String emp_ID = "3";
	
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		logger = Logger.getLogger("EmployeeRestApI"); 
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
}
