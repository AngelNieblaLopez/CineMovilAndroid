package com.example.cinemovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity {

    Button butt_registes;
    EditText nombre, apellido, email, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre = findViewById(R.id.nombre);
        apellido =findViewById(R.id.apellido);
        email =findViewById(R.id.email);
        contrasena = findViewById(R.id.contrasena);
        butt_registes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    };
}