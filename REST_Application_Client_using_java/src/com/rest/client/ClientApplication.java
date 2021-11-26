package com.rest.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientApplication {

	public static void main(String[] args) {

		try {
			URL url = new URL("http://localhost:4041/rest/hi?id=145421");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream inputStream = connection.getInputStream();
				InputStreamReader reader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(reader);
				String line = br.readLine();
				while (line != null) {
					System.out.println(line);
					line = br.readLine();
				}

				connection.disconnect();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
