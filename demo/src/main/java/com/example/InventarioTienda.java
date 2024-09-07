package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InventarioTienda {
    
    public static void main(String[] args) {
        Object[][] productos = new Object[10][3];

        inicializarProductos(productos);
        Scanner scanner = new Scanner(System.in); // scanner
        ejecutarMenu(productos, scanner); //ejecuta el menu
        scanner.close();
    }

    public static void ejecutarMenu(Object[][] productos, Scanner scanner) {
        int opcion;

        do { 
            mostrarMenu(); //muestra las opciones qu etiene el menu
            opcion = leerOpcion(scanner);
            ejecutarOpcion(opcion, productos, scanner); //lee la opcion ingresada y l ejecuta
        } while (opcion != 5); //hacer algun cambio si es que
        
    }

    public static void mostrarMenu() {
        System.out.println("\n--- Bienvenido al menu --- ");
        System.out.println("1. Agregar productos al inventario");
        System.out.println("2. Restar productos al inventario"); 
        System.out.println("3. Consultar disponibilidad de un producto");
        System.out.println("4. Listar todos los productos");
        System.out.println("5. Salir");
        System.out.print("Ingrese una opcion: ");
    }  

    public static int leerOpcion(Scanner scanner) {
        int opcion = 0;

        while(true) {
            try {
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    if (opcion >= 1 && opcion <= 5) {
                        break;

                    } else {

                        System.out.println("Opcion invalida. Intentelo de nuevo");
                        scanner.next();
                    }
                } else {
                    System.out.println("Entrada no valida. Ingrese un numero");
                    scanner.next(); //limpia la entrada mala
                }    
            } catch (InputMismatchException e) {
                System.out.println("Error: entrada invalida. Debe ingresar un numero entero"); //Esto es si se ingresa algun decimal
                scanner.next(); //limpia...
            }   
        }
        return opcion;
    }

    public static void ejecutarOpcion(int opcion, Object[][] productos, Scanner scanner) {
        switch (opcion) { //ejecuta la cada opcion del menu
            case 1:
                System.out.print("Ingrese el ID del producto: ");
                int idAgregar = scanner.nextInt();
                System.out.print("Ingrese la cantidad a agregar: ");
                int cantidadAgregar = scanner.nextInt();

                agregarProductos(productos, idAgregar, cantidadAgregar);
            break;

            case 2:
                System.out.print("Ingrese el ID del producto: ");
                int idRestar = scanner.nextInt();
                System.out.print("Ingrese la cantidad a restar");
                int cantidadRestar = scanner.nextInt();

                restarProductos(productos, idRestar, cantidadRestar);
            break;

            case 3:
            System.out.print("Ingrese el ID del producto: ");
            int idConsultar = scanner.nextInt();

            consultarDisponibilidad(productos, idConsultar);
            break;

            case 4:
            System.out.println("\n--- Listado de productos ---");
            listarProductos(productos);
            break;

            case 5:
            System.out.println("Saliendo del programa...");
            break;

            default: 
                System.out.println("Opcion no valida");
                break;
        }
    }

    public static void agregarProductos(Object[][] productos, int idProducto, int cantidad) {
        
        try {
            for (int i = 0; i < productos.length; i++) {
                if (productos [i][0] != null && (int) productos[i][0] == idProducto) {
                    productos [i][2] = (int) productos [i][2] + cantidad;
                    System.out.println("Producto actualizado: " + productos[i][1] + "- cantidad: " + productos[i][2]);
                    return;
                }
            } 
            System.out.println("Error. No se encuentra el producto");   
        } catch (Exception e) {
            System.out.println("Error. Entrada invalida. Verifique El ID del producto y la cantidad");
        }
    }  

    public static void restarProductos(Object[][] productos, int idProducto, int cantidad) {
        
        try {
            for (int i = 0; i < productos.length; i++) {
                if (productos [i][0] != null && (int) productos [i][0] == idProducto) {
                    if ((int) productos[i][2] >= cantidad) {
                        productos[i][2] = (int) productos[i][2] - cantidad;
                        System.out.println("Producto actualizado: " + productos[i][1] + "- cantidad" + productos[i][2]);
                    } else {
                        System.out.println("Error: no hay suficiente stock");
                    }
                    return; 
                }       
            }
            System.out.println("Error. No se encontro el producto co ID" + idProducto);
        } catch (Exception e) {
            System.out.println("Ocurrio un error al restar productos: " + e.getMessage());
        }
    }

    public static void consultarDisponibilidad(Object[][] productos, int idProducto) {
        try {
            for(int i = 0; i < productos.length; i++) {
                if (productos[i][0] != null && (int) productos[i][0] == idProducto) {
                    System.out.println("Producto: " + productos[i][1] + "- cantidad disponible: " + productos[i][2]);
                    return;
                }
            }
            System.out.println("Error, prodcuto no encontrado");
        } catch (Exception e) {
            System.out.println("Ocurrio un error al cosultar la diponibilidad: " + e.getMessage());
        }
    }

    public static void listarProductos(Object[][] productos) {
        try {
            for (int i = 0; i < productos.length; i++) {
                if (productos[i][0] != null) {
                    System.out.println("ID: " + productos[i][0] + "| Nombre: " + productos[i][1] + " | Cantidad: " + productos[i][2]);
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error al listar productos: " + e.getMessage());
        }
    }

    public static void inicializarProductos(Object[][] productos) {
        productos[0][0] = 1; productos[0][1] = "Lapiz"; productos[0][2] = 50;
        productos[1][0] = 2; productos[1][1] = "Cuaderno"; productos[1][2] = 30;
        productos[2][0] = 3; productos[2][1] = "Borrador"; productos[2][2] = 20;
    }
}
