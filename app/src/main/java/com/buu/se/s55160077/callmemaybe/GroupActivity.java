package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GroupActivity extends Activity {
    private List<GroupItem> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        mItems  = new ArrayList<GroupItem>();

        for(int i=0;i<3;i++)
        {
            GroupItem listObj = new GroupItem();
            listObj.setTxtGroupID(String.valueOf(i));
            listObj.setTxtGroup("เพื่อน " + String.valueOf(i));
            mItems.add(listObj);
        }

        GroupAdapter adapter = new GroupAdapter(getApplicationContext(),mItems);

        ListView listView = (ListView)findViewById(R.id.allGroup);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long ID) {
                GroupItem groupItem = mItems.get(position);
                String id = groupItem.getTxtGroupID();

                Toast toast = Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(GroupActivity.this, AllContactActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onAddGroupClick(View v){
        Intent intent = new Intent(this, AddGroupActivity.class);
        this.startActivity(intent);
    }
}
