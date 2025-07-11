package com.amasong.producto.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del producto", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nombre del producto", example = "Polera Nike")
    private String nombre;

    @Schema(description = "Descripción del producto", example = "Polera deportiva original")
    private String descripcion;

    @Schema(description = "Categoría del producto", example = "Ropa")
    private String categoria;

    @Min(0)
    @Schema(description = "Precio del producto", example = "14990")
    private double precio;

    @Min(0)
    @Schema(description = "Stock disponible", example = "25")
    private int stock;
}
