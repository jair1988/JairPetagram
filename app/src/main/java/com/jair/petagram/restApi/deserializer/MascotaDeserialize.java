package com.jair.petagram.restApi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jair.petagram.entidades.Mascota;
import com.jair.petagram.restApi.JsonKeys;
import com.jair.petagram.restApi.model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MascotaDeserialize implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaData = json.getAsJsonObject().getAsJsonArray(JsonKeys.RESPONSE_ARRAY_MEDIA);
        mascotaResponse.setMascotas(deserializeJson(mascotaData));
        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializeJson(JsonArray mascotaData) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaData.size(); i++) {
            JsonObject mascotaUser = mascotaData.get(i).getAsJsonObject();
            mascotas.add(new Mascota(mascotaUser.get(JsonKeys.DATA_USER).getAsString(),mascotaUser.get(JsonKeys.DATA_USER).getAsString(), mascotaUser.get(JsonKeys.DATA_MEDIA_URL).getAsString(), Integer.parseInt(mascotaUser.get(JsonKeys.DATA_LIKES).getAsString().substring(6, 7))));
        }
        return mascotas;
    }
}
