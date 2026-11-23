package com.aerolinea.app;

import com.aerolinea.excepciones.PasajeroNoEncontradoException;
import com.aerolinea.excepciones.PersonalNoEncontradoException;
import com.aerolinea.excepciones.TiqueteInvalidoException;
import com.aerolinea.excepciones.VueloLlenoException;
import com.aerolinea.modelo.Aerolinea;
import com.aerolinea.modelo.AgenteVentas;
import com.aerolinea.modelo.Pasajero;
import com.aerolinea.modelo.PasajeroEconomico;
import com.aerolinea.modelo.PasajeroEjecutivo;
import com.aerolinea.modelo.Piloto;
import com.aerolinea.modelo.Tiquete;
import com.aerolinea.modelo.Vuelo;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pasajero> pasajerosRegistrados = new ArrayList<>();
        Aerolinea aerolinea = new Aerolinea("Aerolínea Andes Express");

        AgenteVentas a1 = new AgenteVentas("Sofía Herrera", "AG001", "Ventas aeropuerto");
        AgenteVentas a2 = new AgenteVentas("Mateo Vargas", "AG002", "Ventas en línea");
        Piloto p1 = new Piloto("Capitán Ricardo León", "P001", "Piloto comandante", 5200);
        aerolinea.agregarPersonal(a1);
        aerolinea.agregarPersonal(a2);
        aerolinea.agregarPersonal(p1);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Seleccione una opción: ");
            System.out.println();
            switch (opcion) {
                case 1:
                    registrarPasajero(sc, pasajerosRegistrados);
                    break;
                case 2:
                    listarRegistrados(pasajerosRegistrados);
                    break;
                case 3:
                    abordarPasajero(sc, aerolinea, pasajerosRegistrados);
                    break;
                case 4:
                    venderTiquete(sc, aerolinea);
                    break;
                case 5:
                    verPasajerosDeVuelo(sc, aerolinea);
                    break;
                case 6:
                    verTiquetesDeAgente(sc, aerolinea);
                    break;
                case 7:
                    desembarcarPasajero(sc, aerolinea);
                    break;
                case 8:
                    buscarPasajero(sc, aerolinea);
                    break;
                case 9:
                    aerolinea.generarReporteGeneral();
                    break;
                case 10:
                    crearVuelo(sc, aerolinea);
                    break;
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // ------------------------------------------------------------------
    // Utilidades de lectura
    // ------------------------------------------------------------------

    /** Insiste hasta recibir un entero; limpia el buffer en cada caso. */
    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // consume el salto de línea restante
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine(); // descarta la entrada inválida
                System.out.println("Entrada inválida: debe ingresar un número entero.");
            }
        }
    }

    private static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static boolean leerSiNo(Scanner sc, String mensaje) {
        while (true) {
            String r = leerTexto(sc, mensaje + " (s/n): ").toLowerCase();
            if (r.equals("s")) {
                return true;
            }
            if (r.equals("n")) {
                return false;
            }
            System.out.println("Responda 's' o 'n'.");
        }
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== AEROLÍNEA ANDES EXPRESS =====");
        System.out.println("1.  Registrar pasajero");
        System.out.println("2.  Listar pasajeros registrados");
        System.out.println("3.  Abordar pasajero a un vuelo");
        System.out.println("4.  Vender tiquete");
        System.out.println("5.  Ver pasajeros de un vuelo");
        System.out.println("6.  Ver tiquetes de un agente");
        System.out.println("7.  Desembarcar un pasajero");
        System.out.println("8.  Buscar pasajero por pasaporte");
        System.out.println("9.  Reporte general de la aerolínea");
        System.out.println("10. Crear nuevo vuelo");
        System.out.println("0.  Salir");
    }

    /** Busca en la lista temporal de pasajeros registrados; retorna null si no está. */
    private static Pasajero buscarRegistrado(ArrayList<Pasajero> registrados, String pasaporte) {
        for (Pasajero p : registrados) {
            if (p.getPasaporte().equalsIgnoreCase(pasaporte)) {
                return p;
            }
        }
        return null;
    }

    // ------------------------------------------------------------------
    // Opciones del menú
    // ------------------------------------------------------------------

    private static void registrarPasajero(Scanner sc, ArrayList<Pasajero> registrados) {
        int tipo = leerEntero(sc, "Tipo de pasajero (1=Económico, 2=Ejecutivo): ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo inválido.");
            return;
        }
        try {
            String nombre = leerTexto(sc, "Nombre: ");
            String pasaporte = leerTexto(sc, "Pasaporte: ");
            int edad = leerEntero(sc, "Edad: ");
            Pasajero nuevo;
            if (tipo == 1) {
                int equipaje = leerEntero(sc, "Equipaje (kg): ");
                nuevo = new PasajeroEconomico(nombre, pasaporte, edad, equipaje);
            } else {
                int millas = leerEntero(sc, "Millas acumuladas: ");
                boolean salaVip = leerSiNo(sc, "¿Tiene acceso a sala VIP?");
                nuevo = new PasajeroEjecutivo(nombre, pasaporte, edad, millas, salaVip);
            }
            if (buscarRegistrado(registrados, pasaporte) != null) {
                System.out.println("Error: ya hay un pasajero registrado con el pasaporte " + pasaporte + ".");
                return;
            }
            registrados.add(nuevo);
            System.out.println("Pasajero registrado: " + nuevo.getNombre());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarRegistrados(ArrayList<Pasajero> registrados) {
        if (registrados.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
            return;
        }
        System.out.println("--- Pasajeros registrados ---");
        for (Pasajero p : registrados) {
            System.out.println(p.obtenerInfo()); // polimorfismo dinámico
        }
    }

    private static void abordarPasajero(Scanner sc, Aerolinea aerolinea, ArrayList<Pasajero> registrados) {
        String codigo = leerTexto(sc, "Código del vuelo: ");
        Vuelo vuelo = aerolinea.buscarVuelo(codigo);
        if (vuelo == null) {
            System.out.println("Error: el vuelo " + codigo + " no existe.");
            return;
        }
        String pasaporte = leerTexto(sc, "Pasaporte del pasajero: ");
        Pasajero pasajero = buscarRegistrado(registrados, pasaporte);
        if (pasajero == null) {
            System.out.println("Error: el pasajero con pasaporte " + pasaporte + " no está registrado.");
            return;
        }
        // Un pasajero no puede estar abordado en dos vuelos a la vez.
        try {
            aerolinea.buscarPasajero(pasaporte);
            System.out.println("Error: el pasajero ya abordó un vuelo. Debe desembarcar primero (opción 7).");
            return;
        } catch (PasajeroNoEncontradoException e) {
            // no está abordado en ningún vuelo: se puede continuar
        }
        try {
            vuelo.abordar(pasajero);
        } catch (VueloLlenoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void venderTiquete(Scanner sc, Aerolinea aerolinea) {
        aerolinea.listarAgentes();
        String id = leerTexto(sc, "ID del agente: ");
        try {
            AgenteVentas agente = aerolinea.buscarAgente(id);
            String pasaporte = leerTexto(sc, "Pasaporte del pasajero: ");
            Pasajero pasajero = aerolinea.buscarPasajero(pasaporte); // puede lanzar PasajeroNoEncontradoException
            String fecha = leerTexto(sc, "Fecha del vuelo: ");
            boolean conClase = leerSiNo(sc, "¿Desea indicar la clase?");
            if (conClase) {
                String clase = leerTexto(sc, "Clase: ");
                agente.venderTiquete(fecha, clase, pasajero); // sobrecarga 2
            } else {
                agente.venderTiquete(fecha, pasajero); // sobrecarga 1
            }
        } catch (PersonalNoEncontradoException | PasajeroNoEncontradoException | TiqueteInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void verPasajerosDeVuelo(Scanner sc, Aerolinea aerolinea) {
        String codigo = leerTexto(sc, "Código del vuelo: ");
        Vuelo vuelo = aerolinea.buscarVuelo(codigo);
        if (vuelo == null) {
            System.out.println("Error: el vuelo " + codigo + " no existe.");
            return;
        }
        System.out.println("--- Pasajeros del vuelo " + vuelo.getCodigo() + " a " + vuelo.getDestino() + " ---");
        vuelo.listarPasajeros();
    }

    private static void verTiquetesDeAgente(Scanner sc, Aerolinea aerolinea) {
        aerolinea.listarAgentes();
        String id = leerTexto(sc, "ID del agente: ");
        try {
            AgenteVentas agente = aerolinea.buscarAgente(id);
            if (agente.getTiquetes().isEmpty()) {
                System.out.println(agente.getNombre() + " no ha vendido tiquetes.");
                return;
            }
            System.out.println("--- Tiquetes de " + agente.getNombre() + " ---");
            for (Tiquete t : agente.getTiquetes()) {
                System.out.println(t.getInfo());
            }
        } catch (PersonalNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void desembarcarPasajero(Scanner sc, Aerolinea aerolinea) {
        String codigo = leerTexto(sc, "Código del vuelo: ");
        Vuelo vuelo = aerolinea.buscarVuelo(codigo);
        if (vuelo == null) {
            System.out.println("Error: el vuelo " + codigo + " no existe.");
            return;
        }
        String pasaporte = leerTexto(sc, "Pasaporte del pasajero: ");
        try {
            vuelo.desembarcar(pasaporte);
        } catch (PasajeroNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void buscarPasajero(Scanner sc, Aerolinea aerolinea) {
        String pasaporte = leerTexto(sc, "Pasaporte a buscar: ");
        try {
            Pasajero p = aerolinea.buscarPasajero(pasaporte);
            System.out.println(p.obtenerInfo());
        } catch (PasajeroNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearVuelo(Scanner sc, Aerolinea aerolinea) {
        String codigo = leerTexto(sc, "Código del nuevo vuelo: ");
        String destino = leerTexto(sc, "Destino: ");
        int capacidad = leerEntero(sc, "Capacidad: ");
        try {
            aerolinea.agregarVuelo(codigo, destino, capacidad);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
