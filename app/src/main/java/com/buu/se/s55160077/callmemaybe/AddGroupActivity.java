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


public class AddGroupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
    }

    public void onCreateClick(View v){
        EditText name = (EditText) findViewById(R.id.groupName);

        if ( name.getText().toString() != null && !(name.getText().toString().isEmpty()))
        {
            new AddContact().execute("");
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please Fill Group Name", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private class AddContact extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_category/categoryinsert";

            RestService re = new RestService();

            EditText name = (EditText) findViewById(R.id.groupName);

            SharedPreferences sp = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);

            String uid = sp.getString("USID", "");

            JSONObject resultJson = re.putAddGroup(url, name.getText().toString(), uid);

            return resultJson;
        }

        @Override
        protected void onPostExecute(JSONObject resultJson) {
            Log.d("STRING", resultJson.toString());
            try {

                if (resultJson.getString("result").equals("SUCCESS")){

                    Toast toast = Toast.makeText(getApplicationContext(), "Add Success", Toast.LENGTH_SHORT);
                    toast.show();

                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't Add Group", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(resultJson);
        }
    }
}
