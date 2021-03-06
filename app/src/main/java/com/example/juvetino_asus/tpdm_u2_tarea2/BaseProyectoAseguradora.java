package com.example.juvetino_asus.tpdm_u2_tarea2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseProyectoAseguradora extends SQLiteOpenHelper {



    public BaseProyectoAseguradora  (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROPIETARIO (" +
                "TELEFONO VARCHAR(20) PRIMARY KEY NOT NULL," +
                "NOMBRE VARCHAR(200) NOT NULL," +
                "DOMICILIO VARCHAR(200),"+
                "FECHA DATE)");

        db.execSQL("CREATE TABLE SEGURO (" +
                "IDSEGURO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "DESCRIPCION VARCHAR(200) NOT NULL," +
                "FECHA DATE," +
                "TIPO VARCHAR(20)," +
                "TELEFONO VARCHAR(20)  NOT NULL, FOREIGN KEY (TELEFONO) REFERENCES PROPIETARIO(TELEFONO))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
