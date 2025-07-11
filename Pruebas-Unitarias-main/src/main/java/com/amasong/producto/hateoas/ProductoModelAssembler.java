package com.amasong.producto.hateoas;

import com.amasong.producto.controlador.ProductoControladorV2;
import com.amasong.producto.modelo.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControladorV2.class).obtenerProducto(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoControladorV2.class).listarProductos()).withRel("productos"));
    }
}
