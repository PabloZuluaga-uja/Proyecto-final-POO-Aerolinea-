package com.aerolinea.modelo;

/** Clase abstracta base de los pasajeros. */
public abstract class Pasajero {
    private String nombre;
    private String pasaporte;
    private int edad;

    public Pasajero(String nombre, String pasaporte, int edad) {
        Validador.validarNombre(nombre);
        Validador.validarPasaporte(pasaporte);
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("Edad inválida: " + edad);
        }
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public int getEdad() {
        return edad;
    }

    public abstract String obtenerInfo();
}
