package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onLoginClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    public void onRegisterClick(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        this.startActivity(intent);
    }
}
