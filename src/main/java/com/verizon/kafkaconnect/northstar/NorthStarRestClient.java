package com.verizon.kafkaconnect.northstar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class NorthStarRestClient {

	 
	 static { 
		 HttpsURLConnection.setDefaultHostnameVerifier(new  HostnameVerifier() 
		 		{ public boolean verify(String hostname, SSLSession  session)
		 			{	  if (hostname.equals("192.168.0.3")) return true; return false;
		 			 }
		 		}); 
	 		}
	
	
	public static void main(String[] args) {
		//callRest();
		//	callRestUrlVoid("http://www.mocky.io/v2/592c0f1a10000062153898d2");	
		//	System.out.println(getAuthToken("https://192.168.0.3:5000/v3/auth/tokens"));
	}

	
public String callRestServicewithAuthToken( String authToken,String url, String topic, String bootstrapServers ){
	//System.out.println("inside callRestServicewithAuthToken ");
	String authenticationToken=null;
	
	if(authToken!=null && authToken.length()>9){
	 authenticationToken=getAuthToken(authToken);
	}
	
	Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	WebTarget webTarget = client.target(url);
	Invocation.Builder invocationBuilder;
	
	if (authenticationToken!=null){
     invocationBuilder =  webTarget.request(
			MediaType.APPLICATION_JSON).header("X-Auth-Token", authenticationToken);
	}else {
		 invocationBuilder =  webTarget.request(
					MediaType.APPLICATION_JSON);
	}
	Response response = invocationBuilder.get();
	if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "	+ response.getStatus());
		}
	NorthStarProducer producer=new NorthStarProducer();
	
	
	try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(InputSteramtoString(response.readEntity(InputStream.class)));
				producer.produceMessage(root.toString(),topic,bootstrapServers);	
				return null;
				
	} catch (JsonParseException e) {
		
		e.printStackTrace();
	} catch (JsonMappingException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
     
return null;
}


private static String getAuthToken(String authToken) {
	
Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	
	WebTarget webTarget = client.target(authToken);

	Invocation.Builder invocationBuilder = webTarget
			.request(MediaType.APPLICATION_JSON);
		
	String body="{\r\n    \"auth\": {\r\n        \"identity\": {\r\n            \"methods\": [\r\n                \"password\"\r\n            ],\r\n            \"password\": {\r\n                \"user\": {\r\n                    \"id\": \"3abd1c4b579e4a019afa0dc6e64c4a62\",\r\n                    \"password\": \"sdnadminos@123\"\r\n                }\r\n            }\r\n        },\r\n        \"scope\": {\r\n            \"project\": {\r\n                \"id\": \"1f9f615c8d784dbba027f6d73262064a\"\r\n            }\r\n        }\r\n    }\r\n}";

	Response response = invocationBuilder.post(Entity.json(body));
	
	String token =response.getHeaderString("X-Subject-Token");
	System.out.println(token);
	
	
	return token;
}

public static String callRest(){
	
	BufferedReader reader = new BufferedReader(new  InputStreamReader(callRestUrl("http://www.mocky.io/v2/592c0f1a10000062153898d2"), StandardCharsets.UTF_8));		  
	 StringBuilder result = new StringBuilder();		  
	 String line;
	 				  
	  try {
	  
	  while ((line = reader.readLine()) != null)
	  		{
		  	result.append(line);
	  			}
	  
	  			System.out.println(result);
	 
	  		} catch (IOException e) {
	  			System.out.println("Exception is " +e);  
	  								}
	return result.toString();
}

public static InputStream callRestUrl(String urltoCall) {

	Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	
	WebTarget webTarget = client.target(urltoCall);

	Invocation.Builder invocationBuilder = webTarget
			.request(MediaType.APPLICATION_JSON);
	
	Response response = invocationBuilder.get();

	
	
	return response.readEntity(InputStream.class);

}

public static void callRestUrlVoid(String urltoCall) {

	Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	
	WebTarget webTarget = client.target(urltoCall);

	Invocation.Builder invocationBuilder = webTarget
			.request(MediaType.APPLICATION_JSON);
	
	Response response = invocationBuilder.get();

	
	System.out.println(response.getHeaderString("Content-Type"));
	
	System.out.println(response.getHeaders().toString());
	
	ObjectMapper mapper = new ObjectMapper();
	try {
	 
		
		JsonNode root = mapper.readTree(InputSteramtoString(response.readEntity(InputStream.class)));
	
		
		JsonNode servers = root.path("servers");
		
		for (JsonNode node : servers) {
			String type = node.path("status").asText();
			String ref = node.path("hostId").asText();
			System.out.println("type : " + type);
			System.out.println("ref : " + ref);

		}
		
	} catch (JsonParseException e) {
		 
		e.printStackTrace();
	} catch (JsonMappingException e) {
	 
		e.printStackTrace();
	} catch (IOException e) {
		 
		e.printStackTrace();
	}
     
		
	

}


public static String InputSteramtoString(InputStream stream){
	
	BufferedReader reader = new BufferedReader(new  InputStreamReader(stream, StandardCharsets.UTF_8));		  
	 StringBuilder result = new StringBuilder();		  
	 String line;
	 				  
	  try {
	  
	  while ((line = reader.readLine()) != null)
	  		{
		  	result.append(line);
	  			}
	  
	  			System.out.println(result);
	 
	  		} catch (IOException e) {
	  			System.out.println("Exception is " +e);  
	  								}
	return result.toString();
}

}