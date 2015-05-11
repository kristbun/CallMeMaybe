package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    }
}
