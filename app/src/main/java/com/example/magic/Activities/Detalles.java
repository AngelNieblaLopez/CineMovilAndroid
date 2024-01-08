package com.example.magic.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.magic.Domain.FilmItem;
import com.example.magic.R;
import com.example.magic.retrofit.ApiRetrofit;
import com.example.magic.retrofit.functions.ApiResponseScheduleFunctions;
import com.example.magic.retrofit.functions.ApiScheduleFunctions;
import com.example.magic.retrofit.functions.Function;
import com.example.magic.retrofit.movies.ApiMovieDetail;
import com.example.magic.retrofit.movies.ApiResponseMovieDetail;
import com.example.magic.retrofit.movies.Movie;
import com.example.magic.retrofit.movies.Movie;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Detalles extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieSummaryInfo;
    private int idFilm;
    private ImageView pic2, backImg;
    private RecyclerView.Adapter adapterActorList;
    private RecyclerView  recyclerViewCategory;
    private NestedScrollView scrollView;

    private Button btnShowHorarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        sendRequest(idFilm);
      //  sendRequest();
    }

    private void  make(){
        obtenerHorariosDinamicos(new HorariosCallback() {
            @Override
            public void onHorariosObtenidos(List<ApiScheduleFunctions> horarios) {
                showHorarios(horarios);
            }

            @Override
            public void onError(String mensajeError) {
                showMessage(mensajeError);
            }
        });
    }

    private void showHorarios(List<ApiScheduleFunctions> scheduleFunctions) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Horarios Disponibles")
                    .setItems(getHorariosArray(scheduleFunctions), (dialog, which) -> {
                        ApiScheduleFunctions selectedSchedule = scheduleFunctions.get(which);
                        Intent intent = new Intent(Detalles.this, boletos.class);
                        intent.putExtra("functionId", selectedSchedule.id);
                        startActivity(intent);
                    });

            builder.create().show();

    }

    private String[] getHorariosArray(List<ApiScheduleFunctions> scheduleFunctions) {
        String[] horariosArray = new String[scheduleFunctions.size()];

        for (int i = 0; i < scheduleFunctions.size(); i++) {
            horariosArray[i] = scheduleFunctions.get(i).startDate;
        }

        return horariosArray;
    }

    public interface HorariosCallback {
        void onHorariosObtenidos(List<ApiScheduleFunctions> horarios);
        void onError(String mensajeError);
    }

    private void obtenerHorariosDinamicos(final HorariosCallback callback) {
        Function function = ApiRetrofit.getRetrofitInstance().create(Function.class);

        Call<ApiResponseScheduleFunctions> call = function.availableByMovie(String.valueOf(idFilm));

        call.enqueue(new Callback<ApiResponseScheduleFunctions>() {
            @Override
            public void onResponse(Call<ApiResponseScheduleFunctions> call, Response<ApiResponseScheduleFunctions> response) {
                if (response.isSuccessful()) {
                    ApiResponseScheduleFunctions apiResponse = response.body();
                    if (apiResponse != null) {
                        List<ApiScheduleFunctions> horarios = apiResponse.functions;
                        callback.onHorariosObtenidos(horarios);
                    } else {
                        callback.onError("Error en la solicitud");
                    }
                } else {
                    if (response.code() == 404) {
                        callback.onError("Datos incorrectos");
                    } else {
                        callback.onError("Error en la solicitud");
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponseScheduleFunctions> call, Throwable t) {
                callback.onError("Error en la conexión");
            }
        });
    }

    private void sendRequest(int movieId) {

        progressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
        Movie movie = ApiRetrofit.getRetrofitInstance().create(Movie.class);
        Call<ApiResponseMovieDetail> call = movie.detail(movieId);

        call.enqueue(new Callback<ApiResponseMovieDetail>() {
            @Override
            public void onResponse(Call<ApiResponseMovieDetail> call, Response<ApiResponseMovieDetail> response) {
                if (response.isSuccessful()){
                    ApiResponseMovieDetail apiResponse = response.body();
                    if (apiResponse != null) {
                        ApiMovieDetail detail = apiResponse.movieDetail;


                        Glide.with(Detalles.this)
                                .load(detail.imageUrl)
                                .into(pic2);

                        progressBar.setVisibility(View.GONE);
                        scrollView.setVisibility(View.VISIBLE);

                        titleTxt.setText(detail.name);
                        movieRateTxt.setText("2");
                        movieTimeTxt.setText("2");
                        movieSummaryInfo.setText(detail.description);
                    }
                } else {
                        showMessage("Error en la solicitud");
                }
            }

            @Override
            public void onFailure(Call<ApiResponseMovieDetail> call, Throwable t) {
                showMessage("Error en la conexión");
                progressBar.setVisibility(View.GONE);
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

            //titleTxt.setText(item.getTitle());
            titleTxt.setText("SDF");
            movieRateTxt.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieSummaryInfo.setText(item.getPlot());


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
        backImg=findViewById(R.id.backImg);
        recyclerViewCategory=findViewById(R.id.genreView);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        btnShowHorarios = findViewById(R.id.btnShowHorarios);
        btnShowHorarios.setOnClickListener(v -> make());
        backImg.setOnClickListener(v -> finish());
    }

    public void showMessage(String message) {
        Toast.makeText(Detalles.this, message, Toast.LENGTH_SHORT).show();
    }
}