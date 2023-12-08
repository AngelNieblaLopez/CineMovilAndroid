package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magic.R;

public class PagoActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        // Obtener referencias a los elementos del diseño
        EditText editTextCardNumber = findViewById(R.id.editTextCardNumber);
        EditText editTextExpiryDate = findViewById(R.id.editTextExpiryDate);
        EditText editTextCardPin = findViewById(R.id.editTextCardPin);
        EditText editTextPropietario = findViewById(R.id.editTextPropietario); // Nuevo campo para el propietario
        Button btnPagar = findViewById(R.id.btnPagar);

        // Configurar un Listener para el botón de pago
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la información ingresada por el usuario
                String numeroTarjeta = editTextCardNumber.getText().toString();
                String fechaVencimiento = editTextExpiryDate.getText().toString();
                String nipTarjeta = editTextCardPin.getText().toString();
                String propietario = editTextPropietario.getText().toString(); // Obtener el propietario

                // Validar que todos los campos estén completos (agrega lógica de validación según tus necesidades)
                if (numeroTarjeta.isEmpty() || fechaVencimiento.isEmpty() || nipTarjeta.isEmpty() || propietario.isEmpty()) {
                    Toast.makeText(PagoActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Enviar la información a la lógica de pago (aquí puedes integrar tu pasarela de pago)
                realizarPago(numeroTarjeta, fechaVencimiento, nipTarjeta, propietario);
            }
        });
    }

    private void realizarPago(String numeroTarjeta, String fechaVencimiento, String nipTarjeta, String propietario) {
        // Implementa la lógica para procesar el pago aquí
        // Puedes integrar tu pasarela de pago y manejar la lógica correspondiente

        // Por ejemplo, mostrar un mensaje de éxito con los detalles del pago
        String mensajeExito = "Pago realizado con éxito\n" +
                "Número de tarjeta: " + numeroTarjeta + "\n" +
                "Fecha de vencimiento: " + fechaVencimiento + "\n" +
                "NIP de la tarjeta: " + nipTarjeta + "\n" +
                "Propietario: " + propietario;

        Toast.makeText(this, mensajeExito, Toast.LENGTH_SHORT).show();

        // Puedes realizar más acciones según el resultado del pago, como navegar a otra actividad, etc.
    }
}