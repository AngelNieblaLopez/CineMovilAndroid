package com.example.magic;

import java.util.ArrayList;
import java.util.List;

public class globals {
    public static int clientId;

    public static String movieId;

    public static String functionId;

    public static int quantitySeats;

    public static List<String> seatsIds = new ArrayList<>();

    public static void toggleSeat( String dato) {
        if (seatsIds.contains(dato)) {
            seatsIds.remove(dato);
        } else {
            seatsIds.add(dato);
        }
    }

}
