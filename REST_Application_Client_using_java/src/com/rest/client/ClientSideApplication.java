package com.rest.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientSideApplication {

	public static void main(String[] args) {

		try {
			URL url = new URL("http://localhost:4041/rest/employee");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int code = connection.getResponseCode();
			if (code == 200) {
				InputStream inputStream = connection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader reader = new BufferedReader(inputStreamReader);
				String line = reader.readLine();

				while (line != null) {
					System.out.println(line);
					line = reader.readLine();
				}

				connection.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
