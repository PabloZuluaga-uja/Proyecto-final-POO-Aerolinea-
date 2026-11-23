package com.aerolinea.modelo;

/** Asociación entre un AgenteVentas y un Pasajero. */
public class Tiquete {
    private String fecha;
    private String clase;
    private Pasajero pasajero;
    private AgenteVentas agente;

    public Tiquete(String fecha, String clase, Pasajero pasajero, AgenteVentas agente) {
        this.fecha = fecha;
        this.clase = clase;
        this.pasajero = pasajero;
        this.agente = agente;
    }

    public String getInfo() {
        return "Tiquete: " + fecha + " | Clase: " + clase + " | Agente: " + agente.getNombre()
                + " | Pasajero: " + pasajero.getNombre();
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public AgenteVentas getAgente() {
        return agente;
    }

    public String getFecha() {
        return fecha;
    }
}
