package com.aerolinea.modelo;

import com.aerolinea.excepciones.TiqueteInvalidoException;
import java.util.ArrayList;

/** Agente que vende tiquetes. Asociado a Tiquete. */
public class AgenteVentas extends Personal {
    private ArrayList<Tiquete> tiquetes;

    public AgenteVentas(String nombre, String id, String cargo) {
        super(nombre, id, cargo);
        this.tiquetes = new ArrayList<>();
    }

    private void validarBase(String fecha, Pasajero pasajero) throws TiqueteInvalidoException {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new TiqueteInvalidoException("La fecha no puede estar vacía.");
        }
        if (pasajero == null) {
            throw new TiqueteInvalidoException("Debe indicarse un pasajero válido.");
        }
    }

    /** SOBRECARGA 1: clase económica por defecto. */
    public void venderTiquete(String fecha, Pasajero pasajero) throws TiqueteInvalidoException {
        validarBase(fecha, pasajero);
        Tiquete t = new Tiquete(fecha, "Económica", pasajero, this);
        tiquetes.add(t);
        System.out.println("Tiquete vendido para " + fecha + " a " + pasajero.getNombre());
    }

    /** SOBRECARGA 2: clase indicada por el usuario. */
    public void venderTiquete(String fecha, String clase, Pasajero pasajero) throws TiqueteInvalidoException {
        validarBase(fecha, pasajero);
        if (clase == null || clase.trim().isEmpty()) {
            throw new TiqueteInvalidoException("La clase no puede estar vacía.");
        }
        Tiquete t = new Tiquete(fecha, clase, pasajero, this);
        tiquetes.add(t);
        System.out.println("Tiquete vendido para " + fecha + " en clase " + clase + " a " + pasajero.getNombre());
    }

    /** SOBREESCRITURA (polimorfismo dinámico). */
    @Override
    public String generarReporte() {
        return "=== AGENTE DE VENTAS ===\nNombre: " + getNombre() + "\nID: " + getId()
                + "\nCargo: " + getCargo() + "\nTiquetes vendidos: " + tiquetes.size();
    }

    public ArrayList<Tiquete> getTiquetes() {
        return tiquetes;
    }
}
