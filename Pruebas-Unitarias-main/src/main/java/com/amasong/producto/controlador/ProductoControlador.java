package com.amasong.producto.controlador;

import com.amasong.producto.modelo.Producto;
import com.amasong.producto.servicio.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
@Tag(name = "Producto", description = "Operaciones CRUD sobre productos (sin HATEOAS)")
public class ProductoControlador {

    @Autowired
    private ProductoServicio servicio;

    @GetMapping("/prueba")
    @Operation(summary = "Prueba de conexión", description = "Devuelve un mensaje de prueba")
    public String prueba() {
        return "Hola desde el controlador";
    }

    @GetMapping
    @Operation(summary = "Listar productos", description = "Devuelve una lista de productos sin enlaces HATEOAS")
    public List<Producto> listarProductos() {
        return servicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto", description = "Devuelve un producto por su ID sin enlaces HATEOAS")
    public Producto obtenerProducto(@PathVariable Long id) {
        return servicio.obtenerPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    @PostMapping
    @Operation(summary = "Guardar producto", description = "Guarda un nuevo producto en la base de datos")
    public Producto guardarProducto(@RequestBody Producto producto) {
        return servicio.guardar(producto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por su ID")
    public void eliminarProducto(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
