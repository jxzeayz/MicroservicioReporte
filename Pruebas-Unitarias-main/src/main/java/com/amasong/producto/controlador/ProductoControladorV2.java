package com.amasong.producto.controlador;

import com.amasong.producto.hateoas.ProductoModelAssembler;
import com.amasong.producto.modelo.Producto;
import com.amasong.producto.servicio.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/producto")
@Tag(name = "Producto V2", description = "Versión 2 con endpoints personalizados y enlaces HATEOAS")
public class ProductoControladorV2 {

    @Autowired
    private ProductoServicio servicio;

    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Listar productos", description = "Devuelve todos los productos con enlaces HATEOAS")
    public CollectionModel<EntityModel<Producto>> listarProductos() {
        List<Producto> productos = servicio.obtenerTodos();
        List<EntityModel<Producto>> productosModel = productos.stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(productosModel,
                linkTo(methodOn(ProductoControladorV2.class).listarProductos()).withSelfRel());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto con enlaces HATEOAS")
    public EntityModel<Producto> obtenerProducto(@PathVariable Long id) {
        Producto producto = servicio.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return assembler.toModel(producto);
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Listar productos por categoría", description = "Devuelve los productos de una categoría específica")
    public CollectionModel<EntityModel<Producto>> productosPorCategoria(@PathVariable String categoria) {
        List<EntityModel<Producto>> productos = servicio.obtenerPorCategoria(categoria).stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoControladorV2.class).productosPorCategoria(categoria)).withSelfRel(),
                linkTo(methodOn(ProductoControladorV2.class).listarProductos()).withRel("todos-los-productos"));
    }
}
