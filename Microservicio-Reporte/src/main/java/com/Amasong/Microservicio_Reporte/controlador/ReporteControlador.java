package com.Amasong.Microservicio_Reporte.controlador;

import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import com.Amasong.Microservicio_Reporte.servicio.ReporteServicio;
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
