package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GroupActivity extends Activity {
    private List<GroupItem> mItems;
    private String gid;

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

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long ID) {
                        GroupItem groupItem = mItems.get(position);
                        String gname = groupItem.getTxtGroup();

                        gid = groupItem.getTxtGroupID();

                        Log.d("STRING", gname);

                        final Dialog dialog = new Dialog(GroupActivity.this,R.style.spoungebobDialog);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.group_edit);
                        dialog.setCancelable(true);
                        dialog.show();
                        EditText editText = (EditText)dialog.findViewById(R.id.groupName);
                        editText.setText(gname);

                        Button rename = (Button)dialog.findViewById(R.id.rename);
                        rename.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText editText = (EditText) dialog.findViewById(R.id.groupName);

                                if (editText.getText().toString() != null && !(editText.getText().toString().isEmpty())) {
                                    dialog.dismiss();
                                    new EditGroup().execute(editText.getText().toString());
                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Please Fill Groupname", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            }
                        });

                        Button del = (Button)dialog.findViewById(R.id.del);
                        del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new DelGroup().execute("");
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

    public void onAddGroupClick(View v){
        Intent intent = new Intent(this, AddGroupActivity.class);
        this.startActivity(intent);
    }


    private class EditGroup extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_category/categoryupdate";

            RestService re = new RestService();
            JSONObject resultJson = re.putEditGroup(url,gid,params[0]);

            return resultJson;
        }

        @Override
        protected void onPostExecute(JSONObject resultJson) {
            Log.d("STRING", resultJson.toString());
            try {

                if (resultJson.getString("result").equals("SUCCESS")){

                    Toast toast = Toast.makeText(getApplicationContext(), "Edit Success", Toast.LENGTH_SHORT);
                    toast.show();
                    new GroupJson().execute("");
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't Edit to Groupname", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(resultJson);
        }
    }

    private class DelGroup extends AsyncTask<String, Integer, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            String url = "http://10.16.77.108/dezatoshop/restcontact/index.php/api/c_con_category/categorydelete";

            RestService re = new RestService();
            JSONObject resultJson = re.putDeleteGroup(url,gid);

            return resultJson;
        }

        @Override
        protected void onPostExecute(JSONObject resultJson) {
            Log.d("STRING", resultJson.toString());
            try {

                if (resultJson.getString("result").equals("SUCCESS")){

                    Toast toast = Toast.makeText(getApplicationContext(), "Delete Success", Toast.LENGTH_SHORT);
                    toast.show();
                    new GroupJson().execute("");
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Can't Delete to Groupname", Toast.LENGTH_SHORT);
                    toast.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(resultJson);
        }
    }
}
