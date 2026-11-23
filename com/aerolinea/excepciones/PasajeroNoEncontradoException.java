package com.aerolinea.excepciones;

/** Se lanza cuando no existe un pasajero con el pasaporte indicado. */
public class PasajeroNoEncontradoException extends Exception {
    public PasajeroNoEncontradoException(String pasaporte) {
        super("Pasajero con pasaporte " + pasaporte + " no encontrado.");
    }
}
