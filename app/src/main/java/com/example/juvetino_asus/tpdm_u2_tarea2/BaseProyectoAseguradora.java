package com.example.juvetino_asus.tpdm_u2_tarea2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseProyectoAseguradora extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SEGURO(IDSEGURO INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " DESCRIPCION VARCHAR(200) NOT NULL," +
                "FECHA DATE, " +
                "TIPO FLOAT," +
                " TELEFONO VARCHAR (200))");

        db.execSQL("CREATE TABLE PROPIETARIO(FOREIGN KEY (TELEFONO) REFERENCES SEGURO (TELEFONO) ," +
                "NOMBRE VARCHAR(200), " +
                "DOMICILIO VARCHAR (200)," +
                " FECHA VARCHAR (200))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
