package com.example.magic.Activities;

public class Pelicula {
    private String titulo;
    private String descripcion;
    private CategoriaPelicula categoria;

    public Pelicula(String titulo, String descripcion, CategoriaPelicula categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaPelicula getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPelicula categoria) {
        this.categoria = categoria;
    }
}
