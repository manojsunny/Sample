package com.nit.rest.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.client.PassengerInfo;

public class Client {
	
	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:4041/rest/bookTicket");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			//create passenger info
			PassengerInfo info =  new PassengerInfo(); 
			info.setFirstName("john");
			info.setLastName("smith");
			info.setFrom("Hyd");
			info.setTo("Goa");
			info.setJourneyDate("30-10-2021");
			info.setTrainNumber("Vasco-Di-Gamma-78541");
			
			ObjectMapper mapper = new ObjectMapper();
			String valueAsString = mapper.writeValueAsString(info);
			
			connection.setDoOutput(true);
			
			connection.setRequestProperty("Content-type", "application/json");
			connection.setRequestProperty("accept", "application/json");
			
			OutputStream stream = connection.getOutputStream();
			stream.write(valueAsString.getBytes());
			stream.flush();
			
			int code = connection.getResponseCode();
			
			if(code == 201) {
				InputStream inputStream = connection.getInputStream();
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = bufferedReader.readLine();
				if(line != null) {
					System.out.println("\n"+line);
					line = bufferedReader.readLine();
				}
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}