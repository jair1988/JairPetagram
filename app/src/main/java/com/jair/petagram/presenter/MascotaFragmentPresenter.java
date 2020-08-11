package com.jair.petagram.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jair.petagram.Fragment.IMascotaFragmentView;
import com.jair.petagram.entidades.Mascota;
import com.jair.petagram.restApi.EndPointsApi;
import com.jair.petagram.restApi.adapter.RestApiAdapter;
import com.jair.petagram.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaFragmentPresenter implements IMascotaFragmentPresenter {
    private IMascotaFragmentView iMascotaFragmentView;
    private Context context;
    ArrayList<Mascota> mascotas;

    public MascotaFragmentPresenter(IMascotaFragmentView iMascotaFragmentView, Context context) {
        this.iMascotaFragmentView = iMascotaFragmentView;
        this.context = context;
        getUserMedia();
    }

    @Override
    public void viewPets() {
        iMascotaFragmentView.iniciarRV(iMascotaFragmentView.iniciarAdaptador(mascotas));
        iMascotaFragmentView.createVerticalLinearLayout();
    }

    @Override
    public void getUserMedia() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndPointsApi endPointsApi = restApiAdapter.connectApi(restApiAdapter.buildDeserializerMediaRecent());
        Call<MascotaResponse> mascotaResponseCall = endPointsApi.getRecentMedia();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                viewPets();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Ocurrio un error", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }
}
