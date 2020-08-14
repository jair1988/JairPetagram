package com.jair.petagram.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jair.petagram.R;
import com.jair.petagram.adaptadores.AdapterMascota;
import com.jair.petagram.entidades.Mascota;
import com.jair.petagram.presenter.MascotaFragmentPresenter;

import java.util.ArrayList;


public class MascotasFragment extends Fragment implements IMascotaFragmentView {
    private RecyclerView rvMascota;

    public MascotasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mascotas, container, false);
        rvMascota = view.findViewById(R.id.rvMascota);
        createVerticalLinearLayout();
        new MascotaFragmentPresenter(this, getContext());
        return view;
    }

    @Override
    public void createVerticalLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascota.setLayoutManager(llm);
    }

    @Override
    public AdapterMascota iniciarAdaptador(ArrayList<Mascota> mascotas) {
        return new AdapterMascota(mascotas, getActivity());
    }

    @Override
    public void iniciarRV(AdapterMascota adapter) {
        rvMascota.setAdapter(adapter);
    }


}
