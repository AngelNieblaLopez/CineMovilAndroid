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
import com.example.magic.globals;
import com.example.magic.retrofit.ApiRetrofit;
import com.example.magic.retrofit.sales.ApiResponseSaleRegister;
import com.example.magic.retrofit.sales.ApiSendSaleRegister;
import com.example.magic.retrofit.sales.Sale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Sale sale = ApiRetrofit.getRetrofitInstance().create(Sale.class);
        ApiSendSaleRegister newSale = new ApiSendSaleRegister(globals.functionId, String.valueOf(globals.clientId), propietario, numeroTarjeta,  nipTarjeta, fechaVencimiento, globals.seatsIds);
        Call<ApiResponseSaleRegister> call = sale.registerSale(newSale);

        call.enqueue(new Callback<ApiResponseSaleRegister>() {
            @Override
            public void onResponse(Call<ApiResponseSaleRegister> call, Response<ApiResponseSaleRegister> response) {
                if (response.isSuccessful()){
                    ApiResponseSaleRegister apiResponse = response.body();
                    if (apiResponse != null) {

                        String mensajeExito = "Pago realizado con éxito\n" +
                                "Número de tarjeta: " + numeroTarjeta + "\n" +
                                "Fecha de vencimiento: " + fechaVencimiento + "\n" +
                                "NIP de la tarjeta: " + nipTarjeta + "\n" +
                                "Propietario: " + propietario;

                        showMessage(mensajeExito);
                        startActivity(new Intent(PagoActivity.this, Principal.class));
                    } else {
                        showMessage("Error en la solicitud");

                    }
                } else {
                    if(response.code() == 404) {
                        showMessage("Datos incorrectos");

                    } else {
                        showMessage("Error en la solicitud");
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponseSaleRegister> call, Throwable t) {
                showMessage("Error en la conexión");
            }
        });





    }

    public void showMessage(String message) {
        Toast.makeText(PagoActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}