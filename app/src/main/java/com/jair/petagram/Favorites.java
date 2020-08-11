package com.jair.petagram;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jair.petagram.adaptadores.AdapterMascota;
import com.jair.petagram.entidades.Mascota;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        loadActionBar();
        loadRecyclerView();
    }

    //Load Action Bar
    private void loadActionBar() {
        Toolbar toolbar = findViewById(R.id.actionBar);
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tvTitle)).setText("Petagram");
        toolbar.setLogo(R.drawable.pet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //load Recyclerview
    private void loadRecyclerView() {
        RecyclerView rvMascota = findViewById(R.id.rvMascota);
        ArrayList<Mascota> mascotas = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascota.setLayoutManager(linearLayoutManager);
        rvMascota.setAdapter(new AdapterMascota(mascotas, this));
    }
}
