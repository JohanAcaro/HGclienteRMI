package org.example;

import org.example.database.remote.JuegoDeTronosInterfaceRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Registry registry;
        Scanner lector = new Scanner(System.in);
        try {
            registry = LocateRegistry.getRegistry("192.168.1.42", 5055);
            System.out.println("Hemos obtenido el registro");
            JuegoDeTronosInterfaceRMI juegoTronos = (JuegoDeTronosInterfaceRMI) registry.lookup("misPersonajes");
            System.out.println("Hemos obtenido el objeto remoto");
            System.out.println(); // Retorno de carro.
            String buscado;
            String opcion;
            do {
                escribirMenu();
                opcion = lector.nextLine().toUpperCase();
                switch (opcion) {
                    case "A" -> {
                        System.out.println("Personajes");
                        System.out.println(juegoTronos.allPersonajes());
                    }
                    case "B" -> {
                        System.out.println("Casas");
                        System.out.println(juegoTronos.allCasas());
                    }
                    case "C" -> {
                        System.out.println("Escribe el nombre del personaje: ");
                        buscado = lector.nextLine();
                        System.out.println(juegoTronos.buscarPersonaje(buscado));
                    }
                    case "D" -> {
                        System.out.println("Escribe el nombre de la casa de los personajes: ");
                        buscado = lector.nextLine();
                        System.out.println(juegoTronos.buscarPersonajesCasa(buscado));
                    }
                    case "E" -> {
                        System.out.println("Escribe el nombre de la casa: ");
                        buscado = lector.nextLine();
                        System.out.println(juegoTronos.buscarCasa(buscado));
                    }
                    case "F" -> System.out.println("Programa finalizado");
                    default -> System.out.println("Opción incorrecta");
                }
            } while (!opcion.equals("F"));
        } catch (RemoteException | NotBoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lector.close();
    }

    private static void escribirMenu() {
        System.out.println();
        System.out.println("Búsqueda Juego de Tronos");
        System.out.println("--------------------------");
        System.out.println("A = Todos Personajes");
        System.out.println("B = Todas Casas");
        System.out.println("C = Personaje");
        System.out.println("D = Personajes por casa");
        System.out.println("E = Casa");
        System.out.println("F = Terminar programa");
        System.out.println("--------------------------");
        System.out.println("¿Qué opción eliges?");

    }
}
