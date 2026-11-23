package com.aerolinea.excepciones;

/** Se lanza cuando no existe personal registrado con el id indicado. */
public class PersonalNoEncontradoException extends Exception {
    public PersonalNoEncontradoException(String id) {
        super("No existe personal registrado con id " + id + ".");
    }
}