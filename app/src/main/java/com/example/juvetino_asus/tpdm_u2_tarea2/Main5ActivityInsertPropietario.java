package com.example.juvetino_asus.tpdm_u2_tarea2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main5ActivityInsertPropietario extends AppCompatActivity {
    EditText telefono, nombre, domicilio, fecha;
    Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        telefono=findViewById(R.id.txt_TelefonoPropietario);
        nombre=findViewById(R.id.txt_NombrePropietario);
        domicilio=findViewById(R.id.txt_DomicilioPropietario);
        fecha=findViewById(R.id.txt_FechaPropietario);
        insertar=findViewById(R.id.btn_InsetarPropietario);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropietarioClass propietario = new PropietarioClass(Main5ActivityInsertPropietario.this);
                if (propietario.insertar(new PropietarioClass(telefono.getText().toString(),nombre.getText().toString(), domicilio.getText().toString(), fecha.getText().toString()))){
                    Toast.makeText(Main5ActivityInsertPropietario.this, "Se inserto a "+nombre.getText().toString()+" exitosamente", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Main5ActivityInsertPropietario.this, Main3ActivityPropietario.class);
                    startActivity(i);
                    finish();
                }//if
                else {
                    Toast.makeText(Main5ActivityInsertPropietario.this, "No se pudo insertar a "+nombre.getText().toString(), Toast.LENGTH_LONG).show();
                }//else
            }//onClick
        });
    }//onCreate
}//class
