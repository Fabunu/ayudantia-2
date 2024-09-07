package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventarioTiendaTest {
    private Object[][] productos;

    @BeforeEach
    public void setUp() {
        productos = new Object[10][3];
        InventarioTienda.inicializarProductos(productos);
    }

    @Test
    public void testAgregarProductos() {
        InventarioTienda.agregarProductos(productos, 1, 10);
        assertEquals(60, productos[0][2]);
    }

    @Test
    public void testRestarProductos() {
        InventarioTienda.restarProductos(productos, 2, 10);
        assertEquals(20, productos[1][2]);
    }

    @Test
    public void testRestarProductosInsuficientes() {
        InventarioTienda.restarProductos(productos, 3, 25);
        assertEquals(20, productos[2][2]); // El stock no debe cambiar
    }

    @Test
    public void testConsultarDisponibilidad() {
        String output = InventarioTienda.consultarDisponibilidad(productos, 1);
        assertEquals("Producto: Lapiz - cantidad disponible: 50", output.trim());
    }

    @Test
    public void testListarProductos() {
        String output = InventarioTienda.listarProductos(productos);
        assertTrue(output.contains("ID: 1 | Nombre: Lapiz | Cantidad: 50"));
        assertTrue(output.contains("ID: 2 | Nombre: Cuaderno | Cantidad: 30"));
        assertTrue(output.contains("ID: 3 | Nombre: Borrador | Cantidad: 20"));
    }
}
