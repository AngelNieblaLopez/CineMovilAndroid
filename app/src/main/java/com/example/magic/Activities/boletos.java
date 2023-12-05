package com.example.magic.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.magic.R;

public class boletos extends AppCompatActivity {

    private EditText editTextCantidadBoletos;
    private Button btnComprar;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletos);

        editTextCantidadBoletos = findViewById(R.id.editTextCantidadBoletos);
        btnComprar = findViewById(R.id.btnComprar);
        textViewResultado = findViewById(R.id.textViewResultado);

        // Configura el evento de clic del botón Comprar
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarCompra();
            }
        });

        Button btnComprar= findViewById(R.id.btnComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a Activity2
                Intent intent = new Intent(boletos.this, PagoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void realizarCompra() {
        // Obtiene la cantidad ingresada por el usuario
        String cantidadBoletosStr = editTextCantidadBoletos.getText().toString();

        if (!TextUtils.isEmpty(cantidadBoletosStr)) {
            int cantidadBoletos = Integer.parseInt(cantidadBoletosStr);

            // Aquí podrías implementar la lógica de compra de boletos, como procesar el pago, etc.

            // Muestra el resultado de la compra en el TextView
            textViewResultado.setText("Compra realizada para " + cantidadBoletos + " boletos");
        } else {
            // Manejar el caso en que el campo esté vacío
            Toast.makeText(this, "Ingrese la cantidad de boletos", Toast.LENGTH_SHORT).show();
        }
    }
}