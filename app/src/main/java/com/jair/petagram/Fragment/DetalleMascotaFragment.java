package com.jair.petagram.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jair.petagram.R;
import com.jair.petagram.adaptadores.AdapterPerfilMascota;
import com.jair.petagram.entidades.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleMascotaFragment extends Fragment {

    private RecyclerView rvMascota;

    public DetalleMascotaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_mascota, container, false);
        rvMascota = view.findViewById(R.id.rvMascota);
        loadRecyclerView();
        return view;
    }

    //load Recyclerview
    private void loadRecyclerView() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascota.setLayoutManager(gridLayoutManager);
        rvMascota.setAdapter(new AdapterPerfilMascota(mascotas));
    }
}
