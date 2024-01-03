package com.example.magic.retrofit.movies;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenresAdapter extends TypeAdapter<List<String>> {

    @Override
    public void write(JsonWriter out, List<String> value) throws IOException {
        // No necesitas implementar esto si solo estás haciendo la conversión en la lectura
        throw new UnsupportedOperationException("Operación no soportada");
    }

    @Override
    public List<String> read(JsonReader in) throws IOException {
        List<String> genres = new ArrayList<>();

        in.beginArray();
        while (in.hasNext()) {
            String genre = in.nextString();
            genres.add(genre);
        }
        in.endArray();

        return genres;
    }
}