package com.jair.petagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        loadActionBar();
}

    //Load Action Bar
    private void loadActionBar() {
        Toolbar toolbar = findViewById(R.id.actionBar);
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tvTitle)).setText("Acerca de");
        toolbar.setLogo(R.drawable.pet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
