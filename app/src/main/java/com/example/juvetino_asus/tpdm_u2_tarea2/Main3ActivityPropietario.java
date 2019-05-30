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

public class Main3ActivityPropietario extends AppCompatActivity {
    ListView lista_p;
    PropietarioClass[] lista_propietarios;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lista_p=findViewById(R.id.ListPropietario);
        regresar=findViewById(R.id.btnRegresarPropietario);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main3ActivityPropietario.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        lista_p.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(Main3ActivityPropietario.this);
                alerta.setTitle("Alerta")
                        .setMessage("¿Desea modificar/eliminar el propietario seleccionado?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent datos=new Intent(Main3ActivityPropietario.this,Main7ActivityListaPropietario.class);
                                datos.putExtra("nombre",lista_propietarios[position].getNombrePropietario());
                                datos.putExtra("telefono",lista_propietarios[position].getTelefonoPropietario());
                                datos.putExtra("domicilio",lista_propietarios[position].getDomicilioPropietario());
                                datos.putExtra("fecha",lista_propietarios[position].getFechaPropietario());
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
        PropietarioClass propietario = new PropietarioClass(this);
        PropietarioClass vector[] = propietario.consultar();
        lista_propietarios=vector;
        String[] nombre = null;

        if(vector==null){
            nombre = new String[1];
            nombre[0]= "No hay propietarios capturados";
            lista_p.setEnabled(false);
        }else {
            nombre= new String[vector.length];
            for (int i=0; i<vector.length; i++){
                PropietarioClass temporal = vector[i];
                nombre[i] = temporal.getTelefonoPropietario();
            }
            lista_p.setEnabled(true);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombre);
        lista_p.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principalpro,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertar:
                Intent insertar = new Intent(this,Main5ActivityInsertPropietario.class);
                startActivity(insertar);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}