package com.Amasong.Microservicio_Reporte.assembler;

import com.Amasong.Microservicio_Reporte.controlador.ReporteControladorV2;
import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReporteModelAssembler implements RepresentationModelAssembler<Reporte, EntityModel<Reporte>> {

    @Override
    public EntityModel<Reporte> toModel(Reporte reporte) {
        return EntityModel.of(reporte,
            linkTo(methodOn(ReporteControladorV2.class).getReporte(reporte.getId())).withSelfRel(),
            linkTo(methodOn(ReporteControladorV2.class).getAllReportes()).withRel("reportes"),
            linkTo(methodOn(ReporteControladorV2.class).getReportesByTipo(reporte.getTipoReporte())).withRel("reportes-por-tipo"));
    }
}