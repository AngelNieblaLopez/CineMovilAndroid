package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.magic.R;

public class cines extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cines);

        LinearLayout Compras= findViewById(R.id.compras);
        Compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(cines.this, compra.class);
                startActivity(intent);
            }
        });
        LinearLayout favoritos= findViewById(R.id.favoritos);
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(cines.this, Principal.class);
                startActivity(intent);
            }
        });
        LinearLayout cines= findViewById(R.id.cines);
        cines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(cines.this, cines.class);
                startActivity(intent);
            }
        });

    }
}