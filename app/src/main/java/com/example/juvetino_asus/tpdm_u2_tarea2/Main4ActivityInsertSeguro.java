package com.example.juvetino_asus.tpdm_u2_tarea2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main4ActivityInsertSeguro extends AppCompatActivity {
    EditText descripcion, fecha, tipo;
    Button insertar;
    Spinner telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        descripcion=findViewById(R.id.editDescripcion);
        fecha=findViewById(R.id.edtFecha);
        tipo=findViewById(R.id.edtTipoSeguro);
        telefono=findViewById(R.id.comboPropietario);
        insertar=findViewById(R.id.btnGuardar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AseguradoraClass seguro=new AseguradoraClass(Main4ActivityInsertSeguro.this);
                if (seguro.insertar(new AseguradoraClass(1,descripcion.getText().toString(),fecha.getText().toString(),tipo.getText().toString(),telefono.getSelectedItem().toString().split("-")[0]))){
                    Toast.makeText(Main4ActivityInsertSeguro.this,"Seguro insertado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main4ActivityInsertSeguro.this,Main2ActivitySeguro.class);
                    startActivity(i);
                    finish();
                }//if
            }//onClick
        });
    }//onCreate

    protected void onStart(){
        super.onStart();
        PropietarioClass propietario=new PropietarioClass(Main4ActivityInsertSeguro.this);
        PropietarioClass propietarios[]=propietario.consultar();
        if (propietarios!=null){
            String d[]=new String[propietarios.length];
            for (int i=0;i<d.length;i++){
                d[i]=propietarios[i].getTelefonoPropietario()+" - "+propietarios[i].getNombrePropietario();
            }//for
            ArrayAdapter<String> adaptador=new ArrayAdapter<String>(Main4ActivityInsertSeguro.this,android.R.layout.simple_list_item_1,d);
            telefono.setAdapter(adaptador);
        }//if
        else{
            Toast.makeText(this, "No existen propietarios registrados, favor de registrarlos", Toast.LENGTH_LONG).show();
            Intent i=new Intent(Main4ActivityInsertSeguro.this,MainActivity.class);
            startActivity(i);
            finish();
        }//else
    }//onStart
}//c