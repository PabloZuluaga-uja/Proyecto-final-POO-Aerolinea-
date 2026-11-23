package com.aerolinea.excepciones;

/** Se lanza cuando se intenta abordar un vuelo que ya no tiene asientos. */
public class VueloLlenoException extends Exception {
    public VueloLlenoException(String mensaje) {
        super(mensaje);
    }
}
