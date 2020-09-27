package com.jair.petagram.restApi;

import com.jair.petagram.restApi.model.MascotaResponse;
import com.jair.petagram.restApi.model.NotificationResponse;
import com.jair.petagram.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EndPointsApi {
    @GET(ConstantesRestApi.URL_GET_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_TOKEN)
    Call<UsuarioResponse> registrarToken(@Field("token") String token);

    @GET(ConstantesRestApi.KEY_GET_SEND_NOTIFICATION)
    Call<NotificationResponse> sendNotification(@Path("token") String token, @Path("mensaje") String mensaje);
}
