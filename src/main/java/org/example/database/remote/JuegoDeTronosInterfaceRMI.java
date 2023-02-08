package org.example.database.remote;

import java.rmi.Remote;

public interface JuegoDeTronosInterfaceRMI extends Remote {
    // Métodos remotos
    boolean iniciarSesion(String username, String password) throws Exception;
    String allPersonajes() throws Exception;

    String allCasas() throws Exception;
    String buscarPersonaje(String nombre) throws Exception;
    String buscarPersonajesCasa(String casa) throws Exception;

    String buscarCasa(String nombreCasa) throws Exception;

}
