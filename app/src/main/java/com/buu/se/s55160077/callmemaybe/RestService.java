package com.buu.se.s55160077.callmemaybe;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by LUKHINNN on 12/05/2015.
 */
public class RestService {
    public JSONObject putUser(String url,String username,String password)
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

            jsonObject.put("user_login",username);
            jsonObject.put("user_password",password);

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

    public JSONObject putAddContat(String url,String name,String telnum,
                                   String email,String usid,String gid)
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

            jsonObject.put("cat_id",gid);
            jsonObject.put("user_id",usid);
            jsonObject.put("lit_name",name);
            jsonObject.put("lit_telephone",telnum);
            jsonObject.put("lit_email",email);

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

    public JSONObject putEditContat(String url,String name,String telnum,
                                   String email,String conid)
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

            jsonObject.put("lit_name",name);
            jsonObject.put("lit_telephone",telnum);
            jsonObject.put("lit_email",email);
            jsonObject.put("lit_id",conid);

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

    public JSONObject putAddGroup(String url,String name,String usid)
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

            jsonObject.put("user_id",usid);
            jsonObject.put("cat_name",name);

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

    public static JSONObject doGet(String url) {
        JSONObject json = null;

        HttpClient httpclient = new DefaultHttpClient();
        // Prepare a request object
        HttpGet httpget = new HttpGet(url);
        // Accept JSON
        httpget.addHeader("accept", "application/json");
        // Execute the request
        HttpResponse response;

        try {
            response = httpclient.execute(httpget);
            // Get the response entity
            // Log.e("myApp", "Issue is here...!");
            HttpEntity entity = response.getEntity();
            // If response entity is not null
            if (entity != null) {
                // get entity contents and convert it to string
                InputStream instream = entity.getContent();
                String result= convertStreamToString(instream);
                // construct a JSON object with result
                json=new JSONObject(result);
                // Closing the input stream will trigger connection release
                instream.close();
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Return the json
        return json;
    }
}
