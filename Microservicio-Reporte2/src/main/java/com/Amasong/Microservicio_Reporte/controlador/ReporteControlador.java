package com.Amasong.Microservicio_Reporte.controlador;

import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import com.Amasong.Microservicio_Reporte.servicio.ReporteServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteControlador {
    @Autowired
    private ReporteServicio servicio;

        @Operation(summary = "Obtener todos los reportes", description = "Retorna una lista de todos los reportes generados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = Reporte.class)))),
        @ApiResponse(responseCode = "204", description = "No hay reportes disponibles")
    })

    @GetMapping
    public ResponseEntity<List<Reporte>> listar() {
        List<Reporte> reportes = servicio.findAll();
        return reportes.isEmpty() 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.ok(reportes);
    }

    @PostMapping
    public ResponseEntity<Reporte> guardar(@RequestBody Reporte reporte) {
        Reporte nuevoReporte = servicio.save(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoReporte);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> buscarPorId(@PathVariable Long id) {
        Reporte reporte = servicio.findById(id);
        return reporte != null 
            ? ResponseEntity.ok(reporte) 
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Reporte>> buscarPorTipo(@PathVariable String tipo) {
        List<Reporte> reportes = servicio.findByTipo(tipo);
        return reportes.isEmpty() 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.ok(reportes);
    }
}
