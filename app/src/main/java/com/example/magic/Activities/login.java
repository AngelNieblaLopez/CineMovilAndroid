package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.magic.R;

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
        userEdt=findViewById(R.id.editTextText);
        passEdt=findViewById(R.id.editTextPassword);
        loginBtn=findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()){
                Toast.makeText(login.this, "Ingrese su correo y contraseña", Toast.LENGTH_SHORT).show();
            }else if (userEdt.getText().toString().equals("test") && passEdt.getText().toString().equals("test")){
                startActivity(new Intent(login.this, Principal.class));
            }else{
                Toast.makeText(login.this, "Su correo o contraseña no es correcta", Toast.LENGTH_SHORT).show();
            }
        });
    }
}