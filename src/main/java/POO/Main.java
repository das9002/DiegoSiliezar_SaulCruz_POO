package POO;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Authservice authService = new Authservice();
            Scanner scanner = new Scanner(System.in);
            boolean salir = false;

            while (!salir) {
                mostrarMenu();
                try {
                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 1 -> registrarUsuario(authService, scanner);
                        case 2 -> iniciarSesion(authService, scanner);
                        case 3 -> eliminarCuenta(authService, scanner);
                        case 4 -> verificarDisponibilidad(authService, scanner);
                        case 5 -> {
                            System.out.println("Saliendo del sistema...");
                            salir = true;
                        }
                        default -> System.out.println("Opción no válida. Ingrese un número entre 1 y 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingrese un número válido.");
                    scanner.nextLine();
                }
                System.out.println();
            }
            scanner.close();
        }

        private static void mostrarMenu() {
            System.out.println("-- AUTHSECURE CONTROL DE ACCESOS  --");
            System.out.println("1. Registrar nuevo usuario: ");
            System.out.println("2. Iniciar sesión: ");
            System.out.println("3. Eliminar cuenta de usuario: ");
            System.out.println("4. Verificar disponibilidad de Username: ");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
        }

        private static void registrarUsuario(Authservice authService, Scanner scanner) {
            System.out.println("\n--- REGISTRO DE USUARIO ---");
            System.out.print("Ingrese username: ");
            String username = scanner.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("El username no puede estar vacío.");
                return;
            }

            System.out.print("Ingrese contraseña: ");
            String password = scanner.nextLine();

            System.out.print("Ingrese nombre completo: ");
            String nombreCompleto = scanner.nextLine().trim();

            System.out.print("Ingrese email: ");
            String email = scanner.nextLine().trim();

            Usuario nuevoUsuario = new Usuario(username, password, nombreCompleto, email);
            boolean exito = authService.registarUsuario(nuevoUsuario);

            if (exito) {
                System.out.println("Usuario registrado exitosamente.");
            } else {
                System.out.println("Error: El nombre de usuario '" + username + "' ya se encuentra registrado.");
            }
        }

        private static void iniciarSesion(Authservice authService, Scanner scanner) {
            System.out.println("\n--- INICIO DE SESIÓN ---");
            System.out.print("Ingrese username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Ingrese contraseña: ");
            String password = scanner.nextLine();

            boolean autenticado = authService.iniciarSesion(username, password);

            if (autenticado) {
                System.out.println("¡Inicio de sesión exitoso! Bienvenido, " + username + ".");
            } else {
                System.out.println("Error: Credenciales incorrectas o usuario no encontrado.");
            }
        }

        private static void eliminarCuenta(Authservice authService, Scanner scanner) {
            System.out.println("\n--- ELIMINACIÓN DE CUENTA ---");
            System.out.print("Ingrese username a eliminar: ");
            String username = scanner.nextLine().trim();

            boolean eliminado = authService.eliminarCuenta(username);

            if (eliminado) {
                System.out.println("La cuenta del usuario '" + username + "' ha sido eliminada correctamente.");
            } else {
                System.out.println("Error: El usuario '" + username + "' no existe en el sistema.");
            }
        }

        private static void verificarDisponibilidad(Authservice authService, Scanner scanner) {
            System.out.println("\n--- VERIFICAR DISPONIBILIDAD ---");
            System.out.print("Ingrese username a consultar: ");
            String username = scanner.nextLine().trim();

            boolean disponible = authService.verificarDisponibilidad(username);

            if (disponible) {
                System.out.println("El nombre de usuario '" + username + "' está disponible.");
            } else {
                System.out.println("El nombre de usuario '" + username + "' NO está disponible (ya fue registrado).");
            }
        }
    }
