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

    public void onAllContactClick(View v){
        Intent intent = new Intent(this, GroupActivity.class);
        this.startActivity(intent);
    }

    public void onProfileClick(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }

    public void onSignoutClick(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
        finish();
    }
}
