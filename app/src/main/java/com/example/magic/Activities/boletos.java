package com.example.magic.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magic.R;

public class boletos extends AppCompatActivity {

    private Spinner spinnerCantidadBoletos;
    private Button btnComprar;
    private TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletos);

        spinnerCantidadBoletos = findViewById(R.id.spinnerCantidadBoletos);
        btnComprar = findViewById(R.id.btnComprar);
        textViewResultado = findViewById(R.id.textViewResultado);

        // Llena el spinner con opciones de cantidad de boletos
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.opciones_cantidad_boletos, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCantidadBoletos.setAdapter(adapter);

        // Configura el evento de clic del botón Comprar
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarCompra();
            }
        });
    }

    private void realizarCompra() {
        // Obtiene la cantidad seleccionada del spinner
        int cantidadBoletos = Integer.parseInt(spinnerCantidadBoletos.getSelectedItem().toString());

        // Aquí podrías implementar la lógica de compra de boletos, como procesar el pago, etc.

        // Muestra el resultado de la compra en el TextView
        textViewResultado.setText("Compra realizada para " + cantidadBoletos + " boletos");
    }
}