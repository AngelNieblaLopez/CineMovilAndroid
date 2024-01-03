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
import com.example.magic.globals;
import com.example.magic.retrofit.ApiRetrofit;
import com.example.magic.retrofit.clients.ApiResponseClientRegister;
import com.example.magic.retrofit.clients.ApiSendClientRegister;
import com.example.magic.retrofit.clients.Client;
import com.example.magic.retrofit.sales.ApiResponseSaleRegister;
import com.example.magic.retrofit.sales.ApiSendSaleRegister;
import com.example.magic.retrofit.sales.Sale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class boletos extends AppCompatActivity {

    private EditText editTextCantidadBoletos;
    private Button btnComprar;
    private TextView textViewResultado;
    private String functionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boletos);

        functionId = getIntent().getStringExtra("functionId");
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

        Button btnsillas= findViewById(R.id.btnsillas);
        btnsillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a Activity2
                String cantidadBoletosStr = editTextCantidadBoletos.getText().toString();

                if (!TextUtils.isEmpty(cantidadBoletosStr)) {
                    int cantidadBoletos = Integer.parseInt(cantidadBoletosStr);

                    if(cantidadBoletos <= 0) {
                        textViewResultado.setText("Se debe de ingresar un valor mayor a 0");
                        return;
                    }
                    globals.functionId = functionId;
                    globals.quantitySeats = cantidadBoletos;
                    Intent intent = new Intent(boletos.this, sillas.class);
                    startActivity(intent);

                } else {
                    showMessage("Ingrese la cantidad de boletos");
                }



            }
        });

    }

    private void realizarCompra() {
        // Obtiene la cantidad ingresada por el usuario

    }

    public void showMessage(String message) {
        Toast.makeText(boletos.this, message, Toast.LENGTH_SHORT).show();
    }
}