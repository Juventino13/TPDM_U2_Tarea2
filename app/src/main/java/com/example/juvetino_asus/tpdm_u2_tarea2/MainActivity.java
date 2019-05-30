package com.example.juvetino_asus.tpdm_u2_tarea2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView persona, seguro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persona=findViewById(R.id.imgpersona);
        seguro=findViewById(R.id.imgsalud);

        persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main3ActivityPropietario.class);
                startActivity(i);
                finish();
            }
        });

        seguro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2ActivitySeguro.class);
                startActivity(i);
                finish();
            }
        });
    }
}
