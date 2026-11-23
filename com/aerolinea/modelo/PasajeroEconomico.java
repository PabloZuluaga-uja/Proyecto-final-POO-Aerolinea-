package com.aerolinea.modelo;

public class PasajeroEconomico extends Pasajero {
    private int equipajeKg;

    public PasajeroEconomico(String nombre, String pasaporte, int edad, int equipajeKg) {
        super(nombre, pasaporte, edad);
        if (equipajeKg < 0 || equipajeKg > 23) {
            throw new IllegalArgumentException("El equipaje debe estar entre 0 y 23 kg.");
        }
        this.equipajeKg = equipajeKg;
    }

    @Override
    public String obtenerInfo() {
        return "Pasajero económico: " + getNombre() + " | Pasaporte: " + getPasaporte()
                + " | Edad: " + getEdad() + " | Equipaje: " + equipajeKg + " kg";
    }
}
