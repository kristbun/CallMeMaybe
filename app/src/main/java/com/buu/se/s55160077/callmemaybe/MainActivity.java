package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onAddContactClick(View v){
        Intent intent = new Intent(this, AddContactActivity.class);
        this.startActivity(intent);
    }
    public void onAddGroupClick(View v){
        Intent intent = new Intent(this, AddGroupActivity.class);
        this.startActivity(intent);
    }
    public void onAllClick(View v){
        Intent intent = new Intent(this, AllContactActivity.class);
        this.startActivity(intent);
    }
}
