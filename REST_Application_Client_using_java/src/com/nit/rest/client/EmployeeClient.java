package com.nit.rest.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.client.Person;

public class EmployeeClient {
	
	public static void main(String[] args) {
		
		try {
			URL url = new URL("http://localhost:4041/com/employee");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			//setting http headers
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			
			//set data to body
			Person p = new Person();
			p.setName("smith");
			p.setEmail("smith@gmail.com");
			p.setAddress("Hyderabad");
			p.setPersonId(78544545);
			
			ObjectMapper mapper =  new ObjectMapper();
			String string = mapper.writeValueAsString(p);
			
			connection.setDoOutput(true);
			
			OutputStream stream = connection.getOutputStream();
			stream.write(string.getBytes());
			stream.flush();
			
			//get response code
			int responseCode = connection.getResponseCode();
			if(responseCode == 201 || responseCode == 200) {
				InputStream inputStream = connection.getInputStream();
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = bufferedReader.readLine();
				
				while(line != null) {
					System.out.println(line);
					line = bufferedReader.readLine();
				}
				
				connection.disconnect();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
