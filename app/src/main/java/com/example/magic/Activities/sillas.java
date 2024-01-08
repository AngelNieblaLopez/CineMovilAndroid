package com.example.magic.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.magic.R;
import com.example.magic.globals;
import com.example.magic.retrofit.ApiRetrofit;
import com.example.magic.retrofit.clients.ApiClientLogin;
import com.example.magic.retrofit.clients.ApiResponseClientLogin;
import com.example.magic.retrofit.clients.Client;
import com.example.magic.retrofit.seats.ApiResponseSeatsAvailable;
import com.example.magic.retrofit.seats.Seat;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sillas extends AppCompatActivity {
    TextView restTickets;
    int[] buyedSeats = new int[0];

    void refreshRestTicketsTex(int actualrestTickets, int selectedQtyTickets) {
      restTickets.setText("Boletos restantes: "+String.valueOf(Math.abs(actualrestTickets - selectedQtyTickets) ));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sillas);

        // Obtén el GridLayout
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        restTickets = findViewById(R.id.textView2);
        globals.seatsIds.clear();
        // Crea y agrega botones dinámicamente;
        crearBotones(gridLayout);




        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para el clic en el botón "Guardado" dinámico
                if(globals.quantitySeats != globals.seatsIds.size()){
                    showMessage("No se seleccionaron todos los asientos requeridos");
                    return;
                }

                Toast.makeText(sillas.this, "Guardado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(sillas.this, PagoActivity.class);
                startActivity(intent);
            }
        };

        crearBotonDinamico(gridLayout, "Guardado", listener);
    }

    private void crearBotones(GridLayout gridLayout) {
        refreshRestTicketsTex(0, globals.quantitySeats);
        // Número de botones que deseas crear
        int numeroDeBotones = 24;
        String[] columnas = { "A", "B", "C", "D" };

        for (int i = 1; i <= numeroDeBotones; i++) {
            String texto = "";
            // Crea un nuevo botón
            if (i >= 1 && i <= 6) {
                texto = columnas[0] + "" + i;
            } else if (i >= 7 && i <= 12) {
                texto = columnas[1] + "" + i;
            } else if (i >= 13 && i <= 18) {
                texto = columnas[2] + "" + i;
            } else if (i >= 19 && i <= 24) {
                texto = columnas[3] + "" + i;
            }

            Button button = new Button(this);

            // Establece el texto del botón (puedes personalizarlo según tus necesidades)
            button.setText(texto);
            button.setTag(String.valueOf(i));

            // Configura la acción al hacer clic en el botón
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String seatId = (String) view.getTag();
                    boolean isBuyed = false;

                    for (int i = 0; i < gridLayout.getChildCount(); i++) {
                        View buttonSeat = gridLayout.getChildAt(i);
                        String seatIdPosible = (String) buttonSeat.getTag();

                        if(seatIdPosible.equals(seatId)) {
                            for (int buyedSeatIndex = 0; buyedSeatIndex < buyedSeats.length; buyedSeatIndex++) {
                                if (String.valueOf(buyedSeats[buyedSeatIndex]).equals(seatId)  ) {

                                    isBuyed = true;
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    if(isBuyed) return;

                    globals.toggleSeat(seatId);
                    if(globals.seatsIds.size() > globals.quantitySeats) {
                        globals.toggleSeat(seatId);
                        return;
                    }
                    refreshRestTicketsTex(globals.seatsIds.size(), globals.quantitySeats);

                    // Maneja la lógica cuando se hace clic en el botón
                    if (!button.isSelected()) {
                        // Si no está seleccionado, selecciónalo y marca el botón
                        button.setSelected(true);
                        // button.setBackgroundColor(Color.parseColor("#ff0000")); // Cambia el color de fondo al seleccionar
                         button.setBackgroundColor(Color.parseColor("#12a351"));
                    } else {
                        // Si ya está seleccionado, desmárcalo
                        button.setSelected(false);
                        button.setBackgroundColor(Color.parseColor("#ffffff")); // Restaura el color de fondo original
                    }
                }
            });

            // Cambia el color de fondo del botón (puedes personalizar el color según tus necesidades)
            button.setBackgroundColor(Color.parseColor("#ffffff"));

            // Añade un contorno alrededor del botón (directamente en los parámetros de diseño)
            int anchoBotonEnPixeles = (int) getResources().getDimension(R.dimen.ancho_boton);
            int altoBotonEnPixeles = (int) getResources().getDimension(R.dimen.alto_boton);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.setMargins(5, 5, 5, 5); // Márgenes para el contorno
            button.setLayoutParams(params);

            // Agrega el botón al GridLayout
            gridLayout.addView(button);
        }





        // Mandar a llamr asientos ocupados

        Seat seat = ApiRetrofit.getRetrofitInstance().create(Seat.class);
        Call<ApiResponseSeatsAvailable> call = seat.availableSeats(globals.functionId);

        call.enqueue(new Callback<ApiResponseSeatsAvailable>() {
            @Override
            public void onResponse(Call<ApiResponseSeatsAvailable> call, Response<ApiResponseSeatsAvailable> response) {
                if (response.isSuccessful()){
                    ApiResponseSeatsAvailable apiResponse = response.body();
                    if (apiResponse != null) {
                        buyedSeats = apiResponse.seatsAvailable.buyedSeatIds;
                        for (int i = 0; i < gridLayout.getChildCount(); i++) {
                            View buttonSeat = gridLayout.getChildAt(i);
                            String seatId = (String) buttonSeat.getTag();



                            for (int buyedSeatIndex = 0; buyedSeatIndex < buyedSeats.length; buyedSeatIndex++) {
                                if (String.valueOf(buyedSeats[buyedSeatIndex]).equals(seatId)  ) {

                                    buttonSeat.setBackgroundColor(Color.RED);
                                    break;
                                }
                            }

                        }
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
            public void onFailure(Call<ApiResponseSeatsAvailable> call, Throwable t) {
                showMessage("Error en la conexión");
            }
        });




    }

    private void crearBotonDinamico(GridLayout gridLayout, String texto, View.OnClickListener onClickListener ) {
        Button button = new Button(this);
        button.setText(texto);
        button.setOnClickListener(onClickListener);

        // Centra el botón dentro del GridLayout
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = GridLayout.LayoutParams.WRAP_CONTENT;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins(5, 5, 5, 5); // Márgenes para el contorno
        button.setLayoutParams(params);

        // Centra el botón horizontalmente
        button.setGravity(Gravity.CENTER_HORIZONTAL);

        // Agrega el botón al GridLayout
        gridLayout.addView(button);


    }

    public void showMessage(String message) {
        Toast.makeText(sillas.this, message, Toast.LENGTH_SHORT).show();
    }
}