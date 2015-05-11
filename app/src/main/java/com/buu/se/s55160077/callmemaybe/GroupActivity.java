package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class GroupActivity extends Activity {
    private List<ContactItem> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        mItems  = new ArrayList<ContactItem>();

        for(int i=0;i<3;i++)
        {
            ContactItem listObj = new ContactItem();
            listObj.setTxtGroup("เพื่อน " + String.valueOf(i));
            mItems.add(listObj);
        }

        GroupAdapter adapter = new GroupAdapter(getApplicationContext(),mItems);

        ListView listView = (ListView)findViewById(R.id.allGroup);
        listView.setAdapter(adapter);
    }

}
