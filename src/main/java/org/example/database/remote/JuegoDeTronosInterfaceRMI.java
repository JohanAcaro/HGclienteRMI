package org.example.database.remote;

import java.rmi.Remote;

public interface JuegoDeTronosInterfaceRMI extends Remote {
    public String allPersonajes() throws Exception;

    public String allCasas() throws Exception;
    public String buscarPersonaje(String nombre) throws Exception;
    public String buscarPersonajesCasa(String casa) throws Exception;

    public String buscarCasa(String nombreCasa) throws Exception;

}
