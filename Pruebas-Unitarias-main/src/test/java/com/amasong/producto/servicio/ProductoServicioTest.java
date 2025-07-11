package com.amasong.producto.servicio;

import com.amasong.producto.modelo.Producto;
import com.amasong.producto.repositorio.ProductoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServicioTest {

    @Mock
    private ProductoRepositorio repositorio;

    @InjectMocks
    private ProductoServicio servicio;

    @Test
    void testObtenerTodos() {
        Producto p1 = new Producto(1L, "Polera", "Polera negra", "Ropa", 12000, 10);
        Producto p2 = new Producto(2L, "Zapatilla", "Zapatilla blanca", "Calzado", 40000, 5);

        when(repositorio.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> productos = servicio.obtenerTodos();

        assertEquals(2, productos.size());
        assertEquals("Polera", productos.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        Producto producto = new Producto(1L, "Gorra", "Gorra roja", "Accesorios", 8000, 20);

        when(repositorio.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = servicio.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Gorra", resultado.get().getNombre());
    }

    @Test
    void testGuardar() {
        Producto producto = new Producto(null, "Poleron", "Poleron con capucha", "Ropa", 25000, 12);

        when(repositorio.save(producto)).thenReturn(producto);

        Producto guardado = servicio.guardar(producto);

        assertNotNull(guardado);
        assertEquals("Poleron", guardado.getNombre());
    }

    @Test
    void testEliminar() {
        Long id = 1L;
        doNothing().when(repositorio).deleteById(id);

        servicio.eliminar(id);

        verify(repositorio, times(1)).deleteById(id);
    }

    @Test
    void testObtenerPorCategoria() {
        Producto p1 = new Producto(1L, "Zapatos", "Zapatos deportivos", "Calzado", 20000, 15);
        Producto p2 = new Producto(2L, "Zapatillas", "Zapatillas blancas", "Calzado", 30000, 10);

        when(repositorio.findByCategoriaIgnoreCase("Calzado")).thenReturn(Arrays.asList(p1, p2));

        List<Producto> productos = servicio.obtenerPorCategoria("Calzado");

        assertEquals(2, productos.size());
        assertEquals("Zapatos", productos.get(0).getNombre());
    }
}
