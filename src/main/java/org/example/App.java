package org.example;

import org.example.database.remote.JuegoDeTronosInterfaceRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class App
{
    public static void main( String[] args )
    {
        // Declara el registro de objetos remotos y el lector de teclado
        Registry registry;
        var lector = new Scanner(System.in);
        try {
            // Obtiene el registro del host y puerto indicados
            registry = LocateRegistry.getRegistry("192.168.56.1", 5055);
            System.out.println("Hemos obtenido el registro");
            // Obtiene el objeto remoto
            JuegoDeTronosInterfaceRMI juegoTronos = (JuegoDeTronosInterfaceRMI) registry.lookup("misPersonajes");
            System.out.println("Hemos obtenido el objeto remoto");
            System.out.println(); // Retorno de carro.
            // Declara las variables necesarias
            String buscado;
            String opcion;
            // Login
            login(lector, juegoTronos);

            do {
                // Muestra el menú
                escribirMenu();
                opcion = lector.nextLine().toUpperCase();
                // Ejecuta la opción elegida
                switch (opcion) {
                    case "A" -> {
                        // Muestra todos los personajes
                        System.out.println("\nPersonajes");
                        System.out.println(juegoTronos.allPersonajes());
                    }
                    case "B" -> {
                        // Muestra todas las casas
                        System.out.println("\nCasas");
                        System.out.println(juegoTronos.allCasas());
                    }
                    case "C" -> {
                        // Busca un personaje
                        System.out.println("Escribe el nombre del personaje: ");
                        buscado = lector.nextLine();
                        System.out.println("\nPersonaje");
                        System.out.println(juegoTronos.buscarPersonaje(buscado));
                    }
                    case "D" -> {
                        // Busca una casa
                        System.out.println("Escribe el nombre de la casa: ");
                        buscado = lector.nextLine();
                        System.out.println("\nCasa");
                        System.out.println(juegoTronos.buscarCasa(buscado));
                    }
                    case "E" -> {
                        // Busca los personajes de una casa
                        System.out.println("Escribe el nombre de la casa de los personajes: ");
                        buscado = lector.nextLine();
                        System.out.println("\nPersonajes de la casa " + buscado);
                        System.out.println(juegoTronos.buscarPersonajesCasa(buscado));
                    }
                    case "F" -> System.out.println("Programa finalizado");

                    default -> System.out.println("Opción incorrecta");
                }
            } while (!opcion.equals("F"));
        } catch (RemoteException | NotBoundException e) {
            // Si hay algún error, lo muesro por pantalla
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Cierra el lector de teclado
        lector.close();
    }

    private static void login(Scanner lector, JuegoDeTronosInterfaceRMI juegoTronos) {
        // Declara las variables necesarias
        String usuario;
        String password;
        boolean loginCorrecto = false;
        // Bucle del login hasta que sea correcto
        do {
            // Pide el usuario y la contraseña
            System.out.println("Introduce el usuario: ");
            usuario = lector.nextLine();
            System.out.println("Introduce la contraseña: ");
            password = lector.nextLine();
            // Comprueba si el usuario y la contraseña son correctos
            try {
                loginCorrecto = juegoTronos.iniciarSesion(usuario, password);
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Muestra el mensaje de bienvenida o de error
            if (loginCorrecto) {
                System.out.println("Bienvenido " + usuario);
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }
            System.out.println();
        } while (!loginCorrecto);
    }

    private static void escribirMenu() {
        // Menú de opciones
        System.out.println("Búsqueda Juego de Tronos");
        System.out.println("--------------------------");
        System.out.println("A = Todos Personajes");
        System.out.println("B = Todas Casas");
        System.out.println("C = Personaje");
        System.out.println("D = Casa");
        System.out.println("E = Personajes por casa");
        System.out.println("F = Terminar programa");
        System.out.println("--------------------------");
        System.out.println("¿Qué opción eliges?");

    }
}
