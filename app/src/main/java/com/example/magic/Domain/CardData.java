package com.example.magic.Domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardData {
    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String name;
    public String room;
    public String startDate;
    public String seats;

    public CardData(String name, String room, String startDate, String seats) {
        this.name = name;
        this.room = room;
        this.startDate = startDate;
        this.seats = seats;
    }

    // Puedes agregar más campos según tus necesidades
}


