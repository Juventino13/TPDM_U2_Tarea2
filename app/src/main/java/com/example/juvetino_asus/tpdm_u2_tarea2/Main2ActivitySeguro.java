package com.example.juvetino_asus.tpdm_u2_tarea2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Main2ActivitySeguro extends AppCompatActivity {
    ListView lista_s;
    AseguradoraClass[] lista_seguros;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lista_s=findViewById(R.id.ListSeguros);
        regresar=findViewById(R.id.btnBack);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main2ActivitySeguro.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        lista_s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(Main2ActivitySeguro.this);
                alerta.setTitle("Alerta")
                        .setMessage("¿Desea modificar/eliminar el seguro seleccionado?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent datos=new Intent(Main2ActivitySeguro.this,Main6ActivityListaSeguro.class);
                                datos.putExtra("idseguro",lista_seguros[position].getIdSeguro());
                                datos.putExtra("descripcion",lista_seguros[position].getDescripcionSeguro());
                                datos.putExtra("fecha",lista_seguros[position].getFechaSeguro());
                                datos.putExtra("tipo",lista_seguros[position].getTipoSeguro());
                                datos.putExtra("telefono",lista_seguros[position].getTelefonoSeguro());
                                startActivity(datos);
                                finish();
                            }
                        })
                        .setNegativeButton("NO",null).show();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        AseguradoraClass seguro = new AseguradoraClass(this);
        AseguradoraClass vector[] = seguro.consultar();
        lista_seguros=vector;
        String[] descripcion = null;

        if(vector==null){
            descripcion = new String[1];
            descripcion[0]= "No hay seguros capturados";
            lista_s.setEnabled(false);
        }else {
            descripcion= new String[vector.length];
            for (int i=0; i<vector.length; i++){
                AseguradoraClass temporal = vector[i];
                descripcion[i] = temporal.getDescripcionSeguro();
            }
            lista_s.setEnabled(true);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,descripcion);
        lista_s.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertar_Seguro:
                Intent insertar = new Intent(this,Main4ActivityInsertSeguro.class);
                startActivity(insertar);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
