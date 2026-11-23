package com.aerolinea.modelo;

/** Clase final: no puede heredarse. */
public final class PasajeroEjecutivo extends Pasajero {
    private int millas;
    private boolean salaVip;

    public PasajeroEjecutivo(String nombre, String pasaporte, int edad, int millas, boolean salaVip) {
        super(nombre, pasaporte, edad);
        if (millas < 0) {
            throw new IllegalArgumentException("Las millas no pueden ser negativas.");
        }
        this.millas = millas;
        this.salaVip = salaVip;
    }

    @Override
    public String obtenerInfo() {
        return "Pasajero ejecutivo: " + getNombre() + " | Pasaporte: " + getPasaporte()
                + " | Edad: " + getEdad() + " | Millas: " + millas
                + " | Sala VIP: " + (salaVip ? "Sí" : "No");
    }

    public int getMillas() {
        return millas;
    }
}
