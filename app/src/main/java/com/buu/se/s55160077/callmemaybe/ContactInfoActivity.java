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


public class ContactInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        String cname;
        String ctel;
        String cmail;

        Bundle extras = getIntent().getExtras();

        cname = extras.getString("NAME");
        ctel = extras.getString("TEL");
        cmail = extras.getString("MAIL");

        EditText name = (EditText) findViewById(R.id.name);
        EditText tel = (EditText) findViewById(R.id.telnum);
        EditText mail = (EditText) findViewById(R.id.mail);

        name.setText(cname);
        tel.setText(ctel);
        mail.setText(cmail);
    }

    public void onEditClick(View v){
        EditText name = (EditText) findViewById(R.id.name);
        EditText telnum = (EditText) findViewById(R.id.telnum);

        if ( name.getText().toString() != null && !(name.getText().toString().isEmpty())
                && telnum.getText().toString() != null && !(telnum.getText().toString().isEmpty()))
        {
            new EditContact().execute("");
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please Fill Name and Telnum", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private class EditContact extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_list/listupdate";

            RestService re = new RestService();

            EditText name = (EditText) findViewById(R.id.name);
            EditText tel = (EditText) findViewById(R.id.telnum);
            EditText mail = (EditText) findViewById(R.id.mail);

            Bundle extras = getIntent().getExtras();

            String cid = extras.getString("ID");

            JSONObject resultJson = re.putEditContat(url, name.getText().toString(),
                    tel.getText().toString(), mail.getText().toString(), cid);

            return resultJson;
        }

        @Override
        protected void onPostExecute(JSONObject resultJson) {
            Log.d("STRING", resultJson.toString());
            try {

                if (resultJson.getString("result").equals("SUCCESS")){

                    Toast toast = Toast.makeText(getApplicationContext(), "Edit Success", Toast.LENGTH_SHORT);
                    toast.show();

                    finish();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't Edit to contact", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(resultJson);
        }
    }
}
