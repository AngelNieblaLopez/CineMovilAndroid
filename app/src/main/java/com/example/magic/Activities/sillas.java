package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magic.R;

public class sillas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sillas);

        // Obtén el GridLayout
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Crea y agrega botones dinámicamente
        crearBotones(gridLayout);

        // Configura los clics en los botones "Guardar" y "Next"
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnNext = findViewById(R.id.btnNext);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para guardar
                Toast.makeText(sillas.this, "Guardar", Toast.LENGTH_SHORT).show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para ir al siguiente paso
                Toast.makeText(sillas.this, "Next", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(sillas.this, boletos.class);
                startActivity(intent);
            }
        });

        // Crea y agrega botones dinámicos "Next" y "Guardado"
        crearBotonDinamico(gridLayout, "Next", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para el clic en el botón "Next" dinámico
                Toast.makeText(sillas.this, "Next", Toast.LENGTH_SHORT).show();
            }
        });

        crearBotonDinamico(gridLayout, "Guardado", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para el clic en el botón "Guardado" dinámico
                Toast.makeText(sillas.this, "Guardado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(sillas.this, boletos.class);
                startActivity(intent);
            }
        });
    }

    private void crearBotones(GridLayout gridLayout) {
        // Número de botones que deseas crear
        int numeroDeBotones = 40;

        for (int i = 1; i <= numeroDeBotones; i++) {
            // Crea un nuevo botón
            Button button = new Button(this);

            // Establece el texto del botón (puedes personalizarlo según tus necesidades)
            button.setText("Botón " + i);

            // Cambia el nombre del botón
            String nuevoNombre = "" + i;
            button.setText(nuevoNombre);

            // Establece un identificador único para el botón
            button.setId(View.generateViewId());

            // Configura la acción al hacer clic en el botón
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Maneja la lógica cuando se hace clic en el botón
                    if (!button.isSelected()) {
                        // Si no está seleccionado, selecciónalo y marca el botón
                        button.setSelected(true);
                        button.setBackgroundColor(Color.parseColor("#ff0000")); // Cambia el color de fondo al seleccionar
                        Toast.makeText(sillas.this, "Botón seleccionado: " + button.getText(), Toast.LENGTH_SHORT).show();
                    } else {
                        // Si ya está seleccionado, desmárcalo
                        button.setSelected(false);
                        button.setBackgroundColor(Color.parseColor("#ffffff")); // Restaura el color de fondo original
                        Toast.makeText(sillas.this, "Botón deseleccionado: " + button.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            // Cambia el color de fondo del botón (puedes personalizar el color según tus necesidades)
            button.setBackgroundColor(Color.parseColor("#ffffff"));

            // Añade un contorno alrededor del botón (directamente en los parámetros de diseño)
            int anchoBotonEnPixeles = (int) getResources().getDimension(R.dimen.ancho_boton);
            int altoBotonEnPixeles = (int) getResources().getDimension(R.dimen.alto_boton);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(5, 5, 5, 5); // Márgenes para el contorno
            button.setLayoutParams(params);

            // Agrega el botón al GridLayout
            gridLayout.addView(button);
        }
    }

    private void crearBotonDinamico(GridLayout gridLayout, String texto, View.OnClickListener onClickListener) {
        Button button = new Button(this);
        button.setText(texto);
        button.setOnClickListener(onClickListener);

        // Resto del código para diseño y añadir al GridLayout
        // ...
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = GridLayout.LayoutParams.WRAP_CONTENT;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins(5, 5, 5, 5); // Márgenes para el contorno
        button.setLayoutParams(params);

        // Agrega el botón al GridLayout
        gridLayout.addView(button);


    }
}