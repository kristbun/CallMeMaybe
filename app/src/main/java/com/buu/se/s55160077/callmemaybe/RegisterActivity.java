package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onSignUpClick(View v){
        EditText name = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.userPW);
        EditText repassword = (EditText) findViewById(R.id.userRePW);

        if ( name.getText().toString() != null && !(name.getText().toString().isEmpty())
                && password.getText().toString() != null && !(password.getText().toString().isEmpty())
                && repassword.getText().toString() != null && !(repassword.getText().toString().isEmpty()))
        {
            if (password.getText().toString().matches(repassword.getText().toString())){
                new AddUser().execute("");
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Password in 2 field is not match", Toast.LENGTH_SHORT);
                toast.show();
            }

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please Fill User and Password", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private class AddUser extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_user/userinsert";

            RestService re = new RestService();

            EditText name = (EditText) findViewById(R.id.username);
            EditText password = (EditText) findViewById(R.id.userPW);

            JSONObject resultJson = re.putUser(url, name.getText().toString(), password.getText().toString());

            return resultJson;
        }

        @Override
        protected void onPostExecute(JSONObject resultJson) {
            Log.d("STRING", resultJson.toString());
            try {

                if (resultJson.getString("result").equals("SUCCESS")){

                    Toast toast = Toast.makeText(getApplicationContext(), "Add User Success", Toast.LENGTH_SHORT);
                    toast.show();

                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't Add to contact", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(resultJson);
        }
    }
}
