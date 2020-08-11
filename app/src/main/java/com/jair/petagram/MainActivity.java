package com.jair.petagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jair.petagram.Fragment.DetalleMascotaFragment;
import com.jair.petagram.Fragment.MascotasFragment;
import com.jair.petagram.adaptadores.PageAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadActionBar();
        loadViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_contact:
                startActivity(new Intent(this, ContactoActivity.class));
                return true;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.action_favorite:
                startActivity(new Intent(this, Favorites.class));
                return true;
            case R.id.action_configure:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    //Load Action Bar
    private void loadActionBar() {
        Toolbar toolbar = findViewById(R.id.actionBar);
        toolbar.setTitle("");
        ((TextView) toolbar.findViewById(R.id.tvTitle)).setText("Petagram");
        toolbar.setLogo(R.drawable.pet);
        setSupportActionBar(toolbar);
    }

    //Load view pager
    private void loadViewPager() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        fragmentArrayList.add(new MascotasFragment());
        fragmentArrayList.add(new DetalleMascotaFragment());
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), 0, fragmentArrayList));
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_house);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_dog);
    }

}
