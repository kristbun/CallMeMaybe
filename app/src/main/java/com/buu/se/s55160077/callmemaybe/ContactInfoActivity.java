package com.buu.se.s55160077.callmemaybe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class ContactInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        String cname;
        String ctel;
        String cmail;

        Bundle extras = getIntent().getExtras();

        cname = extras.getString("NAME");
        ctel = extras.getString("TEL");
        cmail = extras.getString("MAIL");

        EditText name = (EditText) findViewById(R.id.name);
        EditText tel = (EditText) findViewById(R.id.telnum);
        EditText mail = (EditText) findViewById(R.id.mail);

        name.setText(cname);
        tel.setText(ctel);
        mail.setText(cmail);


    }

}
