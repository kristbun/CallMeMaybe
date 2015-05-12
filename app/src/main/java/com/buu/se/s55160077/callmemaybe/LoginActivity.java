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

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);

        EditText editName = (EditText)findViewById(R.id.username);

        editName.setText(sp.getString("USERNAME", ""));
    }


    public void onLoginClick(View v){
        new UserJson().execute("");
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
