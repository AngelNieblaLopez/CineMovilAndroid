package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.magic.R;

public class compra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        LinearLayout usuario= findViewById(R.id.usuario);
        usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(compra.this, usuario.class);
                startActivity(intent);
            }
        });
        LinearLayout favoritos= findViewById(R.id.favoritos);
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(compra.this, Principal.class);
                startActivity(intent);
            }
        });
        LinearLayout cines= findViewById(R.id.cines);
        cines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(compra.this, cines.class);
                startActivity(intent);
            }
        });

    }
    public class Purchase {
        private String movieName;
        private double moviePrice;
        private String movieTime;
        private int movieImage;

        public Purchase(String movieName, double moviePrice, String movieTime, int movieImage) {
            this.movieName = movieName;
            this.moviePrice = moviePrice;
            this.movieTime = movieTime;
            this.movieImage = movieImage;
        }

        public String getMovieName() {
            return movieName;
        }

        public double getMoviePrice() {
            return moviePrice;
        }

        public String getMovieTime() {
            return movieTime;
        }

        public int getMovieImage() {
            return movieImage;
        }
    }
}