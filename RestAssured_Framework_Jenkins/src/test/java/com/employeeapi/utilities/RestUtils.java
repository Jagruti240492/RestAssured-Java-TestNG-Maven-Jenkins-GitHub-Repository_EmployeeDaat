package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils
{
	public static String empName()
	{
		String generated_String = RandomStringUtils.randomAlphabetic(1);
		return("Sadguru "+generated_String);
	}
	public static String empSalary()
	{
		String generated_String = RandomStringUtils.randomNumeric(6);
		return(generated_String);
	}
	public static String empAge()
	{
		String generated_String = RandomStringUtils.randomNumeric(2);
		return(generated_String);
	}

}
