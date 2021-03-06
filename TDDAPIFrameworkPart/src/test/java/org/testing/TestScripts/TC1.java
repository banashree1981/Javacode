package org.testing.TestScripts;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testing.ResponseValidation.ResponseValidation;
import org.testing.TestSteps.HTTPMethods;
import org.testing.Utilities.PropertiesFileLoad;
import org.testing.Utilities.ResponseDataParsingUsingJsonPath;
import org.testing.Utilities.ResponseParsingUsingOrgJson;
import org.testing.Utilities.jsontoXML;
import org.testing.Utilities.xmltoJson;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

//TC1------- Post Request

public class TC1 {
	
	  static String idValue;
	
	  @Test
	  public void testcase1() throws IOException{
		  
		  Random r= new Random();   // To generate random id
		  Integer i= r.nextInt();
		  System.out.println("Random number generated is" + i);
		  
		  String firstname= RandomStringUtils.randomAlphabetic(10);   //To generate random string
		  System.out.println("Random name generated is" + firstname);
		
		JSONObject data= new JSONObject();
		data.put("firstname", firstname);
		data.put("lastname", "Virus");
		data.put("id", i.toString());
		data.put("designation", "Sr. QA");
		
		
		Properties pr= PropertiesFileLoad.propertiesFile ("../TDDAPIFrameworkPart/Env.Properties");
		HTTPMethods http= new HTTPMethods(pr);
		Response res= http.PostRequest("QA_URI", data.toString());
		
		System.out.println("Status code is" + res.statusCode());
		System.out.println("Data is");
		System.out.println(res.asString());
		
		ResponseValidation.responseStatusCodeValidate(201, res);
		idValue= ResponseDataParsingUsingJsonPath.response_parsing(res, "id");
		
		System.out.println("id value is" + idValue);
		
		String responseMessage= "{\r\n" + 
				"  \"firstName\": \"John\",\r\n" + 
				"  \"lastName\" : \"doe\",\r\n" + 
				"  \"age\"      : 26,\r\n" + 
				"  \"address\"  : {\r\n" + 
				"    \"streetAddress\": \"naist street\",\r\n" + 
				"    \"city\"         : \"Nara\",\r\n" + 
				"    \"postalCode\"   : \"630-0192\"\r\n" + 
				"  },\r\n" + 
				"  \"phoneNumbers\": [\r\n" + 
				"    {\r\n" + 
				"      \"type\"  : \"iPhone\",\r\n" + 
				"      \"number\": \"0123-4567-8888\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"type\"  : \"home\",\r\n" + 
				"      \"number\": \"0123-4567-8910\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}" ;
		
		String m= ResponseParsingUsingOrgJson.responseParsing(responseMessage, "address", "postalCode");
		System.out.println("Postal code is" + m); 
		
		String message= xmltoJson.xmltojsonObject("<note>\r\n" + 
				"<to>Tove</to>\r\n" + 
				"<from>Jani</from>\r\n" + 
				"<heading>Reminder</heading>\r\n" + 
				"<body>Don't forget me this weekend!</body>\r\n" + 
				"</note>");
		
	      System.out.println("After conversion to json");
	      System.out.println(message);
	      
	      String jsonobjectresponseMessage= "{\r\n" + 
	      		"  \"firstName\": \"John\",\r\n" + 
	      		"  \"lastName\" : \"doe\",\r\n" + 
	      		"  \"age\"      : 26,\r\n" + 
	      		"  \"address\"  : {\r\n" + 
	      		"    \"streetAddress\": \"naist street\",\r\n" + 
	      		"    \"city\"         : \"Nara\",\r\n" + 
	      		"    \"postalCode\"   : \"630-0192\"\r\n" + 
	      		"  },\r\n" + 
	      		"  \"phoneNumbers\": [\r\n" + 
	      		"    {\r\n" + 
	      		"      \"type\"  : \"iPhone\",\r\n" + 
	      		"      \"number\": \"0123-4567-8888\"\r\n" + 
	      		"    },\r\n" + 
	      		"    {\r\n" + 
	      		"      \"type\"  : \"home\",\r\n" + 
	      		"      \"number\": \"0123-4567-8910\"\r\n" + 
	      		"    }\r\n" + 
	      		"  ]\r\n" + 
	      		"}";
	      
	      String xmlData= jsontoXML.jsontoXMLConversion(jsonobjectresponseMessage);
	      System.out.println("After conversion to xmlJson" + xmlData);
	      
	      String message1= jsontoXML.jsontoXMLConversion(res.asString());
	      System.out.println("After conversion of json data to xml " + message1);
	      
	      String jsonarrayresponseMessage = "[{\r\n" + 
	      		"	\"friends\": [{\r\n" + 
	      		"		\"id\": \"Test\",\r\n" + 
	      		"		\"firstname\": \"Roopanshi\",\r\n" + 
	      		"		\"lastname\": \"Raj\",\r\n" + 
	      		"		\"age\": 27,\r\n" + 
	      		"		\"designation\": \"QA\"\r\n" + 
	      		"	}]\r\n" + 
	      		"}]";
	      
	      String arrayxmlData= jsontoXML.jsontoXMLConversionn(jsonarrayresponseMessage);
	      System.out.println("After conversion to xmlArray" + arrayxmlData);
	      
	}

}
 