package com.teltech.quiz_service.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class WebCallHelper {

	public String getCallForAPI(String apiUrl) { 
       
      
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            // Build GET Request
            //var request = ClassicRequestBuilder.get(apiUrl).build();
            HttpGet request = new HttpGet(apiUrl);
            request.addHeader("Accept", "application/json"); // Set Accept header

            // Execute Request
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // Read JSON response as String
                String jsonResponse = EntityUtils.toString(response.getEntity());

                return jsonResponse;
                // Convert JSON response to List<Question> using Jackson
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String();
        }
    }
	
}