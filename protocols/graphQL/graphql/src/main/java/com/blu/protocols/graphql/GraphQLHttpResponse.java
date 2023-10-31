package com.blu.protocols.graphql;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.catalina.connector.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GraphQLHttpResponse {
    private static final Logger logger = LoggerFactory.getLogger(GraphQLClient.class);
    private static final String QlURL="http://localhost:8080/graphql";
    private static final String query = "{bookById(id: 2) {id, name}}";

    public static void main(String[] args) throws URISyntaxException, IOException {
        logger.info("GraphQLHttpResponse started!!");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //HttpGet request = new HttpGet(QlURL);
        HttpPost request =  new HttpPost(QlURL);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");

        Map<String, Object> variables = new HashMap<>();
        //String query ="{\"query\":\"query get_book{ bookById(id: 2) {id, name} }\"}";
        String query = "{bookById(id: \"2\") {id, name, pageCount, author {firstName}} }";
        variables.put("query", query);

        JSONObject jsonObject = new JSONObject(variables);
        StringEntity requestEntity = new StringEntity(jsonObject.toString());
        request.setEntity(requestEntity);


//        URI uri = new URIBuilder(request.getURI())
//                .addParameter("query", query)
//                .build();
        //logger.info("URI to call: "+ uri.toString());
        // set the request
        //request.setURI(uri);
        // make the get request
        CloseableHttpResponse response =  httpClient.execute(request);
        // print the response
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder builder = new StringBuilder();
        String line="";
        while ((line = bufReader.readLine()) != null) {
            builder.append(line);
            builder.append(System.lineSeparator());
        }

        logger.info("[RESPONSE]:" + builder.toString());

    }
}
