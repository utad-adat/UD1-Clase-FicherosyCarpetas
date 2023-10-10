package org.example;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String directorioActual = "."; // Directorio actual

        while (true) {
            mostrarContenidoDirectorio(directorioActual);

            System.out.println("\nOpciones:");
            System.out.println("1. Navegar a una carpeta");
            System.out.println("2. Crear una carpeta");
            System.out.println("3. Crear un archivo de texto");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la carpeta a la que desea navegar: ");
                    String carpetaDestino = scanner.next();
                    directorioActual = navegarACarpeta(directorioActual, carpetaDestino);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre de la carpeta que desea crear: ");
                    String nombreCarpeta = scanner.next();
                    crearCarpeta(directorioActual, nombreCarpeta);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del archivo de texto que desea crear: ");
                    String nombreArchivo = scanner.next();
                    crearArchivoTexto(directorioActual, nombreArchivo);
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void mostrarContenidoDirectorio(String directorio) {
        File directorioActual = new File(directorio);
        File[] archivos = directorioActual.listFiles();

        System.out.println("\nContenido de " + directorioActual.getAbsolutePath() + ":");
        if (archivos != null) {
            for (File archivo : archivos) {
                System.out.println(archivo.getName() + (archivo.isDirectory() ? " [Carpeta]" : " [Archivo]"));
            }
        }
    }

    private static String navegarACarpeta(String directorioActual, String carpetaDestino) {
        File nuevaCarpeta = new File(directorioActual, carpetaDestino);
        if (nuevaCarpeta.isDirectory() && nuevaCarpeta.exists()) {
            return nuevaCarpeta.getAbsolutePath();
        } else {
            System.out.println("La carpeta especificada no existe.");
            return directorioActual;
        }
    }

    private static void crearCarpeta(String directorioActual, String nombreCarpeta) {
        File nuevaCarpeta = new File(directorioActual, nombreCarpeta);
        if (nuevaCarpeta.mkdir()) {
            System.out.println("Carpeta creada exitosamente.");
        } else {
            System.out.println("No se pudo crear la carpeta.");
        }
    }

    private static void crearArchivoTexto(String directorioActual, String nombreArchivo) {
        File nuevoArchivo = new File(directorioActual, nombreArchivo);
        try {
            if (nuevoArchivo.createNewFile()) {
                System.out.println("Archivo de texto creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el archivo de texto.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}