package com.example.magic.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.magic.R;
import com.example.magic.globals;
import com.example.magic.retrofit.ApiRetrofit;
import com.example.magic.retrofit.clients.ApiClientLogin;
import com.example.magic.retrofit.clients.ApiResponseClientLogin;
import com.example.magic.retrofit.clients.ApiResponseClientRegister;
import com.example.magic.retrofit.clients.ApiSendClientRegister;
import com.example.magic.retrofit.clients.Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registroN extends AppCompatActivity{

    EditText name, lastName, secondLastName, email, password;
    Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_n);

        name = findViewById(R.id.etNombre);
        lastName = findViewById(R.id.etLastName);
        secondLastName = findViewById(R.id.etSecondLastName);
        name = findViewById(R.id.etNombre);
        email = findViewById(R.id.etCorreo);
        password = findViewById(R.id.etPass);
        insertar = findViewById(R.id.registroBtn);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarDatos();
            }
        });

        TextView contra_RR= findViewById(R.id.contra_RR);
        contra_RR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registroN.this, login.class));
            }
        });

    }

    private void insertarDatos() {
        final String Nombre = name.getText().toString().trim();
        final String Correo = email.getText().toString().trim();
        final String Pass = password.getText().toString().trim();
        final String primerApellido = lastName.getText().toString().trim();
        final String segundoApellido = secondLastName.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando");

        if(Nombre.isEmpty()){
            name.setError("Ingrese un nombre");
            return;
        }else if (Correo.isEmpty()){
            email.setError("Ingrese un correo");
            return;
        } else if (Pass.isEmpty()) {
            password.setError("Ingrese una contraseña");
        } else if(primerApellido.isEmpty()){
            lastName.setError("Ingrese el primer apellido");
        } else if(segundoApellido.isEmpty()) {
            secondLastName.setError("Ingrese el primer apellido");
        } else {
            progressDialog.show();
            Client client = ApiRetrofit.getRetrofitInstance().create(Client.class);
            ApiSendClientRegister newCient = new ApiSendClientRegister(Pass, Nombre, primerApellido, segundoApellido, Correo);
            Call<ApiResponseClientRegister> call = client.registerUser(newCient);

            call.enqueue(new Callback<ApiResponseClientRegister>() {
                @Override
                public void onResponse(Call<ApiResponseClientRegister> call, Response<ApiResponseClientRegister> response) {
                    if (response.isSuccessful()){
                        ApiResponseClientRegister apiResponse = response.body();
                        if (apiResponse != null) {
                            startActivity(new Intent(registroN.this, login.class));
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
                public void onFailure(Call<ApiResponseClientRegister> call, Throwable t) {
                    showMessage("Error en la conexión");
                }
            });

            progressDialog.dismiss();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void showMessage(String message) {
        Toast.makeText(registroN.this, message, Toast.LENGTH_SHORT).show();
    }
}
