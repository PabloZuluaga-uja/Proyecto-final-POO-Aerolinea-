package com.aerolinea.excepciones;

/** Se lanza cuando los datos de un tiquete no son válidos. */
public class TiqueteInvalidoException extends Exception {
    public TiqueteInvalidoException(String mensaje) {
        super(mensaje);
    }
}
