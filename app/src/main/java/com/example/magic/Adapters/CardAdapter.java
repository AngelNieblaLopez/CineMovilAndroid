package com.example.magic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.magic.Domain.CardData;
import com.example.magic.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    // Lista de datos para las tarjetas
    private List<CardData> cardList;

    // Constructor
    public CardAdapter(List<CardData> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_sale_card_layout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardData cardData = cardList.get(position);



        holder.titleTextView.setText(cardData.name);
        holder.roomTextView.setText(cardData.room);
        holder.seatsTextView.setText("   "+cardData.seats);
        holder.startDateTextView.setText(cardData.startDate);

        // Configurar la tarjeta
        // Puedes personalizar esto según tus necesidades

        // Manejar clics en la tarjeta para expandir/colapsar
        holder.cardView.setOnClickListener(v -> {
            boolean expanded = cardData.isExpanded();
            cardData.setExpanded(!expanded);
            notifyItemChanged(position);
        });

        // Actualizar visibilidad del contenido adicional
        if (cardData.isExpanded()) {
            holder.hiddenContent.setVisibility(View.VISIBLE);
        } else {
            holder.hiddenContent.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    // ViewHolder para la tarjeta
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        LinearLayout hiddenContent;
        TextView titleTextView, roomTextView, startDateTextView, seatsTextView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            hiddenContent = itemView.findViewById(R.id.hiddenContent);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            roomTextView = itemView.findViewById(R.id.roomTextView);
            startDateTextView = itemView.findViewById(R.id.startDateTextView);
            seatsTextView = itemView.findViewById(R.id.seatsTextView);

        }
    }
}
