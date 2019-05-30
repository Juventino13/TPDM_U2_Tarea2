package com.example.juvetino_asus.tpdm_u2_tarea2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class PropietarioClass {

    private BaseProyectoAseguradora base;
    private String telefonoPropietario;
    private String nombrePropietario;
    private String domicilioPropietario;
    private String fechaPropietario;
    protected String error;

    public PropietarioClass(Activity activity){
        base = new BaseProyectoAseguradora(activity, "ASEGURADORA",null,1);
    }

    public PropietarioClass(String telPropietario, String nomPripietario, String domPropietario, String fechaPro){
        this.telefonoPropietario = telPropietario;
        this.nombrePropietario = nomPripietario;
        this.domicilioPropietario = domPropietario;
        this.fechaPropietario = fechaPro;
    }

    public boolean insertar(PropietarioClass propietario) {
        try {
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("TELEFONO", propietario.getTelefonoPropietario());
            datos.put("NOMBRE", propietario.getNombrePropietario());
            datos.put("DOMICILIO", propietario.getDomicilioPropietario());
            datos.put("FECHA", propietario.getFechaPropietario());

            long resultado = transaccionInsertar.insert("PROPIETARIO", null, datos);
            transaccionInsertar.close();
            if (resultado == -1) {
                return false;
            }
        }
        catch (SQLiteException e) {
            error = e.getMessage();
            Log.e("Error SQL ",e.getMessage());
            return false;
        }
        return true;
    }

    public boolean eliminar(PropietarioClass propietario) {
        try {
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            long resultado = transaccionEliminar.delete("PROPIETARIO", "TELEFONO=?", new String[]{"" + propietario.getTelefonoPropietario()});
            transaccionEliminar.close();

            if (resultado < 0) {
                return false;
            }//if
        }//try
        catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }//catch
        return true;
    }//eliminar

    public boolean actualizar(PropietarioClass propietario){
        try{
            SQLiteDatabase transaccionActualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("TELEFONO", propietario.getTelefonoPropietario());
            datos.put("NOMBRE", propietario.getNombrePropietario());
            datos.put("DOMICILIO", propietario.getDomicilioPropietario());
            datos.put("FECHA", propietario.getFechaPropietario());

            String dato[]={propietario.getTelefonoPropietario()+""};
            long resultado = transaccionActualizar.update("PROPIETARIO",datos,"TELEFONO=?",dato);
            transaccionActualizar.close();

            if(resultado<0){
                return false;
            }
        }
        catch(SQLiteException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PropietarioClass[] consultar(){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM PROPIETARIO",null);
            if (c.moveToFirst()) {
                PropietarioClass[] propietarios = new PropietarioClass[c.getCount()];
                int posicion = 0;
                do {
                    propietarios[posicion] = new PropietarioClass (c.getString(0), c.getString(1),c.getString(2),c.getString(3));
                    posicion++;
                }//do
                while (c.moveToNext());
                return propietarios;
            }//if
        }//try
        catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }//catch
        return null;
    }//consultar

    public PropietarioClass consultar(String dato){
        try{
            SQLiteDatabase db=base.getReadableDatabase();
            Cursor c=db.rawQuery("SELECT * FROM PROPIETARIO WHERE TELEFONO=?",new String[]{dato.toString()});
            if (c.moveToFirst()) {
                PropietarioClass propietario = new PropietarioClass(c.getString(0), c.getString(1),c.getString(2),c.getString(3));
                return propietario;
            }//if
        }//try
        catch(SQLiteException e){
            e.printStackTrace();
            return null;
        }//catch
        return null;
    }//consultar

    public String getTelefonoPropietario() {
        return telefonoPropietario;
    }

    public void setTelefonoPropietario(String telefono) {
        this.telefonoPropietario = telefono;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombre(String nombre) {
        this.nombrePropietario = nombre;
    }

    public String getDomicilioPropietario() {
        return domicilioPropietario;
    }

    public void setDomicilioPropietario(String domicilio) {
        this.domicilioPropietario = domicilio;
    }

    public String getFechaPropietario() {
        return fechaPropietario;
    }

    public void setFechaPropietario(String fecha) {
        this.fechaPropietario = fecha;
    }

}
