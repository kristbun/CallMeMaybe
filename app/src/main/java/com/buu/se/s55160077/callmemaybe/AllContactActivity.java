package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class AllContactActivity extends Activity {

    private List<ContactItem> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contact);

        mItems  = new ArrayList<ContactItem>();

        for(int i=0;i<10;i++)
        {
            ContactItem listObj = new ContactItem();
            listObj.setTxtName("name " + String.valueOf(i));
            listObj.setTxtTelnum("087-6061250");
            mItems.add(listObj);
        }

        ContactAdapter adapter = new ContactAdapter(getApplicationContext(),mItems);

        ListView listView = (ListView)findViewById(R.id.allContact);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(AllContactActivity.this, ContactInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onAddContactClick(View v){
        Intent intent = new Intent(this, AddContactActivity.class);
        this.startActivity(intent);
    }
}
