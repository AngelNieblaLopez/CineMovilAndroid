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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.magic.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registroN extends AppCompatActivity{

    EditText nombre, correo, pass;
    Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_n);

        nombre = findViewById(R.id.etNombre);
        correo = findViewById(R.id.etCorreo);
        pass = findViewById(R.id.etPass);
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
        final String Nombre = nombre.getText().toString().trim();
        final String Correo = correo.getText().toString().trim();
        final String Pass = pass.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando");

        if(nombre.getText().toString().isEmpty()){
            nombre.setError("Ingrese un nombre");
            return;
        }else if (correo.getText().toString().isEmpty()){
            correo.setError("Ingrese un correo");
            return;
        }
        else {
            progressDialog.show();
            StringRequest request =new StringRequest(Request.Method.POST, "http://localhost/magic/insertar.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Registro correctamente")) {
                        Toast.makeText(registroN.this, "Datos insertados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(registroN.this, Principal.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(registroN.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(registroN.this, "No se pudo insertar", Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(registroN.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params=new HashMap<>();
                    params.put("nombre", String.valueOf(nombre));
                    params.put("correo", String.valueOf(correo));
                    params.put("pass", String.valueOf(pass));
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(registroN.this);
            requestQueue.add(request);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
