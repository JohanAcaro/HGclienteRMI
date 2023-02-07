package org.example.database.entities;

import java.io.Serial;
import java.io.Serializable;

public class Personaje implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
    private int id;

    private String nombre;

    private Casa casa;

    private int edad;
    private String titulo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
