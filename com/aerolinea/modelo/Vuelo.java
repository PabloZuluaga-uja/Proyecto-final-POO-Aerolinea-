package com.aerolinea.modelo;

import com.aerolinea.excepciones.PasajeroNoEncontradoException;
import com.aerolinea.excepciones.VueloLlenoException;
import java.util.ArrayList;

/**
 * Agregación con Pasajero: el vuelo puede existir sin pasajeros y los pasajeros
 * pueden existir sin vuelo. Usa el tipo abstracto Pasajero (polimorfismo).
 */

public class Vuelo {
    private String codigo;
    private String destino;
    private int capacidad;
    private ArrayList<Pasajero> pasajeros;

    public Vuelo(String codigo, String destino, int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad debe ser mayor a 0.");
        }
        this.codigo = codigo;
        this.destino = destino;
        this.capacidad = capacidad;
        this.pasajeros = new ArrayList<>();
    }

    public void abordar(Pasajero p) throws VueloLlenoException {
        if (pasajeros.size() >= capacidad) {
            throw new VueloLlenoException("Vuelo " + codigo + " sin asientos disponibles.");
        }
        pasajeros.add(p);
        System.out.println("Pasajero " + p.getNombre() + " abordó el vuelo " + codigo);
    }

    public void desembarcar(String pasaporte) throws PasajeroNoEncontradoException {
        for (int i = 0; i < pasajeros.size(); i++) {
            if (pasajeros.get(i).getPasaporte().equalsIgnoreCase(pasaporte)) {
                pasajeros.remove(i);
                System.out.println("Pasajero desembarcado.");
                return;
            }
        }
        throw new PasajeroNoEncontradoException(pasaporte);
    }

    public Pasajero buscarPasajero(String pasaporte) throws PasajeroNoEncontradoException {
        for (Pasajero p : pasajeros) {
            if (p.getPasaporte().equalsIgnoreCase(pasaporte)) {
                return p;
            }
        }
        throw new PasajeroNoEncontradoException(pasaporte);
    }

    /** Polimorfismo dinámico: cada pasajero responde con su propio obtenerInfo(). */
    public void listarPasajeros() {
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros en este vuelo.");
            return;
        }
        for (Pasajero p : pasajeros) {
            System.out.println(p.obtenerInfo());
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }
}
