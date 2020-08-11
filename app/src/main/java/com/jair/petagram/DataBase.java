package com.jair.petagram;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jair.petagram.entidades.LikeMascota;
import com.jair.petagram.entidades.Mascota;

@SuppressWarnings("FieldCanBeLocal")
public class DataBase {

    //region Variables

    //Context
    private final Context mCtx;
    //Helper
    private final DatabaseHelper mBdHelper;
    //Name instance BD
    private SQLiteDatabase sqLiteDatabase;
    //Version data base
    private static final int VERSION_BD = 1;
    //Name data base
    private static final String NAME_BD = "BDMASCOTAS";
    //Sentence Create Table
    private static final String CREATE_TABLE_IF_EXISTS = "CREATE TABLE IF NOT EXISTS";
    // endregion

    //region Columns Tables
    //Tables
    private static final String TABLE_MASCOTA = "MASCOTA";
    private static final String TABLE_LIKE_MASCOTA = "LikeMascota";

    //endregion

    //region Sentence Create Tables
    private static final String BD_MASCOTA = String.format("%s %s(%s TEXT NOT NULL PRIMARY KEY, %s TEXT NOT NULL, %s INTEGER NOT NULL)", CREATE_TABLE_IF_EXISTS, TABLE_MASCOTA, "idMascota", "nombre", "foto");
    private static final String BD_LIKE_MASCOTA = String.format("%s %s(%s TEXT NOT NULL,%s INTEGER NOT NULL)", CREATE_TABLE_IF_EXISTS, TABLE_LIKE_MASCOTA, "idMascota", "voto");
    //endregion

    //region Internal
    public DataBase(Context ctx) {
        this.mCtx = ctx;
        mBdHelper = new DatabaseHelper(mCtx);
    }

    private void open() {
        sqLiteDatabase = mBdHelper.getWritableDatabase();
    }

    private void close() {
        mBdHelper.close();
    }
    //endregion

    //region Table Session
    public void insertLike(LikeMascota likeMascota) {
        try {
            open();
            ContentValues values = new ContentValues();
            values.put("idMascota", likeMascota.getIdMascota());
            values.put("voto", likeMascota.getVoto());
            sqLiteDatabase.insert(TABLE_LIKE_MASCOTA, null, values);
        } catch (Exception e) {
            Log.e("Error_BD", e.getMessage());
        } finally {
            close();
        }
    }

    public Mascota readLikes(Mascota mascota) {
        try {
            open();
            String[] campos = new String[]{"voto"};
            String[] whereArgs = new String[]{
                    String.valueOf(mascota.getId())
            };
            Cursor query = sqLiteDatabase.query(TABLE_LIKE_MASCOTA, campos, "idMascota = ?", whereArgs, null, null, null);
            int votos = 0;
            if (query != null) {
                query.moveToFirst();
                if (query.getCount() > 0)
                    for (int i = 0; i < query.getCount(); i++) {

                        votos += query.getInt(0);
                        query.moveToNext();
                    }

                query.close();
            }
            mascota.setCantidadVotos(votos);
            return mascota;
        } catch (Exception e) {
            Log.e("Error_BD", e.getMessage());
            return null;
        } finally {
            close();
        }
    }
    //endregion

    //region Private Class DatabaseHelper

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, NAME_BD, null, VERSION_BD);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Create Tables
            try {
                db.execSQL(BD_MASCOTA);
            } catch (Exception e) {
                Log.e("Error_BD", e.getMessage());
            }
            try {
                db.execSQL(BD_LIKE_MASCOTA);
            } catch (Exception e) {
                Log.e("Error_BD", e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int previousVersion, int newVersion) {
            //Not used in version 1
        }
    }

    //endregion
}
