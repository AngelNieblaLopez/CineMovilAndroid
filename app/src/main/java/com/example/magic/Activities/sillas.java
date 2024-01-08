package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magic.R;
import com.example.magic.globals;

public class sillas extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sillas);

        // Obtén el GridLayout
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Crea y agrega botones dinámicamente
        crearBotones(gridLayout);



        crearBotonDinamico(gridLayout, "Guardado", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para el clic en el botón "Guardado" dinámico
                if(globals.quantitySeats != globals.seatsIds.size()){
                    showMessage("No se seleccionaron todos los asientos requeridos");
                    return;
                }

                Toast.makeText(sillas.this, "Guardado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(sillas.this, PagoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void crearBotones(GridLayout gridLayout) {
        // Número de botones que deseas crear
        int numeroDeBotones = 24;
        String[] columnas = { "A", "B", "C", "D" };

        for (int i = 1; i <= numeroDeBotones; i++) {
            String texto = "";
            // Crea un nuevo botón
            if (i >= 1 && i <= 6) {
                texto = columnas[0] + "" + i;
            } else if (i >= 7 && i <= 12) {
                texto = columnas[1] + "" + i;
            } else if (i >= 13 && i <= 18) {
                texto = columnas[2] + "" + i;
            } else if (i >= 19 && i <= 24) {
                texto = columnas[3] + "" + i;
            }

            Button button = new Button(this);

            // Establece el texto del botón (puedes personalizarlo según tus necesidades)
            button.setText(texto);
            button.setTag(String.valueOf(i));

            // Configura la acción al hacer clic en el botón
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String seatId = (String) view.getTag();
                    globals.toggleSeat(seatId);
                    // Maneja la lógica cuando se hace clic en el botón
                    if (!button.isSelected()) {
                        // Si no está seleccionado, selecciónalo y marca el botón
                        button.setSelected(true);
                        button.setBackgroundColor(Color.parseColor("#ff0000")); // Cambia el color de fondo al seleccionar
                    } else {
                        // Si ya está seleccionado, desmárcalo
                        button.setSelected(false);
                        button.setBackgroundColor(Color.parseColor("#ffffff")); // Restaura el color de fondo original
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

        // Centra el botón dentro del GridLayout
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = GridLayout.LayoutParams.WRAP_CONTENT;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins(5, 5, 5, 5); // Márgenes para el contorno
        button.setLayoutParams(params);

        // Centra el botón horizontalmente
        button.setGravity(Gravity.CENTER_HORIZONTAL);

        // Agrega el botón al GridLayout
        gridLayout.addView(button);


    }

    public void showMessage(String message) {
        Toast.makeText(sillas.this, message, Toast.LENGTH_SHORT).show();
    }
}