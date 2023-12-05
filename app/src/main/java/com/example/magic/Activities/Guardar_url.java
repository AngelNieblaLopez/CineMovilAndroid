package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.magic.R;

public class Guardar_url extends AppCompatActivity {

    // Define tu URL estático aquí
    private static final String URL_ESTATICO = "https://www.ejemplo.com";

    private Button btnGuardar;
    private TextView textViewUrlGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_url);

        btnGuardar = findViewById(R.id.btnGuardar);
        textViewUrlGuardado = findViewById(R.id.textViewUrlGuardado);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarURL();
            }
        });
    }

    private void guardarURL() {
        // Utiliza el URL estático en lugar de obtenerlo desde el EditText
        String url = URL_ESTATICO;

        // Puedes omitir la verificación de si el URL está vacío en este caso

        // Muestra el URL en el TextView
        textViewUrlGuardado.setText("URL Guardado: " + url);
    }
}