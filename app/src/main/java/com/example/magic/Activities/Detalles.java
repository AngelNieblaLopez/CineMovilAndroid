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
import com.example.magic.Adapters.ActorsListAdapter;
import com.example.magic.Domain.FilmItem;
import com.example.magic.R;
import com.example.magic.globals;
import com.example.magic.retrofit.ApiRetrofit;
import com.example.magic.retrofit.clients.ApiClientLogin;
import com.example.magic.retrofit.clients.ApiResponseClientLogin;
import com.example.magic.retrofit.clients.Client;
import com.example.magic.retrofit.functions.ApiResponseScheduleFunctions;
import com.example.magic.retrofit.functions.ApiScheduleFunctions;
import com.example.magic.retrofit.functions.Function;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    private Button btnShowHorarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        sendRequest();

        // Move this line here
        btnShowHorarios = findViewById(R.id.btnShowHorarios);
        btnShowHorarios.setOnClickListener(v -> make());
    }

    private void  make(){
        obtenerHorariosDinamicos(new HorariosCallback() {
            @Override
            public void onHorariosObtenidos(List<ApiScheduleFunctions> horarios) {
                // Hacer algo con los horarios obtenidos
                // Por ejemplo, mostrarlos en un diálogo
                showHorarios(horarios);
            }

            @Override
            public void onError(String mensajeError) {
                // Manejar el error
                showMessage(mensajeError);
            }
        });
    }

    private void showHorarios(List<ApiScheduleFunctions> scheduleFunctions) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Horarios Disponibles")
                    .setItems(getHorariosArray(scheduleFunctions), (dialog, which) -> {
                        // Lógica para manejar la selección del horario si es necesario
                        ApiScheduleFunctions selectedSchedule = scheduleFunctions.get(which);
                        String selectedHorario = selectedSchedule.startDate;

                        // Aquí puedes usar un Intent para llevar al usuario a otra actividad
                        // Puedes reemplazar NuevaActividad.class con el nombre de tu nueva actividad
                        Intent intent = new Intent(Detalles.this, boletos.class);

                        // Puedes pasar datos adicionales a la nueva actividad si es necesario
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

    public void showMessage(String message) {
        Toast.makeText(Detalles.this, message, Toast.LENGTH_SHORT).show();
    }
}