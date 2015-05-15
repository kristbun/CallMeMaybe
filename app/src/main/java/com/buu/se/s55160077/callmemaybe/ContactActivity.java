package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ContactActivity extends Activity {

    private List<ContactItem> mItems;

    @Override
    protected void onResume() {
        super.onResume();

        new ListJson().execute("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        new ListJson().execute("");
    }

    private class ListJson extends AsyncTask<String, Integer, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... params) {

            String gid;
            Bundle extras = getIntent().getExtras();
            gid = extras.getString("GID");

            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_list/lists/category_id/"+gid+"/";

            RestService re = new RestService();
            JSONObject jsonobject =  re.doGet(url);

            return jsonobject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonobject) {
            try {

                mItems  = new ArrayList<ContactItem>();

                JSONArray jsonarray;
                jsonarray = jsonobject.getJSONArray("lists");
                int lengthObj = jsonarray.length();

                for (int i = 0; i < lengthObj; i++) {
                    jsonobject = jsonarray.getJSONObject(i);
                    String name = jsonobject.getString("lit_name");
                    String tel = jsonobject.getString("lit_telephone");
                    String litid = jsonobject.getString("lit_id");
                    String email = jsonobject.getString("lit_email");
                    mItems.add(new ContactItem(name,tel,litid,email));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(mItems.size() == 0){
                ImageView imgView = (ImageView)findViewById(R.id.bob);
                imgView.setVisibility(View.VISIBLE);
            }
            else
            {
                ContactAdapter adapter = new ContactAdapter(getApplicationContext(),mItems);

                ListView listView = (ListView)findViewById(R.id.allContact);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long ID) {
                        ContactItem contactItem = mItems.get(position);
                        String lname = contactItem.getTxtName();
                        String ltel = contactItem.getTxtTelnum();
                        String lmail = contactItem.getTxtConMail();

                        Intent intent = new Intent(ContactActivity.this, ContactInfoActivity.class);
                        intent.putExtra("NAME", lname);
                        intent.putExtra("TEL", ltel);
                        intent.putExtra("MAIL", lmail);
                        startActivity(intent);
                    }
                });

                ImageView imgView = (ImageView)findViewById(R.id.bob);
                imgView.setVisibility(View.GONE);
            }

            super.onPostExecute(jsonobject);
        }
    }

    public void onAddContactClick(View v){
        String gid;
        Bundle extras = getIntent().getExtras();
        gid = extras.getString("GID");

        Intent intent = new Intent(this, AddContactActivity.class);
        intent.putExtra("GID", gid);
        this.startActivity(intent);
    }
}
