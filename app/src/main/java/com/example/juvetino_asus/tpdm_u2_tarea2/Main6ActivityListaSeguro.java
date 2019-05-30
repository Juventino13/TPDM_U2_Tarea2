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

public class Main6ActivityListaSeguro extends AppCompatActivity {

    Spinner telefono;
    AseguradoraClass seguro;
    EditText descripcion, fecha, tipo;
    Button actualizar, eliminar, regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        descripcion=findViewById(R.id.editDes);
        fecha=findViewById(R.id.editFecha);
        tipo=findViewById(R.id.editTipo);
        telefono=findViewById(R.id.comboPropietario);
        actualizar=findViewById(R.id.btnActSeguro);
        eliminar=findViewById(R.id.btnEliminarSeguro);
        regresar=findViewById(R.id.btnRegresarPropietario);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AseguradoraClass seg=new AseguradoraClass (Main6ActivityListaSeguro.this);
                if (seg.actualizar(new AseguradoraClass(seguro.getIdSeguro(),descripcion.getText().toString(),fecha.getText().toString(),tipo.getText().toString(),telefono.getSelectedItem().toString().split("-")[0]))){
                    Toast.makeText(Main6ActivityListaSeguro.this,"Seguro actualizado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main6ActivityListaSeguro.this,Main2ActivitySeguro.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main6ActivityListaSeguro.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AseguradoraClass s=new AseguradoraClass(Main6ActivityListaSeguro.this);
                if (s.eliminar(seguro)){
                    Toast.makeText(Main6ActivityListaSeguro.this,"Eliminado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main6ActivityListaSeguro.this,Main2ActivitySeguro.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main6ActivityListaSeguro.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main6ActivityListaSeguro.this,Main2ActivitySeguro.class);
                startActivity(i);
                finish();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        Bundle extras=getIntent().getExtras();
        String tel=extras.getString("telefono");
        seguro=new AseguradoraClass(extras.getInt("idseguro"),extras.getString("descripcion"),extras.getString("fecha"),extras.getString("tipo"),tel);
        descripcion.setText(seguro.getDescripcionSeguro());
        tipo.setText(seguro.getTipoSeguro());
        fecha.setText(seguro.getFechaSeguro());

        PropietarioClass propietario=new PropietarioClass(Main6ActivityListaSeguro.this);
        PropietarioClass propietarios[]=propietario.consultar();
        if (propietarios!=null){
            String d[]=new String[propietarios.length];
            int posicion=0;
            for (int i=0;i<d.length;i++){
                String dato=propietarios[i].getTelefonoPropietario();
                if (dato.equals(tel)){posicion=i;}
                d[i]=dato+" - "+propietarios[i].getNombrePropietario();
            }//for
            ArrayAdapter<String> adaptador=new ArrayAdapter<String>(Main6ActivityListaSeguro.this,android.R.layout.simple_list_item_1,d);
            telefono.setAdapter(adaptador);
            telefono.setSelection(posicion);
        }
    }
}//class
