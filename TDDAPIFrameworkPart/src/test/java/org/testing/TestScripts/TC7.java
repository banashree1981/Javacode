 package org.testing.TestScripts;

import java.io.IOException;
import java.util.Properties;

import org.testing.ResponseValidation.ResponseValidation;
import org.testing.TestSteps.HTTPMethods;
import org.testing.Utilities.PropertiesFileLoad;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

// TC7-------------GET Employee Information based upon id


public class TC7 
{
	
	 String idValue;
	
    @Test
	public void testcase7() throws IOException
	{
		Properties pr= PropertiesFileLoad.propertiesFile ("../TDDAPIFrameworkPart/Env.Properties");
		HTTPMethods http= new HTTPMethods(pr);
		Response res= http.GetEmployeeInfoBasedUponId("dummy_securi", "users","2");
		
		System.out.println("Seventh TestCase");
		System.out.println(res.asString());
		System.out.println(res.statusCode());
		
		ResponseValidation.responseStatusCodeValidate(200, res);


	}
}

