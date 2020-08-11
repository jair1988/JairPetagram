package com.jair.petagram.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jair.petagram.restApi.ConstantesRestApi;
import com.jair.petagram.restApi.EndPointsApi;
import com.jair.petagram.restApi.deserializer.MascotaDeserialize;
import com.jair.petagram.restApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    public EndPointsApi connectApi(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantesRestApi.ROOT_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit.create(EndPointsApi.class);
    }

    public Gson buildDeserializerMediaRecent() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserialize());
        return gsonBuilder.create();
    }
}
