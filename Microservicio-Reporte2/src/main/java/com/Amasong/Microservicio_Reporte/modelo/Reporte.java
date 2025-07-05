package com.Amasong.Microservicio_Reporte.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reportes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreReporte;

    @Column(nullable = false)
    private String tipoReporte; // "INVENTARIO", "VENTAS", "PAGOS"

    @Column(nullable = false, columnDefinition = "TEXT")
    private String datosReporte; // JSON almacenado como String ej: "{\"productos\": 150, \"stockMinimo\": 10}"

    @Column(nullable = false)
    private LocalDate fechaGeneracion;
}

