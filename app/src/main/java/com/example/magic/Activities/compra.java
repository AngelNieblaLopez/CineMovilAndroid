package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.magic.Adapters.CardAdapter;
import com.example.magic.Domain.CardData;
import com.example.magic.R;
import com.example.magic.globals;
import com.example.magic.retrofit.ApiRetrofit;
import com.example.magic.retrofit.clients.ApiClientLogin;
import com.example.magic.retrofit.clients.ApiResponseClientLogin;
import com.example.magic.retrofit.clients.Client;
import com.example.magic.retrofit.sales.ApiResponseSaleClientBuyed;
import com.example.magic.retrofit.sales.ApiSaleClientDetail;
import com.example.magic.retrofit.sales.ApiSaleClientDetailFunction;
import com.example.magic.retrofit.sales.Sale;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class compra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        // acciones abajo
        LinearLayout Compras= findViewById(R.id.compras);
        Compras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(compra.this, compra.class);
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

        // acciones abajo

        getTickets();
    }

    public void getTickets() {
        Sale sale = ApiRetrofit.getRetrofitInstance().create(Sale.class);

        Call<ApiResponseSaleClientBuyed> call = sale.buyedClient(globals.clientId);

        call.enqueue(new Callback<ApiResponseSaleClientBuyed>() {
            @Override
            public void onResponse(Call<ApiResponseSaleClientBuyed> call, Response<ApiResponseSaleClientBuyed> response) {
                if (response.isSuccessful()){
                    ApiResponseSaleClientBuyed apiResponse = response.body();
                    if (apiResponse != null) {

                        List<ApiSaleClientDetail> apiSaleClientDetail = apiResponse.saleClientDetail;
                        generateCards(apiSaleClientDetail);

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
            public void onFailure(Call<ApiResponseSaleClientBuyed> call, Throwable t) {
                showMessage("Error en la conexión");
            }
        });
    }

    private void  generateCards(List<ApiSaleClientDetail> ApiSaleClientDetail){

        RecyclerView recyclerView = findViewById(R.id.sale_cards_recyclerView);

// Configurar el RecyclerView con un LinearLayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

// Crear la lista de datos de tarjetas
        List<CardData> cardDataList = new ArrayList<>();

// Agregar datos de ejemplo
        for (int i = 0; i < ApiSaleClientDetail.size(); i++) {
            ApiSaleClientDetail apiSaleClientDetailFunction = ApiSaleClientDetail.get(i);

            CardData cardData = new CardData(apiSaleClientDetailFunction.apiSaleClientDetailFunction.name,apiSaleClientDetailFunction.apiSaleClientDetailFunction.roomName,apiSaleClientDetailFunction.apiSaleClientDetailFunction.functionStartDate, apiSaleClientDetailFunction.seats_names);
            cardDataList.add(cardData);
        }

// Crear y configurar el adaptador
        CardAdapter cardAdapter = new CardAdapter(cardDataList);
        recyclerView.setAdapter(cardAdapter);
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

    public void showMessage(String message) {
        Toast.makeText(compra.this, message, Toast.LENGTH_SHORT).show();
    }
}