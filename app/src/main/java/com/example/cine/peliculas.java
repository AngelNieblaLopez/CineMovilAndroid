package com.example.cine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class peliculas extends AppCompatActivity {
Button pel_butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas);

        pel_butt = findViewById(R.id.pel_butt);

        pel_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Este código se ejecutará al hacer clic en el botón
                onBackPressed(); // Usa onBackPressed para volver a la actividad anterior en la pila
            }
        });

    }
}