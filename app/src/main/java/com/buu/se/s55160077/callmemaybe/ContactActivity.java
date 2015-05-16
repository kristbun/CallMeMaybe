package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ContactActivity extends Activity {

    private List<ContactItem> mItems;
    private String cid;

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
                ContactAdapter adapter = new ContactAdapter(getApplicationContext(),mItems);
                ListView listView = (ListView)findViewById(R.id.allContact);
                listView.setAdapter(adapter);
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
                        String lid = contactItem.getTxtConID();

                        Intent intent = new Intent(ContactActivity.this, ContactInfoActivity.class);
                        intent.putExtra("NAME", lname);
                        intent.putExtra("TEL", ltel);
                        intent.putExtra("MAIL", lmail);
                        intent.putExtra("ID", lid);
                        startActivity(intent);
                    }
                });

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long ID) {
                        ContactItem conItem = mItems.get(position);
                        cid = conItem.getTxtConID();
                        String cname = conItem.getTxtName();

                        final Dialog dialog = new Dialog(ContactActivity.this, R.style.spoungebobDialog);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.contact_delete);
                        dialog.setCancelable(true);
                        dialog.show();
                        TextView textView = (TextView)dialog.findViewById(R.id.NAME);
                        textView.setText(cname);

                        Button del = (Button) dialog.findViewById(R.id.delete);
                        del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new DelContact().execute("");
                                dialog.dismiss();
                            }
                        });
                        Button cancel = (Button) dialog.findViewById(R.id.cancel);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        return true;
                    }
                });

                ImageView imgView = (ImageView)findViewById(R.id.bob);
                imgView.setVisibility(View.GONE);
            }

            super.onPostExecute(jsonobject);
        }
    }

    private class DelContact extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_list/listdelete";

            RestService re = new RestService();
            JSONObject resultJson = re.putDeleteContact(url, cid);

            return resultJson;
        }

        @Override
        protected void onPostExecute(JSONObject resultJson) {
            Log.d("STRING", resultJson.toString());
            try {

                if (resultJson.getString("result").equals("SUCCESS")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Delete Success", Toast.LENGTH_SHORT);
                    toast.show();
                    new ListJson().execute("");
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't Delete Contact", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(resultJson);
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
