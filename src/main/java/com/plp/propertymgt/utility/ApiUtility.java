package com.plp.propertymgt.utility;

/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Utility class for calling APIs.
 *
 * @since 1.0.0
 */
public final class ApiUtility {

    // to prevent instantiation
    private ApiUtility() {
    }

    /**
     * Used for calling the REST APIs.
     *
     * @param httpGet Instance of the relevant httpGet
     * @return JSONObject representation of the json response
     * @throws Exception 
     * @throws EngineeringMetricsException, ParseException
     */
    public static JSONObject invokeApi(HttpGet httpGet) throws Exception {
        String jsonText;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            int responseCode = httpResponse.getStatusLine().getStatusCode();
            if (responseCode == 200) {
                //success
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
                        .getContent(), StandardCharsets.UTF_8))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    // creating a JSON object from the response
                    jsonText = stringBuilder.toString();
                }
            } else {
                throw new Exception("Error occurred while calling the API, the response code is " +
                        responseCode);
            }
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject)parser.parse(jsonText);
        return jsonObject;
    }

}
