package com.example.magic.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.magic.Adapters.ActorsListAdapter;
import com.example.magic.Domain.FilmItem;
import com.example.magic.R;
import com.google.gson.Gson;

public class Detalles extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieSummaryInfo, movieActorsInfo;
    private int idFilm;
    private ImageView pic2, backImg;
    private RecyclerView.Adapter adapterActorList, adapterCategory;
    private RecyclerView recyclerViewActors, recyclerViewCategory;
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        idFilm=getIntent().getIntExtra("id", 0);
        initView();
        sendRequest();

        Button comprar = findViewById(R.id.comprar);
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para ir a Activity2
                Intent intent = new Intent(Detalles.this, sillas.class);
                startActivity(intent);
            }
        });

    }

    private void sendRequest() {
        mRequestQueue= Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest= new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson gson= new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItem item=gson.fromJson(response,FilmItem.class);

            Glide.with(Detalles.this)
                    .load(item.getPoster())
                    .into(pic2);

            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieSummaryInfo.setText(item.getPlot());
            movieActorsInfo.setText(item.getActors());
            if(item.getImages() != null){
                adapterActorList= new ActorsListAdapter(item.getImages());
                recyclerViewActors.setAdapter(adapterActorList);
            }

        }, error -> progressBar.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt=findViewById(R.id.movieNameTxt);
        progressBar=findViewById(R.id.progressBarDetail);
        scrollView=findViewById(R.id.scrollView5);
        pic2=findViewById(R.id.picDetail);
        movieRateTxt=findViewById(R.id.movieStar);
        movieTimeTxt=findViewById(R.id.movieTime);
        movieSummaryInfo=findViewById(R.id.movieSummery);
        movieActorsInfo=findViewById(R.id.movieActorInfo);
        backImg=findViewById(R.id.backImg);
        recyclerViewCategory=findViewById(R.id.genreView);
        recyclerViewActors=findViewById(R.id.imagesRecycler);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> finish());
    }
}