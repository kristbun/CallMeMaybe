package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);

        if (sp.getBoolean("LOGIN",false) == true){
            new Checklogin().execute();
        }
    }

    private class Checklogin extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            SharedPreferences sp = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);
            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_user/checklogin";
            RestService re = new RestService();
            String Username = sp.getString("USERNAME", "");
            String Password = sp.getString("PASSWORD", "");
            JSONObject resultJson = re.putLogin(url, Username, Password);
            return resultJson;
        }

        @Override
        protected void onPostExecute(JSONObject resultJson) {
            try {
                if (resultJson.getString("message").equals("OK")){
                    Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(resultJson);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);

        EditText editName = (EditText)findViewById(R.id.username);

        editName.setText(sp.getString("USERNAME", ""));
    }


    public void onLoginClick(View v){
        EditText user = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.userpw);

        if ( user.getText().toString() != null && !(user.getText().toString().isEmpty())
             && password.getText().toString() != null && !(password.getText().toString().isEmpty()))
        {
            new UserJson().execute("");
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please Fill Username or Password", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private class UserJson extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_user/checklogin";

            RestService re = new RestService();

            EditText user = (EditText) findViewById(R.id.username);
            EditText password = (EditText) findViewById(R.id.userpw);

            JSONObject resultJson = re.putLogin(url, user.getText().toString(), password.getText().toString());

            return resultJson;
        }

        @Override
        protected void onPostExecute(JSONObject resultJson) {
            Log.d("STRING", resultJson.toString());
            try {

                if (resultJson.getString("message").equals("OK")){
                    Log.d("STRING", resultJson.toString());
                    SharedPreferences sp = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("LOGIN", true);

                    EditText Username = (EditText)findViewById(R.id.username);
                    editor.putString("USERNAME", Username.getText().toString());

                    EditText Password = (EditText) findViewById(R.id.userpw);
                    editor.putString("PASSWORD", Password.getText().toString());

                    JSONArray jsonarray;
                    jsonarray = resultJson.getJSONArray("result");
                    resultJson = jsonarray.getJSONObject(0);
                    String user_id = resultJson.getString("user_id");

                    editor.putString("USID",user_id);
                    editor.commit();

                    Toast toast = Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT);
                    toast.show();

                    Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Login FAIL", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(resultJson);
        }
    }

    public void onRegisterClick(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
    }
}
