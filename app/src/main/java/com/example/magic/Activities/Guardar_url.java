package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.magic.R;

public class Guardar_url extends AppCompatActivity {

    private EditText editTextUrl;
    private Button btnGuardar;
    private TextView textViewUrlGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_url);

        editTextUrl = findViewById(R.id.editTextUrl);
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
        String url = editTextUrl.getText().toString();

        if (!url.isEmpty()) {
            // Aquí podrías guardar el URL en SharedPreferences, base de datos, o cualquier otro almacenamiento que prefieras.
            // En este ejemplo, simplemente lo mostraremos en el TextView.
            textViewUrlGuardado.setText("URL Guardado: " + url);
        } else {
            editTextUrl.setError("Ingrese un URL válido");
        }
    }
}