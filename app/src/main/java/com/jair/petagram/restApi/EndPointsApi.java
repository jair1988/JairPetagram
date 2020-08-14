package com.jair.petagram.restApi;

import com.jair.petagram.restApi.model.MascotaResponse;
import com.jair.petagram.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EndPointsApi {
    @GET(ConstantesRestApi.URL_GET_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_TOKEN)
    Call<UsuarioResponse> registrarToken(@Field("token") String token);
}
