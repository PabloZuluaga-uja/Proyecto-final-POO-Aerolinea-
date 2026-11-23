package com.aerolinea.modelo;

public class Piloto extends Personal {
    private int horasVuelo;

    public Piloto(String nombre, String id, String cargo, int horasVuelo) {
        super(nombre, id, cargo);
        if (horasVuelo < 0) {
            throw new IllegalArgumentException("Las horas de vuelo no pueden ser negativas.");
        }
        this.horasVuelo = horasVuelo;
    }

    /** SOBREESCRITURA (polimorfismo dinámico). */
    @Override
    public String generarReporte() {
        return "=== PILOTO ===\nNombre: " + getNombre() + "\nID: " + getId()
                + "\nCargo: " + getCargo() + "\nHoras de vuelo: " + horasVuelo;
    }
}