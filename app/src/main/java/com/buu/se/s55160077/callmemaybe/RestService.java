package com.buu.se.s55160077.callmemaybe;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by LUKHINNN on 12/05/2015.
 */
public class RestService {
    public JSONObject putLogin(String url,String user,String pw)
    {
        InputStream inputStream = null;
        JSONObject jsonObj = null;
        boolean resultBool = false;

        try {
            // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL

            HttpPost httpPut = new HttpPost(url);
            String json = "";
            //              // 3. build jsonObject
            //              JSONObject jsonObject2 = new JSONObject();
            //              jsonObject2.put("idGuarderias", idG);
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("user_login",user);
            jsonObject.put("user_password",pw);
            //      jsonObject.put("guarderiasIdGuarderias",jsonObject2);
            json = jsonObject.toString();
            Log.d("STRING", json);
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            httpPut.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httpPut.addHeader("Accept", "application/json");
            httpPut.addHeader("Content-type", "application/json");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPut);


            //Try to add this
            inputStream = httpResponse.getEntity().getContent();
            String result = convertStreamToString(inputStream);
            // construct a JSON object with result
            jsonObj = new JSONObject(result);
            // Closing the input stream will trigger connection release
            inputStream.close();


        } catch (Exception e) {
            //Log.d("InputStream", e.getLocalizedMessage());
        }
        return jsonObj;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }
}
