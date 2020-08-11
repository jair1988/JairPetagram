package com.jair.petagram.restApi;


import com.jair.petagram.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointsApi {
    @GET(ConstantesRestApi.URL_GET_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();
}
