package com.Amasong.Microservicio_Reporte.controlador;

import com.Amasong.Microservicio_Reporte.assembler.ReporteModelAssembler;
import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import com.Amasong.Microservicio_Reporte.servicio.ReporteServicio;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/reportes")
public class ReporteControladorV2 {

    private final ReporteServicio servicio;
    private final ReporteModelAssembler assembler;

    public ReporteControladorV2(ReporteServicio servicio, ReporteModelAssembler assembler) {
        this.servicio = servicio;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Reporte>> getAllReportes() {
        List<EntityModel<Reporte>> reportes = servicio.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(reportes,
            linkTo(methodOn(ReporteControladorV2.class).getAllReportes()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Reporte> getReporte(@PathVariable Long id) {
        Reporte reporte = servicio.findById(id);
        return assembler.toModel(reporte);
    }

    @GetMapping("/tipo/{tipo}")
    public CollectionModel<EntityModel<Reporte>> getReportesByTipo(@PathVariable String tipo) {
        List<EntityModel<Reporte>> reportes = servicio.findByTipo(tipo).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
            
        return CollectionModel.of(reportes,
            linkTo(methodOn(ReporteControladorV2.class).getReportesByTipo(tipo)).withSelfRel(),
            linkTo(methodOn(ReporteControladorV2.class).getAllReportes()).withRel("todos-reportes"));
    }

    // Más endpoints con HATEOAS...
}