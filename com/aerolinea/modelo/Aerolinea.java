package com.aerolinea.modelo;

import com.aerolinea.excepciones.PasajeroNoEncontradoException;
import com.aerolinea.excepciones.PersonalNoEncontradoException;
import java.util.ArrayList;

/**
 * Composición con Vuelo: la aerolínea crea sus vuelos internamente
 * (en el constructor y en agregarVuelo). Si desaparece, sus vuelos también.
 */
public class Aerolinea {
    private String nombre;
    private ArrayList<Vuelo> vuelos;
    private ArrayList<Personal> personal;

    public Aerolinea(String nombre) {
        this.nombre = nombre;
        this.vuelos = new ArrayList<>();
        this.personal = new ArrayList<>();
        vuelos.add(new Vuelo("AV101", "Medellín", 10));
        vuelos.add(new Vuelo("AV202", "Cartagena", 8));
        vuelos.add(new Vuelo("AV303", "Cali", 6));
    }

    public void agregarVuelo(String codigo, String destino, int capacidad) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del vuelo no puede estar vacío.");
        }
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }
        if (buscarVuelo(codigo) != null) {
            throw new IllegalArgumentException("Ya existe un vuelo con código " + codigo + ".");
        }
        vuelos.add(new Vuelo(codigo, destino, capacidad)); // lanza IllegalArgumentException si capacidad <= 0
        System.out.println("Vuelo '" + codigo + "' a " + destino + " creado con capacidad " + capacidad);
    }

    public void agregarPersonal(Personal p) {
        personal.add(p);
        System.out.println(p.getNombre() + " registrado en la aerolínea.");
    }

    /** Retorna el vuelo o null si no existe. */
    public Vuelo buscarVuelo(String codigo) {
        for (Vuelo v : vuelos) {
            if (v.getCodigo().equalsIgnoreCase(codigo)) {
                return v;
            }
        }
        return null;
    }

    public Pasajero buscarPasajero(String pasaporte) throws PasajeroNoEncontradoException {
        for (Vuelo v : vuelos) {
            try {
                return v.buscarPasajero(pasaporte);
            } catch (PasajeroNoEncontradoException e) {
                // no está en este vuelo: se sigue con el siguiente
            }
        }
        throw new PasajeroNoEncontradoException(pasaporte);
    }

    public AgenteVentas buscarAgente(String id) throws PersonalNoEncontradoException {
        for (Personal p : personal) {
            if (p instanceof AgenteVentas && p.getId().equalsIgnoreCase(id)) {
                return (AgenteVentas) p;
            }
        }
        throw new PersonalNoEncontradoException(id);
    }

    public void listarAgentes() {
        System.out.println("--- Agentes de ventas ---");
        for (Personal p : personal) {
            if (p instanceof AgenteVentas) {
                System.out.println(p.getId() + " - " + p.getNombre() + " (" + p.getCargo() + ")");
            }
        }
    }

    public void generarReporteGeneral() {
        System.out.println("===== REPORTE GENERAL: " + nombre + " =====");
        for (Vuelo v : vuelos) {
            System.out.println("Vuelo: " + v.getCodigo() + " | Destino: " + v.getDestino()
                    + " | Ocupación: " + v.getPasajeros().size() + "/" + v.getCapacidad());
        }
        System.out.println("Total de personal: " + personal.size());
        for (Personal p : personal) {
            System.out.println(p.generarReporte()); // polimorfismo dinámico
            System.out.println();
        }
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public ArrayList<Personal> getPersonal() {
        return personal;
    }
}
