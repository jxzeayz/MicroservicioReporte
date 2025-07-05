package com.Amasong.Microservicio_Reporte.repositorio;

import com.Amasong.Microservicio_Reporte.modelo.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteRepositorio extends JpaRepository<Reporte, Long> {
    List<Reporte> findByTipoReporte(String tipoReporte); // Búsqueda por tipo
}