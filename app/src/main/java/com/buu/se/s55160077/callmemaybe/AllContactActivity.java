package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
            listObj.setTxtConID(String.valueOf(i));
            listObj.setTxtName("name " + String.valueOf(i));
            listObj.setTxtTelnum("087-6061250");
            mItems.add(listObj);
        }

        ContactAdapter adapter = new ContactAdapter(getApplicationContext(),mItems);

        ListView listView = (ListView)findViewById(R.id.allContact);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long ID) {
                ContactItem contactItem = mItems.get(position);
                String id = contactItem.getTxtConID();

                Toast toast = Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT);
                toast.show();

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
