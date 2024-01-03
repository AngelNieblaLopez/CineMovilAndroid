package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magic.R;
import com.example.magic.globals;
import com.example.magic.retrofit.ApiRetrofit;
import com.example.magic.retrofit.clients.ApiClientLogin;
import com.example.magic.retrofit.clients.ApiResponseClientLogin;
import com.example.magic.retrofit.clients.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
private EditText userEdt, passEdt;
private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userEdt=findViewById(R.id.etCorreo);
        passEdt=findViewById(R.id.etPass);
        loginBtn=findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()){
                Toast.makeText(login.this, "Ingrese su correo y contraseña", Toast.LENGTH_SHORT).show();
            }else {
                Client client = ApiRetrofit.getRetrofitInstance().create(Client.class);
                String email = userEdt.getText().toString();
                String pass = passEdt.getText().toString();
                Call<ApiResponseClientLogin> call = client.loginClient(pass, email);

                call.enqueue(new Callback<ApiResponseClientLogin>() {
                    @Override
                    public void onResponse(Call<ApiResponseClientLogin> call, Response<ApiResponseClientLogin> response) {
                        if (response.isSuccessful()){
                            ApiResponseClientLogin apiResponse = response.body();
                            if (apiResponse != null) {
                                ApiClientLogin client = apiResponse.client;
                                globals.clientId = client.id;

                                // Cambio de activity
                                startActivity(new Intent(login.this, Principal.class));
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
                    public void onFailure(Call<ApiResponseClientLogin> call, Throwable t) {
                        showMessage("Error en la conexión");
                    }
                });
            }

                /*
                if (userEdt.getText().toString().equals("test") && passEdt.getText().toString().equals("test")){
                startActivity(new Intent(login.this, Principal.class));
            }else{
                Toast.makeText(login.this, "Su correo o contraseña no es correcta", Toast.LENGTH_SHORT).show();
            }
            */

        });


        TextView contra_R= findViewById(R.id.contra_R);
        contra_R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(login.this, registroN.class));

            }
        });

        TextView url= findViewById(R.id.url);
        url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a Activity2
                Intent intent = new Intent(login.this, Guardar_url.class);
                startActivity(intent);
            }
        });
        TextView contra= findViewById(R.id.contra);
        contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a Activity2
                Intent intent = new Intent(login.this, contra_recuperar.class);
                startActivity(intent);
            }
        });


    }

    public void showMessage(String message) {
        Toast.makeText(login.this, message, Toast.LENGTH_SHORT).show();
    }

}