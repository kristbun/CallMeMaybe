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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GroupActivity extends Activity {
    private List<GroupItem> mItems;

    @Override
    protected void onResume() {
        super.onResume();

        new GroupJson().execute("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        new GroupJson().execute("");


    }

    private class GroupJson extends AsyncTask<String, Integer, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... params) {

            SharedPreferences sp = getSharedPreferences("USERLOGIN", Context.MODE_PRIVATE);
            String usid = sp.getString("USID","");

            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_category/categorys/user_id/"+usid+"/";

            RestService re = new RestService();
            JSONObject jsonobject =  re.doGet(url);

            return jsonobject;
        }


        @Override
        protected void onPostExecute(JSONObject jsonobject) {
            try {
                mItems  = new ArrayList<GroupItem>();

                JSONArray jsonarray;
                jsonarray = jsonobject.getJSONArray("categorys");
                int lengthObj = jsonarray.length();

                for (int i = 0; i < lengthObj; i++) {
                    jsonobject = jsonarray.getJSONObject(i);
                    String gid = jsonobject.getString("cat_id");
                    String gname = jsonobject.getString("cat_name");
                    mItems.add(new GroupItem(gname,gid));
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
                GroupAdapter adapter = new GroupAdapter(getApplicationContext(),mItems);

                ListView listView = (ListView)findViewById(R.id.allGroup);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View view, int position, long ID) {
                        GroupItem groupItem = mItems.get(position);
                        String id = groupItem.getTxtGroupID();

                        Intent intent = new Intent(GroupActivity.this, ContactActivity.class);
                        intent.putExtra("GID", id);
                        startActivity(intent);
                    }
                });
                ImageView imgView = (ImageView)findViewById(R.id.bob);
                imgView.setVisibility(View.GONE);
            }

            super.onPostExecute(jsonobject);
        }
    }

    public void onAddGroupClick(View v){
        Intent intent = new Intent(this, AddGroupActivity.class);
        this.startActivity(intent);
    }
}
