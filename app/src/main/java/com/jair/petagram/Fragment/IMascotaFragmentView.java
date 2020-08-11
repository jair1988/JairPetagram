package com.jair.petagram.Fragment;


import com.jair.petagram.adaptadores.AdapterMascota;
import com.jair.petagram.entidades.Mascota;

import java.util.ArrayList;

public interface IMascotaFragmentView {
    public void createVerticalLinearLayout();

    public AdapterMascota iniciarAdaptador(ArrayList<Mascota> mascotas);

    public void iniciarRV(AdapterMascota adapter);
}
