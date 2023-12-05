package com.example.magic.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.magic.Adapters.FilmListAdapter;
import com.example.magic.Adapters.SliderAdapters;
import com.example.magic.Domain.ListFilm;
import com.example.magic.Domain.SliderItems;
import com.example.magic.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {
    private RecyclerView.Adapter adapterCartelera, adapterPreventa;
    private RecyclerView recyclerViewCartelera, recyclerViewPreventa;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest, mStringRequest2;
    private ProgressBar loading1, loading2;
    private ViewPager2 viewPager2;
    private Handler slideHandler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        LinearLayout usuario= findViewById(R.id.usuario);
        usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Principal.this, usuario.class);
                startActivity(intent);
            }
        });
        LinearLayout favoritos= findViewById(R.id.favoritos);
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Principal.this, favoritos.class);
                startActivity(intent);
            }
        });
        LinearLayout cines= findViewById(R.id.cines);
        cines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Principal.this, cines.class);
                startActivity(intent);
            }
        });



        initView();
        banners();
        sendRequestCartelera();
        sendRequestPreventa();


    }

    private void sendRequestCartelera() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
            Gson gson= new Gson();
            loading1.setVisibility(View.GONE);
            ListFilm items=gson.fromJson(response,ListFilm.class);
            adapterCartelera=new FilmListAdapter(items);
            recyclerViewCartelera.setAdapter(adapterCartelera);
        }, error -> {
            loading1.setVisibility(View.GONE);
            Log.i("UiLover", "onErrorResponse: "+error.toString());
        });
        mRequestQueue.add(mStringRequest);
    }

    private void sendRequestPreventa() {
        mRequestQueue= Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", response -> {
            Gson gson= new Gson();
            loading2.setVisibility(View.GONE);
            ListFilm items=gson.fromJson(response,ListFilm.class);
            adapterPreventa=new FilmListAdapter(items);
            recyclerViewPreventa.setAdapter(adapterPreventa);
        }, error -> {
            loading2.setVisibility(View.GONE);
            Log.i("UiLover", "onErrorResponse: "+error.toString());
        });
        mRequestQueue.add(mStringRequest2);
    }

    private void banners() {
        List<SliderItems> sliderItems= new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.wide2));
        sliderItems.add(new SliderItems(R.drawable.wide1));

        viewPager2.setAdapter(new SliderAdapters(sliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer= new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
            }
        });
    }
    private Runnable sliderRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable,10);
    }

    private void initView() {
        viewPager2=findViewById(R.id.viewpagerSlider);
        recyclerViewCartelera=findViewById(R.id.view1);
        recyclerViewCartelera.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPreventa=findViewById(R.id.view2);
        recyclerViewPreventa.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        loading1=findViewById(R.id.progressBar1);
        loading2=findViewById(R.id.progressBar2);
    }
}