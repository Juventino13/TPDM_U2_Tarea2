package com.example.juvetino_asus.tpdm_u2_tarea2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main7ActivityListaPropietario extends AppCompatActivity {
    EditText telefonoPropietario, nombrePropietario, domicilioPropietario, fechaPropietario, descripcionSeguro, fechaSeguro, tipoSeguro;
    Button actualizar, eliminar, regresar;
    PropietarioClass propietario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        telefonoPropietario=findViewById(R.id.txt__TelefonoPropietario);
        telefonoPropietario.setEnabled(false);
        nombrePropietario=findViewById(R.id.txt__NombrePropietario);
        domicilioPropietario=findViewById(R.id.txt__DomicilioPropietario);
        fechaPropietario=findViewById(R.id.txt__FechaPropietario);
        descripcionSeguro=findViewById(R.id.editDes);
        fechaSeguro=findViewById(R.id.editFecha);
        tipoSeguro=findViewById(R.id.editTipo);
        actualizar=findViewById(R.id.btnActualizarPropietario);
        eliminar=findViewById(R.id.btnEliminarPro);
        regresar=findViewById(R.id.btnRegresarSeg);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropietarioClass p=new PropietarioClass(Main7ActivityListaPropietario.this);
                if (p.actualizar(new PropietarioClass(telefonoPropietario.getText().toString(), nombrePropietario.getText().toString(), domicilioPropietario.getText().toString(), fechaPropietario.getText().toString()))){
                    Toast.makeText(Main7ActivityListaPropietario.this,"Propietario actualizado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main7ActivityListaPropietario.this,Main3ActivityPropietario.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main7ActivityListaPropietario.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropietarioClass prop=new PropietarioClass(Main7ActivityListaPropietario.this);
                if (prop.eliminar(propietario)){
                    Toast.makeText(Main7ActivityListaPropietario.this,"Propietario eliminado exitosamente",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Main7ActivityListaPropietario.this,Main3ActivityPropietario.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Main7ActivityListaPropietario.this, "Ocurrio un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main7ActivityListaPropietario.this,Main3ActivityPropietario.class);
                startActivity(i);
                finish();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        Bundle extras=getIntent().getExtras();
        if (extras != null) {
            propietario=new PropietarioClass(extras.getString("telefono"),extras.getString("nombre"),extras.getString("domicilio"),extras.getString("fecha"));
            telefonoPropietario.setText(propietario.getTelefonoPropietario());
            nombrePropietario.setText(propietario.getNombrePropietario());
            domicilioPropietario.setText(propietario.getDomicilioPropietario());
            fechaPropietario.setText(propietario.getFechaPropietario());
        }//if
    }//onStart

}
