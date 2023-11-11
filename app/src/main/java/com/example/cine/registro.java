package com.example.cine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registro extends AppCompatActivity {

    Button butt_registes;
    EditText nombre, apellido, email, contrasena;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.nombre);
        apellido =findViewById(R.id.apellido);
        email =findViewById(R.id.email);
        contrasena = findViewById(R.id.contrasena);
        butt_registes =findViewById(R.id.butt_registes);

        butt_registes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeUser = nombre.getText().toString().trim();
                String apellUser =apellido.getText().toString().trim();
                String passUser = contrasena.getText().toString().trim();
                String emailUser = email.getText().toString().trim();


                if (nomeUser.isEmpty() && apellUser.isEmpty() && passUser.isEmpty() && emailUser.isEmpty()) {

                    Toast.makeText(registro.this, "complete los datos", Toast.LENGTH_SHORT).show();
                }else{

                    resgiterUser(nomeUser, apellUser,passUser,emailUser);

                    
                    
                    

                    




                }


            }
        });

    }

    private void resgiterUser(String nomeUser, String apellUser, String passUser, String emailUser) {
    }
}