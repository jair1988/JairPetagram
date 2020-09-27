package com.jair.petagram.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jair.petagram.DataBase;
import com.jair.petagram.MainActivity;
import com.jair.petagram.R;
import com.jair.petagram.entidades.LikeMascota;
import com.jair.petagram.entidades.Mascota;
import com.jair.petagram.restApi.EndPointsApi;
import com.jair.petagram.restApi.adapter.RestApiAdapter;
import com.jair.petagram.restApi.model.NotificationResponse;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterMascota extends RecyclerView.Adapter<AdapterMascota.ViewHolderMascota> {

    private final List<Mascota> mascotas;
    private final Context context;
    final DataBase dataBase;

    public AdapterMascota(List<Mascota> mascotas, Context context) {
        this.mascotas = mascotas;
        this.context = context;
        dataBase = new DataBase(context);
    }

    @NonNull
    @Override
    public ViewHolderMascota onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mascota, parent, false);
        return new ViewHolderMascota(v);
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolderMascota holder, int position) {
        try {
            final Mascota mascota = mascotas.get(position);
            Picasso.get().load(mascota.getFoto()).into(holder.foto);
            holder.nombre.setText(mascota.getNombre());
            holder.votos.setText(String.valueOf(dataBase.readLikes(mascota).getCantidadVotos()));
            holder.imgHuesoBlanco.setOnClickListener(v -> {
                dataBase.insertLike(new LikeMascota(mascota.getId(), 1));
                holder.votos.setText(String.valueOf(dataBase.readLikes(mascota).getCantidadVotos() + 1));
                try {
                    RestApiAdapter restApiAdapter = new RestApiAdapter();
                    EndPointsApi endPointsApi = restApiAdapter.connectApiHeroku();
                    Call<NotificationResponse> notificationResponseCall = endPointsApi.sendNotification(((MainActivity) context).tokenPush, "Has dado like al perro " + mascota.getNombre());
                    notificationResponseCall.enqueue(new Callback<NotificationResponse>() {
                        @Override
                        public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                            NotificationResponse notificationResponse = response.body();
                            Toast.makeText(context, "Respuesta notificacion enviada " + Objects.requireNonNull(notificationResponse).getMensaje(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<NotificationResponse> call, Throwable t) {
                            Toast.makeText(context, "Error conectando servidor", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(context, "Error enviando notificacion", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e("error;", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    static class ViewHolderMascota extends RecyclerView.ViewHolder {
        private ImageView foto;
        private TextView nombre;
        private TextView votos;
        private ImageView imgHuesoBlanco;

        ViewHolderMascota(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.imgFoto);
            nombre = itemView.findViewById(R.id.txtNombre);
            votos = itemView.findViewById(R.id.txtVotos);
            imgHuesoBlanco = itemView.findViewById(R.id.imgHuesoBlanco);
        }
    }
}
