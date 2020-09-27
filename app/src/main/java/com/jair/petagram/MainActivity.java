package com.jair.petagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.jair.petagram.adaptadores.PageAdapter;
import com.jair.petagram.fragment.DetalleMascotaFragment;
import com.jair.petagram.fragment.MascotasFragment;
import com.jair.petagram.restApi.EndPointsApi;
import com.jair.petagram.restApi.adapter.RestApiAdapter;
import com.jair.petagram.restApi.model.UsuarioResponse;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    public String tokenPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadActionBar();
        loadViewPager();
        getOnlyTokenPush();
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
            case R.id.action_notification:
                registerTokenPush();
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

    //get Token com.jair.petagram.firebase
    private void registerTokenPush() {
        try {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            EndPointsApi endPointsApi = restApiAdapter.connectApiHeroku();
            Call<UsuarioResponse> usuarioResponseCall = endPointsApi.registrarToken(tokenPush);
            usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    UsuarioResponse usuarioResponse = response.body();
                    Toast.makeText(MainActivity.this, "El id generado en la BD fue " + Objects.requireNonNull(usuarioResponse).getId() + "y el token de tu dispositivo es " + usuarioResponse.getToken(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error escribiendo en Firebase", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error obteniendo token", Toast.LENGTH_LONG).show();
        }
    }

    //get Token com.jair.petagram.firebase
    private void getOnlyTokenPush() {
        try {
            FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Error obteniendo token", Toast.LENGTH_LONG).show();
                }
                tokenPush = Objects.requireNonNull(task.getResult()).getToken();
            });
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error obteniendo token", Toast.LENGTH_LONG).show();
        }
    }

}
