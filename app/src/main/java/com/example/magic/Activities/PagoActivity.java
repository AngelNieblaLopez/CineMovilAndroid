package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.magic.R;

public class PagoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crear un LinearLayout como diseño principal
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        // Crear un botón de pago dinámicamente
        Button btnPagar = new Button(this);
        btnPagar.setText("Pagar");
        btnPagar.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        // Agregar un Listener al botón para manejar el evento de clic
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para manejar el evento de clic (puedes implementar la lógica de pago aquí)
                realizarPago();
            }
        });

        // Agregar el botón al LinearLayout
        linearLayout.addView(btnPagar);

        // Establecer el LinearLayout como el diseño principal de la actividad
        setContentView(linearLayout);

    }

    private void realizarPago() {
        // Aquí puedes implementar la lógica para procesar el pago
        // (por ejemplo, iniciar un proceso de pago, mostrar una pasarela de pago, etc.)

        // En este ejemplo, mostraremos un mensaje de éxito
        Toast.makeText(this, "Pago realizado con éxito", Toast.LENGTH_SHORT).show();
    }



}