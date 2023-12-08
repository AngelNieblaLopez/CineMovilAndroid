package com.example.magic.Activities;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.magic.R;

import java.io.FileOutputStream;
import java.io.IOException;

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
        String url = editTextUrl.getText().toString().trim();

        if (TextUtils.isEmpty(url)) {
            Toast.makeText(this, "Ingresa una URL válida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar la URL en un archivo de texto
        guardarEnArchivo(url);

        // Mostrar la URL en el TextView
        textViewUrlGuardado.setText("URL Guardado: " + url);

        // Limpiar el EditText
        editTextUrl.getText().clear();
    }

    private void guardarEnArchivo(String url) {
        String nombreArchivo = "urls_guardados.txt";

        try (FileOutputStream fos = openFileOutput(nombreArchivo, Context.MODE_APPEND)) {
            String textoAGuardar = url + "\n";
            fos.write(textoAGuardar.getBytes());
            Toast.makeText(this, "URL guardado en el archivo", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar la URL", Toast.LENGTH_SHORT).show();
        }
    }
}